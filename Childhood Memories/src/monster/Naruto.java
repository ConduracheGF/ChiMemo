package monster;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;
import object.OBJ_Arrow;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Smoke;

import java.util.Random;

public class Naruto extends Entity {
    GamePanel gp;
    public Naruto(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_boss_Naruto;
        name = "Naruto";
        speed = 3;
        maxLife = 5;
        score = 100;
        exp = 25;
        life = maxLife;
        attack = 2;
        defense = 0;
        newProjectile = new OBJ_Smoke(gp);

        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        attackArea.width = 48;
        attackArea.height = 48;
        motion1_duration = 40;
        motion2_duration = 85;

        getImage();
        getAttackImage();
    }
    public void getImage(){
        up1 = setup("/Naruto_sprites/Naruto_walk/Naruto_13", gp.tileSize, gp.tileSize);
        up2 = setup("/Naruto_sprites/Naruto_walk/Naruto_14", gp.tileSize, gp.tileSize);
        up3 = setup("/Naruto_sprites/Naruto_walk/Naruto_15", gp.tileSize, gp.tileSize);
        up4 = setup("/Naruto_sprites/Naruto_walk/Naruto_16", gp.tileSize, gp.tileSize);
        up5 = setup("/Naruto_sprites/Naruto_walk/Naruto_13", gp.tileSize, gp.tileSize);
        up6 = setup("/Naruto_sprites/Naruto_walk/Naruto_14", gp.tileSize, gp.tileSize);
        up7 = setup("/Naruto_sprites/Naruto_walk/Naruto_15", gp.tileSize, gp.tileSize);
        up8 = setup("/Naruto_sprites/Naruto_walk/Naruto_16", gp.tileSize, gp.tileSize);
        up9 = setup("/Naruto_sprites/Naruto_walk/Naruto_13", gp.tileSize, gp.tileSize);

        left1 = setup("/Naruto_sprites/Naruto_walk/Naruto_5", gp.tileSize, gp.tileSize);
        left2 = setup("/Naruto_sprites/Naruto_walk/Naruto_6", gp.tileSize, gp.tileSize);
        left3 = setup("/Naruto_sprites/Naruto_walk/Naruto_7", gp.tileSize, gp.tileSize);
        left4 = setup("/Naruto_sprites/Naruto_walk/Naruto_8", gp.tileSize, gp.tileSize);
        left5 = setup("/Naruto_sprites/Naruto_walk/Naruto_5", gp.tileSize, gp.tileSize);
        left6 = setup("/Naruto_sprites/Naruto_walk/Naruto_6", gp.tileSize, gp.tileSize);
        left7 = setup("/Naruto_sprites/Naruto_walk/Naruto_7", gp.tileSize, gp.tileSize);
        left8 = setup("/Naruto_sprites/Naruto_walk/Naruto_8", gp.tileSize, gp.tileSize);
        left9 = setup("/Naruto_sprites/Naruto_walk/Naruto_5", gp.tileSize, gp.tileSize);

        down1 = setup("/Naruto_sprites/Naruto_walk/Naruto_1", gp.tileSize, gp.tileSize);
        down2 = setup("/Naruto_sprites/Naruto_walk/Naruto_2", gp.tileSize, gp.tileSize);
        down3 = setup("/Naruto_sprites/Naruto_walk/Naruto_3", gp.tileSize, gp.tileSize);
        down4 = setup("/Naruto_sprites/Naruto_walk/Naruto_4", gp.tileSize, gp.tileSize);
        down5 = setup("/Naruto_sprites/Naruto_walk/Naruto_1", gp.tileSize, gp.tileSize);
        down6 = setup("/Naruto_sprites/Naruto_walk/Naruto_2", gp.tileSize, gp.tileSize);
        down7 = setup("/Naruto_sprites/Naruto_walk/Naruto_3", gp.tileSize, gp.tileSize);
        down8 = setup("/Naruto_sprites/Naruto_walk/Naruto_4", gp.tileSize, gp.tileSize);
        down9 = setup("/Naruto_sprites/Naruto_walk/Naruto_1", gp.tileSize, gp.tileSize);

        right1 = setup("/Naruto_sprites/Naruto_walk/Naruto_9", gp.tileSize, gp.tileSize);
        right2 = setup("/Naruto_sprites/Naruto_walk/Naruto_10", gp.tileSize, gp.tileSize);
        right3 = setup("/Naruto_sprites/Naruto_walk/Naruto_11", gp.tileSize, gp.tileSize);
        right4 = setup("/Naruto_sprites/Naruto_walk/Naruto_12", gp.tileSize, gp.tileSize);
        right5 = setup("/Naruto_sprites/Naruto_walk/Naruto_9", gp.tileSize, gp.tileSize);
        right6 = setup("/Naruto_sprites/Naruto_walk/Naruto_10", gp.tileSize, gp.tileSize);
        right7 = setup("/Naruto_sprites/Naruto_walk/Naruto_11", gp.tileSize, gp.tileSize);
        right8 = setup("/Naruto_sprites/Naruto_walk/Naruto_12", gp.tileSize, gp.tileSize);
        right9 = setup("/Naruto_sprites/Naruto_walk/Naruto_9", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        atackUp1 = setup("/Naruto_sprites/Naruto_fight/1", gp.tileSize, gp.tileSize);
        atackUp2 = setup("/Naruto_sprites/Naruto_fight/2", gp.tileSize, gp.tileSize);
        atackUp3 = setup("/Naruto_sprites/Naruto_fight/3", gp.tileSize, gp.tileSize);
        atackUp4 = setup("/Naruto_sprites/Naruto_fight/1", gp.tileSize, gp.tileSize);
        atackUp5 = setup("/Naruto_sprites/Naruto_fight/2", gp.tileSize, gp.tileSize);
        atackUp6 = setup("/Naruto_sprites/Naruto_fight/3", gp.tileSize, gp.tileSize);
        atackUp7 = setup("/Naruto_sprites/Naruto_fight/1", gp.tileSize, gp.tileSize);
        atackUp8 = setup("/Naruto_sprites/Naruto_fight/2", gp.tileSize, gp.tileSize);
        atackUp9 = setup("/Naruto_sprites/Naruto_fight/3", gp.tileSize, gp.tileSize);

        atackLeft1 = setup("/Naruto_sprites/Naruto_fight/4", gp.tileSize, gp.tileSize);
        atackLeft2 = setup("/Naruto_sprites/Naruto_fight/5", gp.tileSize, gp.tileSize);
        atackLeft3 = setup("/Naruto_sprites/Naruto_fight/6", gp.tileSize, gp.tileSize);
        atackLeft4 = setup("/Naruto_sprites/Naruto_fight/4", gp.tileSize, gp.tileSize);
        atackLeft5 = setup("/Naruto_sprites/Naruto_fight/5", gp.tileSize, gp.tileSize);
        atackLeft6 = setup("/Naruto_sprites/Naruto_fight/6", gp.tileSize, gp.tileSize);
        atackLeft7 = setup("/Naruto_sprites/Naruto_fight/4", gp.tileSize, gp.tileSize);
        atackLeft8 = setup("/Naruto_sprites/Naruto_fight/5", gp.tileSize, gp.tileSize);
        atackLeft9 = setup("/Naruto_sprites/Naruto_fight/6", gp.tileSize, gp.tileSize);

        atackDown1  = setup("/Naruto_sprites/Naruto_fight/10", gp.tileSize, gp.tileSize);
        atackDown2 = setup("/Naruto_sprites/Naruto_fight/11", gp.tileSize, gp.tileSize);
        atackDown3 = setup("/Naruto_sprites/Naruto_fight/12", gp.tileSize, gp.tileSize);
        atackDown4 = setup("/Naruto_sprites/Naruto_fight/10", gp.tileSize, gp.tileSize);
        atackDown5 = setup("/Naruto_sprites/Naruto_fight/11", gp.tileSize, gp.tileSize);
        atackDown6 = setup("/Naruto_sprites/Naruto_fight/12", gp.tileSize, gp.tileSize);
        atackDown7 = setup("/Naruto_sprites/Naruto_fight/10", gp.tileSize, gp.tileSize);
        atackDown8 = setup("/Naruto_sprites/Naruto_fight/11", gp.tileSize, gp.tileSize);
        atackDown9 = setup("/Naruto_sprites/Naruto_fight/12", gp.tileSize, gp.tileSize);

        atackRight1 = setup("/Naruto_sprites/Naruto_fight/7", gp.tileSize, gp.tileSize);
        atackRight2 = setup("/Naruto_sprites/Naruto_fight/8", gp.tileSize, gp.tileSize);
        atackRight3 = setup("/Naruto_sprites/Naruto_fight/9", gp.tileSize, gp.tileSize);
        atackRight4 = setup("/Naruto_sprites/Naruto_fight/7", gp.tileSize, gp.tileSize);
        atackRight5 = setup("/Naruto_sprites/Naruto_fight/8", gp.tileSize, gp.tileSize);
        atackRight6 = setup("/Naruto_sprites/Naruto_fight/9", gp.tileSize, gp.tileSize);
        atackRight7 = setup("/Naruto_sprites/Naruto_fight/7", gp.tileSize, gp.tileSize);
        atackRight8 = setup("/Naruto_sprites/Naruto_fight/8", gp.tileSize, gp.tileSize);
        atackRight9 = setup("/Naruto_sprites/Naruto_fight/9", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        if(onPath == true) {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15,100);
            //Searching the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //Check if it shoots a projectile
            checkShootOrNot(30, 20);
        } else {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            //Get random direction
            getRandomDirection();
        }
        if(attacking == false){
            checkAttackOrNot(30,gp.tileSize*2,gp.tileSize*2);
        }

    }
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop() {
        //Dialog
        gp.gameState = gp.dialogState;
        gp.ui.currentDialogue = "M-ai invins!\nPentru a te putea intoarce inapoi in lumea \nta, trebuie sa infringi un adversar\nputernic si foarte rapid.\nAi grija!";

        //CAST A DIE
        int i = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if(i < 50) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 50 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }

    }
}
