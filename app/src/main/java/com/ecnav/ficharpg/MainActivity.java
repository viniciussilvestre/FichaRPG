package com.ecnav.ficharpg;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapter;
import com.ecnav.ficharpg.data.AnswerListAsyncResponse;
import com.ecnav.ficharpg.data.Repository;
import com.ecnav.ficharpg.databinding.ActivityMainBinding;
import com.ecnav.ficharpg.model.ClassFeatures;
import com.ecnav.ficharpg.model.IdViewModel;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;
import com.ecnav.ficharpg.controller.AppController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener
{
    private ActivityMainBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<ClassFeatures> classFeaturesList;

    ActivityResultLauncher<Intent> launchCharacterSheet = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateSpell = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateCharacter = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                        SheetDAndD sheetDAndD = new SheetDAndD();
                        sheetDAndD.setName(data.getStringExtra(Util.NAME_REPLY));
                        sheetDAndD.setCharacterClass(data.getStringExtra(Util.CLASS_REPLY));
                        sheetDAndD.setLevel(data.getIntExtra(Util.LEVEL_REPLY, 1));
                        sheetDAndD.setRace(data.getStringExtra(Util.RACE_REPLY));
                        sheetDAndD.setBackground(data.getStringExtra(Util.BACKGROUND_REPLY));
                        sheetDAndD.setAlignment(data.getStringExtra(Util.ALIGNMENT_REPLY));
                        sheetDAndD.setArmorClass(data.getIntExtra(Util.ARMORCLASS_REPLY, 10));
                        sheetDAndD.setHitPoints(data.getIntExtra(Util.HITPOINTS_REPLY, 0));
                        SheetViewModel.insertDnd(sheetDAndD);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getAllSheetsDnd().observe(this, sheets ->
        {
            recyclerViewAdapter = new RecyclerViewAdapter(sheets, MainActivity.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });

        binding.addButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, CreateCharacter.class);
            intent.putExtra(Util.SHEETTYPE, Util.DUNGEONS_AND_DRAGONS);
            openCreateCharacter(intent);
        });
    }

    public void openCreateSpell(Intent intent)
    {
        launchCreateSpell.launch(intent);
    }

    public void openCreateCharacter(Intent intent)
    {
        launchCreateCharacter.launch(intent);
    }

    public void openCharacterSheet(Intent intent)
    {
        launchCharacterSheet.launch(intent);
    }

    @Override
    public void onContactClick(int position)
    {
        SheetDAndD sheetDAndD = Objects.requireNonNull(sheetViewModel.getAllSheetsDnd().getValue()).get(position);
        Intent intent = new Intent(MainActivity.this, CharacterSheet.class);
        intent.putExtra(Util.CHARACTER_ID, sheetDAndD.getId());
        openCharacterSheet(intent);
    }

    private void jumpAnimation()
    {
        Animation jump = AnimationUtils.loadAnimation(MainActivity.this, R.anim.jump_animation);
        binding.recyclerView.setAnimation(jump);
    }
}