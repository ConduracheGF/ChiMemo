package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_Ghost extends Entity{
    public NPC_Ghost(GamePanel gp) {
        super(gp);

        direction = "down";
        type = type_npc;
        speed = 2;

        getImage();
        setDialogue();
    }
    public void getImage(){
        up1 = setup("/MarioGhost/l0_22", gp.tileSize, gp.tileSize);
        up2 = setup("/MarioGhost/l0_23", gp.tileSize, gp.tileSize);
        up3 = setup("/MarioGhost/l0_24", gp.tileSize, gp.tileSize);
        up4 = setup("/MarioGhost/l0_25", gp.tileSize, gp.tileSize);
        up5 = setup("/MarioGhost/l0_26", gp.tileSize, gp.tileSize);
        up6 = setup("/MarioGhost/l0_27", gp.tileSize, gp.tileSize);
        up7 = setup("/MarioGhost/l0_28", gp.tileSize, gp.tileSize);
        up8 = setup("/MarioGhost/l0_22", gp.tileSize, gp.tileSize);
        up9 = setup("/MarioGhost/l0_23", gp.tileSize, gp.tileSize);

        left1 = setup("/MarioGhost/l0_15", gp.tileSize, gp.tileSize);
        left2 = setup("/MarioGhost/l0_16", gp.tileSize, gp.tileSize);
        left3 = setup("/MarioGhost/l0_17", gp.tileSize, gp.tileSize);
        left4 = setup("/MarioGhost/l0_18", gp.tileSize, gp.tileSize);
        left5 = setup("/MarioGhost/l0_19", gp.tileSize, gp.tileSize);
        left6 = setup("/MarioGhost/l0_20", gp.tileSize, gp.tileSize);
        left7 = setup("/MarioGhost/l0_21", gp.tileSize, gp.tileSize);
        left8 = setup("/MarioGhost/l0_15", gp.tileSize, gp.tileSize);
        left9 = setup("/MarioGhost/l0_16", gp.tileSize, gp.tileSize);

        down1 = setup("/MarioGhost/l0_01", gp.tileSize, gp.tileSize);
        down2 = setup("/MarioGhost/l0_02", gp.tileSize, gp.tileSize);
        down3 = setup("/MarioGhost/l0_03", gp.tileSize, gp.tileSize);
        down4 = setup("/MarioGhost/l0_04", gp.tileSize, gp.tileSize);
        down5 = setup("/MarioGhost/l0_05", gp.tileSize, gp.tileSize);
        down6 = setup("/MarioGhost/l0_06", gp.tileSize, gp.tileSize);
        down7 = setup("/MarioGhost/l0_07", gp.tileSize, gp.tileSize);
        down8 = setup("/MarioGhost/l0_01", gp.tileSize, gp.tileSize);
        down9 = setup("/MarioGhost/l0_02", gp.tileSize, gp.tileSize);

        right1 = setup("/MarioGhost/l0_08", gp.tileSize, gp.tileSize);
        right2 = setup("/MarioGhost/l0_09", gp.tileSize, gp.tileSize);
        right3 = setup("/MarioGhost/l0_10", gp.tileSize, gp.tileSize);
        right4 = setup("/MarioGhost/l0_11", gp.tileSize, gp.tileSize);
        right5 = setup("/MarioGhost/l0_12", gp.tileSize, gp.tileSize);
        right6 = setup("/MarioGhost/l0_13", gp.tileSize, gp.tileSize);
        right7 = setup("/MarioGhost/l0_14", gp.tileSize, gp.tileSize);
        right8 = setup("/MarioGhost/l0_08", gp.tileSize, gp.tileSize);
        right9 = setup("/MarioGhost/l0_09", gp.tileSize, gp.tileSize);

    }
    public void setDialogue() {
        dialogues[0] = "Buna, Mihai! Bine ai venit in lumea jocurilor!";
        dialogues[1] = "Daca vrei sa evadezi de aici trebuie sa\n treci de toate armatele gardienilor!";
        dialogues[2] = "Sa invingi toti gardienii!\nSa-l invingi pe Anonymus si sa\n supravietuiesti pana la final, altfel vei \n ramane captiv pe viata";
        dialogues[3] = "Succes! Vei avea nevoie.";

    }
    public void setAction(){

        if(onPath == true){
            int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++;

            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0;
            }
        }
    }
    public void speak() {
        super.speak();

        onPath = false;
    }
}
