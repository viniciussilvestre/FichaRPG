package com.ecnav.ficharpg.data;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecnav.ficharpg.controller.AppController;
import com.ecnav.ficharpg.model.ClassFeatures;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.Spell;
import com.ecnav.ficharpg.util.SheetRoomDatabase;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private SheetDao sheetDao;
    private LiveData<List<SheetDAndD>> allSheetsDnd;

    ArrayList<Spell> statementsArrayList = new ArrayList<>();
    ArrayList<ClassFeatures> classFeaturesArrayList = new ArrayList<>();
    String urlSpells = "https://raw.githubusercontent.com/jcquinlan/dnd-spells/master/spells.json";
    String urlClassFeatures = "https://raw.githubusercontent.com/BTMorton/dnd-5e-srd/master/json/02%20classes.json";

//    public List<Spell> getSpells(final AnswerListAsyncResponse callBack)
//    {
//
//    }

    public List<ClassFeatures> getClassFeatures(final AnswerListAsyncResponse callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClassFeatures, null, response ->
        {
            for (int i = 0; i < response.length(); i++)
            {
                try
                {
                    ClassFeatures classFeatures = new ClassFeatures();
                    Log.d("TAG", "getFeatures: " + response.getJSONArray(i).getString(0));
                    classFeaturesArrayList.add(classFeatures);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != callBack)
            {
                callBack.processFinished(classFeaturesArrayList);
            }
        }, error ->
        {

        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return classFeaturesArrayList;
    }

    public Repository(Application application)
    {
        SheetRoomDatabase db = SheetRoomDatabase.getDatabase(application);
        sheetDao = db.sheetDao();
        allSheetsDnd = sheetDao.getAllSheetsDnd();
    }

    public LiveData<List<SheetDAndD>> getAllSheetsDnd()
    {
        return allSheetsDnd;
    }


    public void insertDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.insertDnd(sheetDAndD);
        });
    }

    public void updateDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.updateDnd(sheetDAndD);
        });
    }

    public LiveData<SheetDAndD> getCharacterDnd(int id)
    {
        return sheetDao.getCharacterDnd(id);
    }

    public void deleteDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.deleteDnd(sheetDAndD);
        });
    }

    public LiveData<List<SheetDAndD>> searchDatabase(String searchQuery)
    {
        return sheetDao.searchDatabase(searchQuery);
    }
}
