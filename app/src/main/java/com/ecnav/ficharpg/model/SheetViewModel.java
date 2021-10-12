package com.ecnav.ficharpg.model;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ecnav.ficharpg.data.Repository;

import java.util.List;

public class SheetViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<SheetDAndD>> allSheetsDnd;
    public final LiveData<List<Classes>> allClasses;
    public final LiveData<List<Subclass>> allSubclasses;

    public SheetViewModel(@NonNull Application application)
    {
        super (application);
        repository = new Repository(application);
        allSheetsDnd = repository.getAllSheetsDnd();
        allClasses = repository.getAllClassesDnd();
        allSubclasses = repository.getAllSubclassesDnd();
    }

    public LiveData<List<Subclass>> getAllSubclasses()
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
        return repository.getSomeSubclasses(id);
    }

    public static void insertDnd(SheetDAndD sheetDAndD)
    {
        repository.insertDnd(sheetDAndD);
    }

    public static void insertClassDnd(Classes classes)
    {
        repository.insertClassDnd(classes);
    }

    public static void insertSubclassDnd(Subclass subclass)
    {
        repository.insertSubclassDnd(subclass);
    }

    public static void updateDnd(SheetDAndD sheetDAndD)
    {
        repository.updateDnd(sheetDAndD);
    }

    public static void updateClassDnd(Classes classes)
    {
        repository.updateClassDnd(classes);
    }

    public static void updateSubclassDnd(Subclass subclass)
    {
        repository.updateSubclassDnd(subclass);
    }

    public LiveData<SheetDAndD> getCharacterDnd(int id)
    {
        return repository.getCharacterDnd(id);
    }

    public LiveData<Classes> getClassDnd(int id)
    {
        return repository.getClassDnd(id);
    }

    public LiveData<Subclass> getSubclassDnd(int id)
    {
        return repository.getSubclassDnd(id);
    }

    public static void deleteDnd(SheetDAndD sheetDAndD)
    {
        repository.deleteDnd(sheetDAndD);
    }

    public static void deleteClassDnd(Classes classes)
    {
        repository.deleteClassDnd(classes);
    }

    public static void deleteSubclassDnd(Subclass subclass)
    {
        repository.deleteSubclassDnd(subclass);
    }

    public LiveData<List<SheetDAndD>> searchDatabase(String searchQuery)
    {
        return repository.searchDatabase(searchQuery);
    }
}
