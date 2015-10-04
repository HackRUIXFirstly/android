package com.github.hackruixfirstly.firstly.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.github.hackruixfirstly.firstly.FirstlyApplication;
import com.github.hackruixfirstly.firstly.R;
import com.github.hackruixfirstly.firstly.depdendencies.AccessTokenHolder;
import com.github.hackruixfirstly.firstly.models.Experience;
import com.github.hackruixfirstly.firstly.network.FirstlyAPI;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

/**
 * Created by trevor on 10/3/15.
 */
public class NewExperienceActivity extends AppCompatActivity {

    @Inject FirstlyAPI        API;
    @Inject AccessTokenHolder tokenHolder;

    @Bind (R.id.activity_new_experience_toolbar)   Toolbar      toolbar;
    @Bind (R.id.activity_new_experience_title)     EditText     titleText;
    @Bind (R.id.activity_new_experience_submit)    Button       submitButton;
    @Bind (R.id.activity_new_experience_container) LinearLayout container;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_experience);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ((FirstlyApplication) getApplication()).getComponent().inject(this);
    }

    @OnClick (R.id.activity_new_experience_submit)
    public void onClickSubmit () {
        if (titleText.getText().toString().trim().length() == 0) {
            titleText.setError("Experience needs a title!");
        } else {
            Call<Experience> call = API.postExperience(new Experience(titleText.getText().toString()), tokenHolder.token);
            call.enqueue(new Callback<Experience>() {
                @Override
                public void onResponse (Response<Experience> response, Retrofit retrofit) {
                    finish();
                }

                @Override
                public void onFailure (Throwable t) {
                    Timber.e(t.toString());
                    Snackbar.make(container, t.getMessage(), Snackbar.LENGTH_LONG);
                }
            });
        }
    }
}
