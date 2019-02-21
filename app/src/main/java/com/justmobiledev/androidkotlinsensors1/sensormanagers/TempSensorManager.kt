package com.justmobiledev.androidkotlinsensors1.sensormanagers

import android.app.Service
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.justmobiledev.androidkotlinsensors1.MainActivity
import com.justmobiledev.androidkotlinsensors1.MyApplication
import com.justmobiledev.androidkotlinsensors1.models.MySensorEvent
import com.justmobiledev.androidkotlinsensors1.models.SensorType

object TempSensorManager :
    HandlerThread("TempSensorManager"), SensorEventListener {
    private val TAG : String = "TempSensorManager"
    private var handler: Handler? = null
    private var sensorManager : SensorManager?= null
    private var sensor : Sensor?= null
    private var sensorExists = false


    init{
        sensorManager = (MyApplication.getApplicationContext().getSystemService(Service.SENSOR_SERVICE)) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        // Check sensor exists
        if (sensor != null) {
            sensorExists = true
        } else {
            sensorExists = false
        }
    }

    fun startSensor(){
        sensorManager!!.registerListener(this,
            sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    fun stopSensor(){
        sensorManager!!.unregisterListener(this)
    }

    fun sensorExists() : Boolean{
        return sensorExists
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, ""+accuracy)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.values.isNotEmpty()) {

            var msgEvent = MySensorEvent()
            msgEvent.type = SensorType.TEMPERATURE
            msgEvent.value = event.values[0].toString()

            // Send message to MainActivity
            sendMessage(msgEvent)
        }
    }

    fun setHandler(handler: Handler){
        this.handler = handler
    }

    fun sendMessage(sensorEvent: MySensorEvent) {
        if (handler == null) return

        handler?.obtainMessage(sensorEvent.type.ordinal, sensorEvent)?.apply {
            sendToTarget()
        }
    }
}