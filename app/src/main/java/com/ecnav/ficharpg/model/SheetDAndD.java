package com.ecnav.ficharpg.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ecnav.ficharpg.util.Util;

import java.util.ArrayList;

@Entity(tableName = "character_sheet_table")
public class SheetDAndD
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int gameOfTheSheet = Util.DUNGEONS_AND_DRAGONS;
    private String name;
    private ArrayList<Classes> classFeatures = new ArrayList<>();
    private String subClass;
    private int level = 1;
    private String background;
    private String race;
    private String alignment;
    private String experiencePoints;
    private int age;
    private String eyeColor;
    private double height;
    private double weight;
    private String hairColor;
    private String skinColor;
    private String characterBackstory;
    private int armorClass;
    private int initiative;
    private int speed;
    private int hitPoints;
    private int hitPointsMaximum;
    private int temporaryHitPoints;
    private String totalHitDice;
    private String hitDice;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private boolean inspiration;
    private int proficiencyBonus;
    private int passivePerception;
    private String personalityTraits;
    private String ideals;
    private String bonds;
    private String flaws;
    private String featuresAndTraits;
    private boolean strengthSaveProficiency;
    private boolean dexteritySaveProficiency;
    private boolean constitutionSaveProficiency;
    private boolean intelligenceSaveProficiency;
    private boolean wisdomSaveProficiency;
    private boolean charismaSaveProficiency;
    private boolean acrobaticsProficiency;
    private boolean animalHandlingProficiency;
    private boolean arcanaProficiency;
    private boolean athleticsProficiency;
    private boolean deceptionProficiency;
    private boolean historyProficiency;
    private boolean insightProficiency;
    private boolean intimidationProficiency;
    private boolean investigationProficiency;
    private boolean medicineProficiency;
    private boolean natureProficiency;
    private boolean perceptionProficiency;
    private boolean performanceProficiency;
    private boolean persuasionProficiency;
    private boolean religionProficiency;
    private boolean sleightOfHandProficiency;
    private boolean stealthProficiency;
    private boolean survivalProficiency;
    private boolean expertiseAcrobaticsProficiency;
    private boolean expertiseAnimalHandlingProficiency;
    private boolean expertiseArcanaProficiency;
    private boolean expertiseAthleticsProficiency;
    private boolean expertiseDeceptionProficiency;
    private boolean expertiseHistoryProficiency;
    private boolean expertiseInsightProficiency;
    private boolean expertiseIntimidationProficiency;
    private boolean expertiseInvestigationProficiency;
    private boolean expertiseMedicineProficiency;
    private boolean expertiseNatureProficiency;
    private boolean expertisePerceptionProficiency;
    private boolean expertisePerformanceProficiency;
    private boolean expertisePersuasionProficiency;
    private boolean expertiseReligionProficiency;
    private boolean expertiseSleightOfHandProficiency;
    private boolean expertiseStealthProficiency;
    private boolean expertiseSurvivalProficiency;
    private boolean wearingArmor;
    private boolean patientDefense;
    private boolean unarmoredDefense;
    private int cooperPiece;
    private int silverPiece;
    private int electrumPiece;
    private int goldPiece;
    private int platinumPiece;
    private ArrayList<Equipment> equipments = new ArrayList<>();
    private String spellcastingClass;
    private int spellSaveDc;
    private int spellAttackBonus;
    private int spellSlotsLevel0;
    private int spellSlotsLevel1;
    private int spellSlotsLevel2;
    private int spellSlotsLevel3;
    private int spellSlotsLevel4;
    private int spellSlotsLevel5;
    private int spellSlotsLevel6;
    private int spellSlotsLevel7;
    private int spellSlotsLevel8;
    private int spellSlotsLevel9;
    private ArrayList<Spell> level0 = new ArrayList<>();
    private ArrayList<Spell> level1 = new ArrayList<>();
    private ArrayList<Spell> level2 = new ArrayList<>();
    private ArrayList<Spell> level3 = new ArrayList<>();
    private ArrayList<Spell> level4 = new ArrayList<>();
    private ArrayList<Spell> level5 = new ArrayList<>();
    private ArrayList<Spell> level6 = new ArrayList<>();
    private ArrayList<Spell> level7 = new ArrayList<>();
    private ArrayList<Spell> level8 = new ArrayList<>();
    private ArrayList<Spell> level9 = new ArrayList<>();
    //Class name
    private boolean hasSubClass = false;
    private boolean hasMultiClass = false;

    public SheetDAndD()
    {

    }

    public SheetDAndD(int id, String name, int level, String background, String race, String alignment, String experiencePoints, int age, String eyeColor, double height, double weight, String hairColor, String skinColor, String characterBackstory, int armorClass, int initiative, int speed, int hitPoints, int hitPointsMaximum, int temporaryHitPoints, String totalHitDice, String hitDice, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma, boolean inspiration, int proficiencyBonus, int passivePerception, String personalityTraits, String ideals, String bonds, String flaws, String featuresAndTraits, int strengthSave, int dexteritySave, int constitutionSave, int intelligenceSave, int wisdomSave, int charismaSave, boolean strengthSaveProficiency, boolean dexteritySaveProficiency, boolean constitutionSaveProficiency, boolean intelligenceSaveProficiency, boolean wisdomSaveProficiency, boolean charismaSaveProficiency, int acrobatics, int animalHandling, int arcana, int athletics, int deception, int history, int insight, int intimidation, int investigation, int medicine, int nature, int perception, int performance, int persuasion, int religion, int sleightOfHand, int stealth, int survival, boolean acrobaticsProficiency, boolean animalHandlingProficiency, boolean arcanaProficiency, boolean athleticsProficiency, boolean deceptionProficiency, boolean historyProficiency, boolean insightProficiency, boolean intimidationProficiency, boolean investigationProficiency, boolean medicineProficiency, boolean natureProficiency, boolean perceptionProficiency, boolean performanceProficiency, boolean persuasionProficiency, boolean religionProficiency, boolean sleightOfHandProficiency, boolean stealthProficiency, boolean survivalProficiency, int cooperPiece, int silverPiece, int electrumPiece, int goldPiece, int platinumPiece, String equipments, String spellcastingClass, int spellSaveDc, int spellAttackBonus)
    {
        this.id = id;
        this.name = name;
        this.level = level;
        this.background = background;
        this.race = race;
        this.alignment = alignment;
        this.experiencePoints = experiencePoints;
        this.age = age;
        this.eyeColor = eyeColor;
        this.height = height;
        this.weight = weight;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.characterBackstory = characterBackstory;
        this.armorClass = armorClass;
        this.initiative = initiative;
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.hitPointsMaximum = hitPointsMaximum;
        this.temporaryHitPoints = temporaryHitPoints;
        this.totalHitDice = totalHitDice;
        this.hitDice = hitDice;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.inspiration = inspiration;
        this.proficiencyBonus = proficiencyBonus;
        this.passivePerception = passivePerception;
        this.personalityTraits = personalityTraits;
        this.ideals = ideals;
        this.bonds = bonds;
        this.flaws = flaws;
        this.featuresAndTraits = featuresAndTraits;
        this.strengthSaveProficiency = strengthSaveProficiency;
        this.dexteritySaveProficiency = dexteritySaveProficiency;
        this.constitutionSaveProficiency = constitutionSaveProficiency;
        this.intelligenceSaveProficiency = intelligenceSaveProficiency;
        this.wisdomSaveProficiency = wisdomSaveProficiency;
        this.charismaSaveProficiency = charismaSaveProficiency;
        this.acrobaticsProficiency = acrobaticsProficiency;
        this.animalHandlingProficiency = animalHandlingProficiency;
        this.arcanaProficiency = arcanaProficiency;
        this.athleticsProficiency = athleticsProficiency;
        this.deceptionProficiency = deceptionProficiency;
        this.historyProficiency = historyProficiency;
        this.insightProficiency = insightProficiency;
        this.intimidationProficiency = intimidationProficiency;
        this.investigationProficiency = investigationProficiency;
        this.medicineProficiency = medicineProficiency;
        this.natureProficiency = natureProficiency;
        this.perceptionProficiency = perceptionProficiency;
        this.performanceProficiency = performanceProficiency;
        this.persuasionProficiency = persuasionProficiency;
        this.religionProficiency = religionProficiency;
        this.sleightOfHandProficiency = sleightOfHandProficiency;
        this.stealthProficiency = stealthProficiency;
        this.survivalProficiency = survivalProficiency;
        this.cooperPiece = cooperPiece;
        this.silverPiece = silverPiece;
        this.electrumPiece = electrumPiece;
        this.goldPiece = goldPiece;
        this.platinumPiece = platinumPiece;
        this.spellcastingClass = spellcastingClass;
        this.spellSaveDc = spellSaveDc;
        this.spellAttackBonus = spellAttackBonus;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getGameOfTheSheet()
    {
        return gameOfTheSheet;
    }

    public void setGameOfTheSheet(int gameOfTheSheet)
    {
        this.gameOfTheSheet = gameOfTheSheet;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<Classes> getClassFeatures()
    {
        return classFeatures;
    }

    public void setClassFeatures(ArrayList<Classes> classFeatures)
    {
        this.classFeatures = classFeatures;
    }

    public String getSubClass()
    {
        return subClass;
    }

    public void setSubClass(String subClass)
    {
        this.subClass = subClass;
    }

    public boolean isHasSubClass()
    {
        return hasSubClass;
    }

    public void setHasSubClass(boolean hasSubClass)
    {
        this.hasSubClass = hasSubClass;
    }

    public boolean isHasMultiClass()
    {
        return hasMultiClass;
    }

    public void setHasMultiClass(boolean hasMultiClass)
    {
        this.hasMultiClass = hasMultiClass;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getBackground()
    {
        return background;
    }

    public void setBackground(String background)
    {
        this.background = background;
    }

    public String getRace()
    {
        return race;
    }

    public void setRace(String race)
    {
        this.race = race;
    }

    public String getAlignment()
    {
        return alignment;
    }

    public void setAlignment(String alignment)
    {
        this.alignment = alignment;
    }

    public String getExperiencePoints()
    {
        return experiencePoints;
    }

    public void setExperiencePoints(String experiencePoints)
    {
        this.experiencePoints = experiencePoints;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getEyeColor()
    {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor)
    {
        this.eyeColor = eyeColor;
    }

    public double getHeight()
    {
        return height;
    }

    public void setHeight(double height)
    {
        this.height = height;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public String getHairColor()
    {
        return hairColor;
    }

    public void setHairColor(String hairColor)
    {
        this.hairColor = hairColor;
    }

    public String getSkinColor()
    {
        return skinColor;
    }

    public void setSkinColor(String skinColor)
    {
        this.skinColor = skinColor;
    }

    public String getCharacterBackstory()
    {
        return characterBackstory;
    }

    public void setCharacterBackstory(String characterBackstory)
    {
        this.characterBackstory = characterBackstory;
    }

    public int getArmorClass()
    {
        return armorClass;
    }

    public void setArmorClass(int armorClass)
    {
        this.armorClass = armorClass;
    }

    public int getInitiative()
    {
        return initiative;
    }

    public void setInitiative(int initiative)
    {
        this.initiative = initiative;
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints)
    {
        this.hitPoints = hitPoints;
    }

    public int getHitPointsMaximum()
    {
        return hitPointsMaximum;
    }

    public void setHitPointsMaximum(int hitPointsMaximum)
    {
        this.hitPointsMaximum = hitPointsMaximum;
    }

    public int getTemporaryHitPoints()
    {
        return temporaryHitPoints;
    }

    public void setTemporaryHitPoints(int temporaryHitPoints)
    {
        this.temporaryHitPoints = temporaryHitPoints;
    }

    public String getTotalHitDice()
    {
        return totalHitDice;
    }

    public void setTotalHitDice(String totalHitDice)
    {
        this.totalHitDice = totalHitDice;
    }

    public String getHitDice()
    {
        return hitDice;
    }

    public void setHitDice(String hitDice)
    {
        this.hitDice = hitDice;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getDexterity()
    {
        return dexterity;
    }

    public void setDexterity(int dexterity)
    {
        this.dexterity = dexterity;
    }

    public int getConstitution()
    {
        return constitution;
    }

    public void setConstitution(int constitution)
    {
        this.constitution = constitution;
    }

    public int getIntelligence()
    {
        return intelligence;
    }

    public void setIntelligence(int intelligence)
    {
        this.intelligence = intelligence;
    }

    public int getWisdom()
    {
        return wisdom;
    }

    public void setWisdom(int wisdom)
    {
        this.wisdom = wisdom;
    }

    public int getCharisma()
    {
        return charisma;
    }

    public void setCharisma(int charisma)
    {
        this.charisma = charisma;
    }

    public boolean isInspiration()
    {
        return inspiration;
    }

    public void setInspiration(boolean inspiration)
    {
        this.inspiration = inspiration;
    }

    public int getProficiencyBonus()
    {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus)
    {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getPassivePerception()
    {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception)
    {
        this.passivePerception = passivePerception;
    }

    public String getPersonalityTraits()
    {
        return personalityTraits;
    }

    public void setPersonalityTraits(String personalityTraits)
    {
        this.personalityTraits = personalityTraits;
    }

    public String getIdeals()
    {
        return ideals;
    }

    public void setIdeals(String ideals)
    {
        this.ideals = ideals;
    }

    public String getBonds()
    {
        return bonds;
    }

    public void setBonds(String bonds)
    {
        this.bonds = bonds;
    }

    public String getFlaws()
    {
        return flaws;
    }

    public void setFlaws(String flaws)
    {
        this.flaws = flaws;
    }

    public String getFeaturesAndTraits()
    {
        return featuresAndTraits;
    }

    public void setFeaturesAndTraits(String featuresAndTraits)
    {
        this.featuresAndTraits = featuresAndTraits;
    }

    public boolean isStrengthSaveProficiency()
    {
        return strengthSaveProficiency;
    }

    public void setStrengthSaveProficiency(boolean strengthSaveProficiency)
    {
        this.strengthSaveProficiency = strengthSaveProficiency;
    }

    public boolean isDexteritySaveProficiency()
    {
        return dexteritySaveProficiency;
    }

    public void setDexteritySaveProficiency(boolean dexteritySaveProficiency)
    {
        this.dexteritySaveProficiency = dexteritySaveProficiency;
    }

    public boolean isConstitutionSaveProficiency()
    {
        return constitutionSaveProficiency;
    }

    public void setConstitutionSaveProficiency(boolean constitutionSaveProficiency)
    {
        this.constitutionSaveProficiency = constitutionSaveProficiency;
    }

    public boolean isIntelligenceSaveProficiency()
    {
        return intelligenceSaveProficiency;
    }

    public void setIntelligenceSaveProficiency(boolean intelligenceSaveProficiency)
    {
        this.intelligenceSaveProficiency = intelligenceSaveProficiency;
    }

    public boolean isWisdomSaveProficiency()
    {
        return wisdomSaveProficiency;
    }

    public void setWisdomSaveProficiency(boolean wisdomSaveProficiency)
    {
        this.wisdomSaveProficiency = wisdomSaveProficiency;
    }

    public boolean isCharismaSaveProficiency()
    {
        return charismaSaveProficiency;
    }

    public void setCharismaSaveProficiency(boolean charismaSaveProficiency)
    {
        this.charismaSaveProficiency = charismaSaveProficiency;
    }

    public boolean isAcrobaticsProficiency()
    {
        return acrobaticsProficiency;
    }

    public void setAcrobaticsProficiency(boolean acrobaticsProficiency)
    {
        this.acrobaticsProficiency = acrobaticsProficiency;
    }

    public boolean isAnimalHandlingProficiency()
    {
        return animalHandlingProficiency;
    }

    public void setAnimalHandlingProficiency(boolean animalHandlingProficiency)
    {
        this.animalHandlingProficiency = animalHandlingProficiency;
    }

    public boolean isArcanaProficiency()
    {
        return arcanaProficiency;
    }

    public void setArcanaProficiency(boolean arcanaProficiency)
    {
        this.arcanaProficiency = arcanaProficiency;
    }

    public boolean isAthleticsProficiency()
    {
        return athleticsProficiency;
    }

    public void setAthleticsProficiency(boolean athleticsProficiency)
    {
        this.athleticsProficiency = athleticsProficiency;
    }

    public boolean isDeceptionProficiency()
    {
        return deceptionProficiency;
    }

    public void setDeceptionProficiency(boolean deceptionProficiency)
    {
        this.deceptionProficiency = deceptionProficiency;
    }

    public boolean isHistoryProficiency()
    {
        return historyProficiency;
    }

    public void setHistoryProficiency(boolean historyProficiency)
    {
        this.historyProficiency = historyProficiency;
    }

    public boolean isInsightProficiency()
    {
        return insightProficiency;
    }

    public void setInsightProficiency(boolean insightProficiency)
    {
        this.insightProficiency = insightProficiency;
    }

    public boolean isIntimidationProficiency()
    {
        return intimidationProficiency;
    }

    public void setIntimidationProficiency(boolean intimidationProficiency)
    {
        this.intimidationProficiency = intimidationProficiency;
    }

    public boolean isInvestigationProficiency()
    {
        return investigationProficiency;
    }

    public void setInvestigationProficiency(boolean investigationProficiency)
    {
        this.investigationProficiency = investigationProficiency;
    }

    public boolean isMedicineProficiency()
    {
        return medicineProficiency;
    }

    public void setMedicineProficiency(boolean medicineProficiency)
    {
        this.medicineProficiency = medicineProficiency;
    }

    public boolean isNatureProficiency()
    {
        return natureProficiency;
    }

    public void setNatureProficiency(boolean natureProficiency)
    {
        this.natureProficiency = natureProficiency;
    }

    public boolean isPerceptionProficiency()
    {
        return perceptionProficiency;
    }

    public void setPerceptionProficiency(boolean perceptionProficiency)
    {
        this.perceptionProficiency = perceptionProficiency;
    }

    public boolean isPerformanceProficiency()
    {
        return performanceProficiency;
    }

    public void setPerformanceProficiency(boolean performanceProficiency)
    {
        this.performanceProficiency = performanceProficiency;
    }

    public boolean isPersuasionProficiency()
    {
        return persuasionProficiency;
    }

    public void setPersuasionProficiency(boolean persuasionProficiency)
    {
        this.persuasionProficiency = persuasionProficiency;
    }

    public boolean isReligionProficiency()
    {
        return religionProficiency;
    }

    public void setReligionProficiency(boolean religionProficiency)
    {
        this.religionProficiency = religionProficiency;
    }

    public boolean isSleightOfHandProficiency()
    {
        return sleightOfHandProficiency;
    }

    public void setSleightOfHandProficiency(boolean sleightOfHandProficiency)
    {
        this.sleightOfHandProficiency = sleightOfHandProficiency;
    }

    public boolean isStealthProficiency()
    {
        return stealthProficiency;
    }

    public void setStealthProficiency(boolean stealthProficiency)
    {
        this.stealthProficiency = stealthProficiency;
    }

    public boolean isSurvivalProficiency()
    {
        return survivalProficiency;
    }

    public void setSurvivalProficiency(boolean survivalProficiency)
    {
        this.survivalProficiency = survivalProficiency;
    }

    public boolean isExpertiseAcrobaticsProficiency()
    {
        return expertiseAcrobaticsProficiency;
    }

    public void setExpertiseAcrobaticsProficiency(boolean expertiseAcrobaticsProficiency)
    {
        this.expertiseAcrobaticsProficiency = expertiseAcrobaticsProficiency;
    }

    public boolean isExpertiseAnimalHandlingProficiency()
    {
        return expertiseAnimalHandlingProficiency;
    }

    public void setExpertiseAnimalHandlingProficiency(boolean expertiseAnimalHandlingProficiency)
    {
        this.expertiseAnimalHandlingProficiency = expertiseAnimalHandlingProficiency;
    }

    public boolean isExpertiseArcanaProficiency()
    {
        return expertiseArcanaProficiency;
    }

    public void setExpertiseArcanaProficiency(boolean expertiseArcanaProficiency)
    {
        this.expertiseArcanaProficiency = expertiseArcanaProficiency;
    }

    public boolean isExpertiseAthleticsProficiency()
    {
        return expertiseAthleticsProficiency;
    }

    public void setExpertiseAthleticsProficiency(boolean expertiseAthleticsProficiency)
    {
        this.expertiseAthleticsProficiency = expertiseAthleticsProficiency;
    }

    public boolean isExpertiseDeceptionProficiency()
    {
        return expertiseDeceptionProficiency;
    }

    public void setExpertiseDeceptionProficiency(boolean expertiseDeceptionProficiency)
    {
        this.expertiseDeceptionProficiency = expertiseDeceptionProficiency;
    }

    public boolean isExpertiseHistoryProficiency()
    {
        return expertiseHistoryProficiency;
    }

    public void setExpertiseHistoryProficiency(boolean expertiseHistoryProficiency)
    {
        this.expertiseHistoryProficiency = expertiseHistoryProficiency;
    }

    public boolean isExpertiseInsightProficiency()
    {
        return expertiseInsightProficiency;
    }

    public void setExpertiseInsightProficiency(boolean expertiseInsightProficiency)
    {
        this.expertiseInsightProficiency = expertiseInsightProficiency;
    }

    public boolean isExpertiseIntimidationProficiency()
    {
        return expertiseIntimidationProficiency;
    }

    public void setExpertiseIntimidationProficiency(boolean expertiseIntimidationProficiency)
    {
        this.expertiseIntimidationProficiency = expertiseIntimidationProficiency;
    }

    public boolean isExpertiseInvestigationProficiency()
    {
        return expertiseInvestigationProficiency;
    }

    public void setExpertiseInvestigationProficiency(boolean expertiseInvestigationProficiency)
    {
        this.expertiseInvestigationProficiency = expertiseInvestigationProficiency;
    }

    public boolean isExpertiseMedicineProficiency()
    {
        return expertiseMedicineProficiency;
    }

    public void setExpertiseMedicineProficiency(boolean expertiseMedicineProficiency)
    {
        this.expertiseMedicineProficiency = expertiseMedicineProficiency;
    }

    public boolean isExpertiseNatureProficiency()
    {
        return expertiseNatureProficiency;
    }

    public void setExpertiseNatureProficiency(boolean expertiseNatureProficiency)
    {
        this.expertiseNatureProficiency = expertiseNatureProficiency;
    }

    public boolean isExpertisePerceptionProficiency()
    {
        return expertisePerceptionProficiency;
    }

    public void setExpertisePerceptionProficiency(boolean expertisePerceptionProficiency)
    {
        this.expertisePerceptionProficiency = expertisePerceptionProficiency;
    }

    public boolean isExpertisePerformanceProficiency()
    {
        return expertisePerformanceProficiency;
    }

    public void setExpertisePerformanceProficiency(boolean expertisePerformanceProficiency)
    {
        this.expertisePerformanceProficiency = expertisePerformanceProficiency;
    }

    public boolean isExpertisePersuasionProficiency()
    {
        return expertisePersuasionProficiency;
    }

    public void setExpertisePersuasionProficiency(boolean expertisePersuasionProficiency)
    {
        this.expertisePersuasionProficiency = expertisePersuasionProficiency;
    }

    public boolean isExpertiseReligionProficiency()
    {
        return expertiseReligionProficiency;
    }

    public void setExpertiseReligionProficiency(boolean expertiseReligionProficiency)
    {
        this.expertiseReligionProficiency = expertiseReligionProficiency;
    }

    public boolean isExpertiseSleightOfHandProficiency()
    {
        return expertiseSleightOfHandProficiency;
    }

    public void setExpertiseSleightOfHandProficiency(boolean expertiseSleightOfHandProficiency)
    {
        this.expertiseSleightOfHandProficiency = expertiseSleightOfHandProficiency;
    }

    public boolean isExpertiseStealthProficiency()
    {
        return expertiseStealthProficiency;
    }

    public void setExpertiseStealthProficiency(boolean expertiseStealthProficiency)
    {
        this.expertiseStealthProficiency = expertiseStealthProficiency;
    }

    public boolean isExpertiseSurvivalProficiency()
    {
        return expertiseSurvivalProficiency;
    }

    public void setExpertiseSurvivalProficiency(boolean expertiseSurvivalProficiency)
    {
        this.expertiseSurvivalProficiency = expertiseSurvivalProficiency;
    }

    public boolean isWearingArmor()
    {
        return wearingArmor;
    }

    public void setWearingArmor(boolean wearingArmor)
    {
        this.wearingArmor = wearingArmor;
    }

    public boolean isPatientDefense()
    {
        return patientDefense;
    }

    public void setPatientDefense(boolean patientDefense)
    {
        this.patientDefense = patientDefense;
    }

    public boolean isUnarmoredDefense()
    {
        return unarmoredDefense;
    }

    public void setUnarmoredDefense(boolean unarmoredDefense)
    {
        this.unarmoredDefense = unarmoredDefense;
    }

    public int getCooperPiece()
    {
        return cooperPiece;
    }

    public void setCooperPiece(int cooperPiece)
    {
        this.cooperPiece = cooperPiece;
    }

    public int getSilverPiece()
    {
        return silverPiece;
    }

    public void setSilverPiece(int silverPiece)
    {
        this.silverPiece = silverPiece;
    }

    public int getElectrumPiece()
    {
        return electrumPiece;
    }

    public void setElectrumPiece(int electrumPiece)
    {
        this.electrumPiece = electrumPiece;
    }

    public int getGoldPiece()
    {
        return goldPiece;
    }

    public void setGoldPiece(int goldPiece)
    {
        this.goldPiece = goldPiece;
    }

    public int getPlatinumPiece()
    {
        return platinumPiece;
    }

    public void setPlatinumPiece(int platinumPiece)
    {
        this.platinumPiece = platinumPiece;
    }

    public ArrayList<Equipment> getEquipments()
    {
        return equipments;
    }

    public void setEquipments(ArrayList<Equipment> equipments)
    {
        this.equipments = equipments;
    }

    public int getSpellSlotsLevel0()
    {
        return spellSlotsLevel0;
    }

    public void setSpellSlotsLevel0(int spellSlotsLevel0)
    {
        this.spellSlotsLevel0 = spellSlotsLevel0;
    }

    public int getSpellSlotsLevel1()
    {
        return spellSlotsLevel1;
    }

    public void setSpellSlotsLevel1(int spellSlotsLevel1)
    {
        this.spellSlotsLevel1 = spellSlotsLevel1;
    }

    public int getSpellSlotsLevel2()
    {
        return spellSlotsLevel2;
    }

    public void setSpellSlotsLevel2(int spellSlotsLevel2)
    {
        this.spellSlotsLevel2 = spellSlotsLevel2;
    }

    public int getSpellSlotsLevel3()
    {
        return spellSlotsLevel3;
    }

    public void setSpellSlotsLevel3(int spellSlotsLevel3)
    {
        this.spellSlotsLevel3 = spellSlotsLevel3;
    }

    public int getSpellSlotsLevel4()
    {
        return spellSlotsLevel4;
    }

    public void setSpellSlotsLevel4(int spellSlotsLevel4)
    {
        this.spellSlotsLevel4 = spellSlotsLevel4;
    }

    public int getSpellSlotsLevel5()
    {
        return spellSlotsLevel5;
    }

    public void setSpellSlotsLevel5(int spellSlotsLevel5)
    {
        this.spellSlotsLevel5 = spellSlotsLevel5;
    }

    public int getSpellSlotsLevel6()
    {
        return spellSlotsLevel6;
    }

    public void setSpellSlotsLevel6(int spellSlotsLevel6)
    {
        this.spellSlotsLevel6 = spellSlotsLevel6;
    }

    public int getSpellSlotsLevel7()
    {
        return spellSlotsLevel7;
    }

    public void setSpellSlotsLevel7(int spellSlotsLevel7)
    {
        this.spellSlotsLevel7 = spellSlotsLevel7;
    }

    public int getSpellSlotsLevel8()
    {
        return spellSlotsLevel8;
    }

    public void setSpellSlotsLevel8(int spellSlotsLevel8)
    {
        this.spellSlotsLevel8 = spellSlotsLevel8;
    }

    public int getSpellSlotsLevel9()
    {
        return spellSlotsLevel9;
    }

    public void setSpellSlotsLevel9(int spellSlotsLevel9)
    {
        this.spellSlotsLevel9 = spellSlotsLevel9;
    }

    public String getSpellcastingClass()
    {
        return spellcastingClass;
    }

    public void setSpellcastingClass(String spellcastingClass)
    {
        this.spellcastingClass = spellcastingClass;
    }

    public int getSpellSaveDc()
    {
        return spellSaveDc;
    }

    public void setSpellSaveDc(int spellSaveDc)
    {
        this.spellSaveDc = spellSaveDc;
    }

    public int getSpellAttackBonus()
    {
        return spellAttackBonus;
    }

    public void setSpellAttackBonus(int spellAttackBonus)
    {
        this.spellAttackBonus = spellAttackBonus;
    }

    public ArrayList<Spell> getLevel0()
    {
        return level0;
    }

    public void setLevel0(ArrayList<Spell> level0)
    {
        this.level0 = level0;
    }

    public ArrayList<Spell> getLevel1()
    {
        return level1;
    }

    public void setLevel1(ArrayList<Spell> level1)
    {
        this.level1 = level1;
    }

    public ArrayList<Spell> getLevel2()
    {
        return level2;
    }

    public void setLevel2(ArrayList<Spell> level2)
    {
        this.level2 = level2;
    }

    public ArrayList<Spell> getLevel3()
    {
        return level3;
    }

    public void setLevel3(ArrayList<Spell> level3)
    {
        this.level3 = level3;
    }

    public ArrayList<Spell> getLevel4()
    {
        return level4;
    }

    public void setLevel4(ArrayList<Spell> level4)
    {
        this.level4 = level4;
    }

    public ArrayList<Spell> getLevel5()
    {
        return level5;
    }

    public void setLevel5(ArrayList<Spell> level5)
    {
        this.level5 = level5;
    }

    public ArrayList<Spell> getLevel6()
    {
        return level6;
    }

    public void setLevel6(ArrayList<Spell> level6)
    {
        this.level6 = level6;
    }

    public ArrayList<Spell> getLevel7()
    {
        return level7;
    }

    public void setLevel7(ArrayList<Spell> level7)
    {
        this.level7 = level7;
    }

    public ArrayList<Spell> getLevel8()
    {
        return level8;
    }

    public void setLevel8(ArrayList<Spell> level8)
    {
        this.level8 = level8;
    }

    public ArrayList<Spell> getLevel9()
    {
        return level9;
    }

    public void setLevel9(ArrayList<Spell> level9)
    {
        this.level9 = level9;
    }

    public void addSpells(int level, Spell spell)
    {
        switch (level)
        {
            case 0:
            {
                level0.add(spell);
                break;
            }
            case 1:
            {
                level1.add(spell);
                break;
            }
            case 2:
            {
                level2.add(spell);
                break;
            }
            case 3:
            {
                level3.add(spell);
                break;
            }
            case 4:
            {
                level4.add(spell);
                break;
            }
            case 5:
            {
                level5.add(spell);
                break;
            }
            case 6:
            {
                level6.add(spell);
                break;
            }
            case 7:
            {
                level7.add(spell);
                break;
            }
            case 8:
            {
                level8.add(spell);
                break;
            }
            case 9:
            {
                level9.add(spell);
                break;
            }
        }
    }

    public void addEquipment(Equipment equipment)
    {
        equipments.add(equipment);
    }
}
