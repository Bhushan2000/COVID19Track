package com.example.covid_19track.ui.vaccine;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.covid_19track.R;
import com.example.covid_19track.databinding.FragmentVaccineBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VaccineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VaccineFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VaccineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VaccineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VaccineFragment newInstance(String param1, String param2) {
        VaccineFragment fragment = new VaccineFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // creating a variable for our button.
    private Button searchButton;

    // creating variable for our edit text.
    private EditText pinCodeEdt;

    // creating a variable for our recycler view.
    RecyclerView centersRV;

    // creating a variable for adapter class.
    VaccineAdapter vaccineAdapter;


    // creating a variable for our list
    List<VaccineModel> centerList;

    // creating a variable for progress bar.
    ProgressBar loadingPB;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentVaccineBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVaccineBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        getActivity().setTitle("Vaccination");

        centerList = new ArrayList<>();




        binding.idBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pincode = binding.idEdtPinCode.getText().toString();
                // on below line we are validating
                // our pin code as 6 digit or not.
                if (pincode.length() != 6) {

                    // this method is called when users enter invalid pin code.
                    Toast.makeText(getContext(), "Please enter valid pin code", Toast.LENGTH_SHORT).show();

                } else {
                    centerList.clear();


                    getAppointments(binding.idEdtPinCode.getText().toString());


                }
            }
        });


        return view;
    }

    private void getAppointments(String pincode) {

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        date = dateFormat.format(calendar.getTime()).trim();


       // String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pincode + "&date=" + date;

        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + pincode + "&date=" + date;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(getContext());
        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        binding.idPBLoading.setVisibility(View.VISIBLE);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {




                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty
                binding.idPBLoading.setVisibility(View.GONE);
                Log.e("TAG", "SUCCESS RESPONSE IS $response"+response);

                binding.idEdtPinCode.setText("");

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("centers");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject centerObj = jsonArray.getJSONObject(i);

                        String centerName = centerObj.getString("name");
                        String centerAddress = centerObj.getString("address");
                        String centerFromTime = centerObj.getString("from");
                        String centerToTime = centerObj.getString("to");
                        String fee_type = centerObj.getString("fee_type");

                        // on below line we are creating a variable for our session object
                        JSONObject sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0);
                        int ageLimit = sessionObj.getInt("min_age_limit");
                        String vaccineName = sessionObj.getString("vaccine");
                        int avaliableCapacity = sessionObj.getInt("available_capacity");


                        centerList.add(new VaccineModel(centerName,
                                centerAddress,
                                centerFromTime,
                                centerToTime,
                                fee_type,
                                ageLimit,
                                vaccineName,
                                avaliableCapacity));


                    }


                    vaccineAdapter = new VaccineAdapter(centerList, getContext());
//
                    // on the below line we are setting layout manager to our recycler view.
                    binding.centersRV.setLayoutManager(new LinearLayoutManager(getContext()));

                    // on the below line we are setting an adapter to our recycler view.
                    binding.centersRV.setAdapter(vaccineAdapter);

                    // on the below line we are notifying our adapter as the data is updated.
                    vaccineAdapter.notifyDataSetChanged();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                binding.idPBLoading.setVisibility(View.GONE);

                Toast.makeText(getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();

            }
        });

        // below line is to make
        // a json object request.
        queue.add(request);


//
    }


}