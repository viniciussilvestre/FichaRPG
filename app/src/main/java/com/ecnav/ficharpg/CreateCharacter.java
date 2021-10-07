package com.ecnav.ficharpg;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.ecnav.ficharpg.databinding.ActivityCreateCharacterBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;

public class CreateCharacter extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
{
    private ActivityCreateCharacterBinding binding;
    private String classSelected = "";
    private ArrayList<Classes> classes = new ArrayList<>();
    private SheetViewModel sheetViewModel;

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
                        int classId;
                        boolean classChosen;
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
                                    for (int j = 0; j < classes.size(); j++)
                                    {
                                        if (classSelected.getClassId() != classes.get(j).getClassId())
                                        {
                                            classes.add(classSelected);
                                        }
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_character);

//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classes, R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//        binding.spinnerClass.setAdapter(adapter);
//        binding.spinnerClass.setOnItemSelectedListener(this);

        binding.addClassButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(CreateCharacter.this, ClassChooser.class);
            openClassChooser(intent);
            //binding.classTextView.setText(classSelected);
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
                String hp = binding.hitPointsField.getText().toString();
                int intHp = Integer.parseInt(hp);
                replyIntent.putExtra(Util.NAME_REPLY, name);
                Log.d("TAG", "CreateCharacter classes value: " + classes);
                replyIntent.putParcelableArrayListExtra(Util.CLASS_REPLY, classes);
                replyIntent.putExtra(Util.BACKGROUND_REPLY, background);
                replyIntent.putExtra(Util.RACE_REPLY, race);
                replyIntent.putExtra(Util.LEVEL_REPLY, intLevel);
                replyIntent.putExtra(Util.ALIGNMENT_REPLY, alignment);
                replyIntent.putExtra(Util.HITPOINTS_REPLY, intHp);
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

//    @Override
//    public void onItemSelected(@NonNull AdapterView<?> parent, View view, int position, long id)
//    {
//        if (position != 0)
//        {
//            setClassSelected(parent.getItemAtPosition(position).toString());
//        }
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent)
//    {
//
//    }
//
//    public void setClassSelected(String newClass)
//    {
//        if (this.classSelected.isEmpty())
//        {
//            this.classSelected = newClass;
//        }
//        if (!this.classSelected.contains(newClass))
//        {
//            this.classSelected = classSelected + ", " + newClass;
//        }
//    }
}