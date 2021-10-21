package com.ecnav.ficharpg.ui.addthings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityAddSpellBinding;

public class AddSpell extends AppCompatActivity
{
    private ActivityAddSpellBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_spell);

        binding.saveButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });
    }
}