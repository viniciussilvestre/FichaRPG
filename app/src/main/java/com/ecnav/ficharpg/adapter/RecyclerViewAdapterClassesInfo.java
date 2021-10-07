package com.ecnav.ficharpg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.model.Feature;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterClassesInfo extends RecyclerView.Adapter<RecyclerViewAdapterClassesInfo.ViewHolderClassesInfo>
{
    private OnContactClickListener onContactClickListener;
    private List<Feature> featureList;
    private Context context;

    public RecyclerViewAdapterClassesInfo (List<Feature> featureList, Context context, OnContactClickListener onContactClickListener)
    {
        this.featureList = featureList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolderClassesInfo onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_info_row, parent, false);
        return new ViewHolderClassesInfo(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClassesInfo holder, int position)
    {
        Feature feature = Objects.requireNonNull(featureList.get(position));
        String featureName = feature.getNome();
        String featureDescription = feature.getDescription();
        holder.featureName.setText(featureName);
        holder.featureDescription.setText(featureDescription);
    }

    @Override
    public int getItemCount()
    {
        return featureList.size();
    }

    public static class ViewHolderClassesInfo extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView featureName;
        public TextView featureDescription;

        public ViewHolderClassesInfo(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            featureName = itemView.findViewById(R.id.nameFeatureText);
            featureDescription = itemView.findViewById(R.id.descriptionText);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener
    {
        void onContactClick(int position);
    }
}
