package com.magmamobile.game.engine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.ViewGroup;
import com.magmamobile.game.engine.menus.MenuDialog;
import com.magmamobile.game.engine.menus.MenuEx;
import com.magmamobile.game.engine.menus.MenuItemEx;

public abstract class GameActivity extends Activity
{

    protected MenuEx _menu;

    public GameActivity()
    {
    }

    public final MenuEx getMenu()
    {
        return _menu;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        Game.onActivityResult(i, j, intent);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        Game.onActivityCreate(this);
    }

    public boolean onCreateOptionsMenu(MenuEx menuex)
    {
        return true;
    }

    protected void onCreateViews(ViewGroup viewgroup)
    {
    }

    protected void onDestroy()
    {
        super.onDestroy();
        Game.onActivityDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(Game.onActivityKeyDown(i, keyevent))
        {
            return true;
        } else
        {
            return super.onKeyDown(i, keyevent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(Game.onActivityKeyUp(i, keyevent))
        {
            return true;
        } else
        {
            return super.onKeyUp(i, keyevent);
        }
    }

    public boolean onOptionsItemSelected(MenuItemEx menuitemex)
    {
        return true;
    }

    protected void onPause()
    {
        super.onPause();
        Game.onActivityPause();
    }

    public boolean onPrepareOptionsMenu(MenuEx menuex)
    {
        return true;
    }

    protected void onResume()
    {
        super.onResume();
        Game.onActivityResume();
    }

    protected void onStart()
    {
        super.onStart();
        Game.onActivityStart();
    }

    protected void onStop()
    {
        super.onStop();
        Game.onActivityStop();
    }

    public void onWindowFocusChanged(boolean flag)
    {
        super.onWindowFocusChanged(flag);
        Game.onWindowFocusChanged(flag);
    }

    public final void raiseOnPause()
    {
        Game.onActivityPause();
    }

    public final void raiseOnResume()
    {
        Game.onActivityResume();
    }

    public final void showOptionsMenu()
    {
        if(_menu == null)
        {
            _menu = new MenuEx();
            onCreateOptionsMenu(_menu);
        }
        onPrepareOptionsMenu(_menu);
        if(_menu.hasVisibleItems())
        {
            (new MenuDialog(this, _menu)).show();
        }
    }
}
