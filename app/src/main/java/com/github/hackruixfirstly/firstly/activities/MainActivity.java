package com.github.hackruixfirstly.firstly.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.github.hackruixfirstly.firstly.FirstlyApplication;
import com.github.hackruixfirstly.firstly.R;
import com.github.hackruixfirstly.firstly.network.FirstlyAPI;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Inject
    FirstlyAPI API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((FirstlyApplication) getApplication()).getComponent().inject(this);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<JsonObject> call = API.getTest();

                call.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Response<JsonObject> response, Retrofit retrofit) {
                        Timber.d(response.body().toString());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Timber.d(t.toString());
                    }
                });

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
