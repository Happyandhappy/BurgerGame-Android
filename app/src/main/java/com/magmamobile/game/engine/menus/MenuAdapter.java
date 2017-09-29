package com.magmamobile.game.engine.menus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.magmamobile.game.engine.Game;

final class MenuAdapter extends BaseAdapter
{
    private class ViewHolder
    {

        public ImageView icon;
        public LinearLayout layout;
        public View nodata;
        public TextView title;

        private ViewHolder()
        {
            super();
        }

        ViewHolder(ViewHolder viewholder)
        {
            this();
        }
    }


    protected Context _context;
    protected ViewHolder _holder;
    protected MenuEx _menu;

    protected MenuAdapter(MenuEx menuex, Context context)
    {
        _context = context;
        _menu = menuex;
    }

    private ViewHolder createView()
    {
        ViewHolder viewholder = new ViewHolder(null);
        viewholder.nodata = new View(_context);
        android.widget.AbsListView.LayoutParams layoutparams = new android.widget.AbsListView.LayoutParams(0, 0);
        viewholder.nodata.setLayoutParams(layoutparams);
        viewholder.nodata.setTag(viewholder);
        viewholder.layout = new LinearLayout(_context);
        viewholder.layout.setOrientation(LinearLayout.HORIZONTAL);
        android.widget.AbsListView.LayoutParams layoutparams1 = new android.widget.AbsListView.LayoutParams(-1, -2);
        viewholder.layout.setLayoutParams(layoutparams1);
        viewholder.layout.setTag(viewholder);
        viewholder.icon = new ImageView(_context);
        viewholder.icon.setScaleType(android.widget.ImageView.ScaleType.FIT_CENTER);
        android.widget.LinearLayout.LayoutParams layoutparams2 = new android.widget.LinearLayout.LayoutParams((int)Game.dpi(36F), (int)Game.dpi(36F));
        layoutparams2.setMargins((int)Game.dpi(2.0F), (int)Game.dpi(4F), (int)Game.dpi(2.0F), (int)Game.dpi(4F));
        viewholder.layout.addView(viewholder.icon, layoutparams2);
        viewholder.title = new TextView(_context);
        viewholder.title.setGravity(16);
        viewholder.title.setTextSize(1, 16F);
        android.widget.LinearLayout.LayoutParams layoutparams3 = new android.widget.LinearLayout.LayoutParams(-1, -1);
        layoutparams3.setMargins((int)Game.dpi(10F), (int)Game.dpi(4F), (int)Game.dpi(10F), (int)Game.dpi(4F));
        viewholder.layout.addView(viewholder.title, layoutparams3);
        return viewholder;
    }

    public int getCount()
    {
        return _menu.size();
    }

    public MenuItemEx getItem(int i)
    {
        return _menu.getItem(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public int getItemViewType(int i)
    {
        return !_menu.getItem(i).isVisible() ? 1 : 0;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        MenuItemEx menuitemex;
        if(view == null)
        {
            viewholder = createView();
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        menuitemex = _menu.getItem(i);
        if(menuitemex != null)
        {
            android.view.ViewGroup.LayoutParams layoutparams;
            int j;
            if(menuitemex.isVisible())
            {
                viewholder.title.setText(menuitemex._title);
                viewholder.icon.setImageDrawable(menuitemex._icon);
                view = viewholder.layout;
            } else
            {
                view = viewholder.nodata;
            }
            view.getLayoutParams().width = viewgroup.getWidth();
            layoutparams = view.getLayoutParams();
            if(menuitemex.isVisible())
            {
                j = -2;
            } else
            {
                j = 0;
            }
            layoutparams.height = j;
        }
        return view;
    }

    public int getViewTypeCount()
    {
        return 2;
    }
}
