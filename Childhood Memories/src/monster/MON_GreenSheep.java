package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Arrow;
import object.OBJ_Heart;

import java.util.Random;

public class MON_GreenSheep extends Entity{
    GamePanel gp;
    public MON_GreenSheep(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = type_monster_oaie;
        name = "Gren Sheep";
        speed = 1;
        maxLife = 2;
        score = 10;
        exp = 2;
        life = maxLife;
        attack = 1;
        defense = 0;
        projectile = new OBJ_Arrow(gp);

        solidArea.x = 3;
        solidArea.y = 10;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();

    }

    public void getImage(){
        up1 = setup("/Monster/green_sheep/walk/oaie1", gp.tileSize, gp.tileSize);
        up2 = setup("/Monster/green_sheep/walk/oaie2", gp.tileSize, gp.tileSize);
        up3 = setup("/Monster/green_sheep/walk/oaie3", gp.tileSize, gp.tileSize);
        up4 = setup("/Monster/green_sheep/walk/oaie4", gp.tileSize, gp.tileSize);
        up5 = setup("/Monster/green_sheep/walk/oaie5", gp.tileSize, gp.tileSize);
        up6 = setup("/Monster/green_sheep/walk/oaie6", gp.tileSize, gp.tileSize);
        up7 = setup("/Monster/green_sheep/walk/oaie7", gp.tileSize, gp.tileSize);
        up8 = setup("/Monster/green_sheep/walk/oaie8", gp.tileSize, gp.tileSize);
        up9 = setup("/Monster/green_sheep/walk/oaie9", gp.tileSize, gp.tileSize);

        left1 = setup("/Monster/green_sheep/walk/oaie10", gp.tileSize, gp.tileSize);
        left2 = setup("/Monster/green_sheep/walk/oaie11", gp.tileSize, gp.tileSize);
        left3 = setup("/Monster/green_sheep/walk/oaie12", gp.tileSize, gp.tileSize);
        left4 = setup("/Monster/green_sheep/walk/oaie13", gp.tileSize, gp.tileSize);
        left5 = setup("/Monster/green_sheep/walk/oaie14", gp.tileSize, gp.tileSize);
        left6 = setup("/Monster/green_sheep/walk/oaie15", gp.tileSize, gp.tileSize);
        left7 = setup("/Monster/green_sheep/walk/oaie16", gp.tileSize, gp.tileSize);
        left8 = setup("/Monster/green_sheep/walk/oaie17", gp.tileSize, gp.tileSize);
        left9 = setup("/Monster/green_sheep/walk/oaie18", gp.tileSize, gp.tileSize);

        down1 = setup("/Monster/green_sheep/walk/oaie19", gp.tileSize, gp.tileSize);
        down2 = setup("/Monster/green_sheep/walk/oaie20", gp.tileSize, gp.tileSize);
        down3 = setup("/Monster/green_sheep/walk/oaie21", gp.tileSize, gp.tileSize);
        down4 = setup("/Monster/green_sheep/walk/oaie22", gp.tileSize, gp.tileSize);
        down5 = setup("/Monster/green_sheep/walk/oaie23", gp.tileSize, gp.tileSize);
        down6 = setup("/Monster/green_sheep/walk/oaie24", gp.tileSize, gp.tileSize);
        down7 = setup("/Monster/green_sheep/walk/oaie25", gp.tileSize, gp.tileSize);
        down8 = setup("/Monster/green_sheep/walk/oaie26", gp.tileSize, gp.tileSize);
        down9 = setup("/Monster/green_sheep/walk/oaie27", gp.tileSize, gp.tileSize);

        right1 = setup("/Monster/green_sheep/walk/oaie28", gp.tileSize, gp.tileSize);
        right2 = setup("/Monster/green_sheep/walk/oaie29", gp.tileSize, gp.tileSize);
        right3 = setup("/Monster/green_sheep/walk/oaie30", gp.tileSize, gp.tileSize);
        right4 = setup("/Monster/green_sheep/walk/oaie31", gp.tileSize, gp.tileSize);
        right5 = setup("/Monster/green_sheep/walk/oaie32", gp.tileSize, gp.tileSize);
        right6 = setup("/Monster/green_sheep/walk/oaie33", gp.tileSize, gp.tileSize);
        right7 = setup("/Monster/green_sheep/walk/oaie34", gp.tileSize, gp.tileSize);
        right8 = setup("/Monster/green_sheep/walk/oaie35", gp.tileSize, gp.tileSize);
        right9 = setup("/Monster/green_sheep/walk/oaie36", gp.tileSize, gp.tileSize);
    }

    public void setAction(){
        if(onPath == true) {
            //Check if it stops chasing
            checkStopChasingOrNot(gp.player, 15,100);
            //Searching the direction to go
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
            //Check if it shoots a projectile
            checkShootOrNot(200, 30);
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
        dropItem(new OBJ_Heart(gp));
    }
}
