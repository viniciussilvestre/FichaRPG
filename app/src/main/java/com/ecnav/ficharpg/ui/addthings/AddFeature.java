package com.ecnav.ficharpg.ui.addthings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityAddFeatureBinding;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.snackbar.Snackbar;

public class AddFeature extends AppCompatActivity
{
    private ActivityAddFeatureBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_feature);

        binding.saveButton.setOnClickListener(view ->
        {
            if (!binding.levelEditText.getText().toString().isEmpty() && !binding.featureNameEditText.getText().toString().isEmpty() && !binding.featureDescriptionEditText.getText().toString().isEmpty())
            {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(Util.FEATURE_LEVEL, binding.levelEditText.getText().toString());
                replyIntent.putExtra(Util.FEATURE_NAME, binding.featureNameEditText.getText().toString());
                replyIntent.putExtra(Util.FEATURE_DESCRIPTION, binding.featureDescriptionEditText.getText().toString());
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else
            {
                Snackbar.make(binding.saveButton, "Fields are empty",Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}