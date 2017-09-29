package com.magmamobile.mmusia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.magmamobile.mmusia.MMUSIA;

public class ItemGridViewMoreGame extends LinearLayout
{

    private Context mContext;

    public ItemGridViewMoreGame(Context context)
    {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public ItemGridViewMoreGame(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mContext = context;
        buildView(context);
    }

    public void buildView(Context context)
    {
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        linearlayout.setOrientation(LinearLayout.HORIZONTAL);
        linearlayout.setGravity(16);
        linearlayout.setMinimumHeight(MMUSIA.dpi(48F));
        linearlayout.setId(MMUSIA.RES_ID_ITEM_LINEARITEM);
        ImageViewEx imageviewex = new ImageViewEx(context);
        imageviewex.setLayoutParams(new android.view.ViewGroup.LayoutParams(MMUSIA.dpi(64F), MMUSIA.dpi(64F)));
        imageviewex.setId(MMUSIA.RES_ID_ITEM_IMG);
        imageviewex.setPadding(MMUSIA.dpi(5F), MMUSIA.dpi(5F), MMUSIA.dpi(10F), MMUSIA.dpi(5F));
        LinearLayout linearlayout1 = new LinearLayout(context);
        linearlayout1.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        linearlayout1.setOrientation(LinearLayout.VERTICAL);
        TextView textview = new TextView(context);
        textview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        textview.setId(MMUSIA.RES_ID_ITEM_TITLE);
        textview.setTextColor(0xff000000);
        textview.setTypeface(MMUSIA.getTypeFace(), 1);
        textview.setMaxLines(2);
        TextView textview1 = new TextView(context);
        textview1.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, -2));
        textview1.setId(MMUSIA.RES_ID_MOREGAMES_ITEM_FREE);
        textview1.setTextColor(0xff880000);
        textview1.setTextSize(0, MMUSIA.dpi(10F));
        textview1.setTypeface(MMUSIA.getTypeFace(), 1);
        textview1.setMaxLines(2);
        textview1.setGravity(5);
        textview1.setPadding(0, 0, MMUSIA.dpi(10F), 0);
        View view = new View(context);
        view.setBackgroundColor(0xffc0c0c0);
        view.setLayoutParams(new android.view.ViewGroup.LayoutParams(-2, 1));
        linearlayout1.addView(textview);
        linearlayout1.addView(textview1);
        linearlayout.addView(imageviewex);
        linearlayout.addView(linearlayout1);
        addView(linearlayout);
        addView(view);
    }
}
