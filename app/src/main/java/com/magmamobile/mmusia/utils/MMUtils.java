package com.magmamobile.mmusia.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.*;
import com.magmamobile.mmusia.parser.data.ItemMoreGames;
import java.util.ArrayList;
import java.util.List;

public class MMUtils
{

    private static List list;

    public MMUtils()
    {
    }

    private static String getCname(Context context, String s)
    {
        int i;
        if(list == null)
        {
            Intent intent = new Intent("android.intent.action.MAIN", null);
            intent.addCategory("android.intent.category.LAUNCHER");
            list = context.getPackageManager().queryIntentActivities(intent, 0);
        }
        i = 0;
        while(i < list.size())
        {
            if(!isSystemPackage((ResolveInfo)list.get(i)))
            {
                if(((ResolveInfo)list.get(i)).activityInfo.packageName.equals(s))
                    return ((ResolveInfo)list.get(i)).activityInfo.name;

            }else
                list.remove(i);
            i++;
        }
        return "";
    }

    public static ItemMoreGames[] getMoreGamesForExit(Context context, ItemMoreGames aitemmoregames[])
    {
        int i;
        ArrayList arraylist;
        int j;
        ItemMoreGames aitemmoregames1[];
        int k;
        i = 0;
        arraylist = new ArrayList();
        j = 0;
        while(j < aitemmoregames.length) {
            if(isPackageNameInDevice(context, aitemmoregames[j].pname))
            {
                aitemmoregames[j].isLocal = true;
                aitemmoregames[j].cname = getCname(context, aitemmoregames[j].pname);
                arraylist.add(aitemmoregames[j]);
            } else
            {
                aitemmoregames[j].isLocal = false;
                arraylist.add(i, aitemmoregames[j]);
                i++;
            }
            j++;
        }
        aitemmoregames1 = new ItemMoreGames[arraylist.size()];
        k = 0;
        while(k < arraylist.size())
        {
            aitemmoregames1[k] = (ItemMoreGames)arraylist.get(k);
            k++;
        }
        return aitemmoregames1;
    }

    public static boolean isPackageNameInDevice(Context context, String s)
    {
        int i;
        if(list == null)
        {
            Intent intent = new Intent("android.intent.action.MAIN", null);
            intent.addCategory("android.intent.category.LAUNCHER");
            list = context.getPackageManager().queryIntentActivities(intent, 0);
        }
        i = 0;
        while(i < list.size())
        {
            if(!isSystemPackage((ResolveInfo)list.get(i)))
            {
                if(((ResolveInfo)list.get(i)).activityInfo.packageName.equals(s))
                    return true;
            }else
                list.remove(i);
            i++;
        }
        return false;
    }

    private static boolean isSystemPackage(ResolveInfo resolveinfo)
    {
        return (1 & resolveinfo.activityInfo.applicationInfo.flags) != 0;
    }
}
