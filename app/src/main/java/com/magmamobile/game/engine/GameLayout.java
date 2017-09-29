package com.magmamobile.game.engine;

import android.content.Context;
import android.widget.RelativeLayout;

public final class GameLayout extends RelativeLayout
{
    public static final class LayoutParams extends android.widget.RelativeLayout.LayoutParams
    {

        public int getLeft()
        {
            return leftMargin;
        }

        public int getTop()
        {
            return topMargin;
        }

        public void setLeft(int i)
        {
            leftMargin = i;
        }

        public void setTop(int i)
        {
            topMargin = i;
        }

        public LayoutParams(int i, int j)
        {
            super(i, j);
        }
    }


    public GameLayout(Context context)
    {
        super(context);
        setBackgroundColor(0x80ffff00);
    }
}
