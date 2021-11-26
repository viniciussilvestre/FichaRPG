package com.ecnav.ficharpg.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.model.Spell;
import com.ecnav.ficharpg.ui.spellsinfo.SpellsInfo;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterSpell extends RecyclerView.Adapter<RecyclerViewAdapterSpell.ViewHolderSpell>
{
    private OnContactClickListener contactClickListener;
    private List<Spell> spellList;
    private Context context;
    private static Spell removedSpell;

    public RecyclerViewAdapterSpell(List<Spell> spellList, Context context, OnContactClickListener contactClickListener)
    {
        this.contactClickListener = contactClickListener;
        this.spellList = spellList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderSpell onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spell_row_info, parent, false);
        return new ViewHolderSpell(view, contactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSpell holder, int position)
    {
        Spell spell = Objects.requireNonNull(spellList.get(position));
        String spellName = spell.getSpellName();
        String spellDescription = spell.getSpellDescription();
        holder.spellName.setText(spellName);
        holder.spellDescription.setText(spellDescription);
        boolean somatic = spell.isSomatic();
        boolean verbal = spell.isVerbal();
        boolean material = spell.isMaterial();
        String materialComponents = spell.getMaterialComponents();
        holder.somaticRadioButton.setChecked(somatic);
        holder.verbalRadioButton.setChecked(verbal);
        holder.materialRadioButton.setChecked(material);
        holder.materialComponents.setText(materialComponents);
        holder.deleteButton.setOnClickListener(view ->
        {
            removedSpell = spell;
            spellList.remove(spell);
            notifyItemRemoved(holder.getAdapterPosition());
            SpellsInfo.removeSpell(removedSpell);
        });
    }

    @Override
    public int getItemCount()
    {
        return spellList.size();
    }

    public static class ViewHolderSpell extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView spellName;
        public ImageButton deleteButton;
        public RadioButton somaticRadioButton;
        public TextView somaticComponents;
        public RadioButton verbalRadioButton;
        public TextView verbalComponents;
        public RadioButton materialRadioButton;
        public TextView materialComponents;
        public TextView spellDescription;

        public ViewHolderSpell(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            spellName = itemView.findViewById(R.id.nameSpellText);
            deleteButton = itemView.findViewById(R.id.deleteButtonSpell);
            somaticRadioButton = itemView.findViewById(R.id.somaticRadioButton);
            somaticComponents = itemView.findViewById(R.id.somaticComponents);
            verbalRadioButton = itemView.findViewById(R.id.verbalRadioButton);
            verbalComponents = itemView.findViewById(R.id.verbalComponents);
            materialRadioButton = itemView.findViewById(R.id.materialRadioButton);
            materialComponents = itemView.findViewById(R.id.materialComponents);
            spellDescription = itemView.findViewById(R.id.descriptionText);
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
