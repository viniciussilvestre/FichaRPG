package com.ecnav.ficharpg.ui.mainstat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.CharacterSheet;
import com.ecnav.ficharpg.databinding.FragmentMainStatsBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

import java.util.Objects;

public class MainStats extends Fragment
{
    private FragmentMainStatsBinding binding;
    private SheetViewModel sheetViewModel;
    private int parameterReceived;
    private int id;
    private SheetDAndD sheetDAndD;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            parameterReceived = getArguments().getInt(Util.PARAMETER_FOR_FRAGMENT, 0);
        }
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(MainStats.this.requireActivity().getApplication()).create(SheetViewModel.class);
        id = parameterReceived;
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
                binding.classText.setText(sheet.getCharacterClass());
                binding.proficiencyText.setText(String.valueOf(sheet.getProficiencyBonus()));
                binding.speedText.setText(String.valueOf(sheet.getSpeed()));
                binding.levelText.setText(String.valueOf(sheet.getLevel()));
                binding.alignmentText.setText(sheet.getAlignment());
                binding.raceText.setText(sheet.getRace());
                binding.backgroundText.setText(sheet.getBackground());
                binding.initiativeText.setText(String.valueOf(sheet.getInitiative()));
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

        binding.levelText.addTextChangedListener(new TextWatcher()
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
                setProficiencyBonusAuto(Integer.parseInt(binding.levelText.getText().toString()));
            }
        });
        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
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
}