package com.ecnav.ficharpg.ui.addthings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityAddSpellBinding;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;

public class AddSpell extends AppCompatActivity
{
    private ActivityAddSpellBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        DynamicColors.applyToActivitiesIfAvailable(getApplication());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_spell);

        binding.switchMaterial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (binding.switchMaterial.isChecked())
                {
                    binding.spellComponents.setVisibility(View.VISIBLE);
                }
                else
                {
                    binding.spellComponents.setVisibility(View.GONE);
                }
            }
        });

        binding.saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(Util.SPELL_NAME, binding.spellName.getText().toString());
                replyIntent.putExtra(Util.SPELL_LEVEL, Integer.parseInt(binding.spellLevel.getText().toString()));
                replyIntent.putExtra(Util.SPELL_DESCRIPTION, binding.spellDescription.getText().toString());
                replyIntent.putExtra(Util.SPELL_SOMATIC, binding.switchSomatic.isChecked());
                replyIntent.putExtra(Util.SPELL_VERBAL, binding.switchVerbal.isChecked());
                replyIntent.putExtra(Util.SPELL_MATERIAL, binding.switchMaterial.isChecked());
                replyIntent.putExtra(Util.SPELL_COMPONENTS, binding.spellComponents.getText().toString());
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }
}