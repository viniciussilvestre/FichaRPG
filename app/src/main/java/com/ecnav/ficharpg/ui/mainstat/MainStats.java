package com.ecnav.ficharpg.ui.mainstat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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

import com.ecnav.ficharpg.databinding.FragmentMainStatsBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Equipment;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.ui.levelup.LevelUp;
import com.ecnav.ficharpg.util.Dice;
import com.ecnav.ficharpg.util.EquipmentType;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;
import java.util.Random;

public class MainStats extends Fragment
{
    private FragmentMainStatsBinding binding;
    private SheetViewModel sheetViewModel;
    private int id;
    private SheetDAndD sheetDAndD;

    ActivityResultLauncher<Intent> launchAddProfilePicture = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    Uri selectedImage = data.getData();
                    Log.d("TAG", ": " + selectedImage);
                    binding.imageView.setImageURI(selectedImage);
                }
            }
    );

    ActivityResultLauncher<Intent> launchLevelUp = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    if (data.getIntExtra(Util.RAND_OR_AVG_LIFE, 0) == Util.RAND_LIFE_VALUE)
                    {
                        Dice hitDice = sheetDAndD.getClassFeatures().get(0).getHitDice();
                        int random = 0;
                        if (hitDice == Dice.D6)
                        {
                            random = new Random().nextInt(1) + 5;
                        }
                        else if (hitDice == Dice.D8)
                        {
                            random = new Random().nextInt(1) + 7;
                        }
                        else if (hitDice == Dice.D10)
                        {
                            random = new Random().nextInt(1) + 9;
                        }
                        else if (hitDice == Dice.D12)
                        {
                            random = new Random().nextInt(1) + 11;
                        }
                        int currentHealth = Integer.parseInt(binding.healthText.getText().toString());
                        int conMod = (sheetDAndD.getConstitution() - 10)/2;
                        currentHealth = currentHealth + random + conMod;
                        binding.healthText.setText(String.valueOf(currentHealth));
                    }
                    else
                    {
                        int str = data.getIntExtra(Util.STR_REPLY, sheetDAndD.getStrength());
                        int dex = data.getIntExtra(Util.DEX_REPLY, sheetDAndD.getDexterity());
                        int con = data.getIntExtra(Util.CON_REPLY, sheetDAndD.getConstitution());
                        int inte = data.getIntExtra(Util.INT_REPLY, sheetDAndD.getIntelligence());
                        int wis = data.getIntExtra(Util.WIS_REPLY, sheetDAndD.getWisdom());
                        int cha = data.getIntExtra(Util.CHA_REPLY, sheetDAndD.getCharisma());
                        binding.strenghtText.setText(String.valueOf(str));
                        binding.dexterityText.setText(String.valueOf(dex));
                        binding.constitutionText.setText(String.valueOf(con));
                        binding.intelligenceText.setText(String.valueOf(inte));
                        binding.wisdomText.setText(String.valueOf(wis));
                        binding.charismaText.setText(String.valueOf(cha));
                        Dice hitDice = sheetDAndD.getClassFeatures().get(0).getHitDice();
                        int average = 0;
                        if (hitDice == Dice.D6)
                        {
                            average = 4;
                        }
                        else if (hitDice == Dice.D8)
                        {
                            average = 5;
                        }
                        else if (hitDice == Dice.D10)
                        {
                            average = 6;
                        }
                        else if (hitDice == Dice.D12)
                        {
                            average = 7;
                        }
                        int currentHealth = Integer.parseInt(binding.healthText.getText().toString());
                        int conMod = (con - 10)/2;
                        int healthDif = 0;
                        if (conMod != ((sheetDAndD.getConstitution() - 10)/2))
                        {
                            int conDif = conMod - (sheetDAndD.getConstitution() - 10)/2;
                            healthDif = conDif*(Integer.parseInt(binding.levelText.getText().toString()) - 1);
                        }
                        currentHealth = currentHealth + average + conMod + healthDif;
                        binding.healthText.setText(String.valueOf(currentHealth));
                        SheetDAndD sheetDAndD = getNewSheetData();
                        SheetViewModel.updateDnd(sheetDAndD);
                    }
                }
            }
    );

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

        binding.imageView.setOnClickListener(v ->
        {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            openAddImage(intent);
        });

        binding.levelText.addTextChangedListener(new TextWatcher()
        {
            int oldLevel;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
                oldLevel = sheetDAndD.getLevel();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!binding.levelText.getText().toString().isEmpty())
                {
                    int level = Integer.parseInt(binding.levelText.getText().toString());
                    if (level != sheetDAndD.getLevel())
                    {
                        if (oldLevel < level && level < 21)
                        {
                            int levelDifference = level - oldLevel;
                            if (levelDifference > 0)
                            {
                                while (levelDifference != 0)
                                {
                                    Intent intent = new Intent(getActivity(), LevelUp.class);
                                    intent.putExtra(Util.CHARACTER_ID, id);
                                    ArrayList<Classes> classes = sheetDAndD.getClassFeatures();
                                    ArrayList<Subclass> subclasses = sheetDAndD.getSubclasses();
                                    ArrayList<Feature> extraFeatures = sheetDAndD.getFeatures();
                                    ArrayList<Feature> features = new ArrayList<>();
                                    sortFeatures(classes, subclasses, extraFeatures, features, level);
                                    if (features.get(features.size() - 1).getLevel() >= level)
                                    {
                                        if (features.get(features.size() - 1).getNome().contains("(Optional)"))
                                        {
                                            if (features.get(features.size() - 2).getLevel() >= level)
                                            {
                                                if (features.get(features.size() - 2).getNome().equals("Ability Score Improvement"))
                                                {
                                                    intent.putExtra(Util.POINTS_OR_SKILL, Util.POINTS_VALUE);
                                                }
                                            }
                                            else
                                            {
                                                intent.putExtra(Util.POINTS_OR_SKILL, Util.SKILL_VALUE);
                                                intent.putExtra(Util.FEATURE_NAME, features.get(features.size() - 1).getNome());
                                                intent.putExtra(Util.FEATURE_DESCRIPTION, features.get(features.size() - 1).getDescription());
                                            }
                                        }
                                        else
                                        {
                                            if (features.get(features.size() - 1).getNome().equals("Ability Score Improvement"))
                                            {
                                                intent.putExtra(Util.POINTS_OR_SKILL, Util.POINTS_VALUE);
                                            }
                                            else
                                            {
                                                intent.putExtra(Util.POINTS_OR_SKILL, Util.SKILL_VALUE);
                                                intent.putExtra(Util.FEATURE_NAME, features.get(features.size() - 1).getNome());
                                                intent.putExtra(Util.FEATURE_DESCRIPTION, features.get(features.size() - 1).getDescription());
                                            }
                                        }
                                    }

//                                    if (features.get(features.size() - 1).getNome().contains("(Optional)"))
//                                    {
//                                        if (features.get(features.size() - 2).getLevel() >= level)
//                                        {
//                                            if (features.get(features.size() - 2).getNome().equals("Ability Score Improvement"))
//                                            {
//                                                intent.putExtra(Util.POINTS_OR_SKILL, Util.POINTS_VALUE);
//                                            }
//                                        }
//                                    }
//                                    else
//                                    {
//                                        if (features.get(features.size() - 1).getNome().equals("Ability Score Improvement"))
//                                        {
//                                            intent.putExtra(Util.POINTS_OR_SKILL, Util.POINTS_VALUE);
//                                        }
//                                        else
//                                        {
//                                            intent.putExtra(Util.POINTS_OR_SKILL, Util.SKILL_VALUE);
//                                            intent.putExtra(Util.FEATURE_NAME, features.get(features.size() - 1).getNome());
//                                            intent.putExtra(Util.FEATURE_DESCRIPTION, features.get(features.size() - 1).getDescription());
//                                        }
//                                    }

                                    openLevelUp(intent);
                                    levelDifference--;
                                }
                            }
                        }
                    }
                }
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
                    int armorClass = 10;
                    boolean hasArmor = false;
                    ArrayList<Equipment> equipments = sheetDAndD.getEquipments();
                    for (int i = 0; i < equipments.size(); i++)
                    {
                        if (equipments.get(i).getEquipmentType() == EquipmentType.LIGHT_ARMOR || equipments.get(i).getEquipmentType() == EquipmentType.MEDIUM_ARMOR || equipments.get(i).getEquipmentType() == EquipmentType.HEAVY_ARMOR)
                        {
                            if (equipments.get(i).getEquipmentType() == EquipmentType.LIGHT_ARMOR)
                            {
                                armorClass = equipments.get(i).getArmorClass() + dexMod;
                                hasArmor = true;
                            }
                            else if (equipments.get(i).getEquipmentType() == EquipmentType.MEDIUM_ARMOR)
                            {
                                if (dexMod <= 2)
                                {
                                    armorClass = equipments.get(i).getArmorClass() + dexMod;
                                }
                                else
                                {
                                    armorClass = equipments.get(i).getArmorClass() + 2;
                                }
                                hasArmor = true;
                            }
                            else
                            {
                                armorClass = equipments.get(i).getArmorClass();
                                hasArmor = true;
                            }
                        }
                    }
                    if (!hasArmor)
                    {
                        if (dexMod > 0)
                        {
                            armorClass = 10 + dexMod;
                        }
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

    private void openAddImage(Intent intent)
    {
        launchAddProfilePicture.launch(intent);
    }

    private void openLevelUp(Intent intent)
    {
        launchLevelUp.launch(intent);
    }

    private SheetDAndD getNewSheetData()
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