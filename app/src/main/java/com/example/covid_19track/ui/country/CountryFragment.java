package com.example.covid_19track.ui.country;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19track.MainActivity;
import com.example.covid_19track.R;
import com.example.covid_19track.databinding.FragmentCountryBinding;
import com.example.covid_19track.databinding.FragmentHomeBinding;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryFragment extends Fragment {
    CovidCountryAdapter covidCountryAdapter;


    List<CovidCountry> covidCountries;

    FragmentCountryBinding binding;


    private static final String TAG = CountryFragment.class.getSimpleName();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentCountryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvCovidCountry.setLayoutManager(new LinearLayoutManager(getActivity()));




        //set has option menu as true because we have menu
        setHasOptionsMenu(true);

//        call list
        covidCountries = new ArrayList<>();

        //call volley method
        getDataFromServerSortTotalCases();

        binding.swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                covidCountries.clear();
                getDataFromServerSortTotalCases();
                binding.swipeToRefresh.setRefreshing(false);

            }
        });

        return root;
    }

    private void showRecyclerView() {
        covidCountryAdapter = new CovidCountryAdapter(covidCountries, getActivity());
        binding.rvCovidCountry.setAdapter(covidCountryAdapter);

        ItemClickSupport.addTo(binding.rvCovidCountry).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidCountry(covidCountries.get(position));

            }
        });
    }

    private void showSelectedCovidCountry(CovidCountry covidCountry) {
        Intent covidCcountryDetail = new Intent(getActivity(), CovidCountryDetails.class);
        covidCcountryDetail.putExtra("EXTRA COVID", covidCountry);
        startActivity(covidCcountryDetail);
    }

    private void getDataFromServerSortTotalCases() {

        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);
                    binding.progressCircularCountry.setVisibility(View.GONE);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryInfo = data.getJSONObject("countryInfo");


                            covidCountries.add(new CovidCountry(data.getString("country")
                                    , data.getInt("cases")
                                    , data.getString("todayCases")
                                    , data.getString("deaths")
                                    , data.getString("todayDeaths")
                                    , data.getString("recovered")
                                    , data.getString("active")
                                    , data.getString("critical")
                                    , countryInfo.getString("flag")
                                    , data.getString("continent")
                                    , data.getInt("tests")
                                    , data.getInt("population")
                                    , data.getInt("todayRecovered")


                            ));
                        }

//                        sort descending
                        Collections.sort(covidCountries, new Comparator<CovidCountry>() {
                            @Override
                            public int compare(CovidCountry o1, CovidCountry o2) {
                                if (o1.getmCases() > o2.getmCases()) {
                                    return -1;
                                } else {
                                    return 1;
                                }
                            }
                        });

                        // actionBar Title

                        showRecyclerView();
                        getActivity().setTitle(jsonArray.length() + " Countries");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressCircularCountry.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse: " + error);


            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    private void getDataFromServerSortAlphabetically() {

        String url = "https://corona.lmao.ninja/v2/countries";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                binding.progressCircularCountry.setVisibility(View.GONE);
                if (response != null) {
                    Log.e(TAG, "onResponse: " + response);

                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryInfo = data.getJSONObject("countryInfo");


                            covidCountries.add(new CovidCountry(data.getString("country")
                                    , data.getInt("cases")
                                    , data.getString("todayCases")
                                    , data.getString("deaths")
                                    , data.getString("todayDeaths")
                                    , data.getString("recovered")
                                    , data.getString("active")
                                    , data.getString("critical")
                                    , countryInfo.getString("flag")
                                    , data.getString("continent")
                                    , data.getInt("tests")
                                    , data.getInt("population")
                                    , data.getInt("todayRecovered")

                            ));
                        }

//

                        // actionBar Title
                        getActivity().setTitle(jsonArray.length() + " countries");
                        showRecyclerView();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressCircularCountry.setVisibility(View.GONE);
                Log.e(TAG, "onErrorResponse: " + error);


            }
        });
        Volley.newRequestQueue(getActivity()).add(stringRequest);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = new SearchView(getActivity());
        searchView.setQueryHint("Search...");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (covidCountryAdapter != null) {
                    covidCountryAdapter.getFilter().filter(newText);

                }
                return true;
            }
        });
        searchItem.setActionView(searchView);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_sort_alpha:
                 Snackbar.make(binding.progressCircularCountry,"Sort Alphabetically",Snackbar.LENGTH_SHORT).show();

                covidCountries.clear();
                binding.progressCircularCountry.setVisibility(View.VISIBLE);
                getDataFromServerSortAlphabetically();
                return true;

            case R.id.action_sort_cases:
                 Snackbar.make(binding.progressCircularCountry,"Sort by Total cases",Snackbar.LENGTH_SHORT).show();

                covidCountries.clear();
                binding.progressCircularCountry.setVisibility(View.VISIBLE);
                getDataFromServerSortTotalCases();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
