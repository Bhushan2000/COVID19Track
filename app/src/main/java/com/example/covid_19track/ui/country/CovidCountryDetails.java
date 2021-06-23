package com.example.covid_19track.ui.country;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.covid_19track.R;
import com.example.covid_19track.databinding.ActivityCovidCountryDetailsBinding;

public class CovidCountryDetails extends AppCompatActivity {



    ActivityCovidCountryDetailsBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCovidCountryDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);





        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");



        // Call Covid country
        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA COVID");
        binding.tvTotalCountryName.setText(covidCountry.getmCovidCountry());
        binding.tvDetailTotalCases.setText(Integer.toString(covidCountry.getmCases()));
        binding.tvDetailTodayCases.setText(covidCountry.getmTodayCases());
        binding.tvDetailTotalDeaths.setText(covidCountry.getmDeaths());
        binding.tvDetailTodayDeaths.setText(covidCountry.getmTodayDeaths());
        binding.tvDetailTotalRecovered.setText(covidCountry.getmRecovered());
        binding.tvDetailTotalActive.setText(covidCountry.getmActive());
        binding.tvDetailTotalCritical.setText(covidCountry.getmCritical());
        binding.tvContinent.setText(covidCountry.getContinent());
        binding.tvPopulation.setText(Integer.toString(covidCountry.getPopulation()));
        binding.tvTests.setText(Integer.toString(covidCountry.getTests()));
        binding.tvTodayRecovered.setText(Integer.toString(covidCountry.getTodayRecovered()));


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);


    }
}
