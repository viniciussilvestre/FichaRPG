package com.ecnav.ficharpg.model;

public class Spell
{
    private String spellName;
    private int spellLevel;
    private String spellDescription;
    private boolean somatic;
    private boolean verbal;
    private boolean material;
    private String materialComponents;

    public Spell()
    {
    }

    public Spell(String spellName, int spellLevel, String spellDescription, boolean somatic, boolean verbal, boolean material, String materialComponents)
    {
        this.spellName = spellName;
        this.spellLevel = spellLevel;
        this.spellDescription = spellDescription;
        this.somatic = somatic;
        this.verbal = verbal;
        this.material = material;
        this.materialComponents = materialComponents;
    }

    public String getSpellName()
    {
        return spellName;
    }

    public void setSpellName(String spellName)
    {
        this.spellName = spellName;
    }

    public int getSpellLevel()
    {
        return spellLevel;
    }

    public void setSpellLevel(int spellLevel)
    {
        this.spellLevel = spellLevel;
    }

    public String getSpellDescription()
    {
        return spellDescription;
    }

    public void setSpellDescription(String spellDescription)
    {
        this.spellDescription = spellDescription;
    }

    public boolean isSomatic()
    {
        return somatic;
    }

    public void setSomatic(boolean somatic)
    {
        this.somatic = somatic;
    }

    public boolean isVerbal()
    {
        return verbal;
    }

    public void setVerbal(boolean verbal)
    {
        this.verbal = verbal;
    }

    public boolean isMaterial()
    {
        return material;
    }

    public void setMaterial(boolean material)
    {
        this.material = material;
    }

    public String getMaterialComponents()
    {
        return materialComponents;
    }

    public void setMaterialComponents(String materialComponents)
    {
        this.materialComponents = materialComponents;
    }
}
