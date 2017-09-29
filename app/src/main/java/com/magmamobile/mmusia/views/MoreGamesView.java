package com.magmamobile.mmusia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.magmamobile.mmusia.MMUSIA;

public class MoreGamesView extends LinearLayout
{

    private Context mContext;

    public MoreGamesView(Context context)
    {
        super(context);
        setBackgroundColor(0xff000000);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public MoreGamesView(Context context, AttributeSet attributeset)
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
        LinearLayout linearlayout2 = new LinearLayout(context);
        linearlayout2.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        linearlayout2.setOrientation(LinearLayout.VERTICAL);
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
        ListView listview = new ListView(context);
        listview.setId(MMUSIA.RES_ID_LISTVIEW_MOREGAMES);
        listview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        listview.setBackgroundColor(-1);
        listview.setCacheColorHint(-1);
        listview.setDividerHeight(0);
        listview.setClickable(true);
        linearlayout.addView(listview);
        addView(linearlayout);
    }
}
