package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Armour extends Entity implements OBJ_Interface {
    public OBJ_Armour(GamePanel gp){
        super(gp);

        type = type_armour;
        name = "Armour";
        down1 = setup("/Objects/armour", gp.tileSize, gp.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nMade by iron.";
        stackable = true;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
