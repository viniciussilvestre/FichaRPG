package com.ecnav.ficharpg.model;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ecnav.ficharpg.data.Repository;

import java.util.List;

public class SheetViewModel extends AndroidViewModel
{
    public static Repository repository;
    public final LiveData<List<SheetDAndD>> allSheetsDnd;

    public SheetViewModel(@NonNull Application application)
    {
        super (application);
        repository = new Repository(application);
        allSheetsDnd = repository.getAllSheetsDnd();
    }

    public LiveData<List<SheetDAndD>> getAllSheetsDnd()
    {
        return allSheetsDnd;
    }

    public static void insertDnd(SheetDAndD sheetDAndD)
    {
        repository.insertDnd(sheetDAndD);
    }

    public static void updateDnd(SheetDAndD sheetDAndD)
    {
        repository.updateDnd(sheetDAndD);
    }

    public LiveData<SheetDAndD> getCharacterDnd(int id)
    {
        return repository.getCharacterDnd(id);
    }

    public static void deleteDnd(SheetDAndD sheetDAndD)
    {
        repository.deleteDnd(sheetDAndD);
    }
}
