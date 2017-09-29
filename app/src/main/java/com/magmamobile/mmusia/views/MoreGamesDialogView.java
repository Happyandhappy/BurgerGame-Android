package com.magmamobile.mmusia.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.magmamobile.mmusia.MMUSIA;

public class MoreGamesDialogView extends LinearLayout
{

    private Context mContext;

    public MoreGamesDialogView(Context context)
    {
        super(context);
        setBackgroundColor(0xff000000);
        setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public MoreGamesDialogView(Context context, AttributeSet attributeset)
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
