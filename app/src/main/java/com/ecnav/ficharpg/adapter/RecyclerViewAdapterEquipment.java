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
import com.ecnav.ficharpg.model.Equipment;
import com.ecnav.ficharpg.util.EquipmentType;

import java.util.List;
import java.util.Objects;

public class RecyclerViewAdapterEquipment extends RecyclerView.Adapter<RecyclerViewAdapterEquipment.ViewHolderEquipment>
{
    private OnContactClickListener onContactClickListener;
    private List<Equipment> equipmentList;
    private Context context;

    public RecyclerViewAdapterEquipment(List<Equipment> equipmentList, Context context, OnContactClickListener onContactClickListener)
    {
        this.onContactClickListener = onContactClickListener;
        this.equipmentList = equipmentList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderEquipment onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.equipment_row, parent, false);
        return new ViewHolderEquipment(view, onContactClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderEquipment holder, int position)
    {
        Equipment equipment = Objects.requireNonNull(equipmentList.get(position));
        String equipName = equipment.getNome();
        String equipDescription = equipment.getDescription();
        EquipmentType equipmentType = equipment.getEquipmentType();
        holder.equipName.setText(equipName);
        holder.equipDescription.setText(equipDescription);
        if (equipmentType == EquipmentType.HEAVY_ARMOR || equipmentType == EquipmentType.LIGHT_ARMOR || equipmentType == EquipmentType.MEDIUM_ARMOR)
        {
            holder.armorClass.setVisibility(View.VISIBLE);
            holder.weight.setVisibility(View.GONE);
            holder.amount.setVisibility(View.GONE);
            holder.damageBonus.setVisibility(View.GONE);
            holder.attackBonus.setVisibility(View.GONE);
            holder.usages.setVisibility(View.GONE);
            StringBuilder armorClassString = new StringBuilder();
            armorClassString.append("Armor class: ").append(equipment.getArmorClass());
            holder.armorClass.setText(armorClassString);
            if (equipment.getUsages() != 0)
            {
                holder.usages.setVisibility(View.VISIBLE);
                StringBuilder usagesString = new StringBuilder();
                usagesString.append("Usages: ").append(equipment.getUsages());
                holder.usages.setText(usagesString);
            }
            if (equipment.getWeight() != 0)
            {
                holder.weight.setVisibility(View.VISIBLE);
                StringBuilder weightString = new StringBuilder();
                weightString.append("Weight: ").append(equipment.getWeight());
                holder.weight.setText(weightString);
            }
        }
        else if (equipmentType == EquipmentType.WEAPON)
        {
            holder.armorClass.setVisibility(View.GONE);
            holder.weight.setVisibility(View.GONE);
            holder.amount.setVisibility(View.GONE);
            holder.damageBonus.setVisibility(View.VISIBLE);
            holder.attackBonus.setVisibility(View.VISIBLE);
            holder.usages.setVisibility(View.GONE);
            StringBuilder attackBonusString = new StringBuilder();
            attackBonusString.append("Attack bonus: ").append(equipment.getAttackDiceBonus());
            holder.attackBonus.setText(attackBonusString);
            StringBuilder damageBonusString = new StringBuilder();
            damageBonusString.append("Damage bonus: ").append(equipment.getDamageDiceBonus());
            holder.damageBonus.setText(damageBonusString);
            if (equipment.getUsages() != 0)
            {
                holder.usages.setVisibility(View.VISIBLE);
                StringBuilder usagesString = new StringBuilder();
                usagesString.append("Usages: ").append(equipment.getUsages());
                holder.usages.setText(usagesString);
            }
            if (equipment.getWeight() != 0)
            {
                holder.weight.setVisibility(View.VISIBLE);
                StringBuilder weightString = new StringBuilder();
                weightString.append("Weight: ").append(equipment.getWeight());
                holder.weight.setText(weightString);
            }
        }
        else if (equipmentType == EquipmentType.SHIELD)
        {
            holder.armorClass.setVisibility(View.VISIBLE);
            holder.weight.setVisibility(View.GONE);
            holder.amount.setVisibility(View.GONE);
            holder.damageBonus.setVisibility(View.GONE);
            holder.attackBonus.setVisibility(View.GONE);
            holder.usages.setVisibility(View.GONE);
            holder.armorClass.setText(String.valueOf(equipment.getArmorClass()));
            if (equipment.getUsages() != 0)
            {
                holder.usages.setVisibility(View.VISIBLE);
                StringBuilder usagesString = new StringBuilder();
                usagesString.append("Usages: ").append(equipment.getUsages());
                holder.usages.setText(usagesString);
            }
            if (equipment.getWeight() != 0)
            {
                holder.weight.setVisibility(View.VISIBLE);
                StringBuilder weightString = new StringBuilder();
                weightString.append("Weight: ").append(equipment.getWeight());
                holder.weight.setText(weightString);
            }
        }
        else if (equipmentType == EquipmentType.CONSUMABLE || equipmentType == EquipmentType.AMMO)
        {
            holder.armorClass.setVisibility(View.GONE);
            holder.weight.setVisibility(View.GONE);
            holder.amount.setVisibility(View.VISIBLE);
            holder.damageBonus.setVisibility(View.GONE);
            holder.attackBonus.setVisibility(View.GONE);
            holder.usages.setVisibility(View.GONE);
            holder.weight.setText(String.valueOf(equipment.getWeight()));
            holder.amount.setText(String.valueOf(equipment.getAmount()));
            if (equipment.getWeight() != 0)
            {
                holder.weight.setVisibility(View.VISIBLE);
                StringBuilder weightString = new StringBuilder();
                weightString.append("Weight: ").append(equipment.getWeight());
                holder.weight.setText(weightString);
            }
        }
        holder.deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                equipmentList.remove(equipment);
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return equipmentList.size();
    }

    public static class ViewHolderEquipment extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        OnContactClickListener onContactClickListener;
        public TextView equipName;
        public TextView equipDescription;
        public TextView armorClass;
        public TextView attackBonus;
        public TextView damageBonus;
        public TextView usages;
        public TextView weight;
        public TextView amount;
        public ImageButton deleteButton;

        public ViewHolderEquipment(@NonNull View itemView, OnContactClickListener onContactClickListener)
        {
            super(itemView);
            equipName = itemView.findViewById(R.id.equipName);
            equipDescription = itemView.findViewById(R.id.equipDescription);
            armorClass = itemView.findViewById(R.id.armorClass);
            attackBonus = itemView.findViewById(R.id.attackBonus);
            damageBonus = itemView.findViewById(R.id.damageBonus);
            usages = itemView.findViewById(R.id.usages);
            weight = itemView.findViewById(R.id.weight);
            amount = itemView.findViewById(R.id.amount);
            deleteButton = itemView.findViewById(R.id.deleteButton);
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
