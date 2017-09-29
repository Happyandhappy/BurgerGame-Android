package com.magmamobile.game.engine;

import android.hardware.*;

final class SensorCallback
    implements SensorEventListener
{

    SensorCallback()
    {
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
        Game.onSensorAccuracyChanged(sensor, i);
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        Game.onSensorChanged(sensorevent);
    }
}
