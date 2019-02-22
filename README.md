# Android Sample App for Exploring Sensors (Kotlin)

## Overview
This project is a sample app for exploring device sensors for my [blog post on justmobiledev.com](http://justmobiledev.com/exploring-device-sensors-with-kotlin/).
It implements asynchronous sensor data collection for light, temperature and gyroscope and displays the results.

## Supporting material
For more detailed information about the implementation, please see my [blog post on exploring device sensors with Kotlin](http://justmobiledev.com/exploring-device-sensors-with-kotlin/).

The Android documentation on the [Sensor Manager](https://developer.android.com/reference/android/hardware/SensorManager.html) and [Sensor Types](https://source.android.com/devices/sensors/sensor-types) is a good read to better understand the project.

## Getting Started
1. Cone the project from GitHub

```
	git clone https://github.com/justmobiledev/android-kotlin-sensors-1.git
```
2. Build the project in AndroidStudio
3. Deploy the app on an actual device so you can see live sensor readings (versus emulator)


## Implementation
* The sensor collection classes are implemented as singletons: LightSensorManager, GyroSensorManager and TempSensorManager
* These manager classes get a reference to the SensorManager and then the corresponding Sensor using getDefaultSensor with the sensor type constant.
* In order to perform asynchronous data collection, the managers use a HandlerThread and a Handler that gets the HandlerThread's looper.
Then the handler is passed into the SensorManager, registerListener() method.
* In order to pass sensor event data back to the controller, the controller registers a handler with the custom sensor manager classes.
When a new sensor event is detected, the custom sensor manager processes the event and sends a handler message to the controller for display in the view.

## Sample App Usage
1. Select the 'START' button to start collecting sensor data from the light sensor, temperature sensor and gyroscope
2. In case the device does not have a corresponding sensor, it will display 'sensor not available'
3. Press the 'STOP' button to stop collecting data.

## Screenshots
![Kotlin Sensors 1](screenshots/android-kotlin-sensors-1.png?raw=true "Kotlin Sensors 1")
