package com.example.covid_19track.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.toolbox.StringRequest;

public class CovidCountry implements Parcelable {

    String mCovidCountry;
    int  mCases;
    String mTodayCases, mDeaths, mTodayDeaths, mRecovered, mActive, mCritical, mFlag;
    String continent;
    int tests,population;
    int todayRecovered;



//    public CovidCountry(String mCovidCountry, int mCases, String mTodayCases, String mDeaths, String mTodayDeaths, String mRecovered, String mActive, String mCritical, String mFlag) {
//        this.mCovidCountry = mCovidCountry;
//        this.mCases = mCases;
//        this.mTodayCases = mTodayCases;
//        this.mDeaths = mDeaths;
//        this.mTodayDeaths = mTodayDeaths;
//        this.mRecovered = mRecovered;
//        this.mActive = mActive;
//        this.mCritical = mCritical;
//        this.mFlag = mFlag;
//    }


    public CovidCountry(String mCovidCountry, int mCases, String mTodayCases, String mDeaths, String mTodayDeaths, String mRecovered, String mActive, String mCritical, String mFlag, String continent, int tests, int population, int todayRecovered) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeaths = mDeaths;
        this.mTodayDeaths = mTodayDeaths;
        this.mRecovered = mRecovered;
        this.mActive = mActive;
        this.mCritical = mCritical;
        this.mFlag = mFlag;
        this.continent = continent;
        this.tests = tests;
        this.population = population;
        this.todayRecovered = todayRecovered;
    }

    protected CovidCountry(Parcel in) {
        mCovidCountry = in.readString();
        mCases = in.readInt();
        mTodayCases = in.readString();
        mDeaths = in.readString();
        mTodayDeaths = in.readString();
        mRecovered = in.readString();
        mActive = in.readString();
        mCritical = in.readString();
        mFlag = in.readString();
        continent = in.readString();
        tests = in.readInt();
        population = in.readInt();
        todayRecovered = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCovidCountry);
        dest.writeInt(mCases);
        dest.writeString(mTodayCases);
        dest.writeString(mDeaths);
        dest.writeString(mTodayDeaths);
        dest.writeString(mRecovered);
        dest.writeString(mActive);
        dest.writeString(mCritical);
        dest.writeString(mFlag);
        dest.writeString(continent);
        dest.writeInt(tests);
        dest.writeInt(population);
        dest.writeInt(todayRecovered);


    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CovidCountry> CREATOR = new Creator<CovidCountry>() {
        @Override
        public CovidCountry createFromParcel(Parcel in) {
            return new CovidCountry(in);
        }

        @Override
        public CovidCountry[] newArray(int size) {
            return new CovidCountry[size];
        }
    };

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public int getmCases() {
        return mCases;
    }

    public String getmTodayCases() {
        return mTodayCases;
    }

    public String getmDeaths() {
        return mDeaths;
    }

    public String getmTodayDeaths() {
        return mTodayDeaths;
    }

    public String getmRecovered() {
        return mRecovered;
    }

    public String getmActive() {
        return mActive;
    }

    public String getmCritical() {
        return mCritical;
    }

    public String getmFlag() {
        return mFlag;
    }

    public String getContinent() {
        return continent;
    }

    public int getTests() {
        return tests;
    }

    public int getPopulation() {
        return population;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }
}