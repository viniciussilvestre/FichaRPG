package com.ecnav.ficharpg.ui.equipment;

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

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterEquipment;
import com.ecnav.ficharpg.databinding.FragmentEquipmentInfoBinding;
import com.ecnav.ficharpg.model.Equipment;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.addthings.AddEquipment;
import com.ecnav.ficharpg.util.EquipmentType;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;
import java.util.Objects;

public class EquipmentInfo extends Fragment implements RecyclerViewAdapterEquipment.OnContactClickListener
{
    private FragmentEquipmentInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterEquipment recyclerViewAdapterEquipment;
    private int id;
    private ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
    private SheetDAndD sheetDAndD;

    ActivityResultLauncher<Intent> launchAddEquipment = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    equipmentArrayList = this.sheetDAndD.getEquipments();
                    EquipmentType equipmentType = (EquipmentType) data.getSerializableExtra(Util.EQUIP_TYPE);
                    String name;
                    String description;
                    int armorClass;
                    int usages;
                    int amount;
                    int attackBonus;
                    int damageBonus;
                    Equipment equipment = new Equipment();

                    if (equipmentType == EquipmentType.HEAVY_ARMOR || equipmentType == EquipmentType.LIGHT_ARMOR || equipmentType == EquipmentType.MEDIUM_ARMOR)
                    {
                        name = data.getStringExtra(Util.EQUIP_NAME);
                        description = data.getStringExtra(Util.EQUIP_DESCRIPTION);
                        armorClass = data.getIntExtra(Util.ARMORCLASS_REPLY, 0);
                        usages = data.getIntExtra(Util.EQUIP_USAGES, 0);
                        equipment.setEquipmentType(equipmentType);
                        equipment.setNome(name);
                        equipment.setDescription(description);
                        equipment.setArmorClass(armorClass);
                        equipment.setUsages(usages);
                        equipmentArrayList.add(equipment);
                    }
                    else if (equipmentType == EquipmentType.WEAPON)
                    {
                        name = data.getStringExtra(Util.EQUIP_NAME);
                        description = data.getStringExtra(Util.EQUIP_DESCRIPTION);
                        usages = data.getIntExtra(Util.EQUIP_USAGES, 0);
                        attackBonus = data.getIntExtra(Util.EQUIP_ATTACK, 0);
                        damageBonus = data.getIntExtra(Util.EQUIP_DAMAGE, 0);
                        equipment.setEquipmentType(equipmentType);
                        equipment.setNome(name);
                        equipment.setDescription(description);
                        equipment.setUsages(usages);
                        equipment.setAttackDiceBonus(attackBonus);
                        equipment.setDamageDiceBonus(damageBonus);
                        equipmentArrayList.add(equipment);
                    }
                    else if (equipmentType == EquipmentType.SHIELD)
                    {
                        name = data.getStringExtra(Util.EQUIP_NAME);
                        description = data.getStringExtra(Util.EQUIP_DESCRIPTION);
                        armorClass = data.getIntExtra(Util.ARMORCLASS_REPLY, 0);
                        usages = data.getIntExtra(Util.EQUIP_USAGES, 0);
                        equipment.setEquipmentType(equipmentType);
                        equipment.setNome(name);
                        equipment.setDescription(description);
                        equipment.setUsages(usages);
                        equipment.setArmorClass(armorClass);
                        equipmentArrayList.add(equipment);
                    }
                    else if (equipmentType == EquipmentType.CONSUMABLE)
                    {
                        name = data.getStringExtra(Util.EQUIP_NAME);
                        description = data.getStringExtra(Util.EQUIP_DESCRIPTION);
                        amount = data.getIntExtra(Util.EQUIP_AMOUNT, 0);
                        equipment.setEquipmentType(equipmentType);
                        equipment.setNome(name);
                        equipment.setDescription(description);
                        equipment.setAmount(amount);
                        equipmentArrayList.add(equipment);
                    }
                    else if (equipmentType == EquipmentType.AMMO)
                    {
                        name = data.getStringExtra(Util.EQUIP_NAME);
                        description = data.getStringExtra(Util.EQUIP_DESCRIPTION);
                        amount = data.getIntExtra(Util.EQUIP_AMOUNT, 0);
                        equipment.setEquipmentType(equipmentType);
                        equipment.setNome(name);
                        equipment.setDescription(description);
                        equipment.setAmount(amount);
                        equipmentArrayList.add(equipment);
                    }
                    SheetDAndD sheetDAndD = getNewSheetData();
                    SheetViewModel.updateDnd(sheetDAndD);
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(EquipmentInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
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
                equipmentArrayList = sheet.getEquipments();
                recyclerViewAdapterEquipment = new RecyclerViewAdapterEquipment(equipmentArrayList, EquipmentInfo.this.requireActivity(), this);
                binding.recyclerView.setAdapter(recyclerViewAdapterEquipment);
            }
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.canScrollVertically(Integer.MIN_VALUE))
                {
                    binding.addButton.hide();
                }
                else
                {
                    binding.addButton.show();
                }
            }

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
            Intent intent = new Intent(getActivity(), AddEquipment.class);
            openAddEquipment(intent);
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

    public void openAddEquipment(Intent intent)
    {
        launchAddEquipment.launch(intent);
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
        sheetDAndD.setFeatures(this.sheetDAndD.getFeatures());
        sheetDAndD.setEquipments(equipmentArrayList);
        sheetDAndD.setLevel0(this.sheetDAndD.getLevel0());
        sheetDAndD.setLevel1(this.sheetDAndD.getLevel1());
        sheetDAndD.setLevel2(this.sheetDAndD.getLevel2());
        sheetDAndD.setLevel3(this.sheetDAndD.getLevel3());
        sheetDAndD.setLevel4(this.sheetDAndD.getLevel4());
        sheetDAndD.setLevel5(this.sheetDAndD.getLevel5());
        sheetDAndD.setLevel6(this.sheetDAndD.getLevel6());
        sheetDAndD.setLevel7(this.sheetDAndD.getLevel7());
        sheetDAndD.setLevel8(this.sheetDAndD.getLevel8());
        sheetDAndD.setLevel9(this.sheetDAndD.getLevel9());
        return sheetDAndD;
    }
}