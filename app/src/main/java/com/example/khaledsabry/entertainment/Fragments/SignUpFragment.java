package com.example.khaledsabry.entertainment.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.khaledsabry.entertainment.Controllers.SignInUpController;
import com.example.khaledsabry.entertainment.Interfaces.OnSuccess;
import com.example.khaledsabry.entertainment.R;


public class SignUpFragment extends Fragment {

    EditText username;
    EditText password;
    EditText age;
    EditText email;
    EditText phone;
    TextView header;
    Button signUp;
    SignInUpController signInUpController;
    boolean userNamechecked = false;
    boolean userNameAvailable = false;
    boolean emailchecked = false;
    boolean emailAvailable = false;

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        header = view.findViewById(R.id.header);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        age = view.findViewById(R.id.age);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        signUp = view.findViewById(R.id.signup);

        header.setText("Sign Up");
        header.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        signInUpController = new SignInUpController();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });


        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkUserNameAvailability();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkEmailAvailability();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


    private void signUp() {

        if (!allCheck())
            return;


        signInUpController.signUp(username.getText().toString(), email.getText().toString(), password.getText().toString(), age.getText().toString(), phone.getText().toString());


    }


    private boolean allCheck() {
        if (checkEmail() && checkPassWord() && checkUserName() && checkPhone() && checkAge())
            return true;
        return false;
    }

    private boolean checkPassWord() {
        if (password.getText().toString().isEmpty() || password.getText().toString().length() < 4) {
            signInUpController.toast("please check your password");
            return false;
        }

        return true;
    }

    private boolean checkUserName() {
        if (username.getText().toString().isEmpty() || username.getText().toString().length() < 4) {
            signInUpController.toast("check your username");
            return false;
        }


        if (userNamechecked)
            if (!userNameAvailable) {
                signInUpController.toast("there is another one has this username");
                return false;
            }

        return true;
    }

    private boolean checkEmail() {
        if (email.getText().toString().isEmpty() || email.getText().toString().length() < 5 || !email.getText().toString().contains("@")) {
            signInUpController.toast("check your email");
            return false;
        }
        if (emailchecked)
            if (!emailAvailable) {
                signInUpController.toast("there is another one has this email");
                return false;
            }
        return true;
    }


    private boolean checkAge() {
        if (age.getText().toString().isEmpty() || age.getText().toString().length() < 1) {
            signInUpController.toast("check your age");
            return false;
        }
        try {
            Integer.valueOf(age.getText().toString());

            if (Integer.valueOf(age.getText().toString()) < 0 || Integer.valueOf(age.getText().toString()) > 90)
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            signInUpController.toast("check your age if he has any letter");
            return false;
        }


        return true;
    }


    private boolean checkPhone() {
        if (phone.getText().toString().isEmpty() || phone.getText().toString().length() < 6) {
            signInUpController.toast("check your phone");
            return false;
        }

        try {
            Integer.valueOf(phone.getText().toString());

            if (Integer.valueOf(phone.getText().toString()) < 0)
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            signInUpController.toast("check your phone if he has any letter");
            return false;
        }

        return true;
    }


    private void checkUserNameAvailability() {
        userNamechecked = false;
        signInUpController.checkUserNameAvailability(username.getText().toString(), new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                userNamechecked = true;
                userNameAvailable = state;
            }
        });
    }

    private void checkEmailAvailability() {
        emailchecked = false;
        signInUpController.checkEmailAvailability(email.getText().toString(), new OnSuccess.bool() {
            @Override
            public void onSuccess(boolean state) {
                emailchecked = true;
                emailAvailable = state;
            }
        });
    }

}
