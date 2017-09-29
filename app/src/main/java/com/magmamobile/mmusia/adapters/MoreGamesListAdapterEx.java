package com.magmamobile.mmusia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.parser.data.ItemMoreGames;
import com.magmamobile.mmusia.views.ImageViewEx;
import com.magmamobile.mmusia.views.ItemMoreGameView;

public class MoreGamesListAdapterEx extends BaseAdapter
{
    public static class ViewHolder
    {

        ImageViewEx img;
        LinearLayout linearItem;
        TextView txtFree;
        TextView txtTitle;

        public ViewHolder()
        {
        }
    }

    public static class ViewHolderLoading
    {

        TextView txtTitle;

        public ViewHolderLoading()
        {
        }
    }


    private Context mContext;
    private ItemMoreGames myDatas[];

    public MoreGamesListAdapterEx(Context context)
    {
        myDatas = null;
        mContext = null;
        mContext = context;
    }

    public int getCount()
    {
        return myDatas.length;
    }

    public ItemMoreGames getItem(int i)
    {
        return myDatas[i];
    }

    public long getItemId(int i)
    {
        return (long)i;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        ViewHolder viewholder;
        ItemMoreGames itemmoregames;
        if(view == null)
        {
            view = (new ItemMoreGameView(mContext)).getRootView();
            viewholder = new ViewHolder();
            viewholder.linearItem = (LinearLayout)view.findViewById(MMUSIA.RES_ID_ITEM_LINEARITEM);
            viewholder.txtTitle = (TextView)view.findViewById(MMUSIA.RES_ID_ITEM_TITLE);
            viewholder.txtFree = (TextView)view.findViewById(MMUSIA.RES_ID_MOREGAMES_ITEM_FREE);
            viewholder.img = (ImageViewEx)view.findViewById(MMUSIA.RES_ID_ITEM_IMG);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        viewholder.linearItem.setVisibility(View.VISIBLE);
        itemmoregames = myDatas[i];
        viewholder.txtTitle.setText(itemmoregames.title);
        viewholder.img.setImageDrawable(MCommon.getAssetDrawable((Activity)mContext, "mussianews32.png"));
        if(itemmoregames.free == 1)
        {
            viewholder.txtFree.setText("Free");
        } else
        {
            viewholder.txtFree.setText("");
        }
        if(!itemmoregames.urlImg.equals(""))
        {
            viewholder.img.setRemoteURI(itemmoregames.urlImg);
            viewholder.img.loadImage(mContext);
        }
        return view;
    }

    public void setData(ItemMoreGames aitemmoregames[])
    {
        myDatas = aitemmoregames;
    }
}
