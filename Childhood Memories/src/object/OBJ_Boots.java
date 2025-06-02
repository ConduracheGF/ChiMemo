package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Boots extends Entity implements OBJ_Interface {
    public OBJ_Boots(GamePanel gp){
        super(gp);
        name = "Boots";
        image = setup("/Objects/shield", gp.tileSize, gp.tileSize);
        collision = true;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
