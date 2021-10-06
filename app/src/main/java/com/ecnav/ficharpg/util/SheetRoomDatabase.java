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
import com.ecnav.ficharpg.model.ClassFeatures;
import com.ecnav.ficharpg.model.SheetDAndD;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {SheetDAndD.class},
        version = 12,
        autoMigrations = {
                @AutoMigration(from = 11, to = 12)
        },
        exportSchema = true
)
@TypeConverters({Converters.class})
public abstract class SheetRoomDatabase extends RoomDatabase
{
    public abstract SheetDao sheetDao();
    public static final int NUMBER_OF_THREADS = 4;

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
                sheetDao.deleteAll();
            });
        }
    };
}
