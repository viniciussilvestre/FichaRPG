package com.ecnav.ficharpg.ui.levelup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.ecnav.ficharpg.CharacterSheet;
import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityLevelUpBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

public class LevelUp extends AppCompatActivity
{
    private ActivityLevelUpBinding binding;
    private int id;
    private SheetDAndD sheetDAndD;
    private SheetViewModel sheetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            id = data.getInt(Util.CHARACTER_ID);
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_level_up);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(LevelUp.this.getApplication()).create(SheetViewModel.class);
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
}