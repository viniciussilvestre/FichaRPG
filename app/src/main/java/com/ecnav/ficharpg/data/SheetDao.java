package com.ecnav.ficharpg.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.SheetDAndD;

import java.util.List;

@Dao
public interface SheetDao
{
    //---------------------------------------------------//
    //Dungeons and dragon
    //---------------------------------------------------//
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(SheetDAndD sheetDAndD);
    @Delete()
    void delete(SheetDAndD sheetDAndD);
    @Query("SELECT * FROM character_sheet_table ORDER BY name ASC")
    LiveData<List<SheetDAndD>> getAllSheetsDnd();
    @Query("SELECT * FROM character_sheet_table WHERE character_sheet_table.id == :id")
    LiveData<SheetDAndD> getCharacterDnd(int id);
    @Update
    void updateDnd(SheetDAndD sheetDAndD);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Classes classes);
    @Delete()
    void delete(Classes classes);
    @Query("SELECT * FROM classes_table ORDER BY className ASC")
    LiveData<List<Classes>> getAllClassesDnd();
    @Query("SELECT * FROM classes_table WHERE classes_table.classId == :id")
    LiveData<Classes> getClassDnd(int id);
    @Update
    void updateClass(Classes classes);

    //---------------------------------------------------//
    //All sheets
    //---------------------------------------------------//
    @Query("DELETE FROM character_sheet_table")
    void deleteAll();
    //---------------------------------------------------//
    @Query("SELECT * FROM character_sheet_table WHERE name LIKE :searchQuery")
    LiveData<List<SheetDAndD>> searchDatabase(String searchQuery);
}
