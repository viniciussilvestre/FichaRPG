package com.ecnav.ficharpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ecnav.ficharpg.databinding.ActivityCharacterSheetBinding;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CharacterSheet extends AppCompatActivity
{
    private ActivityCharacterSheetBinding binding;
    private SheetViewModel sheetViewModel;
    private IdViewModel idViewModel;
    private int id;
    private int sheetType;
    private SheetDAndD sheetDAndD;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            id = data.getInt(Util.CHARACTER_ID);
        }
        idViewModel = new ViewModelProvider(this).get(IdViewModel.class);
        idViewModel.setId(id);

        binding = ActivityCharacterSheetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.main_stat, R.id.proficiency_info, R.id.class_info, R.id.subclass_info).build();
        NavController navController = getNavController();
        //Navigation.findNavController(this, R.id.fragmentContainerView);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(CharacterSheet.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getCharacterDnd(id).observe(this, sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
            }
            else
            {
                finish();
            }
        });
    }

    @NonNull
    private NavController getNavController()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (!(fragment instanceof NavHostFragment))
        {
            throw new IllegalStateException("Activity " + this + " does not have a NavHostFragment");
        }
        return ((NavHostFragment) fragment).getNavController();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sheet_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete)
        {
            SheetViewModel.deleteDnd(sheetDAndD);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_sheet);
//        int parameterForFragment = id;
//        Bundle dataForFragment = new Bundle();
//        dataForFragment.putInt(Util.PARAMETER_FOR_FRAGMENT, parameterForFragment);
//        fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MainStats.class, dataForFragment).setReorderingAllowed(true).commit();
//        MenuItem stat = binding.bottomNavigationView.getMenu().getItem(0);
//        MenuItem proficiency = binding.bottomNavigationView.getMenu().getItem(1);
//        MenuItem classInfo = binding.bottomNavigationView.getMenu().getItem(2);
//        MenuItem subclassInfo = binding.bottomNavigationView.getMenu().getItem(3);
//        binding.bottomNavigationView.setOnItemSelectedListener(item ->
//        {
//            if (item == stat)
//            {
//                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, MainStats.class, dataForFragment).setReorderingAllowed(true).commit();
//                stat.setChecked(true);
//                proficiency.setChecked(false);
//                classInfo.setChecked(false);
//                subclassInfo.setChecked(false);
//                return true;
//            }
//            else if (item == proficiency)
//            {
//                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, Proficiencys.class, dataForFragment).setReorderingAllowed(true).commit();
//                stat.setChecked(false);
//                proficiency.setChecked(true);
//                classInfo.setChecked(false);
//                subclassInfo.setChecked(false);
//                return true;
//            }
//            else if (item == classInfo)
//            {
//                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, ClassInfo.class, dataForFragment).setReorderingAllowed(true).commit();
//                stat.setChecked(false);
//                proficiency.setChecked(false);
//                classInfo.setChecked(true);
//                subclassInfo.setChecked(false);
//                return true;
//            }
//            else if (item == subclassInfo)
//            {
//                fragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SubclassInfo.class, dataForFragment).setReorderingAllowed(true).commit();
//                stat.setChecked(false);
//                proficiency.setChecked(false);
//                classInfo.setChecked(false);
//                subclassInfo.setChecked(true);
//                return true;
//            }
//            return false;
//        });

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