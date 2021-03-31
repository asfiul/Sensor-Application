package com.example.sensorrecord;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG ="MainActivity";
    private SensorManager sensorManager; //
    Sensor accelerometer,gyroscope,Light,Proximity;  //
    TextView xValue,yValue,zValue,x_GValue,y_GValue,z_GValue,light,proximity;




    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(this,foreground.class);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N)
        {
            startForegroundService(intent);
        }else
        {
            startService(intent);
        }
        xValue=findViewById(R.id.xValue);
        yValue=findViewById(R.id.yValue);
        zValue=findViewById(R.id.zValue);

        x_GValue=findViewById(R.id.x_GValue);
        y_GValue=findViewById(R.id.y_GValue);
        z_GValue=findViewById(R.id.z_GValue);



        light=findViewById(R.id.light);
        proximity=findViewById(R.id.proximity);



      //Sensor:--------------

        Log.d(TAG,"onCreate:Initializing Sensor Services ");
        sensorManager= (SensorManager) getSystemService(Context.SENSOR_SERVICE); //

        accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometer!=null) {
            sensorManager.registerListener(MainActivity.this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL); //
            Log.d(TAG,"onCreate:Registered  accelerometer listener");
        }else{
            xValue.setText("Accelerometer Not Supported");
        }

        gyroscope=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if(gyroscope!=null){
            sensorManager.registerListener(MainActivity.this,gyroscope,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG,"onCreate:Registered gyroscope listener");
        }else{
            x_GValue.setText("gyroscope Not Supported");
        }

        Light=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(light!=null){
            sensorManager.registerListener(MainActivity.this,Light,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG,"onCreate:Registered Light listener");
        }else{
            light.setText("gyroscope Not Supported");
        }

        Proximity=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(proximity!=null){
            sensorManager.registerListener(MainActivity.this,Proximity,SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG,"onCreate:Registered Light listener");
        }else {
            light.setText("Proximity Not Supported");
        }

    }




    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged:X:" + event.values[0] + "Y: " + event.values[1] + "Z: " + event.values[2]);
            xValue.setText("xValue: " + event.values[0]);
            yValue.setText("yValue: " + event.values[1]);
            zValue.setText("zValue: " + event.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            x_GValue.setText("xValue: " + event.values[0]);
            y_GValue.setText("yValue: " + event.values[1]);
            z_GValue.setText("zValue: " + event.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            light.setText("xValue: " + event.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
            proximity.setText("xValue: " + event.values[0]);
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

///About:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.manu_layout,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.aboutid) {
            Intent intent=new Intent(getApplicationContext(),About.class);
            startActivity(intent);

        }



        return super.onOptionsItemSelected(item);
    }




}