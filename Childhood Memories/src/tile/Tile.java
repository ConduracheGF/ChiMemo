package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean collision = false;
    public boolean semiSolid = false;
    public int collisionOffsetY = 0;
    public int zIndex = 0;
}