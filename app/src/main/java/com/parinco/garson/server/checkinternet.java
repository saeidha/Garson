package com.parinco.garson.server;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.parinco.garson.LoginAndSignup.AsyncTaskLoginSignup;

/**
 * Created by saeed on 8/5/16.
 */
public class checkinternet {
    Activity context;
    String checkurl;
    Boolean b = true;
    String result="0";

       public checkinternet(Activity context,String url){
        this.context=context;
           this.checkurl=url;
    }
   public boolean isNetworkAvailable() {
       ConnectivityManager connec =(ConnectivityManager)context.getSystemService
               (context.getBaseContext().CONNECTIVITY_SERVICE);
       // Check for network connections
       if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||

               connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
               connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
               connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
           Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
           return true;
       }else if (
               connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                       connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
           Toast.makeText(context, " Not Connected ", Toast.LENGTH_LONG).show();
           return false;
       }
       return false;
   }
    public boolean isURLReachable() {
        final AsyncTaskLoginSignup bgtask = new AsyncTaskLoginSignup(context);
                bgtask.execute(checkurl,"check");

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                result = AsyncTaskLoginSignup.resultstring;

                Log.i("my", AsyncTaskLoginSignup.resultstring+"kkk");
                if(result.equals("1")){
                    b=true;
                }else{
                    b=false;
                }

            }
        }, 3000);

        return b;


    }
}
