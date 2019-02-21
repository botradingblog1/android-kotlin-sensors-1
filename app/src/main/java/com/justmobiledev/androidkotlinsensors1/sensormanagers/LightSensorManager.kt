package com.justmobiledev.androidkotlinsensors1.sensormanagers

import android.app.Service
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import com.justmobiledev.androidkotlinsensors1.MyApplication

object LightSensorManager : 
    HandlerThread("LightSensorManager"), SensorEventListener {
    private val TAG : String = "LightSensorManager"
    private var handler: Handler? = null
    private var sensorManager : SensorManager?= null
    private var sensor : Sensor?= null
    private var sensorExists = false


    init{
        sensorManager = (MyApplication.getApplicationContext().getSystemService(Service.SENSOR_SERVICE)) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

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
            // ground!!.updateMe(event.values[1] , event.values[0])
            //Log.d(TAG, ""+event.values[1]+", "+event.values[0])
            Log.d(TAG, "onSensorChanged: "+event.values[0]);
        }
    }

    /* private fun getHandler(looper: Looper): Handler {
        //1
        return object:Handler(looper) {
            //2
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)

                /* val foodOrder = msg?.obj as FoodOrder
                //4
                foodOrder.foodPrice = convertCurrency(foodOrder.foodPrice)
                //5
                foodOrder.sideOrder = attachSideOrder()
                //6
                val processedMessage = Message()
                //7
                processedMessage.obj = foodOrder*/

                //uiHandler.sendMessage(processedMessage)
            }
        }
    }

    fun sendOrder(foodOrder: FoodOrder) {
        //1
        val message = Message()
        //2
        message.obj = foodOrder
        //3
        handler?.sendMessage(message)
    }

    override fun onLooperPrepared() {
        super.onLooperPrepared()

        handler = getHandler(looper)
    }*/
}
