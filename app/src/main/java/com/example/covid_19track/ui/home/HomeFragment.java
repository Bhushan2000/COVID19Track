package com.example.covid_19track.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19track.FetchDataInBackground;
import com.example.covid_19track.R;
import com.example.covid_19track.databinding.FragmentHomeBinding;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.ReferenceQueue;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HomeFragment extends Fragment {


    private static final String TAG = "HomeFragment";

    FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        // actionBar Title
        getActivity().setTitle("Overview");


        // call volley
        getData();
        return view;
    }

    private String getData(long milliSecond) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE,dd MMM yyyy hh:mm:ss aaa");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSecond);
        return simpleDateFormat.format(calendar.getTime());


    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://corona.lmao.ninja/v2/all";


        


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                binding.progressCircularHome.setVisibility(View.GONE);

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    binding.tvTotalConfirmed.setText(jsonObject.getString("cases"));
                    binding.tvTotalDeaths.setText(jsonObject.getString("deaths"));
                    binding.tvTotalRecovered.setText(jsonObject.getString("recovered"));
                    binding.tvLastUpdated.setText(getData(jsonObject.getLong("updated")));

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.progressCircularHome.setVisibility(View.GONE);


                Log.d("onErrorResponse: ", error.toString());


            }
        });

        queue.add(stringRequest);


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
