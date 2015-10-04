package com.github.hackruixfirstly.firstly.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.github.hackruixfirstly.firstly.FirstlyApplication;
import com.github.hackruixfirstly.firstly.R;
import com.github.hackruixfirstly.firstly.models.AuthRequest;
import com.github.hackruixfirstly.firstly.network.FirstlyAPI;
import com.squareup.okhttp.ResponseBody;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by trevor on 10/3/15.
 */
public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginManager loginManager;

    @Inject FirstlyAPI API;

    @Bind(R.id.activity_login_fb_button) TextView loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((FirstlyApplication) getApplication()).getComponent().inject(this);

        loginManager = LoginManager.getInstance();
        callbackManager = CallbackManager.Factory.create();

        loginManager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                AccessToken accessToken = loginResult.getAccessToken();

                Call<ResponseBody> call = API.auth(new AuthRequest(accessToken.getToken()));

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse (Response<ResponseBody> response, Retrofit retrofit) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure (Throwable t) {
                        Snackbar.make(loginButton, t.toString(), Snackbar.LENGTH_LONG).show();
                    }
                });


            }

            @Override
            public void onCancel() {
                Snackbar.make(loginButton, "Login Cancelled by User", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(loginButton, error.getMessage(), Snackbar.LENGTH_LONG).show();            }
            }
        );
    }

    @OnClick(R.id.activity_login_fb_button)
    public void login() {
        loginManager.logInWithReadPermissions(this, Arrays.asList("user_friends"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

