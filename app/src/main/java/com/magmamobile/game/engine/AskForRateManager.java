package com.magmamobile.game.engine;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

public final class AskForRateManager
{

    private static final int COUNTER = 10;
    private static final String RES_ABOUT = "res_about";
    private static final String RES_APPNAME = "app_name";
    private static final String RES_ASKAGAIN = "chkNeverAskAgain";
    private static final String RES_ASKNO = "btnAskNo";
    private static final String RES_ASKRATING = "txtAskRating";
    private static final String RES_ASKYES = "btnAskYes";
    private static final String RES_ICON = "icon";
    private static final String RES_LAYOUT = "ask4rate";
    private static final String RES_RATETEXT = "ask4rate_text";
    private static boolean prefAsk4RateNeverAskAgain = false;
    private static int prefAskedMarketCount = 0;
    private static int prefGameCount = 0;

    public AskForRateManager()
    {
    }

    private static void savePref()
    {
        Game.setPrefInt("prefGameCount", prefGameCount);
        Game.setPrefInt("prefAskedMarketCount", prefAskedMarketCount);
        Game.setPrefBoolean("prefAsk4RateNeverAskAgain", prefAsk4RateNeverAskAgain);
    }

    public static void show()
    {
        Game.postRunnable(new Runnable() {

            public void run()
            {
                AskForRateManager.showAsk4Rate();
            }

        });
    }

    private static void showAsk4Rate()
    {
        GameActivity gameactivity = Game.getContext();
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(gameactivity);
        View view = LayoutInflater.from(gameactivity).inflate(Game.getRLayout("ask4rate"), null);
        TextView textview = (TextView)view.findViewById(Game.getRId("txtAskRating"));
        android.widget.Button button = (android.widget.Button)view.findViewById(Game.getRId("btnAskYes"));
        android.widget.Button button1 = (android.widget.Button)view.findViewById(Game.getRId("btnAskNo"));
        final CheckBox check = (CheckBox)view.findViewById(Game.getRId("chkNeverAskAgain"));
        textview.setText(Game.getResString(Game.getRString("ask4rate_text")).replace("\2441\244", Game.getResString(Game.getRString("app_name"))));
        check.setChecked(false);
        builder.setView(view);
        builder.setIcon(Game.getRDrawable("icon"));
        builder.setCancelable(true);
        builder.setTitle(gameactivity.getString(Game.getRString("res_about")));
        final AlertDialog ald = builder.show();
        button.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view1)
            {
                ald.dismiss();
                Game.showMarketUpdate();
                AskForRateManager.prefAsk4RateNeverAskAgain = true;
                AskForRateManager.savePref();
            }
        });
        button1.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View view1)
            {
                ald.dismiss();
                AskForRateManager.prefAsk4RateNeverAskAgain = check.isChecked();
                AskForRateManager.savePref();
            }
        });
    }

    public static void start()
    {
        prefGameCount = Game.getPrefInt("prefGameCount", 0);
        prefAskedMarketCount = Game.getPrefInt("prefAskedMarketCount", 0);
        prefAsk4RateNeverAskAgain = Game.getPrefBoolean("prefAsk4RateNeverAskAgain", false);
        if(prefGameCount / 10 != prefAskedMarketCount && !prefAsk4RateNeverAskAgain && prefGameCount / 10 > 0)
        {
            prefAskedMarketCount = prefGameCount / 10;
            showAsk4Rate();
            savePref();
        }
        prefGameCount = 1 + prefGameCount;
        savePref();
    }





}
