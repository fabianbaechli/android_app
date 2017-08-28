package zli.previewapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;

import static android.hardware.Sensor.TYPE_LIGHT;

public class MainActivity extends AppCompatActivity {
    private String lightVal = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button interactWithSensorButton = (Button) findViewById(R.id.communicateWithSensorButton);
        interactWithSensorButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                System.out.println("button event");
                SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                System.out.println("light:" + lightSensor.getResolution());
            }
        });
    }

    public void onSensorChanged(SensorEvent event) {
        System.out.println("sensor event");
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            System.out.println("Sensor change: " + Arrays.toString(event.values));
        }
    }
}
