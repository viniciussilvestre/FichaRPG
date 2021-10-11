package com.ecnav.ficharpg.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "subclass_table")
public class Subclass implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int subclassId;
    @ColumnInfo(defaultValue = "0")
    private int mainsClassId;
    private String mainClass;
    private String subclassName;
    private ArrayList<Feature> features = new ArrayList<>();

    public Subclass()
    {

    }

    public Subclass(int subclassId, int mainsClassId, String mainClass, String subclassName)
    {
        this.subclassId = subclassId;
        this.mainsClassId = mainsClassId;
        this.mainClass = mainClass;
        this.subclassName = subclassName;
    }

    public Subclass(Parcel source)
    {
        this.subclassId = source.readInt();
        this.mainsClassId = source.readInt();
        this.mainClass = source.readString();
        this.subclassName = source.readString();
        source.readTypedList(features, Feature.CREATOR);
    }

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

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(subclassId);
        dest.writeInt(mainsClassId);
        dest.writeString(mainClass);
        dest.writeString(subclassName);
        dest.writeTypedList(features);
    }

    public static final Parcelable.Creator<Subclass> CREATOR = new Creator<Subclass>()
    {

        @Override
        public Subclass createFromParcel(Parcel source)
        {
            return new Subclass(source);
        }

        @Override
        public Subclass[] newArray(int size)
        {
            return new Subclass[0];
        }
    };
}
