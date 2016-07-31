package com.parinco.garson.LoginAndSignup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by saeed on 7/30/16.
 */
public class loginSharedPrefrencess extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        Boolean myBoolean = prefs.getBoolean("login", false);

    }
}
