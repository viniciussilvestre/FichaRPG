package com.ecnav.ficharpg.ui.addthings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityAddEquipmentBinding;
import com.ecnav.ficharpg.util.EquipmentType;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

import java.util.Objects;

public class AddEquipment extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private ActivityAddEquipmentBinding binding;
    private EquipmentType equipmentType = EquipmentType.NOTHING;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_equipment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.equipTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(this);

        binding.saveButton.setOnClickListener(view ->
        {
            if (!binding.equipmentName.getText().toString().isEmpty() && !binding.equipDescriptionEditText.getText().toString().isEmpty() && equipmentType != EquipmentType.NOTHING)
            {
                Intent replyIntent = new Intent();
                String equipName = binding.equipmentName.getText().toString();
                String equipDescription = binding.equipDescriptionEditText.getText().toString();
                replyIntent.putExtra(Util.EQUIP_NAME, equipName);
                replyIntent.putExtra(Util.EQUIP_DESCRIPTION, equipDescription);
                if (equipmentType == EquipmentType.HEAVY_ARMOR || equipmentType == EquipmentType.LIGHT_ARMOR || equipmentType == EquipmentType.MEDIUM_ARMOR)
                {
                    int armorClass = Integer.parseInt(binding.armorClassEditText.getText().toString());
                    int usages = Integer.parseInt(binding.usagesEditText.getText().toString());
                    replyIntent.putExtra(Util.EQUIP_TYPE, equipmentType);
                    replyIntent.putExtra(Util.ARMORCLASS_REPLY, armorClass);
                    replyIntent.putExtra(Util.EQUIP_USAGES, usages);
                }
                else if (equipmentType == EquipmentType.WEAPON)
                {
                    int attackBonus = Integer.parseInt(binding.attackBonusEditText.getText().toString());
                    int damageBonus = Integer.parseInt(binding.damageBonusEditText.getText().toString());
                    int usages = Integer.parseInt(binding.usagesEditText.getText().toString());
                    replyIntent.putExtra(Util.EQUIP_TYPE, equipmentType);
                    replyIntent.putExtra(Util.EQUIP_ATTACK, attackBonus);
                    replyIntent.putExtra(Util.EQUIP_DAMAGE, damageBonus);
                    replyIntent.putExtra(Util.EQUIP_USAGES, usages);
                }
                else if (equipmentType == EquipmentType.SHIELD)
                {
                    int armorClass = Integer.parseInt(binding.armorClassEditText.getText().toString());
                    int usages = Integer.parseInt(binding.usagesEditText.getText().toString());
                    replyIntent.putExtra(Util.EQUIP_TYPE, equipmentType);
                    replyIntent.putExtra(Util.ARMORCLASS_REPLY, armorClass);
                    replyIntent.putExtra(Util.EQUIP_USAGES, usages);
                }
                else if (equipmentType == EquipmentType.CONSUMABLE)
                {
                    int amount = Integer.parseInt(binding.usagesEditText.getText().toString());
                    replyIntent.putExtra(Util.EQUIP_TYPE, equipmentType);
                    replyIntent.putExtra(Util.EQUIP_AMOUNT, amount);
                }
                else if (equipmentType == EquipmentType.AMMO)
                {
                    int amount = Integer.parseInt(binding.usagesEditText.getText().toString());
                    replyIntent.putExtra(Util.EQUIP_TYPE, equipmentType);
                    replyIntent.putExtra(Util.EQUIP_AMOUNT, amount);
                }
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        String equipType = (String) parent.getItemAtPosition(position);
        switch (equipType)
        {
            case "Select type":
            {
                break;
            }
            case "Heavy armor":
            {
                equipmentType = EquipmentType.HEAVY_ARMOR;
                break;
            }
            case "Medium armor":
            {
                equipmentType = EquipmentType.MEDIUM_ARMOR;
                break;
            }
            case "Light armor":
            {
                equipmentType = EquipmentType.LIGHT_ARMOR;
                break;
            }
            case "Weapon":
            {
                equipmentType = EquipmentType.WEAPON;
                break;
            }
            case "Shield":
            {
                equipmentType = EquipmentType.SHIELD;
                break;
            }
            case "Consumable":
            {
                equipmentType = EquipmentType.CONSUMABLE;
                break;
            }
            case "Ammo":
            {
                equipmentType = EquipmentType.AMMO;
                break;
            }
        }
        if (equipmentType == EquipmentType.HEAVY_ARMOR || equipmentType == EquipmentType.LIGHT_ARMOR || equipmentType == EquipmentType.MEDIUM_ARMOR)
        {
            binding.attackBonusEditText.setVisibility(View.GONE);
            binding.damageBonusEditText.setVisibility(View.GONE);
            binding.amountEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.VISIBLE);
            binding.usagesEditText.setVisibility(View.VISIBLE);
        }
        else if (equipmentType == EquipmentType.WEAPON)
        {
            binding.amountEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.GONE);
            binding.attackBonusEditText.setVisibility(View.VISIBLE);
            binding.damageBonusEditText.setVisibility(View.VISIBLE);
            binding.usagesEditText.setVisibility(View.VISIBLE);
        }
        else if (equipmentType == EquipmentType.SHIELD)
        {
            binding.amountEditText.setVisibility(View.GONE);
            binding.attackBonusEditText.setVisibility(View.GONE);
            binding.damageBonusEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.VISIBLE);
            binding.usagesEditText.setVisibility(View.VISIBLE);
        }
        else if (equipmentType == EquipmentType.CONSUMABLE)
        {
            binding.amountEditText.setVisibility(View.VISIBLE);
            binding.attackBonusEditText.setVisibility(View.GONE);
            binding.damageBonusEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.GONE);
            binding.usagesEditText.setVisibility(View.GONE);
        }
        else if (equipmentType == EquipmentType.AMMO)
        {
            binding.amountEditText.setVisibility(View.VISIBLE);
            binding.attackBonusEditText.setVisibility(View.GONE);
            binding.damageBonusEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.GONE);
            binding.usagesEditText.setVisibility(View.GONE);
        }
        else
        {
            binding.amountEditText.setVisibility(View.GONE);
            binding.attackBonusEditText.setVisibility(View.GONE);
            binding.damageBonusEditText.setVisibility(View.GONE);
            binding.armorClassEditText.setVisibility(View.GONE);
            binding.usagesEditText.setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}