package com.ecnav.ficharpg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.ui.SubclassChooser;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterSubclass extends RecyclerView.Adapter<RecyclerViewAdapterSubclass.ViewHolderSubclasses>
{
    private OnContactClickListener onContactClickListener;
    private List<Subclass> subclassList;
    private Context context;

    public RecyclerViewAdapterSubclass(List<Subclass> subclassList, Context context, OnContactClickListener onContactClickListener)
    {
        this.subclassList = subclassList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolderSubclasses onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_info_row, parent, false);
        return new ViewHolderSubclasses(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSubclasses holder, int position)
    {
        Subclass subclass = Objects.requireNonNull(subclassList.get(position));
        String subclassName = subclass.getSubclassName();
        holder.subclassName.setText(subclassName);
        holder.gameLogo.setImageResource(R.drawable.dungeons_and_dragons_logo);
    }

    @Override
    public int getItemCount()
    {
        return subclassList.size();
    }

    public static class ViewHolderSubclasses extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView subclassName;
        public ImageView subclassLogo;
        public ImageView gameLogo;

        public ViewHolderSubclasses(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            subclassName = itemView.findViewById(R.id.rowClassName);
            subclassLogo = itemView.findViewById(R.id.classLogo);
            gameLogo = itemView.findViewById(R.id.logo);
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
