package com.ecnav.ficharpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ecnav.ficharpg.databinding.ActivityCharacterSheetBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.classinfo.ClassInfo;
import com.ecnav.ficharpg.ui.mainstat.MainStats;
import com.ecnav.ficharpg.ui.proficiencys.Proficiencys;
import com.ecnav.ficharpg.ui.subclassinfo.SubclassInfo;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;
import java.util.Objects;

public class CharacterSheet extends AppCompatActivity
{
    private ActivityCharacterSheetBinding binding;
    private SheetViewModel sheetViewModel;
    private int id;
    private int sheetType;
    private SheetDAndD sheetDAndD;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getSupportFragmentManager();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_sheet);
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            id = data.getInt(Util.CHARACTER_ID);
        }
        int parameterForFragment = id;
        Bundle dataForFragment = new Bundle();
        dataForFragment.putInt(Util.PARAMETER_FOR_FRAGMENT, parameterForFragment);
        fragmentManager.beginTransaction().add(R.id.fragmentContainerView, MainStats.class, dataForFragment).setReorderingAllowed(true).commit();
        MenuItem stat = binding.bottomNavigationView.getMenu().getItem(0);
        MenuItem proficiency = binding.bottomNavigationView.getMenu().getItem(1);
        MenuItem classInfo = binding.bottomNavigationView.getMenu().getItem(2);
        MenuItem subclassInfo = binding.bottomNavigationView.getMenu().getItem(3);
        binding.bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if (item == stat)
            {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MainStats.class, dataForFragment).setReorderingAllowed(true).commit();
            }
            else if (item == proficiency)
            {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, Proficiencys.class, dataForFragment).setReorderingAllowed(true).commit();
            }
            else if (item == classInfo)
            {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ClassInfo.class, dataForFragment).setReorderingAllowed(true).commit();
            }
            else if (item == subclassInfo)
            {
                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SubclassInfo.class, dataForFragment).setReorderingAllowed(true).commit();
            }
            return false;
        });

//        binding = ActivityCharacterSheetBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(CharacterSheet.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getCharacterDnd(id).observe(this, sheet ->
        {
            if (sheet == null)
            {
                finish();
            }
        });
    }

//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        SheetDAndD sheetDAndD = getNewSheetData();
//        SheetViewModel.updateDnd(sheetDAndD);
//    }

//    public SheetDAndD getNewSheetData()
//    {
//        SheetDAndD sheetDAndD = new SheetDAndD();
//        sheetDAndD.setId(id);
//        sheetDAndD.setName(binding.characterNameText.getText().toString());
//        sheetDAndD.setCharacterClass(binding.classText.getText().toString());
//        sheetDAndD.setProficiencyBonus(Integer.parseInt(binding.proficiencyText.getText().toString()));
//        sheetDAndD.setSpeed(Integer.parseInt(binding.speedText.getText().toString()));
//        sheetDAndD.setArmorClass(Integer.parseInt(binding.armorClassText.getText().toString()));
//        sheetDAndD.setLevel(Integer.parseInt(binding.levelText.getText().toString()));
//        sheetDAndD.setRace(binding.raceText.getText().toString());
//        sheetDAndD.setBackground(binding.backgroundText.getText().toString());
//        sheetDAndD.setAlignment(binding.alignmentText.getText().toString());
//        sheetDAndD.setInitiative(Integer.parseInt(binding.initiativeText.getText().toString()));
//        sheetDAndD.setArmorClass(Integer.parseInt(binding.armorClassText.getText().toString()));
//        sheetDAndD.setHitPoints(Integer.parseInt(binding.healthText.getText().toString()));
//        sheetDAndD.setStrength(Integer.parseInt(binding.strenghtText.getText().toString()));
//        sheetDAndD.setDexterity(Integer.parseInt(binding.dexterityText.getText().toString()));
//        sheetDAndD.setConstitution(Integer.parseInt(binding.constitutionText.getText().toString()));
//        sheetDAndD.setIntelligence(Integer.parseInt(binding.intelligenceText.getText().toString()));
//        sheetDAndD.setWisdom(Integer.parseInt(binding.wisdomText.getText().toString()));
//        sheetDAndD.setCharisma(Integer.parseInt(binding.charismaText.getText().toString()));
//        return sheetDAndD;
//    }
}