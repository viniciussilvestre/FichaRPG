package com.ecnav.ficharpg.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ecnav.ficharpg.util.Dice;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "classes_table")
public class Classes implements Serializable
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
    private ArrayList<String> classFeatures = new ArrayList<>();

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

    public ArrayList<String> getClassFeatures()
    {
        return classFeatures;
    }

    public void setClassFeatures(ArrayList<String> classFeatures)
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

    public void addFeatures(String feature)
    {
        classFeatures.add(feature);
    }
}
