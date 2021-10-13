package com.ecnav.ficharpg.ui.equipment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.FragmentEquipmentInfoBinding;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.spellsinfo.SpellsInfo;

public class EquipmentInfo extends Fragment
{
    private FragmentEquipmentInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private int id;
    private SheetDAndD sheetDAndD;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(EquipmentInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentEquipmentInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(EquipmentInfo.this.requireActivity()));
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
            }
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                if (dy != 0 && binding.addButton.isExtended())
                {
                    binding.addButton.shrink();
                }
                if (dy < 0 && !binding.addButton.isExtended())
                {
                    binding.addButton.extend();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.addButton.setOnClickListener(view ->
        {

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

    public SheetDAndD getNewSheetData()
    {
        SheetDAndD sheetDAndD = new SheetDAndD();
        sheetDAndD.setId(id);
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
        return sheetDAndD;
    }
}