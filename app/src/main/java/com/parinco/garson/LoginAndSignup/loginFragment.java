package com.parinco.garson.LoginAndSignup;


import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parinco.garson.R;

import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    static String json,ans;

    EditText etuser , etpass ;
    String user,pass;

    static String ipstr = "192.168.1.20";
    String result;
    Boolean check = true;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets()
                ,"fonts/yekan.ttf");



        //login
        etuser = (EditText) view.findViewById(R.id.loginuser);
        etpass = (EditText) view.findViewById(R.id.loginpass);

        TextInputLayout userinput = (TextInputLayout) view.findViewById
                (R.id.textinputuser);
        TextInputLayout passinput = (TextInputLayout) view.findViewById
                (R.id.textinputpass);
        userinput.setTypeface(typeface);
        passinput.setTypeface(typeface);

        Button loginbutton = (Button) view.findViewById(R.id.btn_login);
        loginbutton.setTypeface(typeface);
        loginbutton.setText(" ورود ");

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Log.i("my","1");
                user = etuser.getText().toString().trim();
                pass = etpass.getText().toString().trim();
                Log.i("my",user+" : "+pass);
                if(checkuserandpass(user,pass)) {
                    login(view);
                }
            }
        });





        //signup
         TextView signup = (TextView) view.findViewById(R.id.link_signup);
        signup.setText(" آیا حساب کاربری نساخته اید؟ ساخت حساب کاربری ");
        signup.setTypeface(typeface);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.loginfragment,new SignupFragment(),"login")
                        .addToBackStack("login")
                        .commit();

            }
        });

        return view;
    }
    boolean checkuserandpass(String user,String pass){
        if(user.isEmpty() || pass.isEmpty()){
            if(user.isEmpty()) {
                etuser.setError(" نام کاربری را وارد کنید ");
            }if(pass.isEmpty()) {
                etpass.setError(" رمز عبور را وارد کنید ");
            }
            return false;
        }

        return true;
    }


    private void login(View view) {





        //login


        final ProgressDialog progressDialog = ProgressDialog.show(getActivity()
        ,null,null);
        progressDialog.setContentView(R.layout.progressbar);
        progressDialog.setIndeterminate(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AsyncTaskLoginSignup bgtask = new AsyncTaskLoginSignup(getActivity());

                String method = "login";

                try {
                    result=new AsyncTaskLoginSignup(getActivity())
                            .execute(ipstr,method, user, pass).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                Log.i("my", "3");
                //result = AsyncTaskLoginSignup.resultstring;


                //check = checknet.isURLReachable();
                progressDialog.dismiss();
                if(result.equals("0")){
                    Toast.makeText(getActivity(),"نام کاربری یا رمزعبور اشتباه است"
                            ,Toast.LENGTH_SHORT).show();
                }else if(result.equals("1")){
                    Toast.makeText(getActivity(),"درست است"
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"شبکه در دسترس نمی باشد",
                            Toast.LENGTH_SHORT).show();
                }

            }
        },3000);
       // checkinternet checknet = new checkinternet(getActivity(),ipstr);
       // check = checknet.isNetworkAvailable();

        //Log.i("my",check+"");

//


    }


}

