package com.parinco.garson.LoginAndSignup;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parinco.garson.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class loginFragment extends Fragment {


    public loginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        login(view);
        return view;
    }

    private void login(View view) {
        final TextView signup = (TextView) view.findViewById(R.id.signuptext);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFragment loginfragment = (loginFragment) getFragmentManager()
                        .findFragmentByTag("login");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.remove(loginfragment);
               // transaction.setCustomAnimations(R.animator.enter_from_left,
               //         R.animator.exit_to_right);
                transaction.add(R.id.loginfragment,new SignupFragment(),"signup");
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }


}
