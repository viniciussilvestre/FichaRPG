package com.ecnav.ficharpg.ui.proficiencys;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.databinding.FragmentProficiencysBinding;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.google.android.material.snackbar.Snackbar;

public class Proficiencys extends Fragment
{
    private FragmentProficiencysBinding binding;
    private SheetViewModel sheetViewModel;
    //private int parameterReceived;
    private int id;
    private SheetDAndD sheetDAndD;
    private int proficiencyBonus;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(Proficiencys.this.requireActivity().getApplication()).create(SheetViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentProficiencysBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                setProficiencyBonusAuto(sheet.getLevel());
                proficiencyBonus = Integer.parseInt(binding.proficiencyText.getText().toString());
                int strValue = (sheet.getStrength() - 10)/2;
                int dexValue = (sheet.getDexterity() - 10)/2;
                int conValue = (sheet.getConstitution() - 10)/2;
                int intValue = (sheet.getIntelligence() - 10)/2;
                int wisValue = (sheet.getWisdom() - 10)/2;
                int chaValue = (sheet.getCharisma() - 10)/2;
                int acrobaticsValue = dexValue;
                int stealthValue = dexValue;
                int sleightHandValue = dexValue;
                int arcanaValue = intValue;
                int historyValue = intValue;
                int investigationValue = intValue;
                int natureValue = intValue;
                int religionValue = intValue;
                int animalHandlingValue = wisValue;
                int insightValue = wisValue;
                int medicineValue = wisValue;
                int perceptionValue = wisValue;
                int survivalValue = wisValue;
                int athleticsValue = strValue;
                int deceptionValue = chaValue;
                int intimidationValue = chaValue;
                int performanceValue = chaValue;
                int persuasionValue = chaValue;

                if (sheet.isStrengthSaveProficiency())
                {
                    strValue += proficiencyBonus;
                }
                if (sheet.isDexteritySaveProficiency())
                {
                    dexValue += proficiencyBonus;
                }
                if (sheet.isConstitutionSaveProficiency())
                {
                    conValue += proficiencyBonus;
                }
                if (sheet.isIntelligenceSaveProficiency())
                {
                    intValue += proficiencyBonus;
                }
                if (sheet.isWisdomSaveProficiency())
                {
                    wisValue += proficiencyBonus;
                }
                if (sheet.isCharismaSaveProficiency())
                {
                    chaValue += proficiencyBonus;
                }
                if (sheet.isAcrobaticsProficiency())
                {
                    acrobaticsValue += proficiencyBonus;
                    if (sheet.isExpertiseAcrobaticsProficiency())
                    {
                        acrobaticsValue += proficiencyBonus;
                    }
                }
                if (sheet.isStealthProficiency())
                {
                    stealthValue += proficiencyBonus;
                    if (sheet.isExpertiseStealthProficiency())
                    {
                        stealthValue += proficiencyBonus;
                    }
                }
                if (sheet.isSleightOfHandProficiency())
                {
                    sleightHandValue += proficiencyBonus;
                    if (sheet.isExpertiseStealthProficiency())
                    {
                        sleightHandValue += proficiencyBonus;
                    }
                }
                if (sheet.isArcanaProficiency())
                {
                    arcanaValue += proficiencyBonus;
                    if (sheet.isExpertiseArcanaProficiency())
                    {
                        arcanaValue += proficiencyBonus;
                    }
                }
                if (sheet.isHistoryProficiency())
                {
                    historyValue += proficiencyBonus;
                    if (sheet.isExpertiseHistoryProficiency())
                    {
                        historyValue += proficiencyBonus;
                    }
                }
                if (sheet.isInvestigationProficiency())
                {
                    investigationValue += proficiencyBonus;
                    if (sheet.isExpertiseIntimidationProficiency())
                    {
                        investigationValue += proficiencyBonus;
                    }
                }
                if (sheet.isNatureProficiency())
                {
                    natureValue += proficiencyBonus;
                    if (sheet.isExpertiseNatureProficiency())
                    {
                        natureValue += proficiencyBonus;
                    }
                }
                if (sheet.isReligionProficiency())
                {
                    religionValue += proficiencyBonus;
                    if (sheet.isExpertiseReligionProficiency())
                    {
                        religionValue += proficiencyBonus;
                    }
                }
                if (sheet.isAnimalHandlingProficiency())
                {
                    animalHandlingValue += proficiencyBonus;
                    if (sheet.isExpertiseAnimalHandlingProficiency())
                    {
                        animalHandlingValue += proficiencyBonus;
                    }
                }
                if (sheet.isInsightProficiency())
                {
                    insightValue += proficiencyBonus;
                    if (sheet.isExpertiseInsightProficiency())
                    {
                        insightValue += proficiencyBonus;
                    }
                }
                if (sheet.isMedicineProficiency())
                {
                    medicineValue += proficiencyBonus;
                    if (sheet.isExpertiseMedicineProficiency())
                    {
                        medicineValue += proficiencyBonus;
                    }
                }
                if (sheet.isPerceptionProficiency())
                {
                    perceptionValue += proficiencyBonus;
                    if (sheet.isExpertisePerceptionProficiency())
                    {
                        perceptionValue += proficiencyBonus;
                    }
                }
                if (sheet.isSurvivalProficiency())
                {
                    survivalValue += proficiencyBonus;
                    if (sheet.isExpertiseSurvivalProficiency())
                    {
                        survivalValue += proficiencyBonus;
                    }
                }

                if (sheet.isAthleticsProficiency())
                {
                    athleticsValue += proficiencyBonus;
                    if (sheet.isExpertiseAthleticsProficiency())
                    {
                        athleticsValue += proficiencyBonus;
                    }
                }
                if (sheet.isDeceptionProficiency())
                {
                    deceptionValue += proficiencyBonus;
                    if (sheet.isExpertiseDeceptionProficiency())
                    {
                        deceptionValue += proficiencyBonus;
                    }
                }
                if (sheet.isIntimidationProficiency())
                {
                    intimidationValue += proficiencyBonus;
                    if (sheet.isExpertiseIntimidationProficiency())
                    {
                        intimidationValue += proficiencyBonus;
                    }
                }
                if (sheet.isPerformanceProficiency())
                {
                    performanceValue += proficiencyBonus;
                    if (sheet.isExpertisePerformanceProficiency())
                    {
                        performanceValue += proficiencyBonus;
                    }
                }
                if (sheet.isPersuasionProficiency())
                {
                    persuasionValue += proficiencyBonus;
                    if (sheet.isExpertisePersuasionProficiency())
                    {
                        persuasionValue += proficiencyBonus;
                    }
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
                binding.arcanaValue.setText(String.valueOf(arcanaValue));
                binding.historyValue.setText(String.valueOf(historyValue));
                binding.investigationValue.setText(String.valueOf(investigationValue));
                binding.natureValue.setText(String.valueOf(natureValue));
                binding.religionValue.setText(String.valueOf(religionValue));
                binding.animHandValue.setText(String.valueOf(animalHandlingValue));
                binding.insightValue.setText(String.valueOf(insightValue));
                binding.medicineValue.setText(String.valueOf(medicineValue));
                binding.perceptionValue.setText(String.valueOf(perceptionValue));
                binding.survivalValue.setText(String.valueOf(survivalValue));
                binding.athleticsValue.setText(String.valueOf(athleticsValue));
                binding.deceptionValue.setText(String.valueOf(deceptionValue));
                binding.intimidationValue.setText(String.valueOf(intimidationValue));
                binding.performanceValue.setText(String.valueOf(performanceValue));
                binding.persuasionValue.setText(String.valueOf(persuasionValue));

                binding.strSavingThrow.setChecked(sheet.isStrengthSaveProficiency());
                binding.dexSavingThrow.setChecked(sheet.isDexteritySaveProficiency());
                binding.conSavingThrow.setChecked(sheet.isConstitutionSaveProficiency());
                binding.intSavingThrow.setChecked(sheet.isIntelligenceSaveProficiency());
                binding.wisSavingThrow.setChecked(sheet.isWisdomSaveProficiency());
                binding.chaSavingThrow.setChecked(sheet.isCharismaSaveProficiency());
                binding.acrobaticsSkill.setChecked(sheet.isAcrobaticsProficiency());
                binding.stealthSkill.setChecked(sheet.isStealthProficiency());
                binding.sleighHandSkill.setChecked(sheet.isSleightOfHandProficiency());
                binding.arcanaSkill.setChecked(sheet.isArcanaProficiency());
                binding.historySkill.setChecked(sheet.isHistoryProficiency());
                binding.investigationSkill.setChecked(sheet.isInvestigationProficiency());
                binding.natureSkill.setChecked(sheet.isNatureProficiency());
                binding.religionSkill.setChecked(sheet.isReligionProficiency());
                binding.animHandSkill.setChecked(sheet.isAnimalHandlingProficiency());
                binding.insightSkill.setChecked(sheet.isInsightProficiency());
                binding.medicineSkill.setChecked(sheet.isMedicineProficiency());
                binding.perceptionSkill.setChecked(sheet.isPerceptionProficiency());
                binding.survivalSkill.setChecked(sheet.isSurvivalProficiency());
                binding.athleticsSkill.setChecked(sheet.isAthleticsProficiency());
                binding.deceptionSkill.setChecked(sheet.isDeceptionProficiency());
                binding.intimidationSkill.setChecked(sheet.isIntimidationProficiency());
                binding.performanceSkill.setChecked(sheet.isPerformanceProficiency());
                binding.persuasionSkill.setChecked(sheet.isPersuasionProficiency());

            }
        });

        binding.strSavingThrow.setOnClickListener(view ->
        {
            int strValue = (this.sheetDAndD.getStrength() - 10)/2;
            if (binding.strSavingThrow.isChecked())
            {
                strValue += proficiencyBonus;
            }
            binding.strSavingValue.setText(String.valueOf(strValue));
        });

        binding.dexSavingThrow.setOnClickListener(view ->
        {
            int dexValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.dexSavingThrow.isChecked())
            {
                dexValue += proficiencyBonus;
            }
            binding.dexSavingValue.setText(String.valueOf(dexValue));
        });

        binding.conSavingThrow.setOnClickListener(view ->
        {
            int conValue = (this.sheetDAndD.getConstitution() - 10)/2;
            if (binding.conSavingThrow.isChecked())
            {
                conValue += proficiencyBonus;
            }
            binding.conSavingValue.setText(String.valueOf(conValue));
        });

        binding.intSavingThrow.setOnClickListener(view ->
        {
            int intValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.intSavingThrow.isChecked())
            {
                intValue += proficiencyBonus;
            }
            binding.intSavingValue.setText(String.valueOf(intValue));
        });

        binding.wisSavingThrow.setOnClickListener(view ->
        {
            int wisValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.wisSavingThrow.isChecked())
            {
                wisValue += proficiencyBonus;
            }
            binding.wisSavingValue.setText(String.valueOf(wisValue));
        });

        binding.chaSavingThrow.setOnClickListener(view ->
        {
            int chaValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.chaSavingThrow.isChecked())
            {
                chaValue += proficiencyBonus;
            }
            binding.chaSavingValue.setText(String.valueOf(chaValue));
        });

        binding.acrobaticsSkill.setOnClickListener(view ->
        {
            int acrobaticsValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.acrobaticsSkill.isChecked())
            {
                acrobaticsValue += proficiencyBonus;
            }
            binding.acrobaticsValue.setText(String.valueOf(acrobaticsValue));
        });

        binding.acrobaticsSkill.setOnLongClickListener(view ->
        {
            int acrobaticsValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.acrobaticsSkill.isChecked())
            {
                acrobaticsValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseAcrobaticsProficiency())
                {
                    this.sheetDAndD.setExpertiseAcrobaticsProficiency(false);
                    binding.acrobaticsValue.setText(String.valueOf(acrobaticsValue));
                    Snackbar.make(binding.acrobaticsSkill, "Expertise disabled for acrobatics skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseAcrobaticsProficiency(true);
                    acrobaticsValue += proficiencyBonus;
                    binding.acrobaticsValue.setText(String.valueOf(acrobaticsValue));
                    Snackbar.make(binding.acrobaticsSkill, "Expertise enabled for acrobatics skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseAcrobaticsProficiency(false);
            }
            return true;
        });

        binding.stealthSkill.setOnClickListener(view ->
        {
            int stealthValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.stealthSkill.isChecked())
            {
                stealthValue += proficiencyBonus;
            }
            binding.stealthValue.setText(String.valueOf(stealthValue));
        });

        binding.stealthSkill.setOnLongClickListener(view ->
        {
            int stealthValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.stealthSkill.isChecked())
            {
                stealthValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseStealthProficiency())
                {
                    this.sheetDAndD.setExpertiseStealthProficiency(false);
                    binding.stealthValue.setText(String.valueOf(stealthValue));
                    Snackbar.make(binding.stealthSkill, "Expertise disabled for stealth skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseStealthProficiency(true);
                    stealthValue += proficiencyBonus;
                    binding.stealthValue.setText(String.valueOf(stealthValue));
                    Snackbar.make(binding.stealthSkill, "Expertise enabled for stealth skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseStealthProficiency(false);
            }
            return true;
        });

        binding.sleighHandSkill.setOnClickListener(view ->
        {
            int sleightHandValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.sleighHandSkill.isChecked())
            {
                sleightHandValue += proficiencyBonus;
            }
            binding.sleightHandValue.setText(String.valueOf(sleightHandValue));
        });

        binding.sleighHandSkill.setOnLongClickListener(view ->
        {
            int sleightHandValue = (this.sheetDAndD.getDexterity() - 10)/2;
            if (binding.sleighHandSkill.isChecked())
            {
                sleightHandValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseSleightOfHandProficiency())
                {
                    this.sheetDAndD.setExpertiseSleightOfHandProficiency(false);
                    binding.sleightHandValue.setText(String.valueOf(sleightHandValue));
                    Snackbar.make(binding.sleighHandSkill, "Expertise disabled for sleigh of hand skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseSleightOfHandProficiency(true);
                    sleightHandValue += proficiencyBonus;
                    binding.sleightHandValue.setText(String.valueOf(sleightHandValue));
                    Snackbar.make(binding.sleighHandSkill, "Expertise enabled for sleigh of hand skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseSleightOfHandProficiency(false);
            }
            return true;
        });

        binding.arcanaSkill.setOnClickListener(view ->
        {
            int arcanaValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.arcanaSkill.isChecked())
            {
                arcanaValue += proficiencyBonus;
            }
            binding.arcanaValue.setText(String.valueOf(arcanaValue));
        });

        binding.arcanaSkill.setOnLongClickListener(view ->
        {
            int arcanaValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.arcanaSkill.isChecked())
            {
                arcanaValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseArcanaProficiency())
                {
                    this.sheetDAndD.setExpertiseArcanaProficiency(false);
                    binding.arcanaValue.setText(String.valueOf(arcanaValue));
                    Snackbar.make(binding.arcanaSkill, "Expertise disabled for arcana skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseArcanaProficiency(true);
                    arcanaValue += proficiencyBonus;
                    binding.arcanaValue.setText(String.valueOf(arcanaValue));
                    Snackbar.make(binding.arcanaSkill, "Expertise enabled for arcana skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseArcanaProficiency(false);
            }
            return true;
        });

        binding.historySkill.setOnClickListener(view ->
        {
           int historyValue = (this.sheetDAndD.getIntelligence() - 10)/2;
           if (binding.historySkill.isChecked())
           {
               historyValue += proficiencyBonus;
           }
           binding.historyValue.setText(String.valueOf(historyValue));
        });

        binding.historySkill.setOnLongClickListener(view ->
        {
            int historyValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.historySkill.isChecked())
            {
                historyValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseHistoryProficiency())
                {
                    this.sheetDAndD.setExpertiseHistoryProficiency(false);
                    binding.historyValue.setText(String.valueOf(historyValue));
                    Snackbar.make(binding.historySkill, "Expertise disabled for history skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseHistoryProficiency(true);
                    historyValue += proficiencyBonus;
                    binding.historyValue.setText(String.valueOf(historyValue));
                    Snackbar.make(binding.historySkill, "Expertise enabled for history skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseHistoryProficiency(false);
            }
            return true;
        });

        binding.investigationSkill.setOnClickListener(view ->
        {
            int investigationValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.investigationSkill.isChecked())
            {
                investigationValue += proficiencyBonus;
            }
            binding.investigationValue.setText(String.valueOf(investigationValue));
        });

        binding.investigationSkill.setOnLongClickListener(view ->
        {
            int investigationValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.investigationSkill.isChecked())
            {
                investigationValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseInvestigationProficiency())
                {
                    this.sheetDAndD.setExpertiseInvestigationProficiency(false);
                    binding.investigationValue.setText(String.valueOf(investigationValue));
                    Snackbar.make(binding.investigationSkill, "Expertise disabled for investigation skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseInvestigationProficiency(true);
                    investigationValue += proficiencyBonus;
                    binding.investigationValue.setText(String.valueOf(investigationValue));
                    Snackbar.make(binding.investigationSkill, "Expertise enabled for investigation skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseInvestigationProficiency(false);
            }
            return true;
        });

        binding.natureSkill.setOnClickListener(view ->
        {
            int natureValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.natureSkill.isChecked())
            {
                natureValue += proficiencyBonus;
            }
            binding.natureValue.setText(String.valueOf(natureValue));
        });

        binding.natureSkill.setOnLongClickListener(view ->
        {
            int natureValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.natureSkill.isChecked())
            {
                natureValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseNatureProficiency())
                {
                    this.sheetDAndD.setExpertiseNatureProficiency(false);
                    binding.natureValue.setText(String.valueOf(natureValue));
                    Snackbar.make(binding.natureSkill, "Expertise disabled for nature skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseNatureProficiency(true);
                    natureValue += proficiencyBonus;
                    binding.natureValue.setText(String.valueOf(natureValue));
                    Snackbar.make(binding.natureSkill, "Expertise enabled for nature skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseNatureProficiency(false);
            }
            return true;
        });

        binding.religionSkill.setOnClickListener(view ->
        {
            int religionValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.religionSkill.isChecked())
            {
                religionValue += proficiencyBonus;
            }
            binding.religionValue.setText(String.valueOf(religionValue));
        });

        binding.religionSkill.setOnLongClickListener(view ->
        {
            int religionValue = (this.sheetDAndD.getIntelligence() - 10)/2;
            if (binding.religionSkill.isChecked())
            {
                religionValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseReligionProficiency())
                {
                    this.sheetDAndD.setExpertiseReligionProficiency(false);
                    binding.religionValue.setText(String.valueOf(religionValue));
                    Snackbar.make(binding.religionSkill, "Expertise disabled for religion skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseReligionProficiency(true);
                    religionValue += proficiencyBonus;
                    binding.religionValue.setText(String.valueOf(religionValue));
                    Snackbar.make(binding.religionSkill, "Expertise enabled for religion skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseReligionProficiency(false);
            }
            return true;
        });

        binding.animHandSkill.setOnClickListener(view ->
        {
            int animHandValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.animHandSkill.isChecked())
            {
                animHandValue += proficiencyBonus;
            }
            binding.animHandValue.setText(String.valueOf(animHandValue));
        });

        binding.animHandSkill.setOnLongClickListener(view ->
        {
            int animHandValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.animHandSkill.isChecked())
            {
                animHandValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseAnimalHandlingProficiency())
                {
                    this.sheetDAndD.setExpertiseAnimalHandlingProficiency(false);
                    binding.animHandValue.setText(String.valueOf(animHandValue));
                    Snackbar.make(binding.animHandSkill, "Expertise disabled for animal handling skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseAnimalHandlingProficiency(true);
                    animHandValue += proficiencyBonus;
                    binding.animHandValue.setText(String.valueOf(animHandValue));
                    Snackbar.make(binding.animHandSkill, "Expertise enabled for animal handling skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseAnimalHandlingProficiency(false);
            }
            return true;
        });

        binding.insightSkill.setOnClickListener(view ->
        {
            int insightValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.insightSkill.isChecked())
            {
                insightValue += proficiencyBonus;
            }
            binding.insightValue.setText(String.valueOf(insightValue));
        });

        binding.insightSkill.setOnLongClickListener(view ->
        {
            int insightValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.insightSkill.isChecked())
            {
                insightValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseInsightProficiency())
                {
                    this.sheetDAndD.setExpertiseInsightProficiency(false);
                    binding.insightValue.setText(String.valueOf(insightValue));
                    Snackbar.make(binding.insightSkill, "Expertise disabled for insight skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseInsightProficiency(true);
                    insightValue += proficiencyBonus;
                    binding.insightValue.setText(String.valueOf(insightValue));
                    Snackbar.make(binding.insightSkill, "Expertise enabled for insight skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseInsightProficiency(false);
            }
            return true;
        });

        binding.medicineSkill.setOnClickListener(view ->
        {
            int medicineValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.medicineSkill.isChecked())
            {
                medicineValue += proficiencyBonus;
            }
            binding.medicineValue.setText(String.valueOf(medicineValue));
        });

        binding.medicineSkill.setOnLongClickListener(view ->
        {
            int medicineValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.medicineSkill.isChecked())
            {
                medicineValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseMedicineProficiency())
                {
                    this.sheetDAndD.setExpertiseMedicineProficiency(false);
                    binding.medicineValue.setText(String.valueOf(medicineValue));
                    Snackbar.make(binding.medicineSkill, "Expertise disabled for medicine skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseMedicineProficiency(true);
                    medicineValue += proficiencyBonus;
                    binding.medicineValue.setText(String.valueOf(medicineValue));
                    Snackbar.make(binding.medicineSkill, "Expertise enabled for medicine skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseMedicineProficiency(false);
            }
            return true;
        });

        binding.perceptionSkill.setOnClickListener(view ->
        {
            int perceptionValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.perceptionSkill.isChecked())
            {
                perceptionValue += proficiencyBonus;
            }
            binding.perceptionValue.setText(String.valueOf(perceptionValue));
        });

        binding.perceptionSkill.setOnLongClickListener(view ->
        {
            int perceptionValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.perceptionSkill.isChecked())
            {
                perceptionValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertisePerceptionProficiency())
                {
                    this.sheetDAndD.setExpertisePerceptionProficiency(false);
                    binding.perceptionValue.setText(String.valueOf(perceptionValue));
                    Snackbar.make(binding.perceptionSkill, "Expertise disabled for perception skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertisePerceptionProficiency(true);
                    perceptionValue += proficiencyBonus;
                    binding.perceptionValue.setText(String.valueOf(perceptionValue));
                    Snackbar.make(binding.perceptionSkill, "Expertise enabled for perception skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertisePerceptionProficiency(false);
            }
            return true;
        });

        binding.survivalSkill.setOnClickListener(view ->
        {
            int survivalValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.survivalSkill.isChecked())
            {
                survivalValue += proficiencyBonus;
            }
            binding.survivalValue.setText(String.valueOf(survivalValue));
        });

        binding.survivalSkill.setOnLongClickListener(view ->
        {
            int survivalValue = (this.sheetDAndD.getWisdom() - 10)/2;
            if (binding.survivalSkill.isChecked())
            {
                survivalValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseSurvivalProficiency())
                {
                    this.sheetDAndD.setExpertiseSurvivalProficiency(false);
                    binding.survivalValue.setText(String.valueOf(survivalValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise disabled for survival skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseSurvivalProficiency(true);
                    survivalValue += proficiencyBonus;
                    binding.survivalValue.setText(String.valueOf(survivalValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise enabled for survival skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseSurvivalProficiency(false);
            }
            return true;
        });

        binding.athleticsSkill.setOnClickListener(view ->
        {
            int athleticsValue = (this.sheetDAndD.getStrength() - 10)/2;
            if (binding.athleticsSkill.isChecked())
            {
                athleticsValue += proficiencyBonus;
            }
            binding.athleticsValue.setText(String.valueOf(athleticsValue));
        });

        binding.athleticsSkill.setOnLongClickListener(view ->
        {
            int athleticsValue = (this.sheetDAndD.getStrength() - 10)/2;
            if (binding.athleticsSkill.isChecked())
            {
                athleticsValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseAthleticsProficiency())
                {
                    this.sheetDAndD.setExpertiseAthleticsProficiency(false);
                    binding.athleticsValue.setText(String.valueOf(athleticsValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise disabled for athletics skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseAthleticsProficiency(true);
                    athleticsValue += proficiencyBonus;
                    binding.athleticsValue.setText(String.valueOf(athleticsValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise enabled for athletics skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseAthleticsProficiency(false);
            }
            return true;
        });

        binding.deceptionSkill.setOnClickListener(view ->
        {
            int deceptionValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.deceptionSkill.isChecked())
            {
                deceptionValue += proficiencyBonus;
            }
            binding.deceptionValue.setText(String.valueOf(deceptionValue));
        });

        binding.deceptionSkill.setOnLongClickListener(view ->
        {
            int deceptionValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.deceptionSkill.isChecked())
            {
                deceptionValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseDeceptionProficiency())
                {
                    this.sheetDAndD.setExpertiseDeceptionProficiency(false);
                    binding.deceptionValue.setText(String.valueOf(deceptionValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise disabled for deception skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseDeceptionProficiency(true);
                    deceptionValue += proficiencyBonus;
                    binding.deceptionValue.setText(String.valueOf(deceptionValue));
                    Snackbar.make(binding.deceptionSkill, "Expertise enabled for deception skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseDeceptionProficiency(false);
            }
            return true;
        });

        binding.intimidationSkill.setOnClickListener(view ->
        {
            int intimidationValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.intimidationSkill.isChecked())
            {
                intimidationValue += proficiencyBonus;
            }
            binding.intimidationValue.setText(String.valueOf(intimidationValue));
        });

        binding.intimidationSkill.setOnLongClickListener(view ->
        {
            int intimidationValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.intimidationSkill.isChecked())
            {
                intimidationValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertiseIntimidationProficiency())
                {
                    this.sheetDAndD.setExpertiseIntimidationProficiency(false);
                    binding.intimidationValue.setText(String.valueOf(intimidationValue));
                    Snackbar.make(binding.intimidationSkill, "Expertise disabled for intimidation skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertiseIntimidationProficiency(true);
                    intimidationValue += proficiencyBonus;
                    binding.intimidationValue.setText(String.valueOf(intimidationValue));
                    Snackbar.make(binding.intimidationSkill, "Expertise enabled for intimidation skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertiseIntimidationProficiency(false);
            }
            return true;
        });

        binding.performanceSkill.setOnClickListener(view ->
        {
            int performanceValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.performanceSkill.isChecked())
            {
                performanceValue += proficiencyBonus;
            }
            binding.performanceValue.setText(String.valueOf(performanceValue));
        });

        binding.performanceSkill.setOnLongClickListener(view ->
        {
            int performanceValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.performanceSkill.isChecked())
            {
                performanceValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertisePerformanceProficiency())
                {
                    this.sheetDAndD.setExpertisePerformanceProficiency(false);
                    binding.performanceValue.setText(String.valueOf(performanceValue));
                    Snackbar.make(binding.performanceSkill, "Expertise disabled for performance skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertisePerformanceProficiency(true);
                    performanceValue += proficiencyBonus;
                    binding.performanceValue.setText(String.valueOf(performanceValue));
                    Snackbar.make(binding.performanceSkill, "Expertise enabled for performance skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertisePerformanceProficiency(false);
            }
            return true;
        });

        binding.persuasionSkill.setOnClickListener(view ->
        {
            int persuasionValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.persuasionSkill.isChecked())
            {
                persuasionValue += proficiencyBonus;
            }
            binding.persuasionValue.setText(String.valueOf(persuasionValue));
        });

        binding.persuasionSkill.setOnLongClickListener(view ->
        {
            int persuasionValue = (this.sheetDAndD.getCharisma() - 10)/2;
            if (binding.persuasionSkill.isChecked())
            {
                persuasionValue += proficiencyBonus;
                if (this.sheetDAndD.isExpertisePersuasionProficiency())
                {
                    this.sheetDAndD.setExpertisePersuasionProficiency(false);
                    binding.persuasionValue.setText(String.valueOf(persuasionValue));
                    Snackbar.make(binding.performanceSkill, "Expertise disabled for persuasion skill", Snackbar.LENGTH_SHORT).show();
                }
                else
                {
                    this.sheetDAndD.setExpertisePersuasionProficiency(true);
                    persuasionValue += proficiencyBonus;
                    binding.persuasionValue .setText(String.valueOf(persuasionValue));
                    Snackbar.make(binding.performanceSkill, "Expertise enabled for persuasion skill", Snackbar.LENGTH_SHORT).show();
                }
            }
            else
            {
                this.sheetDAndD.setExpertisePersuasionProficiency(false);
            }
            return true;
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
        sheetDAndD.setExpertiseAcrobaticsProficiency(this.sheetDAndD.isExpertiseAcrobaticsProficiency());
        sheetDAndD.setStealthProficiency(binding.stealthSkill.isChecked());
        sheetDAndD.setExpertiseStealthProficiency(this.sheetDAndD.isExpertiseStealthProficiency());
        sheetDAndD.setSleightOfHandProficiency(binding.sleighHandSkill.isChecked());
        sheetDAndD.setExpertiseSleightOfHandProficiency(this.sheetDAndD.isExpertiseSleightOfHandProficiency());
        sheetDAndD.setArcanaProficiency(binding.arcanaSkill.isChecked());
        sheetDAndD.setExpertiseArcanaProficiency(this.sheetDAndD.isExpertiseArcanaProficiency());
        sheetDAndD.setHistoryProficiency(binding.historySkill.isChecked());
        sheetDAndD.setExpertiseHistoryProficiency(this.sheetDAndD.isExpertiseHistoryProficiency());
        sheetDAndD.setInvestigationProficiency(binding.investigationSkill.isChecked());
        sheetDAndD.setExpertiseInvestigationProficiency(this.sheetDAndD.isExpertiseInvestigationProficiency());
        sheetDAndD.setNatureProficiency(binding.natureSkill.isChecked());
        sheetDAndD.setExpertiseNatureProficiency(this.sheetDAndD.isExpertiseNatureProficiency());
        sheetDAndD.setReligionProficiency(binding.religionSkill.isChecked());
        sheetDAndD.setExpertiseReligionProficiency(this.sheetDAndD.isExpertiseReligionProficiency());
        sheetDAndD.setAnimalHandlingProficiency(binding.animHandSkill.isChecked());
        sheetDAndD.setExpertiseAnimalHandlingProficiency(this.sheetDAndD.isExpertiseAnimalHandlingProficiency());
        sheetDAndD.setInsightProficiency(binding.insightSkill.isChecked());
        sheetDAndD.setExpertiseInsightProficiency(this.sheetDAndD.isExpertiseInsightProficiency());
        sheetDAndD.setMedicineProficiency(binding.medicineSkill.isChecked());
        sheetDAndD.setExpertiseMedicineProficiency(this.sheetDAndD.isExpertiseMedicineProficiency());
        sheetDAndD.setPerceptionProficiency(binding.perceptionSkill.isChecked());
        sheetDAndD.setExpertisePerceptionProficiency(this.sheetDAndD.isExpertisePerceptionProficiency());
        sheetDAndD.setSurvivalProficiency(binding.survivalSkill.isChecked());
        sheetDAndD.setExpertiseSurvivalProficiency(this.sheetDAndD.isExpertiseSurvivalProficiency());
        sheetDAndD.setAthleticsProficiency(binding.athleticsSkill.isChecked());
        sheetDAndD.setExpertiseAthleticsProficiency(this.sheetDAndD.isExpertiseAthleticsProficiency());
        sheetDAndD.setDeceptionProficiency(binding.deceptionSkill.isChecked());
        sheetDAndD.setExpertiseDeceptionProficiency(this.sheetDAndD.isExpertiseDeceptionProficiency());
        sheetDAndD.setIntimidationProficiency(binding.intimidationSkill.isChecked());
        sheetDAndD.setExpertiseIntimidationProficiency(this.sheetDAndD.isExpertiseIntimidationProficiency());
        sheetDAndD.setPerformanceProficiency(binding.performanceSkill.isChecked());
        sheetDAndD.setExpertisePerformanceProficiency(this.sheetDAndD.isExpertisePerformanceProficiency());
        sheetDAndD.setPersuasionProficiency(binding.persuasionSkill.isChecked());
        sheetDAndD.setExpertisePersuasionProficiency(this.sheetDAndD.isExpertisePersuasionProficiency());
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
        sheetDAndD.setName(this.sheetDAndD.getName());
        sheetDAndD.setClassFeatures(this.sheetDAndD.getClassFeatures());
        sheetDAndD.setSubclasses(this.sheetDAndD.getSubclasses());
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
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
        sheetDAndD.setFeatures(this.sheetDAndD.getFeatures());
        sheetDAndD.setEquipments(this.sheetDAndD.getEquipments());
        return sheetDAndD;
    }
}