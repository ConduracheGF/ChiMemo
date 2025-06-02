package entity;

import main.GamePanel;
import main.UtilityTool;
import monster.Naruto;
import monster.Sonic;
import monster.SuperMario;
import object.OBJ_Smoke;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static main.AssetSetter.creat;

public class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, up3, up4, up5, up6, up7, up8, up9,up10, up11, up12, up13, down1, down2, down3, down4, down5, down6, down7, down8, down9, down10, down11, down12, down13, left1, left2, left3, left4, left5, left6, left7, left8, left9, left10, left11, left12, left13, right1, right2, right3, right4, right5, right6, right7, right8, right9, right10, right11, right12, right13;
    public BufferedImage atackUp1, atackUp2, atackUp3, atackUp4, atackUp5, atackUp6, atackUp7, atackUp8, atackUp9,
            atackLeft1, atackLeft2, atackLeft3, atackLeft4, atackLeft5, atackLeft6, atackLeft7, atackLeft8, atackLeft9,
            atackDown1, atackDown2, atackDown3, atackDown4, atackDown5, atackDown6, atackDown7, atackDown8, atackDown9,
            atackRight1, atackRight2, atackRight3, atackRight4, atackRight5, atackRight6, atackRight7, atackRight8, atackRight9;
    public BufferedImage image, image2, image3;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    String[] dialogues = new String[20];

    //STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean attacking = false;
    public boolean isAttacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean portalActive = false;
    public boolean isBoss = false;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    //CHARACTER ATRIBUTES
    public String name;
    public int maxLife;
    public int life;
    public int speed;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int score;
    public int kills;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentArmour;
    public Projectile projectile;
    public Projectile newProjectile;
    //ITEM ATTRIBUTES
    public int value;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public boolean stackable = false;
    public int amount = 1;
    //TYPE
    public int type;// 0 = player, 1 = npc, 2 = monster, 8 = boss
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_sword = 3;
    public static final int type_baston_magic = 4;
    public final int type_armour = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_boss_Naruto = 8;
    public final int type_boss_Sonic = 9;
    public final int type_boss_Mario = 10;
    public final int type_monster_oaie = 11;
    public final int type_monster_schelet = 12;
    public final int  type_monster_shrek = 13;

    //improvizare
    BufferedImage[] dyingImages;
    int dyingFrame = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    //Functii ajutatoare pentru urmarirea sau nu a jucatorului
    public int getXdistance(Entity target) {
        int xDistance = Math.abs(worldX - target.worldX);
        return xDistance;
    }
    public int getYdistance(Entity target) {
        int yDistance = Math.abs(worldY - target.worldY);
        return yDistance;
    }
    public int getTileDistance(Entity target) {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target) {
        int goalCol = (target.worldX + target.solidArea.x)/gp.tileSize;
        return goalCol;
    }
    public int getGoalRow(Entity target) {
        int goalRow = (target.worldY + target.solidArea.y)/gp.tileSize;
        return goalRow;
    }

    //functie de reset, atunci cand personajul moare
    public void resetCounter() {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
    }
    public void setAction(){}
    public void damageReaction(){}
    public void speak() {
        if(dialogues[dialogueIndex] == null){
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void use(Entity entity) {}
    public void checkDrop(){}
    public void dropItem(Entity droppedItem) {
        for(int i = 0; i < gp.obj[gp.currentMap].length; i++) {
            if(gp.obj[gp.currentMap][i] == null) {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;
                gp.obj[gp.currentMap][i].worldY = worldY;
                break;
            }
        }
    }
    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if((this.type == type_monster_oaie || this.type == type_monster_schelet || this.type == type_monster_shrek|| this.type == type_boss_Mario || this.type == type_boss_Sonic || this.type == type_boss_Naruto) && contactPlayer == true){
            damagePlayer(attack);
        }
    }
    public void update(){
        if(attacking == true) {
            attacking();
        } else {
            setAction();
            checkCollision();
            if (collisionOn == false){
                switch (direction){
                    case "up":
                        worldY  -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

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
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30) {
            shotAvailableCounter++;
        }
    }

    public void checkAttackOrNot(int rate, int straight, int horizontal) {
        boolean targetInRange = false;
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);
        switch (direction) {
            case "up":
                if(gp.player.worldY < worldY && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "down":
                if(gp.player.worldY > worldY && yDis < straight && xDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "left":
                if(gp.player.worldX < worldX && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
            case "right":
                if(gp.player.worldX > worldX && xDis < straight && yDis < horizontal){
                    targetInRange = true;
                }
                break;
        }
        if(targetInRange == true) {
            //Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0) {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkShootOrNot(int rate, int shotInterval) {
        int i = new Random().nextInt(rate);
        if (type == type_boss_Naruto) {
            if (i == 0 && shotAvailableCounter >= shotInterval) {
                Projectile p = new OBJ_Smoke(gp);
                p.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(p);
                shotAvailableCounter = 0;
            }
        } else if(type == type_monster_oaie) {
            if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
                projectile.set(worldX, worldY, direction, true, this);
                gp.projectileList.add(projectile);
                shotAvailableCounter = 0;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) > distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = false;
            }
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate) {
        if(getTileDistance(target) < distance){
            int i = new Random().nextInt(rate);
            if(i == 0){
                onPath = true;
            }
        }
    }
    public void getRandomDirection() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1; //pick up a number from 1 to 100
            if (i <= 25) { direction = "up"; }
            if (i > 25 && i <= 50) { direction = "down"; }
            if (i > 50 && i <= 75) { direction = "left"; }
            if (i > 75 && i <= 100) { direction = "right"; }
            actionLockCounter = 0;
        }
    }
    public void attacking() {
        spriteCounter++;
        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 10) {
            spriteNum = 2;
        }
        if(spriteCounter >10 && spriteCounter <=15) {
            spriteNum = 3;
        }
        if(spriteCounter > 15 && spriteCounter <= 20) {
            spriteNum = 4;
        }
        if(spriteCounter > 20 && spriteCounter <= 25) {
            spriteNum = 5;
        }
        if(spriteCounter > 25 && spriteCounter <= 30)
        {
            spriteNum = 6;

            //SAVE THE CURRENT worldX worldY solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //ADJUST PLAYER'S worldX worldY for the attacking area
            switch (direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.width; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(this.type == type_monster_oaie || this.type == type_monster_schelet || this.type == type_monster_shrek|| this.type == type_boss_Mario || this.type == type_boss_Sonic || this.type == type_boss_Naruto) {
                if(gp.cChecker.checkPlayer(this) == true) {
                    damagePlayer(attack);
                }
            } else {
                //Check monster collision with the worldX worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                gp.player.damageMonster(monsterIndex, attack);
            }

            //After checking collisions, results the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > 30){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack) {
        if(gp.player.invincible == false){
            //we can give damage
            gp.playSE(2);
            int damage = attack - gp.player.defense;
            if(damage < 0){
                damage = 0;
            }
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            if (dying == true) {

                dyingAnimation(g2, screenX, screenY); // transmiti coordonatele
            } else {
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
                            if(spriteNum == 7) { image = atackUp7; }
                            if(spriteNum == 8) { image = atackUp8; }
                            if(spriteNum == 9) { image = atackUp9; }
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
                            if(spriteNum == 7) { image = atackDown7; }
                            if(spriteNum == 8) { image = atackDown8; }
                            if(spriteNum == 9) { image = atackDown9; }
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
                            if(spriteNum == 1) { image = atackLeft1; }
                            if(spriteNum == 2) { image = atackLeft2; }
                            if(spriteNum == 3) { image = atackLeft3; }
                            if(spriteNum == 4) { image = atackLeft4; }
                            if(spriteNum == 5) { image = atackLeft5; }
                            if(spriteNum == 6) { image = atackLeft6; }
                            if(spriteNum == 7) { image = atackLeft7; }
                            if(spriteNum == 8) { image = atackLeft8; }
                            if(spriteNum == 9) { image = atackLeft9; }
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
                            if(spriteNum == 7) { image = atackRight7; }
                            if(spriteNum == 8) { image = atackRight8; }
                            if(spriteNum == 9) { image = atackRight9; }
                        }
                        break;
                }
                //Monster HP bar
                if((this.type == type_monster_oaie || this.type == type_monster_schelet || this.type == type_monster_shrek|| this.type == type_boss_Mario || this.type == type_boss_Sonic || this.type == type_boss_Naruto) && hpBarOn == true) {
                    double oneScale = (double) gp.tileSize/maxLife;
                    double hpBarValue = oneScale * life;

                    g2.setColor(new Color(35,35,35));
                    g2.fillRect(screenX - 1, screenY - 16, gp.tileSize, 12);

                    g2.setColor(new Color(255, 0, 30));
                    g2.fillRect(screenX, screenY - 15, (int) hpBarValue-2, 10);

                    hpBarCounter++;
                    if(hpBarCounter > 300) {
                        hpBarCounter = 0;
                        hpBarOn = false;
                    }
                }

                if (invincible == true) {
                    hpBarOn = true;
                    hpBarCounter = 0;
                    changeAlpha(g2,0.4F);
                }

                if (image != null) {
                    g2.drawImage(image, tempScreenX, tempScreenY,null);
                }
                changeAlpha(g2,1F);
            }
        }
    }
    public void dyingAnimation(Graphics2D g2, int screenX, int screenY) {
        int mapNum = 0;
        if (dyingImages == null) {
            loadDyingImages(gp.currentMap);
        }

        if (gp.gameState == gp.playState) {
            dyingCounter++;

            int frameSpeed = 10; // viteza animatiei

            if (dyingCounter % frameSpeed == 0) {
                dyingFrame++;
            }

            if (dyingFrame >= dyingImages.length) {
                alive = false;
                dyingFrame = dyingImages.length - 1;
            }
            if(mapNum == 0) {
                if (gp.player.kills == creat[mapNum] && type == type_monster_oaie && isBoss == false) {
                    gp.monster[mapNum][creat[mapNum]] = new Naruto(gp);
                    gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize * 34;
                    gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize * 5;
                    creat[mapNum]++;
                    isBoss = true;
                }
            }
            mapNum++;
            if(mapNum == 1) {
                if (gp.player.kills == (creat[mapNum]+creat[0]) && type == type_monster_schelet && isBoss == false) {
                    gp.monster[mapNum][creat[mapNum]] = new Sonic(gp);
                    gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize * 9;
                    gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize * 27;
                    creat[mapNum]++;
                    isBoss=true;
                }
            }
            mapNum++;
            if(mapNum == 2) {
                if (gp.player.kills == (creat[mapNum]+creat[1]+creat[0]) && type == type_monster_shrek && isBoss==false) {
                    gp.monster[mapNum][creat[mapNum]] = new SuperMario(gp);
                    gp.monster[mapNum][creat[mapNum]].worldX = gp.tileSize * 34;
                    gp.monster[mapNum][creat[mapNum]].worldY = gp.tileSize * 23;
                    creat[mapNum]++;
                    isBoss=true;
                }
            }

        }

        changeImage(g2, screenX, screenY);
    }
    public void loadDyingImages(int mapNum) {
        dyingImages = new BufferedImage[9]; // sau cÃ¢te imagini ai
        if(gp.currentMap == 0) {
            if(type == type_monster_oaie) {
                dyingImages[0] = setup("/Monster/green_sheep/hurt/l0_dead_1", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Monster/green_sheep/hurt/l0_dead_2", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Monster/green_sheep/hurt/l0_dead_3", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Monster/green_sheep/hurt/l0_dead_4", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Monster/green_sheep/hurt/l0_dead_5", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Monster/green_sheep/hurt/l0_dead_6", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Monster/green_sheep/hurt/l0_dead_6", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Monster/green_sheep/hurt/l0_dead_6", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Monster/green_sheep/hurt/l0_dead_6", gp.tileSize, gp.tileSize);
            } else if(type == type_boss_Naruto) {
                dyingImages[0] = setup("/Naruto_sprites/Naruto_dead/1", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Naruto_sprites/Naruto_dead/2", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Naruto_sprites/Naruto_dead/3", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Naruto_sprites/Naruto_dead/4", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Naruto_sprites/Naruto_dead/5", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Naruto_sprites/Naruto_dead/5", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Naruto_sprites/Naruto_dead/5", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Naruto_sprites/Naruto_dead/5", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Naruto_sprites/Naruto_dead/5", gp.tileSize, gp.tileSize);
                isBoss=false;
            }
        }
        else if(gp.currentMap == 1){
            if(type == type_monster_schelet) {
                dyingImages[0] = setup("/Monster/schelet/hurt/l0_hurt_1", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Monster/schelet/hurt/l0_hurt_2", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Monster/schelet/hurt/l0_hurt_3", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Monster/schelet/hurt/l0_hurt_4", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Monster/schelet/hurt/l0_hurt_5", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Monster/schelet/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Monster/schelet/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Monster/schelet/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Monster/schelet/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
            } else if(type == type_boss_Sonic) {
                dyingImages[0] = setup("/Sonic/l0_sonic_50", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Sonic/l0_sonic_51", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Sonic/l0_sonic_52", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Sonic/l0_sonic_53", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Sonic/l0_sonic_54", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Sonic/l0_sonic_55", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Sonic/l0_sonic_56", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Sonic/l0_sonic_57", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Sonic/l0_sonic_58", gp.tileSize, gp.tileSize);
                isBoss=false;
            }
        } else {
            if(type == type_monster_shrek)
            {
                dyingImages[0] = setup("/Monster/shrek_yellow/hurt/l0_hurt_1", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Monster/shrek_yellow/hurt/l0_hurt_2", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Monster/shrek_yellow/hurt/l0_hurt_3", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Monster/shrek_yellow/hurt/l0_hurt_4", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Monster/shrek_yellow/hurt/l0_hurt_5", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Monster/shrek_yellow/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Monster/shrek_yellow/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Monster/shrek_yellow/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Monster/shrek_yellow/hurt/l0_hurt_6", gp.tileSize, gp.tileSize);
            }
            else if(type == type_boss_Mario) {
                dyingImages[0] = setup("/Mario/l0_35", gp.tileSize, gp.tileSize);
                dyingImages[1] = setup("/Mario/l0_36", gp.tileSize, gp.tileSize);
                dyingImages[2] = setup("/Mario/l0_37", gp.tileSize, gp.tileSize);
                dyingImages[3] = setup("/Mario/l0_38", gp.tileSize, gp.tileSize);
                dyingImages[4] = setup("/Mario/l0_39", gp.tileSize, gp.tileSize);
                dyingImages[5] = setup("/Mario/l0_40", gp.tileSize, gp.tileSize);
                dyingImages[6] = setup("/Mario/l0_40", gp.tileSize, gp.tileSize);
                dyingImages[7] = setup("/Mario/l0_40", gp.tileSize, gp.tileSize);
                dyingImages[8] = setup("/Mario/l0_40", gp.tileSize, gp.tileSize);
                isBoss=false;
            }
        }
    }
    public void changeImage(Graphics2D g2, int screenX, int screenY) {
        if (dyingImages != null && dyingFrame < dyingImages.length) {
            g2.drawImage(dyingImages[dyingFrame], screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, width, height);
        } catch (IOException e){
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow) {
        int startCol = (worldX + solidArea.x)/gp.tileSize;
        int startRow = (worldY + solidArea.y)/gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search() == true) {
            //Next worldX & worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //SolidArea position - Entity
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                //left or right
                if(enLeftX > nextX) {
                    direction = "left";
                }
                if(enLeftX < nextX) {
                    direction = "right";
                }
            } else if(enTopY > nextY && enLeftX > nextX) {
                //up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            } else if(enTopY > nextY && enLeftX < nextX) {
                //up or right
                direction = "up";
                if(collisionOn == true) {
                    direction = "right";
                }
            } else if(enTopY < nextY && enLeftX > nextX) {
                //down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "left";
                }
            } else if(enTopY < nextY && enLeftX < nextX) {
                //down or right
                direction = "down";
                checkCollision();
                if(collisionOn == true) {
                    direction = "right";
                }
            }

        }
    }
}