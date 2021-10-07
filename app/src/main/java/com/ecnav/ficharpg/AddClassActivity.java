package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ecnav.ficharpg.databinding.ActivityAddClassBinding;

public class AddClassActivity extends AppCompatActivity
{
    private ActivityAddClassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_class);
    }
}