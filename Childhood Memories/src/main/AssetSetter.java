package main;

import entity.NPC_Ghost;
import monster.MON_GreenSheep;
import monster.MON_Schelet;
import monster.MON_YellowShrek;
import object.OBJ_Baston;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion;

public class AssetSetter {
    GamePanel gp;
    public static int[] creat = new int[5];
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        int mapNum = 0;
        int i = 0;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*34;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*17;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        mapNum++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*29;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*17;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Baston(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*5;
        gp.obj[mapNum][i].worldY = gp.tileSize*5;
        mapNum++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*8;
        gp.obj[mapNum][i].worldY = gp.tileSize*6;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*17;
        gp.obj[mapNum][i].worldY = gp.tileSize*23;
        i++;
        gp.obj[mapNum][i] = new OBJ_Baston(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*34;
        gp.obj[mapNum][i].worldY = gp.tileSize*25;
    }
    public void setNPC() {
        int mapNum = 0;
        int i = 0;

        //Map 0
        gp.npc[mapNum][i] = new NPC_Ghost(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*5;
        gp.npc[mapNum][i].worldY = gp.tileSize*10;
        i++;
    }
    public void setMonster() {
        int mapNum = 0;
        creat[0]=0; creat[1]=0; creat[2]=0;
        gp.monster[mapNum][creat[mapNum]] = new MON_GreenSheep(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*10;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*14;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_GreenSheep(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*23;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*14;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_GreenSheep(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*14;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*23;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_GreenSheep(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*28;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*27;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_GreenSheep(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*32;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*6;
        creat[mapNum]++;

        mapNum = 1;
        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*23;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*5;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*7;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*7;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*32;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*8;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*34;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*12;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*28;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*17;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_Schelet(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*15;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*28;
        creat[mapNum]++;

        mapNum = 2;
        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*11;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*11;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*9;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*10;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*11;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*27;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*9;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*26;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*17;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*28;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*14;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*28;
        creat[mapNum]++;

        gp.monster[mapNum][creat[mapNum]] = new MON_YellowShrek(gp);
        gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize*15;
        gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize*19;
        creat[mapNum]++;
    }
}