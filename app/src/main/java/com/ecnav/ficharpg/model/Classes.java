package com.ecnav.ficharpg.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ecnav.ficharpg.util.Dice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "classes_table")
public class Classes implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int classId;
    //Class name
    private String className;
    //Hit points
    private Dice hitDice;
    private int hitPointsAtFirstLevel;
    private String hitPointsAtHigherLevel;
    //Proficiencies
    //Equipment
    private ArrayList<Feature> classFeatures = new ArrayList<>();

    public Classes()
    {

    }

    public Classes(String className, Dice hitDice, int hitPointsAtFirstLevel, String hitPointsAtHigherLevel)
    {
        this.className = className;
        this.hitDice = hitDice;
        this.hitPointsAtFirstLevel = hitPointsAtFirstLevel;
        this.hitPointsAtHigherLevel = hitPointsAtHigherLevel;
    }

    public Classes(Parcel source)
    {
        this.classId = source.readInt();
        this.className = source.readString();
        this.hitDice = (Dice) source.readSerializable();
        this.hitPointsAtFirstLevel = source.readInt();
        this.hitPointsAtHigherLevel = source.readString();
        source.readTypedList(classFeatures, Feature.CREATOR);
    }

    public ArrayList<Feature> getClassFeatures()
    {
        return classFeatures;
    }

    public void setClassFeatures(ArrayList<Feature> classFeatures)
    {
        this.classFeatures = classFeatures;
    }

    public int getClassId()
    {
        return classId;
    }

    public void setClassId(int classId)
    {
        this.classId = classId;
    }

    public String getClassName()
    {
        return className;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public Dice getHitDice()
    {
        return hitDice;
    }

    public void setHitDice(Dice hitDice)
    {
        this.hitDice = hitDice;
    }

    public int getHitPointsAtFirstLevel()
    {
        return hitPointsAtFirstLevel;
    }

    public void setHitPointsAtFirstLevel(int hitPointsAtFirstLevel)
    {
        this.hitPointsAtFirstLevel = hitPointsAtFirstLevel;
    }

    public String getHitPointsAtHigherLevel()
    {
        return hitPointsAtHigherLevel;
    }

    public void setHitPointsAtHigherLevel(String hitPointsAtHigherLevel)
    {
        this.hitPointsAtHigherLevel = hitPointsAtHigherLevel;
    }

    public void addFeatures(Feature feature)
    {
        classFeatures.add(feature);
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(classId);
        dest.writeString(className);
        dest.writeSerializable(hitDice);
        dest.writeInt(hitPointsAtFirstLevel);
        dest.writeString(hitPointsAtHigherLevel);
        dest.writeTypedList(classFeatures);
    }

    public static final Parcelable.Creator<Classes> CREATOR = new Creator<Classes>()
    {
        @Override
        public Classes createFromParcel(Parcel source)
        {
            return new Classes(source);
        }

        @Override
        public Classes[] newArray(int size)
        {
            return new Classes[0];
        }
    };
}
