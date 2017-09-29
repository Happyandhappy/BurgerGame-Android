package com.magmamobile.mmusia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.*;
import com.magmamobile.mmusia.MMUSIA;

public class MainView extends LinearLayout
{

    private Context mContext;

    public MainView(Context context)
    {
        super(context);
        setBackgroundColor(0xff000000);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public MainView(Context context, AttributeSet attributeset)
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
        TabHost tabhost = new TabHost(context, null);
        tabhost.setId(MMUSIA.RES_ID_LISTVIEW_MAINTAB);
        tabhost.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearlayout = new LinearLayout(context);
        linearlayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        linearlayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout framelayout = new FrameLayout(context);
        framelayout.setLayoutParams(new android.widget.FrameLayout.LayoutParams(-1, -1));
        framelayout.setBackgroundColor(0xffffff);
        framelayout.setId(android.R.id.tabcontent);
        LinearLayout linearlayout1 = new LinearLayout(context);
        linearlayout1.setId(MMUSIA.RES_ID_TAB_UPDATE);
        linearlayout1.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        linearlayout1.setOrientation(LinearLayout.VERTICAL);
        ListView listview = new ListView(context);
        listview.setId(MMUSIA.RES_ID_LISTVIEW_APPUPDATE);
        listview.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        listview.setBackgroundColor(-1);
        listview.setCacheColorHint(-1);
        listview.setDividerHeight(0);
        listview.setClickable(true);
        LinearLayout linearlayout2 = new LinearLayout(context);
        linearlayout2.setId(MMUSIA.RES_ID_TAB_NEWS);
        linearlayout2.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        linearlayout2.setOrientation(LinearLayout.VERTICAL);
        ListView listview1 = new ListView(context);
        listview1.setId(MMUSIA.RES_ID_LISTVIEW_NEWSLIST);
        listview1.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -1));
        listview1.setBackgroundColor(-1);
        listview1.setCacheColorHint(-1);
        listview1.setDividerHeight(0);
        listview1.setClickable(true);
        TabWidget tabwidget = new TabWidget(context);
        tabwidget.setId(android.R.id.tabs);
        tabwidget.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        tabwidget.setBackgroundColor(0);
        tabwidget.setBaselineAligned(false);
        tabwidget.setClipToPadding(true);
        tabwidget.setDrawingCacheQuality(ViewAnimator.DRAWING_CACHE_QUALITY_HIGH);
        linearlayout2.addView(listview1);
        framelayout.addView(linearlayout2);
        linearlayout.addView(tabwidget);
        linearlayout.addView(framelayout);
        tabhost.addView(linearlayout);
        addView(tabhost);
    }
}
