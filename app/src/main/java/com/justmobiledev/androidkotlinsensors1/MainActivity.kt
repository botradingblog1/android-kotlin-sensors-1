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
import android.widget.Toast
import com.justmobiledev.androidkotlinsensors1.models.MySensorEvent
import com.justmobiledev.androidkotlinsensors1.sensormanagers.LightSensorManager

import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (!LightSensorManager.sensorExists()){
            Toast.makeText(this, "Light sensor does not exists", Toast.LENGTH_LONG)
        }
        else {
            LightSensorManager.startSensor()
        }

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



    object SensorEventHandler {
        // Handle messages
        val handler: Handler = object : Handler(Looper.getMainLooper()) {
            /*
             * handleMessage() defines the operations to perform when
             * the Handler receives a new Message to process.
             */
            override fun handleMessage(inputMessage: Message) {
                // Gets the image task from the incoming Message object.
                val sensorEvent = inputMessage.obj as MySensorEvent
                Log.d("cxs", "event: "+sensorEvent.type)

            }
        }

        fun sendMessage(sensorEvent: MySensorEvent) {
            handler.obtainMessage(sensorEvent.type.ordinal, sensorEvent)?.apply {
                sendToTarget()
            }
        }

    }
}
