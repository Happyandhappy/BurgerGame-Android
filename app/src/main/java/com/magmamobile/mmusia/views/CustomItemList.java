package com.magmamobile.mmusia.views;

import android.graphics.Bitmap;

public class CustomItemList
{

    public Bitmap bm;
    public String tag;
    public String text;

    public CustomItemList(String s, Bitmap bitmap, String s1)
    {
        text = "";
        bm = null;
        tag = "";
        text = s;
        bm = bitmap;
        tag = s1;
    }
}
