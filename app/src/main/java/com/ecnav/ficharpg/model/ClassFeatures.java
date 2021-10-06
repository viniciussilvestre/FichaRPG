package com.ecnav.ficharpg.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ecnav.ficharpg.util.Dice;

import java.io.Serializable;

public class ClassFeatures implements Serializable
{
    private int classId;
    //Class name
    private String className;
    //Hit points
    private Dice hitDice;
    private int hitPointsAtFirstLevel;
    private String hitPointsAtHigherLevel;
    //Proficiencies
    //Equipment
    private String firstClassFeature;
    private String secondClassFeature;
    private String thirdClassFeature;
    private String forthClassFeature;
    private String abilityScoreImprovement;
    private String fifthClassFeature;
    private String sixthClassFeature;
    private String seventhClassFeature;
    private String eighthClassFeature;
    private String ninthClassFeature;
    private String tenthClassFeature;
    private String eleventhClassFeature;

    public ClassFeatures()
    {

    }

    public ClassFeatures(String className, Dice hitDice, int hitPointsAtFirstLevel, String hitPointsAtHigherLevel, String firstClassFeature, String secondClassFeature, String thirdClassFeature, String forthClassFeature, String abilityScoreImprovement, String fifthClassFeature, String sixthClassFeature, String seventhClassFeature, String eighthClassFeature, String ninthClassFeature, String tenthClassFeature, String eleventhClassFeature)
    {
        this.className = className;
        this.hitDice = hitDice;
        this.hitPointsAtFirstLevel = hitPointsAtFirstLevel;
        this.hitPointsAtHigherLevel = hitPointsAtHigherLevel;
        this.firstClassFeature = firstClassFeature;
        this.secondClassFeature = secondClassFeature;
        this.thirdClassFeature = thirdClassFeature;
        this.forthClassFeature = forthClassFeature;
        this.abilityScoreImprovement = abilityScoreImprovement;
        this.fifthClassFeature = fifthClassFeature;
        this.sixthClassFeature = sixthClassFeature;
        this.seventhClassFeature = seventhClassFeature;
        this.eighthClassFeature = eighthClassFeature;
        this.ninthClassFeature = ninthClassFeature;
        this.tenthClassFeature = tenthClassFeature;
        this.eleventhClassFeature = eleventhClassFeature;
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

    public String getFirstClassFeature()
    {
        return firstClassFeature;
    }

    public void setFirstClassFeature(String firstClassFeature)
    {
        this.firstClassFeature = firstClassFeature;
    }

    public String getSecondClassFeature()
    {
        return secondClassFeature;
    }

    public void setSecondClassFeature(String secondClassFeature)
    {
        this.secondClassFeature = secondClassFeature;
    }

    public String getThirdClassFeature()
    {
        return thirdClassFeature;
    }

    public void setThirdClassFeature(String thirdClassFeature)
    {
        this.thirdClassFeature = thirdClassFeature;
    }

    public String getForthClassFeature()
    {
        return forthClassFeature;
    }

    public void setForthClassFeature(String forthClassFeature)
    {
        this.forthClassFeature = forthClassFeature;
    }

    public String getAbilityScoreImprovement()
    {
        return abilityScoreImprovement;
    }

    public void setAbilityScoreImprovement(String abilityScoreImprovement)
    {
        this.abilityScoreImprovement = abilityScoreImprovement;
    }

    public String getFifthClassFeature()
    {
        return fifthClassFeature;
    }

    public void setFifthClassFeature(String fifthClassFeature)
    {
        this.fifthClassFeature = fifthClassFeature;
    }

    public String getSixthClassFeature()
    {
        return sixthClassFeature;
    }

    public void setSixthClassFeature(String sixthClassFeature)
    {
        this.sixthClassFeature = sixthClassFeature;
    }

    public String getSeventhClassFeature()
    {
        return seventhClassFeature;
    }

    public void setSeventhClassFeature(String seventhClassFeature)
    {
        this.seventhClassFeature = seventhClassFeature;
    }

    public String getEighthClassFeature()
    {
        return eighthClassFeature;
    }

    public void setEighthClassFeature(String eighthClassFeature)
    {
        this.eighthClassFeature = eighthClassFeature;
    }

    public String getNinthClassFeature()
    {
        return ninthClassFeature;
    }

    public void setNinthClassFeature(String ninthClassFeature)
    {
        this.ninthClassFeature = ninthClassFeature;
    }

    public String getTenthClassFeature()
    {
        return tenthClassFeature;
    }

    public void setTenthClassFeature(String tenthClassFeature)
    {
        this.tenthClassFeature = tenthClassFeature;
    }

    public String getEleventhClassFeature()
    {
        return eleventhClassFeature;
    }

    public void setEleventhClassFeature(String eleventhClassFeature)
    {
        this.eleventhClassFeature = eleventhClassFeature;
    }
}
