package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH)
    {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 2;
        direction = "down";
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk00.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk01.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk02.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk03.png"));
            up5 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk04.png"));
            up6 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk05.png"));
            up7 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk06.png"));
            up8 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk07.png"));
            up9 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk08.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk09.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk10.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk11.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk12.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk13.png"));
            left6 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk14.png"));
            left7 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk15.png"));
            left8 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk16.png"));
            left9 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk17.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk18.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk19.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk20.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk21.png"));
            down5 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk22.png"));
            down6 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk23.png"));
            down7 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk24.png"));
            down8 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk25.png"));
            down9 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk26.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk27.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk28.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk29.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk30.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk31.png"));
            right6 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk32.png"));
            right7 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk33.png"));
            right8 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk34.png"));
            right9 = ImageIO.read(getClass().getResourceAsStream("/Mihai/sprite_Mihai_walk35.png"));


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if (keyH.upPressed == true) {
                direction = "up";
                y  -= speed;
            } else if(keyH.downPressed == true) {
                direction = "down";
                y  += speed;
            } else if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
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
    }
    public void draw(Graphics2D g2){
       // g2.setColor(Color.white);
       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;
        switch (direction)
        {
            case "up":
                if(spriteNum == 1)
                {
                    image = up1;
                }
                if(spriteNum == 2)
                {
                    image = up2;
                }
                if(spriteNum == 3)
                {
                    image = up3;
                }
                if(spriteNum == 4)
                {
                    image = up4;
                }
                if(spriteNum == 5)
                {
                    image = up5;
                }
                if(spriteNum == 6)
                {
                    image = up6;
                }
                if(spriteNum == 7)
                {
                    image = up7;
                }
                if(spriteNum == 8)
                {
                    image = up8;
                }
                if(spriteNum == 9)
                {
                    image = up9;
                }
                break;
            case "down":
                if(spriteNum == 1)
                {
                    image = down1;
                }
                if(spriteNum == 2)
                {
                    image = down2;
                }
                if(spriteNum == 3)
                {
                    image = down3;
                }
                if(spriteNum == 4)
                {
                    image = down4;
                }
                if(spriteNum == 5)
                {
                    image = down5;
                }
                if(spriteNum == 6)
                {
                    image = down6;
                }
                if(spriteNum == 7)
                {
                    image = down7;
                }
                if(spriteNum == 8)
                {
                    image = down8;
                }
                if(spriteNum == 9)
                {
                    image = down9;
                }
                break;
            case "left":
                if(spriteNum == 1)
                {
                    image = left1;
                }
                if(spriteNum == 2)
                {
                    image = left2;
                }
                if(spriteNum == 3)
                {
                    image = left3;
                }
                if(spriteNum == 4)
                {
                    image = left4;
                }
                if(spriteNum == 5)
                {
                    image = left5;
                }
                if(spriteNum == 6)
                {
                    image = left6;
                }
                if(spriteNum == 7)
                {
                    image = left7;
                }
                if(spriteNum == 8)
                {
                    image = left8;
                }
                if(spriteNum == 9)
                {
                    image = left9;
                }
                break;
            case "right":
                if(spriteNum == 1)
                {
                    image = right1;
                }
                if(spriteNum == 2)
                {
                    image = right2;
                }
                if(spriteNum == 3)
                {
                    image = right3;
                }
                if(spriteNum == 4)
                {
                    image = right4;
                }
                if(spriteNum == 5)
                {
                    image = right5;
                }
                if(spriteNum == 6)
                {
                    image = right6;
                }
                if(spriteNum == 7)
                {
                    image = right7;
                }
                if(spriteNum == 8)
                {
                    image = right8;
                }
                if(spriteNum == 9)
                {
                    image = right9;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
