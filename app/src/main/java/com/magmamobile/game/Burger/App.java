package com.magmamobile.game.Burger;

import android.graphics.Paint;
import android.graphics.Typeface;
import com.magmamobile.game.Burger.display.CartoonFader;
import com.magmamobile.game.Burger.display.PauseFader;
import com.magmamobile.game.Burger.display.ScrollBG;
import com.magmamobile.game.Burger.display.SunshineBG;
import com.magmamobile.game.Burger.game.MenuInfo;
import com.magmamobile.game.Burger.managers.AchievementManager;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.GalleryManager;
import com.magmamobile.game.Burger.managers.GameManager;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.Burger.managers.SoundManager;
import com.magmamobile.game.Burger.managers.UIManager;
import com.magmamobile.game.Burger.managers.UndoManager;
import com.magmamobile.game.Burger.stages.Achievement;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.Burger.stages.Config;
import com.magmamobile.game.Burger.stages.EndGame;
import com.magmamobile.game.Burger.stages.Gallery;
import com.magmamobile.game.Burger.stages.GoodJob;
import com.magmamobile.game.Burger.stages.Home;
import com.magmamobile.game.Burger.stages.LevelSelect;
import com.magmamobile.game.Burger.stages.ModeSelect;
import com.magmamobile.game.Burger.stages.NewItem;
import com.magmamobile.game.Burger.stages.PauseGame;
import com.magmamobile.game.Burger.stages.QuitGame;
import com.magmamobile.game.Burger.ui.TrophyAlert;
import com.magmamobile.game.engine.*;

// Referenced classes of package com.magmamobile.game.Burger:
//            AppParams

public final class App extends GameApplication
{

    public static final boolean BETA_MODE = false;
    public static final boolean DEBUG_MODE = false;
    public static final int EXT_AOTD = 13;
    public static final int EXT_FACEBOOK = 14;
    public static final int EXT_SHARE = 15;
    public static final boolean NOPUB_MODE = false;
    public static final int STAGE_CONFIG = 3;
    public static final int STAGE_ENDGAME = 11;
    public static final int STAGE_GALLERY = 4;
    public static final int STAGE_GAME = 8;
    public static final int STAGE_GOODJOB = 9;
    public static final int STAGE_HOME = 1;
    public static final int STAGE_LEVEL = 6;
    public static final int STAGE_MODE = 5;
    public static final int STAGE_NEWITEM = 7;
    public static final int STAGE_PAUSEGAME = 10;
    public static final int STAGE_QUITGAME = 12;
    public static final int STAGE_TROPHY = 2;
    public static boolean alternateFont;
    public static boolean bigFont;
    private static final String bigLangs[] = {
        "ja", "ru", "el"
    };
    public static Typeface defaultFont;
    public static CartoonFader fader;
    private static final String langs[] = {
        "lt", "tr", "pl", "sr", "cs", "hr", "sk"
    };
    private static Paint pDebug;
    public static PauseFader pause;
    private static boolean running = false;
    public static Typeface scoreFont;
    public static ScrollBG scrollBG;
    public static SunshineBG shineBG;
    private static final String syslangs[] = {
        "el"
    };
    public static VelocityTracker tracker;
    public static TrophyAlert trophy;

    public App()
    {
    }

    public static void Quit()
    {
        running = false;
        Game.Quit();
    }

    public static Paint getStroked(Paint paint)
    {
        return getStroked(paint, 1.0F, 0xff000000);
    }

    public static Paint getStroked(Paint paint, float f)
    {
        return getStroked(paint, f, 0xff000000);
    }

    public static Paint getStroked(Paint paint, float f, int i)
    {
        Paint paint1 = new Paint(paint);
        paint1.setColor(i);
        paint1.setStyle(android.graphics.Paint.Style.STROKE);
        paint1.setStrokeCap(android.graphics.Paint.Cap.ROUND);
        paint1.setStrokeJoin(android.graphics.Paint.Join.ROUND);
        paint1.setStrokeWidth(f);
        return paint1;
    }

    public static final boolean hd()
    {
        return Game.isHD();
    }

    private void initManagers()
    {
        if(!BitmapManager.inited)
        {
            BitmapManager.init();
        }
        SoundManager.init();
        UIManager.init();
        PrefManager.init();
        GameManager.init();
        MenuInfo.init();
        AchievementManager.init();
        UndoManager.init();
        GalleryManager.init();
    }

    private boolean isBigLang()
    {
        String s = Game.getResString(R.string.lang);
        String as[] = bigLangs;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                return false;
            }
            if(s.equals(as[j]))
            {
                return true;
            }
            j++;
        } while(true);
    }

    private boolean isSystemLang()
    {
        String s = Game.getResString(R.string.lang);
        String as[] = syslangs;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                return false;
            }
            if(s.equals(as[j]))
            {
                return true;
            }
            j++;
        } while(true);
    }

    private boolean isTroubleLang()
    {
        String s = Game.getResString(R.string.lang);
        String as[] = langs;
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                return false;
            }
            if(s.equals(as[j]))
            {
                return true;
            }
            j++;
        } while(true);
    }

    public static float scalefFont(float f)
    {
        if(alternateFont || bigFont)
        {
            return Game.scalef(0.8F * f);
        } else
        {
            return Game.scalef(f);
        }
    }

    public static void showDebug()
    {
    }

    public static void showTrophy()
    {
        if(trophy.isVisible())
        {
            trophy.draw();
        }
    }

    public static void trace(float f)
    {
    }

    public static void trace(int i)
    {
    }

    public static void trace(Boolean boolean1)
    {
    }

    public static void trace(String s)
    {
    }

    public static void trace(int ai[])
    {
        trace(ai, ", ");
    }

    public static void trace(int ai[], String s)
    {
    }

    public AppParameters onCreateParameters()
    {
        return new AppParams();
    }

    public void onEngineInitialize()
    {
        if(running && StageManager.getStageCount() > 0)
        {
            return;
        }
        running = true;
        super.onEngineInitialize();
        float f;
        if(hd())
        {
            f = 1.5F;
        } else
        {
            f = 1.0F;
        }
        Game.setMultiplier(f);
        initManagers();
        scrollBG = new ScrollBG();
        shineBG = new SunshineBG();
        fader = new CartoonFader();
        fader.init();
        pause = new PauseFader();
        pDebug = new Paint();
        pDebug.setTextSize(20F);
        pDebug.setColor(0xff000000);
        trophy = new TrophyAlert();
        if(isSystemLang())
        {
            defaultFont = Typeface.SANS_SERIF;
        } else
        if(isTroubleLang())
        {
            alternateFont = true;
            defaultFont = Label.loadTypeface("foo.ttf");
            Game.getParameters().DEFAULT_LABEL_TYPEFACE = "foo.ttf";
        } else
        {
            defaultFont = Label.loadTypeface("BradBunR.ttf");
            Game.getParameters().DEFAULT_LABEL_TYPEFACE = "BradBunR.ttf";
        }
        bigFont = isBigLang();
        scoreFont = Label.loadTypeface("linesquare.ttf");
        if(tracker == null)
        {
            tracker = VelocityTracker.obtain();
        }
        addStage(com.magmamobile.game.Burger.stages.Home.class);
        addStage(com.magmamobile.game.Burger.stages.Achievement.class);
        addStage(com.magmamobile.game.Burger.stages.Config.class);
        addStage(com.magmamobile.game.Burger.stages.Gallery.class);
        addStage(com.magmamobile.game.Burger.stages.ModeSelect.class);
        addStage(com.magmamobile.game.Burger.stages.LevelSelect.class);
        addStage(com.magmamobile.game.Burger.stages.NewItem.class);
        addStage(com.magmamobile.game.Burger.stages.Board.class);
        addStage(com.magmamobile.game.Burger.stages.GoodJob.class);
        addStage(com.magmamobile.game.Burger.stages.PauseGame.class);
        addStage(com.magmamobile.game.Burger.stages.EndGame.class);
        addStage(com.magmamobile.game.Burger.stages.QuitGame.class);
        setFirstStage(1);
    }

}
