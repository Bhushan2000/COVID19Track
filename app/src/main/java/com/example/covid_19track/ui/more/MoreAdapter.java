package com.example.covid_19track.ui.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid_19track.R;
import com.example.covid_19track.ui.vaccine.VaccineAdapter;
import com.example.covid_19track.ui.vaccine.VaccineModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.MoreVH> {

    Context context;
    List<MoreModel> moreModelList;
    int lastPosition = -1;

    public MoreAdapter(Context context, List<MoreModel> moreModelList) {
        this.context = context;
        this.moreModelList = moreModelList;
    }

    @NonNull
    @NotNull
    @Override
    public MoreVH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_item_layout, parent, false);
        return new MoreVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MoreAdapter.MoreVH holder, int position) {
        MoreModel moreModel = moreModelList.get(position);

        holder.imageView.setImageResource(moreModel.getImageId());
        holder.textView.setText(moreModel.getSymptom_name());



        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_animation_fall_down);
            holder.itemView.setAnimation(animation);
            lastPosition = position;

        }
    }

    @Override
    public int getItemCount() {
        return moreModelList.size();
    }

    class MoreVH extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;


        public MoreVH(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.symptomImage);
            textView = itemView.findViewById(R.id.tvSymName);

        }
    }
}
