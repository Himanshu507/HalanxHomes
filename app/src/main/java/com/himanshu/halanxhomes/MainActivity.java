package com.himanshu.halanxhomes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.himanshu.halanxhomes.model.LoginKey;
import com.himanshu.halanxhomes.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RelativeLayout relay1, relay;
    ImageView img;
    EditText username, password;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(
                "User", MODE_PRIVATE);
        String presentkey = prefs.getString("key", null);
        if (presentkey != null) {
            Intent i = new Intent(this,List_Homes.class);
            startActivity(i);
            finish();
        }

        initviews();
    }

    public void initviews() {
        relay1 = findViewById(R.id.relay1);
        img = findViewById(R.id.img);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        relay = findViewById(R.id.relay);
        progressBar = findViewById(R.id.progress);
    }

    public void emaillogin(View view) {

        String useremail = username.getText().toString().trim();
        String userpasswd = password.getText().toString().trim();


        if (useremail.isEmpty()) {
            username.setError("Email is required..");
            username.requestFocus();
            return;
        }

        if (userpasswd.isEmpty()) {
            password.setError("Password is required..");
            password.requestFocus();
            return;
        }

        relay.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        buttonwork(useremail, userpasswd);

    }

    public void buttonwork(String username, String password) {
        Call<LoginKey> call = RetrofitClient.getInstance().getApi().userkey(username, password);
        call.enqueue(new Callback<LoginKey>() {
            @Override
            public void onResponse(Call<LoginKey> call, Response<LoginKey> response) {
                LoginKey loginKey = response.body();
                if (response.isSuccessful()) {
                    if (!loginKey.getKey().isEmpty()) {
                        Toast.makeText(getApplicationContext(), loginKey.getKey(), Toast.LENGTH_SHORT).show();
                        saveshared(loginKey.getKey());
                        Intent i = new Intent(getApplicationContext(),List_Homes.class);
                        startActivity(i);
                        finish();
                    }
                } else {
                    progressBar.setVisibility(View.GONE);
                    relay.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<LoginKey> call, Throwable t) {

            }
        });
    }

    public void saveshared(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", key);
        editor.apply();
    }


}
