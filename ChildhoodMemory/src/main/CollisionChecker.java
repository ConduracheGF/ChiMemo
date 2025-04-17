package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // Get entity's solid area position in world coordinates
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Calculate which tiles the entity is touching
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        // Variables to store the tiles we'll check
        int tileNum1, tileNum2;

        // Reset collision flag
        entity.collisionOn = false;

        // Check collision based on movement direction
        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                if(entityTopRow < 0) {
                    entity.collisionOn = true;
                    break;
                }

                // Check all tiles along the top edge
                for(int col = entityLeftCol; col <= entityRightCol; col++) {
                    if(col < 0 || col >= gp.maxWorldCol) {
                        entity.collisionOn = true;
                        break;
                    }

                    // Check all layers for collision
                    if(isTileColliding(col, entityTopRow)) {
                        entity.collisionOn = true;
                        break;
                    }
                }
                break;

            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                if(entityBottomRow >= gp.maxWorldRow) {
                    entity.collisionOn = true;
                    break;
                }

                // Check all tiles along the bottom edge
                for(int col = entityLeftCol; col <= entityRightCol; col++) {
                    if(col < 0 || col >= gp.maxWorldCol) {
                        entity.collisionOn = true;
                        break;
                    }

                    if(isTileColliding(col, entityBottomRow)) {
                        entity.collisionOn = true;
                        break;
                    }
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                if(entityLeftCol < 0) {
                    entity.collisionOn = true;
                    break;
                }

                // Check all tiles along the left edge
                for(int row = entityTopRow; row <= entityBottomRow; row++) {
                    if(row < 0 || row >= gp.maxWorldRow) {
                        entity.collisionOn = true;
                        break;
                    }

                    if(isTileColliding(entityLeftCol, row)) {
                        entity.collisionOn = true;
                        break;
                    }
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                if(entityRightCol >= gp.maxWorldCol) {
                    entity.collisionOn = true;
                    break;
                }

                // Check all tiles along the right edge
                for(int row = entityTopRow; row <= entityBottomRow; row++) {
                    if(row < 0 || row >= gp.maxWorldRow) {
                        entity.collisionOn = true;
                        break;
                    }

                    if(isTileColliding(entityRightCol, row)) {
                        entity.collisionOn = true;
                        break;
                    }
                }
                break;
        }
    }

    private boolean isTileColliding(int col, int row) {
        // Check ground layer
        if(gp.tileM.groundLayer[col][row] != -1 &&
                gp.tileM.tile[gp.tileM.groundLayer[col][row]].collision) {
            return true;
        }

        // Check objects layer
        if(gp.tileM.objectsLayer[col][row] != -1 &&
                gp.tileM.tile[gp.tileM.objectsLayer[col][row]].collision) {
            return true;
        }

        // Check trees layer
        if(gp.tileM.treesDLayer[col][row] != -1 &&
                gp.tileM.tile[gp.tileM.treesDLayer[col][row]].collision) {
            return true;
        }
        // Check trees celalalt
        if(gp.tileM.treesDeLayer[col][row] != -1 &&
                gp.tileM.tile[gp.tileM.treesDeLayer[col][row]].collision) {
            return true;
        }

        return false;
    }
}