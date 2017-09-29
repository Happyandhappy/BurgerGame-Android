package com.magmamobile.game.engine;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.SparseArray;

public final class SensorsManager
{

    protected static boolean enabled;
    protected static SensorCallback sensorListener;
    protected static SensorManager sensorMgr;
    protected static SparseArray sensors;

    public SensorsManager()
    {
    }

    public static void disable(int i)
    {
        SensorInfo sensorinfo = (SensorInfo)sensors.get(i);
        if(sensorinfo != null && enabled)
        {
            sensors.remove(i);
            if(sensorinfo.activated)
            {
                sensorinfo.activated = false;
                sensorMgr.unregisterListener(sensorListener, sensorinfo.sensor);
            }
        }
    }

    public static void enable(int i)
    {
        SensorInfo sensorinfo = (SensorInfo)sensors.get(i);
        if(sensorinfo == null)
        {
            sensorinfo = new SensorInfo();
            sensorinfo.sensor = sensorMgr.getDefaultSensor(i);
            sensorinfo.activated = false;
            sensors.append(i, sensorinfo);
        }
        if(enabled && !sensorinfo.activated)
        {
            sensorinfo.activated = true;
            sensorMgr.registerListener(sensorListener, sensorinfo.sensor, 1);
        }
    }

    protected static void onInitialize()
    {
        if(Game.parameters.USE_SENSORS)
        {
            sensorListener = new SensorCallback();
            sensors = new SparseArray();
            sensorMgr = (SensorManager)Game.activity.getSystemService(Context.SENSOR_SERVICE);
        }
    }

    protected static void onPause()
    {
        if(sensorMgr == null)
            return;
        int i;
        int j;
        i = sensors.size();
        j = 0;
        while(j < i) {
            SensorInfo sensorinfo = (SensorInfo)sensors.valueAt(j);
            if(sensorinfo.activated)
            {
                sensorMgr.unregisterListener(sensorListener, sensorinfo.sensor);
                sensorinfo.activated = false;
            }
            j++;
        }
        enabled = false;
    }

    protected static void onResume()
    {
        if(sensorMgr == null)
            return;
        int i;
        int j;
        i = sensors.size();
        j = 0;
        while(j < i) {
            SensorInfo sensorinfo = (SensorInfo)sensors.valueAt(j);
            if(!sensorinfo.activated)
            {
                sensorMgr.registerListener(sensorListener, sensorinfo.sensor, 1);
                sensorinfo.activated = true;
            }
            j++;
        }
        enabled = true;
    }

    protected static void onTerminate()
    {
    }
}
