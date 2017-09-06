package zli.todoplus;

/**
 * Created by yvokeller on 05.09.17.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import zli.todoplus.objects.TodoManager;

public class StepCountService extends Service implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mStepDetectorSensor;
    private Sensor mStepCounterSensor;

    private TodoManager manager;

    @Override
    public void onCreate() {
        super.onCreate();

        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
            mStepDetectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

            mSensorManager.registerListener(this, mStepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        manager = new TodoManager(this);
        //System.out.println("registered service");
    }

   /*public void onCreate() {
        super.onCreate();
        System.out.println("step count service started");
        mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
            mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

            mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }

        manager = new TodoManager(this);
    }*/

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("registered service");
        return Service.START_STICKY;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        //System.out.println("sensor changed");
        System.out.println("step registered!");

        manager.newStepDone();
    }


    /*@Override
    public void onSensorChanged(SensorEvent event) {
        System.out.println("sensor changed");
        Integer steps = Math.round(event.values[0]);
        manager.newStepDone2(steps);
    }*/

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}