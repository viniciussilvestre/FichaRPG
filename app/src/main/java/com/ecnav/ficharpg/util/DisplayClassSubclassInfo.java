package com.ecnav.ficharpg.util;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClassesInfo;
import com.ecnav.ficharpg.databinding.ActivityClassesInfoBinding;
import com.ecnav.ficharpg.model.SheetViewModel;

public class DisplayClassSubclassInfo extends AppCompatActivity implements RecyclerViewAdapterClassesInfo.OnContactClickListener
{
    private ActivityClassesInfoBinding binding;
    private int classSubclassId;
    private int classSubclassFlag;
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
            classSubclassId = data.getInt(Util.CLASS_ID);
            classSubclassFlag = data.getInt(Util.CLASS_OR_SUBCLASS, 0);
        }
        binding.recyclerViewClassInfo.setHasFixedSize(true);
        binding.recyclerViewClassInfo.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(DisplayClassSubclassInfo.this.getApplication()).create(SheetViewModel.class);
        if (classSubclassFlag == Util.CLASS_INFO_FLAG)
        {
            sheetViewModel.getClassDnd(classSubclassId).observe(this, classInfo ->
            {
                recyclerViewAdapterClassesInfo = new RecyclerViewAdapterClassesInfo(classInfo.getClassFeatures(), DisplayClassSubclassInfo.this, this);
                binding.recyclerViewClassInfo.setAdapter(recyclerViewAdapterClassesInfo);
            });
        }
        else if (classSubclassFlag == Util.SUBCLASS_INFO_FLAG)
        {
            sheetViewModel.getSubclassDnd(classSubclassId).observe(this, subclass ->
            {
                recyclerViewAdapterClassesInfo = new RecyclerViewAdapterClassesInfo(subclass.getFeatures(), DisplayClassSubclassInfo.this, this);
                binding.recyclerViewClassInfo.setAdapter(recyclerViewAdapterClassesInfo);
            });
        }

        binding.button.setOnClickListener(view ->
        {
            if (classSubclassFlag == Util.CLASS_INFO_FLAG)
            {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(Util.CHOSEN_CLASS_BOOLEAN, true);
                replyIntent.putExtra(Util.CHOSEN_CLASS_ID, classSubclassId);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
            else if (classSubclassFlag == Util.SUBCLASS_INFO_FLAG)
            {
                Intent replyIntent = new Intent();
                replyIntent.putExtra(Util.CHOSEN_CLASS_ID, classSubclassId);
                replyIntent.putExtra(Util.CHOSEN_CLASS_BOOLEAN, true);
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
    }

    @Override
    public void onContactClick(int position)
    {

    }
}