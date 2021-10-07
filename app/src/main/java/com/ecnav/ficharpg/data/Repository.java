package com.ecnav.ficharpg.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecnav.ficharpg.controller.AppController;
import com.ecnav.ficharpg.model.Classes;
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
    private LiveData<List<Classes>> allClasses;

    ArrayList<Spell> statementsArrayList = new ArrayList<>();
    ArrayList<Classes> classesArrayList = new ArrayList<>();
    String urlSpells = "https://raw.githubusercontent.com/jcquinlan/dnd-spells/master/spells.json";
    String urlClassFeatures = "https://raw.githubusercontent.com/BTMorton/dnd-5e-srd/master/json/02%20classes.json";

//    public List<Spell> getSpells(final AnswerListAsyncResponse callBack)
//    {
//
//    }

    public List<Classes> getClassFeatures(final AnswerListAsyncResponse callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClassFeatures, null, response ->
        {
            for (int i = 0; i < response.length(); i++)
            {
                try
                {
                    Classes classes = new Classes();
                    Log.d("TAG", "getFeatures: " + response.getJSONArray(i).getString(0));
                    classesArrayList.add(classes);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != callBack)
            {
                callBack.processFinished(classesArrayList);
            }
        }, error ->
        {

        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return classesArrayList;
    }

    public Repository(Application application)
    {
        SheetRoomDatabase db = SheetRoomDatabase.getDatabase(application);
        sheetDao = db.sheetDao();
        allSheetsDnd = sheetDao.getAllSheetsDnd();
        allClasses = sheetDao.getAllClassesDnd();
    }

    public LiveData<List<SheetDAndD>> getAllSheetsDnd()
    {
        return allSheetsDnd;
    }

    public LiveData<List<Classes>> getAllClassesDnd()
    {
        return allClasses;
    }

    public void insertDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.insert(sheetDAndD);
        });
    }

    public void insertClassDnd(Classes classes)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.insert(classes);
        });
    }

    public void updateDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.updateDnd(sheetDAndD);
        });
    }

    public void updateClassDnd(Classes classes)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.updateClass(classes);
        });
    }

    public LiveData<SheetDAndD> getCharacterDnd(int id)
    {
        return sheetDao.getCharacterDnd(id);
    }

    public LiveData<Classes> getClassDnd(int id)
    {
        return sheetDao.getClassDnd(id);
    }

    public void deleteDnd(SheetDAndD sheetDAndD)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.delete(sheetDAndD);
        });
    }

    public void deleteClassDnd(Classes classes)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.updateClass(classes);
        });
    }

    public LiveData<List<SheetDAndD>> searchDatabase(String searchQuery)
    {
        return sheetDao.searchDatabase(searchQuery);
    }
}
