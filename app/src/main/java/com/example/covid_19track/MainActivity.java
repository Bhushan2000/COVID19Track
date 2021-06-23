package com.example.covid_19track;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.covid_19track.databinding.ActivityMainBinding;
import com.example.covid_19track.ui.country.CountryFragment;
import com.example.covid_19track.ui.home.HomeFragment;
import com.example.covid_19track.ui.more.MoreFragment;
import com.example.covid_19track.ui.vaccine.VaccineFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity  extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Fragment fragment;

    // ViewBinding
      ActivityMainBinding mainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ViewBinding
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);










        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(mainBinding.navView, navController);

        //loading the default fragment
        loadFragment(new HomeFragment());
        mainBinding.navView.setOnNavigationItemSelectedListener(this);
        mainBinding.navView.setItemIconTintList(null);



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

          fragment = null;

        switch (item.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;

            case R.id.navigation_country1:
                fragment = new CountryFragment();
                break;
            case R.id.navigation_vaccine:
                fragment = new VaccineFragment();
                break;
            case R.id.moreFragment:
                fragment = new MoreFragment();
                break;




        }
        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    protected void onRestoreInstanceState(final Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Read the state of item position
      fragment = new HomeFragment();
    }
}
