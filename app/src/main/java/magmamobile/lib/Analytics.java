package magmamobile.lib;

import android.content.Context;
import android.util.Log;

public final class Analytics
{

    private static boolean _d = false;

    public Analytics()
    {
    }

    private static final void Log(String s)
    {
        android.util.Log.d("-= Analytics =-", s);
    }

    public static final boolean endSession()
    {
        try
        {
            if(_d)
            {
                Log("endSession");
            }
        }
        catch(Exception exception)
        {
            if(_d)
            {
                exception.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static final String getVersion()
    {
        return "1.5.1";
    }

    public static final boolean isDebug()
    {
        return _d;
    }

    public static final void setDebug(boolean flag)
    {
        _d = flag;
    }

    public static final Object startSession(Context context, String s)
    {
        try
        {
            if(_d)
            {
                Log((new StringBuilder()).append("startSession ").append(s).toString());
            }
        }
        catch(Exception exception)
        {
            if(_d)
            {
                exception.printStackTrace();
            }
            return null;
        }
        return null;
    }

    public static final boolean track(String s)
    {
        try
        {
            if(_d)
            {
                Log((new StringBuilder()).append("track ").append(s).toString());
            }
        }
        catch(Exception exception)
        {
            if(_d)
            {
                exception.printStackTrace();
            }
            return false;
        }
        return true;
    }

    public static final boolean trackAndDispatch(String s)
    {
        try
        {
            if(_d)
            {
                Log((new StringBuilder()).append("trackAndDispatch ").append(s).toString());
            }
        }
        catch(Exception exception)
        {
            if(_d)
            {
                exception.printStackTrace();
            }
            return false;
        }
        return true;
    }

}
