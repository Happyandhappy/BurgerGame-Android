package com.magmamobile.game.Burger.game;

import android.graphics.Bitmap;
import android.graphics.Paint;
import com.magmamobile.game.Burger.App;
import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.managers.BitmapManager;
import com.magmamobile.game.Burger.managers.GameManager;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;

public class InfoMessage
{

    private int center;
    private int dgx;
    private int dmx;
    private float f;
    private float fontSize;
    public String goal;
    public boolean goalAdded;
    public int gx;
    public int gy;
    private int height;
    public String label;
    private int left;
    public int mx;
    public int my;
    private int nextPhase;
    private int num;
    public Paint pGoal1;
    public Paint pGoal2;
    public Paint pLabel1;
    public Paint pLabel2;
    private int phase;
    private int right;
    private float scale;
    private float strokeSize;
    public boolean visible;
    private int wFrames;
    private int width;
    public int x;
    public int y;

    public InfoMessage()
    {
        visible = false;
        center = Game.mBufferCW;
        phase = 0;
        fontSize = App.scalefFont(60F);
        strokeSize = Game.scalef(6F);
        pLabel1 = new Paint();
        pLabel1.setAntiAlias(Game.getAntiAliasEnabled());
        pLabel1.setColor(-1);
        pLabel1.setTypeface(App.defaultFont);
        pLabel1.setTextSize(fontSize);
        pLabel1.setTextAlign(android.graphics.Paint.Align.CENTER);
        pLabel2 = App.getStroked(pLabel1, strokeSize, 0xff338eff);
        pGoal1 = new Paint(pLabel1);
        pGoal1.setTextSize(App.scalefFont(32F));
        pGoal1.setTextAlign(android.graphics.Paint.Align.LEFT);
        pGoal2 = App.getStroked(pGoal1, Game.scalef(5F), 0xff338eff);
    }

    private void offsetText(int i)
    {
        x = i;
        gx = x + dgx;
        mx = x + dmx;
    }

    public void animate()
    {
        switch(phase) {
            default:
            case 3:
            case 5:
                break;
            case 1:
                f = 0.075F + f;
                if (f >= 1.0F) {
                    f = 1.0F;
                    phase = 0;
                }
                offsetText((int) MathUtils.lerpOvershoot(right, center, f));
                break;
            case 2:
                f = f - 0.1F;
                if(f <= 0.0F)
                {
                    f = 0.0F;
                    if(num == 0)
                    {
                        phase = 5;
                    } else
                    {
                        wait(8, 6);
                    }
                }
                offsetText((int)MathUtils.lerpOvershoot(left, center, f));
                break;
            case 4:
                wFrames = -1 + wFrames;
                if (wFrames <= 0) {
                    if (nextPhase == 1) {
                        moveIn();
                        return;
                    }
                    if (nextPhase == 6) {
                        countDown();
                        return;
                    } else {
                        moveOut();
                        return;
                    }
                }
                break;
            case 6:
                f = 0.05F + f;
                if(f >= 1.0F)
                {
                    f = 1.0F;
                    num = -1 + num;
                    if(num < 0)
                    {
                        f = 1.0F;
                        phase = 5;
                    } else
                    {
                        f = 0.0F;
                        if(num > 0)
                        {
                            setText(String.valueOf(num));
                        } else
                        {
                            setText(Game.getResString(R.string.res_go));
                        }
                    }
                }
                scale = MathUtils.lerpDecelerate(1.0F, 2.0F, f);
                y = (int)((float)Game.scalei(320) + (float)height * scale) / 2;
                pLabel1.setTextSize(fontSize * scale);
                pLabel2.setTextSize(fontSize * scale);
                pLabel2.setStrokeWidth(strokeSize * scale);
                break;
        }
    }

    public void countDown()
    {
        f = 0.0F;
        x = center;
        setText(String.valueOf(num));
        phase = 6;
    }

    public boolean isFinished()
    {
        return phase == 5;
    }

    public boolean isRunning()
    {
        return phase != 0;
    }

    public boolean isWaiting()
    {
        return phase == 4;
    }

    public void moveIn()
    {
        f = 0.0F;
        x = right;
        visible = true;
        int i;
        if(GameManager.gameMode == 1)
        {
            i = 3;
        } else
        {
            i = 0;
        }
        num = i;
        phase = 1;
        offsetText(x);
    }

    public void moveOut()
    {
        if(visible)
        {
            f = 1.0F;
            phase = 2;
        }
    }

    public void moveOut(boolean flag)
    {
        if(flag)
        {
            num = 0;
        }
        moveOut();
    }

    public void setText(String s)
    {
        label = s;
        pLabel1.setTextSize(fontSize);
        pLabel2.setTextSize(fontSize);
        pLabel2.setStrokeWidth(strokeSize);
        width = Game.getTextWidth(label, pLabel2);
        height = Game.getTextHeight(label, pLabel2);
        y = (Game.mBufferHeight + height) / 2;
        right = Game.mBufferWidth + width;
        left = -width;
        goalAdded = false;
    }

    public void setText(String s, int i)
    {
        setText(s);
        y = Game.mBufferCH;
        goal = (new StringBuilder(String.valueOf(Game.getResString(R.string.res_goal)))).append(": ").append(i).toString();
        int j = Game.getTextWidth(goal, pGoal2);
        int k = BitmapManager.mIntro.getWidth();
        dgx = (j + k) / -2;
        dmx = j + dgx;
        gy = y + Game.getTextHeight(goal, pGoal2) + Game.scalei(4);
        my = (gy - BitmapManager.mIntro.getHeight()) + Game.scalei(3);
        right = Math.max(right, k + (j + Game.mBufferWidth));
        left = Math.min(left, -(j + k));
        goalAdded = true;
    }

    public void stop()
    {
        phase = 0;
        visible = false;
    }

    public void wait(int i, int j)
    {
        wFrames = i;
        nextPhase = j;
        phase = 4;
    }
}
