package com.magmamobile.game.engine;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import java.io.File;

public final class ScoreManager
{
    public static class BaseFormater
        implements ScoreFormater
    {

        public void onFormat(ScoreItem scoreitem)
        {
        }

        public BaseFormater()
        {
        }
    }

    public static interface ScoreFormater
    {

        public abstract void onFormat(ScoreItem scoreitem);
    }

    public enum Sorting
    {
        ascending("ascending", 0),
        descending("descending", 1);

        static
        {
            Sorting asorting[] = new Sorting[2];
            asorting[0] = ascending;
            asorting[1] = descending;
        }

        private Sorting(String s, int i)
        {
        }
    }


    private static final String SQL_CREATE = "CREATE TABLE IF NOT EXISTS scores (user TEXT,score REAL,mode INTEGER,time INTEGER);";
    private static final String SQL_FILE = "scores";
    private static final String SQL_INSERT = "INSERT INTO scores (user,score,mode,time) VALUES (%s,%s,%s,%s);";
    private static final String SQL_SELECT1 = "SELECT user,score FROM scores WHERE mode=%s ORDER BY score %s LIMIT %s;";
    private static Context _context;
    private static SQLiteDatabase _database;
    private static ScoreFormater _formater;
    private static int _mode;
    private static int _pageSize;
    private static Sorting _sort;
    private static String _user;

    public ScoreManager()
    {
    }

    public static SQLiteDatabase getDatabase()
    {
        return _database;
    }

    public static File getDatabaseFile()
    {
        return _context.getDatabasePath("scores");
    }

    public static ScoreFormater getFormater()
    {
        return _formater;
    }

    public static int getMode()
    {
        return _mode;
    }

    public static int getPageSize()
    {
        return _pageSize;
    }

    public static ScoreItem getScore()
    {
        Object aobj[] = new Object[3];
        aobj[0] = toSQL(_mode);
        aobj[1] = getSorting();
        aobj[2] = Integer.valueOf(1);
        String s = String.format("SELECT user,score FROM scores WHERE mode=%s ORDER BY score %s LIMIT %s;", aobj);
        Cursor cursor = _database.rawQuery(s, null);
        ScoreItem scoreitem = null;
        if(cursor != null)
        {
            boolean flag = cursor.moveToFirst();
            scoreitem = null;
            if(flag)
            {
                scoreitem = new ScoreItem();
                scoreitem.pos = 1;
                scoreitem.user = cursor.getString(0);
                scoreitem.score = cursor.getDouble(1);
                scoreitem.mine = _user.equals(scoreitem.user);
                if(_formater != null)
                {
                    _formater.onFormat(scoreitem);
                } else
                {
                    scoreitem.sScore = String.valueOf(scoreitem.score);
                    scoreitem.sUser = String.valueOf(1).concat(" ").concat(scoreitem.user);
                }
            }
            cursor.close();
        }
        return scoreitem;
    }

    public static ScoreList getScores()
    {
        ScoreList scorelist = new ScoreList();
        Cursor cursor;
        ScoreItem scoreitem;
        Object aobj[] = new Object[3];
        aobj[0] = toSQL(_mode);
        aobj[1] = getSorting();
        aobj[2] = Integer.valueOf(_pageSize);
        String s = String.format("SELECT user,score FROM scores WHERE mode=%s ORDER BY score %s LIMIT %s;", aobj);
        cursor = _database.rawQuery(s, null);
        if(cursor.moveToFirst()) {
            int i = 1;
            while (true) {
                scoreitem = new ScoreItem();
                scoreitem.pos = i;
                scoreitem.user = cursor.getString(0);
                scoreitem.score = cursor.getDouble(1);
                scoreitem.mine = _user.equals(scoreitem.user);
                if (_formater == null) {
                    try {
                        scoreitem.sScore = String.valueOf(scoreitem.score);
                        scoreitem.sUser = String.valueOf(i).concat(" ").concat(scoreitem.user);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                        return scorelist;
                    }
                } else
                    _formater.onFormat(scoreitem);
                scorelist.add(scoreitem);
                i++;
                if (!cursor.moveToNext())
                    break;
            }
        }
        cursor.close();
        return scorelist;
    }

    public static Sorting getSort()
    {
        return _sort;
    }

    private static String getSorting()
    {
        if(_sort == Sorting.ascending)
        {
            return "ASC";
        } else
        {
            return "DESC";
        }
    }

    public static String getUser()
    {
        return _user;
    }

    public static void setFormater(ScoreFormater scoreformater)
    {
        _formater = scoreformater;
    }

    public static void setMode(int i)
    {
        _mode = i;
    }

    public static void setPageSize(int i)
    {
        _pageSize = i;
    }

    public static void setSort(Sorting sorting)
    {
        _sort = sorting;
    }

    public static void setUser(String s)
    {
        _user = s;
    }

    public static void start(Context context)
    {
        stop();
        _mode = 0;
        _pageSize = 100;
        _user = "Me";
        _context = context;
        _sort = Sorting.ascending;
        _database = SQLiteDatabase.openOrCreateDatabase(getDatabaseFile(), null);
        _database.execSQL("CREATE TABLE IF NOT EXISTS scores (user TEXT,score REAL,mode INTEGER,time INTEGE" +
"R);"
);
    }

    public static void stop()
    {
        if(_database == null)
        {
            return;
        } else
        {
            _database.close();
            _database = null;
            _context = null;
            return;
        }
    }

    public static void submitScore(double d)
    {
        try
        {
            Object aobj[] = new Object[4];
            aobj[0] = toSQL(_user);
            aobj[1] = toSQL(d);
            aobj[2] = toSQL(_mode);
            aobj[3] = toSQL(SystemClock.elapsedRealtime());
            String s = String.format("INSERT INTO scores (user,score,mode,time) VALUES (%s,%s,%s,%s);", aobj);
            _database.execSQL(s);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void submitScore(int i, double d)
    {
        try
        {
            setMode(i);
            Object aobj[] = new Object[4];
            aobj[0] = toSQL(_user);
            aobj[1] = toSQL(d);
            aobj[2] = toSQL(_mode);
            aobj[3] = toSQL(SystemClock.elapsedRealtime());
            String s = String.format("INSERT INTO scores (user,score,mode,time) VALUES (%s,%s,%s,%s);", aobj);
            _database.execSQL(s);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private static String toSQL(double d)
    {
        return String.valueOf(d);
    }

    private static String toSQL(long l)
    {
        return String.valueOf(l);
    }

    private static String toSQL(String s)
    {
        return "'".concat(s.replace("'", "''")).concat("'");
    }
}
