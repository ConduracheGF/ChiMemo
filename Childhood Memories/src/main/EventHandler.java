package main;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    int tempMap, tempCol, tempRow;
    public static int countPortalTeleport = 0;

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map = 0;
        int col = 0;
        int row = 0;
        while (map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow){
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 23;
            eventRect[map][col][row].y = 23;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col = 0;
                row++;
                if(row == gp.maxWorldRow) {
                    row = 0;
                    map++;
                }
            }
        }
    }
    public void checkEvent() {
        //CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM THE LAST EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);
        if(distance > gp.tileSize){
            canTouchEvent = true;
        }
        //apeluri evenimente pentru damagePit, heallingPool(neverificat) si teleport
        if(canTouchEvent == true) {
            if (hit(0, 2, 2, "any") == true) {
                damagePit(gp.dialogState);
            } else if (hit(0, 2, 3, "any") == true) {
                heallingPool(gp.dialogState);
            }
            //putem pune o conditie daca moare boss ul, in hit la apel punem coordonatele lui Mihai si poate pleca de oriunde la victorie
            else if (hit(0, 2, 4, "any") == true) {
                if (gp.portalActive == true) {
                    teleport(1, 2, 2);
                    gp.portalActive = false;
                    countPortalTeleport++;
                }

                if (countPortalTeleport >= 2) {
                    teleport(1, 2, 2);
                }
            } else if (hit(1, 2, 2, "any") == true) {
                if (gp.portalActive == true) {
                    teleport(2, 2, 4);
                    gp.portalActive = false;
                    countPortalTeleport++;
                }
                if (countPortalTeleport >= 2) {
                    teleport(2, 2, 4);
                }
            } else if (hit(2, 2, 3, "any") == true) {
                if (gp.portalActive == true) {
                    teleport(0, 2, 4);
                    gp.portalActive = false;
                    countPortalTeleport++;
                }
                if (countPortalTeleport > 2) {
                    teleport(0, 2, 4);
                }
            }
        }
    }

    public boolean hit(int map, int col, int row, String regDirection) {
        boolean hit = false;

        if(map == gp.currentMap) {
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if(gp.player.direction.contentEquals(regDirection) || regDirection.contentEquals("any")) {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }

            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }
        return hit;
    }
    public void teleport(int map, int col, int row){
        gp.gameState = gp.transitionState;
        tempMap = map;
        tempCol = col;
        tempRow = row;
        canTouchEvent = false;
        gp.playSE(11);
    }
    public void damagePit(int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fall  into a pit!";
        gp.player.life -= 1;
        //eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
    public void heallingPool(int gameState) {
        if(gp.keyH.enterPressed == true){
            gp.gameState = gameState;
            gp.player.attackCanceled = true;
            gp.ui.currentDialogue = "You drink the water.\nYour life has been recovered.";
            gp.player.life = gp.player.maxLife;
            gp.aSetter.setMonster();
            //gp.saveLoad.saveInventory();
            gp.saveLoad.saveWinnerToDatabase(gp.player.exp);
        }
    }
}
