package object;

import entity.Entity;
import main.GamePanel;



public class OBJ_Heart extends Entity implements OBJ_Interface {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickupOnly;
        value = 2;
        name = "Heart";
        down1 = setup("/Objects/inima_full1", gp.tileSize, gp.tileSize);
        image =  setup("/Objects/inima_full", gp.tileSize, gp.tileSize);
        image2 = setup("/Objects/inima_half", gp.tileSize, gp.tileSize);
        image3 = setup("/Objects/inima_goala", gp.tileSize, gp.tileSize);
    }
    public void use(Entity entity) {
        gp.playSE(6);
        gp.ui.showMessage("Life +" + value);
        entity.life += value;

    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
