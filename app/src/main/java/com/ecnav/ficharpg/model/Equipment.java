package com.ecnav.ficharpg.model;

import com.ecnav.ficharpg.util.Dice;
import com.ecnav.ficharpg.util.EquipmentType;

public class Equipment
{
    private String nome;
    private EquipmentType equipmentType;
    private String description;
    private Dice damageDice;
    private int armorClass;
    private int attackDiceBonus;
    private int damageDiceBonus;
    private int usages;
    private int weight;
    private int amount;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public EquipmentType getEquipmentType()
    {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType)
    {
        this.equipmentType = equipmentType;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Dice getDamageDice()
    {
        return damageDice;
    }

    public void setDamageDice(Dice damageDice)
    {
        this.damageDice = damageDice;
    }

    public int getArmorClass()
    {
        return armorClass;
    }

    public void setArmorClass(int armorClass)
    {
        this.armorClass = armorClass;
    }

    public int getAttackDiceBonus()
    {
        return attackDiceBonus;
    }

    public void setAttackDiceBonus(int attackDiceBonus)
    {
        this.attackDiceBonus = attackDiceBonus;
    }

    public int getDamageDiceBonus()
    {
        return damageDiceBonus;
    }

    public void setDamageDiceBonus(int damageDiceBonus)
    {
        this.damageDiceBonus = damageDiceBonus;
    }

    public int getUsages()
    {
        return usages;
    }

    public void setUsages(int usages)
    {
        this.usages = usages;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }
}
