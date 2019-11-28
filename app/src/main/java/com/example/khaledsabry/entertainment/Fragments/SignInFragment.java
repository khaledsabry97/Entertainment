package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.khaledsabry.entertainment.Activities.MainActivity;
import com.example.khaledsabry.entertainment.Controllers.SignInUpController;
import com.example.khaledsabry.entertainment.Controllers.Toasts;
import com.example.khaledsabry.entertainment.Fragments.MainMenu.MainMenuFragment;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;


public class SignInFragment extends Fragment {
    EditText username;
    EditText password;
    Button signIn;
    Button signUp;
    Button forgetPassword;
    SignInUpController controller;

    public static SignInFragment newInstance() {
        SignInFragment fragment = new SignInFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        signIn = view.findViewById(R.id.signin);
        signUp = view.findViewById(R.id.signup);
        forgetPassword = view.findViewById(R.id.forgetpassword);
        controller = new SignInUpController();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.loadFragmentNoReturn(R.id.mainFrame, SignUpFragment.newInstance());
            }
        });


        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //should provide the fragment here
            }
        });

//signIn();
        return view;
    }


    private void signIn()
    {
        /*
     if(!allChecked())
         return;
*/
        //username.setText("khaled");
        //password.setText("4234324");
     controller.signIn(username.getText().toString(), password.getText().toString(), new OnSuccess.bool() {
         @Override
         public void onSuccess(boolean state) {
             if(!state)
                 Toasts.warning("check your username/password");
             else
                 MainActivity.loadFragmentNoReturn(R.id.mainFrame, MainFragment.newInstance());
         }
     });

    }

    private boolean allChecked()
    {
        if(userNameCheck() && passwordCheck())
            return true;
        return false;
    }

    private boolean userNameCheck()
    {
        if(username.getText().toString().length() == 0)
        {
            Toasts.warning("write your username");
            return false;
        }
        return true;
    }

    private boolean passwordCheck()
    {
        if(password.getText().toString().length() == 0)
        {
            Toasts.warning("write your password");
            return false;
        }
        return true;
    }



}
