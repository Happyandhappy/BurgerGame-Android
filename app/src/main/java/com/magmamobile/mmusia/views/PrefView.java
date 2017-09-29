package com.magmamobile.mmusia.views;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.data.LanguageBase;

public class PrefView extends LinearLayout
{

    public PrefView(Context context)
    {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
        buildView(context);
    }

    public void buildView(Context context)
    {
        CheckBox checkbox = new CheckBox(context);
        checkbox.setText(LanguageBase.DIALOG_SETTINGS_CHKTEXT);
        checkbox.setId(MMUSIA.RES_ID_PREF_CHECK_ENABLE);
        addView(checkbox);
    }
}
