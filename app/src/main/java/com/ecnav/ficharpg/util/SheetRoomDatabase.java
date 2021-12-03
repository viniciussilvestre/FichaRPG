package com.ecnav.ficharpg.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.DeleteColumn;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.AutoMigrationSpec;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ecnav.ficharpg.data.SheetDao;
import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.SheetDAndD;
import com.ecnav.ficharpg.model.Subclass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {SheetDAndD.class, Classes.class, Subclass.class},
        version = 30,
        autoMigrations = {
                @AutoMigration(from = 29, to = 30, spec = SheetRoomDatabase.myAutoMigration.class),
        },
        exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class SheetRoomDatabase extends RoomDatabase
{
    public abstract SheetDao sheetDao();
    public static final int NUMBER_OF_THREADS = 4;

    @DeleteColumn.Entries({
            @DeleteColumn(tableName = "character_sheet_table", columnName = "classFeatures"),
            @DeleteColumn(tableName = "character_sheet_table", columnName = "subclasses"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "forthClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "className"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "hitPointsAtHigherLevel"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "tenthClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "abilityScoreImprovement"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "ninthClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "seventhClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "eleventhClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "secondClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "thirdClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "eighthClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "firstClassFeature"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "hitPointsAtFirstLevel"),
//            @DeleteColumn(tableName = "character_sheet_table", columnName = "characterClass"),
//            @DeleteColumn(tableName = "classes_table", columnName = "subclassFeatures")
    })
    static class myAutoMigration implements AutoMigrationSpec
    {

    }

    private static volatile SheetRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SheetRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (SheetRoomDatabase.class)
            {
                if (INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), SheetRoomDatabase.class, "sheet_database")
                            //.addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback sRoomDatabaseCallBack = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            databaseWriteExecutor.execute(() ->
            {
                SheetDao sheetDao = INSTANCE.sheetDao();
            });
        }
    };
}
