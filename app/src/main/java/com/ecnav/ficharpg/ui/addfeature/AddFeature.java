package com.ecnav.ficharpg.ui.addfeature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityAddFeatureBinding;

public class AddFeature extends AppCompatActivity
{
    private ActivityAddFeatureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_feature);

        binding.saveButton.setOnClickListener(v ->
        {
            finish();
        });
    }
}