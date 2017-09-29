package com.magmamobile.game.engine;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.*;
import android.view.*;
import android.view.animation.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.io.InputStream;

public class SplashActivity extends Activity
{

    private BitmapDrawable image;
    private ImageView imageview;
    private Class mActivity;
    private int mBackColor;
    private boolean mCancel;
    private long mDuration;
    private Handler mHandler;
    private String mLogoAsset;
    private long mStartTime;

    public SplashActivity(Class class1)
    {
        this(class1, "magmamobile.png");
    }

    public SplashActivity(Class class1, String s)
    {
        this(class1, s, 100L, 3000L);
    }

    public SplashActivity(Class class1, String s, long l, long l1)
    {
        this(class1, s, l, l1, -1);
    }

    public SplashActivity(Class class1, String s, long l, long l1, int i)
    {
        mActivity = class1;
        mLogoAsset = s;
        mStartTime = l;
        mDuration = l1;
        mBackColor = i;
    }

    public static final int getVersion()
    {
        return 259;
    }

    private void handleMessage(Message message)
    {
        float f = image.getIntrinsicWidth() >> 1;
        float f1 = image.getIntrinsicHeight() >> 1;
        int i = getWindowManager().getDefaultDisplay().getWidth();
        int j = (int)((float)i * (f1 / f));
        AnimationSet animationset = new AnimationSet(true);
        TranslateAnimation translateanimation = new TranslateAnimation(0.0F, 0.0F, -500F, 0.0F);
        translateanimation.setDuration(1000L);
        animationset.addAnimation(translateanimation);
        ScaleAnimation scaleanimation = new ScaleAnimation(10F, 1.0F, 10F, 1.0F, f, f1);
        scaleanimation.setDuration(1000L);
        animationset.addAnimation(scaleanimation);
        RotateAnimation rotateanimation = new RotateAnimation(360F, 0.0F, f, f1);
        rotateanimation.setDuration(1000L);
        animationset.addAnimation(rotateanimation);
        imageview.setBackgroundDrawable(image);
        imageview.setLayoutParams(new android.widget.LinearLayout.LayoutParams(i, j));
        imageview.startAnimation(animationset);
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(Game.metaiDTGV)
        {
            getWindow().addFlags(0x80000);
        }
        mHandler = new Handler() {
            public void handleMessage(Message message)
            {
                SplashActivity.this.handleMessage(message);
            }
        };
        imageview = new ImageView(this);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setBackgroundColor(mBackColor);
        linearlayout.setGravity(17);
        linearlayout.addView(imageview);
        setContentView(linearlayout);
    }

    protected void onStart()
    {
        super.onStart();
        (new Thread() {
            public void run()
            {
                try
                {
                    InputStream inputstream = getAssets().open(mLogoAsset);
                    image = new BitmapDrawable(inputstream);
                    inputstream.close();
                }
                catch(Exception exception)
                {
                    throw new RuntimeException("Can't load MagmaMobile logo", exception);
                }
                try
                {
                    Thread.sleep(mStartTime);
                }
                catch(Exception exception1) { }
                mHandler.sendEmptyMessage(0);
                try
                {
                    Thread.sleep(mDuration);
                }
                catch(Exception exception2) { }
                if(!mCancel && mActivity != null)
                {
                    startActivity(new Intent(SplashActivity.this, mActivity));
                }
                finish();
            }
        }).start();
    }

    protected void onStop()
    {
        super.onStop();
        mCancel = true;
    }
}
