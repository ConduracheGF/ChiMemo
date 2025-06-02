package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Arrow extends Projectile implements OBJ_Interface {
    GamePanel gp;
    public OBJ_Arrow(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Arrow";
        speed = 8;
        maxLife = 50;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);
        up2 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);
        up3 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);
        up4 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);
        up5 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);
        up6 = setup("/Projectile/arrow_up",gp.tileSize,gp.tileSize);

        left1 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);
        left2 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);
        left3 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);
        left4 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);
        left5 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);
        left6 = setup("/Projectile/arrow_left",gp.tileSize,gp.tileSize);

        down1 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);
        down2 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);
        down3 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);
        down4 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);
        down5 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);
        down6 = setup("/Projectile/arrow_down",gp.tileSize,gp.tileSize);

        right1 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
        right2 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
        right3 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
        right4 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
        right5 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
        right6 = setup("/Projectile/arrow_right",gp.tileSize,gp.tileSize);
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}