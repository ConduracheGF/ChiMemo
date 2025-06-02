package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity implements OBJ_Interface {
    public OBJ_Chest(GamePanel gp) {
        super(gp);
        name = "Chest";
        //schimba poza cu un cufar
        image = setup("/Objects/Sabie", gp.tileSize, gp.tileSize);

    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
