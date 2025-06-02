package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import javax.imageio.ImageIO;

import main.AssetSetter;
import ai.PathFinder;
import data.DataStorage;
import data.SaveLoad;
import entity.Entity;
import entity.Player;
import tile.TileManager;

import static main.AssetSetter.creat;
import static main.Main.window;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 4;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 15;
    public final int maxScreenRow = 10;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public boolean isNewGame = true;

    // WORLD SETTINGS
    public final int maxWorldCol = 40;
    public final int maxWorldRow = 30;
    public final int maxMap = 5;
    public int currentMap = 0;
    public boolean portalActive;
    //FOR FULLSCREEN SETTINGS
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen, TitleBackground, apa1 = null, apa2 = null, apa3 = null;
    Graphics2D g2;
    public boolean fullScreenOn = false;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    public DataStorage dataStorage;


    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    public PathFinder pFinder = new PathFinder(this);
    SaveLoad saveLoad = new SaveLoad();
    Thread gameThread;

    //ENTITY AND OBJECT
    public Player player = new Player(this, keyH);
    public Entity[][] obj = new Entity[maxMap][20];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] monster = new Entity[maxMap][50];
    ArrayList<Entity> entityList = new ArrayList<>();
    public ArrayList<Entity> projectileList = new ArrayList<>();

    //GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogState = 3;
    public final int characterState = 4;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int transitionState = 7;
    public final int endGameState = 8;
    private boolean reloaded = true;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public  void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
        gameState = titleState;
        try {
            TitleBackground = ImageIO.read(getClass().getResourceAsStream("/Maps/ImagineFundalJoc.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            apa1 = ImageIO.read(getClass().getResourceAsStream("/Tile_Niv2/IarbaApa/Apa1.png"));
            apa2 = ImageIO.read(getClass().getResourceAsStream("/Tile_Niv2/IarbaApa/Apa2.png"));
            apa3 = ImageIO.read(getClass().getResourceAsStream("/Tile_Niv2/IarbaApa/Apa3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();
    }
    public void resetGame(boolean restart) {
        monster = new Entity[maxMap][50];
        npc = new Entity[maxMap][10];
        obj = new Entity[maxMap][20];
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreStatus();
        player.resetCounter();
        aSetter.setMonster();
        aSetter.setNPC();
        aSetter.setObject();
    }
    public void setFullScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Main.window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        screenWidth2 = (int) width;
        screenHeight2 = (int) height;
        float fullScreenOffsetFactor = (float) screenWidth / (float) screenWidth2;
    }
    public void setWindowed() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();

        //dezactiveaza full screen ul
        gd.setFullScreenWindow(null);

        //reconstruire window
        window.dispose();
        window.setUndecorated(false);
        window.setResizable(false);
        window.setPreferredSize(new Dimension(screenWidth, screenHeight));
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        screenWidth2 = screenWidth;
        screenHeight2 = screenHeight;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    private void applySaveData() {

        if (dataStorage != null) {

            player.worldX = dataStorage.posX;
            player.worldY = dataStorage.posY;
            player.life = dataStorage.life;
            player.speed = 8;
            player.name = dataStorage.name;
            player.maxLife = dataStorage.maxLife;
            player.maxMana = dataStorage.maxMana;
            player.mana = dataStorage.mana;
            player.strength = dataStorage.strength;
            player.dexterity = dataStorage.dexterity;
            player.exp = dataStorage.exp;
            player.nextLevelExp = dataStorage.nextLevelExp;
            player.score = dataStorage.score;
            currentMap = dataStorage.harta;
            player.level = dataStorage.level;
            if(currentMap == 0) {
                player.kills = 0;
            } else if (currentMap == 1) {
                player.kills = creat[0]+1;
            } else if (currentMap == 2) {
                player.kills = creat[0]+creat[1]+2;
            }
        }
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen(); // draw everything to the buffered image
                drawToScreen(); // draw the buffered image to the screen
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        if(gameState == playState){
            //PLAYER
            player.update();
            //LOAD PLAYER
            if(!isNewGame&&reloaded){
                applySaveData();
                reloaded=false;
            }
            //NPC
            for(int i = 0; i < npc[currentMap].length; i++) {
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }
            }
            for(int i = 0; i < monster[currentMap].length; i++) {
                if(monster[currentMap][i] != null) {
                    if(monster[currentMap][i].alive ==  true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }

                    if(monster[currentMap][i].alive ==  false){
                        monster[currentMap][i].checkDrop();
                        monster[currentMap][i] = null;
                    }

                }
            }
            for(int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive == true) {
                        projectileList.get(i).update();
                    }

                    if (projectileList.get(i).alive == false) {
                        projectileList.remove(i);
                    }

                }
            }
        }
        if(gameState == pauseState){
            //nimic
        }
    }
    public void drawToTempScreen() {

        //Daca vrei doar iarba
        Random rand = new Random(100);

        BufferedImage[] apaTiles = {apa1, apa2};

        for (int x = 0; x < screenWidth2; x += 64) {
            for (int y = 0; y < screenHeight2; y += 64) {
                int index = rand.nextInt(apaTiles.length); // 0, 1 sau 2
                g2.drawImage(apaTiles[index], x, y, null);
            }
        }

        if(gameState == titleState){
            ui.draw(g2);
        }

        //OTHERS
        else {
            //TILE
            tileM.drawGroundAndObjects(g2); // desenare sol + obiecte

            //ADD ENTITIES

            for(int i = 0; i < obj[currentMap].length; i++){
                if(obj[currentMap][i] != null) {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i = 0; i < npc[currentMap].length; i++) {
                if(npc[currentMap][i] != null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i = 0; i < monster[currentMap].length; i++) {
                if(monster[currentMap][i] != null){
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i = 0; i < projectileList.size(); i++) {
                if(projectileList.get(i) != null){
                    entityList.add(projectileList.get(i));
                }
            }

            //SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            //DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            //EMPTY ENTITY LIST
            entityList.clear();

            player.draw(g2);
            //TILE
            tileM.drawTrees(g2);           // desenare copaci (peste jucător)

            //UI
            ui.draw(g2);
        }

        //DEBUG
        long drawStart = 0;
        if(keyH.showDebugText == true){
            drawStart = System.nanoTime();
        }

        //DEBUG
        if(keyH.showDebugText == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.white);
            int x = 10;
            int y = 400;
            int lineHeight = 20;
            g2.drawString("WorldX: " + player.worldX, x, y); y += lineHeight;
            g2.drawString("WorldY: " + player.worldY, x, y); y += lineHeight;
            g2.drawString("Col: " + (player.worldX + player.solidArea.x)/tileSize, x, y); y += lineHeight;
            g2.drawString("Row: " + (player.worldY + player.solidArea.y)/tileSize, x, y); y += lineHeight;
            g2.drawString("Draw Time: " + passed, x, y);
        }
    }
    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0 , 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic() {
        music.stop();
    }
    //SOUND EFECT
    public void playSE(int i){
        se.setFile(i);
        se.play();
    }
}
