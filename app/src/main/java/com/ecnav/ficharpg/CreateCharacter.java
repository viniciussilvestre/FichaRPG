package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ecnav.ficharpg.databinding.ActivityCreateCharacterBinding;
import com.ecnav.ficharpg.util.Util;

public class CreateCharacter extends AppCompatActivity
{
    private ActivityCreateCharacterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_character);
        binding.saveButton.setOnClickListener(view ->
        {
            Intent replyIntent = new Intent();
            if (!TextUtils.isEmpty(binding.characterNameField.getText()) && !TextUtils.isEmpty(binding.characterClassField.getText()) && !TextUtils.isEmpty(binding.characterBackgroundField.getText()) && !TextUtils.isEmpty(binding.characterRaceField.getText()) && !TextUtils.isEmpty(binding.characterLevelField.getText()))
            {
                String name = binding.characterNameField.getText().toString();
                String characterClass = binding.characterClassField.getText().toString();
                String background = binding.characterBackgroundField.getText().toString();
                String race = binding.characterRaceField.getText().toString();
                String stringLevel = binding.characterLevelField.getText().toString();
                int intLevel = Integer.parseInt(stringLevel);
                String alignment = binding.characterAlignmentField.getText().toString();
                String ca = binding.armorClassField.getText().toString();
                int intCa = Integer.parseInt(ca);
                String hp = binding.hitPointsField.getText().toString();
                int intHp = Integer.parseInt(hp);
                String init = binding.initiativeTextField.getText().toString();
                int intInit = Integer.parseInt(init);
                replyIntent.putExtra(Util.NAME_REPLY, name);
                replyIntent.putExtra(Util.CLASS_REPLY, characterClass);
                replyIntent.putExtra(Util.BACKGROUND_REPLY, background);
                replyIntent.putExtra(Util.RACE_REPLY, race);
                replyIntent.putExtra(Util.LEVEL_REPLY, intLevel);
                replyIntent.putExtra(Util.ALIGNMENT_REPLY, alignment);
                replyIntent.putExtra(Util.ARMORCLASS_REPLY, intCa);
                replyIntent.putExtra(Util.HITPOINTS_REPLY, intHp);
                replyIntent.putExtra(Util.INITIATIVE_REPLY, intInit);
                setResult(RESULT_OK, replyIntent);
            }
            else
            {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();
        });
    }
}