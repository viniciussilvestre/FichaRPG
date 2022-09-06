package com.ecnav.ficharpg.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClasses;
import com.ecnav.ficharpg.databinding.ActivityClassChooserBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.ui.addthings.AddClass;
import com.ecnav.ficharpg.util.DisplayClassSubclassInfo;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

import java.util.Objects;

public class ClassChooser extends AppCompatActivity implements RecyclerViewAdapterClasses.OnContactClickListener
{
    private ActivityClassChooserBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterClasses recyclerViewAdapter;

    ActivityResultLauncher<Intent> launchAddClass = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                }
            }
    );

    ActivityResultLauncher<Intent> launchClassesInfo = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(Util.CHOSEN_CLASS_ID, data.getIntExtra(Util.CHOSEN_CLASS_ID, 0));
                    replyIntent.putExtra(Util.CHOSEN_CLASS_BOOLEAN, data.getBooleanExtra(Util.CHOSEN_CLASS_BOOLEAN, false));
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_class_chooser);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(ClassChooser.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getAllClassesDnd().observe(this, classes ->
        {
            recyclerViewAdapter = new RecyclerViewAdapterClasses(classes, ClassChooser.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
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
            Intent intent = new Intent(ClassChooser.this, AddClass.class);
            openAddClass(intent);
        });
    }

    @Override
    public void onContactClick(int position)
    {
        Classes classes = Objects.requireNonNull(sheetViewModel.getAllClassesDnd().getValue()).get(position);
        Intent intent = new Intent(ClassChooser.this, DisplayClassSubclassInfo.class);
        intent.putExtra(Util.CLASS_ID, classes.getClassId());
        intent.putExtra(Util.CLASS_OR_SUBCLASS, Util.CLASS_INFO_FLAG);
        openClassesInfo(intent);
    }

    public void openAddClass(Intent intent)
    {
        launchAddClass.launch(intent);
    }

    public void openClassesInfo(Intent intent)
    {
        launchClassesInfo.launch(intent);
    }
}