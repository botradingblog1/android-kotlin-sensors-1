# Android library for storing preferences securely (Java)

## Overview
The purpose or this project is to show how preferences can be stored securely in your app.
For example, sometimes it is necessary to store sensitive user information persistently in your app (e.g. a user's DOB or ddress).
The default Android SharedPreferences are saved in a plain text XML file in the app's internal storage, so not a good place to store sensitive information.

This project shows you how to create a wrapper around the standard SharedPreferences class that encrypts both preference keys and values with a strong encryption algorithm.

Both preference keys and values can be encrypted using AES-256 CBC mode. Encryption keys are generated when the SecurePreferences are intialized and stored in the Android KeyStore.

NOTE: Use at your own risk. The library has not been tested in a production app

## Supporting material
For more detailed information about the implementation, please see my [blog post on storing preferences securely](http://justmobiledev.com/storing-preferences-securely-on-android/).

The Android documentation on [the Android Keystore System](https://developer.android.com/training/articles/keystore) is a good read to better understand the project.

## Getting Started
Add the SecurePreferences library to your root build.gradle at the end of repositories:

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Add the dependency to your app bild.gradle:

```
dependencies {
	        implementation 'com.github.justmobiledev:android-secure-preferences-lib-1:1.0'
	}
```

## Usage
```java
        // Create secure preferences
        SharedPreferences securePreferences = new SecurePrefsBuilder()
                .setApplication(MyApplication.getInstance())
                .obfuscateValue(true)
                .obfuscateKey(true)
                .setSharePrefFileName("your_secure_preference_file_bname")
                .createSharedPrefs(this);

        // Add a preference
        SharedPreferences.Editor editor = securePreferences.edit();

        String prefValue = "test string";

        // Store the string
        editor.putString("test_key", prefValue);
        editor.commit();

        // Get the string
        String prefValueRetrieved = securePreferences.getString("test_key", "");
```

## Implementation
* The SecurePrefsBuilder is a builder class used to specify properties for your shared preferences, e.g. file name, or if keys should be encrypted.
* The wrapper around the standard SharedPreferences is implemented in the SecurePrefs class.
* The project uses the Android KeyGenerator to create an AES-256 encryption key, which is safely stored in the Keystore.
* When you set values, the key and the value is encrypted in the SharedPrefs class with the encryption key retrieved from the Keystore before they are stored in the shared preferences XML file.
* When you get values, they key is encrypted to look up the value, the value is decrypted from the shared preferences XML file. For both actions the encryption key is retrieved from the Keystore.
* To see how your key and value are stored, open the 'Device Explorer' from the right Android Studio tab, and go to data\data\com.mobile.justmobiledev.androidsecurepreferences1\shared_prefs\my_secure_prefs_file.xml.
You can right-click on the file and 'Save As' to a temp directory on your computer. The file content should show the encrypted key and value, e.g.

```xml
<map>
    <string name="atgF69t3sjhUZwJosmIRZSfgXLQhg21FxbrJ534ATXo=&#10;">pXJ7qfFftbUlMozjOidT6EBXwX4l5go2XE3mo/DVrxw=&#10;    </string>
</map>
```

## Sample App Usage
1. When the app is started, you can enter a preference value into the first field.
2. Press the 'Start' button to encrypt key and value and store them into the preferences xml file
3. The app fetches the preferences again, decrypts the value and displays them in the second text field.

## Screenshots
![Secure Preferences](screenshots/secure_preferences_ss_1.png?raw=true "Secure Preferences")
