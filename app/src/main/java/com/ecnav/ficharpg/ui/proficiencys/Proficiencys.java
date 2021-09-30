package com.ecnav.ficharpg.ui.proficiencys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.databinding.FragmentProficiencysBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

public class Proficiencys extends Fragment
{
    private FragmentProficiencysBinding binding;
    private SheetViewModel sheetViewModel;
    private int parameterReceived;
    private int id;
    private SheetDAndD sheetDAndD;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            parameterReceived = getArguments().getInt(Util.PARAMETER_FOR_FRAGMENT, 0);
        }
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(Proficiencys.this.requireActivity().getApplication()).create(SheetViewModel.class);
        id = parameterReceived;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentProficiencysBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                int strValue = (sheet.getStrength() - 10)/2;
                int dexValue = (sheet.getDexterity() - 10)/2;
                int conValue = (sheet.getConstitution() - 10)/2;
                int intValue = (sheet.getIntelligence() - 10)/2;
                int wisValue = (sheet.getWisdom() - 10)/2;
                int chaValue = (sheet.getCharisma() - 10)/2;
                int acrobaticsValue = dexValue;
                int stealthValue = dexValue;
                int sleightHandValue = dexValue;
                setProficiencyBonusAuto(sheet.getLevel());
                if (sheet.isStrengthSaveProficiency())
                {
                    strValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isDexteritySaveProficiency())
                {
                    dexValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isConstitutionSaveProficiency())
                {
                    conValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isIntelligenceSaveProficiency())
                {
                    conValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isWisdomSaveProficiency())
                {
                    conValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isCharismaSaveProficiency())
                {
                    chaValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isAcrobaticsProficiency())
                {
                    acrobaticsValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isStealthProficiency())
                {
                    stealthValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                if (sheet.isSleightOfHandProficiency())
                {
                    sleightHandValue += Integer.parseInt(binding.proficiencyText.getText().toString());
                }
                binding.strSavingValue.setText(String.valueOf(strValue));
                binding.dexSavingValue.setText(String.valueOf(dexValue));
                binding.conSavingValue.setText(String.valueOf(conValue));
                binding.intSavingValue.setText(String.valueOf(intValue));
                binding.wisSavingValue.setText(String.valueOf(wisValue));
                binding.chaSavingValue.setText(String.valueOf(chaValue));
                binding.acrobaticsValue.setText(String.valueOf(acrobaticsValue));
                binding.stealthValue.setText(String.valueOf(stealthValue));
                binding.sleightHandValue.setText(String.valueOf(sleightHandValue));
                binding.strSavingThrow.setChecked(sheet.isStrengthSaveProficiency());
                binding.dexSavingThrow.setChecked(sheet.isDexteritySaveProficiency());
                binding.conSavingThrow.setChecked(sheet.isConstitutionSaveProficiency());
                binding.intSavingThrow.setChecked(sheet.isIntelligenceSaveProficiency());
                binding.wisSavingThrow.setChecked(sheet.isWisdomSaveProficiency());
                binding.chaSavingThrow.setChecked(sheet.isCharismaSaveProficiency());
                binding.acrobaticsSkill.setChecked(sheet.isAcrobaticsProficiency());
                binding.stealthSkill.setChecked(sheet.isStealthProficiency());
                binding.sleighHandSkill.setChecked(sheet.isSleightOfHandProficiency());
            }
        });

        binding.strSavingThrow.setOnClickListener(view ->
        {
            int strValue = (this.sheetDAndD.getStrength() - 10)/2;
            if (binding.strSavingThrow.isChecked())
            {
                strValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.strSavingValue.setText(String.valueOf(strValue));
        });

        binding.dexSavingThrow.setOnClickListener(view ->
        {
            int dexValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.dexSavingThrow.isChecked())
            {
                dexValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.dexSavingValue.setText(String.valueOf(dexValue));
        });

        binding.conSavingThrow.setOnClickListener(view ->
        {
            int conValue = (this.sheetDAndD.getConstitution() - 10)/2;
            if (binding.conSavingThrow.isChecked())
            {
                conValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.conSavingValue.setText(String.valueOf(conValue));
        });

        binding.intSavingThrow.setOnClickListener(view ->
        {
            int intValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.intSavingThrow.isChecked())
            {
                intValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.intSavingValue.setText(String.valueOf(intValue));
        });

        binding.wisSavingThrow.setOnClickListener(view ->
        {
            int wisValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.wisSavingThrow.isChecked())
            {
                wisValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.wisSavingValue.setText(String.valueOf(wisValue));
        });

        binding.chaSavingThrow.setOnClickListener(view ->
        {
            int chaValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.chaSavingThrow.isChecked())
            {
                chaValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.chaSavingValue.setText(String.valueOf(chaValue));
        });

        binding.acrobaticsSkill.setOnClickListener(view ->
        {
            int acrobaticsValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.acrobaticsSkill.isChecked())
            {
                acrobaticsValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.acrobaticsValue.setText(String.valueOf(acrobaticsValue));
        });

        binding.stealthSkill.setOnClickListener(view ->
        {
            int stealthValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.stealthSkill.isChecked())
            {
                stealthValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.stealthValue.setText(String.valueOf(stealthValue));
        });

        binding.sleighHandSkill.setOnClickListener(view ->
        {
            int sleightHandValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.sleighHandSkill.isChecked())
            {
                sleightHandValue += Integer.parseInt(binding.proficiencyText.getText().toString());
            }
            binding.sleightHandValue.setText(String.valueOf(sleightHandValue));
        });
        return root;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        SheetDAndD sheetDAndD = getNewSheetData();
        SheetViewModel.updateDnd(sheetDAndD);
    }

    public void setProficiencyBonusAuto(int level)
    {
        if (level == 1 || level == 2 || level == 3 || level == 4)
        {
            binding.proficiencyText.setText(String.valueOf(2));
        }
        if (level == 5 || level == 6 || level == 7 || level == 8)
        {
            binding.proficiencyText.setText(String.valueOf(3));
        }
        if (level == 9 || level == 10 || level == 11 || level == 12)
        {
            binding.proficiencyText.setText(String.valueOf(4));
        }
        if (level == 13 || level == 14 || level == 15 || level == 16)
        {
            binding.proficiencyText.setText(String.valueOf(5));
        }
        if (level == 17 || level == 18 || level == 19 || level == 20)
        {
            binding.proficiencyText.setText(String.valueOf(6));
        }
    }

    public SheetDAndD getNewSheetData()
    {
        SheetDAndD sheetDAndD = new SheetDAndD();
        sheetDAndD.setId(id);
        sheetDAndD.setStrengthSaveProficiency(binding.strSavingThrow.isChecked());
        sheetDAndD.setDexteritySaveProficiency(binding.dexSavingThrow.isChecked());
        sheetDAndD.setConstitutionSaveProficiency(binding.conSavingThrow.isChecked());
        sheetDAndD.setIntelligenceSaveProficiency(binding.intSavingThrow.isChecked());
        sheetDAndD.setWisdomSaveProficiency(binding.wisSavingThrow.isChecked());
        sheetDAndD.setCharismaSaveProficiency(binding.chaSavingThrow.isChecked());
        sheetDAndD.setAcrobaticsProficiency(binding.acrobaticsSkill.isChecked());
        sheetDAndD.setStealthProficiency(binding.stealthSkill.isChecked());
        sheetDAndD.setSleightOfHandProficiency(binding.sleighHandSkill.isChecked());
        sheetDAndD.setName(this.sheetDAndD.getName());
        sheetDAndD.setCharacterClass(this.sheetDAndD.getCharacterClass());
        sheetDAndD.setSpeed(this.sheetDAndD.getSpeed());
        sheetDAndD.setArmorClass(this.sheetDAndD.getArmorClass());
        sheetDAndD.setLevel(this.sheetDAndD.getLevel());
        sheetDAndD.setRace(this.sheetDAndD.getRace());
        sheetDAndD.setBackground(this.sheetDAndD.getBackground());
        sheetDAndD.setAlignment(this.sheetDAndD.getAlignment());
        sheetDAndD.setInitiative(this.sheetDAndD.getInitiative());
        sheetDAndD.setArmorClass(this.sheetDAndD.getArmorClass());
        sheetDAndD.setHitPoints(this.sheetDAndD.getHitPoints());
        sheetDAndD.setStrength(this.sheetDAndD.getStrength());
        sheetDAndD.setDexterity(this.sheetDAndD.getDexterity());
        sheetDAndD.setConstitution(this.sheetDAndD.getConstitution());
        sheetDAndD.setIntelligence(this.sheetDAndD.getIntelligence());
        sheetDAndD.setWisdom(this.sheetDAndD.getWisdom());
        sheetDAndD.setCharisma(this.sheetDAndD.getCharisma());
        return sheetDAndD;
    }
}