package com.parinco.garson.LoginAndSignup;


import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.app.FragmentTransaction;

import com.parinco.garson.MainPage.Homefragment;
import com.parinco.garson.R;
import com.parinco.garson.LoginAndSignup.SignupFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.loginfragment,new loginFragment(),"login");
        transaction.commit();




    }


}
