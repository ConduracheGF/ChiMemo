package main;

import entity.Entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, enterPressed, shotKeyPressed, forcedClosure;
    boolean showDebugText = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int  code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }

        //PLAY STATE
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogState){
            dialogueState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState) {
            characterState(code);
        }
        //OPTIONS STATE
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        //GAME OVER STATE
        else if (gp.gameState == gp.gameOverState) {
            gameOverState(code);
        }
        //END GAME STATE
        else if (gp.gameState == gp.endGameState){
            endGState(code);
        }
    }
    public void titleState(int code) {
        if(gp.ui.titleScreenState == 0){

            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                gp.playSE(1);
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                gp.playSE(1);
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.ui.titleScreenState = 1;
                }
                if(gp.ui.commandNum == 1){
                    gp.gameState = gp.optionsState;
                    gp.ui.subState = 0;
                    gp.ui.commandNum = 0;
                }
                if(gp.ui.commandNum == 2){
                    System.exit(0);
                }
            }
        }
        else if(gp.ui.titleScreenState == 1){

            if(code == KeyEvent.VK_W){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                gp.playSE(1);
            }
            if(code == KeyEvent.VK_S){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                gp.playSE(1);
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 1){
                    gp.dataStorage = gp.saveLoad.loadFromSlot(gp.player.name, gp);
                    gp.isNewGame = false;
                    gp.gameState = gp.playState;
                    gp.playMusic(0);
                }
                if(gp.ui.commandNum == 2){
                    gp.ui.titleScreenState = 0;
                }
            }
        }
    }
    public void playState(int code) {
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_J) {
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_SPACE){
            spacePressed = true;
        }
        //Putem pune si alta tasta
        if(code == KeyEvent.VK_F && gp.player.currentWeapon.type == Entity.type_baston_magic){
            shotKeyPressed = true;
        }
        //Inchidere fortata a jocului
        if(code == KeyEvent.VK_Q){
            System.exit(0);
        }
        // DEBUG
        if(code == KeyEvent.VK_T){
            if(showDebugText == false){
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }
    }
    public void pauseState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
            if (gp.ui.subState == 0 && gp.ui.commandNum == 2) {
                System.out.println("Salvare baza de date");
                gp.saveLoad.saveToSlot(gp.player.name, gp.currentMap, gp.player.level, gp.player.life, gp.player.maxLife, gp.player.mana, gp.player.maxMana, gp.player.strength, gp.player.dexterity, gp.player.exp, gp.player.nextLevelExp, gp.player.score, gp.player.worldX, gp.player.worldY );
                gp.playSE(1);
            }
        }
        int maxCommandNum = 2;
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(1);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(1);
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
    }
    public void dialogueState(int code) {
        if(code == KeyEvent.VK_SPACE){
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code) {
        if(code == KeyEvent.VK_J){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.ui.slotRow != 0){
                gp.ui.slotRow--;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.slotCol != 0){
                gp.ui.slotCol--;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.slotRow != 3){
                gp.ui.slotRow++;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.slotCol != 4){
                gp.ui.slotCol++;
                gp.playSE(1);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }
    public void optionsState(int code) {
        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState) {
            case 0: maxCommandNum = 4; break;
            case 1: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            gp.playSE(1);
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            gp.playSE(1);
            if(gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
    }
    public void gameOverState(int code) {
        if(code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(3);
        }
        if(code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(3);
        }
        if(code == KeyEvent.VK_ENTER) {
            if(gp.ui.commandNum == 0) {
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }
    public void endGState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                System.exit(0);
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            if (gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(1);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) {
                    gp.se.volumeScale--;
                    gp.playSE(1);
                }
            }
        }
        if(code == KeyEvent.VK_D) {
            if (gp.ui.subState == 0) {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(1);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) {
                    gp.se.volumeScale++;
                    gp.playSE(1);
                }
            }
        }
    }
}