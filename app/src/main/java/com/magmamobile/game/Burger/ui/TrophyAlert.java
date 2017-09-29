package com.magmamobile.game.Burger.ui;

import android.graphics.*;

import com.magmamobile.game.Burger.R;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.engine.Game;
import com.magmamobile.game.engine.MathUtils;
import java.util.ArrayList;

public class TrophyAlert
{

    private final int WAIT = 64;
    public boolean added;
    private float f;
    private Bitmap icon;
    private int iconH;
    private int iconW;
    private int iconX;
    private int iconY;
    private String label;
    private int labelY;
    private Matrix mtx;
    private Paint pAlias;
    private Paint pLabel;
    private Paint pRect;
    private Paint pTitle;
    private int phase;
    private ArrayList queue;
    private int radius;
    private RectF rect;
    private int textX;
    private String title;
    private int titleY;
    private int width;
    private int x;
    private int y;

    public TrophyAlert()
    {
        added = false;
        phase = 0;
        mtx = new Matrix();
        pAlias = new Paint();
        pAlias.setAntiAlias(true);
        queue = new ArrayList();
        radius = Game.scalei(17);
        pRect = new Paint();
        pRect.setColor(0xff596365);
        title = Game.getResString(R.string.res_successalert);
        pTitle = new Paint();
        pTitle.setColor(-1);
        pTitle.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        pTitle.setTextSize(Game.scalef(12F));
        pLabel = new Paint();
        pLabel.setColor(-1);
        pLabel.setTypeface(Typeface.SANS_SERIF);
        pLabel.setTextSize(Game.scalef(10F));
        x = 0;
        y = Game.scalei(16);
        iconX = x + Game.scalei(4);
        iconY = y + Game.scalei(2);
        int i = Game.scalei(30);
        iconH = i;
        iconW = i;
        textX = x + Game.scalei(38);
        titleY = y + Game.scalei(14);
        labelY = y + Game.scalei(28);
        rect = new RectF(x - radius, y, x + Game.scalei(128), y + Game.scalei(34));
    }

    private void moveIn()
    {
        f = 0.0F;
        setMatrix(f);
        phase = 1;
        SoundManager.playSound(30);
    }

    private void moveOut()
    {
        f = 1.0F;
        setMatrix(f);
        phase = 2;
    }

    private void setMatrix(float f1)
    {
        mtx.setTranslate(MathUtils.lerpDecelerate(-width, 0.0F, f1), 0.0F);
    }

    private void show()
    {
        if(phase == 0)
        {
            setTrophy(((Integer)queue.get(0)).intValue());
            queue.remove(0);
            moveIn();
        }
    }

    public void animate()
    {
        switch (phase) {
            default:case 3:
                f = 0.0F;
                phase = 0;
                return;
            case 1:
                f = 0.1F + f;
                if(f >= 1.0F)
                {
                    f = 1.0F;
                    wait(64);
                }
                setMatrix(f);
                return;
            case 2:
                f = f - 0.1F;
                if(f <= 0.0F)
                {
                    f = 0.0F;
                    phase = 0;
                    if(!queue.isEmpty())
                    {
                        show();
                    }
                }
                setMatrix(f);
                return;
            case 4:
                f = f - 1.0F;
                if(f > 0.0F)
                    return;
                moveOut();
                return;
        }
    }

    public void draw()
    {
        if(PrefManager.configs[3] && phase != 4)
        {
            Game.mCanvas.setMatrix(mtx);
        }
        Game.mCanvas.drawRoundRect(rect, radius, radius, pRect);
        Game.drawBitmap(icon, iconX, iconY, iconW, iconH, pAlias);
        Game.drawText(title, textX, titleY, pTitle);
        Game.drawText(label, textX, labelY, pLabel);
        if(PrefManager.configs[3])
        {
            Game.mCanvas.setMatrix(null);
        }
    }

    public boolean isVisible()
    {
        return phase != 0;
    }

    public void setTrophy(int i)
    {
        icon = BitmapManager.successIcon[i];
        label = Game.getResString(AchievementManager.TITLE[i]);
        width = textX + Math.max(Game.getTextWidth(title, pTitle), Game.getTextWidth(label, pLabel)) + radius / 2;
        rect.right = x + width;
    }

    public void show(int i)
    {
        queue.add(Integer.valueOf(i));
        show();
    }

    public void show(int ai[])
    {
        int i = ai.length;
        int j = 0;
        do
        {
            if(j >= i)
            {
                show();
                return;
            }
            int k = ai[j];
            queue.add(Integer.valueOf(k));
            j++;
        } while(true);
    }

    public void wait(int i)
    {
        f = i;
        phase = 4;
    }
}
