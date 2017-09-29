package com.magmamobile.mmusia.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.magmamobile.mmusia.MCommon;
import com.magmamobile.mmusia.MMUSIA;
import com.magmamobile.mmusia.parser.data.ItemNews;
import com.magmamobile.mmusia.views.ImageViewEx;
import com.magmamobile.mmusia.views.ItemView;
import java.text.SimpleDateFormat;

public class NewsListAdapterEx extends BaseAdapter
{
    public static class ViewHolder
    {

        ImageViewEx img;
        LinearLayout linearItem;
        TextView txtDate;
        TextView txtDesc;
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


    private SimpleDateFormat formaterDate;
    private Context mContext;
    private ItemNews myDatas[];

    public NewsListAdapterEx(Context context)
    {
        myDatas = null;
        mContext = null;
        formaterDate = new SimpleDateFormat("yyyy-MM-dd");
        mContext = context;
    }

    public int getCount()
    {
        return myDatas.length;
    }

    public ItemNews getItem(int i)
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
        ItemNews itemnews;
        if(view == null)
        {
            view = (new ItemView(mContext)).getRootView();
            viewholder = new ViewHolder();
            viewholder.linearItem = (LinearLayout)view.findViewById(MMUSIA.RES_ID_ITEM_LINEARITEM);
            viewholder.txtTitle = (TextView)view.findViewById(MMUSIA.RES_ID_ITEM_TITLE);
            viewholder.txtDate = (TextView)view.findViewById(MMUSIA.RES_ID_ITEM_DATE);
            viewholder.txtDesc = (TextView)view.findViewById(MMUSIA.RES_ID_ITEM_DESC);
            viewholder.img = (ImageViewEx)view.findViewById(MMUSIA.RES_ID_ITEM_IMG);
            view.setTag(viewholder);
        } else
        {
            viewholder = (ViewHolder)view.getTag();
        }
        viewholder.linearItem.setVisibility(View.VISIBLE);
        itemnews = myDatas[i];
        viewholder.txtTitle.setText(itemnews.NewsTitle);
        viewholder.txtDesc.setText(itemnews.NewsDesc);
        viewholder.txtDate.setText(formaterDate.format(itemnews.NewsDate));
        viewholder.img.setImageDrawable(MCommon.getAssetDrawable((Activity)mContext, "mussianews32.png"));
        if(!itemnews.imgUrl.equals(""))
        {
            viewholder.img.setRemoteURI(itemnews.imgUrl);
            viewholder.img.loadImage(mContext);
        }
        return view;
    }

    public void setData(ItemNews aitemnews[])
    {
        myDatas = aitemnews;
    }
}
