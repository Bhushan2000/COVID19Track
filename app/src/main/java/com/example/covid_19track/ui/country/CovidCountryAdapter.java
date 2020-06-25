package com.example.covid_19track.ui.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.covid_19track.R;

import java.util.ArrayList;
import java.util.List;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> implements Filterable {
    private List<CovidCountry> covidCountries;
    private List<CovidCountry>covidCountriesFull;

    private Context context;

    public CovidCountryAdapter(List<CovidCountry> covidCountries, Context context) {
        this.covidCountries = covidCountries;
        this.context = context;
        covidCountriesFull = new ArrayList<>(covidCountries);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_covid_country, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CovidCountry covidCountry = covidCountries.get(position);
        holder.tvtotalCases.setText(Integer.toString(covidCountry.getmCases()));
        holder.tvCountryName.setText(covidCountry.getmCovidCountry());

        Glide
                .with(context)
                .load(covidCountry.getmFlag())
                .apply(new RequestOptions())
                .override(240,160)
                .into(holder.imgCountryFlag);

    }

    @Override
    public int getItemCount() {
        return covidCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvtotalCases, tvCountryName;
        ImageView imgCountryFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvtotalCases = itemView.findViewById(R.id.tvTotalCases);
            tvCountryName = itemView.findViewById(R.id.tvCountryNames);
            imgCountryFlag = itemView.findViewById(R.id.imgFlag);
        }
    }

    @Override
    public Filter getFilter() {
        return covidCountriesFilter;
    }
    private Filter covidCountriesFilter  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CovidCountry> filteredCovidCountry = new ArrayList<>();
            if(constraint == null || constraint.length()==0){
                filteredCovidCountry.addAll(covidCountriesFull);

            }else{
                String filterPattern =  constraint.toString().toLowerCase().trim();
                for(CovidCountry itemCovidCountry:covidCountriesFull){
                    if(itemCovidCountry.getmCovidCountry().toLowerCase().contains(filterPattern)){
                        filteredCovidCountry.add(itemCovidCountry);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredCovidCountry;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            covidCountries.clear();
            covidCountries.addAll((List)results.values);
            notifyDataSetChanged();
        }
    };
}