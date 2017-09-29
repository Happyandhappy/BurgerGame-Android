package com.magmamobile.game.engine;

import android.content.*;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;

public final class ChangeLogDialog
    implements android.content.DialogInterface.OnClickListener
{

    private String mClose;
    private Context mContext;
    private int mLayout;
    private String mTitle;
    private String mVersion;

    public ChangeLogDialog(Context context, int i, int j, int k)
    {
        mVersion = Utils.getAppVersion(context);
        mContext = context;
        mLayout = i;
        mClose = context.getString(j);
        mTitle = context.getString(k);
    }

    public ChangeLogDialog(Context context, int i, String s, String s1)
    {
        mVersion = Utils.getAppVersion(context);
        mContext = context;
        mLayout = i;
        mClose = s;
        mTitle = s1;
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        android.content.SharedPreferences.Editor editor;
        switch(i)
        {
        default:
            return;

        case -1: 
            editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
            break;
        }
        editor.putString("lastversion", mVersion);
        editor.commit();
    }

    public void show()
    {
        show(false);
    }

    public void show(boolean flag)
    {
        for(String s = PreferenceManager.getDefaultSharedPreferences(mContext).getString("lastversion", ""); !flag && (mVersion.equals("1.0.0") || mVersion.equals(s));)
        {
            return;
        }

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(mContext);
        builder.setView(LayoutInflater.from(mContext).inflate(mLayout, null));
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton(mClose, this);
        builder.setCancelable(true);
        builder.setTitle(mTitle);
        builder.show();
    }
}
