package object;

import entity.Entity;
import  main.GamePanel;

public class OBJ_Baston extends Entity implements OBJ_Interface {
    public OBJ_Baston(GamePanel gp) {
        super(gp);

        type = type_baston_magic;
        name = "Magic Wand";
        down1 = setup("/Objects/baston_magic1", gp.tileSize, gp.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Bastonul Magic]\n You can throw a fire ball!\n It's bit malefical, but it's not!";
        stackable = true;
        motion1_duration = 20;
        motion2_duration = 40;
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}