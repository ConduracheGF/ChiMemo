package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage inima_full, inima_half, inima_goala, crystal_full, crystal_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public String currentDialogue = "";
    public int commandNum = 0;
    public int titleScreenState = 0; //0: the filter is the second screen
    public int slotCol = 0;
    public int slotRow = 0;
    int subState = 0;
    int pauzaState = 0;
    int counter = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Cambria", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        //CREATE HUD OBJECT
        Entity inima = new OBJ_Heart(gp);
        inima_full = inima.image;
        inima_half = inima.image2;
        inima_goala = inima.image3;
        Entity crystal = new OBJ_ManaCrystal(gp);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;
    }
    public void showMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //TITLE SCREEN
        if(gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        //PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        //DIALOG STATE
        if(gp.gameState == gp.dialogState){
            drawPlayerLife();
            drawDialogueScreen();
        }
        //CHARACTER STATE
        if(gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
        //OPTIONS STATE
        if(gp.gameState == gp.optionsState) {
            drawOptionsScreen();
        }
        //GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }
        //TRANSITION STATE
        if(gp.gameState == gp.transitionState) {
            drawTransition();
        }

        //GAME FINISHED STATE
        if(gp.gameState == gp.endGameState){
            drawEndState();
        }
    }
    public void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110F));

        text = "Game Over";
        //Umbra la text
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Retry
        g2.setFont(g2.getFont().deriveFont(50F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-40, y);
        }

        //Back to the title screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 90;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-40, y);
        }
    }
    public void drawPlayerLife() {

        //gp.player.life = 2;

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //MAX LIFE
        while(i < gp.player.maxLife/2){
            g2.drawImage(inima_goala, x, y,null);
            i++;
            x += gp.tileSize;
        }
        //RESET
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //CURRENT LIFE
        while(i < gp.player.life) {
            g2.drawImage(inima_half, x, y, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(inima_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }

        // Arată mana DOAR dacă arma curentă este un baston magic
        if(gp.player.currentWeapon != null && gp.player.currentWeapon.type == Entity.type_baston_magic) {
            //MAX MANA
            x = gp.tileSize/2;
            y = (int) (gp.tileSize*1.5);
            i = 0;
            while(i < gp.player.maxMana) {
                g2.drawImage(crystal_blank, x, y, null);
                i++;
                x += 35;
            }

            //MANA
            x = gp.tileSize/2;
            y = (int) (gp.tileSize*1.5);
            i = 0;
            while(i < gp.player.mana) {
                g2.drawImage(crystal_full, x, y, null);
                i++;
                x += 35;
            }
        }

    }
    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX + 2, messageY + 2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // messageCounter ++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawEndState() {
        //BACKGROUND
        g2.drawImage(gp.TitleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
        String text;
        // Dimensiuni tabel
        int boxWidth = gp.tileSize * 14;
        int boxHeight = gp.tileSize * 6;
        int boxX = (gp.screenWidth - boxWidth) / 2;
        int boxY = gp.tileSize * 2;
        drawSubWindow(boxX, boxY, boxWidth, boxHeight);


        //TITLE NAME
        int x;
        int y;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 55F));
        g2.setColor(new Color(58,142,186));
        text = "Congrats! You finished the game!";
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        //Quit
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,50F));
        g2.setColor(new Color(58,142,186));
        text = "Quit";
        x = getXforCenteredText(text);
        y += 90;
        g2.drawString(text, x, y);
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);
        if(commandNum == 0) {
            g2.setColor(new Color(58,142,186));
            g2.drawString(">", x-40, y);
        }
    }
    public void drawTitleScreen() {
        g2.setColor(new Color(0,0,0));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        if(titleScreenState == 0){

            //BACKGROUND
            g2.drawImage(gp.TitleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);
            //TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "ChildhoodMemories";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            //SHADOW
            g2.setColor(new Color(58,142,186));
            g2.drawString(text,x+5,y+5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //MENIU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
            // Dimensiuni tabel
            int boxWidth = gp.tileSize * 4;
            int boxHeight = gp.tileSize * 4;
            int boxX = (gp.screenWidth - boxWidth) / 2;
            int boxY = gp.tileSize * 5;

            drawSubWindow(boxX,boxY,boxWidth,boxHeight);
            text = "PLAY";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }

            text = "SETTINGS";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {

            g2.drawImage(gp.TitleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);

            // Dimensiuni tabel
            int boxWidth = gp.tileSize * 5;
            int boxHeight = gp.tileSize * 4;
            int boxX = (gp.screenWidth - boxWidth) / 2;
            int boxY = gp.tileSize * 3;

            drawSubWindow(boxX, boxY, boxWidth, boxHeight);
            g2.setColor(new Color(58,142,186));
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));


            String text = "New Game";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*4;
            g2.drawString(text, x, y);
            if(commandNum == 0){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }

            text = "Load Game";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2){
                g2.drawString(">", x + 20 - gp.tileSize, y);
            }
        }
    }
    public void drawPauseScreen() {

        g2.drawImage(gp.TitleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setColor(new Color(58,142,186));
        g2.setFont(g2.getFont().deriveFont(42F));

        int frameX = gp.tileSize*5;
        int frameY = gp.tileSize*2;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*6;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (pauzaState) {
            case 0: pause(frameX, frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void pause(int frameX, int frameY) {

        int textX;
        int textY;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));
        //TITLE
        String text = "PAUSED";
        textX = getXforCenteredText(text) + gp.tileSize/2;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //RESUME
        textY += (gp.tileSize * 3/2);
        g2.drawString("Resume", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                gp.gameState = gp.playState;
                commandNum = 0;
            }
        }

        //OPTIONS
        textY += gp.tileSize;
        g2.drawString("Options", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                gp.gameState = gp.optionsState;
                commandNum = 0;
            }
        }

        //QUIT GAME
        textY += gp.tileSize;
        g2.drawString("Quit Game", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed) {
                //devine lent
                subState = 0;
                gp.resetGame(true);
                quitGame();
            }
        }
    }
    public void quitGame() {
        System.exit(0);
    }
    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line: currentDialogue.split("\n")) {
            g2.drawString(line, x-25, y);
            y += 40;
        }
    }
    public void drawCharacterScreen() {
        //CREATE A FRAME
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize * 5;
        final int frameHeight = gp.tileSize * 8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //TEXT
        g2.setColor(new Color(58,142,186));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));


        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //NAMES
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Fireballs", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Score", textX, textY);
        textY += lineHeight;
        g2.drawString("Experience", textX, textY);
        textY += lineHeight;
        g2.drawString("NextLevelExp", textX, textY);
        textY += lineHeight;
        g2.drawString("Kills", textX, textY);
        textY += lineHeight + 5;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 5;
        g2.drawString("Armour", textX, textY);
        textY += lineHeight;

        //VALUES
        int tailX = (frameX + frameWidth) - 30;
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.score);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.kills);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight/2;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - 2*gp.tileSize/3 + 3, textY-20,null);
        textY += (gp.tileSize/2+gp.tileSize/4);
        g2.drawImage(gp.player.currentArmour.down1, tailX - 2*gp.tileSize/3 + 3, textY-24,null);
    }
    public void drawInventory() {
        //FRAME
        int frameX = gp.tileSize*8;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        //SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        //DRAW PLAYER'S ITEMS
        for(int i = 0; i < gp.player.inventory.size(); i++) {
            //EQUIP CURSOR
            if(gp.player.inventory.get(i) == gp.player.currentWeapon ||
                    gp.player.inventory.get(i) == gp.player.currentArmour){
                g2.setColor(new Color(58,142,186,128));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);

            //Display amount
            if(gp.player.inventory.get(i).amount > 1) {
                g2.setFont(g2.getFont().deriveFont(28f));
                int amountX;
                int amountY;

                String s = "" + gp.player.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //Shadow
                g2.setColor(new Color(58,142,186,60));
                g2.drawString(s, amountX+20, amountY);
                //Number
                g2.setColor(new Color(58,142,186));
                g2.drawString(s, amountX+17, amountY-3);
            }

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //CURSOR
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;
        //DRAW CURSOR
        g2.setColor(new Color(58,142,186));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        //DESCRIPTION FRAME
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;

        //DRAW DESCRIPTION TEXT
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()) {
            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for(String line: gp.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }
    public void drawOptionsScreen() {

        //BACKGROUND
        g2.drawImage(gp.TitleBackground, 0, 0, gp.screenWidth, gp.screenHeight, null);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 28f));

        //SUB WINDOW
        int frameX = gp.tileSize*5;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*8;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX, frameY); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        //TITLE
        String text = "Options";
        textX = getXforCenteredText(text) + gp.tileSize/2;
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FULL SCREEN ON/OFF
        textX = frameX + (2*gp.tileSize/3);
        textY += gp.tileSize;
        g2.drawString("Full Screen", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                if(gp.fullScreenOn == false) {
                    gp.fullScreenOn = true;
                    gp.setFullScreen();
                } else if(gp.fullScreenOn == true) {
                    gp.fullScreenOn = false;
                    gp.setWindowed();
                }
                subState = 1;
            }
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1) {
            g2.drawString(">", textX-25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2) {
            g2.drawString(">", textX-25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }

        //Back
        textY += (gp.tileSize * 3/2);
        g2.drawString("Back", textX, textY);
        if(commandNum == 4) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                if (gp.ui.titleScreenState == 0 && gp.gameState == gp.optionsState) {
                    // Back to title menu
                    gp.gameState = gp.titleState;
                    commandNum = 0;
                } else {
                    // Back to pause menu
                    gp.gameState = gp.pauseState;
                    pauzaState = 0;
                    commandNum = 0;
                }
            }
        }

        //FULL SCREEN CHECK
        textX = frameX + gp.tileSize*4 - gp.tileSize/2;
        textY = frameY + 3*gp.tileSize/2 + 10;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 24, 24);
        if(gp.fullScreenOn == true) {
            g2.fillRect(textX, textY, 24, 24);
        }

        //MUSIC VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        int volumeWidth = 24 * gp.music.volumeScale;
        g2.fillRect(textX, textY, volumeWidth,24);

        //SE VOLUME
        textY += gp.tileSize;
        g2.drawRect(textX, textY, 120, 24);
        volumeWidth = 24 * gp.se.volumeScale;
        g2.fillRect(textX, textY, volumeWidth,24);
    }
    public void options_fullScreenNotification(int frameX, int frameY) {
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will take \neffect after restarting \nthe game.";

        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += 40;
        }

        //BACK
        textY = frameY + gp.tileSize*7;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY) {
        int textX;
        int textY;

        //TITLE
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize/2;
        textY += gp.tileSize;
        g2.drawString("Move", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Dialog/Attack", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Shoot/Cast", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Me/Inventory", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Pause", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Confirm/Select", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Run", textX, textY);

        textX = frameX + gp.tileSize*4;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Space", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("F", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("J", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("ESC", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Enter", textX, textY); textY += 3*gp.tileSize/4;
        g2.drawString("Shift", textX, textY);

        //BACK
        textX = frameX + gp.tileSize/2;
        textY = frameY + gp.tileSize*7+gp.tileSize/3;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0) {
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true) {
                subState = 0;
            }
        }
    }
    public int getItemIndexOnSlot() {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawTransition() {
        counter++;
        g2.setColor(new Color(0,0,0,counter*5));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        if(counter == 50) {
            counter = 0;
            gp.gameState = gp.playState;
            gp.currentMap = gp.eHandler.tempMap;
            gp.player.worldX = gp.tileSize * gp.eHandler.tempCol;
            gp.player.worldY = gp.tileSize * gp.eHandler.tempRow;
            gp.eHandler.previousEventX = gp.player.worldX;
            gp.eHandler.previousEventY = gp.player.worldY;
        }
    }
    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(248, 248, 250, 220)); // alb cu transparenta
        g2.fillRoundRect(x,y,width,height, 35, 35);

        g2.setColor(new Color(58,142,186));
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5,y+5,width-10,height-10, 25, 25);

    }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}