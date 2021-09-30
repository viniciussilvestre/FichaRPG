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
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.util.Util;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>
{
    private OnContactClickListener contactClickListener;
    private List<SheetDAndD> sheetsList;
    private Context context;

    public RecyclerViewAdapter(List<SheetDAndD> sheetsList, Context context, OnContactClickListener onContactClickListener)
    {
        this.sheetsList = sheetsList;
        this.context = context;
        this.contactClickListener = onContactClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheet_row, parent, false);
        return new ViewHolder(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        SheetDAndD sheet = Objects.requireNonNull(sheetsList.get(position));
        String characterName = sheet.getName();
        String characterClass = sheet.getCharacterClass();
        int level = sheet.getLevel();
        holder.name.setText(characterName);
        holder.characterClass.setText(characterClass);
        holder.characterLevel.setText(String.valueOf(level));
        holder.sheetLogo.setImageResource(R.drawable.dungeons_and_dragons_logo);
    }

    @Override
    public int getItemCount()
    {
        return Objects.requireNonNull(sheetsList.size());
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView name;
        public TextView characterClass;
        public TextView characterLevel;
        public ImageView sheetLogo;

        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            name = itemView.findViewById(R.id.rowNameCharacter);
            characterClass = itemView.findViewById(R.id.rowCharacterClass);
            characterLevel = itemView.findViewById(R.id.rowCharacterLevel);
            sheetLogo = itemView.findViewById(R.id.sheetGameType);
            this.onContactClickListener = onContactClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener
    {
        void onContactClick(int position);
    }
}
