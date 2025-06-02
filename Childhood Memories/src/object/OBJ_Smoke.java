package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Smoke extends Projectile implements OBJ_Interface {
    GamePanel gp;
    public OBJ_Smoke(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Smoke";
        speed = 8;
        maxLife = 100;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/Projectile/smoke_0",gp.tileSize,gp.tileSize);
        up2 = setup("/Projectile/smoke_1",gp.tileSize,gp.tileSize);
        up3 = setup("/Projectile/smoke_2",gp.tileSize,gp.tileSize);
        up4 = setup("/Projectile/smoke_3",gp.tileSize,gp.tileSize);
        up5 = setup("/Projectile/smoke_4",gp.tileSize,gp.tileSize);
        up6 = setup("/Projectile/smoke_5",gp.tileSize,gp.tileSize);

        left1 = setup("/Projectile/smoke_0",gp.tileSize,gp.tileSize);
        left2 = setup("/Projectile/smoke_1",gp.tileSize,gp.tileSize);
        left3 = setup("/Projectile/smoke_2",gp.tileSize,gp.tileSize);
        left4 = setup("/Projectile/smoke_3",gp.tileSize,gp.tileSize);
        left5 = setup("/Projectile/smoke_4",gp.tileSize,gp.tileSize);
        left6 = setup("/Projectile/smoke_5",gp.tileSize,gp.tileSize);

        down1 = setup("/Projectile/smoke_0",gp.tileSize,gp.tileSize);
        down2 = setup("/Projectile/smoke_1",gp.tileSize,gp.tileSize);
        down3 = setup("/Projectile/smoke_2",gp.tileSize,gp.tileSize);
        down4 = setup("/Projectile/smoke_3",gp.tileSize,gp.tileSize);
        down5 = setup("/Projectile/smoke_4",gp.tileSize,gp.tileSize);
        down6 = setup("/Projectile/smoke_5",gp.tileSize,gp.tileSize);

        right1 = setup("/Projectile/smoke_0",gp.tileSize,gp.tileSize);
        right2 = setup("/Projectile/smoke_1",gp.tileSize,gp.tileSize);
        right3 = setup("/Projectile/smoke_2",gp.tileSize,gp.tileSize);
        right4 = setup("/Projectile/smoke_3",gp.tileSize,gp.tileSize);
        right5 = setup("/Projectile/smoke_4",gp.tileSize,gp.tileSize);
        right6 = setup("/Projectile/smoke_5",gp.tileSize,gp.tileSize);
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}