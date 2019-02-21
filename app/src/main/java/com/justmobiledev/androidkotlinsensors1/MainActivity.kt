package com.justmobiledev.androidkotlinsensors1

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.justmobiledev.androidkotlinsensors1.models.MySensorEvent
import com.justmobiledev.androidkotlinsensors1.models.SensorType
import com.justmobiledev.androidkotlinsensors1.sensormanagers.GyroSensorManager
import com.justmobiledev.androidkotlinsensors1.sensormanagers.LightSensorManager
import com.justmobiledev.androidkotlinsensors1.sensormanagers.TempSensorManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // Set handlers
        LightSensorManager.setHandler(handler)
        TempSensorManager.setHandler(handler)
        GyroSensorManager.setHandler(handler)

        // Register Button Clicks
        buttonStart.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                if (!LightSensorManager.sensorExists()){
                    tvLightSensorValue.text = "No Light Sensor"
                }
                else {
                    LightSensorManager.startSensor()
                }

                if (!TempSensorManager.sensorExists()){
                    tvTempSensorValue.text = "No Temperature Sensor"
                }
                else {
                    TempSensorManager.startSensor()
                }

                if (!GyroSensorManager.sensorExists()){
                    tvGyroSensorValue.text = "No Gyroscope Sensor"
                }
                else {
                    GyroSensorManager.startSensor()
                }
            }})

        buttonStop.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                LightSensorManager.stopSensor()
                tvLightSensorValue.text = ""

                TempSensorManager.stopSensor()
                tvTempSensorValue.text = ""

                GyroSensorManager.stopSensor()
                tvGyroSensorValue.text = ""
            }})
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when(item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    // Handle messages
    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        /*
         * handleMessage() defines the operations to perform when
         * the Handler receives a new Message to process.
         */
        override fun handleMessage(inputMessage: Message) {
            // Gets the image task from the incoming Message object.
            val sensorEvent = inputMessage.obj as MySensorEvent

            // Light Sensor events
            if (sensorEvent.type == SensorType.LIGHT){
                tvLightSensorValue.text = sensorEvent.value
            }
            // Temperature events
            else if (sensorEvent.type == SensorType.TEMPERATURE){
                tvTempSensorValue.text = sensorEvent.value
            }
            // Gyroscope events
            else if (sensorEvent.type == SensorType.GYRO){
                tvGyroSensorValue.text = sensorEvent.value
            }
        }
    }
}
