package com.github.hackruixfirstly.firstly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.github.hackruixfirstly.firstly.R;

import java.util.Arrays;

import butterknife.ButterKnife;

/**
 * Created by trevor on 10/3/15.
 */
public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginManager loginManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();
        loginManager.logInWithReadPermissions(this, Arrays.asList("user_friends"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

