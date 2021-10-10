package com.ecnav.ficharpg.ui.subclassinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.FragmentSubclassInfoBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.classinfo.ClassInfo;
import com.ecnav.ficharpg.ui.proficiencys.Proficiencys;

import java.util.ArrayList;

public class SubclassInfo extends Fragment
{
    private FragmentSubclassInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private IdViewModel idViewModel;
    private ArrayList<Classes> classes = new ArrayList<>();
    private int id;
    private SheetDAndD sheetDAndD;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(SubclassInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentSubclassInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(SubclassInfo.this.requireActivity()));
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                classes = sheet.getClassFeatures();
            }
        });
        return root;
    }
}