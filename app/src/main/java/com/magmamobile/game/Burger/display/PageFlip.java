package com.magmamobile.game.Burger.display;

import android.graphics.*;
import com.magmamobile.game.Burger.game.MenuTimer;
import com.magmamobile.game.Burger.managers.*;
import com.magmamobile.game.Burger.stages.Board;
import com.magmamobile.game.engine.MathUtils;

public class PageFlip
{

    public static final int MODE_CALENDAR = 1;
    public static final int MODE_MENU = 0;
    private float cx;
    private float degrees;
    public float e1;
    public float e2;
    public float f;
    public int height;
    public Matrix matrix;
    public int mode;
    public Matrix mtx;
    private Region paper;
    public Path path1;
    public Path path2;
    public int phase;
    private Region region;
    public int reverse;
    private final float speed = 0.075F;
    private float tan;
    public boolean visible;
    public int width;
    public int x;
    private int xA;
    private int xB;
    public int y;
    private int yA;
    private int yB;

    public PageFlip()
    {
        visible = true;
        path1 = new Path();
        path2 = new Path();
        matrix = new Matrix();
        mtx = new Matrix();
        visible = true;
    }

    private int getReverse()
    {
        switch(mode) {
            default:
                if(phase != 1)
                    return -1;
                return 1;
            case 1:
                if(phase != 2)
                {
                    return -1;
                }
                return 1;
        }
    }

    private void setAngle(float f1)
    {
        int k1;
        Matrix matrix1;
        float f2;
        float f3;
        int j2;
        int i = 2;
        tan = (float)Math.tan(f1);
        reverse = getReverse();
        int j = x;
        int k;
        int l1;
        if(reverse == 1)
        {
            k = width;
        } else
        {
            k = 0;
        }
        xB = k + j;
        yB = y + (int)((float)width * tan);
        if(Math.abs(f1) > e2)
        {
            int i2 = x;
            if(reverse == 1)
            {
                j2 = 2 * width - (int)((float)height / tan);
            } else
            {
                j2 = (int)((float)height / tan) - width;
            }
            xA = j2 + i2;
            yA = y + height;
        } else
        {
            int l = x;
            int i1 = reverse;
            int j1 = 0;
            if(i1 != 1)
            {
                j1 = width;
            }
            xA = l + j1;
            yA = y + (int)(tan * (float)(2 * width));
        }
        path1.rewind();
        path1.moveTo(x, y);
        path1.lineTo(x + width, y);
        if(reverse == 1)
        {
            path1.lineTo(xB, yB);
            path1.lineTo(xA, yA);
            if(Math.abs(f1) >= e2)
            {
                path1.lineTo(x, y + height);
            }
        } else
        {
            if(Math.abs(f1) >= e2)
            {
                path1.lineTo(x + width, y + height);
            }
            path1.lineTo(xA, yA);
            path1.lineTo(xB, yB);
        }
        path1.close();
        paper.set(x, y, x + width, y + height);
        region.setPath(path1, paper);
        paper.op(region, android.graphics.Region.Op.DIFFERENCE);
        path2 = paper.getBoundaryPath();
        degrees = (float)((Math.toDegrees(f1) - 90D) * (double)reverse);
        k1 = x;
        if(reverse == 1)
        {
            l1 = i;
        } else
        {
            l1 = -1;
        }
        cx = k1 + l1 * width;
        matrix.setTranslate(-cx, -y);
        matrix.postRotate(degrees);
        matrix.postScale(-1F, 1.0F);
        matrix.postRotate(-degrees);
        matrix.postTranslate(cx, y);
        path2.transform(matrix);
        matrix1 = mtx;
        f2 = -2F * (float)reverse;
        f3 = -(e1 - f1);
        if(mode != 1)
        {
            i = 1;
        }
        matrix1.setScale(f2, f3 * (float)i);
        mtx.postRotate((float)Math.toDegrees(f1) * (float)(-reverse));
        mtx.postTranslate(xB, yB);
    }

    public void animate()
    {
        switch(phase) {
            default:
                phase = 0;
                break;
            case 1:
                visible = true;
                f = 0.075F + f;
                if(f > 1.0F)
                {
                    phase = 0;
                    if(mode == 0)
                    {
                        Board.activate();
                        if(GameManager.menuTimed)
                        {
                            MenuTimer.start();
                            return;
                        }
                    } else
                    {
                        phase = 5;
                        return;
                    }
                } else
                {
                    setAngle(MathUtils.lerpDecelerate(0.0F, e1, f));
                    return;
                }
                break;
            case 2:
                visible = PrefManager.configs[3];
                f = 0.075F + f;
                if(f > 1.0F)
                {
                    setAngle(0.0F);
                    phase = 5;
                } else {
                    setAngle(MathUtils.lerpAccelerate(e1, 0.0F, f));
                }
                break;
        }
    }

    public void init()
    {
        e1 = (float)Math.atan((float)height / (float)width);
        e2 = (float)Math.atan((float)height / (float)(2 * width));
        region = new Region();
        paper = new Region();
        setAngle(0.0F);
        stop();
        visible = true;
    }

    public boolean isFinished()
    {
        return phase == 5;
    }

    public boolean isPlaying()
    {
        return phase != 0;
    }

    public void moveIn()
    {
        f = 0.0F;
        phase = 1;
        SoundManager.playSound(23);
    }

    public void moveOut()
    {
        f = 0.0F;
        phase = 2;
        SoundManager.playSound(24);
        setAngle(e1);
        visible = PrefManager.configs[3];
    }

    public void stop()
    {
        phase = 0;
    }
}
