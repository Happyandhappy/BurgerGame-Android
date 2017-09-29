package up;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.view.View;

public class FeatureWrapper
{
    public static class Pair
    {

        public String key;
        public String value;

        public Pair(String s, String s1)
        {
            key = s;
            value = s1;
        }
    }


    public FeatureWrapper()
    {
    }

    public boolean hasSystemFeature(Context context, String s)
    {
        return false;
    }

    public void sendNotification(Context context, String s, String s1, String s2, boolean flag, Class class1, int i, int j, int k, Pair apair[])
    {
        Intent intent;
        int l;
        NotificationManager notificationmanager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationmanager == null)
        {
            return;
        }
        Notification notification = new Notification();

        intent = new Intent(context, class1);
        intent.addFlags(0x10000000);
        if(apair != null) {
            l = 0;
            while (l < apair.length) {
                Pair pair = apair[l];
                intent.putExtra(pair.key, pair.value);
                l++;
            }
        }

        Notification.Builder builder = new Notification.Builder(context);

        builder.setAutoCancel(false);
        //builder.setTicker("this is ticker text");
        builder.setContentTitle(s);
        builder.setContentText(s1);
        builder.setSmallIcon(i);
        builder.setContentIntent(PendingIntent.getActivity(context, 0, intent, k));
        //builder.setOngoing(true);
        //builder.setSubText("This is subtext...");   //API level 16
        //builder.setNumber(100);
        builder.build();

        notification = builder.getNotification();

        if(flag)
        {
            notification.defaults = 3;
        } else
        {
            notification.defaults = 1;
        }
        notification.flags = 16;
        notification.icon = i;

        // notification.setLatestEventInfo(context, s, s1, PendingIntent.getActivity(context, 0, intent, k));
        notificationmanager.notify(j, notification);
    }

    public void setLayerTypeSoftware(View view)
    {
    }

    public void setSystemUiVisibility(View view)
    {
    }
}
