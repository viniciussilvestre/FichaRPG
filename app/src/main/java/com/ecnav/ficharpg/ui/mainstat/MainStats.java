package com.ecnav.ficharpg.ui.mainstat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.databinding.FragmentMainStatsBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;

import java.util.ArrayList;

public class MainStats extends Fragment
{
    private FragmentMainStatsBinding binding;
    private SheetViewModel sheetViewModel;
    private int id;
    private SheetDAndD sheetDAndD;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);

        id = idViewModel.getSelectedItem();
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(MainStats.this.requireActivity().getApplication()).create(SheetViewModel.class);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentMainStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                binding.characterNameText.setText(sheet.getName());
                ArrayList<Classes> classes = sheet.getClassFeatures();
                StringBuilder classesText = new StringBuilder();
                for (int i = 0; i < classes.size(); i++)
                {
                    if (i == classes.size() - 1)
                    {
                        classesText.append(classes.get(i).getClassName());
                    }
                    else
                    {
                        classesText.append(classes.get(i).getClassName()).append(", ");
                    }
                }
                binding.classText.setText(classesText.toString());
                binding.speedText.setText(String.valueOf(sheet.getSpeed()));
                binding.levelText.setText(String.valueOf(sheet.getLevel()));
                binding.alignmentText.setText(sheet.getAlignment());
                binding.raceText.setText(sheet.getRace());
                binding.backgroundText.setText(sheet.getBackground());
                binding.armorClassText.setText(String.valueOf(sheet.getArmorClass()));
                binding.healthText.setText(String.valueOf(sheet.getHitPoints()));
                binding.strenghtText.setText(String.valueOf(sheet.getStrength()));
                int strMod = (sheet.getStrength() - 10)/2;
                binding.modStrText.setText(String.valueOf(strMod));
                binding.dexterityText.setText(String.valueOf(sheet.getDexterity()));
                int dexMod = (sheet.getDexterity() - 10)/2;
                binding.modDexText.setText(String.valueOf(dexMod));
                binding.constitutionText.setText(String.valueOf(sheet.getConstitution()));
                int conMod = (sheet.getConstitution() - 10)/2;
                binding.modConText.setText(String.valueOf(conMod));
                binding.intelligenceText.setText(String.valueOf(sheet.getIntelligence()));
                int intMod = (sheet.getIntelligence() - 10)/2;
                binding.modIntText.setText(String.valueOf(intMod));
                binding.wisdomText.setText(String.valueOf(sheet.getWisdom()));
                int wisMod = (sheet.getWisdom() - 10)/2;
                binding.modWisText.setText(String.valueOf(wisMod));
                binding.charismaText.setText(String.valueOf(sheet.getCharisma()));
                int chaMod = (sheet.getCharisma() - 10)/2;
                binding.modChaText.setText(String.valueOf(chaMod));
            }
            else
            {
                binding.characterNameText.setText(String.valueOf(id));
            }
        });

        binding.strenghtText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.strenghtText.getText().toString().isEmpty())
                {
                    int strMod = (Integer.parseInt(binding.strenghtText.getText().toString()) - 10)/2;
                    binding.modStrText.setText(String.valueOf(strMod));
                }
            }
        });

        binding.dexterityText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.dexterityText.getText().toString().isEmpty())
                {
                    int dexMod = (Integer.parseInt(binding.dexterityText.getText().toString()) - 10)/2;
                    binding.modDexText.setText(String.valueOf(dexMod));
                    binding.initiativeText.setText(String.valueOf(dexMod));
                    int armorClass;
                    if (dexMod > 0)
                    {
                        armorClass = 10 + dexMod;
                    }
                    else
                    {
                        armorClass = 10;
                    }
                    binding.armorClassText.setText(String.valueOf(armorClass));
                }
            }
        });

        binding.constitutionText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.constitutionText.getText().toString().isEmpty())
                {
                    int conMod = (Integer.parseInt(binding.constitutionText.getText().toString()) - 10)/2;
                    binding.modConText.setText(String.valueOf(conMod));
                }
            }
        });

        binding.intelligenceText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.intelligenceText.getText().toString().isEmpty())
                {
                    int intMod = (Integer.parseInt(binding.intelligenceText.getText().toString()) - 10)/2;
                    binding.modIntText.setText(String.valueOf(intMod));
                }
            }
        });

        binding.wisdomText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(!binding.wisdomText.getText().toString().isEmpty())
                {
                    int wisMod = (Integer.parseInt(binding.wisdomText.getText().toString()) - 10)/2;
                    binding.modWisText.setText(String.valueOf(wisMod));
                }
            }
        });

        binding.charismaText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.charismaText.getText().toString().isEmpty())
                {
                    int chaMod = (Integer.parseInt(binding.charismaText.getText().toString()) - 10)/2;
                    binding.modChaText.setText(String.valueOf(chaMod));
                }
            }
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

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }

    public SheetDAndD getNewSheetData()
    {
        SheetDAndD sheetDAndD = new SheetDAndD();
        sheetDAndD.setId(id);
        sheetDAndD.setName(binding.characterNameText.getText().toString());
        sheetDAndD.setClassFeatures(this.sheetDAndD.getClassFeatures());
        sheetDAndD.setSubclasses(this.sheetDAndD.getSubclasses());
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
        sheetDAndD.setSpeed(Integer.parseInt(binding.speedText.getText().toString()));
        sheetDAndD.setArmorClass(Integer.parseInt(binding.armorClassText.getText().toString()));
        sheetDAndD.setLevel(Integer.parseInt(binding.levelText.getText().toString()));
        sheetDAndD.setRace(binding.raceText.getText().toString());
        sheetDAndD.setBackground(binding.backgroundText.getText().toString());
        sheetDAndD.setAlignment(binding.alignmentText.getText().toString());
        sheetDAndD.setInitiative(Integer.parseInt(binding.initiativeText.getText().toString()));
        sheetDAndD.setArmorClass(Integer.parseInt(binding.armorClassText.getText().toString()));
        sheetDAndD.setHitPoints(Integer.parseInt(binding.healthText.getText().toString()));
        sheetDAndD.setStrength(Integer.parseInt(binding.strenghtText.getText().toString()));
        sheetDAndD.setDexterity(Integer.parseInt(binding.dexterityText.getText().toString()));
        sheetDAndD.setConstitution(Integer.parseInt(binding.constitutionText.getText().toString()));
        sheetDAndD.setIntelligence(Integer.parseInt(binding.intelligenceText.getText().toString()));
        sheetDAndD.setWisdom(Integer.parseInt(binding.wisdomText.getText().toString()));
        sheetDAndD.setCharisma(Integer.parseInt(binding.charismaText.getText().toString()));
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
        sheetDAndD.setStrengthSaveProficiency(this.sheetDAndD.isStrengthSaveProficiency());
        sheetDAndD.setDexteritySaveProficiency(this.sheetDAndD.isDexteritySaveProficiency());
        sheetDAndD.setConstitutionSaveProficiency(this.sheetDAndD.isConstitutionSaveProficiency());
        sheetDAndD.setIntelligenceSaveProficiency(this.sheetDAndD.isIntelligenceSaveProficiency());
        sheetDAndD.setWisdomSaveProficiency(this.sheetDAndD.isWisdomSaveProficiency());
        sheetDAndD.setCharismaSaveProficiency(this.sheetDAndD.isCharismaSaveProficiency());
        sheetDAndD.setAcrobaticsProficiency(this.sheetDAndD.isAcrobaticsProficiency());
        sheetDAndD.setExpertiseAcrobaticsProficiency(this.sheetDAndD.isExpertiseAcrobaticsProficiency());
        sheetDAndD.setStealthProficiency(this.sheetDAndD.isStealthProficiency());
        sheetDAndD.setExpertiseStealthProficiency(this.sheetDAndD.isExpertiseStealthProficiency());
        sheetDAndD.setSleightOfHandProficiency(this.sheetDAndD.isSleightOfHandProficiency());
        sheetDAndD.setExpertiseSleightOfHandProficiency(this.sheetDAndD.isExpertiseSleightOfHandProficiency());
        sheetDAndD.setArcanaProficiency(this.sheetDAndD.isArcanaProficiency());
        sheetDAndD.setExpertiseArcanaProficiency(this.sheetDAndD.isExpertiseArcanaProficiency());
        sheetDAndD.setHistoryProficiency(this.sheetDAndD.isHistoryProficiency());
        sheetDAndD.setExpertiseHistoryProficiency(this.sheetDAndD.isExpertiseHistoryProficiency());
        sheetDAndD.setInvestigationProficiency(this.sheetDAndD.isInvestigationProficiency());
        sheetDAndD.setExpertiseInvestigationProficiency(this.sheetDAndD.isExpertiseInvestigationProficiency());
        sheetDAndD.setNatureProficiency(this.sheetDAndD.isNatureProficiency());
        sheetDAndD.setExpertiseNatureProficiency(this.sheetDAndD.isExpertiseNatureProficiency());
        sheetDAndD.setReligionProficiency(this.sheetDAndD.isReligionProficiency());
        sheetDAndD.setExpertiseReligionProficiency(this.sheetDAndD.isExpertiseReligionProficiency());
        sheetDAndD.setAnimalHandlingProficiency(this.sheetDAndD.isAnimalHandlingProficiency());
        sheetDAndD.setExpertiseAnimalHandlingProficiency(this.sheetDAndD.isExpertiseAnimalHandlingProficiency());
        sheetDAndD.setInsightProficiency(this.sheetDAndD.isInsightProficiency());
        sheetDAndD.setExpertiseInsightProficiency(this.sheetDAndD.isExpertiseInsightProficiency());
        sheetDAndD.setMedicineProficiency(this.sheetDAndD.isMedicineProficiency());
        sheetDAndD.setExpertiseMedicineProficiency(this.sheetDAndD.isExpertiseMedicineProficiency());
        sheetDAndD.setPerceptionProficiency(this.sheetDAndD.isPerceptionProficiency());
        sheetDAndD.setExpertisePerceptionProficiency(this.sheetDAndD.isExpertisePerceptionProficiency());
        sheetDAndD.setSurvivalProficiency(this.sheetDAndD.isSurvivalProficiency());
        sheetDAndD.setExpertiseSurvivalProficiency(this.sheetDAndD.isExpertiseSurvivalProficiency());
        sheetDAndD.setAthleticsProficiency(this.sheetDAndD.isAthleticsProficiency());
        sheetDAndD.setExpertiseAthleticsProficiency(this.sheetDAndD.isExpertiseAthleticsProficiency());
        sheetDAndD.setDeceptionProficiency(this.sheetDAndD.isDeceptionProficiency());
        sheetDAndD.setExpertiseDeceptionProficiency(this.sheetDAndD.isExpertiseDeceptionProficiency());
        sheetDAndD.setIntimidationProficiency(this.sheetDAndD.isIntimidationProficiency());
        sheetDAndD.setExpertiseIntimidationProficiency(this.sheetDAndD.isExpertiseIntimidationProficiency());
        sheetDAndD.setPerformanceProficiency(this.sheetDAndD.isPerformanceProficiency());
        sheetDAndD.setExpertisePerformanceProficiency(this.sheetDAndD.isExpertisePerformanceProficiency());
        sheetDAndD.setPersuasionProficiency(this.sheetDAndD.isPersuasionProficiency());
        sheetDAndD.setExpertisePersuasionProficiency(this.sheetDAndD.isExpertisePersuasionProficiency());
        sheetDAndD.setFeatures(this.sheetDAndD.getFeatures());
        sheetDAndD.setEquipments(this.sheetDAndD.getEquipments());
        return sheetDAndD;
    }

    public void fixRaceAndBackgroundWidth(int raceTextWidth, int backgroundTextWidth)
    {
        if (raceTextWidth > backgroundTextWidth)
        {
            binding.backgroundText.setWidth(raceTextWidth);
        }
        if (backgroundTextWidth > raceTextWidth)
        {
            binding.raceText.setWidth(backgroundTextWidth);
        }
    }
}