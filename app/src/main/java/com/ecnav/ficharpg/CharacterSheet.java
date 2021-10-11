package com.ecnav.ficharpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
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
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(CharacterSheet.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getCharacterDnd(id).observe(this, sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                if (!sheet.isHasSubClass())
                {
                    navView.getMenu().findItem(R.id.subclass_info).setEnabled(false);
                    navView.getMenu().findItem(R.id.subclass_info).setVisible(false);
                }
                else
                {
                    navView.getMenu().findItem(R.id.subclass_info).setEnabled(true);
                    navView.getMenu().findItem(R.id.subclass_info).setVisible(true);
                }
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
        getMenuInflater().inflate(R.menu.menu_sheet_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete)
        {
            SheetViewModel.deleteDnd(sheetDAndD);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}