package com.ecnav.ficharpg.ui.featureinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClassesInfo;
import com.ecnav.ficharpg.databinding.FragmentFeatureInfoBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.ui.addthings.AddFeature;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;

public class FeatureInfo extends Fragment implements RecyclerViewAdapterClassesInfo.OnContactClickListener
{
    private FragmentFeatureInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterClassesInfo recyclerViewAdapterClassesInfo;
    private int id;
    private ArrayList<Classes> classes = new ArrayList<>();
    private ArrayList<Subclass> subclasses = new ArrayList<>();
    private ArrayList<Feature> extraFeatures = new ArrayList<>();
    private SheetDAndD sheetDAndD;

    ActivityResultLauncher<Intent> launchAddFeature = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    ArrayList<Feature> featureArrayList = sheetDAndD.getFeatures();
                    Feature feature = new Feature();
                    feature.setLevel(data.getIntExtra(Util.FEATURE_LEVEL, 1));
                    feature.setNome(data.getStringExtra(Util.FEATURE_NAME));
                    feature.setDescription(data.getStringExtra(Util.FEATURE_DESCRIPTION));
                    featureArrayList.add(feature);
                    extraFeatures = featureArrayList;
                    SheetDAndD sheetDAndD = getNewSheetData();
                    SheetViewModel.updateDnd(sheetDAndD);
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(FeatureInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentFeatureInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(FeatureInfo.this.requireActivity()));
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                classes = sheet.getClassFeatures();
                subclasses = sheet.getSubclasses();
                extraFeatures = sheet.getFeatures();
                int level = sheet.getLevel();
                ArrayList<Feature> features = new ArrayList<>();
                sortFeatures(classes, subclasses, extraFeatures, features, level);
                recyclerViewAdapterClassesInfo = new RecyclerViewAdapterClassesInfo(features, FeatureInfo.this.requireActivity(), this);
                binding.recyclerView.setAdapter(recyclerViewAdapterClassesInfo);
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
            Intent intent = new Intent(getActivity(), AddFeature.class);
            openAddFeature(intent);
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
    public void onContactClick(int position)
    {

    }

    public void openAddFeature(Intent intent)
    {
        launchAddFeature.launch(intent);
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
        sheetDAndD.setFeatures(extraFeatures);
        return sheetDAndD;
    }

    private void sortFeatures(ArrayList<Classes> classes, ArrayList<Subclass> subclasses, ArrayList<Feature> extraFeatures, ArrayList<Feature> features, int level)
    {
        ArrayList<Feature> classFeatures = new ArrayList<>();
        for (int i = 0; i < classes.size(); i++)
        {
            ArrayList<Feature> temp = classes.get(i).getClassFeatures();
            for (int j = 0; j < temp.size(); j++)
            {
                if (temp.get(j).getLevel() <= level)
                {
                    classFeatures.add(temp.get(j));
                }
            }
        }
        ArrayList<Feature> subclassFeatures = new ArrayList<>();
        for (int i = 0; i < subclasses.size(); i++)
        {
            ArrayList<Feature> temp = subclasses.get(i).getFeatures();
            for (int j = 0; j < temp.size(); j++)
            {
                if (temp.get(j).getLevel() <= level)
                {
                    subclassFeatures.add(temp.get(j));
                }
            }
        }
        ArrayList<Feature> aux = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < classFeatures.size() && j < subclassFeatures.size())
        {
            if (classFeatures.get(i).getLevel() < subclassFeatures.get(j).getLevel())
            {
                aux.add(classFeatures.get(i));
                i++;
            }
            else
            {
                aux.add(subclassFeatures.get(j));
                j++;
            }
        }
        while (i < classFeatures.size())
        {
            aux.add(classFeatures.get(i));
            i++;
        }
        while (j < subclassFeatures.size())
        {
            aux.add(subclassFeatures.get(j));
            j++;
        }
        i = 0;
        j = 0;
        while (i < aux.size() && j < extraFeatures.size())
        {
            if (aux.get(i).getLevel() < extraFeatures.get(j).getLevel())
            {
                features.add(aux.get(i));
                i++;
            }
            else
            {
                features.add(extraFeatures.get(j));
                j++;
            }
        }
        while (i < aux.size())
        {
            features.add(aux.get(i));
            i++;
        }
        while (j < extraFeatures.size())
        {
            features.add(extraFeatures.get(j));
            j++;
        }
    }
}