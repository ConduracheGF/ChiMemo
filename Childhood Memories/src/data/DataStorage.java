package data;

import entity.Entity;
import monster.*;
import main.GamePanel;
import object.OBJ_Interface;

import java.util.ArrayList;
import java.util.HashMap;

public class DataStorage {
    public String name;
    public int maxLife;
    public int maxMana;
    public int mana;
    public int strength;
    public int dexterity;
    public int exp;
    public int nextLevelExp;
    public int score;
    public int harta;
    public int level;
    public int life;
    public int posX;
    public int posY;
    public HashMap<ArrayList<OBJ_Interface>,Integer> inventory;

    //Date salvate in baza de date
    public DataStorage(String name, int harta, int level, int life, int maxLife, int mana, int maxMana, int strength, int dexterity, int exp, int nextLevelExp, int score, int posX, int posY /*,HashMap<ArrayList<OBJ_Interface>, Integer> inventory*/) {
        this.name = name;
        this.harta = harta;
        this.level = level;
        this.life = life;
        this.maxLife = maxLife;
        this.mana = mana;
        this.maxMana = maxMana;
        this.strength = strength;
        this.dexterity = dexterity;
        this.exp = exp;
        this.nextLevelExp = nextLevelExp;
        this.score = score;
        this.posX = posX;
        this.posY = posY;
    }
    public int getLevel() {
        return level;
    }
    public int getLife() {
        return life;
    }
    public int getPosX() {
        return posX;
    }
    public int getPosY() {
        return posY;
    }
}