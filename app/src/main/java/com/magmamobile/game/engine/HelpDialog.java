package com.magmamobile.game.engine;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import java.util.Locale;

public final class HelpDialog extends WebViewClient
    implements android.view.View.OnClickListener, android.content.DialogInterface.OnDismissListener
{

    private int _close;
    private Context _context;
    private String _languages[];
    private int _title;
    private String _url;
    private android.widget.Button mButton;
    private Dialog mDialog;
    private LinearLayout mLayout;
    private ProgressBar mProgress;
    private WebView mWebview;

    public HelpDialog(Context context, String s, String as[], int i, int j)
    {
        _languages = as;
        _context = context;
        _close = i;
        _title = j;
        _url = s;
    }

    private String getHtmlURL(Context context)
    {
        if(_languages != null) {
            String s;
            int i;
            int j;
            s = context.getResources().getConfiguration().locale.getLanguage();
            i = 0;
            j = _languages.length;
            while (i < j) {
                if (_languages[i].equals(s)) {
                    return _url.replace("{0}", "-".concat(_languages[i]));
                }
                i++;
            }
        }
        return _url.replace("{0}", "");
    }

    public String getUrl()
    {
        return _url;
    }

    public void onClick(View view)
    {
        if(view == mButton)
        {
            mDialog.dismiss();
        }
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
    }

    public void onPageFinished(WebView webview, String s)
    {
        super.onPageFinished(webview, s);
        mLayout.removeAllViews();
        mLayout.addView(mWebview);
        mLayout.addView(mButton);
    }

    public void setUrl(String s)
    {
        _url = s;
    }

    public void show()
    {
        mDialog = new Dialog(_context);
        mDialog.setOnDismissListener(this);
        mDialog.setTitle(_title);
        mLayout = new LinearLayout(_context);
        mLayout.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -1));
        mLayout.setOrientation(LinearLayout.VERTICAL);
        mLayout.setGravity(1);
        mProgress = new ProgressBar(_context);
        mProgress.setIndeterminate(true);
        mProgress.setPadding(64, 64, 64, 64);
        mLayout.addView(mProgress);
        mButton = new android.widget.Button(_context);
        android.widget.LinearLayout.LayoutParams layoutparams = new android.widget.LinearLayout.LayoutParams(-1, -2);
        layoutparams.topMargin = -(int)Game.dpi(50F);
        mButton.setLayoutParams(layoutparams);
        mButton.setText(_close);
        mButton.setOnClickListener(this);
        mWebview = new WebView(_context);
        mWebview.setHorizontalScrollBarEnabled(true);
        android.widget.LinearLayout.LayoutParams layoutparams1 = new android.widget.LinearLayout.LayoutParams(-1, -1);
        layoutparams1.bottomMargin = (int)Game.dpi(50F);
        mWebview.setLayoutParams(layoutparams1);
        mWebview.loadUrl(getHtmlURL(_context));
        mWebview.setWebViewClient(this);
        mDialog.setContentView(mLayout);
        mDialog.show();
        mDialog.getWindow().setLayout(-1, -1);
    }
}
