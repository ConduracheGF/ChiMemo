package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion extends Entity implements OBJ_Interface {
    GamePanel gp;
    public OBJ_Potion(GamePanel gp){
        super(gp);

        this.gp = gp;

        type = type_consumable;
        name = "Potion";
        value = 5;
        down1 = setup("/Objects/Potiune", gp.tileSize, gp.tileSize);
        description = "[" + name + "]\nNew potion. New Life.\nHeals your life by " + value + ".";
        stackable = true;
    }
    public void use(Entity entity){
        gp.gameState = gp.dialogState;
        gp.ui.currentDialogue = "You drank the " + name + "!\nYour life has been recovered by " + value + ".\n" + "Your ammo has been restored by " + value + ".";
        entity.life += value;
        entity.mana += value;
        gp.playSE(3);
    }
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
