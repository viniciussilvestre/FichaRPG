package com.ecnav.ficharpg.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.databinding.ActivitySubclassChooserBinding;
import com.ecnav.ficharpg.model.SheetViewModel;

public class SubclassChooser extends AppCompatActivity
{
    private ActivitySubclassChooserBinding binding;
    private SheetViewModel sheetViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subclass_chooser);
    }
}