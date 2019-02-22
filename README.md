# Android Sample App for Exploring Sensors (Kotlin)

## Overview
This project is a sample app for exploring device sensors for my [blog post on justmobiledev.com](http://justmobiledev.com/exploring-device-sensors-with-kotlin/).
It implements asynchronous sensor data collection for light, temperature and gyroscope and displays the results.

## Supporting material
For more detailed information about the implementation, please see my [blog post on exploring device sensors with Kotlin](http://justmobiledev.com/exploring-device-sensors-with-kotlin/).

The Android documentation on [Sensor Types](https://source.android.com/devices/sensors/sensor-types) is a good read to better understand the project.

## Getting Started
1. Cone the project from GitHub

```
	git clong https://github.com/justmobiledev/android-kotlin-sensors-1.git
```
2. Build the project in AndroidStudio
3. Deploy the app on an actual device, so you can see live sensor readings (versus emulator)


## Implementation
* The SecurePrefsBuilder is a builder class used to specify properties for your shared preferences, e.g. file name, or if keys should be encrypted.
* The wrapper around the standard SharedPreferences is implemented in the SecurePrefs class.
* The project uses the Android KeyGenerator to create an AES-256 encryption key, which is safely stored in the Keystore.
* When you set values, the key and the value is encrypted in the SharedPrefs class with the encryption key retrieved from the Keystore before they are stored in the shared preferences XML file.
* When you get values, they key is encrypted to look up the value, the value is decrypted from the shared preferences XML file. For both actions the encryption key is retrieved from the Keystore.
* To see how your key and value are stored, open the 'Device Explorer' from the right Android Studio tab, and go to data\data\com.mobile.justmobiledev.androidsecurepreferences1\shared_prefs\my_secure_prefs_file.xml.
You can right-click on the file and 'Save As' to a temp directory on your computer. The file content should show the encrypted key and value, e.g.


## Sample App Usage
1. Select the 'START' button to start collecting sensor data from the light sensor, temperature sensor and gyroscope
2. In case the device does not have a corresponding sensor, it will display 'sensor not available'
3. Press the 'STOP' button to stop collecting data.

## Screenshots
![Kotlin Sensors 1](screenshots/android-kotlin-sensors-1.png?raw=true "Kotlin Sensors 1")
