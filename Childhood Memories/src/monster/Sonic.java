package monster;
import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class Sonic extends Entity {
    GamePanel gp;
    public Sonic(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_boss_Sonic;
        name = "Sonic";
        speed = 5;
        maxLife = 5;
        score = 100;
        exp = 25;
        life = maxLife;
        attack = 3;
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
        up1 = setup("/Sonic/l0_sonic_37", gp.tileSize, gp.tileSize);
        up2 = setup("/Sonic/l0_sonic_38", gp.tileSize, gp.tileSize);
        up3 = setup("/Sonic/l0_sonic_40", gp.tileSize, gp.tileSize);
        up4 = setup("/Sonic/l0_sonic_39",gp.tileSize, gp.tileSize);
        up5 = setup("/Sonic/l0_sonic_41", gp.tileSize, gp.tileSize);
        up6 = setup("/Sonic/l0_sonic_38", gp.tileSize, gp.tileSize);
        up7 = setup("/Sonic/l0_sonic_40", gp.tileSize, gp.tileSize);
        up8 = setup("/Sonic/l0_sonic_39", gp.tileSize, gp.tileSize);
        up9 = setup("/Sonic/l0_sonic_41", gp.tileSize, gp.tileSize);

        left1 = setup("/Sonic/l0_sonic_36", gp.tileSize, gp.tileSize);
        left2 = setup("/Sonic/l0_sonic_35", gp.tileSize, gp.tileSize);
        left3 = setup("/Sonic/l0_sonic_34", gp.tileSize, gp.tileSize);
        left4 = setup("/Sonic/l0_sonic_33", gp.tileSize, gp.tileSize);
        left5 = setup("/Sonic/l0_sonic_32", gp.tileSize, gp.tileSize);
        left6 = setup("/Sonic/l0_sonic_31", gp.tileSize, gp.tileSize);
        left7 = setup("/Sonic/l0_sonic_30", gp.tileSize, gp.tileSize);
        left8 = setup("/Sonic/l0_sonic_29", gp.tileSize, gp.tileSize);
        left9 = setup("/Sonic/l0_sonic_28", gp.tileSize, gp.tileSize);

        right1 = setup("/Sonic/l0_sonic_19", gp.tileSize, gp.tileSize);
        right2 = setup("/Sonic/l0_sonic_20", gp.tileSize, gp.tileSize);
        right3 = setup("/Sonic/l0_sonic_21", gp.tileSize, gp.tileSize);
        right4 = setup("/Sonic/l0_sonic_22", gp.tileSize, gp.tileSize);
        right5 = setup("/Sonic/l0_sonic_23", gp.tileSize, gp.tileSize);
        right6 = setup("/Sonic/l0_sonic_24", gp.tileSize, gp.tileSize);
        right7 = setup("/Sonic/l0_sonic_25", gp.tileSize, gp.tileSize);
        right8 = setup("/Sonic/l0_sonic_26", gp.tileSize, gp.tileSize);
        right9 = setup("/Sonic/l0_sonic_27", gp.tileSize, gp.tileSize);

        down1 = setup("/Sonic/l0_sonic_45", gp.tileSize, gp.tileSize);
        down2 = setup("/Sonic/l0_sonic_46", gp.tileSize, gp.tileSize);
        down3 = setup("/Sonic/l0_sonic_47", gp.tileSize, gp.tileSize);
        down4 = setup("/Sonic/l0_sonic_48", gp.tileSize, gp.tileSize);
        down5 = setup("/Sonic/l0_sonic_49", gp.tileSize, gp.tileSize);
        down6 = setup("/Sonic/l0_sonic_46", gp.tileSize, gp.tileSize);
        down7 = setup("/Sonic/l0_sonic_47", gp.tileSize, gp.tileSize);
        down8 = setup("/Sonic/l0_sonic_48", gp.tileSize, gp.tileSize);
        down9 = setup("/Sonic/l0_sonic_49", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        left1 = setup("/Sonic/l0_sonic_10", gp.tileSize, gp.tileSize);
        left2 = setup("/Sonic/l0_sonic_11", gp.tileSize, gp.tileSize);
        left3 = setup("/Sonic/l0_sonic_12", gp.tileSize, gp.tileSize);
        left4 = setup("/Sonic/l0_sonic_13", gp.tileSize, gp.tileSize);
        left5 = setup("/Sonic/l0_sonic_14", gp.tileSize, gp.tileSize);
        left6 = setup("/Sonic/l0_sonic_15", gp.tileSize, gp.tileSize);
        left7 = setup("/Sonic/l0_sonic_16", gp.tileSize, gp.tileSize);
        left8 = setup("/Sonic/l0_sonic_17", gp.tileSize, gp.tileSize);
        left9 = setup("/Sonic/l0_sonic_18", gp.tileSize, gp.tileSize);

        right1 = setup("/Sonic/l0_sonic_01", gp.tileSize, gp.tileSize);
        right2 = setup("/Sonic/l0_sonic_02", gp.tileSize, gp.tileSize);
        right3 = setup("/Sonic/l0_sonic_03", gp.tileSize, gp.tileSize);
        right4 = setup("/Sonic/l0_sonic_04", gp.tileSize, gp.tileSize);
        right5 = setup("/Sonic/l0_sonic_05", gp.tileSize, gp.tileSize);
        right6 = setup("/Sonic/l0_sonic_06", gp.tileSize, gp.tileSize);
        right7 = setup("/Sonic/l0_sonic_07", gp.tileSize, gp.tileSize);
        right8 = setup("/Sonic/l0_sonic_08", gp.tileSize, gp.tileSize);
        right9 = setup("/Sonic/l0_sonic_09", gp.tileSize, gp.tileSize);
    }
    public void setAction(){
        if(onPath == true) {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15,100);
            //Searching the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //Check if it shoots a projectile
            checkAttackOrNot(30,gp.tileSize/4, gp.tileSize/4);

        } else {
            //Check if it starts chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            //Get random direction
            getRandomDirection();
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
        gp.ui.currentDialogue = "Imi accept infrangerea.\nMai ai un singur gardian de infrant,\ndar, crede-ma, nu va fi usor.";


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
