package com.ecnav.ficharpg.util;

import androidx.room.TypeConverter;
import com.ecnav.ficharpg.model.Spell;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters
{
    @TypeConverter
    public static ArrayList<Spell> fromString(String value)
    {
        Type listType = new TypeToken<ArrayList<Spell>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<Spell> list)
    {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
