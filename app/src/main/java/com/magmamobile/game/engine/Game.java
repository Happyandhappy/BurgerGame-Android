package com.magmamobile.game.engine;

import android.app.Activity;
import android.content.*;
import android.content.pm.*;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.*;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.*;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.*;
import android.widget.Toast;

import com.magmamobile.game.engine.ex.Vector2D;
import com.magmamobile.game.engine.features.FeatureWrapper;
import com.magmamobile.game.engine.features.FeatureWrapper01;
import com.magmamobile.game.engine.features.FeatureWrapper04;
import com.magmamobile.game.engine.features.FeatureWrapper05;
import com.magmamobile.game.engine.features.FeatureWrapper11;
import com.magmamobile.game.engine.features.FeatureWrapper18;
import com.magmamobile.game.engine.features.FeatureWrapper19;
import com.magmamobile.game.engine.tmp.AnimPackage;
import com.magmamobile.game.engine.tmp.text.StrokeTextDrawer;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.zip.ZipFile;

public final class Game
{

    public static final int CENTERED = 0x80000000;
    public static final byte COLOR_MODE_AUTO = 0;
    public static final byte COLOR_MODE_HIGH = 2;
    public static final byte COLOR_MODE_LOW = 1;
    public static final boolean DEBUG = false;
    public static final int ENGINE_MODE_BALANCED = 0;
    public static final int ENGINE_MODE_TEMPORAL = 1;
    protected static final int EVENT_HIDE_ADS = 0x10002;
    protected static final int EVENT_SHOW_ADS = 0x10001;
    protected static final int EVENT_SHOW_TOAST = 7;
    public static final long GAME_PAUSE = 1000L;
    public static final int GAME_STAGE_MAIN = 0;
    public static final int GAME_STAGE_QUIT = -1;
    public static final int MAX_VOLUME = 15;
    public static final byte ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_LANDSCAPE = 0;
    public static final int ORIENTATION_LANDSCAPE_REVERSE = 8;
    public static final int ORIENTATION_LANDSCAPE_SENSOR = 6;
    public static final int ORIENTATION_NOTSET = -1;
    public static final int ORIENTATION_PORTRAIT = 1;
    public static final int ORIENTATION_PORTRAIT_REVERSE = 9;
    public static final int ORIENTATION_PORTRAIT_SENSOR = 7;
    public static final int ORIENTATION_SENSOR = 4;
    public static final byte ORIENTATION_VERTICAL = 0;
    protected static Class Rdrawable;
    protected static Class Rid;
    protected static Class Rlayout;
    protected static Class Rstring;
    public static final int SOUND_STREAM = 3;
    public static final int TOUCH_ACTION_DOWN = 1;
    public static final int TOUCH_ACTION_MOVE = 2;
    public static final int TOUCH_ACTION_NONE = 0;
    public static final int TOUCH_ACTION_UP = 3;
    public static final int TOUCH_MODE_MANAGED = 0;
    public static final int TOUCH_MODE_TOPLEVEL = 2;
    public static final int TOUCH_MODE_UNMANAGED = 1;
    private static final long WAIT_TIMEOUT = 3000L;
    protected static Paint _alphaPaint;
    protected static Paint _bitBltPaint;
    protected static int _touchmode;
    private static long acCurTime;
    private static long acDeltaTime;
    private static boolean acLoop;
    private static long acNextTime;
    private static boolean acting;
    public static long actionFreq;
    public static GameRate actionRate;
    private static Object actionSync;
    public static Thread actionThread;
    protected static GameActivity activity;
    public static int androidSDKVersion;
    public static String appPackageName;
    public static int appVersionCode;
    public static String appVersionName;
    protected static GameApplication application;
    protected static SparseArray bitmapCache;
    public static Bitmap buffer;
    private static int charLastSize;
    private static Bitmap charLayer;
    private static int charRealX;
    private static int charRealY;
    private static int charXSpace;
    private static int charYSpace;
    protected static int colorMode;
    protected static DebugToolReceiver debugToolReceiver;
    protected static float density;
    protected static int displayHeight;
    protected static int displayWidth;
    private static RectF dr = new RectF();
    public static Canvas drawCanvas;
    public static Rect dstRect;
    public static PaintFlagsDrawFilter dwfilter;
    protected static int engineMode;
    protected static FeatureWrapper featureWrapper;
    public static GameRenderMode gameRenderMode;
    protected static String hash;
    protected static boolean hideActionBar;
    public static SurfaceHolder holder;
    protected static boolean isDebuggable;
    public static boolean isDirty;
    protected static boolean isiDTouch;
    protected static boolean lateResume;
    public static GameLayout layout;
    protected static Locale locale;
    public static int mBufferCH;
    public static int mBufferCW;
    public static int mBufferDiagonal;
    public static int mBufferHeight;
    public static int mBufferWidth;
    public static boolean mCLipFix;
    public static Canvas mCanvas;
    protected static float mDivRatioX;
    protected static float mDivRatioY;
    public static GameMessageHandler mHandler;
    protected static float mMulRatioX;
    protected static float mMulRatioY;
    public static boolean mNoStretch;
    public static Thread mThread;
    protected static MarketWrapper marketWrapper;
    protected static Matrix matrix;
    protected static boolean metaForFamilies;
    protected static String metaMarket;
    protected static boolean metaNoFullScreen;
    protected static boolean metaiDTGV;
    private static MMQiClient mmqi;
    protected static float multiplier;
    protected static SparseArray musicCache;
    protected static int ninePatchSize1 = 16;
    protected static int ninePatchSize2 = 32;
    protected static int ninePatchSize3 = 48;
    private static int ninePatchStyle = 0;
    public static boolean opAlias;
    public static boolean opFirst;
    public static boolean opHaptic;
    private static boolean opLoaded;
    public static boolean opMusic;
    public static boolean opSound;
    public static boolean opStretch;
    public static boolean opVibrate;
    public static int opVolume;
    public static boolean opWakeup;
    public static int orientation;
    private static int paintClr;
    private static Paint paintColor;
    protected static AppParameters parameters;
    public static boolean paused;
    protected static SharedPreferences preferences;
    protected static Rect rcDst;
    protected static Rect rcSrc;
    private static long reCurTime;
    private static long reDeltaTime;
    private static boolean reLoop;
    private static long reNextTime;
    public static boolean ready;
    public static long renderFreq;
    public static GameRate renderRate;
    private static Object renderSync;
    public static Thread renderThread;
    private static boolean rendering;
    protected static ErrorReporter reporter;
    private static String res_cache[];
    private static String res_d[];
    private static String res_l[];
    private static String res_lang;
    private static int res_langid;
    private static int res_t[][];
    public static boolean running;
    public static AudioManager soundMngr;
    public static SoundPool soundPool;
    protected static String sourceDir;
    public static Rect srcRect;
    private static StrokeTextDrawer stp;
    private static float stpf;
    protected static int surfaceHeight;
    protected static int surfaceWidth;
    private static Rect textBound;
    public static long tick;
    public static Typeface typeface;
    private static Vibrator vibrator;
    public static SurfaceView view;

    public Game()
    {
    }

    public static final boolean HasFacebookApp()
    {
        ApplicationInfo applicationinfo;
        boolean flag;
        try
        {
            applicationinfo = application.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
        }
        catch(Exception exception)
        {
            return false;
        }
        flag = false;
        if(applicationinfo != null)
        {
            flag = true;
        }
        return flag;
    }

    public static final void OnSystemUiVisibility(View view1, int i)
    {
        if((i & 4) == 0)
        {
            featureWrapper.setSystemUiVisibility(view1, 5894);
        }
    }

    public static final boolean OutOfBound(int i, int j, int k, int l)
    {
        return i < -k || j < -l || i + k > mBufferWidth || j + l > mBufferHeight;
    }

    public static final void Quit()
    {
        StageManager.setStage(-1);
    }

    protected static void UpdateDisplayData()
    {
        Display display = ((WindowManager)application.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        DisplayMetrics displaymetrics = new DisplayMetrics();
        Point point = featureWrapper.getRealSize(display);
        displayWidth = point.x;
        displayHeight = point.y;
        if(displayHeight >= displayWidth)
            orientation = 0;
        else
            orientation = 1;
        display.getMetrics(displaymetrics);
        density = displaymetrics.density;
    }

    private static final void addLegacyOverflowButton(Activity activity1)
    {
        if(androidSDKVersion >= 11)
        {
            ViewConfiguration viewconfiguration;
            Field field;
            try
            {
                activity1.getWindow().addFlags(android.view.WindowManager.LayoutParams.class.getField("FLAG_NEEDS_MENU_KEY").getInt(null));
            } catch(Exception exception) { }
            try
            {
                viewconfiguration = ViewConfiguration.get(activity1);
                field = android.view.ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
                if(field != null)
                {
                    field.setAccessible(true);
                    field.setBoolean(viewconfiguration, false);
                }
            }
            catch(Exception exception1)
            {
                return;
            }
        }
    }

    public static final void bitBltBitmap(Bitmap bitmap)
    {
        mCanvas.drawBitmap(bitmap, 0.0F, 0.0F, _bitBltPaint);
    }

    public static final void bitBltBitmap(Bitmap bitmap, float f, float f1)
    {
        mCanvas.drawBitmap(bitmap, f, f1, _bitBltPaint);
    }

    public static final int bufferToScreenX(int i)
    {
        return (int)(mDivRatioX * (float)i);
    }

    public static final int bufferToScreenY(int i)
    {
        return (int)(mDivRatioY * (float)i);
    }

    public static final int centerX(int i)
    {
        return mBufferWidth - i >> 1;
    }

    public static final int centerY(int i)
    {
        return mBufferHeight - i >> 1;
    }

    private static void checkErrorReporter(Context context)
    {
        if(reporter != null)
        {
            reporter.CheckErrorAndSendMail(context);
        }
    }

    public static final void clearBitmapPixel(Bitmap bitmap)
    {
        (new Canvas(bitmap)).drawColor(0, android.graphics.PorterDuff.Mode.SRC);
    }

    public static final void clipClear()
    {
        mCanvas.clipRect(0.0F, 0.0F, mBufferWidth, mBufferHeight, android.graphics.Region.Op.REPLACE);
    }

    public static final void clipRect(int i, int j, int k, int l)
    {
        mCanvas.clipRect(i, j, k, l, android.graphics.Region.Op.REPLACE);
    }

    public static final void clipTranslate(float f, float f1)
    {
        mCanvas.translate(f, f1);
    }

    public static final Bitmap cloneBitmap(Bitmap bitmap)
    {
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), getBitmapOptions().inPreferredConfig);
        Canvas canvas = new Canvas(bitmap1);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        canvas.drawBitmap(bitmap, 0.0F, 0.0F, paint);
        return bitmap1;
    }

    public static final Bitmap createBitmap(int i, int j)
    {
        return Bitmap.createBitmap(i, j, getBitmapOptions().inPreferredConfig);
    }

    public static final Bitmap createBitmap(byte abyte0[])
    {
        return BitmapFactory.decodeByteArray(abyte0, 0, abyte0.length, getBitmapOptions());
    }

    public static final float dpi(float f)
    {
        if(density == 1.0F)
        {
            return f;
        } else
        {
            return f * density;
        }
    }

    public static final int dpiToBufferX(float f)
    {
        if(density != 1.0F)
        {
            f = f * density * mMulRatioX;
        }
        return (int)f;
    }

    public static final int dpiToBufferY(float f)
    {
        if(density != 1.0F)
        {
            f = f * density * mMulRatioY;
        }
        return (int)f;
    }

    public static final void drawArc(RectF rectf, float f, float f1, boolean flag, Paint paint)
    {
        mCanvas.drawArc(rectf, f, f1, flag, paint);
    }

    public static final void drawBackground(int i)
    {
        mCanvas.drawBitmap(getBitmap(i), 0.0F, 0.0F, _bitBltPaint);
    }

    public static final void drawBackground(int i, float f, float f1)
    {
        mCanvas.drawBitmap(getBitmap(i), f, f1, _bitBltPaint);
    }

    public static final void drawBitmap(Bitmap bitmap)
    {
        mCanvas.drawBitmap(bitmap, 0.0F, 0.0F, null);
    }

    public static final void drawBitmap(Bitmap bitmap, float f, float f1)
    {
        mCanvas.drawBitmap(bitmap, f, f1, null);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j)
    {
        mCanvas.drawBitmap(bitmap, i, j, null);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, float f, Paint paint)
    {
        matrix.reset();
        if(k != 0)
        {
            matrix.postRotate(k);
        }
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        if(f != 1.0F)
        {
            matrix.postScale(f, f);
        }
        matrix.postTranslate(i, j);
        mCanvas.drawBitmap(bitmap, matrix, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, int l)
    {
        rcSrc.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rcDst.set(i, j, i + k, j + l);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, null);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1, Paint paint)
    {
        rcSrc.set(i, j, i + k, j + l);
        rcDst.set(i1, j1, i1 + k1, j1 + l1);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, int l, int i1, Paint paint)
    {
        matrix.reset();
        if(i1 != 0)
        {
            matrix.postRotate(i1);
        }
        matrix.preTranslate(k, l);
        matrix.postTranslate(i, j);
        mCanvas.drawBitmap(bitmap, matrix, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, int l, Paint paint)
    {
        rcSrc.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rcDst.set(i, j, i + k, j + l);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, int k, Paint paint)
    {
        matrix.reset();
        matrix.postRotate(k);
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        matrix.postTranslate(i, j);
        mCanvas.drawBitmap(bitmap, matrix, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, int i, int j, Paint paint)
    {
        mCanvas.drawBitmap(bitmap, i, j, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, Matrix matrix1, Paint paint)
    {
        mCanvas.drawBitmap(bitmap, matrix1, paint);
    }

    public static final void drawBitmap(Bitmap bitmap, Vector2D vector2d, float f, float f1)
    {
        matrix.reset();
        if(f != 0.0F)
        {
            matrix.postRotate(f);
        }
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        if(f1 != 1.0F)
        {
            matrix.postScale(f1, f1);
        }
        matrix.postTranslate(vector2d.x, vector2d.y);
        mCanvas.drawBitmap(bitmap, matrix, null);
    }

    public static final void drawBitmap(Bitmap bitmap, Vector2D vector2d, float f, float f1, int i)
    {
        matrix.reset();
        if(f != 0.0F)
        {
            matrix.postRotate(f);
        }
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        if(f1 != 1.0F)
        {
            matrix.postScale(f1, f1);
        }
        matrix.postTranslate(vector2d.x, vector2d.y);
        _alphaPaint.setAlpha(i);
        mCanvas.drawBitmap(bitmap, matrix, _alphaPaint);
    }

    public static void drawBitmap9(Bitmap bitmap, int i, int j, int k, int l, Paint paint)
    {
        Rect rect;
        Rect rect1;
        int i1;
        int j1;
        rect = rcSrc;
        rect1 = rcDst;
        i1 = k - ninePatchSize2;
        j1 = l - ninePatchSize2;
        if(ninePatchStyle != 0) {
            if(ninePatchStyle != 1)
                return;
            rect.set(0, 0, ninePatchSize1, ninePatchSize1);
            rect1.set(i, j, i + ninePatchSize1, j + ninePatchSize1);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            if(i1 > 0) {
                int j2 = i + ninePatchSize1;
                while (j2 < i1 + (i + ninePatchSize1)) {
                    rect.set(ninePatchSize1, 0, ninePatchSize2, ninePatchSize1);
                    rect1.set(j2, j, j2 + ninePatchSize1, j + ninePatchSize1);
                    mCanvas.drawBitmap(bitmap, rect, rect1, paint);
                    j2 += ninePatchSize1;
                }
            }
            rect.set(ninePatchSize2, 0, ninePatchSize3, ninePatchSize1);
            rect1.set((i + k) - ninePatchSize1, j, i + k, j + ninePatchSize1);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            if(j1 > 0) {
                rect.set(0, ninePatchSize1, ninePatchSize1, ninePatchSize2);
                rect1.set(i, j + ninePatchSize1, i + ninePatchSize1, j1 + (j + ninePatchSize1));
                mCanvas.drawBitmap(bitmap, rect, rect1, paint);
                if (i1 > 0) {
                    int l1 = i + ninePatchSize1;
                    while (l1 < i1 + (i + ninePatchSize1)) {
                        int i2 = j + ninePatchSize1;
                        while (i2 < j1 + (j + ninePatchSize1)) {
                            rect.set(ninePatchSize1, ninePatchSize1, ninePatchSize2, ninePatchSize2);
                            rect1.set(l1, i2, l1 + ninePatchSize1, i2 + ninePatchSize1);
                            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
                            i2 += ninePatchSize1;
                        }
                        l1 += ninePatchSize1;
                    }
                }
                rect.set(ninePatchSize2, ninePatchSize1, ninePatchSize3, ninePatchSize2);
                rect1.set((i + k) - ninePatchSize1, j + ninePatchSize1, i + k, j1 + (j + ninePatchSize1));
                mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            }
            rect.set(0, ninePatchSize2, ninePatchSize1, ninePatchSize3);
            rect1.set(i, (j + l) - ninePatchSize1, i + ninePatchSize1, j + l);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            if(i1 > 0) {
                int k1 = i + ninePatchSize1;
                while (k1 < i1 + (i + ninePatchSize1)) {
                    rect.set(ninePatchSize1, ninePatchSize2, ninePatchSize2, ninePatchSize3);
                    rect1.set(k1, (j + l) - ninePatchSize1, k1 + ninePatchSize1, j + l);
                    mCanvas.drawBitmap(bitmap, rect, rect1, paint);
                    k1 += ninePatchSize1;
                }
            }
            rect.set(ninePatchSize2, ninePatchSize2, ninePatchSize3, ninePatchSize3);
            rect1.set((i + k) - ninePatchSize1, (j + l) - ninePatchSize1, i + k, j + l);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            return;
        }
        rect.set(0, 0, ninePatchSize1, ninePatchSize1);
        rect1.set(i, j, i + ninePatchSize1, j + ninePatchSize1);
        mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        if(i1 > 0)
        {
            rect.set(ninePatchSize1, 0, ninePatchSize2, ninePatchSize1);
            rect1.set(i + ninePatchSize1, j, i1 + (i + ninePatchSize1), j + ninePatchSize1);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        }
        rect.set(ninePatchSize2, 0, ninePatchSize3, ninePatchSize1);
        rect1.set((i + k) - ninePatchSize1, j, i + k, j + ninePatchSize1);
        mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        if(j1 > 0)
        {
            rect.set(0, ninePatchSize1, ninePatchSize1, ninePatchSize2);
            rect1.set(i, j + ninePatchSize1, i + ninePatchSize1, j1 + (j + ninePatchSize1));
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            if(i1 > 0)
            {
                rect.set(ninePatchSize1, ninePatchSize1, ninePatchSize2, ninePatchSize2);
                rect1.set(i + ninePatchSize1, j + ninePatchSize1, i1 + (i + ninePatchSize1), j1 + (j + ninePatchSize1));
                mCanvas.drawBitmap(bitmap, rect, rect1, paint);
            }
            rect.set(ninePatchSize2, ninePatchSize1, ninePatchSize3, ninePatchSize2);
            rect1.set((i + k) - ninePatchSize1, j + ninePatchSize1, i + k, j1 + (j + ninePatchSize1));
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        }
        rect.set(0, ninePatchSize2, ninePatchSize1, ninePatchSize3);
        rect1.set(i, (j + l) - ninePatchSize1, i + ninePatchSize1, j + l);
        mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        if(i1 > 0)
        {
            rect.set(ninePatchSize1, ninePatchSize2, ninePatchSize2, ninePatchSize3);
            rect1.set(i + ninePatchSize1, (j + l) - ninePatchSize1, i1 + (i + ninePatchSize1), j + l);
            mCanvas.drawBitmap(bitmap, rect, rect1, paint);
        }
        rect.set(ninePatchSize2, ninePatchSize2, ninePatchSize3, ninePatchSize3);
        rect1.set((i + k) - ninePatchSize1, (j + l) - ninePatchSize1, i + k, j + l);
        mCanvas.drawBitmap(bitmap, rect, rect1, paint);
    }

    public static void drawBitmapAlpha(Bitmap bitmap, int i, int j, int k)
    {
        _alphaPaint.setAlpha(k);
        mCanvas.drawBitmap(bitmap, i, j, _alphaPaint);
    }

    public static void drawBitmapAlpha(Bitmap bitmap, int i, int j, int k, float f, int l)
    {
        _alphaPaint.setAlpha(l);
        matrix.reset();
        if(k != 0)
        {
            matrix.postRotate(k);
        }
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        if(f != 1.0F)
        {
            matrix.postScale(f, f);
        }
        matrix.postTranslate(i, j);
        mCanvas.drawBitmap(bitmap, matrix, _alphaPaint);
    }

    public static void drawBitmapAlpha(Bitmap bitmap, int i, int j, int k, int l, int i1)
    {
        _alphaPaint.setAlpha(i1);
        rcSrc.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        rcDst.set(i, j, i + k, j + l);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, _alphaPaint);
    }

    public static void drawBitmapCentered(Bitmap bitmap)
    {
        mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, mBufferHeight - bitmap.getHeight() >> 1, null);
    }

    public static void drawBitmapCentered(Bitmap bitmap, int i, int j)
    {
        if(i == 0x80000000)
        {
            if(j == 0x80000000)
            {
                mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, mBufferHeight - bitmap.getHeight() >> 1, null);
                return;
            } else
            {
                mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, j, null);
                return;
            }
        }
        if(j == 0x80000000)
        {
            mCanvas.drawBitmap(bitmap, i, mBufferHeight - bitmap.getHeight() >> 1, null);
            return;
        } else
        {
            mCanvas.drawBitmap(bitmap, i, j, null);
            return;
        }
    }

    public static void drawBitmapCentered(Bitmap bitmap, int i, int j, Paint paint)
    {
        if(i == 0x80000000)
        {
            if(j == 0x80000000)
                mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, mBufferHeight - bitmap.getHeight() >> 1, paint);
			else
                mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, j, paint);
        }else if(j == 0x80000000)
            mCanvas.drawBitmap(bitmap, i, mBufferHeight - bitmap.getHeight() >> 1, paint);
        else
            mCanvas.drawBitmap(bitmap, i, j, paint);
    }

    public static void drawBitmapCentered(Bitmap bitmap, Paint paint)
    {
        mCanvas.drawBitmap(bitmap, mBufferWidth - bitmap.getWidth() >> 1, mBufferHeight - bitmap.getHeight() >> 1, paint);
    }

    public static void drawBitmapFlipped(Bitmap bitmap, int i, int j, boolean flag, boolean flag1, Paint paint)
    {
        int k = -1;
        matrix.reset();
        Matrix matrix1 = matrix;
        int l;
        float f;
        Matrix matrix2;
        int i1;
        float f1;
        int j1;
        if(flag)
        {
            l = k;
        } else
        {
            l = 1;
        }
        f = l;
        if(!flag1)
        {
            k = 1;
        }
        matrix1.setScale(f, k);
        matrix2 = matrix;
        if(flag)
        {
            i1 = bitmap.getWidth();
        } else
        {
            i1 = 0;
        }
        f1 = i1 + i;
        if(flag1)
        {
            j1 = bitmap.getHeight();
        } else
        {
            j1 = 0;
        }
        matrix2.postTranslate(f1, j1 + j);
        mCanvas.drawBitmap(bitmap, matrix, paint);
    }

    public static void drawBitmapFlippedAlpha(Bitmap bitmap, int i, int j, boolean flag, boolean flag1, int k)
    {
        int l = -1;
        _alphaPaint.setAlpha(k);
        matrix.reset();
        Matrix matrix1 = matrix;
        int i1;
        float f;
        Matrix matrix2;
        int j1;
        float f1;
        int k1;
        if(flag)
        {
            i1 = l;
        } else
        {
            i1 = 1;
        }
        f = i1;
        if(!flag1)
        {
            l = 1;
        }
        matrix1.setScale(f, l);
        matrix2 = matrix;
        if(flag)
        {
            j1 = bitmap.getWidth();
        } else
        {
            j1 = 0;
        }
        f1 = j1 + i;
        if(flag1)
        {
            k1 = bitmap.getHeight();
        } else
        {
            k1 = 0;
        }
        matrix2.postTranslate(f1, k1 + j);
        mCanvas.drawBitmap(bitmap, matrix, _alphaPaint);
    }

    public static void drawBitmapFlippedAlpha(Bitmap bitmap, int i, int j, boolean flag, boolean flag1, int k, int l)
    {
        int i1 = -1;
        _alphaPaint.setAlpha(k);
        matrix.reset();
        Matrix matrix1 = matrix;
        int j1;
        float f;
        Matrix matrix2;
        int k1;
        float f1;
        int l1;
        if(flag)
        {
            j1 = i1;
        } else
        {
            j1 = 1;
        }
        f = j1;
        if(!flag1)
        {
            i1 = 1;
        }
        matrix1.setScale(f, i1);
        matrix.postRotate(l);
        matrix.preTranslate(-bitmap.getWidth() >> 1, -bitmap.getHeight() >> 1);
        matrix2 = matrix;
        if(flag)
        {
            k1 = bitmap.getWidth();
        } else
        {
            k1 = 0;
        }
        f1 = k1 + i;
        if(flag1)
        {
            l1 = bitmap.getHeight();
        } else
        {
            l1 = 0;
        }
        matrix2.postTranslate(f1, l1 + j);
        mCanvas.drawBitmap(bitmap, matrix, _alphaPaint);
    }

    public static final void drawBitmapLine(Bitmap bitmap, int i, int j, int k, int l, Paint paint)
    {
        int i1 = k - i;
        int j1 = l - j;
        int k1 = -bitmap.getHeight() >> 1;
        float f = (float)Math.sqrt(i1 * i1 + j1 * j1) / (float)bitmap.getWidth();
        float f1 = 57.29578F * (float)Math.atan2(j1, i1);
        matrix.reset();
        matrix.preTranslate(0.0F, k1);
        matrix.postScale(f, 1.0F);
        matrix.postRotate(f1);
        matrix.postTranslate(i, j);
        mCanvas.drawBitmap(bitmap, matrix, paint);
    }

    public static void drawBitmapLineAngle(Bitmap bitmap, int i, int j, int k, int l, Paint paint)
    {
        int i1 = k - i;
        int j1 = l - j;
        int k1 = -bitmap.getHeight() >> 1;
        int l1 = (int)Math.sqrt(i1 * i1 + j1 * j1);
        float f = 57.29578F * (float)Math.atan2(j1, i1);
        rcSrc.set(0, 0, l1, bitmap.getHeight());
        mCanvas.save();
        mCanvas.translate(i, j);
        mCanvas.rotate(f);
        mCanvas.translate(0.0F, k1);
        mCanvas.drawBitmap(bitmap, rcSrc, rcSrc, paint);
        mCanvas.restore();
    }

    public static void drawBitmapParcel(Bitmap bitmap, int i, int j, int k, int l)
    {
        rcSrc.set(0, 0, k, l);
        rcDst.set(i, j, i + k, j + l);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, null);
    }

    public static void drawBitmapParcel(Bitmap bitmap, int i, int j, int k, int l, int i1, int j1)
    {
        rcSrc.set(i, j, i + k, j + l);
        rcDst.set(i1, j1, i1 + k, j1 + l);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, null);
    }

    public static void drawBitmapParcel(Bitmap bitmap, int i, int j, int k, int l, int i1, int j1, int k1, 
            int l1)
    {
        rcSrc.set(i, j, i + k, j + l);
        rcDst.set(i1, j1, i1 + k1, j1 + l1);
        mCanvas.drawBitmap(bitmap, rcSrc, rcDst, null);
    }

    public static final void drawBmpText(int i, int j, int k)
    {
        drawBmpText(i, j, k, null);
    }

    public static final void drawBmpText(int i, int j, int k, Paint paint)
    {
        if(k < 0 || k > 90 || charLayer == null)
        {
            return;
        } else
        {
            rcSrc.set(k * charRealX, 0, k * charRealX + charRealX, charRealY);
            rcDst.set(i, j, i + charXSpace, j + charYSpace);
            mCanvas.drawBitmap(charLayer, rcSrc, rcDst, paint);
            return;
        }
    }

    public static final void drawBmpText(int i, int j, CharSequence charsequence)
    {
        drawBmpText(i, j, charsequence, null);
    }

    public static final void drawBmpText(int i, int j, CharSequence charsequence, Paint paint)
    {
        int k = 0;
        int l = i;
        int i1 = charsequence.length();
        do
        {
            if(k >= i1)
            {
                return;
            }
            drawBmpText(l, j, -32 + charsequence.charAt(k), paint);
            l += charXSpace;
            k++;
        } while(true);
    }

    public static final void drawBmpTextNumber(int i, int j, int k)
    {
        if(k < 0 || k > 9)
        {
            return;
        } else
        {
            drawBmpText(i, j, k + 16);
            return;
        }
    }

    public static final void drawBmpTextTime(int i, int j, long l)
    {
        int i2;
        int j2;
        int k = (int)(l / 10L);
        int i1 = i - charXSpace;
        drawBmpTextNumber(i1, j, k % 10);
        int j1 = i1 - 15;
        int k1 = k / 10;
        drawBmpTextNumber(j1, j, k1 % 10);
        int l1 = j1 - 15;
        i2 = k1 / 10;
        drawBmpText(l1, j, 14);
        j2 = l1 - 15;
        if(i2 == 0)
        {
            drawBmpText(j2, j, 0);
            j2 -= charRealX;
        }else {
            while (i2 > 0) {
                drawBmpTextNumber(j2, j, i2 % 10);
                i2 /= 10;
                j2 -= charRealX;
            }
        }
        charLastSize = -24 + (i - j2);
    }

    public static final void drawBox(int i, int j, int k, int l, Paint paint)
    {
        mCanvas.drawRect(i, j, k, l, paint);
    }

    public static final void drawCircle(float f, float f1, float f2, Paint paint)
    {
        mCanvas.drawCircle(f, f1, f2, paint);
    }

    public static final void drawCircle(int i, int j, int k, Paint paint)
    {
        mCanvas.drawCircle(i, j, k, paint);
    }

    public static final void drawColor(int i)
    {
        if(paintClr != i)
        {
            paintClr = i;
            paintColor.setColor(paintClr);
        }
        mCanvas.drawPaint(paintColor);
    }

    public static final void drawLine(float f, float f1, float f2, float f3, Paint paint)
    {
        mCanvas.drawLine(f, f1, f2, f3, paint);
    }

    public static final void drawLine(int i, int j, int k, int l, Paint paint)
    {
        mCanvas.drawLine(i, j, k, l, paint);
    }

    public static final void drawPaint(Paint paint)
    {
        mCanvas.drawPaint(paint);
    }

    public static final void drawPath(Path path, Paint paint)
    {
        mCanvas.drawPath(path, paint);
    }

    public static final void drawRect(float f, float f1, float f2, float f3, Paint paint)
    {
        mCanvas.drawRect(f, f1, f + f2, f1 + f3, paint);
    }

    public static final void drawRoundRect(int i, int j, int k, int l, int i1, Paint paint)
    {
        dr.set(i, j, i + k, j + l);
        mCanvas.drawRoundRect(dr, i1, i1, paint);
    }

    public static final void drawSolidCircle(float f, float f1, float f2, int i)
    {
        if(paintClr != i)
        {
            paintClr = i;
            paintColor.setColor(paintClr);
        }
        mCanvas.drawCircle(f, f1, f2, paintColor);
    }

    public static final void drawSolidLine(float f, float f1, float f2, float f3, int i)
    {
        if(paintClr != i)
        {
            paintClr = i;
            paintColor.setColor(paintClr);
        }
        mCanvas.drawLine(f, f1, f2, f3, paintColor);
    }

    public static final void drawSolidRect(float f, float f1, float f2, float f3, int i)
    {
        if(paintClr != i)
        {
            paintClr = i;
            paintColor.setColor(paintClr);
        }
        mCanvas.drawRect(f, f1, f + f2, f1 + f3, paintColor);
    }

    public static final void drawText(String s, int i, int j, Paint paint)
    {
        if(s == null)
        {
            return;
        } else
        {
            mCanvas.drawText(s, i, j, paint);
            return;
        }
    }

    public static final void drawText(char ac[], int i, int j, Paint paint)
    {
        if(ac == null)
        {
            return;
        } else
        {
            mCanvas.drawText(ac, 0, ac.length, i, j, paint);
            return;
        }
    }

    public static final void drawTextCE(String s, int i, int j, Paint paint)
    {
        if(s == null)
        {
            return;
        }
        if(i == 0x80000000 || j == 0x80000000)
        {
            paint.getTextBounds(s, 0, s.length(), textBound);
            int k;
            int l;
            if(i == 0x80000000)
            {
                k = centerX(textBound.right - textBound.left);
            } else
            {
                k = i;
            }
            if(j == 0x80000000)
            {
                l = centerY(textBound.bottom - textBound.top);
            } else
            {
                l = j;
            }
            mCanvas.drawText(s, k, l, paint);
            return;
        } else
        {
            mCanvas.drawText(s, i, j, paint);
            return;
        }
    }

    public static final int drawTextLine(String s, int i, int j, int k, Paint paint)
    {
        StringTokenizer stringtokenizer;
        float f;
        String as[];
        int l;
        stringtokenizer = new StringTokenizer(s, " ,:;.", true);
        f = 4F + paint.getTextSize();
        as = new String[10];
        l = 0;
        as[0] = "";
        while(stringtokenizer.hasMoreTokens()){
            String s1 = stringtokenizer.nextToken();
            if(TextUtils.getTextWidth((new StringBuilder(String.valueOf(as[l]))).append(s1).toString(), paint) > k)
            {
                as[l + 1] = s1;
                l++;
            } else
            {
                as[l] = (new StringBuilder(String.valueOf(as[l]))).append(s1).toString();
            }
        }
        int i1 = 0;
        while(i1 <= l)
        {
            drawText(as[i1], i, (int)((float)j + f * (float)i1), paint);
            i1++;
        }
        return l;
    }

    public static final void enableAliasing(boolean flag)
    {
    }

    protected static final void freeAllMusic()
    {
        if(musicCache != null)
        {
            int i = 0;
            int j = musicCache.size();
            while(i < j) 
            {
                Music music = (Music)musicCache.valueAt(i);
                if(music != null)
                {
                    music.terminate();
                }
                i++;
            }
        }
    }

    public static final void freeBitmap(int i)
    {
        if(i < 0 || i >= GamePak._cache.length)
        {
            Bitmap bitmap = (Bitmap)bitmapCache.get(i);
            if(bitmap != null)
            {
                bitmapCache.remove(i);
                bitmap.recycle();
            }
        } else
        {
            Bitmap bitmap1 = (Bitmap)GamePak._cache[i];
            if(bitmap1 != null)
            {
                GamePak._cache[i] = null;
                bitmap1.recycle();
                return;
            }
        }
    }

    public static final void freeMusic(int i)
    {
        Music music = (Music)GamePak._cache[i];
        if(music != null)
        {
            music.terminate();
            GamePak._cache[i] = null;
            musicCache.remove(i);
        }
    }

    public static final GameRate getActionRate()
    {
        return actionRate;
    }

    public static final boolean getAliasing()
    {
        return getAntiAliasEnabled();
    }

    public static final int getAndroidSDKVersion()
    {
        return androidSDKVersion;
    }

    public static final AnimPackage getAnimPackage(int i)
    {
        AnimPackage animpackage = (AnimPackage)GamePak._cache[i];
        if(animpackage == null)
        {
            animpackage = AnimPackage.loadFromRes(i);
            GamePak._cache[i] = animpackage;
        }
        return animpackage;
    }

    public static final boolean getAntiAliasEnabled()
    {
        return opAlias;
    }

    public static final int getAppVersion()
    {
        return appVersionCode;
    }

    public static final String getAppVersionName()
    {
        return appVersionName;
    }

    public static final GameApplication getApplication()
    {
        return application;
    }

    public static final Context getBaseContext()
    {
        if(activity != null)
        {
            return activity;
        } else
        {
            return application;
        }
    }

    public static final Bitmap getBitmap(int i)
    {
        Bitmap bitmap = (Bitmap)GamePak._cache[i];
        if(bitmap == null || bitmap.isRecycled())
        {
            bitmap = GamePak.getBitmap(i, getBitmapOptions());
            GamePak._cache[i] = bitmap;
        }
        return bitmap;
    }

    public static final android.graphics.BitmapFactory.Options getBitmapOptions()
    {
        if(androidSDKVersion <= 3)
        {
            return BitmapUtils_API3.createOption();
        } else
        {
            return BitmapUtils_API4.createOption();
        }
    }

    public static final Bitmap getBitmapSafe(int i)
    {
        Bitmap bitmap = getBitmap(i);
        if(bitmap != null && bitmap.isRecycled())
        {
            freeBitmap(i);
            bitmap = getBitmap(i);
        }
        return bitmap;
    }

    public static final int getBufferHeight()
    {
        return mBufferHeight;
    }

    public static final int getBufferWidth()
    {
        return mBufferWidth;
    }

    public static final int getColorMode()
    {
        return colorMode;
    }

    public static final GameActivity getContext()
    {
        return activity;
    }

    public static final IGameStage getCurrentStage()
    {
        return StageManager._currentStage;
    }

    public static final float getDensity()
    {
        return density;
    }

    public static final int getDisplayHeight()
    {
        return displayHeight;
    }

    public static final int getDisplaySize()
    {
        return (int)Math.sqrt(displayWidth * displayWidth + displayHeight * displayHeight);
    }

    public static final int getDisplayWidth()
    {
        return displayWidth;
    }

    public static final FeatureWrapper getFeatureWrapper()
    {
        return featureWrapper;
    }

    public static final File getFile(String s)
    {
        return new File(getFilesDir().getAbsolutePath().concat("/").concat(s));
    }

    public static final File getFilesDir()
    {
        return getBaseContext().getFilesDir();
    }

    public static final String getFootprint()
    {
        return hash;
    }

    public static final GameMessageHandler getHandler()
    {
        return mHandler;
    }

    public static final boolean getHapticEnable()
    {
        return opHaptic;
    }

    public static final String getLang()
    {
        return res_lang;
    }

    public static final String getLangDesc(int i)
    {
        return res_d[i];
    }

    public static final String getLangDesc(String s)
    {
        if(s == null)
        {
            s = res_d[0];
        } else
        {
            int i = 0;
            while(i < res_l.length) 
            {
                if(res_l[i].equals(s))
                {
                    return res_d[i];
                }
                i++;
            }
        }
        return s;
    }

    public static final String[] getLangs()
    {
        return res_l;
    }

    public static final String getLanguage()
    {
        return locale.getLanguage();
    }

    public static final int getLastBmpTextSize()
    {
        return charLastSize;
    }

    public static final GameLayout getLayout()
    {
        return layout;
    }

    public static final MarketWrapper getMarket()
    {
        return marketWrapper;
    }

    public static final float getMultiplier()
    {
        return multiplier;
    }

    public static final Music getMusic(int i)
    {
        Music music = (Music)GamePak._cache[i];
        if(music == null)
        {
            music = new Music(i);
            GamePak._cache[i] = music;
            musicCache.put(i, music);
        }
        return music;
    }

    public static final boolean getMusicEnable()
    {
        return opMusic;
    }

    public static final int getOrientation()
    {
        return orientation;
    }

    public static final String getPackageName()
    {
        return appPackageName;
    }

    public static final AppParameters getParameters()
    {
        return parameters;
    }

    public static final boolean getPrefBoolean(String s, boolean flag)
    {
        return getPreferences().getBoolean(s, flag);
    }

    public static final float getPrefFloat(String s, float f)
    {
        return getPreferences().getFloat(s, f);
    }

    public static final int getPrefInt(String s, int i)
    {
        return getPreferences().getInt(s, i);
    }

    public static final long getPrefLong(String s, long l)
    {
        return getPreferences().getLong(s, l);
    }

    public static final String getPrefString(String s, String s1)
    {
        return getPreferences().getString(s, s1);
    }

    public static final SharedPreferences getPreferences()
    {
        if(preferences == null)
        {
            preferences = PreferenceManager.getDefaultSharedPreferences(application);
        }
        return preferences;
    }

    private static final Class getRClass(String s)
    {
        Class class1;
        try
        {
            class1 = Class.forName((new StringBuilder(String.valueOf(appPackageName))).append(".R$").append(s).toString());
        }
        catch(Exception exception)
        {
            return null;
        }
        return class1;
    }

    public static final int getRDrawable(String s)
    {
        int i;
        try
        {
            i = ((Integer)Rdrawable.getField(s).get(Rdrawable)).intValue();
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static final int getRId(String s)
    {
        int i;
        try
        {
            i = ((Integer)Rid.getField(s).get(Rid)).intValue();
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static final int getRLayout(String s)
    {
        int i;
        try
        {
            i = ((Integer)Rlayout.getField(s).get(Rlayout)).intValue();
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static final int getRString(String s)
    {
        int i;
        try
        {
            i = ((Integer)Rstring.getField(s).get(Rstring)).intValue();
        }
        catch(Exception exception)
        {
            return 0;
        }
        return i;
    }

    public static final GameRate getRate()
    {
        return renderRate;
    }

    public static final GameRenderMode getRenderMode()
    {
        return gameRenderMode;
    }

    public static final GameRate getRenderRate()
    {
        return renderRate;
    }

    public static final Bitmap getResBitmap(int i)
    {
        Bitmap bitmap = (Bitmap)bitmapCache.get(i);
        if(bitmap == null)
        {
            bitmap = BitmapFactory.decodeResource(activity.getResources(), i, getBitmapOptions());
            bitmapCache.put(i, bitmap);
        }
        return bitmap;
    }

    public static final String getResString(int i)
    {
        int k;
        ZipFile zipfile;
        java.util.zip.ZipEntry zipentry;
        BufferedInputStream bufferedinputstream;
        int l;
        int i1;
        byte abyte0[];
        byte abyte1[];
        String s1;
        String s;
        if(!(res_langid >= 0 && res_t != null)) {
            s = application.getResources().getString(i);
            return s;
        }
        int j;
        if(res_cache == null)
        {
            res_cache = new String[res_t[res_langid].length];
        }
        j = i & 0xffff;
        s = res_cache[j];
        if(s != null)
            return s;
        try
        {
            k = res_t[res_langid][j];
            if(k == 0)
                k = res_t[0][j];
            if(k <= 0)
                return s;
            zipfile = new ZipFile(sourceDir);
            zipentry = zipfile.getEntry("resources.arsc");
            if(zipentry != null) {
                bufferedinputstream = new BufferedInputStream(zipfile.getInputStream(zipentry));
                bufferedinputstream.skip(k);
                l = bufferedinputstream.read();
                i1 = bufferedinputstream.read();
                if (l > 0) {
                    if (l != i1) {
                        abyte1 = new byte[l * 2];
                        bufferedinputstream.read(abyte1, 0, abyte1.length);
                        s1 = new String(abyte1, "UTF-16LE");
                        s = s1;
                    } else {
                        abyte0 = new byte[l];
                        bufferedinputstream.read(abyte0, 0, abyte0.length);
                        s = new String(abyte0, "UTF-8");
                    }
                }
                res_cache[j] = s;
                bufferedinputstream.close();
            }
            zipfile.close();
            return s;
        }
        catch(Exception exception)
        {
            return s;
        }
    }

    public static final String[] getResStringArray(int i)
    {
        return application.getResources().getStringArray(i);
    }

    public static final int getSmartBannerHeight()
    {
        byte byte0 = 50;
        DisplayMetrics displaymetrics = application.getResources().getDisplayMetrics();
        float f = (float)displaymetrics.heightPixels / displaymetrics.density;
        if(f >= 720F)
        {
            byte0 = 90;
        } else
        if(f >= 400F)
        {
            return byte0;
        }
        return byte0;
    }

    public static final Sound getSound(int i)
    {
        Sound sound = (Sound)GamePak._cache[i];
        if(sound == null)
        {
            sound = new Sound(GamePak.getSound(soundPool, i));
            GamePak._cache[i] = sound;
        }
        return sound;
    }

    public static final boolean getSoundEnable()
    {
        return opSound;
    }

    public static final float getSoundLevel()
    {
        return (float)opVolume / 15F;
    }

    public static final int getSurfaceHeight()
    {
        return surfaceHeight;
    }

    public static final int getSurfaceWidth()
    {
        return surfaceWidth;
    }

    public static final Typeface getSystemDefaultTypeface()
    {
        return Typeface.DEFAULT;
    }

    public static final File getTempDir()
    {
        return getBaseContext().getCacheDir();
    }

    public static final File getTempFile(String s)
    {
        return new File(getTempDir().getAbsolutePath().concat("/").concat(s));
    }

    public static final int getTextHeight(String s, Paint paint)
    {
        paint.getTextBounds(s, 0, s.length(), textBound);
        return textBound.bottom - textBound.top;
    }

    public static final int getTextHeight(char ac[], Paint paint)
    {
        paint.getTextBounds(ac, 0, ac.length, textBound);
        return textBound.bottom - textBound.top;
    }

    public static final int getTextWidth(String s, Paint paint)
    {
        paint.getTextBounds(s, 0, s.length(), textBound);
        return textBound.right - textBound.left;
    }

    public static final int getTextWidth(char ac[], Paint paint)
    {
        paint.getTextBounds(ac, 0, ac.length, textBound);
        return textBound.right - textBound.left;
    }

    public static final Thread getThread(int i)
    {
        if(i == 1)
        {
            return actionThread;
        }
        if(i == 2)
        {
            return renderThread;
        } else
        {
            return null;
        }
    }

    public static final float getTick(int i)
    {
        if(i != 0)
        {
            return (float)(tick % (long)i) / (float)i;
        } else
        {
            return 0.0F;
        }
    }

    public static int getTouchMode()
    {
        return _touchmode;
    }

    public static final Typeface getTypeface()
    {
        return typeface;
    }

    public static final boolean getVibrateEnable()
    {
        return opVibrate;
    }

    public static final int getVolume()
    {
        return opVolume;
    }

    public static final boolean getWakeupEnable()
    {
        return opWakeup;
    }

    protected static final void handleMessage(Message message)
    {
        switch (message.what) {
            default:case 4:
                break;
            case 2:case 3:
                if(message.obj != null)
                {
                    ((IGameStage)message.obj).onCall(message.arg1);
                    return;
                }
                break;
            case 5:
                if(message.obj != null)
                {
                    IGameStage igamestage = (IGameStage)message.obj;
                    View view1;
                    if(igamestage.getContentView() == null)
                    {
                        view1 = igamestage.onCreateView();
                    } else
                    {
                        view1 = igamestage.getContentView();
                    }
                    if(view1 != null)
                    {
                        igamestage.setContentView(view1);
                        if(view1.getParent() == null)
                        {
                            layout.addView(view1, 2, new GameLayout.LayoutParams(-1, -1));
                        }
                        igamestage.onShowView();
                        return;
                    }
                }
                break;
            case 6:
                if(message.obj != null)
                {
                    layout.removeView(((IGameStage)message.obj).getContentView());
                    return;
                }
                break;
            case 7:
                if(message.obj != null)
                {
                    Toast.makeText(activity, (String)message.obj, Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
            case 65537:
                break;
            case 65538:
                break;
        }
    }

    public static final boolean hasFacebookApp()
    {
        ApplicationInfo applicationinfo;
        boolean flag;
        try
        {
            applicationinfo = application.getPackageManager().getApplicationInfo("com.facebook.katana", 0);
        }
        catch(Exception exception)
        {
            return false;
        }
        flag = false;
        if(applicationinfo != null)
        {
            flag = true;
        }
        return flag;
    }

    public static final void invalidate()
    {
        isDirty = true;
    }

    private static final boolean is720x1280()
    {
        return displayWidth == 720 && displayHeight == 1280 || displayWidth == 1280 && displayHeight == 720;
    }

    public static final boolean isBigTablet()
    {
        DisplayMetrics displaymetrics;
        int i;
        boolean flag;
        float f2;
        float f3;
        int j;
        try
        {
            displaymetrics = activity.getResources().getDisplayMetrics();
            float f = (float)displaymetrics.widthPixels / displaymetrics.xdpi;
            float f1 = (float)displaymetrics.heightPixels / displaymetrics.ydpi;
            flag = false;
            if(Math.sqrt(f * f + f1 * f1) < 6D)
            {
                return false;
            }
            f2 = displaymetrics.widthPixels;
            f3 = displaymetrics.density;
            flag = false;
            if(f2 >= 768F * f3)
            {
                flag = true;
            }
            return flag;
        }
        catch(Throwable throwable)
        {
            return false;
        }
    }

    public static final boolean isBitmapReady(Bitmap bitmap)
    {
        while(bitmap == null || bitmap.isRecycled()) 
        {
            return false;
        }
        return true;
    }

    public static final boolean isDebuggable()
    {
        return isDebuggable;
    }

    public static final boolean isFirstUse()
    {
        return opFirst;
    }

    public static final boolean isHD()
    {
        return getDisplaySize() > 577;
    }

    public static final boolean isMainThread()
    {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    public static final boolean isNoFullScreen()
    {
        return metaNoFullScreen;
    }

    public static final boolean isNullOrEmpty(String s)
    {
        while(s == null || s.length() == 0) 
        {
            return true;
        }
        return false;
    }

    public static final boolean isTablet()
    {
        double d;
        int i;
        boolean flag;
        try
        {
            DisplayMetrics displaymetrics = activity.getResources().getDisplayMetrics();
            float f = (float)displaymetrics.widthPixels / displaymetrics.xdpi;
            float f1 = (float)displaymetrics.heightPixels / displaymetrics.ydpi;
            d = Math.sqrt(f * f + f1 * f1);
        }
        catch(Throwable throwable)
        {
            return false;
        }
        flag = false;
        if(d >= 6D)
        {
            flag = true;
        }
        return flag;
    }

    public static final boolean isiDTGV()
    {
        return metaiDTGV;
    }

    public static final boolean isiDTouch()
    {
        return isiDTouch;
    }

    private static int keyConvert(int i)
    {
        switch(i)
        {
        default:
            return -1;

        case 4: // '\004'
            return 0;
        }
    }

    protected static final void loadAsyncTask()
    {
        try
        {
            Class.forName("android.os.AsyncTask");
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public static Bitmap loadBitmap(int i)
    {
        return GamePak.getBitmap(i, getBitmapOptions());
    }

    private static void loadPreferences()
    {
        SharedPreferences sharedpreferences = getPreferences();
        opFirst = sharedpreferences.getBoolean("first", true);
        opSound = sharedpreferences.getBoolean("sound", parameters.DEFAULT_SOUND_ENABLED);
        opMusic = sharedpreferences.getBoolean("music", parameters.DEFAULT_MUSIC_ENABLED);
        opVibrate = sharedpreferences.getBoolean("vibrate", parameters.DEFAULT_VIBRATE_ENABLED);
        opHaptic = sharedpreferences.getBoolean("haptic", parameters.DEFAULT_HAPTIC_ENABLED);
        opAlias = sharedpreferences.getBoolean("aliasing", parameters.DEFAULT_ALIASING_ENABLED);
        opStretch = sharedpreferences.getBoolean("stretch", parameters.DEFAULT_STRETCH_ENABLED);
        opWakeup = sharedpreferences.getBoolean("wakeup", parameters.DEFAULT_KEEPSCREENON_ENABLED);
        if(soundMngr != null)
        {
            opVolume = soundMngr.getStreamVolume(3);
        }
        opLoaded = true;
    }

    public static final Typeface loadTypeface(String s)
    {
        try {
            if (s != null) {
                Typeface typeface1 = Typeface.createFromAsset(activity.getAssets(), s);
                return typeface1;
            }
        }catch(Exception e) {}
        return getSystemDefaultTypeface();
    }

    protected static final void onActionCycle()
    {
        acNextTime = 0L;
        acLoop = true;
        actionSync = new Object();
        ActionCounter.restart();
        while(running) {
            if(!ready || paused)
            {
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception) { }
            }else {
                acCurTime = SystemClock.elapsedRealtime();
                acDeltaTime = acNextTime - acCurTime;
                if (acDeltaTime > 0L) {
                    try {
                        Thread.sleep(acDeltaTime);
                    } catch (InterruptedException interruptedexception1) {
                    }
                } else {
                    acNextTime = acCurTime + actionFreq;
                    if (rendering) {
                        synchronized (renderSync) {
                            try {
                                renderSync.wait(3000L);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                    acting = true;
                    while (GameActionStack.hasData()) {
                        switch (GameActionStack.pop()) {
                            case 4: // '\004'
                                StageManager.getCurrentStage().onBackButton();
                                break;
                        }
                    }
                    if (StageManager._currentStage != null && StageManager._currentStage.isActive()) {
                        StageManager._currentStage.onAction();
                    }
                    Keyboard.clear();
                    TouchScreen.clear();
                    if (parameters.USE_ACTION_COUNTER) {
                        ActionCounter.next();
                    }
                    acting = false;
                    synchronized (actionSync) {
                        actionSync.notifyAll();
                    }
                    tick = 1L + tick;
                }
            }
        }
        acLoop = false;
        if(!rendering)
        {
            syncQuit();
            return;
        }
        try {
            synchronized (renderSync) {
                renderSync.wait(3000L);
            }
        }catch (InterruptedException e){}
        syncQuit();
    }

    protected static void onActivityCreate(GameActivity gameactivity)
    {
        GameLayout.LayoutParams layoutparams;
        if(metaiDTGV)
        {
            gameactivity.getWindow().addFlags(0x80000);
        }
        checkErrorReporter(gameactivity);
        System.gc();
        setContext(gameactivity);
        gameactivity.setVolumeControlStream(3);
        UpdateDisplayData();
        mHandler = new GameMessageHandler();
        colorMode = parameters.getColorMode();
        mBufferWidth = parameters.getBufferWidth();
        mBufferHeight = parameters.getBufferHeight();
        boolean flag;
        if(displayWidth == mBufferWidth && displayHeight == mBufferHeight)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mNoStretch = flag;
        mBufferDiagonal = (int)Math.sqrt(mBufferWidth * mBufferWidth + mBufferHeight * mBufferHeight);
        mBufferCW = mBufferWidth >> 1;
        mBufferCH = mBufferHeight >> 1;
        updateRatio();
        renderRate = parameters.DEFAULT_GAMERATE;
        actionRate = parameters.DEFAULT_GAMERATE;
        renderFreq = parameters.DEFAULT_GAMERATE.getTime();
        actionFreq = parameters.DEFAULT_GAMERATE.getTime();
        engineMode = parameters.DEFAULT_ENGINE_MODE;
        gameRenderMode = parameters.DEFAULT_RENDERMODE;
        GamePak.initialize(parameters.getPack());
        soundPool = new SoundPool(parameters.SOUND_MAX, 3, parameters.SOUND_QUALITY);
        soundMngr = (AudioManager)gameactivity.getSystemService(Context.AUDIO_SERVICE);
        SensorsManager.onInitialize();
        loadPreferences();
        if(opFirst)
        {
            application.onFirstUse();
            savePreferences();
        }
        layout = new GameLayout(gameactivity);
        view = featureWrapper.createGameView(gameactivity);
        if(isiDTouch)
        {
            view.setKeepScreenOn(false);
        } else
        {
            view.setKeepScreenOn(opWakeup);
        }
        holder = view.getHolder();
        if(parameters.DEFAULT_RENDERER == 0)
        {
            holder.setFixedSize(mBufferWidth, mBufferHeight);

            if(androidSDKVersion >= 16 && is720x1280() && parameters.FIX_GS3BLACKSCREEN != 2)
            {
                holder.setFormat(1);
            } else
            {
                view.setBackgroundColor(0);
            }
        } else
        {
            srcRect = new Rect(0, 0, mBufferWidth, mBufferHeight);
            dstRect = new Rect();
            view.setBackgroundColor(0);
        }
        holder.addCallback(new GameSurfaceCallback());
        layoutparams = new GameLayout.LayoutParams(0, 0);
        layoutparams.width = -1;
        layoutparams.height = -1;
        layout.addView(view, layoutparams);
        gameactivity.onCreateViews(layout);
        gameactivity.setContentView(layout);
        addLegacyOverflowButton(gameactivity);
        buffer = createBitmap(mBufferWidth, mBufferHeight);
        mCanvas = new Canvas(buffer);
        dwfilter = new PaintFlagsDrawFilter(0, 71);
        mCanvas.setDrawFilter(dwfilter);
        textBound = new Rect();
        typeface = Label.loadTypeface(parameters.DEFAULT_LABEL_TYPEFACE);
        musicCache = new SparseArray();
        bitmapCache = new SparseArray();
        rcSrc = new Rect();
        rcDst = new Rect();
        matrix = new Matrix();
        _bitBltPaint = new Paint();
        _bitBltPaint.setFilterBitmap(true);
        _bitBltPaint.setAntiAlias(false);
        _bitBltPaint.setDither(false);
        _bitBltPaint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.SRC));
        _alphaPaint = new Paint();
        _alphaPaint.setAntiAlias(getAliasing());
        _alphaPaint.setFilterBitmap(getAliasing());
        charLayer = null;
        charRealX = 24;
        charRealY = 24;
        charXSpace = charRealX;
        charYSpace = charRealY;
        vibrator = (Vibrator)gameactivity.getSystemService(Context.VIBRATOR_SERVICE);
        paintClr = 0;
        paintColor = new Paint();
        paintColor.setColor(paintClr);
        Keyboard.reset();
        TouchScreen.reset();
        BackgroundMusic.reset();
    }

    protected static final void onActivityDestroy()
    {
        StageManager.onTerminate();
    }

    protected static boolean onActivityKeyDown(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(keyevent.getAction() != 0)
            return true;
        if(i != 82 && i != 24 && i != 25){
            int j = keyConvert(i);
            if(j != -1)
            {
                Keyboard.keys[j] = -1;
                Keyboard.state[j] = -1;
            }
            if(i == 4)
            {
                IGameStage igamestage = StageManager.getCurrentStage();
                if(igamestage != null)
                {
                    igamestage.onBackButton();
                }
            }
            if(!parameters.USE_DPAD_FOCUS)
            {
                return flag;
            }
            if(i == 19 || i == 21)
            {
                GameStage gamestage = (GameStage)StageManager.getCurrentStage();
                if(gamestage != null)
                {
                    gamestage._foucs.goPrevious();
                    return flag;
                }
            }else if(i == 20 || i == 22) {
                GameStage gamestage1 = (GameStage)StageManager.getCurrentStage();
                if(gamestage1 != null)
                {
                    gamestage1._foucs.goNext();
                    return flag;
                }
            }else if(i == 23) {
                GameStage gamestage2 = (GameStage) StageManager.getCurrentStage();
                if (gamestage2 != null) {
                    GameObject gameobject = gamestage2._foucs.getFocus();
                    if (gameobject != null) {
                        gameobject.focusClick = flag;
                        return flag;
                    }
                }
            }
        }else {
            opVolume = soundMngr.getStreamVolume(3);
            flag = false;
        }
        return flag;
    }

    protected static boolean onActivityKeyUp(int i, KeyEvent keyevent)
    {
        boolean flag = true;
        if(keyevent.getAction() == 1)
        {
            if(i == 82 || i == 24 || i == 25)
            {
                if(i == 82)
                {
                    activity.showOptionsMenu();
                }
                opVolume = soundMngr.getStreamVolume(3);
                flag = false;
            } else
            {
                int j = keyConvert(i);
                if(j != -1)
                {
                    Keyboard.keys[j] = 0;
                    Keyboard.state[j] = 1;
                    return flag;
                }
            }
        }
        return flag;
    }

    protected static final void onActivityPause()
    {
        SensorsManager.onPause();
        BackgroundMusic.pause();
        paused = true;
    }

    protected static void onActivityResult(int i, int j, Intent intent)
    {
        IGameStage igamestage = StageManager.getCurrentStage();
        if(igamestage != null)
        {
            lateResume = true;
            if(igamestage.enterOnResume())
            {
                igamestage.onEnter();
            }
        }
    }

    protected static final void onActivityResume()
    {
        setAppOrientation();
        SensorsManager.onResume();
        BackgroundMusic.resume();
        paused = false;
    }

    protected static final void onActivityStart()
    {
        if(running)
        {
            return;
        } else {
            loadPreferences();
            running = true;
            paused = false;
            mThread = new Thread() {

                public void run()
                {
                    System.gc();
                    Game.onJoypadClear();
                    Keyboard.reset();
                    TouchScreen.reset();
                    Game.application.onEngineInitialize();
                    StageManager.initializeStages();
                    if(Game.lateResume)
                    {
                        Game.lateResume = false;
                        if(StageManager.getCurrentStage() != null)
                        {
                            StageManager.getCurrentStage().onLateResume();
                        }
                    }
                    if(Game.engineMode == 0)
                    {
                        if(Game.parameters.DEFAULT_RENDERER == 1)
                        {
                            Game.renderThread = new Thread() {
                                public void run()
                                {
                                    Game.onRenderCycleSoftware();
                                }
                            };
                        } else
                        {
                            Game.renderThread = new Thread() {
                                public void run()
                                {
                                    Game.onRenderCycleHarware();
                                }
                            };
                        }
                        Game.actionThread = new Thread() {
                            public void run()
                            {
                                Game.onActionCycle();
                            }
                        };
                        Game.actionThread.start();
                        Game.renderThread.start();
                        return;
                    } else
                    {
                        Game.renderThread = new GameThreadTemporal();
                        Game.actionThread = Game.renderThread;
                        Game.actionThread.start();
                        return;
                    }
                }

            };
            mThread.start();
            return;
        }
    }

    protected static final void onActivityStop()
    {
        if(running)
        {
            BackgroundMusic.stop();
            running = false;
            paused = true;
        }
    }

    protected static void onApplicationConfigurationChanged(Configuration configuration)
    {
        if(!configuration.locale.getLanguage().equals(locale.getLanguage()))
        {
            locale = configuration.locale;
        }
    }

    protected static void onApplicationCreate(GameApplication gameapplication)
    {
        loadAsyncTask();
        multiplier = 1.0F;
        application = gameapplication;
        appPackageName = gameapplication.getPackageName();
        locale = Locale.getDefault();
        androidSDKVersion = Integer.valueOf(android.os.Build.VERSION.SDK).intValue();
        String s = Build.PRODUCT;
        isiDTouch = "iDTable".equals(s) | "iDWall".equals(s);
        try {
            ApplicationInfo applicationinfo = gameapplication.getPackageManager().getApplicationInfo(appPackageName, PackageManager.GET_META_DATA);
            boolean flag;
            String s1;
            boolean flag1;
            boolean flag2;
            Bundle bundle;
            boolean flag3;
            if ((2 & applicationinfo.flags) != 0)
                flag = true;
            else
                flag = false;
            isDebuggable = flag;
            if (applicationinfo.metaData == null)
                s1 = null;
            else
                s1 = applicationinfo.metaData.getString("market");
            metaMarket = s1;
            if (applicationinfo.metaData == null)
                flag1 = false;
            else
                flag1 = applicationinfo.metaData.getBoolean("iDTGV");
            metaiDTGV = flag1;
            if (applicationinfo.metaData == null)
                flag2 = false;
            else
                flag2 = applicationinfo.metaData.getBoolean("NOFULLSCREEN");
            metaNoFullScreen = flag2;
            bundle = applicationinfo.metaData;
            flag3 = false;
            if (bundle != null)
                flag3 = applicationinfo.metaData.getBoolean("FOR_FAMILIES");
            metaForFamilies = flag3;
            sourceDir = applicationinfo.sourceDir;
        }catch(Exception e){}
        AppParameters appparameters;
        if(androidSDKVersion >= 19)
        {
            featureWrapper = new FeatureWrapper19();
        } else
        if(androidSDKVersion >= 18)
        {
            featureWrapper = new FeatureWrapper18();
        } else
        if(androidSDKVersion >= 11)
        {
            featureWrapper = new FeatureWrapper11();
        } else
        if(androidSDKVersion >= 5)
        {
            featureWrapper = new FeatureWrapper05();
        } else
        if(androidSDKVersion >= 4)
        {
            featureWrapper = new FeatureWrapper04();
        } else
        {
            featureWrapper = new FeatureWrapper01();
        }
        if("Amazon".equals(metaMarket))
        {
            marketWrapper = new MarketAmazon();
        } else
        {
            marketWrapper = new MarketGooglePlay();
        }
        UpdateDisplayData();
        appparameters = application.onCreateParameters();
        parameters = appparameters;
        if(appparameters.USE_ERROR_REPORTER)
        {
            reporter = new ErrorReporter(application);
        }
        if(appparameters.BACKTRACK_ENABLED)
        {
            hash = ArrayUtils.md5(gameapplication);
        }
        if(appparameters.DEBUG_MESSAGE_ENABLED)
        {
            debugToolReceiver = new DebugToolReceiver();
            gameapplication.registerReceiver(debugToolReceiver, new IntentFilter("com.magmamobile.debugtool"));
        }
        if(appparameters.ADWHIRL_ENABLED)
        {
            WrapperAdWhirl.check(appparameters);
        }
        onJoypadInitialize();
        res_init();
    }

    protected static void onApplicationLowMemory()
    {
    }

    protected static void onApplicationTerminate()
    {
        if(application != null && debugToolReceiver != null)
        {
            application.unregisterReceiver(debugToolReceiver);
        }
    }

    static final void onJoypadClear()
    {
        if(androidSDKVersion >= 5)
        {
            JoypadUtils_API5.onJoypadClear();
            return;
        } else
        {
            JoypadUtils_API4.onJoypadClear();
            return;
        }
    }

    static final boolean onJoypadEvent(MotionEvent motionevent)
    {
        if(androidSDKVersion >= 5)
        {
            return JoypadUtils_API5.onJoypadEvent(motionevent);
        } else
        {
            return JoypadUtils_API4.onJoypadEvent(motionevent);
        }
    }

    static final void onJoypadInitialize()
    {
        if(androidSDKVersion >= 5)
        {
            JoypadUtils_API5.onJoypadInitialize();
            return;
        } else
        {
            JoypadUtils_API4.onJoypadInitialize();
            return;
        }
    }

    protected static final void onRenderCycleHarware()
    {
        reNextTime = 0L;
        reLoop = true;
        renderSync = new Object();
        RenderCounter.restart();
        while(running) {
            if(ready && !paused)
            {
                reCurTime = SystemClock.elapsedRealtime();
                reDeltaTime = reNextTime - reCurTime;
                if(reDeltaTime <= 0L)
                {
                    reNextTime = reCurTime + renderFreq;
                    if(gameRenderMode == GameRenderMode.OnDemand)
                    {
                        if(isDirty)
                        {
                            v0();
                            isDirty = false;
                        }
                    } else {
                        v0();
                    }
                } else {
                    try
                    {
                        Thread.sleep(reDeltaTime);
                    }
                    catch(InterruptedException interruptedexception1) { }
                }
            } else {
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
        reLoop = false;
        if(acting)
        {
            try {
                synchronized (actionSync) {
                    actionSync.wait(3000L);
                }
            }catch(Exception e){}
        }
        syncQuit();
    }

    protected static final void onRenderCycleSoftware()
    {
        reNextTime = 0L;
        reLoop = true;
        renderSync = new Object();
        RenderCounter.restart();
        while(running){
            if(ready && !paused)
            {
                reCurTime = SystemClock.elapsedRealtime();
                reDeltaTime = reNextTime - reCurTime;
                if(reDeltaTime <= 0L)
                {
                    reNextTime = reCurTime + renderFreq;
                    if(gameRenderMode == GameRenderMode.OnDemand)
                    {
                        if(isDirty)
                        {
                            v1();
                            isDirty = false;
                        }
                    } else
                    {
                        v1();
                    }
                } else
                {
                    try
                    {
                        Thread.sleep(reDeltaTime);
                    }
                    catch(InterruptedException interruptedexception1) { }
                }
            } else
            {
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
        reLoop = false;
        if(acting)
        {
            try {
                synchronized (actionSync) {
                    actionSync.wait(3000L);
                }
            }catch(Exception e) {}
        }
        syncQuit();
    }

    protected static final void onSensorAccuracyChanged(Sensor sensor, int i)
    {
    }

    protected static final void onSensorChanged(SensorEvent sensorevent)
    {
        IGameStage igamestage = StageManager.getCurrentStage();
        if(igamestage != null && igamestage.isActive())
        {
            igamestage.onSensorEvent(sensorevent);
        }
    }

    protected static final void onSurfaceChanged(SurfaceHolder surfaceholder, int i, int j, int k)
    {
        if(parameters.DEFAULT_RENDERER == 1)
        {
            dstRect.set(0, 0, displayWidth, displayHeight);
        }
        surfaceWidth = j;
        surfaceHeight = k;
        updateRatio();
        if(mBufferWidth > displayWidth || mBufferHeight > displayHeight)
        {
            mCLipFix = true;
        } else
        {
            mCLipFix = false;
        }
        ready = true;
        view.measure(-1, -1);
    }

    protected static final void onSurfaceCreated(SurfaceHolder surfaceholder)
    {
    }

    protected static final void onSurfaceDestroyed(SurfaceHolder surfaceholder)
    {
        ready = false;
    }

    protected static final void onTemporalCycleHardware()
    {
        acNextTime = 0L;
        acLoop = true;
        reLoop = true;
        ActionCounter.restart();
        RenderCounter.restart();
        while(running)
        {
            if(ready && !paused)
            {
                acCurTime = SystemClock.elapsedRealtime();
                acDeltaTime = acNextTime - acCurTime;
                if(acDeltaTime <= 0L)
                {
                    acNextTime = acCurTime + actionFreq;
                    acting = true;
                    while(GameActionStack.hasData())
                    {
                    	switch(GameActionStack.pop())
                        {
                        case 4: // '\004'
                            StageManager.getCurrentStage().onBackButton();
                            break;
                        }
                    }
                    if(StageManager._currentStage != null && StageManager._currentStage.isActive())
                    {
                        StageManager._currentStage.onAction();
                    }
                    Keyboard.clear();
                    TouchScreen.clear();
                    if(parameters.USE_ACTION_COUNTER)
                    {
                        ActionCounter.next();
                    }
                    drawCanvas = holder.lockCanvas();
                    if(drawCanvas != null)
                    {
                        rendering = true;
                        if(StageManager._currentStage != null && StageManager._currentStage.isActive())
                        {
                            StageManager._currentStage.onRender();
                        }
                        if(mCLipFix)
                        {
                            drawCanvas.clipRect(0.0F, 0.0F, mBufferWidth, mBufferHeight, android.graphics.Region.Op.REPLACE);
                        }
                        if(opAlias)
                        {
                            drawCanvas.setDrawFilter(dwfilter);
                            drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
                        } else
                        {
                            drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
                        }
                        holder.unlockCanvasAndPost(drawCanvas);
                        if(parameters.USE_RENDER_COUNTER)
                        {
                            RenderCounter.next();
                        }
                        rendering = false;
                    }
                    tick = 1L + tick;
                }else{
	                try
	                {
	                    Thread.sleep(acDeltaTime);
	                }
	                catch(InterruptedException interruptedexception1) { }
                }
            } else {
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
        acLoop = false;
        reLoop = false;
        syncQuit();
    }

    protected static final void onTemporalCycleSoftware()
    {
        acNextTime = 0L;
        acLoop = true;
        reLoop = true;
        ActionCounter.restart();
        RenderCounter.restart();
        while(running)
        {
            if(ready && !paused)
            {
                acCurTime = SystemClock.elapsedRealtime();
                acDeltaTime = acNextTime - acCurTime;
                if(acDeltaTime <= 0L)
                {
                    acNextTime = acCurTime + actionFreq;
                    acting = true;
                    while(GameActionStack.hasData())
                    {
                    	switch(GameActionStack.pop())
                        {
                        case 4: // '\004'
                            StageManager.getCurrentStage().onBackButton();
                            break;
                        }
                    }
                    if(StageManager._currentStage != null && StageManager._currentStage.isActive())
                    {
                        StageManager._currentStage.onAction();
                    }
                    Keyboard.clear();
                    TouchScreen.clear();
                    if(parameters.USE_ACTION_COUNTER)
                    {
                        ActionCounter.next();
                    }
                    drawCanvas = holder.lockCanvas();
                    if(drawCanvas != null)
                    {
                        rendering = true;
                        if(StageManager._currentStage != null && StageManager._currentStage.isActive())
                        {
                            StageManager._currentStage.onRender();
                        }
                        if(opAlias)
                        {
                            if(mNoStretch)
                            {
                                drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
                            } else
                            {
                                drawCanvas.setDrawFilter(dwfilter);
                                drawCanvas.drawBitmap(buffer, srcRect, dstRect, null);
                            }
                        } else
                        if(mNoStretch)
                        {
                            drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
                        } else
                        {
                            drawCanvas.drawBitmap(buffer, srcRect, dstRect, null);
                        }
                        holder.unlockCanvasAndPost(drawCanvas);
                        if(parameters.USE_RENDER_COUNTER)
                        {
                            RenderCounter.next();
                        }
                        rendering = false;
                    }
                    tick = 1L + tick;
                } else {
	                try
	                {
	                    Thread.sleep(acDeltaTime);
	                }
	                catch(InterruptedException interruptedexception1) { }
                }
            } else {
                try
                {
                    Thread.sleep(1000L);
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
        acLoop = false;
        reLoop = false;
        syncQuit();
    }

    protected static void onWindowFocusChanged(boolean flag)
    {
        if(flag)
        {
            setFullScreen2();
        }
    }

    public static final void postAction(int i, Object obj)
    {
        mHandler.sendMessage(mHandler.obtainMessage(2, i, 0, obj));
    }

    public static final boolean postRunnable(Runnable runnable)
    {
        return mHandler.post(runnable);
    }

    public static final boolean postRunnableDelayed(Runnable runnable, long l)
    {
        return mHandler.postDelayed(runnable, l);
    }

    public static final void pushToast(String s)
    {
        mHandler.pushToast(s);
    }

    public static final InputStreamEx readFile(String s)
    {
        File file;
        try {
            file = new File(getFilesDir().getAbsolutePath().concat("/").concat(s));
            if (file.exists()) {
                InputStreamEx inputstreamex = new InputStreamEx(file);
                return inputstreamex;
            } else {
                return null;
            }
        }catch(Exception e) {
            return null;
        }
    }

    public static final void registerJoypad(Joypad joypad)
    {
        if(androidSDKVersion >= 5)
        {
            JoypadUtils_API5.registerJoypad(joypad);
            return;
        } else
        {
            JoypadUtils_API4.registerJoypad(joypad);
            return;
        }
    }

    private static final void res_init()
    {
        try
        {
            Class class1 = Class.forName((new StringBuilder(String.valueOf(appPackageName))).append(".S").toString());
            res_t = (int[][])class1.getField("T").get(class1);
            res_l = (String[])class1.getField("L").get(class1);
            res_d = (String[])class1.getField("D").get(class1);
        }
        catch(Exception exception) { }
        res_lang = null;
        res_langid = -1;
        setLang(null);
    }

    public static final void restart()
    {
        if(activity == null)
        {
            return;
        } else
        {
            activity.startActivity(new Intent(activity, activity.getClass()));
            System.exit(0);
            return;
        }
    }

    public static final void restart(Class class1)
    {
        if(activity == null)
        {
            return;
        } else
        {
            activity.startActivity(new Intent(activity, class1));
            System.exit(0);
            return;
        }
    }

    protected static final void savePreferences()
    {
        if(!opLoaded)
        {
            throw new RuntimeException("SavePreferences called before LoadPreferences");
        }
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean("first", false);
        editor.putBoolean("vibrate", opVibrate);
        editor.putBoolean("haptic", opHaptic);
        editor.putBoolean("sound", opSound);
        editor.putBoolean("music", opMusic);
        editor.putBoolean("aliasing", opAlias);
        editor.putBoolean("stretch", opStretch);
        editor.putBoolean("wakeup", opWakeup);
        if(soundMngr != null)
        {
            soundMngr.setStreamVolume(3, opVolume, 0);
        }
        editor.commit();
    }

    public static final float scalef(float f)
    {
        if(multiplier != 1.0F)
        {
            f *= multiplier;
        }
        return f;
    }

    public static final int scalei(float f)
    {
        if(multiplier == 1.0F)
        {
            return (int)f;
        } else
        {
            return (int)(f * multiplier);
        }
    }

    public static final int scalei(int i)
    {
        if(multiplier != 1.0F)
        {
            i = (int)((float)i * multiplier);
        }
        return i;
    }

    public static final int screenTobufferX(float f)
    {
        return (int)(f * mMulRatioX);
    }

    public static final int screenTobufferY(float f)
    {
        return (int)(f * mMulRatioY);
    }

    public static final File screenshot(String s)
    {
        try {
            File file;
            synchronized (buffer) {
                String s1 = (new StringBuilder()).append(System.currentTimeMillis()).toString();
                file = new File(Environment.getExternalStorageDirectory().getPath().concat((new StringBuilder("/")).append(s).append("-").append(s1).append(".png").toString()));
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                buffer.compress(android.graphics.Bitmap.CompressFormat.PNG, 90, fileoutputstream);
                fileoutputstream.close();
                activity.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.parse((new StringBuilder("file://")).append(Environment.getExternalStorageDirectory()).toString())));
            }
            return file;
        }catch(Exception e) {
            return null;
        }
    }

    public static final void setActionRate(GameRate gamerate)
    {
        actionRate = gamerate;
        actionFreq = gamerate.getTime();
    }

    public static final void setAliasing(boolean flag)
    {
        setAntiAliasEnabled(flag);
    }

    public static final void setAntiAliasEnabled(boolean flag)
    {
        if(opAlias == flag)
        {
            return;
        } else
        {
            opAlias = flag;
            savePreferences();
            return;
        }
    }

    protected static final void setAppOrientation()
    {
        if(androidSDKVersion >= 11 && parameters != null)
        {
            int i = parameters.APP_ORIENTATION;
            if(i != -1)
            {
                activity.setRequestedOrientation(parameters.APP_ORIENTATION);
            }
        }
    }

    public static final void setBmpTextBitmap(Bitmap bitmap)
    {
        charLayer = bitmap;
    }

    public static final void setBmpTextSize(int i, int j, int k, int l)
    {
        charRealX = i;
        charRealY = j;
        charXSpace = k;
        charYSpace = l;
    }

    public static final void setColorMode(int i)
    {
        colorMode = i;
    }

    protected static void setContext(GameActivity gameactivity)
    {
        activity = gameactivity;
        try
        {
            PackageInfo packageinfo = activity.getPackageManager().getPackageInfo(appPackageName, 0);
            appVersionCode = packageinfo.versionCode;
            appVersionName = packageinfo.versionName;
        }
        catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
        }
        Rstring = getRClass("string");
        Rdrawable = getRClass("drawable");
        Rlayout = getRClass("layout");
        Rid = getRClass("id");
    }

    protected static final void setFullScreen()
    {
    }

    protected static void setFullScreen2()
    {
        while(metaNoFullScreen || androidSDKVersion < 19) 
        {
            return;
        }
        featureWrapper.setSystemUiVisibility(activity.getWindow().getDecorView(), 5894);
        featureWrapper.setOnSystemUiVisibilityChangeListener(activity.getWindow().getDecorView());
    }

    public static final void setHapticEnable(boolean flag)
    {
        if(opHaptic == flag)
        {
            return;
        } else
        {
            opHaptic = flag;
            savePreferences();
            return;
        }
    }

    public static final void setLang(String s)
    {
        int i;
        String s1;
        i = -1;
        s1 = null;
        if(s != null) {
            String s3;
            int j;
            s3 = s.toLowerCase();
            j = 0;
            int k;
            k = res_l.length;
            s1 = null;
            while (j < k) {
                if (res_l[j].equals(s3)) {
                    i = j;
                    s1 = s3;
                    break;
                }
                j++;
            }
        }
        if(res_langid != i)
        {
            String s2 = res_lang;
            res_langid = i;
            res_lang = s1;
            res_cache = null;
            application.onEngineLanguageChanged(s2, s1);
        }
        return;
    }

    public static final void setLayerTypeSofware(View view1)
    {
        featureWrapper.setLayerTypeSofware(view1);
    }

    public static final void setMultiplier(float f)
    {
        multiplier = f;
    }

    public static final void setMusicEnable(boolean flag)
    {
        if(opMusic == flag)
        {
            return;
        } else
        {
            opMusic = flag;
            savePreferences();
            return;
        }
    }

    public static void setNinePatchSize(int i)
    {
        ninePatchSize1 = i;
        ninePatchSize2 = 2 * ninePatchSize1;
        ninePatchSize3 = 3 * ninePatchSize1;
    }

    public static final void setPrefBoolean(String s, boolean flag)
    {
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putBoolean(s, flag);
        editor.commit();
    }

    public static final void setPrefFloat(String s, float f)
    {
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putFloat(s, f);
        editor.commit();
    }

    public static final void setPrefInt(String s, int i)
    {
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putInt(s, i);
        editor.commit();
    }

    public static final void setPrefLong(String s, long l)
    {
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putLong(s, l);
        editor.commit();
    }

    public static final void setPrefString(String s, String s1)
    {
        android.content.SharedPreferences.Editor editor = getPreferences().edit();
        editor.putString(s, s1);
        editor.commit();
    }

    public static final void setRate(GameRate gamerate)
    {
        renderRate = gamerate;
        actionRate = gamerate;
        renderFreq = gamerate.getTime();
        actionFreq = gamerate.getTime();
    }

    public static final void setRenderMode(GameRenderMode gamerendermode)
    {
        gameRenderMode = gamerendermode;
    }

    public static final void setRenderRate(GameRate gamerate)
    {
        renderRate = gamerate;
        renderFreq = gamerate.getTime();
    }

    public static final void setSoundEnable(boolean flag)
    {
        if(opSound == flag)
        {
            return;
        } else
        {
            opSound = flag;
            savePreferences();
            return;
        }
    }

    public static void setStage(int i)
    {
        StageManager.setStage(i);
    }

    public static void setTouchMode(int i)
    {
        _touchmode = i;
    }

    public static final void setVibrateEnable(boolean flag)
    {
        if(opVibrate == flag)
        {
            return;
        } else
        {
            opVibrate = flag;
            savePreferences();
            return;
        }
    }

    public static final void setVolume(int i)
    {
        while(soundMngr == null || opVolume == i) 
        {
            return;
        }
        opVolume = i;
        if(opVolume > 0) {
            opSound = true;
            if(opVolume > 15)
                opVolume = 15;
        }else {
            opSound = false;
            opVolume = 0;
        }
        savePreferences();
    }

    public static final void setWakeupEnable(final boolean v)
    {
        if(opWakeup == v)
        {
            return;
        }
        opWakeup = v;
        if(view != null)
        {
            mHandler.post(new Runnable() {
                public void run()
                {
                    Game.view.setKeepScreenOn(v);
                }
            });
        }
        savePreferences();
    }

    public static final void share(String s, String s1, String s2, String s3)
    {
        try
        {
            if(!featureWrapper.startFacebookShare(s3))
            {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/*");
                intent.putExtra("android.intent.extra.TEXT", s);
                intent.putExtra("android.intent.extra.SUBJECT", s1);
                activity.startActivity(Intent.createChooser(intent, s2));
            }
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public static final void showAlertDialog(Context context, String s, String s1)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setMessage(s1);
        builder.setTitle(s);
        builder.show();
    }

    public static final void showAlertDialog(String s, String s1)
    {
        showAlertDialog(((Context) (activity)), s, s1);
    }

    public static final boolean showBrowser(String s)
    {
        try
        {
            activity.startActivityForResult(new Intent("android.intent.action.VIEW", Uri.parse(s)), -1);
        }
        catch(Exception exception)
        {
            return false;
        }
        return true;
    }

    public static final boolean showFacebookPage()
    {
        if(hasFacebookApp())
        {
            return showBrowser("fb://page/121554497873951");
        } else
        {
            return showBrowser("https://m.facebook.com/121554497873951");
        }
    }

    public static final boolean showMarket(String s)
    {
        return marketWrapper.showGame(s);
    }

    public static final boolean showMarket(String s, String s1)
    {
        return marketWrapper.showGame(s, s1);
    }

    public static final boolean showMarketPublisher()
    {
        return marketWrapper.showPublisher();
    }

    public static final boolean showMarketPublisher(String s)
    {
        return marketWrapper.showPublisher(s);
    }

    public static final boolean showMarketUpdate()
    {
        return marketWrapper.showCurrentGame();
    }

    public static final boolean showMarketUpdate(String s)
    {
        return marketWrapper.showCurrentGame(s);
    }

    public static final void showMoreGames()
    {
        try
        {
            activity.startActivityForResult(new Intent(activity, com.magmamobile.mmusia.activities.MMUSIAMoreGamesActivity.class), 1);
            return;
        }
        catch(Exception exception)
        {
            return;
        }
    }

    public static final boolean showPrivacyPolicy()
    {
        return showBrowser("http://www.magmamobile.com/privacypolicy");
    }

    public static final void sleep(long l)
    {
        try
        {
            Thread.sleep(l);
            return;
        }
        catch(InterruptedException interruptedexception)
        {
            return;
        }
    }

    private static void syncQuit()
    {
        if(!acLoop && !reLoop)
        {
            if(activity != null && StageManager.isQuit())
            {
                StageManager.free();
                activity.finish();
            }
            application.onEngineTerminate();
            freeAllMusic();
        }
    }

    public static final void toast(int i)
    {
        mHandler.pushToast(getResString(i));
    }

    public static final void toast(String s)
    {
        mHandler.pushToast(s);
    }

    public static final void unregisterJoypad(Joypad joypad)
    {
        if(androidSDKVersion >= 5)
        {
            JoypadUtils_API5.unregisterJoypad(joypad);
            return;
        } else
        {
            JoypadUtils_API4.unregisterJoypad(joypad);
            return;
        }
    }

    protected static final void updateRatio()
    {
        mDivRatioX = (float)displayWidth / (float)mBufferWidth;
        mDivRatioY = (float)displayHeight / (float)mBufferHeight;
        mMulRatioX = (float)mBufferWidth / (float)displayWidth;
        mMulRatioY = (float)mBufferHeight / (float)displayHeight;
    }

    private static final void v0()
    {
        drawCanvas = holder.lockCanvas();
        if(drawCanvas == null)
            return;
        try {
            if (acting) {
                synchronized (actionSync) {
                    actionSync.wait(3000L);
                }
            }
            rendering = true;
            if (StageManager._currentStage != null && StageManager._currentStage.isActive()) {
                StageManager._currentStage.onRender();
            }
            if (mCLipFix) {
                drawCanvas.clipRect(0.0F, 0.0F, mBufferWidth, mBufferHeight, android.graphics.Region.Op.REPLACE);
            }
            InterruptedException interruptedexception;
            if (opAlias) {
                drawCanvas.setDrawFilter(dwfilter);
                drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
            } else {
                drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
            }
            holder.unlockCanvasAndPost(drawCanvas);
            if (parameters.USE_RENDER_COUNTER) {
                RenderCounter.next();
            }
            rendering = false;
            synchronized (renderSync) {
                renderSync.notifyAll();
            }
        }catch(Exception e) {

        }
    }

    private static final void v1()
    {
        drawCanvas = holder.lockCanvas();
        if(drawCanvas == null)
            return;
        try {
            if (acting) {
                synchronized (actionSync) {
                    actionSync.wait(3000L);
                }
            }
            rendering = true;
            if (StageManager._currentStage != null && StageManager._currentStage.isActive()) {
                StageManager._currentStage.onRender();
            }
            InterruptedException interruptedexception;
            if (opAlias) {
                if (mNoStretch) {
                    drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
                } else {
                    drawCanvas.setDrawFilter(dwfilter);
                    drawCanvas.drawBitmap(buffer, srcRect, dstRect, null);
                }
            } else if (mNoStretch) {
                drawCanvas.drawBitmap(buffer, 0.0F, 0.0F, null);
            } else {
                drawCanvas.drawBitmap(buffer, srcRect, dstRect, null);
            }
            holder.unlockCanvasAndPost(drawCanvas);
            if (parameters.USE_RENDER_COUNTER) {
                RenderCounter.next();
            }
            rendering = false;
            synchronized (renderSync) {
                renderSync.notifyAll();
            }
        }catch (Exception e) {

        }
    }

    public static final void vibrate(long l)
    {
        if(!opVibrate || vibrator == null)
        {
            return;
        } else
        {
            vibrator.vibrate(l);
            return;
        }
    }

    public static final void vibrate(long al[], int i)
    {
        if(!opVibrate || vibrator == null)
        {
            return;
        } else
        {
            vibrator.vibrate(al, i);
            return;
        }
    }

    public static final void volumeDown()
    {
        setVolume(-1 + opVolume);
    }

    public static final void volumeUp()
    {
        setVolume(1 + opVolume);
    }

    public static final OutputStreamEx writeFile(String s)
    {
        File file = new File(getFilesDir().getAbsolutePath().concat("/").concat(s));
        OutputStreamEx outputstreamex;
        try
        {
            outputstreamex = new OutputStreamEx(file);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            filenotfoundexception.printStackTrace();
            return null;
        }
        return outputstreamex;
    }

}
