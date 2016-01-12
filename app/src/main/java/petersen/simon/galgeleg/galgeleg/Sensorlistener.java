package petersen.simon.galgeleg.galgeleg;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Vibrator;
import android.widget.Toast;

import petersen.simon.galgeleg.fragments.SpilFragment;

/**
 * Created by Blumen on 07-01-2016.
 */
public class Sensorlistener implements SensorEventListener {
    public static boolean sensorCheck = false;

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();

        if(sensorType == Sensor.TYPE_ACCELEROMETER){
            double t = 9.80665; //Normal tyngdeacceleration
            double sum = Math.abs(event.values[0]) + Math.abs(event.values[1]) + Math.abs(event.values[2]);

            if(sum > 3*t){
                //indsæt restart og pisk lyd
                //SpilFragment.genstartVedRyst();
                sensorCheck = true;

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
