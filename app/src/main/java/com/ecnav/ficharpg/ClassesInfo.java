package com.ecnav.ficharpg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClassesInfo;
import com.ecnav.ficharpg.databinding.ActivityClassesInfoBinding;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Util;

import java.util.Objects;

public class ClassesInfo extends AppCompatActivity implements RecyclerViewAdapterClassesInfo.OnContactClickListener
{
    private ActivityClassesInfoBinding binding;
    private int classId;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterClassesInfo recyclerViewAdapterClassesInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_classes_info);
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            classId = data.getInt(Util.CLASS_ID);
        }
        binding.recyclerViewClassInfo.setHasFixedSize(true);
        binding.recyclerViewClassInfo.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(ClassesInfo.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getClassDnd(classId).observe(this, classInfo ->
        {
            recyclerViewAdapterClassesInfo = new RecyclerViewAdapterClassesInfo(classInfo.getClassFeatures(), ClassesInfo.this, this);
            binding.recyclerViewClassInfo.setAdapter(recyclerViewAdapterClassesInfo);
        });

        binding.button.setOnClickListener(v ->
        {
            Intent replyIntent = new Intent();
            replyIntent.putExtra(Util.CHOSEN_CLASS_BOOLEAN, true);
            replyIntent.putExtra(Util.CHOSEN_CLASS_ID, classId);
            setResult(RESULT_OK, replyIntent);
            finish();
        });
    }

    @Override
    public void onContactClick(int position)
    {

    }
}