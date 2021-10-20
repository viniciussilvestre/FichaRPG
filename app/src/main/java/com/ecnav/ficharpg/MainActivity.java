package com.ecnav.ficharpg;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapter;
import com.ecnav.ficharpg.data.Repository;
import com.ecnav.ficharpg.databinding.ActivityMainBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnContactClickListener
{
    private ActivityMainBinding binding;
    private SheetViewModel sheetViewModel;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Classes> classesList;

    ActivityResultLauncher<Intent> launchCharacterSheet = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateSpell = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateCharacter = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result ->
            {
                if (result.getResultCode() == Activity.RESULT_OK)
                {
                    Intent data = result.getData();
                    assert data != null;
                    SheetDAndD sheetDAndD = new SheetDAndD();
                    ArrayList<Classes> classes = data.getParcelableArrayListExtra(Util.CLASS_REPLY);
                    ArrayList<Subclass> subclasses = data.getParcelableArrayListExtra(Util.SUBCLASS_REPLY);
                    sheetDAndD.setName(data.getStringExtra(Util.NAME_REPLY));
                    //sheetDAndD.setCharacterClass(data.getStringExtra(Util.CLASS_REPLY));
                    sheetDAndD.setClassFeatures(classes);
                    if (!subclasses.isEmpty())
                    {
                        sheetDAndD.setSubclasses(subclasses);
                        sheetDAndD.setHasSubClass(true);
                    }
                    sheetDAndD.setLevel(data.getIntExtra(Util.LEVEL_REPLY, 1));
                    sheetDAndD.setRace(data.getStringExtra(Util.RACE_REPLY));
                    sheetDAndD.setBackground(data.getStringExtra(Util.BACKGROUND_REPLY));
                    sheetDAndD.setAlignment(data.getStringExtra(Util.ALIGNMENT_REPLY));
                    SheetViewModel.insertDnd(sheetDAndD);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sheetViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()).create(SheetViewModel.class);
        List<Classes> classes = new Repository(MainActivity.this.getApplication()).getClassDndFeatures(classesArrayList ->
        {
            for (int i = 0; i < classesArrayList.size(); i++)
            {
                SheetViewModel.insertClassDnd(classesArrayList.get(i));
            }
        });

        List<Subclass> subclasses = new Repository(MainActivity.this.getApplication()).getSubclassDndFeatures(subclassArrayList ->
        {
            for (int i = 0; i < subclassArrayList.size(); i++)
            {
                SheetViewModel.insertSubclassDnd(subclassArrayList.get(i));
            }
        });

        sheetViewModel.getAllSheetsDnd().observe(this, sheets ->
        {
            recyclerViewAdapter = new RecyclerViewAdapter(sheets, MainActivity.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState)
            {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && recyclerView.canScrollVertically(Integer.MIN_VALUE))
                {
                    binding.addButton.setVisibility(View.INVISIBLE);
                }
                else
                {
                    binding.addButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy)
            {
                if (dy != 0 && binding.addButton.isExtended())
                {
                    binding.addButton.shrink();
                }
                if (dy < 0 && !binding.addButton.isExtended())
                {
                    binding.addButton.extend();
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        binding.addButton.setOnClickListener(view ->
        {
            Intent intent = new Intent(MainActivity.this, CreateCharacter.class);
            intent.putExtra(Util.SHEETTYPE, Util.DUNGEONS_AND_DRAGONS);
            openCreateCharacter(intent);
        });
    }

    @Override
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent)
    {
        if (Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    public void openCreateSpell(Intent intent)
    {
        launchCreateSpell.launch(intent);
    }

    public void openCreateCharacter(Intent intent)
    {
        launchCreateCharacter.launch(intent);
    }

    public void openCharacterSheet(Intent intent)
    {
        launchCharacterSheet.launch(intent);
    }

    @Override
    public void onContactClick(int position)
    {
        SheetDAndD sheetDAndD = Objects.requireNonNull(sheetViewModel.getAllSheetsDnd().getValue()).get(position);
        Intent intent = new Intent(MainActivity.this, CharacterSheet.class);
        intent.putExtra(Util.CHARACTER_ID, sheetDAndD.getId());
        openCharacterSheet(intent);
    }
}