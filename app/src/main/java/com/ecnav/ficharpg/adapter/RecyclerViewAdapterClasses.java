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
import com.ecnav.ficharpg.model.Classes;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterClasses extends RecyclerView.Adapter<RecyclerViewAdapterClasses.ViewHolderClasses>
{
    private OnContactClickListener contactClickListener;
    private List<Classes> classesList;
    private Context context;

    public RecyclerViewAdapterClasses(List<Classes> classesList, Context context, OnContactClickListener contactClickListener)
    {
        this.classesList = classesList;
        this.context = context;
        this.contactClickListener = contactClickListener;
    }

    @NonNull
    @Override
    public ViewHolderClasses onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_row, parent, false);
        return new ViewHolderClasses(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClasses holder, int position)
    {
        Classes features = Objects.requireNonNull(classesList.get(position));
        String className = features.getClassName();
        holder.className.setText(className);
        holder.gameLogo.setImageResource(R.drawable.dungeons_and_dragons_logo);
    }

    @Override
    public int getItemCount()
    {
        return classesList.size();
    }

    public static class ViewHolderClasses extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView className;
        public ImageView classLogo;
        public ImageView gameLogo;

        public ViewHolderClasses(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            className = itemView.findViewById(R.id.rowClassName);
            classLogo = itemView.findViewById(R.id.classLogo);
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
