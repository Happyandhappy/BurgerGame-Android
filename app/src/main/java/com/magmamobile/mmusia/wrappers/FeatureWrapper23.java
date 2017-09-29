package com.magmamobile.mmusia.wrappers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import up.FeatureWrapper;

public class FeatureWrapper23 extends FeatureWrapper
{

    public FeatureWrapper23()
    {
    }

    public void sendNotification(Context context, String s, String s1, String s2, boolean flag, Class class1, int i, 
            int j, int k, up.FeatureWrapper.Pair apair[])
    {
        NotificationManager notificationmanager;
        Intent intent;
        notificationmanager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationmanager == null)
        {
            return;
        }
        intent = new Intent(context, class1);
        intent.addFlags(0x10000000);
        if(apair != null) {
            int l = 0;
            while (l < apair.length) {
                up.FeatureWrapper.Pair pair = apair[l];
                intent.putExtra(pair.key, pair.value);
                l++;
            }
        }
        PendingIntent pendingintent = PendingIntent.getActivity(context, 0, intent, k);
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(context);
        builder.setSmallIcon(i);
        builder.setContentTitle(s);
        builder.setContentText(s1);
        builder.setContentIntent(pendingintent);
        notificationmanager.notify(j, builder.build());
    }
}
