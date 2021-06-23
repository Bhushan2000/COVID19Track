package com.example.covid_19track;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bytes.ByteBufferRewinder;
import com.example.covid_19track.ui.home.HomeFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FetchDataInBackground extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog.Builder aBuilder;


    public FetchDataInBackground(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        String date = dateFormat.format(calendar.getTime());

        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=" + "440034" + "&date=" + date;

        InputStream inputStream;


        try {
            URL urlClass = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlClass.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuilder = new StringBuilder();

            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");

            }
            inputStream.close();
            reader.close();
            return stringBuilder.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {


        }

        return null;
    }

    // all task is completed then it update the UI
    @Override
    protected void onPostExecute(String s) {
        aBuilder = new AlertDialog.Builder(context);
        aBuilder.setTitle("Congratulation");
        aBuilder.setMessage("Data Fetched Successfully!!...");

        aBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });

//        if (s != null) {
//            aBuilder.create().show();
//
//            SplashActivity.tv.setText(s);
//
//        } else {
//            Toast.makeText(context, "Check internet connection", Toast.LENGTH_SHORT).show();
//
//        }


    }
}
