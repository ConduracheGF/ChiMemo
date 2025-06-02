package monster;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class SuperMario extends Entity {
    GamePanel gp;
    public SuperMario(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_boss_Mario;
        name = "Mario";
        speed = 3;
        maxLife = 5;
        score = 100;
        exp = 25;
        life = maxLife;
        attack = 5;
        defense = 1;

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
        up1 = setup("/Mario/l0_22", gp.tileSize, gp.tileSize);
        up2 = setup("/Mario/l0_23", gp.tileSize, gp.tileSize);
        up3 = setup("/Mario/l0_24", gp.tileSize, gp.tileSize);
        up4 = setup("/Mario/l0_25",gp.tileSize, gp.tileSize);
        up5 = setup("/Mario/l0_26", gp.tileSize, gp.tileSize);
        up6 = setup("/Mario/l0_27", gp.tileSize, gp.tileSize);
        up7 = setup("/Mario/l0_28", gp.tileSize, gp.tileSize);
        up8 = setup("/Mario/l0_23", gp.tileSize, gp.tileSize);
        up9 = setup("/Mario/l0_24", gp.tileSize, gp.tileSize);

        left1 = setup("/Mario/l0_15", gp.tileSize, gp.tileSize);
        left2 = setup("/Mario/l0_16", gp.tileSize, gp.tileSize);
        left3 = setup("/Mario/l0_17", gp.tileSize, gp.tileSize);
        left4 = setup("/Mario/l0_18", gp.tileSize, gp.tileSize);
        left5 = setup("/Mario/l0_19", gp.tileSize, gp.tileSize);
        left6 = setup("/Mario/l0_20", gp.tileSize, gp.tileSize);
        left7 = setup("/Mario/l0_21", gp.tileSize, gp.tileSize);
        left8 = setup("/Mario/l0_16", gp.tileSize, gp.tileSize);
        left9 = setup("/Mario/l0_17", gp.tileSize, gp.tileSize);

        right1 = setup("/Mario/l0_08", gp.tileSize, gp.tileSize);
        right2 = setup("/Mario/l0_09", gp.tileSize, gp.tileSize);
        right3 = setup("/Mario/l0_10", gp.tileSize, gp.tileSize);
        right4 = setup("/Mario/l0_11", gp.tileSize, gp.tileSize);
        right5 = setup("/Mario/l0_12", gp.tileSize, gp.tileSize);
        right6 = setup("/Mario/l0_13", gp.tileSize, gp.tileSize);
        right7 = setup("/Mario/l0_14", gp.tileSize, gp.tileSize);
        right8 = setup("/Mario/l0_08", gp.tileSize, gp.tileSize);
        right9 = setup("/Mario/l0_09", gp.tileSize, gp.tileSize);

        down1 = setup("/Mario/l0_01", gp.tileSize, gp.tileSize);
        down2 = setup("/Mario/l0_02", gp.tileSize, gp.tileSize);
        down3 = setup("/Mario/l0_03", gp.tileSize, gp.tileSize);
        down4 = setup("/Mario/l0_04", gp.tileSize, gp.tileSize);
        down5 = setup("/Mario/l0_05", gp.tileSize, gp.tileSize);
        down6 = setup("/Mario/l0_06", gp.tileSize, gp.tileSize);
        down7 = setup("/Mario/l0_07", gp.tileSize, gp.tileSize);
        down8 = setup("/Mario/l0_02", gp.tileSize, gp.tileSize);
        down9 = setup("/Mario/l0_03", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        atackUp1 = setup("/Mario/l0_atac_19", gp.tileSize, gp.tileSize);
        atackUp2 = setup("/Mario/l0_atac_20", gp.tileSize, gp.tileSize);
        atackUp3 = setup("/Mario/l0_atac_21", gp.tileSize, gp.tileSize);
        atackUp4 = setup("/Mario/l0_atac_22",gp.tileSize, gp.tileSize);
        atackUp5 = setup("/Mario/l0_atac_23", gp.tileSize, gp.tileSize);
        atackUp6 = setup("/Mario/l0_atac_24", gp.tileSize, gp.tileSize);
        atackUp7 = setup("/Mario/l0_atac_19", gp.tileSize, gp.tileSize);
        atackUp8 = setup("/Mario/l0_atac_20", gp.tileSize, gp.tileSize);
        atackUp9 = setup("/Mario/l0_atac_21", gp.tileSize, gp.tileSize);

        atackLeft1 = setup("/Mario/l0_atac_18", gp.tileSize, gp.tileSize);
        atackLeft2 = setup("/Mario/l0_atac_17", gp.tileSize, gp.tileSize);
        atackLeft3 = setup("/Mario/l0_atac_16", gp.tileSize, gp.tileSize);
        atackLeft4 = setup("/Mario/l0_atac_15", gp.tileSize, gp.tileSize);
        atackLeft5 = setup("/Mario/l0_atac_14", gp.tileSize, gp.tileSize);
        atackLeft6 = setup("/Mario/l0_atac_13", gp.tileSize, gp.tileSize);
        atackLeft7 = setup("/Mario/l0_atac_18", gp.tileSize, gp.tileSize);
        atackLeft8 = setup("/Mario/l0_atac_17", gp.tileSize, gp.tileSize);
        atackLeft9 = setup("/Mario/l0_atac_16", gp.tileSize, gp.tileSize);

        atackRight1 = setup("/Mario/l0_atac_07", gp.tileSize, gp.tileSize);
        atackRight2 = setup("/Mario/l0_atac_08", gp.tileSize, gp.tileSize);
        atackRight3 = setup("/Mario/l0_atac_09", gp.tileSize, gp.tileSize);
        atackRight4 = setup("/Mario/l0_atac_10", gp.tileSize, gp.tileSize);
        atackRight5 = setup("/Mario/l0_atac_11", gp.tileSize, gp.tileSize);
        atackRight6 = setup("/Mario/l0_atac_12", gp.tileSize, gp.tileSize);
        atackRight7 = setup("/Mario/l0_atac_07", gp.tileSize, gp.tileSize);
        atackRight8 = setup("/Mario/l0_atac_08", gp.tileSize, gp.tileSize);
        atackRight9 = setup("/Mario/l0_atac_09", gp.tileSize, gp.tileSize);

        atackDown1 = setup("/Mario/l0_atac_01", gp.tileSize, gp.tileSize);
        atackDown2 = setup("/Mario/l0_atac_02", gp.tileSize, gp.tileSize);
        atackDown3 = setup("/Mario/l0_atac_03", gp.tileSize, gp.tileSize);
        atackDown4 = setup("/Mario/l0_atac_04", gp.tileSize, gp.tileSize);
        atackDown5 = setup("/Mario/l0_atac_05", gp.tileSize, gp.tileSize);
        atackDown6 = setup("/Mario/l0_atac_06", gp.tileSize, gp.tileSize);
        atackDown7 = setup("/Mario/l0_atac_01", gp.tileSize, gp.tileSize);
        atackDown8 = setup("/Mario/l0_atac_02", gp.tileSize, gp.tileSize);
        atackDown9 = setup("/Mario/l0_atac_03", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        if(onPath == true) {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15,100);
            //Searching the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

        } else {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            //Get random direction
            getRandomDirection();
        }
        if(attacking == false){
            checkAttackOrNot(30,gp.tileSize*4,gp.tileSize*3);
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
        gp.ui.currentDialogue = "Felicitari!\nAi reusit sa invingi cel mai puternic adversar\n al universului jocurilor.\n Poti sa te intorci inapoi la lumea ta,\n intamplarile de aici fiindu-ti sterse...";


        //CAST A DIE
        int i = new Random().nextInt(100) + 1;

        //SET THE MONSTER DROP
        if(i < 50) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 50 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp));
        }
        gp.gameState = gp.endGameState;
    }
}

