package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ecnav.ficharpg.databinding.ActivityCreateSpellBinding;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

public class CreateSpell extends AppCompatActivity
{
    private ActivityCreateSpellBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        DynamicColors.applyToActivitiesIfAvailable(getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_spell);
    }
}