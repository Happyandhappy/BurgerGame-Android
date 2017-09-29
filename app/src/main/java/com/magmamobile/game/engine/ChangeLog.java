package com.magmamobile.game.engine;

import android.app.AlertDialog;
import android.content.*;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.*;
import android.widget.TextView;
import java.lang.reflect.Field;

public final class ChangeLog
    implements android.content.DialogInterface.OnClickListener
{

    protected Context context;
    protected int resClose;
    protected int resLayout;
    protected int resLog;
    protected int resTitle;
    protected String version;

    public ChangeLog(Context context1, int i)
    {
        version = Utils.getAppVersion(context1);
        context = context1;
        resLog = i;
    }

    private String loadVersionPref()
    {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("lastversion", "");
    }

    private void saveVersionPref()
    {
        android.content.SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("lastversion", version);
        editor.commit();
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        if(i == -1)
        {
            saveVersionPref();
        }
    }

    public void show()
    {
        show(false);
    }

    public void show(boolean flag)
    {
        String s = loadVersionPref();
        if(!flag && (version.equals("1.0.0") || version.equals(s) || Game.isFirstUse()))
        {
            saveVersionPref();
            return;
        }
        android.app.AlertDialog.Builder builder;
        View view;
        TextView textview;
        try
        {
            Class class1 = Class.forName(context.getPackageName().concat(".R$layout"));
            Class class2 = Class.forName(context.getPackageName().concat(".R$string"));
            resLayout = ((Integer)class1.getField("changelog").get(class1)).intValue();
            resClose = ((Integer)class2.getField("res_close").get(class2)).intValue();
            resTitle = ((Integer)class2.getField("res_changelog").get(class2)).intValue();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        builder = new android.app.AlertDialog.Builder(context);
        view = LayoutInflater.from(context).inflate(resLayout, null);
        textview = (TextView)view.findViewById(android.R.id.text1);
        textview.setText(Html.fromHtml(GamePak.getText(resLog).replace("\n", "<br/>")));
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton(resClose, this);
        builder.setTitle(resTitle);
        builder.setCancelable(true);
        builder.setView(view);
        builder.show().getWindow().setLayout(-1, -2);
    }
}
