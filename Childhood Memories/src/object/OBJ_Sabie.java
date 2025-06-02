package object;

import entity.Entity;
import main.GamePanel;


public class OBJ_Sabie extends Entity implements OBJ_Interface {
    public OBJ_Sabie(GamePanel gp){
        super(gp);

        type = type_sword;
        name = "Sword";
        down1 = setup("/Objects/Sabie", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword.";
        stackable = true;
        motion1_duration = 2;
        motion2_duration = 10;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
