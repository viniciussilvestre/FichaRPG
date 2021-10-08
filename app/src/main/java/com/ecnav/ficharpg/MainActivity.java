package com.ecnav.ficharpg;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.SearchView;

import com.ecnav.ficharpg.adapter.RecyclerViewAdapter;
import com.ecnav.ficharpg.data.AnswerListAsyncResponse;
import com.ecnav.ficharpg.data.Repository;
import com.ecnav.ficharpg.databinding.ActivityMainBinding;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.SheetViewModel;
import com.ecnav.ficharpg.util.Dice;
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
            new ActivityResultCallback<ActivityResult>()
            {
                @Override
                public void onActivityResult(ActivityResult result)
                {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        assert data != null;
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateSpell = registerForActivityResult(
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
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchCreateCharacter = registerForActivityResult(
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
                        SheetDAndD sheetDAndD = new SheetDAndD();
                        ArrayList<Classes> classes = data.getParcelableArrayListExtra(Util.CLASS_REPLY);
                        sheetDAndD.setName(data.getStringExtra(Util.NAME_REPLY));
                        //sheetDAndD.setCharacterClass(data.getStringExtra(Util.CLASS_REPLY));
                        sheetDAndD.setClassFeatures(classes);
                        sheetDAndD.setLevel(data.getIntExtra(Util.LEVEL_REPLY, 1));
                        sheetDAndD.setRace(data.getStringExtra(Util.RACE_REPLY));
                        sheetDAndD.setBackground(data.getStringExtra(Util.BACKGROUND_REPLY));
                        sheetDAndD.setAlignment(data.getStringExtra(Util.ALIGNMENT_REPLY));
                        sheetDAndD.setHitPoints(data.getIntExtra(Util.HITPOINTS_REPLY, 0));
                        SheetViewModel.insertDnd(sheetDAndD);
                    }
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
        sheetViewModel.getAllSheetsDnd().observe(this, sheets ->
        {
            recyclerViewAdapter = new RecyclerViewAdapter(sheets, MainActivity.this, this);
            binding.recyclerView.setAdapter(recyclerViewAdapter);
        });

//        Classes classes = new Classes();
//        classes.setClassName("Rogue");
//        classes.setHitDice(Dice.D8);
//        classes.setHitPointsAtHigherLevel("1d8 + modificador de constiuiçao ou 5 + modificador de constiuiçao");
//        Feature feature1 = new Feature();
//        feature1.setNome("Proficiencies");
//        StringBuilder proficiencies = new StringBuilder();
//        proficiencies.append("Armor: ").append("Light armor").append("\n");
//        proficiencies.append("Weapons: Simple weapons, hand crossbows, longswords, rapiers, shortswords").append("\n");
//        proficiencies.append("Tools: Thieves' tools").append("\n");
//        proficiencies.append("Skills: Choose four from Acrobatics, Athletics, Deception, Insight, Intimidation, Investigation, Perception, Performance, Persuasion, Sleight of Hand, and Stealth");
//        feature1.setDescription(proficiencies.toString());
//        classes.addFeatures(feature1);
//        Feature feature2 = new Feature();
//        feature2.setNome("Equipment");
//        StringBuilder equipments = new StringBuilder();
//        equipments.append("You start with the following equipment, in addition to the equipment granted by your background:").append("\n");
//        equipments.append("(a) a rapier or (b) a shortsword").append("\n");
//        equipments.append("(a) a shortbow and quiver of 20 arrows or (b) a shortsword");
//        equipments.append("(a) a burglar's pack, (b) dungeoneer's pack, or (c) an explorer's pack").append("\n");
//        equipments.append("Leather armor, two daggers, and thieves' tools");
//        feature2.setDescription(equipments.toString());
//        classes.addFeatures(feature2);
//        Feature feature3 = new Feature();
//        feature3.setNome("Expertise");
//        feature3.setDescription("At 1st level, choose two of your skill proficiencies, or one of your skill proficiencies and your proficiency with thieves' tools. Your proficiency bonus is doubled for any ability check you make that uses either of the chosen proficiencies. \n At 6th level, you can choose two more of your proficiencies (in skills or with thieves' tools) to gain this benefit.");
//        classes.addFeatures(feature3);
//        Feature feature4 = new Feature();
//        feature4.setNome("Sneak Attack");
//        StringBuilder sneak_attack = new StringBuilder();
//        sneak_attack.append("Beginning at 1st level, you know how to strike subtly and exploit a foe's distraction. Once per turn, you can deal an extra 1d6 damage to one creature you hit with an attack if you have advantage on the attack roll. The attack must use a finesse or a ranged weapon.").append("\n");
//        sneak_attack.append("You don't need advantage on the attack roll if another enemy of the target is within 5 feet of it, that enemy isn't incapacitated, and you don't have disadvantage on the attack roll.").append("\n");
//        sneak_attack.append("The amount of the extra damage increases as you gain levels in this class");
//        feature4.setDescription(sneak_attack.toString());
//        classes.addFeatures(feature4);
//        Feature feature5 = new Feature();
//        feature5.setNome("Thieves' Cant");
//        feature5.setDescription("During your rogue training you learned thieves' cant, a secret mix of dialect, jargon, and code that allows you to hide messages in seemingly normal conversation. Only another creature that knows thieves' cant understands such messages. It takes four times longer to convey such a message than it does to speak the same idea plainly. \n In addition, you understand a set of secret signs and symbols used to convey short, simple messages, such as whether an area is dangerous or the territory of a thieves' guild, whether loot is nearby, or whether the people in an area are easy marks or will provide a safe house for thieves on the run.");
//        classes.addFeatures(feature5);
//        Feature feature6 = new Feature();
//        feature6.setNome("Cunning Action");
//        feature6.setDescription("Starting at 2nd level, your quick thinking and agility allow you to move and act quickly. You can take a bonus action on each of your turns in combat. This action can be used only to take the Dash, Disengage, or Hide action.");
//        classes.addFeatures(feature6);
//        Feature feature7 = new Feature();
//        feature7.setNome("Roguish Archetype");
//        feature7.setDescription("Click on this card to select an archetype");
//        classes.addFeatures(feature7);
//        Feature feature8 = new Feature();
//        feature8.setNome("Steady Aim (Optional)");
//        feature8.setDescription("At 3rd level, as a bonus action, you give yourself advantage on your next attack roll on the current turn. You can use this bonus action only if you haven't moved during this turn, and after you use the bonus action, your speed is 0 until the end of the current turn.");
//        classes.addFeatures(feature8);
//        Feature feature9 = new Feature();
//        feature9.setNome("Ability Score Improvement");
//        feature9.setDescription("When you reach 4th level, and again at 8th, 10th, 12th, 16th, and 19th level, you can increase one ability score of your choice by 2, or you can increase two ability scores of your choice by 1. As normal, you can't increase an ability score above 20 using this feature.");
//        classes.addFeatures(feature9);
//        Feature feature10 = new Feature();
//        feature10.setNome("Uncanny Dodge");
//        feature10.setDescription("Starting at 5th level, when an attacker that you can see hits you with an attack, you can use your reaction to halve the attack's damage against you.");
//        classes.addFeatures(feature10);
//        Feature feature11 = new Feature();
//        feature11.setNome("Evasion");
//        feature11.setDescription("Beginning at 7th level, you can nimbly dodge out of the way of certain area effects, such as a red dragon's fiery breath or an Ice Storm spell. When you are subjected to an effect that allows you to make a Dexterity saving throw to take only half damage, you instead take no damage if you succeed on the saving throw, and only half damage if you fail.");
//        classes.addFeatures(feature11);
//        Feature feature12 = new Feature();
//        feature12.setNome("Reliable Talent");
//        feature12.setDescription("By 11th level, you have refined your chosen skills until they approach perfection. Whenever you make an ability check that lets you add your proficiency bonus, you can treat a d20 roll of 9 or lower as a 10.");
//        classes.addFeatures(feature12);
//        Feature feature13 = new Feature();
//        feature13.setNome("Blindsense");
//        feature13.setDescription("Starting at 14th level, if you are able to hear, you are aware of the location of any hidden or invisible creature within 10 feet of you.");
//        classes.addFeatures(feature13);
//        Feature feature14 = new Feature();
//        feature14.setNome("Slippery Mind");
//        feature14.setDescription("By 15th level, you have acquired greater mental strength. You gain proficiency in Wisdom saving throws.");
//        classes.addFeatures(feature14);
//        Feature feature15 = new Feature();
//        feature15.setNome("Elusive");
//        feature15.setDescription("Beginning at 18th level, you are so evasive that attackers rarely gain the upper hand against you. No attack roll has advantage against you while you aren't incapacitated.");
//        classes.addFeatures(feature15);
//        Feature feature16 = new Feature();
//        feature16.setNome("Stroke of Luck");
//        feature16.setDescription("At 20th level, you have an uncanny knack for succeeding when you need to. If your attack misses a target within range, you can turn the miss into a hit. Alternatively, if you fail an ability check, you can treat the d20 roll as a 20. \n Once you use this feature, you can't use it again until you finish a short or long rest");
//        classes.addFeatures(feature16);
//        SheetViewModel.insertClassDnd(classes);

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
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