package com.ecnav.ficharpg.ui.levelup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivityLevelUpBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.snackbar.Snackbar;


public class LevelUp extends AppCompatActivity
{
    private ActivityLevelUpBinding binding;
    private int id;
    private int skillOrPoints;
    private String featureName;
    private String featureDescription;
    private SheetDAndD sheetDAndD;
    private SheetViewModel sheetViewModel;
    private int points;
    private int str;
    private int dex;
    private int inte;
    private int cha;
    private int wis;
    private int con;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            id = data.getInt(Util.CHARACTER_ID);
            skillOrPoints = data.getInt(Util.POINTS_OR_SKILL);
            if (skillOrPoints == Util.SKILL_VALUE)
            {
                featureName = data.getString(Util.FEATURE_NAME);
                featureDescription = data.getString(Util.FEATURE_DESCRIPTION);
            }
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_level_up);
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(LevelUp.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getCharacterDnd(id).observe(this, sheet ->
        {
            if (sheet != null)
            {
                sheetDAndD = sheet;
                str = sheet.getStrength();
                dex = sheet.getDexterity();
                con = sheet.getConstitution();
                inte = sheet.getIntelligence();
                wis = sheet.getWisdom();
                cha = sheet.getCharisma();
                if (skillOrPoints == Util.POINTS_VALUE)
                {
                    binding.choiceGroup.setVisibility(View.VISIBLE);
                    binding.pointsGroup.setVisibility(View.GONE);
                    binding.skillGroup.setVisibility(View.GONE);
                }
                else
                {
                    binding.choiceGroup.setVisibility(View.GONE);
                    binding.skillGroup.setVisibility(View.VISIBLE);
                    binding.pointsGroup.setVisibility(View.GONE);
                    binding.nameFeatureText.setText(featureName);
                    binding.descriptionText.setText(featureDescription);
                }
            }
            else
            {
                finish();
            }
        });

        binding.pointsChoice.setOnClickListener(v ->
        {
            binding.choiceGroup.setVisibility(View.GONE);
            binding.pointsGroup.setVisibility(View.VISIBLE);
            binding.skillGroup.setVisibility(View.GONE);
            points = 2;
            binding.strenghtText.setText(String.valueOf(str));
            int strMod = (str - 10)/2;
            binding.modStrText.setText(String.valueOf(strMod));
            binding.dexterityText.setText(String.valueOf(dex));
            int dexMod = (dex - 10)/2;
            binding.modDexText.setText(String.valueOf(dexMod));
            binding.constitutionText.setText(String.valueOf(con));
            int conMod = (con - 10)/2;
            binding.modConText.setText(String.valueOf(conMod));
            binding.intelligenceText.setText(String.valueOf(inte));
            int intMod = (inte - 10)/2;
            binding.modIntText.setText(String.valueOf(intMod));
            binding.wisdomText.setText(String.valueOf(wis));
            int wisMod = (wis - 10)/2;
            binding.modWisText.setText(String.valueOf(wisMod));
            binding.charismaText.setText(String.valueOf(cha));
            int chaMod = (cha - 10)/2;
            binding.modChaText.setText(String.valueOf(chaMod));
        });

        binding.featChoice.setOnClickListener(v ->
        {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(Util.FEAT_REPLY, Util.FEAT_VALUE);
            finish();
        });

        binding.strenghtText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.strenghtText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.strenghtText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.strenghtText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.strenghtText.getText().toString().isEmpty())
                {
                    int strMod = (Integer.parseInt(binding.strenghtText.getText().toString()) - 10)/2;
                    binding.modStrText.setText(String.valueOf(strMod));
                }
            }
        });

        binding.dexterityText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.dexterityText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.dexterityText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.dexterityText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.dexterityText.getText().toString().isEmpty())
                {
                    int dexMod = (Integer.parseInt(binding.dexterityText.getText().toString()) - 10)/2;
                    binding.modDexText.setText(String.valueOf(dexMod));
                }
            }
        });

        binding.constitutionText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.constitutionText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.constitutionText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.constitutionText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.constitutionText.getText().toString().isEmpty())
                {
                    int conMod = (Integer.parseInt(binding.constitutionText.getText().toString()) - 10)/2;
                    binding.modConText.setText(String.valueOf(conMod));
                }
            }
        });

        binding.intelligenceText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.intelligenceText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.intelligenceText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.intelligenceText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.intelligenceText.getText().toString().isEmpty())
                {
                    int intMod = (Integer.parseInt(binding.intelligenceText.getText().toString()) - 10)/2;
                    binding.modIntText.setText(String.valueOf(intMod));
                }
            }
        });

        binding.wisdomText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.wisdomText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.wisdomText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.wisdomText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(!binding.wisdomText.getText().toString().isEmpty())
                {
                    int wisMod = (Integer.parseInt(binding.wisdomText.getText().toString()) - 10)/2;
                    binding.modWisText.setText(String.valueOf(wisMod));
                }
            }
        });

        binding.charismaText.setOnClickListener(v ->
        {
            int value = Integer.parseInt(binding.charismaText.getText().toString());
            if (points != 0)
            {
                value++;
                points--;
                binding.charismaText.setText(String.valueOf(value));
            }
            else
            {
                Snackbar.make(binding.pointsGroup, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.charismaText.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (!binding.charismaText.getText().toString().isEmpty())
                {
                    int chaMod = (Integer.parseInt(binding.charismaText.getText().toString()) - 10)/2;
                    binding.modChaText.setText(String.valueOf(chaMod));
                }
            }
        });

        binding.resetButton.setOnClickListener(v ->
        {
            binding.strenghtText.setText(String.valueOf(str));
            binding.dexterityText.setText(String.valueOf(dex));
            binding.constitutionText.setText(String.valueOf(con));
            binding.intelligenceText.setText(String.valueOf(inte));
            binding.wisdomText.setText(String.valueOf(wis));
            binding.charismaText.setText(String.valueOf(cha));
            points = 2;
        });

        binding.pointsSaveButton.setOnClickListener(v ->
        {
            Intent replyIntent = new Intent();
            if (binding.pointsRadioGroup.getCheckedRadioButtonId() == R.id.pointsCardAvgLife)
            {
                replyIntent.putExtra(Util.RAND_OR_AVG_LIFE, Util.AVG_LIFE_VALUE);
                replyIntent.putExtra(Util.CON_REPLY, Integer.parseInt(binding.constitutionText.getText().toString()));
                replyIntent.putExtra(Util.STR_REPLY, Integer.parseInt(binding.strenghtText.getText().toString()));
                replyIntent.putExtra(Util.DEX_REPLY, Integer.parseInt(binding.dexterityText.getText().toString()));
                replyIntent.putExtra(Util.INT_REPLY, Integer.parseInt(binding.intelligenceText.getText().toString()));
                replyIntent.putExtra(Util.WIS_REPLY, Integer.parseInt(binding.wisdomText.getText().toString()));
                replyIntent.putExtra(Util.CHA_REPLY, Integer.parseInt(binding.charismaText.getText().toString()));
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else if (binding.pointsRadioGroup.getCheckedRadioButtonId() == R.id.pointsCardRandLife)
            {
                replyIntent.putExtra(Util.RAND_OR_AVG_LIFE, Util.RAND_LIFE_VALUE);
                replyIntent.putExtra(Util.CON_REPLY, Integer.parseInt(binding.constitutionText.getText().toString()));
                replyIntent.putExtra(Util.STR_REPLY, Integer.parseInt(binding.strenghtText.getText().toString()));
                replyIntent.putExtra(Util.DEX_REPLY, Integer.parseInt(binding.dexterityText.getText().toString()));
                replyIntent.putExtra(Util.INT_REPLY, Integer.parseInt(binding.intelligenceText.getText().toString()));
                replyIntent.putExtra(Util.WIS_REPLY, Integer.parseInt(binding.wisdomText.getText().toString()));
                replyIntent.putExtra(Util.CHA_REPLY, Integer.parseInt(binding.charismaText.getText().toString()));
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else
            {
                Snackbar.make(binding.pointsRadioGroup, "Escolha como deseja aumentar a vida", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.skillSaveButton.setOnClickListener(v ->
        {
            Intent replyIntent = new Intent();
            if (binding.skillRadioGroup.getCheckedRadioButtonId() == R.id.skillCardAvgLife)
            {
                replyIntent.putExtra(Util.RAND_OR_AVG_LIFE, Util.AVG_LIFE_VALUE);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else if (binding.skillRadioGroup.getCheckedRadioButtonId() == R.id.skillCardRandLife)
            {
                replyIntent.putExtra(Util.RAND_OR_AVG_LIFE, Util.RAND_LIFE_VALUE);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else
            {
                Snackbar.make(binding.skillRadioGroup, "Escolha como deseja aumentar a vida", Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}