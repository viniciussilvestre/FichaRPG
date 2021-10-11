package com.ecnav.ficharpg.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ecnav.ficharpg.R;
import com.ecnav.ficharpg.adapter.RecyclerViewAdapterClasses;
import com.ecnav.ficharpg.adapter.RecyclerViewAdapterSubclass;
import com.ecnav.ficharpg.databinding.ActivitySubclassChooserBinding;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.util.DisplayClassSubclassInfo;
import com.ecnav.ficharpg.util.Util;

import java.util.Objects;

public class SubclassChooser extends AppCompatActivity implements RecyclerViewAdapterSubclass.OnContactClickListener
{
    private ActivitySubclassChooserBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapterSubclass recyclerViewAdapter;

    ActivityResultLauncher<Intent> launchSubclassInfo = registerForActivityResult(
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
                        Intent replyIntent = new Intent();
                        replyIntent.putExtra(Util.CHOSEN_CLASS_ID, data.getIntExtra(Util.CHOSEN_CLASS_ID, 0));
                        replyIntent.putExtra(Util.CHOSEN_CLASS_BOOLEAN, data.getBooleanExtra(Util.CHOSEN_CLASS_BOOLEAN, false));
                        setResult(RESULT_OK, replyIntent);
                        finish();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subclass_chooser);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(SubclassChooser.this.getApplication()).create(SheetViewModel.class);
        sheetViewModel.getAllSubclasses().observe(this, subclasses ->
        {
            recyclerViewAdapter = new RecyclerViewAdapterSubclass(subclasses, SubclassChooser.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });
    }

    @Override
    public void onContactClick(int position)
    {
        Subclass subclass = Objects.requireNonNull(sheetViewModel.getAllSubclasses().getValue().get(position));
        Intent intent = new Intent(SubclassChooser.this, DisplayClassSubclassInfo.class);
        intent.putExtra(Util.CLASS_ID, subclass.getSubclassId());
        intent.putExtra(Util.CLASS_OR_SUBCLASS, Util.SUBCLASS_INFO_FLAG);
        openSubclassInfo(intent);
    }

    public void openSubclassInfo(Intent intent)
    {
        launchSubclassInfo.launch(intent);
    }
}