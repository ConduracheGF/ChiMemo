package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

public class OBJ_Fireball extends Projectile implements OBJ_Interface {
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp){
        super(gp);
        this.gp = gp;

        name = "Fireball";
        speed = 8;
        maxLife = 50;
        life = maxLife;
        attack = 2;
        useCost = 1;
        alive = false;
        getImage();
    }
    public void getImage(){
        up1 = setup("/Projectile/fireball_up",gp.tileSize,gp.tileSize);
        up2 = setup("/Projectile/fireball_up",gp.tileSize,gp.tileSize);
        left1 = setup("/Projectile/fireball_left",gp.tileSize,gp.tileSize);
        left2 = setup("/Projectile/fireball_left",gp.tileSize,gp.tileSize);
        down1 = setup("/Projectile/fireball_down",gp.tileSize,gp.tileSize);
        down2 = setup("/Projectile/fireball_down",gp.tileSize,gp.tileSize);
        right1 = setup("/Projectile/fireball_right",gp.tileSize,gp.tileSize);
        right2 = setup("/Projectile/fireball_right",gp.tileSize,gp.tileSize);

    }
    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        if(user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void substractResource(Entity user) {
        user.mana -= useCost;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
