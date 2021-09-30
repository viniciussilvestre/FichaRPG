package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ecnav.ficharpg.databinding.ActivityCreateSpellBinding;

public class CreateSpell extends AppCompatActivity
{
    private ActivityCreateSpellBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_spell);
    }
}