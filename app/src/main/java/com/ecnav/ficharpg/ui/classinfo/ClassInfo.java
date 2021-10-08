package com.ecnav.ficharpg.ui.classinfo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClassesInfo;
import com.ecnav.ficharpg.databinding.FragmentClassInfoBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;

import java.util.ArrayList;

public class ClassInfo extends Fragment implements RecyclerViewAdapterClassesInfo.OnContactClickListener
{
    private FragmentClassInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private IdViewModel idViewModel;
    private int parameterReceived;
    private RecyclerViewAdapterClassesInfo recyclerViewAdapterClassesInfo;
    private int id;
    private ArrayList<Classes> classes = new ArrayList<>();
    private SheetDAndD sheetDAndD;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null)
//        {
//            parameterReceived = getArguments().getInt(Util.PARAMETER_FOR_FRAGMENT, 0);
//        }
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(ClassInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        //id = parameterReceived;
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentClassInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(ClassInfo.this.requireActivity()));
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                classes = sheet.getClassFeatures();
                recyclerViewAdapterClassesInfo = new RecyclerViewAdapterClassesInfo(classes.get(0).getClassFeatures(), ClassInfo.this.requireActivity(), this);
                binding.recyclerView.setAdapter(recyclerViewAdapterClassesInfo);
            }
        });
        return root;
    }

    @Override
    public void onContactClick(int position)
    {

    }
}