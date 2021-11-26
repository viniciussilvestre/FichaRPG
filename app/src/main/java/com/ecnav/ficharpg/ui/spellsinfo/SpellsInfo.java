package com.ecnav.ficharpg.ui.spellsinfo;

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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterSpell;
import com.ecnav.ficharpg.databinding.FragmentSpellsInfoBinding;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Spell;
import com.ecnav.ficharpg.ui.addthings.AddSpell;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;

public class SpellsInfo extends Fragment implements RecyclerViewAdapterSpell.OnContactClickListener
{
    private FragmentSpellsInfoBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterSpell recyclerViewAdapterSpell;
    private int id;
    private static ArrayList<Spell> spellArrayList = new ArrayList<>();
    private static ArrayList<Spell> level0;
    private static ArrayList<Spell> level1;
    private static ArrayList<Spell> level2;
    private static ArrayList<Spell> level3;
    private static ArrayList<Spell> level4;
    private static ArrayList<Spell> level5;
    private static ArrayList<Spell> level6;
    private static ArrayList<Spell> level7;
    private static ArrayList<Spell> level8;
    private static ArrayList<Spell> level9;
    private SheetDAndD sheetDAndD;

    ActivityResultLauncher<Intent> launchAddSpell = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    Spell spell = new Spell(data.getStringExtra(Util.SPELL_NAME), data.getIntExtra(Util.SPELL_LEVEL, 0), data.getStringExtra(Util.SPELL_DESCRIPTION), data.getBooleanExtra(Util.SPELL_SOMATIC, false), data.getBooleanExtra(Util.SPELL_VERBAL, false), data.getBooleanExtra(Util.SPELL_MATERIAL, false), data.getStringExtra(Util.SPELL_COMPONENTS));
                    addSpell(spell);
                    updateSheet();
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(SpellsInfo.this.requireActivity().getApplication()).create(SheetViewModel.class);
        IdViewModel idViewModel = new ViewModelProvider(requireActivity()).get(IdViewModel.class);
        id = idViewModel.getSelectedItem();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentSpellsInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(SpellsInfo.this.requireActivity()));
        sheetViewModel.getCharacterDnd(id).observe(getViewLifecycleOwner(), sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                level0 = sheet.getLevel0();
                level1 = sheet.getLevel1();
                level2 = sheet.getLevel2();
                level3 = sheet.getLevel3();
                level4 = sheet.getLevel4();
                level5 = sheet.getLevel5();
                level6 = sheet.getLevel6();
                level7 = sheet.getLevel7();
                level8 = sheet.getLevel8();
                level9 = sheet.getLevel9();
                spellArrayList = getSpellArrayList(level0, level1, level2, level3, level4, level5, level6, level7, level8, level9);
                recyclerViewAdapterSpell = new RecyclerViewAdapterSpell(spellArrayList, SpellsInfo.this.requireActivity(), this);
                binding.recyclerView.setAdapter(recyclerViewAdapterSpell);
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
            Intent intent = new Intent(getActivity(), AddSpell.class);
            openAddSpell(intent);
        });
        return root;
    }

    public void openAddSpell(Intent intent)
    {
        launchAddSpell.launch(intent);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        updateSheet();
    }

    @Override
    public void onContactClick(int position)
    {

    }

    public static void removeSpell(Spell removedSpell)
    {
        if (removedSpell != null)
        {
            spellArrayList.remove(removedSpell);
            removeSpellFromCharacter(removedSpell);
        }
    }

    private static void removeSpellFromCharacter(Spell spell)
    {
        if (spell.getSpellLevel() == 0)
        {
            level0.remove(spell);
        }
        if (spell.getSpellLevel() == 1)
        {
            level1.remove(spell);
        }
        if (spell.getSpellLevel() == 2)
        {
            level2.remove(spell);
        }
        if (spell.getSpellLevel() == 3)
        {
            level3.remove(spell);
        }
        if (spell.getSpellLevel() == 4)
        {
            level4.remove(spell);
        }
        if (spell.getSpellLevel() == 5)
        {
            level5.remove(spell);
        }
        if (spell.getSpellLevel() == 6)
        {
            level6.remove(spell);
        }
        if (spell.getSpellLevel() == 7)
        {
            level7.remove(spell);
        }
        if (spell.getSpellLevel() == 8)
        {
            level8.remove(spell);
        }
        if (spell.getSpellLevel() == 9)
        {
            level9.remove(spell);
        }
    }

    public ArrayList<Spell> getSpellArrayList(ArrayList<Spell> level0, ArrayList<Spell> level1, ArrayList<Spell> level2, ArrayList<Spell> level3, ArrayList<Spell> level4, ArrayList<Spell> level5, ArrayList<Spell> level6, ArrayList<Spell> level7, ArrayList<Spell> level8, ArrayList<Spell> level9)
    {
        ArrayList<Spell> spells = new ArrayList<>();
        spells.addAll(level0);
        spells.addAll(level1);
        spells.addAll(level2);
        spells.addAll(level3);
        spells.addAll(level4);
        spells.addAll(level5);
        spells.addAll(level6);
        spells.addAll(level7);
        spells.addAll(level8);
        spells.addAll(level9);
        return spells;
    }

    public void updateSheet()
    {
        SheetDAndD sheetDAndD = getNewSheetData();
        SheetViewModel.updateDnd(sheetDAndD);
    }

    public void addSpell(Spell spell)
    {
        if (spell.getSpellLevel() == 0)
        {
            level0.add(spell);
        }
        if (spell.getSpellLevel() == 1)
        {
            level1.add(spell);
        }
        if (spell.getSpellLevel() == 2)
        {
            level2.add(spell);
        }
        if (spell.getSpellLevel() == 3)
        {
            level3.add(spell);
        }
        if (spell.getSpellLevel() == 4)
        {
            level4.add(spell);
        }
        if (spell.getSpellLevel() == 5)
        {
            level5.add(spell);
        }
        if (spell.getSpellLevel() == 6)
        {
            level6.add(spell);
        }
        if (spell.getSpellLevel() == 7)
        {
            level7.add(spell);
        }
        if (spell.getSpellLevel() == 8)
        {
            level8.add(spell);
        }
        if (spell.getSpellLevel() == 9)
        {
            level9.add(spell);
        }
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
        sheetDAndD.setEquipments(this.sheetDAndD.getEquipments());
        sheetDAndD.setLevel0(this.level0);
        sheetDAndD.setLevel1(this.level1);
        sheetDAndD.setLevel2(this.level2);
        sheetDAndD.setLevel3(this.level3);
        sheetDAndD.setLevel4(this.level4);
        sheetDAndD.setLevel5(this.level5);
        sheetDAndD.setLevel6(this.level6);
        sheetDAndD.setLevel7(this.level7);
        sheetDAndD.setLevel8(this.level8);
        sheetDAndD.setLevel9(this.level9);
        return sheetDAndD;
    }
}