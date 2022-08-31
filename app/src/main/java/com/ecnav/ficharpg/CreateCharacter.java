package com.ecnav.ficharpg;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.ecnav.ficharpg.databinding.ActivityCreateCharacterBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.ui.ClassChooser;
import com.ecnav.ficharpg.ui.SubclassChooser;
import com.ecnav.ficharpg.util.Util;
import com.google.android.material.color.DynamicColors;
import com.google.android.material.elevation.SurfaceColors;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class CreateCharacter extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
{
    private ActivityCreateCharacterBinding binding;
    private ArrayList<Classes> classes = new ArrayList<>();
    private ArrayList<Subclass> subclasses = new ArrayList<>();
    private SheetViewModel sheetViewModel;
    private boolean classChosen;
    private int classId;

    ActivityResultLauncher<Intent> launchClassChooser = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                        classChosen = data.getBooleanExtra(Util.CHOSEN_CLASS_BOOLEAN, false);
                        classId = data.getIntExtra(Util.CHOSEN_CLASS_ID, 0);
                        if (classChosen && classId != 0)
                        {
                            sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(CreateCharacter.this.getApplication()).create(SheetViewModel.class);
                            sheetViewModel.getClassDnd(classId).observe(CreateCharacter.this, classSelected ->
                            {
                                if (classes.isEmpty())
                                {
                                    classes = new ArrayList<>();
                                    classes.add(classSelected);
                                }
                                else
                                {
                                    int classAlreadyAdded = 0;
                                    for (int j = 0; j < classes.size(); j++)
                                    {
                                        if (classSelected.getClassId() == classes.get(j).getClassId())
                                        {
                                            classAlreadyAdded = 1;
                                        }
                                    }
                                    if (classAlreadyAdded == 0)
                                    {
                                        classes.add(classSelected);
                                    }
                                }
                                StringBuilder classesText = new StringBuilder();
                                for (int i = 0; i < classes.size(); i++)
                                {
                                    if (i == classes.size() - 1)
                                    {
                                        classesText.append(classes.get(i).getClassName());
                                    }
                                    else
                                    {
                                        classesText.append(classes.get(i).getClassName()).append(", ");
                                    }
                                }
                                binding.classTextView.setText(classesText.toString());
                            });
                        }
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchSubclassChooser = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                        boolean subclassChosen = data.getBooleanExtra(Util.CHOSEN_CLASS_BOOLEAN, false);
                        int subclassId = data.getIntExtra(Util.CHOSEN_CLASS_ID, 0);
                        if (subclassChosen && subclassId != 0)
                        {
                            sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(CreateCharacter.this.getApplication()).create(SheetViewModel.class);
                            sheetViewModel.getSubclassDnd(subclassId).observe(CreateCharacter.this, subclassSelected ->
                            {
                                if (subclasses.isEmpty())
                                {
                                    subclasses = new ArrayList<>();
                                    subclasses.add(subclassSelected);
                                }
                                else
                                {
                                    int subclassAlreadySelected = 0;
                                    for (int j = 0; j < subclasses.size(); j++)
                                    {
                                        if (subclassSelected.getSubclassId() == subclasses.get(j).getSubclassId())
                                        {
                                            subclassAlreadySelected = 1;
                                        }
                                    }
                                    if (subclassAlreadySelected == 0)
                                    {
                                        subclasses.add(subclassSelected);
                                    }
                                }
                                StringBuilder subclassesText = new StringBuilder();
                                for (int i = 0; i < subclasses.size(); i++)
                                {
                                    if (i == subclasses.size() - 1)
                                    {
                                        subclassesText.append(subclasses.get(i).getSubclassName());
                                    }
                                    else
                                    {
                                        subclassesText.append(subclasses.get(i).getSubclassName()).append(", ");
                                    }
                                }
                                binding.subclassText.setText(subclassesText.toString());
                            });
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_character);
        binding.addSubclassButton.setVisibility(View.GONE);
        binding.subclassText.setVisibility(View.GONE);

        binding.addClassButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(CreateCharacter.this, ClassChooser.class);
            openClassChooser(intent);
        });

        binding.addSubclassButton.setOnClickListener(view ->
        {
            if (classChosen)
            {
                Intent intent = new Intent(CreateCharacter.this, SubclassChooser.class);
                intent.putExtra(Util.CHOSEN_CLASS_ID, classId);
                intent.putExtra(Util.CLASS_OR_SUBCLASS, Util.SUBCLASS_INFO_FLAG);
                openSubclassChooser(intent);
            }
            else
            {
                Snackbar.make(binding.addSubclassButton, "Please select a class first", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.characterLevelField.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (!binding.characterLevelField.getText().toString().isEmpty())
                {
                    if (Integer.parseInt(s.toString()) >= 3)
                    {
                        binding.addSubclassButton.setVisibility(View.VISIBLE);
                        binding.subclassText.setVisibility(View.VISIBLE);
                    }
                    else if (classId == 2 || classId == 5)
                    {
                        binding.addSubclassButton.setVisibility(View.VISIBLE);
                        binding.subclassText.setVisibility(View.VISIBLE);
                    }
                    else if (Integer.parseInt(s.toString()) < 3)
                    {
                        binding.addSubclassButton.setVisibility(View.GONE);
                        binding.subclassText.setVisibility(View.GONE);
                    }
                }
            }
        });

        binding.saveButton.setOnClickListener(view ->
        {
            Intent replyIntent = new Intent();
            if (!TextUtils.isEmpty(binding.characterNameField.getText()) && !TextUtils.isEmpty(binding.characterBackgroundField.getText()) && !TextUtils.isEmpty(binding.characterRaceField.getText()) && !TextUtils.isEmpty(binding.characterLevelField.getText()))
            {
                String name = binding.characterNameField.getText().toString();
                String background = binding.characterBackgroundField.getText().toString();
                String race = binding.characterRaceField.getText().toString();
                String stringLevel = binding.characterLevelField.getText().toString();
                int intLevel = Integer.parseInt(stringLevel);
                String alignment = binding.characterAlignmentField.getText().toString();
                replyIntent.putExtra(Util.NAME_REPLY, name);
                replyIntent.putParcelableArrayListExtra(Util.CLASS_REPLY, classes);
                replyIntent.putParcelableArrayListExtra(Util.SUBCLASS_REPLY, subclasses);
                replyIntent.putExtra(Util.BACKGROUND_REPLY, background);
                replyIntent.putExtra(Util.RACE_REPLY, race);
                replyIntent.putExtra(Util.LEVEL_REPLY, intLevel);
                replyIntent.putExtra(Util.ALIGNMENT_REPLY, alignment);
                setResult(RESULT_OK, replyIntent);
            }
            else
            {
                setResult(RESULT_CANCELED, replyIntent);
            }
            finish();
        });
    }

    public void openClassChooser(Intent intent)
    {
        launchClassChooser.launch(intent);
    }

    public void openSubclassChooser(Intent intent)
    {
        launchSubclassChooser.launch(intent);
    }
}