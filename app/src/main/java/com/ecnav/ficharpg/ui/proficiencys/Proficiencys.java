package com.ecnav.ficharpg.ui.proficiencys;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
        return root;
    }
}