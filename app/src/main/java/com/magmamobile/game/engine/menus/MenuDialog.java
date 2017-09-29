package com.magmamobile.game.engine.menus;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.magmamobile.game.engine.GameActivity;

public final class MenuDialog extends Dialog
    implements android.widget.AdapterView.OnItemClickListener
{

    protected MenuAdapter _adapter;
    protected GameActivity _context;
    protected ListView _listView;
    protected MenuEx _menu;

    public MenuDialog(GameActivity gameactivity, MenuEx menuex)
    {
        super(gameactivity);
        _context = gameactivity;
        _menu = menuex;
    }

    public void dismiss()
    {
        super.dismiss();
        _context.raiseOnResume();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        _adapter = new MenuAdapter(_menu, _context);
        _listView = new ListView(_context);
        _listView.setOnItemClickListener(this);
        _listView.setAdapter(_adapter);
        requestWindowFeature(1);
        setContentView(_listView);
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        _context.onOptionsItemSelected(_adapter.getItem(i));
        dismiss();
    }

    public void show()
    {
        super.show();
        _context.raiseOnPause();
    }
}
