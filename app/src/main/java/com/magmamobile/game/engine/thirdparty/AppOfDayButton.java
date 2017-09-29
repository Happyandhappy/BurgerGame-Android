package com.magmamobile.game.engine.thirdparty;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.engine.*;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.image.ImageCache;
import com.magmamobile.mmusia.parser.data.ApiBase;

public class AppOfDayButton extends GameObject
{

    protected Bitmap background;
    protected Bitmap curBitmap;
    protected int innerHeight;
    protected int innerWidth;
    protected Bitmap oldBitmap;
    protected float paddingLeft;
    protected float paddingTop;
    protected Paint paint;
    protected Sound sound;

    public AppOfDayButton()
    {
        visible = true;
        enabled = true;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Sound sound1;
        if(Game.getParameters().DEFAULT_BUTTON_SOUND != 0)
        {
            sound1 = Game.getSound(Game.getParameters().DEFAULT_BUTTON_SOUND);
        } else
        {
            sound1 = null;
        }
        sound = sound1;
        request();
    }

    public AppOfDayButton(int i, int j, int k, int l)
    {
        visible = true;
        enabled = true;
        x = i;
        y = j;
        w = k;
        h = l;
        innerWidth = k;
        innerHeight = l;
        paddingLeft = 0.0F;
        paddingTop = 0.0F;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Sound sound1;
        if(Game.getParameters().DEFAULT_BUTTON_SOUND != 0)
        {
            sound1 = Game.getSound(Game.getParameters().DEFAULT_BUTTON_SOUND);
        } else
        {
            sound1 = null;
        }
        sound = sound1;
        request();
    }

    public AppOfDayButton(int i, int j, int k, int l, int i1, int j1)
    {
        visible = true;
        enabled = true;
        x = i;
        y = j;
        w = k;
        h = l;
        innerWidth = i1;
        innerHeight = j1;
        paddingLeft = (k - i1) / 2;
        paddingTop = (k - j1) / 2;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        Sound sound1;
        if(Game.getParameters().DEFAULT_BUTTON_SOUND != 0)
        {
            sound1 = Game.getSound(Game.getParameters().DEFAULT_BUTTON_SOUND);
        } else
        {
            sound1 = null;
        }
        sound = sound1;
        request();
    }

    private void releasePreviousBitmap()
    {
        if(oldBitmap != null && !oldBitmap.isRecycled())
        {
            oldBitmap.recycle();
            oldBitmap = null;
            System.gc();
        }
    }

    private void request()
    {
        (new Thread(new Runnable() {
            public int load(int i)
            {
                int j = 1;
                try {
	                while(j < 5) {
		                ApiBase apibase = MMUSIA.api;
		                if(apibase != null) {
		                	break;
		                }
		                long l = j * 1000;
	                    Thread.sleep(l);
	                    j++;
	                }
                }catch(InterruptedException e) {
                	e.printStackTrace();
                }
                if(MMUSIA.api != null && MMUSIA.api.appodayIconUrl != null) {
                	if(MMUSIA.api.appodayId == i)
                    	return i;
                    oldBitmap = curBitmap;
                    Bitmap bitmap = ImageCache.requestAppOfTheDayBitmap(Game.getContext());
                    if(bitmap != null)
                    {
                        curBitmap = bitmap;
                    }
                    releasePreviousBitmap();
                    return MMUSIA.api.appodayId;
                }
                i = -1;
                return i;
            }

            public void run()
            {
                int i = load(-1);
                try
                {
                    Thread.sleep(10000L);
                }
                catch(InterruptedException interruptedexception)
                {
                    interruptedexception.printStackTrace();
                }
                load(i);
            }
        })).start();
    }

    public Bitmap getBackround()
    {
        return background;
    }

    public float getInnerHeight()
    {
        return (float)innerHeight;
    }

    public float getInnerWidth()
    {
        return (float)innerWidth;
    }

    public float getPaddingLeft()
    {
        return paddingLeft;
    }

    public float getPaddingTop()
    {
        return paddingTop;
    }

    public Sound getSound()
    {
        return sound;
    }

    public void onAction()
    {
        while(!visible || !enabled || curBitmap == null || !TouchScreen.eventUp || !MathUtils.PtInRect((int)x, (int)y, (int)(x + (float)w), (int)(y + (float)h), TouchScreen.x, TouchScreen.y)) 
        {
            return;
        }
        if(sound != null)
        {
            sound.play();
        }
        MMUSIA.openAppOfTheDay(Game.getContext());
    }

    public void onRender()
    {
        if(visible && enabled)
        {
            if(background != null)
            {
                Game.drawBitmap(background, x, y);
            }
            if(curBitmap != null && !curBitmap.isRecycled())
            {
                if(angle == 0.0F)
                {
                    Game.drawBitmap(curBitmap, (int)(x + paddingLeft), (int)(y + paddingTop), innerWidth, innerHeight, paint);
                    return;
                } else
                {
                    Game.drawBitmap(curBitmap, (int)(x + paddingLeft + (float)(innerWidth / 2)), (int)(y + paddingTop + (float)(innerHeight / 2)), (int)angle, 1.0F, paint);
                    return;
                }
            }
        }
    }

    public void setBackground(Bitmap bitmap)
    {
        background = bitmap;
    }

    public void setInnerHeight(int i)
    {
        innerHeight = i;
    }

    public void setInnerWidth(int i)
    {
        innerWidth = i;
    }

    public void setPaddingLeft(float f)
    {
        paddingLeft = f;
    }

    public void setPaddingTop(float f)
    {
        paddingTop = f;
    }

    public void setSound(Sound sound1)
    {
        sound = sound1;
    }

}
