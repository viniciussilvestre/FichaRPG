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
                    binding.pointsCard.setVisibility(View.VISIBLE);
                    binding.newSkillCard.setVisibility(View.GONE);
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
                }
                else
                {
                    binding.newSkillCard.setVisibility(View.VISIBLE);
                    binding.pointsCard.setVisibility(View.GONE);
                    binding.nameFeatureText.setText(featureName);
                    binding.descriptionText.setText(featureDescription);
                }
            }
            else
            {
                finish();
            }
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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
                Snackbar.make(binding.pointsCard, "Você não tem mais pontos", Snackbar.LENGTH_SHORT).show();
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

    private SheetDAndD getNewSheetData()
    {
        SheetDAndD sheetDAndD = new SheetDAndD();
        sheetDAndD.setId(id);
        sheetDAndD.setName(this.sheetDAndD.getName());
        sheetDAndD.setClassFeatures(this.sheetDAndD.getClassFeatures());
        sheetDAndD.setSubclasses(this.sheetDAndD.getSubclasses());
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
        sheetDAndD.setSpeed(this.sheetDAndD.getSpeed());
        sheetDAndD.setLevel(this.sheetDAndD.getLevel());
        sheetDAndD.setRace(this.sheetDAndD.getRace());
        sheetDAndD.setBackground(this.sheetDAndD.getBackground());
        sheetDAndD.setAlignment(this.sheetDAndD.getAlignment());
        sheetDAndD.setInitiative(this.sheetDAndD.getInitiative());
        sheetDAndD.setArmorClass(this.sheetDAndD.getArmorClass());
        sheetDAndD.setHitPoints(this.sheetDAndD.getHitPoints());
        sheetDAndD.setStrength(Integer.parseInt(binding.strenghtText.getText().toString()));
        sheetDAndD.setDexterity(Integer.parseInt(binding.dexterityText.getText().toString()));
        sheetDAndD.setConstitution(Integer.parseInt(binding.constitutionText.getText().toString()));
        sheetDAndD.setIntelligence(Integer.parseInt(binding.intelligenceText.getText().toString()));
        sheetDAndD.setWisdom(Integer.parseInt(binding.wisdomText.getText().toString()));
        sheetDAndD.setCharisma(Integer.parseInt(binding.charismaText.getText().toString()));
        sheetDAndD.setHasSubClass(this.sheetDAndD.isHasSubClass());
        sheetDAndD.setStrengthSaveProficiency(this.sheetDAndD.isStrengthSaveProficiency());
        sheetDAndD.setDexteritySaveProficiency(this.sheetDAndD.isDexteritySaveProficiency());
        sheetDAndD.setConstitutionSaveProficiency(this.sheetDAndD.isConstitutionSaveProficiency());
        sheetDAndD.setIntelligenceSaveProficiency(this.sheetDAndD.isIntelligenceSaveProficiency());
        sheetDAndD.setWisdomSaveProficiency(this.sheetDAndD.isWisdomSaveProficiency());
        sheetDAndD.setCharismaSaveProficiency(this.sheetDAndD.isCharismaSaveProficiency());
        sheetDAndD.setAcrobaticsProficiency(this.sheetDAndD.isAcrobaticsProficiency());
        sheetDAndD.setExpertiseAcrobaticsProficiency(this.sheetDAndD.isExpertiseAcrobaticsProficiency());
        sheetDAndD.setStealthProficiency(this.sheetDAndD.isStealthProficiency());
        sheetDAndD.setExpertiseStealthProficiency(this.sheetDAndD.isExpertiseStealthProficiency());
        sheetDAndD.setSleightOfHandProficiency(this.sheetDAndD.isSleightOfHandProficiency());
        sheetDAndD.setExpertiseSleightOfHandProficiency(this.sheetDAndD.isExpertiseSleightOfHandProficiency());
        sheetDAndD.setArcanaProficiency(this.sheetDAndD.isArcanaProficiency());
        sheetDAndD.setExpertiseArcanaProficiency(this.sheetDAndD.isExpertiseArcanaProficiency());
        sheetDAndD.setHistoryProficiency(this.sheetDAndD.isHistoryProficiency());
        sheetDAndD.setExpertiseHistoryProficiency(this.sheetDAndD.isExpertiseHistoryProficiency());
        sheetDAndD.setInvestigationProficiency(this.sheetDAndD.isInvestigationProficiency());
        sheetDAndD.setExpertiseInvestigationProficiency(this.sheetDAndD.isExpertiseInvestigationProficiency());
        sheetDAndD.setNatureProficiency(this.sheetDAndD.isNatureProficiency());
        sheetDAndD.setExpertiseNatureProficiency(this.sheetDAndD.isExpertiseNatureProficiency());
        sheetDAndD.setReligionProficiency(this.sheetDAndD.isReligionProficiency());
        sheetDAndD.setExpertiseReligionProficiency(this.sheetDAndD.isExpertiseReligionProficiency());
        sheetDAndD.setAnimalHandlingProficiency(this.sheetDAndD.isAnimalHandlingProficiency());
        sheetDAndD.setExpertiseAnimalHandlingProficiency(this.sheetDAndD.isExpertiseAnimalHandlingProficiency());
        sheetDAndD.setInsightProficiency(this.sheetDAndD.isInsightProficiency());
        sheetDAndD.setExpertiseInsightProficiency(this.sheetDAndD.isExpertiseInsightProficiency());
        sheetDAndD.setMedicineProficiency(this.sheetDAndD.isMedicineProficiency());
        sheetDAndD.setExpertiseMedicineProficiency(this.sheetDAndD.isExpertiseMedicineProficiency());
        sheetDAndD.setPerceptionProficiency(this.sheetDAndD.isPerceptionProficiency());
        sheetDAndD.setExpertisePerceptionProficiency(this.sheetDAndD.isExpertisePerceptionProficiency());
        sheetDAndD.setSurvivalProficiency(this.sheetDAndD.isSurvivalProficiency());
        sheetDAndD.setExpertiseSurvivalProficiency(this.sheetDAndD.isExpertiseSurvivalProficiency());
        sheetDAndD.setAthleticsProficiency(this.sheetDAndD.isAthleticsProficiency());
        sheetDAndD.setExpertiseAthleticsProficiency(this.sheetDAndD.isExpertiseAthleticsProficiency());
        sheetDAndD.setDeceptionProficiency(this.sheetDAndD.isDeceptionProficiency());
        sheetDAndD.setExpertiseDeceptionProficiency(this.sheetDAndD.isExpertiseDeceptionProficiency());
        sheetDAndD.setIntimidationProficiency(this.sheetDAndD.isIntimidationProficiency());
        sheetDAndD.setExpertiseIntimidationProficiency(this.sheetDAndD.isExpertiseIntimidationProficiency());
        sheetDAndD.setPerformanceProficiency(this.sheetDAndD.isPerformanceProficiency());
        sheetDAndD.setExpertisePerformanceProficiency(this.sheetDAndD.isExpertisePerformanceProficiency());
        sheetDAndD.setPersuasionProficiency(this.sheetDAndD.isPersuasionProficiency());
        sheetDAndD.setExpertisePersuasionProficiency(this.sheetDAndD.isExpertisePersuasionProficiency());
        sheetDAndD.setFeatures(this.sheetDAndD.getFeatures());
        sheetDAndD.setEquipments(this.sheetDAndD.getEquipments());
        sheetDAndD.setLevel0(this.sheetDAndD.getLevel0());
        sheetDAndD.setLevel1(this.sheetDAndD.getLevel1());
        sheetDAndD.setLevel2(this.sheetDAndD.getLevel2());
        sheetDAndD.setLevel3(this.sheetDAndD.getLevel3());
        sheetDAndD.setLevel4(this.sheetDAndD.getLevel4());
        sheetDAndD.setLevel5(this.sheetDAndD.getLevel5());
        sheetDAndD.setLevel6(this.sheetDAndD.getLevel6());
        sheetDAndD.setLevel7(this.sheetDAndD.getLevel7());
        sheetDAndD.setLevel8(this.sheetDAndD.getLevel8());
        sheetDAndD.setLevel9(this.sheetDAndD.getLevel9());
        return sheetDAndD;
    }
}