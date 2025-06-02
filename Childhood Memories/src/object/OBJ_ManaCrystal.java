package object;
import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity implements OBJ_Interface {
    GamePanel gp;
    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 1;
        name = "Ammo";
        down1 = setup("/Objects/manacrystal_full1",gp.tileSize,gp.tileSize);
        image = setup("/Objects/manacrystal_full",gp.tileSize,gp.tileSize);
        image2 = setup("/Objects/manacrystal_blank",gp.tileSize,gp.tileSize);


    }
    public void use(Entity entity){
        gp.ui.showMessage("Ammo +" + value);
        entity.mana+= value;
        gp.playSE(6);
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
