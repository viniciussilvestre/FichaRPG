package com.ecnav.ficharpg.ui.proficiencys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.FragmentMainStatsBinding;
import com.ecnav.ficharpg.databinding.FragmentProficiencysBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.mainstat.MainStats;
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
                binding.proficiencyText.setText(String.valueOf(sheet.getProficiencyBonus()));
                setProficiencyBonusAuto(sheet.getLevel());
            }
        });
        return root;
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