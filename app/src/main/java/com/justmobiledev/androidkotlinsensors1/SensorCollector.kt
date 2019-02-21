package com.justmobiledev.androidkotlinsensors1

import android.app.Service
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log


object SensorCollector: SensorEventListener {
    private val TAG : String = "SensorCollector"

    private var sensorManager : SensorManager ?= null
    private var tempSensor : Sensor ?= null
    private var sensorExists = false

    init{
        sensorManager = (MyApplication.getApplicationContext().getSystemService(Service.SENSOR_SERVICE)) as SensorManager
        tempSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)

        // Check sensor exists
        if (tempSensor != null) {
            sensorExists = true
        } else {
            sensorExists = true
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, ""+accuracy)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event != null && event.values.isNotEmpty()) {
            // ground!!.updateMe(event.values[1] , event.values[0])
            //Log.d(TAG, ""+event.values[1]+", "+event.values[0])
            Log.d(TAG, "onSensorChanged: "+event.values[0]);
        }
    }

    fun start(){
        sensorManager!!.registerListener(this, tempSensor, SensorManager.SENSOR_DELAY_UI)
    }

    fun stop(){
        sensorManager!!.unregisterListener(this)
    }

    fun sensorExists() : Boolean{
        return sensorExists
    }
}