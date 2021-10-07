package com.ecnav.ficharpg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClasses;
import com.ecnav.ficharpg.databinding.ActivityClassChooserBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

import java.util.Objects;

public class ClassChooser extends AppCompatActivity implements RecyclerViewAdapterClasses.OnContactClickListener
{
    private ActivityClassChooserBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterClasses recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
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

        binding.addButton.setOnClickListener(v ->
        {

        });
    }

    @Override
    public void onContactClick(int position)
    {
        Classes classes = Objects.requireNonNull(sheetViewModel.getAllClassesDnd().getValue()).get(position);
        Intent intent = new Intent();
        intent.putExtra(Util.CLASS_ID, classes.getClassId());
    }
}