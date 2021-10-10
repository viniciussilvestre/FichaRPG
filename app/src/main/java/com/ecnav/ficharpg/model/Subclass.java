package com.ecnav.ficharpg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "subclass_table")
public class Subclass
{
    @PrimaryKey(autoGenerate = true)
    private int subclassId;
    @ColumnInfo(defaultValue = "0")
    private int mainsClassId;
    private String mainClass;
    private String subclassName;
    private ArrayList<Feature> features = new ArrayList<>();

    public int getSubclassId()
    {
        return subclassId;
    }

    public void setSubclassId(int subclassId)
    {
        this.subclassId = subclassId;
    }

    public int getMainsClassId()
    {
        return mainsClassId;
    }

    public void setMainsClassId(int mainsClassId)
    {
        this.mainsClassId = mainsClassId;
    }

    public String getMainClass()
    {
        return mainClass;
    }

    public void setMainClass(String mainClass)
    {
        this.mainClass = mainClass;
    }

    public String getSubclassName()
    {
        return subclassName;
    }

    public void setSubclassName(String subclassName)
    {
        this.subclassName = subclassName;
    }

    public ArrayList<Feature> getFeatures()
    {
        return features;
    }

    public void setFeatures(ArrayList<Feature> features)
    {
        this.features = features;
    }

    public void addFeatures(Feature feature)
    {
        features.add(feature);
    }
}
