package com.himanshu.halanxhomes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.himanshu.halanxhomes.model.Houses;
import com.himanshu.halanxhomes.model.LogOut;
import com.himanshu.halanxhomes.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class List_Homes extends AppCompatActivity {

    private static ChipGroup chipGroup;
    RecyclerView recyclerView;
    private String Furnish = null,
            accomodation_type = null,
            min_rent = null,
            Bhk = null, lat = null, lng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__homes);
        requestPermission();
        chipGroup = findViewById(R.id.chipgroup);

        ChipMaking("Near By");
        ChipMaking("Girls and Boys");
        ChipMaking("Girls and Family");
        ChipMaking("All");
        ChipMaking("Minimum Rent");
        ChipMaking("1-BHK");
        ChipMaking("2-BHK");
        ChipMaking("3-BHK");
        fetchdata();
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setDataonChips();

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }

    public void fetchdata() {
        Call<Houses> list = RetrofitClient.getInstance().getApi().houselisting(Furnish, null,
                accomodation_type, min_rent, null,
                lat, lng, "5", Bhk);
        list.enqueue(new Callback<Houses>() {
            @Override
            public void onResponse(Call<Houses> call, Response<Houses> response) {
                if (response.isSuccessful()) {
                    Houses houses = response.body();
                    String resp = response.body().toString();
                    if (houses.getCount() > 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setAdapter(new home_adapter(List_Homes.this, houses.getResults()));
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "Currently in your locality no homes are avaialbe", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "List is not extracted yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Houses> call, Throwable t) {

            }
        });
    }

    public void setDataonChips() {
        chipGroup.getChildCount();
        final Chip chip = (Chip) chipGroup.getChildAt(0);
        final Chip chip1 = (Chip) chipGroup.getChildAt(1);
        final Chip chip2 = (Chip) chipGroup.getChildAt(2);
        final Chip chip3 = (Chip) chipGroup.getChildAt(3);
        Chip chip4 = (Chip) chipGroup.getChildAt(4);
        final Chip chip5 = (Chip) chipGroup.getChildAt(5);
        final Chip chip6 = (Chip) chipGroup.getChildAt(6);
        final Chip chip7 = (Chip) chipGroup.getChildAt(7);

        chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getlatlng();
                } else {
                    lat = null;
                    lng = null;

                }
                fetchdata();
            }
        });

        chip1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    accomodation_type = "Girls";

                    if (chip1.isChecked() && chip3.isChecked() && chip2.isChecked()) {
                        accomodation_type = "All";
                    }

                } else {
                    accomodation_type = null;
                }

                if (chip2.isChecked() && chip3.isChecked()) {
                    accomodation_type = "Boys and Family";
                } else if (chip2.isChecked()) {
                    accomodation_type = "Boys";
                } else if (chip3.isChecked()) {
                    accomodation_type = "Family";
                }

                fetchdata();
            }
        });

        chip2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    if (chip1.isChecked()) {
                        accomodation_type = "Girls and Boys";
                    }
                    if (chip1.isChecked() && chip3.isChecked() && chip2.isChecked()) {
                        accomodation_type = "All";
                    } else {
                        accomodation_type = "Boys";
                    }
                } else {
                    accomodation_type = null;
                }
                if (chip1.isChecked() && chip3.isChecked()) {
                    accomodation_type = "Girls and Family";
                } else if (chip1.isChecked()) {
                    accomodation_type = "Girls";
                } else if (chip3.isChecked()) {
                    accomodation_type = "Family";
                }
                fetchdata();
            }
        });

        chip3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (chip1.isChecked() && chip2.isChecked()) {
                        accomodation_type = "All";
                    } else if (chip1.isChecked()) {
                        accomodation_type = "Girls and Family";
                    } else {
                        accomodation_type = "Family";
                    }
                } else {
                    accomodation_type = null;
                }
                fetchdata();
            }
        });

        chip4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    min_rent = "10000";
                } else {
                    min_rent = null;
                }
                fetchdata();
            }
        });

        chip5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Bhk = "1";
                    if (chip6.isChecked() && chip7.isChecked()) {
                        Bhk = "3";
                    }
                } else {
                    Bhk = null;
                    if (chip6.isChecked() && chip7.isChecked()) {
                        Bhk = "3";
                    } else if (chip6.isChecked()) {
                        Bhk = "2";
                    } else if (chip7.isChecked()) {
                        Bhk = "3";
                    }
                }


                fetchdata();
            }
        });
        chip6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Bhk = "2";
                    if (chip5.isChecked() && chip7.isChecked()) {
                        Bhk = "3";
                    }
                } else {
                    Bhk = null;
                    if (chip5.isChecked() && chip7.isChecked()) {
                        Bhk = "3";
                    } else if (chip7.isChecked()) {
                        Bhk = "3";
                    } else if (chip5.isChecked()) {
                        Bhk = "1";
                    }
                }

                fetchdata();
            }
        });

        chip7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Bhk = "3";
                    if (chip5.isChecked() && chip6.isChecked()) {
                        Bhk = "3";
                    }
                } else {
                    Bhk = null;
                }

                fetchdata();
            }
        });

    }

    private void getlatlng() {

        if (ActivityCompat.checkSelfPermission(List_Homes.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                //TODO: UI updates.
                if (location != null) {
                    lat = Double.toString(location.getLatitude());
                    lng = Double.toString(location.getLongitude());
                    if (lng != null) {
                        Toast.makeText(getApplicationContext(), "Latitude = " + lat + "Longitude = " + lng, Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void logout(View view) {
        SharedPreferences prefs = getSharedPreferences(
                "User", MODE_PRIVATE);
        String presentkey = prefs.getString("key", null);
        if (presentkey != null) {
            Call<LogOut> logOutCall = RetrofitClient.getInstance().getApi().logout(presentkey);
            logOutCall.enqueue(new Callback<LogOut>() {
                @Override
                public void onResponse(Call<LogOut> call, Response<LogOut> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), response.body().getDetail(), Toast.LENGTH_LONG).show();
                        deleteshared();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<LogOut> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Something Went Wrong", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    public void ChipMaking(String tag) {
        Chip chip = new Chip(this);
        chip.setText(tag);
        chip.setTextAppearanceResource(R.style.ChipTextStyle);
        chip.setPaddingRelative(5, 5, 5, 5);
        chip.setElevation(15);
        chip.setCheckable(true);
        chip.setClickable(true);
        chipGroup.addView(chip);
    }

    public void deleteshared() {
        SharedPreferences sharedPreferences = getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("key");
        editor.commit();
    }

}
