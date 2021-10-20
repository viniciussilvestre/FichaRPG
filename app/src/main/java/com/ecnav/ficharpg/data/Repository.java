package com.ecnav.ficharpg.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ecnav.ficharpg.controller.AppController;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.Spell;
import com.ecnav.ficharpg.model.Subclass;
import com.ecnav.ficharpg.util.Dice;
import com.ecnav.ficharpg.util.SheetRoomDatabase;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository
{
    private SheetDao sheetDao;
    private final LiveData<List<SheetDAndD>> allSheetsDnd;
    private final LiveData<List<Classes>> allClasses;
    private final LiveData<List<Subclass>> allSubclasses;
    private LiveData<List<Subclass>> someSubclasses;

    ArrayList<Spell> statementsArrayList = new ArrayList<>();
    ArrayList<Classes> classesArrayList = new ArrayList<>();
    ArrayList<Subclass> subclassArrayList = new ArrayList<>();
    String urlSpells = "https://raw.githubusercontent.com/jcquinlan/dnd-spells/master/spells.json";
    String urlClassFeatures = "https://raw.githubusercontent.com/viniciussilvestre/ClassFeaturesDND/main/Classes_JSON.json";
    String urlSubclasses = "https://raw.githubusercontent.com/viniciussilvestre/ClassFeaturesDND/main/subclassJSON.json";

//    public List<Spell> getSpells(final AnswerListAsyncResponse callBack)
//    {
//
//    }

    public List<Classes> getClassDndFeatures(final AnswerListAsyncResponse callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlClassFeatures, null, response ->
        {
            for (int i = 0; i < response.length(); i++)
            {
                try
                {
                    Classes classes = new Classes();
                    JSONArray jsonArray = response.getJSONArray(i);
                    classes.setClassId(Integer.parseInt(jsonArray.getString(0)));
                    classes.setClassName(jsonArray.getString(1));
                    classes.setHitDice(Dice.valueOf(jsonArray.getString(2)));
                    classes.setHitPointsAtHigherLevel(jsonArray.getString(3));
                    for (int j = 4; j < jsonArray.length() - 1; j += 3)
                    {
                        Feature feature = new Feature(jsonArray.getString(j + 1), Integer.parseInt(jsonArray.getString(j)), jsonArray.getString(j + 2));
                        classes.addFeatures(feature);
                    }
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

    public List<Subclass> getSubclassDndFeatures(final AnswerListAsyncResponseSub callBack)
    {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlSubclasses, null, response ->
        {
            for (int i = 0; i < response.length(); i++)
            {
                try
                {
                    JSONArray jsonArray = response.getJSONArray(i);
                    Subclass subclass = new Subclass(jsonArray.getInt(0), jsonArray.getInt(1), jsonArray.getString(2), jsonArray.getString(3));
                    ArrayList<Feature> features = new ArrayList<>();
                    for (int j = 4; j < jsonArray.length() - 1; j += 3)
                    {
                        Feature feature = new Feature(jsonArray.getString(j + 1), jsonArray.getInt(j), jsonArray.getString(j + 2));
                        features.add(feature);
                    }
                    subclass.setFeatures(features);
                    subclassArrayList.add(subclass);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
            if (null != callBack)
            {
                callBack.processFinished(subclassArrayList);
            }
        }, error ->
        {

        });
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        return subclassArrayList;
    }

    public Repository(Application application)
    {
        SheetRoomDatabase db = SheetRoomDatabase.getDatabase(application);
        sheetDao = db.sheetDao();
        allSubclasses = sheetDao.getAllSubclassesDnd();
        allSheetsDnd = sheetDao.getAllSheetsDnd();
        allClasses = sheetDao.getAllClassesDnd();
    }

    public LiveData<List<Subclass>> getAllSubclassesDnd()
    {
        return allSubclasses;
    }

    public LiveData<List<SheetDAndD>> getAllSheetsDnd()
    {
        return allSheetsDnd;
    }

    public LiveData<List<Classes>> getAllClassesDnd()
    {
        return allClasses;
    }

    public LiveData<List<Subclass>> getSomeSubclasses(int id)
    {
        someSubclasses = sheetDao.getAllSubclassesFromClasses(id);
        return someSubclasses;
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

    public void insertSubclassDnd(Subclass subclass)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.insert(subclass);
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

    public void updateSubclassDnd(Subclass subclass)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.updateSubclass(subclass);
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

    public LiveData<Subclass> getSubclassDnd(int id)
    {
        return sheetDao.getSubclass(id);
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
            sheetDao.delete(classes);
        });
    }

    public void deleteSubclassDnd(Subclass subclass)
    {
        SheetRoomDatabase.databaseWriteExecutor.execute(() ->
        {
            sheetDao.delete(subclass);
        });
    }

    public LiveData<List<SheetDAndD>> searchDatabase(String searchQuery)
    {
        return sheetDao.searchDatabase(searchQuery);
    }
}
