package com.example.covid_19track.ui.vaccine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19track.R;
import com.example.covid_19track.ui.country.CovidCountryAdapter;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VaccineVH> {
    private List<VaccineModel> vaccineModelList;
    private Context context;
    int lastPosition = -1;

    public VaccineAdapter(List<VaccineModel> vaccineModelList, Context context) {
        this.vaccineModelList = vaccineModelList;
        this.context = context;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public VaccineVH onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.center_rv_item, parent, false);
        return new VaccineVH(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull VaccineAdapter.VaccineVH holder, int position) {
// on below line we are getting item
        // from our list along with its position.
        VaccineModel currentItem = vaccineModelList.get(position);

        // after getting current item we are setting
        // data from our list to our text views.
        holder.centerNameTV.setText(currentItem.centerName);

        holder.centerAddressTV.setText(currentItem.centerAddress);
        holder.centerTimings.setText("From : " + currentItem.centerFromTime + " To : " + currentItem.centerToTime);
        holder.vaccineNameTV.setText(currentItem.vaccineName);
        holder.centerAgeLimitTV.setText( "Age Limit : " + currentItem.ageLimit) ;
        holder.centerFeeTypeTV.setText(currentItem.fee_type);
        holder.avalabilityTV.setText("Availability : " + currentItem.availableCapacity);


        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_animation_fall_down);
            holder.itemView.setAnimation(animation);
            lastPosition = position;

        }
    }

    @Override
    public int getItemCount() {
        return vaccineModelList.size();
    }

    class VaccineVH extends RecyclerView.ViewHolder {
        TextView centerNameTV;
        TextView centerAddressTV;
        TextView centerTimings;
        TextView vaccineNameTV;
        TextView centerAgeLimitTV;
        TextView centerFeeTypeTV;
        TextView avalabilityTV;

        public VaccineVH(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            // on below line we are initializing all our text views along with  its ids.
             centerNameTV = itemView.findViewById(R.id.idTVCenterName);
             centerAddressTV = itemView.findViewById(R.id.idTVCenterAddress);
             centerTimings = itemView.findViewById(R.id.idTVCenterTimings);
             vaccineNameTV = itemView.findViewById(R.id.idTVVaccineName);
             centerAgeLimitTV = itemView.findViewById(R.id.idTVAgeLimit);
             centerFeeTypeTV = itemView.findViewById(R.id.idTVFeeType);
             avalabilityTV = itemView.findViewById(R.id.idTVAvaliablity);
        }
    }
}
