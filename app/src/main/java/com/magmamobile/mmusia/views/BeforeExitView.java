package com.magmamobile.mmusia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.magmamobile.mmusia.MMUSIA;

public class BeforeExitView extends LinearLayout
{

    private Context mContext;

    public BeforeExitView(Context context)
    {
        super(context);
        setBackgroundColor(0xff000000);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public BeforeExitView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = context;
        setBackgroundColor(0xff000000);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public void buildView(Context context)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout linearlayout1 = new LinearLayout(context);
        linearlayout1.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        linearlayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearlayout1.setGravity(16);
        linearlayout1.setMinimumHeight(MMUSIA.dpi(48F));
        linearlayout1.setId(MMUSIA.RES_ID_ITEM_LINEARITEM);
        linearlayout1.setBackgroundColor(0xff000000);
        ImageViewEx imageviewex = new ImageViewEx(context);
        imageviewex.setLayoutParams(new android.view.ViewGroup.LayoutParams(MMUSIA.dpi(64F), MMUSIA.dpi(64F)));
        imageviewex.setId(MMUSIA.RES_ID_IMG_MOREGAMES_HEAD);
        imageviewex.setPadding(MMUSIA.dpi(5F), MMUSIA.dpi(5F), MMUSIA.dpi(10F), MMUSIA.dpi(5F));
        TextView textview = new TextView(context);
        textview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        textview.setId(MMUSIA.RES_ID_TITLE_MOREGAMES_HEAD);
        textview.setTextColor(-1);
        textview.setTextSize(0, MMUSIA.dpi(14F));
        textview.setTypeface(MMUSIA.getTypeFace(), 1);
        textview.setMaxLines(2);
        linearlayout1.addView(imageviewex);
        linearlayout1.addView(textview);
        addView(linearlayout1);
        GridView gridview = new GridView(context);
        gridview.setId(MMUSIA.RES_ID_LISTVIEW_MOREGAMES);
        gridview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        gridview.setBackgroundColor(-1);
        gridview.setCacheColorHint(-1);
        gridview.setClickable(true);
        gridview.setNumColumns(3);
        LinearLayout linearlayout2 = new LinearLayout(context);
        linearlayout2.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        linearlayout2.setOrientation(LinearLayout.HORIZONTAL);
        linearlayout2.setBackgroundColor(0xffcacaca);
        linearlayout.addView(gridview);
        addView(linearlayout);
        addView(linearlayout2);
    }
}
