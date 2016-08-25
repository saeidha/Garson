package com.parinco.garson.LoginAndSignup;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.parinco.garson.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 */
public class SignupFragment extends Fragment {

    List<String> citylist = new ArrayList<String>();
    TextView name, family, user, pass, city, phonenumber, mail;
    EditText etname, etfamily, etuser, etpass, etphonenumber, etmail;
    String suser, spass, scity, sphonenumber, smail, sname, sfamily;
    Spinner spinner;
    View view;
    int temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        this.view = view;
        signup();
        return view;
    }

    private void signup() {
        //change text and font start
        Typeface yekan = Typeface.createFromAsset(getActivity().getAssets()
                , "fonts/yekan.ttf");
        name = (TextView) view.findViewById(R.id.name_singup);
        family = (TextView) view.findViewById(R.id.family_singup);
        user = (TextView) view.findViewById(R.id.user_singup);
        pass = (TextView) view.findViewById(R.id.pass_signup);
        city = (TextView) view.findViewById(R.id.city_signup);
        phonenumber = (TextView) view.findViewById(R.id.number_signup);
        mail = (TextView) view.findViewById(R.id.mail_signup);
        name.setText("نام:");
        name.setTypeface(yekan);
        family.setText("نام خانوادگی:");
        family.setTypeface(yekan);
        user.setText("نام کاربری :");
        user.setTypeface(yekan);
        pass.setText("رمزعبور:");
        pass.setTypeface(yekan);
        city.setText("شهر:");
        city.setTypeface(yekan);
        phonenumber.setText("تلفن همراه:");
        phonenumber.setTypeface(yekan);
        mail.setText("پست الکترونیکی:");
        mail.setTypeface(yekan);

        Button btn = (Button) view.findViewById(R.id.regbtn);
        btn.setTypeface(yekan);
        btn.setText("ثبت نام");


        //finish
        etuser = (EditText) view.findViewById(R.id.fuser_signup);
        etpass = (EditText) view.findViewById(R.id.fpass_signup);
        etname = (EditText) view.findViewById(R.id.fname_signup);
        etfamily = (EditText) view.findViewById(R.id.ffamily_signup);
        etphonenumber = (EditText) view.findViewById(R.id.fnumber_signup);
        etmail = (EditText) view.findViewById(R.id.fmail_signup);
        spinner = (Spinner) view.findViewById(R.id.spinnercity);
        citylist.add("شهر خود را انتخاب کنید");
        citylist.add("مشهد");
        citylist.add("تهران");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, citylist);


        dataAdapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                temp = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                temp = 0;
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reguser();
            }
        });


    }

    boolean checkinput(String sname, String sfamily, String suser, String spass
            , String smail, String sphonenumber, String scity) {
        boolean is = true;
        if (sname.isEmpty()) {
            etname.setError(" نام خود را وارد کنید ");
            is = false;
        }
        if (sfamily.isEmpty()) {
            etfamily.setError(" فامیل خود را وارد کنید ");
            is = false;
        }
        if (suser.isEmpty()) {
            etuser.setError(" نام کاربری را وارد نمایید ");
            is = false;
        }
        if (spass.isEmpty()) {
            etpass.setError(" رمز عبور را وارد کنید ");
            is = false;
        }
        if (smail.isEmpty()) {
            etmail.setError(" پست الکترونیکی خود را وارد نمایید ");
            is = false;
        }
        if (sphonenumber.isEmpty()) {
            etphonenumber.setError(" شماره تلفن خود را وارد نمایید ");
            is = false;
        }
        if (scity.equals("nothing")) {
            Toast.makeText(getContext(), " لطفا شهر خود را انتخاب کنید ", Toast.LENGTH_LONG).show();
            is = false;
        }
        if (!suser.isEmpty()) {
            char[] ch = suser.toCharArray();
            boolean b = true;
            for (int i = 0; i < ch.length && b == true; i++) {
                b=false;

                if ((ch[i] >= 'a' && ch[0] <= 'z') || (ch[i] >= 'A' && ch[i] <= 'Z')
                        || (ch[i] >= '0' && ch[i] <= '9') || ch[i] == '_' || ch[i] == '-'
                        || ch[i] == '@') {
                    b = true;
                    Log.i("f",ch[i]+"");
                }
            }
            if (b == false) {
                etuser.setError(" نام کاربری فقط شامل عدد و حروف انگلیسی و _ یا - یا @ می باشد ");
                is = false;
            }

        }
        if (!spass.isEmpty()) {
            char[] ch = spass.toCharArray();
            boolean b = true;
            for (int i = 0; i < ch.length&& b == true; i++) {
                b=false;
                if ((ch[i] >= 'a' && ch[0] <= 'z') || (ch[i] >= 'A' && ch[i] <= 'Z')
                        || (ch[i] >= '0' && ch[i] <= '9') || ch[i] == '_' || ch[i] == '-'
                        || ch[i] == '@') {
                    b = true;
                    Log.i("f",ch[i]+"");
                }

            }
            if (b == false) {
                etpass.setError(" رمزعبور فقط شامل عدد و حروف انگلیسی و _ یا - یا @ می باشد");
                is = false;
            }

        }
        if (!sphonenumber.isEmpty()) {
            char[] ch = sphonenumber.toCharArray();
            if (ch[0] != '0' || ch[1] != '9') {
                etphonenumber.setError(" شماره تلفن با 09 آغاز می شود ");
                is = false;
            } else if (ch.length != 11) {
                etphonenumber.setError(" شماره تلفن ۱۱ رقم می باشد ");
                is = false;
            } else {
                boolean b = true;
                for (int i = 0; i < ch.length&& b == true; i++) {
                    b=false;
                    if ((ch[i] >= '0' && ch[i] <= '9')) {
                        b = true;
                    }

                }
                if (b == false) {
                    etphonenumber.setError(" شماره تلفن شامل اعداد انگلیسی می باشد ");
                    is = false;
                }

            }
        }
        if(suser.length()>20){
            etuser.setError(" نام کاربری شامل حداکثر ۲۰ کاراکتر می باشد ");
            is=false;
        }
        if(spass.length()>20){
            etpass.setError(" رمزعبور شامل حداکثر ۲۰ کاراکتر می باشد ");
            is=false;
        }

        return is;
    }

    public void reguser() {
        suser = etuser.getText().toString();

        spass = etpass.getText().toString();
        sname = etname.getText().toString();
        smail = etmail.getText().toString();
        sfamily = etfamily.getText().toString();
        sphonenumber = etphonenumber.getText().toString();
        if (temp == 0) {
            scity = "nothing";
        } else if (temp == 1) {
            scity = "mashhad";
        } else if (temp == 2) {
            scity = "tehran";
        }
        Log.i("my", suser + " " + spass + " " + sname + " " + sfamily + " " +
                sphonenumber + " " + smail + " " + scity);

        if (checkinput(sname, sfamily, suser, spass, smail, sphonenumber, scity)) {

            String method = "register";
            Log.i("my","kj-----");
            AsyncTaskLoginSignup bgtask = new AsyncTaskLoginSignup(getContext());
//        bgtask.execute(LoginFragment.ipstr,method,suser,spass,sname,sfamily
//                ,sphonenumber,smail,scity);
//        finish();
        }
    }


}
