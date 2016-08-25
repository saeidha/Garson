package com.parinco.garson.LoginAndSignup;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by saeed on 6/24/16.
 */
public class AsyncTaskLoginSignup extends AsyncTask<String, Void, String> {

    Context context;
    String jsonstring;
    public static String resultstring;

    public AsyncTaskLoginSignup(Context context) {
        Log.i("my","sdsdsd");
        this.context = context;
    }

    protected String doInBackground(String... params) {

        Log.i("my","sdsdsd");
        String method = params[1];
        Log.i("my",method);
        String regurl = "http://" + params[0] + "/project/insert.php";
        String loginurl = "http://" + params[0] + "/garson/login/login.php";
        String urlcheck = "http://" + params[0] + "/garson/checkserver.php";
        Log.i("my","sdsdsd");
        if (method.equals("register")) {
            String user = params[2];
            String pass = params[3];
            String contact = params[4];
            String country = params[5];

            try {
                URL url = new URL(regurl);

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("country", "UTF-8") + "=" + URLEncoder.encode(country, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream in = httpURLConnection.getInputStream();
                in.close();

                return "Registration success";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (method.equals("login")) {
            String user = params[2];
            String pass = params[3];
            Log.i("my",user+" "+pass);

            try {

                URL url = new URL(loginurl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                bufferedWriter.write(data);
                Log.i("my","sdasdad");
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                // Read Server Response
                InputStream in = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

                return bufferedReader.readLine().toString();
                // return stringBuilder.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


        } else if (method.equals("check")) {

                try {
                    Log.i("my","checkkk1");
                    URL url = new URL(urlcheck);// Change to "http://google.com" for www  test.
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    // Read Server Response
                    Log.i("my","checkkk1");
                    InputStream in = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
                    Log.i("my","checkkk1");
                    String string= bufferedReader.readLine().toString();
                    Log.i("my",string+"dff");
                    if (string.equals("1")) {        // 200 = "OK" code (http connection is fine).
                        Log.wtf("my", "Success !");
                        return "1";
                    } else {
                        return "0";
                    }
                } catch (MalformedURLException e1) {
                    return "0";
                } catch (IOException e) {
                    return "0";
                }


        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        resultstring=result;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}