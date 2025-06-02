package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Schelet extends Entity {
    GamePanel gp;
    public MON_Schelet(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster_schelet;
        name = "Schelet";
        speed = 2;
        maxLife = 4;
        score = 30;
        exp = 10;
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
        up1 = setup("/Monster/schelet/walk/l0_walk_01", gp.tileSize, gp.tileSize);
        up2 = setup("/Monster/schelet/walk/l0_walk_02", gp.tileSize, gp.tileSize);
        up3 = setup("/Monster/schelet/walk/l0_walk_03", gp.tileSize, gp.tileSize);
        up4 = setup("/Monster/schelet/walk/l0_walk_04", gp.tileSize, gp.tileSize);
        up5 = setup("/Monster/schelet/walk/l0_walk_05", gp.tileSize, gp.tileSize);
        up6 = setup("/Monster/schelet/walk/l0_walk_06", gp.tileSize, gp.tileSize);
        up7 = setup("/Monster/schelet/walk/l0_walk_07", gp.tileSize, gp.tileSize);
        up8 = setup("/Monster/schelet/walk/l0_walk_08", gp.tileSize, gp.tileSize);
        up9 = setup("/Monster/schelet/walk/l0_walk_09", gp.tileSize, gp.tileSize);

        left1 = setup("/Monster/schelet/walk/l0_walk_10", gp.tileSize, gp.tileSize);
        left2 = setup("/Monster/schelet/walk/l0_walk_11", gp.tileSize, gp.tileSize);
        left3 = setup("/Monster/schelet/walk/l0_walk_12", gp.tileSize, gp.tileSize);
        left4 = setup("/Monster/schelet/walk/l0_walk_13", gp.tileSize, gp.tileSize);
        left5 = setup("/Monster/schelet/walk/l0_walk_14", gp.tileSize, gp.tileSize);
        left6 = setup("/Monster/schelet/walk/l0_walk_15", gp.tileSize, gp.tileSize);
        left7 = setup("/Monster/schelet/walk/l0_walk_16", gp.tileSize, gp.tileSize);
        left8 = setup("/Monster/schelet/walk/l0_walk_17", gp.tileSize, gp.tileSize);
        left9 = setup("/Monster/schelet/walk/l0_walk_18", gp.tileSize, gp.tileSize);

        down1 = setup("/Monster/schelet/walk/l0_walk_19", gp.tileSize, gp.tileSize);
        down2 = setup("/Monster/schelet/walk/l0_walk_20", gp.tileSize, gp.tileSize);
        down3 = setup("/Monster/schelet/walk/l0_walk_21", gp.tileSize, gp.tileSize);
        down4 = setup("/Monster/schelet/walk/l0_walk_22", gp.tileSize, gp.tileSize);
        down5 = setup("/Monster/schelet/walk/l0_walk_23", gp.tileSize, gp.tileSize);
        down6 = setup("/Monster/schelet/walk/l0_walk_24", gp.tileSize, gp.tileSize);
        down7 = setup("/Monster/schelet/walk/l0_walk_25", gp.tileSize, gp.tileSize);
        down8 = setup("/Monster/schelet/walk/l0_walk_26", gp.tileSize, gp.tileSize);
        down9 = setup("/Monster/schelet/walk/l0_walk_27", gp.tileSize, gp.tileSize);

        right1 = setup("/Monster/schelet/walk/l0_walk_28", gp.tileSize, gp.tileSize);
        right2 = setup("/Monster/schelet/walk/l0_walk_29", gp.tileSize, gp.tileSize);
        right3 = setup("/Monster/schelet/walk/l0_walk_30", gp.tileSize, gp.tileSize);
        right4 = setup("/Monster/schelet/walk/l0_walk_31", gp.tileSize, gp.tileSize);
        right5 = setup("/Monster/schelet/walk/l0_walk_32", gp.tileSize, gp.tileSize);
        right6 = setup("/Monster/schelet/walk/l0_walk_33", gp.tileSize, gp.tileSize);
        right7 = setup("/Monster/schelet/walk/l0_walk_34", gp.tileSize, gp.tileSize);
        right8 = setup("/Monster/schelet/walk/l0_walk_35", gp.tileSize, gp.tileSize);
        right9 = setup("/Monster/schelet/walk/l0_walk_36", gp.tileSize, gp.tileSize);
    }
    public void getAttackImage(){
        atackUp1 = setup("/Monster/schelet/slash/l0_slash_01", gp.tileSize, gp.tileSize);
        atackUp2 = setup("/Monster/schelet/slash/l0_slash_02", gp.tileSize, gp.tileSize);
        atackUp3 = setup("/Monster/schelet/slash/l0_slash_03", gp.tileSize, gp.tileSize);
        atackUp4 = setup("/Monster/schelet/slash/l0_slash_04", gp.tileSize, gp.tileSize);
        atackUp5 = setup("/Monster/schelet/slash/l0_slash_05", gp.tileSize, gp.tileSize);
        atackUp6 = setup("/Monster/schelet/slash/l0_slash_06", gp.tileSize, gp.tileSize);
        atackUp7 = setup("/Monster/schelet/slash/l0_slash_01", gp.tileSize, gp.tileSize);
        atackUp8 = setup("/Monster/schelet/slash/l0_slash_02", gp.tileSize, gp.tileSize);
        atackUp9 = setup("/Monster/schelet/slash/l0_slash_03", gp.tileSize, gp.tileSize);

        atackLeft1 = setup("/Monster/schelet/slash/l0_slash_07", gp.tileSize, gp.tileSize);
        atackLeft2 = setup("/Monster/schelet/slash/l0_slash_08", gp.tileSize, gp.tileSize);
        atackLeft3 = setup("/Monster/schelet/slash/l0_slash_09", gp.tileSize, gp.tileSize);
        atackLeft4 = setup("/Monster/schelet/slash/l0_slash_10", gp.tileSize, gp.tileSize);
        atackLeft5 = setup("/Monster/schelet/slash/l0_slash_11", gp.tileSize, gp.tileSize);
        atackLeft6 = setup("/Monster/schelet/slash/l0_slash_12", gp.tileSize, gp.tileSize);
        atackLeft7 = setup("/Monster/schelet/slash/l0_slash_07", gp.tileSize, gp.tileSize);
        atackLeft8 = setup("/Monster/schelet/slash/l0_slash_08", gp.tileSize, gp.tileSize);
        atackLeft9 = setup("/Monster/schelet/slash/l0_slash_09", gp.tileSize, gp.tileSize);

        atackDown1 = setup("/Monster/schelet/slash/l0_slash_13", gp.tileSize, gp.tileSize);
        atackDown2 = setup("/Monster/schelet/slash/l0_slash_14", gp.tileSize, gp.tileSize);
        atackDown3 = setup("/Monster/schelet/slash/l0_slash_15", gp.tileSize, gp.tileSize);
        atackDown4 = setup("/Monster/schelet/slash/l0_slash_16", gp.tileSize, gp.tileSize);
        atackDown5 = setup("/Monster/schelet/slash/l0_slash_17", gp.tileSize, gp.tileSize);
        atackDown6 = setup("/Monster/schelet/slash/l0_slash_18", gp.tileSize, gp.tileSize);
        atackDown7 = setup("/Monster/schelet/slash/l0_slash_13", gp.tileSize, gp.tileSize);
        atackDown8 = setup("/Monster/schelet/slash/l0_slash_14", gp.tileSize, gp.tileSize);
        atackDown9 = setup("/Monster/schelet/slash/l0_slash_15", gp.tileSize, gp.tileSize);

        atackRight1 = setup("/Monster/schelet/slash/l0_slash_19", gp.tileSize, gp.tileSize);
        atackRight2 = setup("/Monster/schelet/slash/l0_slash_20", gp.tileSize, gp.tileSize);
        atackRight3 = setup("/Monster/schelet/slash/l0_slash_21", gp.tileSize, gp.tileSize);
        atackRight4 = setup("/Monster/schelet/slash/l0_slash_22", gp.tileSize, gp.tileSize);
        atackRight5 = setup("/Monster/schelet/slash/l0_slash_23", gp.tileSize, gp.tileSize);
        atackRight6 = setup("/Monster/schelet/slash/l0_slash_24", gp.tileSize, gp.tileSize);
        atackRight7 = setup("/Monster/schelet/slash/l0_slash_19", gp.tileSize, gp.tileSize);
        atackRight8 = setup("/Monster/schelet/slash/l0_slash_20", gp.tileSize, gp.tileSize);
        atackRight9 = setup("/Monster/schelet/slash/l0_slash_21", gp.tileSize, gp.tileSize);
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
            checkAttackOrNot(30,gp.tileSize*2,gp.tileSize);
        }

    }
    public void damageReaction(){
        actionLockCounter = 0;
        //direction = gp.player.direction;
        onPath = true;
    }
    public void checkDrop() {
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
