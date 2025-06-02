package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.AssetSetter.creat;

public class Player extends Entity{
    KeyHandler keyH;
    public final int screenX;
    public String name = "Mihai";
    public final int screenY;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        //SOLID AREA
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
        speed = 8;
        direction = "down";

        //PLAYER STATUS
        level = 1;
        exp = 0;
        nextLevelExp = 10;
        ammo = 7;
        maxLife = 6;
        maxMana = 5;
        mana = maxMana;
        strength = 1;
        dexterity = 0;
        life = maxLife;
        score = 0;
        kills = 0;
        currentWeapon = new OBJ_Sabie(gp);
        currentArmour = new OBJ_Armour(gp);
        projectile = new OBJ_Fireball(gp);
        attack = getAttack();
        defense = getDefense();

        getPlayerImage();
        getPlayerAtackImage();
        setItems();
    }
    public void setDefaultPositions() {
        if(gp.currentMap == 0){
            gp.player.kills = 0;
        } else if (gp.currentMap == 1) {
            gp.player.kills = creat[0];
        } else if (gp.currentMap == 2) {
            gp.player.kills = creat[0]+creat[1];
        }
        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
        direction = "down";

    }
    public void restoreStatus() {
        life = maxLife;
        mana = maxMana;
        speed = 8;
        invincible = false;
        attacking = false;
    }
    public void setItems() {
        inventory.clear();
        inventory.add(currentWeapon);
        inventory.add(currentArmour);
        inventory.add(new OBJ_Potion(gp));
    }
    public int getAttack(){
        attackArea = currentWeapon.attackArea;
        motion1_duration = currentWeapon.motion1_duration;
        motion2_duration = currentWeapon.motion2_duration;
        return attack = strength * currentWeapon.attackValue;
    }
    public int getDefense(){
        return defense = dexterity * currentArmour.defenseValue;
    }
    public int getCurrentWeaponSlot() {
        int currentWeaponSlot = 0;
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentWeapon) {
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }
    public int getCurrentArmourSlot() {
        int currentArmourSlot = 0;
        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i) == currentArmour) {
                currentArmourSlot = i;
            }
        }
        return currentArmourSlot;
    }
    public void getPlayerImage(){
        if (currentWeapon.type == type_sword) {
            up1 = setup("/Mihai/Mihai_sabie/walk/l0_walk_01", gp.tileSize, gp.tileSize);
            up2 = setup("/Mihai/Mihai_sabie/walk/l0_walk_02", gp.tileSize, gp.tileSize);
            up3 = setup("/Mihai/Mihai_sabie/walk/l0_walk_03", gp.tileSize, gp.tileSize);
            up4 = setup("/Mihai/Mihai_sabie/walk/l0_walk_04", gp.tileSize, gp.tileSize);
            up5 = setup("/Mihai/Mihai_sabie/walk/l0_walk_05", gp.tileSize, gp.tileSize);
            up6 = setup("/Mihai/Mihai_sabie/walk/l0_walk_06", gp.tileSize, gp.tileSize);
            up7 = setup("/Mihai/Mihai_sabie/walk/l0_walk_07", gp.tileSize, gp.tileSize);
            up8 = setup("/Mihai/Mihai_sabie/walk/l0_walk_08", gp.tileSize, gp.tileSize);
            up9 = setup("/Mihai/Mihai_sabie/walk/l0_walk_09", gp.tileSize, gp.tileSize);
            left1 = setup("/Mihai/Mihai_sabie/walk/l0_walk_10", gp.tileSize, gp.tileSize);
            left2 = setup("/Mihai/Mihai_sabie/walk/l0_walk_11", gp.tileSize, gp.tileSize);
            left3 = setup("/Mihai/Mihai_sabie/walk/l0_walk_12", gp.tileSize, gp.tileSize);
            left4 = setup("/Mihai/Mihai_sabie/walk/l0_walk_13", gp.tileSize, gp.tileSize);
            left5 = setup("/Mihai/Mihai_sabie/walk/l0_walk_14", gp.tileSize, gp.tileSize);
            left6 = setup("/Mihai/Mihai_sabie/walk/l0_walk_15", gp.tileSize, gp.tileSize);
            left7 = setup("/Mihai/Mihai_sabie/walk/l0_walk_16", gp.tileSize, gp.tileSize);
            left8 = setup("/Mihai/Mihai_sabie/walk/l0_walk_17", gp.tileSize, gp.tileSize);
            left9 = setup("/Mihai/Mihai_sabie/walk/l0_walk_18", gp.tileSize, gp.tileSize);
            down1 = setup("/Mihai/Mihai_sabie/walk/l0_walk_19", gp.tileSize, gp.tileSize);
            down2 = setup("/Mihai/Mihai_sabie/walk/l0_walk_20", gp.tileSize, gp.tileSize);
            down3 = setup("/Mihai/Mihai_sabie/walk/l0_walk_21", gp.tileSize, gp.tileSize);
            down4 = setup("/Mihai/Mihai_sabie/walk/l0_walk_22", gp.tileSize, gp.tileSize);
            down5 = setup("/Mihai/Mihai_sabie/walk/l0_walk_23", gp.tileSize, gp.tileSize);
            down6 = setup("/Mihai/Mihai_sabie/walk/l0_walk_24", gp.tileSize, gp.tileSize);
            down7 = setup("/Mihai/Mihai_sabie/walk/l0_walk_25", gp.tileSize, gp.tileSize);
            down8 = setup("/Mihai/Mihai_sabie/walk/l0_walk_26", gp.tileSize, gp.tileSize);
            down9 = setup("/Mihai/Mihai_sabie/walk/l0_walk_27", gp.tileSize, gp.tileSize);
            right1 = setup("/Mihai/Mihai_sabie/walk/l0_walk_28", gp.tileSize, gp.tileSize);
            right2 = setup("/Mihai/Mihai_sabie/walk/l0_walk_29", gp.tileSize, gp.tileSize);
            right3 = setup("/Mihai/Mihai_sabie/walk/l0_walk_30", gp.tileSize, gp.tileSize);
            right4 = setup("/Mihai/Mihai_sabie/walk/l0_walk_31", gp.tileSize, gp.tileSize);
            right5 = setup("/Mihai/Mihai_sabie/walk/l0_walk_32", gp.tileSize, gp.tileSize);
            right6 = setup("/Mihai/Mihai_sabie/walk/l0_walk_33", gp.tileSize, gp.tileSize);
            right7 = setup("/Mihai/Mihai_sabie/walk/l0_walk_34", gp.tileSize, gp.tileSize);
            right8 = setup("/Mihai/Mihai_sabie/walk/l0_walk_35", gp.tileSize, gp.tileSize);
            right9 = setup("/Mihai/Mihai_sabie/walk/l0_walk_36", gp.tileSize, gp.tileSize);
        } else if(currentWeapon.type == type_baston_magic) {
            up1 = setup("/Mihai/Mihai_magic/walk/l0_walk_01", gp.tileSize, gp.tileSize);
            up2 = setup("/Mihai/Mihai_magic/walk/l0_walk_02", gp.tileSize, gp.tileSize);
            up3 = setup("/Mihai/Mihai_magic/walk/l0_walk_03", gp.tileSize, gp.tileSize);
            up4 = setup("/Mihai/Mihai_magic/walk/l0_walk_04", gp.tileSize, gp.tileSize);
            up5 = setup("/Mihai/Mihai_magic/walk/l0_walk_05", gp.tileSize, gp.tileSize);
            up6 = setup("/Mihai/Mihai_magic/walk/l0_walk_06", gp.tileSize, gp.tileSize);
            up7 = setup("/Mihai/Mihai_magic/walk/l0_walk_07", gp.tileSize, gp.tileSize);
            up8 = setup("/Mihai/Mihai_magic/walk/l0_walk_08", gp.tileSize, gp.tileSize);
            up9 = setup("/Mihai/Mihai_magic/walk/l0_walk_09", gp.tileSize, gp.tileSize);
            left1 = setup("/Mihai/Mihai_magic/walk/l0_walk_10", gp.tileSize, gp.tileSize);
            left2 = setup("/Mihai/Mihai_magic/walk/l0_walk_11", gp.tileSize, gp.tileSize);
            left3 = setup("/Mihai/Mihai_magic/walk/l0_walk_12", gp.tileSize, gp.tileSize);
            left4 = setup("/Mihai/Mihai_magic/walk/l0_walk_13", gp.tileSize, gp.tileSize);
            left5 = setup("/Mihai/Mihai_magic/walk/l0_walk_14", gp.tileSize, gp.tileSize);
            left6 = setup("/Mihai/Mihai_magic/walk/l0_walk_15", gp.tileSize, gp.tileSize);
            left7 = setup("/Mihai/Mihai_magic/walk/l0_walk_16", gp.tileSize, gp.tileSize);
            left8 = setup("/Mihai/Mihai_magic/walk/l0_walk_17", gp.tileSize, gp.tileSize);
            left9 = setup("/Mihai/Mihai_magic/walk/l0_walk_18", gp.tileSize, gp.tileSize);
            down1 = setup("/Mihai/Mihai_magic/walk/l0_walk_19", gp.tileSize, gp.tileSize);
            down2 = setup("/Mihai/Mihai_magic/walk/l0_walk_20", gp.tileSize, gp.tileSize);
            down3 = setup("/Mihai/Mihai_magic/walk/l0_walk_21", gp.tileSize, gp.tileSize);
            down4 = setup("/Mihai/Mihai_magic/walk/l0_walk_22", gp.tileSize, gp.tileSize);
            down5 = setup("/Mihai/Mihai_magic/walk/l0_walk_23", gp.tileSize, gp.tileSize);
            down6 = setup("/Mihai/Mihai_magic/walk/l0_walk_24", gp.tileSize, gp.tileSize);
            down7 = setup("/Mihai/Mihai_magic/walk/l0_walk_25", gp.tileSize, gp.tileSize);
            down8 = setup("/Mihai/Mihai_magic/walk/l0_walk_26", gp.tileSize, gp.tileSize);
            down9 = setup("/Mihai/Mihai_magic/walk/l0_walk_27", gp.tileSize, gp.tileSize);
            right1 = setup("/Mihai/Mihai_magic/walk/l0_walk_28", gp.tileSize, gp.tileSize);
            right2 = setup("/Mihai/Mihai_magic/walk/l0_walk_29", gp.tileSize, gp.tileSize);
            right3 = setup("/Mihai/Mihai_magic/walk/l0_walk_30", gp.tileSize, gp.tileSize);
            right4 = setup("/Mihai/Mihai_magic/walk/l0_walk_31", gp.tileSize, gp.tileSize);
            right5 = setup("/Mihai/Mihai_magic/walk/l0_walk_32", gp.tileSize, gp.tileSize);
            right6 = setup("/Mihai/Mihai_magic/walk/l0_walk_33", gp.tileSize, gp.tileSize);
            right7 = setup("/Mihai/Mihai_magic/walk/l0_walk_34", gp.tileSize, gp.tileSize);
            right8 = setup("/Mihai/Mihai_magic/walk/l0_walk_35", gp.tileSize, gp.tileSize);
            right9 = setup("/Mihai/Mihai_magic/walk/l0_walk_36", gp.tileSize, gp.tileSize);

        } else {
            up1 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk00", gp.tileSize, gp.tileSize);
            up2 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk01", gp.tileSize, gp.tileSize);
            up3 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk02", gp.tileSize, gp.tileSize);
            up4 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk03", gp.tileSize, gp.tileSize);
            up5 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk04", gp.tileSize, gp.tileSize);
            up6 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk05", gp.tileSize, gp.tileSize);
            up7 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk06", gp.tileSize, gp.tileSize);
            up8 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk07", gp.tileSize, gp.tileSize);
            up9 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk08", gp.tileSize, gp.tileSize);
            left1 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk09", gp.tileSize, gp.tileSize);
            left2 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk10", gp.tileSize, gp.tileSize);
            left3 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk11", gp.tileSize, gp.tileSize);
            left4 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk12", gp.tileSize, gp.tileSize);
            left5 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk13", gp.tileSize, gp.tileSize);
            left6 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk14", gp.tileSize, gp.tileSize);
            left7 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk15", gp.tileSize, gp.tileSize);
            left8 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk16", gp.tileSize, gp.tileSize);
            left9 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk17", gp.tileSize, gp.tileSize);
            down1 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk18", gp.tileSize, gp.tileSize);
            down2 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk19", gp.tileSize, gp.tileSize);
            down3 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk20", gp.tileSize, gp.tileSize);
            down4 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk21", gp.tileSize, gp.tileSize);
            down5 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk22", gp.tileSize, gp.tileSize);
            down6 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk23", gp.tileSize, gp.tileSize);
            down7 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk24", gp.tileSize, gp.tileSize);
            down8 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk25", gp.tileSize, gp.tileSize);
            down9 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk26", gp.tileSize, gp.tileSize);
            right1 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk27", gp.tileSize, gp.tileSize);
            right2 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk28", gp.tileSize, gp.tileSize);
            right3 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk29", gp.tileSize, gp.tileSize);
            right4 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk30", gp.tileSize, gp.tileSize);
            right5 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk31", gp.tileSize, gp.tileSize);
            right6 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk32", gp.tileSize, gp.tileSize);
            right7 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk33", gp.tileSize, gp.tileSize);
            right8 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk34", gp.tileSize, gp.tileSize);
            right9 = setup("/Mihai/Mihai_walk/sprite_Mihai_walk35", gp.tileSize, gp.tileSize);
        }
    }
    public void getPlayerAtackImage() {
        if (currentWeapon.type == type_sword) {
            atackUp1 = setup("/Mihai/Mihai_sabie/1", gp.tileSize, gp.tileSize);
            atackUp2 = setup("/Mihai/Mihai_sabie/2", gp.tileSize, gp.tileSize);
            atackUp3 = setup("/Mihai/Mihai_sabie/3", gp.tileSize, gp.tileSize);
            atackUp4 = setup("/Mihai/Mihai_sabie/4", gp.tileSize, gp.tileSize);
            atackUp5 = setup("/Mihai/Mihai_sabie/5", gp.tileSize, gp.tileSize);
            atackUp6 = setup("/Mihai/Mihai_sabie/6", gp.tileSize, gp.tileSize);
            atackUp7 = setup("/Mihai/Mihai_sabie/1", gp.tileSize, gp.tileSize);
            atackUp8 = setup("/Mihai/Mihai_sabie/2", gp.tileSize, gp.tileSize);
            atackUp9 = setup("/Mihai/Mihai_sabie/3", gp.tileSize, gp.tileSize);

            atackLeft1 = setup("/Mihai/Mihai_sabie/7", gp.tileSize, gp.tileSize);
            atackLeft2 = setup("/Mihai/Mihai_sabie/8", gp.tileSize, gp.tileSize);
            atackLeft3 = setup("/Mihai/Mihai_sabie/9", gp.tileSize, gp.tileSize);
            atackLeft4 = setup("/Mihai/Mihai_sabie/10", gp.tileSize, gp.tileSize);
            atackLeft5 = setup("/Mihai/Mihai_sabie/11", gp.tileSize, gp.tileSize);
            atackLeft6 = setup("/Mihai/Mihai_sabie/12", gp.tileSize, gp.tileSize);
            atackLeft7 = setup("/Mihai/Mihai_sabie/7", gp.tileSize, gp.tileSize);
            atackLeft8 = setup("/Mihai/Mihai_sabie/8", gp.tileSize, gp.tileSize);
            atackLeft9 = setup("/Mihai/Mihai_sabie/9", gp.tileSize, gp.tileSize);

            atackDown1 = setup("/Mihai/Mihai_sabie/13", gp.tileSize, gp.tileSize);
            atackDown2 = setup("/Mihai/Mihai_sabie/14", gp.tileSize, gp.tileSize);
            atackDown3 = setup("/Mihai/Mihai_sabie/15", gp.tileSize, gp.tileSize);
            atackDown4 = setup("/Mihai/Mihai_sabie/16", gp.tileSize, gp.tileSize);
            atackDown5 = setup("/Mihai/Mihai_sabie/17", gp.tileSize, gp.tileSize);
            atackDown6 = setup("/Mihai/Mihai_sabie/18", gp.tileSize, gp.tileSize);
            atackDown7 = setup("/Mihai/Mihai_sabie/13", gp.tileSize, gp.tileSize);
            atackDown8 = setup("/Mihai/Mihai_sabie/14", gp.tileSize, gp.tileSize);
            atackDown9 = setup("/Mihai/Mihai_sabie/15", gp.tileSize, gp.tileSize);

            atackRight1 = setup("/Mihai/Mihai_sabie/19", gp.tileSize, gp.tileSize);
            atackRight2 = setup("/Mihai/Mihai_sabie/20", gp.tileSize, gp.tileSize);
            atackRight3 = setup("/Mihai/Mihai_sabie/21", gp.tileSize, gp.tileSize);
            atackRight4 = setup("/Mihai/Mihai_sabie/22", gp.tileSize, gp.tileSize);
            atackRight5 = setup("/Mihai/Mihai_sabie/23", gp.tileSize, gp.tileSize);
            atackRight6 = setup("/Mihai/Mihai_sabie/24", gp.tileSize, gp.tileSize);
            atackRight7 = setup("/Mihai/Mihai_sabie/19", gp.tileSize, gp.tileSize);
            atackRight8 = setup("/Mihai/Mihai_sabie/20", gp.tileSize, gp.tileSize);
            atackRight9 = setup("/Mihai/Mihai_sabie/21", gp.tileSize, gp.tileSize);
        }
        if(currentWeapon.type == type_baston_magic){
            atackUp1 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_01", gp.tileSize, gp.tileSize);
            atackUp2 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_02", gp.tileSize, gp.tileSize);
            atackUp3 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_03", gp.tileSize, gp.tileSize);
            atackUp4 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_04", gp.tileSize, gp.tileSize);
            atackUp5 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_05", gp.tileSize, gp.tileSize);
            atackUp6 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_06", gp.tileSize, gp.tileSize);
            atackUp7 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_01", gp.tileSize, gp.tileSize);
            atackUp8 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_02", gp.tileSize, gp.tileSize);
            atackUp9 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_03", gp.tileSize, gp.tileSize);

            atackLeft1 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_09", gp.tileSize, gp.tileSize);
            atackLeft2 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_10", gp.tileSize, gp.tileSize);
            atackLeft3 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_11", gp.tileSize, gp.tileSize);
            atackLeft4 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_12", gp.tileSize, gp.tileSize);
            atackLeft5 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_13", gp.tileSize, gp.tileSize);
            atackLeft6 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_14", gp.tileSize, gp.tileSize);
            atackLeft7 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_09", gp.tileSize, gp.tileSize);
            atackLeft8 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_10", gp.tileSize, gp.tileSize);
            atackLeft9 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_11", gp.tileSize, gp.tileSize);

            atackDown1 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_17", gp.tileSize, gp.tileSize);
            atackDown2 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_18", gp.tileSize, gp.tileSize);
            atackDown3 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_19", gp.tileSize, gp.tileSize);
            atackDown4 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_20", gp.tileSize, gp.tileSize);
            atackDown5 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_21", gp.tileSize, gp.tileSize);
            atackDown6 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_22", gp.tileSize, gp.tileSize);
            atackDown7 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_17", gp.tileSize, gp.tileSize);
            atackDown8 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_18", gp.tileSize, gp.tileSize);
            atackDown9 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_19", gp.tileSize, gp.tileSize);

            atackRight1 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_25", gp.tileSize, gp.tileSize);
            atackRight2 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_26", gp.tileSize, gp.tileSize);
            atackRight3 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_27", gp.tileSize, gp.tileSize);
            atackRight4 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_28", gp.tileSize, gp.tileSize);
            atackRight5 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_29", gp.tileSize, gp.tileSize);
            atackRight6 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_30", gp.tileSize, gp.tileSize);
            atackRight7 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_25", gp.tileSize, gp.tileSize);
            atackRight8 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_26", gp.tileSize, gp.tileSize);
            atackRight9 = setup("/Mihai/Mihai_magic/thrust_oversize/l0_shoot_27", gp.tileSize, gp.tileSize);
        }
    }
    public void update(){
        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.spacePressed == true)
        {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if(keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false && keyH.spacePressed == false){
                switch (direction){
                    case "up": worldY  -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            if(keyH.spacePressed && attackCanceled == false){
                gp.playSE(1);
                attacking = true;
                spriteCounter = 0;
            }

            attackCanceled = false;
            gp.keyH.spacePressed = false;

            spriteCounter++;
            if(spriteCounter > 12)
            {
                if(spriteNum == 1)
                {
                    spriteNum = 2;
                }
                else if(spriteNum == 2)
                {
                    spriteNum = 3;
                }
                else if(spriteNum == 3)
                {
                    spriteNum = 4;
                }
                else if(spriteNum == 4)
                {
                    spriteNum = 5;
                }
                else if(spriteNum == 5)
                {
                    spriteNum = 6;
                }
                else if(spriteNum == 6)
                {
                    spriteNum = 7;
                }
                else if(spriteNum == 7)
                {
                    spriteNum = 8;
                }
                else if(spriteNum == 8)
                {
                    spriteNum = 9;
                } else if(spriteNum == 9)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if(gp.keyH.shotKeyPressed == true && projectile.alive == false && shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
            // SET DEFAULT COORDINATES, DIRECTION AND USER
            Projectile p = new OBJ_Fireball(gp);
            p.set(worldX, worldY, direction, true, this);
            //ADD IT TO THE LIST
            gp.projectileList.add(p);
            //SUBSTRACT THE COST(MANA, AMMO, ETC)
            p.substractResource(this);
            //sound
            gp.playSE(5);
            shotAvailableCounter = 0;
        }
        //This needs to be of key is statement!
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }

        if(life > maxLife) {
            life = maxLife;
        }
        if(mana > maxMana) {
            mana = maxMana;
        }
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSE(4);
        }
    }
    public void pickUpObject(int i){
        if(i != 999){
            if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
                gp.obj[gp.currentMap][i].use(this);
                gp.obj[gp.currentMap][i] = null;
            }
            else {
                String text;

                if(canObtainItem(gp.obj[gp.currentMap][i])) {
                    gp.playSE(6);
                    text = "Got a " + gp.obj[gp.currentMap][i].name + "!";
                } else {
                    text = "You cannot carry any more!";
                }
                gp.ui.showMessage(text);
                gp.obj[gp.currentMap][i] = null;
            }
        }
    }
    public void interactNPC(int i) {
        if(gp.keyH.spacePressed == true){
            if(i != 999) {
                attackCanceled = true;
                gp.gameState = gp.dialogState;
                gp.npc[gp.currentMap][i].speak();
            }
        }
    }
    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false && gp.monster[gp.currentMap][i].dying == false) {
                gp.playSE(3);

                int damage = gp.monster[gp.currentMap][i].attack - defense;
                if(damage < 0){
                    damage = 0;
                }
                life -= damage;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i, int attack) {
        if(i != 999){
            if(gp.monster[gp.currentMap][i].invincible == false) {
                if(gp.monster[gp.currentMap][i].type == type_monster_oaie || gp.monster[gp.currentMap][i].type == type_monster_schelet || gp.monster[gp.currentMap][i].type == type_monster_shrek) {
                    gp.playSE(2);
                } else {
                    if (gp.monster[gp.currentMap][i].type == type_boss_Naruto) {
                        gp.playSE(7);
                    } else if (gp.monster[gp.currentMap][i].type == type_boss_Sonic) {
                        gp.playSE(12);
                    } else if (gp.monster[gp.currentMap][i].type == type_boss_Mario) {
                        gp.playSE(12);
                    }
                }
                int damage = attack - gp.monster[gp.currentMap][i].defense;
                if(damage < 0){
                    damage = 0;
                }

                gp.monster[gp.currentMap][i].life -= damage;
                gp.ui.showMessage(damage + " damage!");
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0) {
                    if(gp.monster[gp.currentMap][i].type == type_monster_oaie || gp.monster[gp.currentMap][i].type == type_monster_schelet || gp.monster[gp.currentMap][i].type == type_monster_shrek){
                        gp.playSE(4);
                    } else {
                        if (gp.monster[gp.currentMap][i].type == type_boss_Naruto) {
                            gp.playSE(8);
                            gp.portalActive = true;
                        } else if (gp.monster[gp.currentMap][i].type == type_boss_Sonic) {
                            gp.playSE(10);
                            gp.portalActive = true;
                        }   else if (gp.monster[gp.currentMap][i].type == type_boss_Mario) {
                            gp.playSE(13);
                            gp.playSE(14);
                            gp.portalActive = true;
                        }
                    }

                    gp.monster[gp.currentMap][i].dying = true;
                    kills++;
                    gp.ui.showMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
                    gp.ui.showMessage("Exp + " + gp.monster[gp.currentMap][i].exp + "!");
                    exp += gp.monster[gp.currentMap][i].exp;
                    score += gp.monster[gp.currentMap][i].score;
                    checkLevelUp();
                }
            }
        }
    }
    public void checkLevelUp() {
        if(exp >= nextLevelExp){
            exp += 2;
            if(maxLife < 10){
                maxLife += 2;
                maxMana +=1;
            }
            if(life + 2 < maxLife){
                life += 2;
            } else if(life + 1 < maxLife) {
                life += 1;
            } else {
                life = maxLife;
            }
            mana +=2;
            level++;
            nextLevelExp = nextLevelExp * 4;
            dexterity++;
            attack = getAttack();
            score += 100;
            defense = getDefense();

            gp.playSE(1);
            gp.gameState = gp.dialogState;
            gp.ui.currentDialogue = "You are level " + level + " now!\n" +
                    "You feel stronger!";
        }
    }
    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_baston_magic){
                currentWeapon = selectedItem;
                attack = getAttack();
                getPlayerImage();
                getPlayerAtackImage();
            }
            if(selectedItem.type == type_armour){
                currentArmour = selectedItem;
                defense = getDefense();
            }
            if(selectedItem.type == type_consumable) {
                selectedItem.use(this);
                if(selectedItem.amount > 1){
                    selectedItem.amount--;
                } else {
                    inventory.remove(itemIndex);
                }
            }
        }
    }
    public int searchItemInInventory(String itemName) {
        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++) {
            if(inventory.get(i).name.equals(itemName)) {
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;

        //CHECK IF STACKABLE
        if(item.stackable == true) {
            int index = searchItemInInventory(item.name);
            if(index != 999) {
                inventory.get(index).amount++;
                canObtain = true;
            } else { //New item so need to check vacancy
                if(inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else { //Not stackable so check vacancy
            if(inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction)
        {
            case "up":
                if(attacking == false) {
                    if(spriteNum == 1) { image = up1; }
                    if(spriteNum == 2) { image = up2; }
                    if(spriteNum == 3) { image = up3; }
                    if(spriteNum == 4) { image = up4; }
                    if(spriteNum == 5) { image = up5; }
                    if(spriteNum == 6) { image = up6; }
                    if(spriteNum == 7) { image = up7; }
                    if(spriteNum == 8) { image = up8; }
                    if(spriteNum == 9) { image = up9; }
                } else {

                    if(spriteNum == 1) { image = atackUp1; }
                    if(spriteNum == 2) { image = atackUp2; }
                    if(spriteNum == 3) { image = atackUp3; }
                    if(spriteNum == 4) { image = atackUp4; }
                    if(spriteNum == 5) { image = atackUp5; }
                    if(spriteNum == 6) { image = atackUp6; }
                }
                break;
            case "down":
                if(attacking == false) {
                    if(spriteNum == 1) { image = down1; }
                    if(spriteNum == 2) { image = down2; }
                    if(spriteNum == 3) { image = down3; }
                    if(spriteNum == 4) { image = down4; }
                    if(spriteNum == 5) { image = down5; }
                    if(spriteNum == 6) { image = down6; }
                    if(spriteNum == 7) { image = down7; }
                    if(spriteNum == 8) { image = down8; }
                    if(spriteNum == 9) { image = down9; }
                } else {
                    if(spriteNum == 1) { image = atackDown1; }
                    if(spriteNum == 2) { image = atackDown2; }
                    if(spriteNum == 3) { image = atackDown3; }
                    if(spriteNum == 4) { image = atackDown4; }
                    if(spriteNum == 5) { image = atackDown5; }
                    if(spriteNum == 6) { image = atackDown6; }
                }
                break;
            case "left":
                if(attacking == false) {
                    if(spriteNum == 1) { image = left1; }
                    if(spriteNum == 2) { image = left2; }
                    if(spriteNum == 3) { image = left3; }
                    if(spriteNum == 4) { image = left4; }
                    if(spriteNum == 5) { image = left5; }
                    if(spriteNum == 6) { image = left6; }
                    if(spriteNum == 7) { image = left7; }
                    if(spriteNum == 8) { image = left8; }
                    if(spriteNum == 9) { image = left9; }
                } else {
                    //se poate sterge
                    if(spriteNum == 1) { image = atackLeft1; }
                    if(spriteNum == 2) { image = atackLeft2; }
                    if(spriteNum == 3) { image = atackLeft3; }
                    if(spriteNum == 4) { image = atackLeft4; }
                    if(spriteNum == 5) { image = atackLeft5; }
                    if(spriteNum == 6) { image = atackLeft6; }
                }
                break;
            case "right":
                if(attacking == false) {
                    if(spriteNum == 1) { image = right1; }
                    if(spriteNum == 2) { image = right2; }
                    if(spriteNum == 3) { image = right3; }
                    if(spriteNum == 4) { image = right4; }
                    if(spriteNum == 5) { image = right5; }
                    if(spriteNum == 6) { image = right6; }
                    if(spriteNum == 7) { image = right7; }
                    if(spriteNum == 8) { image = right8; }
                    if(spriteNum == 9) { image = right9; }
                } else {
                    if(spriteNum == 1) { image = atackRight1; }
                    if(spriteNum == 2) { image = atackRight2; }
                    if(spriteNum == 3) { image = atackRight3; }
                    if(spriteNum == 4) { image = atackRight4; }
                    if(spriteNum == 5) { image = atackRight5; }
                    if(spriteNum == 6) { image = atackRight6; }
                }
                break;
        }

        if(invincible == true) {
            changeAlpha(g2,0.4f);
        }

        g2.drawImage(image, tempScreenX, tempScreenY, null);

        //RESET ALPHA
        changeAlpha(g2, 1f);
    }
}
