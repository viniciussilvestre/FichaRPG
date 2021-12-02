package com.ecnav.ficharpg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.ui.featureinfo.FeatureInfo;
import com.ecnav.ficharpg.util.Util;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterClassesInfo extends RecyclerView.Adapter<RecyclerViewAdapterClassesInfo.ViewHolderClassesInfo>
{
    private OnContactClickListener onContactClickListener;
    private List<Feature> featureList;
    private Context context;
    private int flag;
    private static Feature removedFeature;

    public RecyclerViewAdapterClassesInfo (List<Feature> featureList, Context context, OnContactClickListener onContactClickListener, int flag)
    {
        this.featureList = featureList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
        this.flag = flag;
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
        holder.deleteButton.setVisibility(View.GONE);
        if (flag == Util.SHOW_FEATURE_IN_CHARACTER)
        {
            holder.featureDescription.setVisibility(View.GONE);
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(view ->
            {
                removedFeature = feature;
                featureList.remove(feature);
                notifyItemRemoved(holder.getAdapterPosition());
                FeatureInfo.removeFeature(removedFeature);
            });

            holder.featureName.setOnClickListener(v ->
            {
                if (holder.featureDescription.getVisibility() == View.GONE)
                {
                    holder.featureDescription.setVisibility(View.VISIBLE);
                }
                else
                {
                    holder.featureDescription.setVisibility(View.GONE);
                }
            });
        }
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
        public ImageButton deleteButton;

        public ViewHolderClassesInfo(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            featureName = itemView.findViewById(R.id.nameFeatureText);
            featureDescription = itemView.findViewById(R.id.descriptionText);
            deleteButton = itemView.findViewById(R.id.deleteButton);
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
