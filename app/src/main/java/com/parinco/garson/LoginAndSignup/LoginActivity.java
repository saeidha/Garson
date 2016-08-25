package com.parinco.garson.LoginAndSignup;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.parinco.garson.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ConnectivityManager cmanager = (ConnectivityManager)
                getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cmanager.getActiveNetworkInfo();

        fragmentTransaction.replace(R.id.loginfragment, new LoginFragment()).commit();
        if(networkInfo !=null && networkInfo.isConnected()) {
            Log.i("my","hooooo");
        }

    }
}
