package com.ecnav.ficharpg.util;

import androidx.room.TypeConverter;

import com.ecnav.ficharpg.model.Classes;
import com.ecnav.ficharpg.model.Equipment;
import com.ecnav.ficharpg.model.Feature;
import com.ecnav.ficharpg.model.Spell;
import com.ecnav.ficharpg.model.Subclass;
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
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Equipment> fromEquipmentString(String value)
    {
        Type listType = new TypeToken<ArrayList<Equipment>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromEquipmentArrayList(ArrayList<Equipment> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Classes> fromClassFeaturesString(String value)
    {
        Type listType = new TypeToken<ArrayList<Classes>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromClassFeaturesArrayList(ArrayList<Classes> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Subclass> fromSubclassFeaturesString(String value)
    {
        Type listType = new TypeToken<ArrayList<Subclass>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromSubclassFeaturesArrayList(ArrayList<Subclass> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static ArrayList<Feature> fromFeaturesString(String value)
    {
        Type listType = new TypeToken<ArrayList<Feature>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromFeaturesArrayList(ArrayList<Feature> list)
    {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
