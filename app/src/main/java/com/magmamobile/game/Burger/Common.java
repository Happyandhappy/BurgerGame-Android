package com.magmamobile.game.Burger;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import com.magmamobile.game.Burger.managers.PrefManager;
import com.magmamobile.game.engine.*;

public final class Common
{

    public Common()
    {
    }

    public static void analytics(String s)
    {
        try
        {
            App.trace((new StringBuilder("ANALYTICS: ")).append(s).toString());
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static void share()
    {
        String s = Game.getResString(R.string.res_share_title);
        Game.share(Game.getResString(R.string.res_share_txt).replace("\2441\244", Game.getParameters().GOO_GL_LINK), s, s, Game.getParameters().GOO_GL_LINK);
    }

    public static void showAbout(Context context)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.about, null);
        ((WebView)view.findViewById(R.id.webAbout)).loadDataWithBaseURL(null, context.getString(R.string.about).replace("\2441\244", Game.getAppVersionName()).replace("\2442\244", GamePak.getText(237)).replace("\2443\244", Game.getMarket().getCurrentGameUrl()).replace("\2444\244", Game.getMarket().getPublisherUrl()).replace("\n", "<br/>"), "text/html", "utf-8", "about:blank");
        builder.setView(view);
        builder.setCancelable(true);
        String s = context.getString(R.string.about_title);
        Object aobj[] = new Object[1];
        aobj[0] = context.getString(R.string.app_name);
        builder.setTitle(String.format(s, aobj));
        builder.setPositiveButton(context.getString(R.string.close), new android.content.DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialoginterface, int i)
            {
            }

        });
        builder.show();
    }

    public static void showAsk4Rate(Context context)
    {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.ask4rate, null);
        Button button = (Button)view.findViewById(R.id.btnAskYes);
        Button button1 = (Button)view.findViewById(R.id.btnAskNo);
        final CheckBox check = (CheckBox)view.findViewById(R.id.chkNeverAskAgain);
        check.setChecked(false);
        builder.setView(view);
        builder.setIcon(R.drawable.icon32);
        builder.setCancelable(true);
        builder.setTitle(context.getString(R.string.about_title));
        final AlertDialog ald = builder.create();
        ald.show();
        button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view1)
            {
                ald.dismiss();
                Game.showMarketUpdate();
                PrefManager.disableAsk4Rate();
                Common.analytics("ask4Rate/yes");
            }
        });
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view1)
            {
                ald.dismiss();
                if(check.isChecked())
                {
                    PrefManager.disableAsk4Rate();
                }
                if(PrefManager.noAsk4Rate)
                {
                    Common.analytics("ask4Rate/Later/NeverAgain");
                    return;
                } else
                {
                    Common.analytics("ask4Rate/Later");
                    return;
                }
            }
        });
    }
}
