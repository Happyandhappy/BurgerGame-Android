package com.magmamobile.mmusia.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.adapters.MoreGamesListAdapterEx;
import com.magmamobile.mmusia.data.LanguageBase;
import com.magmamobile.mmusia.parser.data.ApiBase;
import com.magmamobile.mmusia.parser.data.ItemMoreGames;
import com.magmamobile.mmusia.views.MoreGamesView;

public class MMUSIAMoreGamesActivity extends Activity
{

    private final int MENU_MMUSIA_QUIT = 3;
    private final int MENU_MMUSIA_REFRESH = 2;
    private final int MSG_CHANGE_MESSAGE = 2;
    private final int MSG_CLOSE = 1;
    private final int MSG_LOADJSONFINISH = 5;
    private final int MSG_OPEN = 0;
    private final int MSG_TOAST = 4;
    private ProgressDialog mDialog;
    private ListView mListMoreGamesList;
    Handler messageHandler;

    public MMUSIAMoreGamesActivity()
    {
        messageHandler = new Handler() {
            public void handleMessage(Message message)
            {
                switch(message.what)
                {
                default:
                    return;

                case 0: // '\0'
                    mDialog = ProgressDialog.show(MMUSIAMoreGamesActivity.this, LanguageBase.DIALOG_LOADING, LanguageBase.DIALOG_PLEASEWAIT, true, true);
                    return;

                case 1: // '\001'
                    try
                    {
                        mDialog.dismiss();
                        return;
                    }
                    catch(Exception exception1)
                    {
                        exception1.printStackTrace();
                    }
                    return;

                case 2: // '\002'
                    try
                    {
                        mDialog.setMessage((String)message.obj);
                        return;
                    }
                    catch(Exception exception)
                    {
                        exception.printStackTrace();
                    }
                    return;

                case 4: // '\004'
                    Toast.makeText(MMUSIAMoreGamesActivity.this, (String)message.obj, Toast.LENGTH_LONG).show();
                    return;

                case 5: // '\005'
                    displayMoreGames();
                    return;

                case 999999: 
                    finish();
                    return;
                }
            }
        };
    }

    public void displayMoreGames()
    {
        try
        {
            if(MMUSIA.api.moregames == null)
            {
                MMUSIA.api.moregames = new ItemMoreGames[0];
            }
            MoreGamesListAdapterEx moregameslistadapterex = new MoreGamesListAdapterEx(this);
            moregameslistadapterex.setData(MMUSIA.api.moregames);
            mListMoreGamesList.setAdapter(moregameslistadapterex);
            return;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void loadNews(final Context context)
    {
        (new Thread() {
            public void run()
            {
                loadNewsThread(context);
            }
        }).start();
    }

    public void loadNewsThread(Context context)
    {
        messageHandler.sendMessage(Message.obtain(messageHandler, 0));
        MMUSIA.getLatestNews(context, false, true);
        messageHandler.sendMessage(Message.obtain(messageHandler, 1));
        messageHandler.sendMessage(Message.obtain(messageHandler, 5));
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        LanguageBase.reloadIfNeeded();
        try
        {
            if(MMUSIA.appIconID == 0)
            {
                MMUSIA.appIconID = getResources().getIdentifier(MCommon.iconFileName, "drawable", getPackageName());
            }
        }
        catch(Exception exception)
        {
            MCommon.Log_e("TRIED TO RELOAD ICON APP ID WITHOUT SUCCESS !!!");
            exception.printStackTrace();
        }
        setContentView(new MoreGamesView(this));
        mListMoreGamesList = (ListView)findViewById(MMUSIA.RES_ID_LISTVIEW_MOREGAMES);
        try
        {
            MCommon.Log_e((new StringBuilder("MMUSIA.RES_DRAWABLE_ICONAPP : ")).append(MMUSIA.appIconID).toString());
            if(MMUSIA.appIconID != 0)
            {
                ((ImageView)findViewById(MMUSIA.RES_ID_IMG_MOREGAMES_HEAD)).setImageDrawable(MCommon.getAssetDrawableResize(this, getResources().getDrawable(MMUSIA.appIconID), MCommon.dpiImage(48), MCommon.dpiImage(48)));
            }
        }
        catch(android.content.res.Resources.NotFoundException notfoundexception)
        {
            MCommon.Log_e("OULALA ICON INTROUVABLE !!!");
            MCommon.Log_e((new StringBuilder("MMUSIA.RES_DRAWABLE_ICONAPP : ")).append(MMUSIA.appIconID).toString());
            MCommon.Log_e((new StringBuilder("LanguageBase.TAB_UPDATES : ")).append(LanguageBase.TAB_UPDATES).toString());
            MCommon.Log_e((new StringBuilder("MMUSIA.RES_ID_TAB_UPDATE : ")).append(MMUSIA.RES_ID_TAB_UPDATE).toString());
            notfoundexception.printStackTrace();
        }
        ((TextView)findViewById(MMUSIA.RES_ID_TITLE_MOREGAMES_HEAD)).setText(LanguageBase.DIALOG_MOREGAMES_TITLE);
        if(MMUSIA.api != null)
        {
            if(MMUSIA.api.moregames.length == 0)
            {
                loadNews(this);
            } else
            {
                displayMoreGames();
            }
        } else
        {
            loadNews(this);
        }
        mListMoreGamesList.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                ItemMoreGames itemmoregames = ((MoreGamesListAdapterEx)mListMoreGamesList.getAdapter()).getItem(i);
                if(!itemmoregames.urlMarket.equals(""))
                {
                    if(itemmoregames.urlMarket.startsWith("http://") || itemmoregames.urlMarket.startsWith("https://"))
                    {
                        MCommon.openUrlPage(MMUSIAMoreGamesActivity.this, itemmoregames.urlMarket);
                    } else
                    {
                        MCommon.openMarketLink(MMUSIAMoreGamesActivity.this, itemmoregames.urlMarket);
                    }
                }
                MMUSIA.lClickMoreApp(MMUSIAMoreGamesActivity.this, itemmoregames.pname);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        menu.add(0, 2, 0, LanguageBase.MENU_REFRESH).setIcon(MCommon.getAssetDrawable(this, "mmusiarefresh.png"));
        menu.add(0, 3, 0, LanguageBase.MENU_QUIT).setIcon(MCommon.getAssetDrawable(this, "mmusiaexit.png"));
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        switch (menuitem.getItemId()) {
            default:
                break;
            case 2:
                loadNews(this);
                break;
            case 3:
                finish();
                break;
        }
        return true;
    }
}
