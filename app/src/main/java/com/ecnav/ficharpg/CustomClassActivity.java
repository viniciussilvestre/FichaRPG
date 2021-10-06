package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ecnav.ficharpg.databinding.ActivityCustomClassBinding;

public class CustomClassActivity extends AppCompatActivity
{
    private ActivityCustomClassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_custom_class);
    }
}