package com.magmamobile.game.engine;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class Dialog extends GameObject
{

    public static final int BUTTON1 = 1;
    public static final int BUTTON2 = 2;
    public static final int NONE = 0;
    private Bitmap background;
    private Button btn1;
    private Button btn2;
    private int clicked;
    private Label label;
    private Paint shadow;

    public Dialog(String s, String s1, String s2, boolean flag, Bitmap bitmap, Bitmap bitmap1, Bitmap bitmap2)
    {
        label = new Label();
        label.setMinWidth(Game.mBufferCW);
        label.setAutoSize(true);
        label.setText(s);
        label.setColor(-1);
        background = bitmap;
        if(flag)
        {
            shadow = new Paint();
            shadow.setColor(0x80000000);
        }
        computeSize();
        if(s1 != null)
        {
            btn1 = new Button(0, 0, 0, 0, false, s1, null, bitmap1, bitmap2, null, null);
        }
        if(s2 != null)
        {
            btn2 = new Button(0, 0, 0, 0, false, s2, null, bitmap1, bitmap2, null, null);
        }
    }

    public void computeSize()
    {
        int i;
        byte byte0;
label0:
        {
            i = label.h;
            if(btn1 == null)
            {
                Button button = btn2;
                byte0 = 0;
                if(button == null)
                {
                    break label0;
                }
            }
            byte0 = 60;
        }
        w = 40 + label.w;
        h = byte0 + (i + 40);
        cw = w >> 1;
        ch = h >> 1;
        x = Game.mBufferWidth - w >> 1;
        y = Game.mBufferHeight - h >> 1;
        if(btn1 != null)
        {
            if(btn2 == null)
            {
                btn1.setW(MathUtils.maxi(-20 + cw, 48));
                btn1.setX((int)x + (cw - btn1.cw));
            } else
            {
                btn1.setX(15 + (int)x);
                btn1.setW(MathUtils.maxi(-20 + cw, 48));
            }
            btn1.setY(40 + (i + (int)y));
            btn1.setH(byte0 - 20);
        }
        if(btn2 != null)
        {
            btn2.setX(5 + ((int)x + cw));
            btn2.setY(40 + (i + (int)y));
            btn2.setW(MathUtils.maxi(-20 + cw, 48));
            btn2.setH(byte0 - 20);
        }
    }

    public final void dissmiss()
    {
        enabled = false;
        visible = false;
        clicked = 0;
    }

    public Button getButton(int i)
    {
        switch(i)
        {
        default:
            return null;

        case 0: // '\0'
            return btn1;

        case 1: // '\001'
            return btn2;
        }
    }

    public Label getLabel()
    {
        return label;
    }

    public final int getResult()
    {
        return clicked;
    }

    public String getText()
    {
        return label.getText();
    }

    public void onAction()
    {
        if(enabled)
        {
            clicked = 0;
            if(btn1 != null)
            {
                btn1.onAction();
                if(btn1.onClick)
                {
                    clicked = 1;
                    visible = false;
                    enabled = false;
                    return;
                }
            }
            if(btn2 != null)
            {
                btn2.onAction();
                if(btn2.onClick)
                {
                    clicked = 2;
                    visible = false;
                    enabled = false;
                    return;
                }
            }
        }
    }

    public void onRender()
    {
        if(visible && enabled)
        {
            if(shadow != null)
            {
                Game.drawPaint(shadow);
            }
            if(background != null)
            {
                Game.drawBitmap9(background, (int)x, (int)y, w, h, null);
            }
            if(label != null)
            {
                label.x = x + (float)(cw - label.cw);
                label.y = 20F + y;
                label.onRender();
            }
            if(btn1 != null)
            {
                btn1.onRender();
            }
            if(btn2 != null)
            {
                btn2.onRender();
                return;
            }
        }
    }

    public void setText(String s)
    {
        label.setText(s);
    }

    public final void show()
    {
        computeSize();
        enabled = true;
        visible = true;
        clicked = 0;
    }
}
