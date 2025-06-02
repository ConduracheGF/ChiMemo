package data;

import entity.Entity;
import monster.MON_GreenSheep;
import monster.MON_Schelet;
import monster.MON_YellowShrek;
import monster.Naruto;
import monster.Sonic;
import monster.SuperMario;
import object.*;
import main.GamePanel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SaveLoad {
    GamePanel gp;
    public static final String DB_URL = "jdbc:sqlite:database.db";
    //Salvarea datelor in DB
    public SaveLoad() {
        createTableIfNotExists();
    }
    private void createTableIfNotExists() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();

            String playerSQL = """
                CREATE TABLE IF NOT EXISTS PLAYER_INFO (
                    name VARCHAR(100) PRIMARY KEY,
                    harta INTEGER,
                    level INTEGER,
                    life INTEGER,
                    maxLife INTEGER,
                    mana INTEGER,
                    maxMana INTEGER,
                    strength INTEGER,
                    dexterity INTEGER,
                    exp INTEGER,
                    nextLevelExp INTEGER,
                    score INTEGER,
                    pos_x INTEGER,
                    pos_y INTEGER
                );
            """;

            stmt.execute(playerSQL);

            System.out.println("Tables created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public void saveToSlot(String name, int harta, int level, int life, int maxLife, int mana, int maxMana, int strength, int dexterity, int exp, int nextLevelExp, int score, int pos_x, int pos_y) {
        clearSlotData();

        String sql = """
            INSERT INTO PLAYER_INFO (name, harta, level, life, maxLife, mana, maxMana, strength, dexterity, exp, nextLevelExp, score, pos_x, pos_y)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
        """;

        try  {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, harta);
            pstmt.setInt(3, level);
            pstmt.setInt(4, life);
            pstmt.setInt(5, maxLife);
            pstmt.setInt(6, mana);
            pstmt.setInt(7, maxMana);
            pstmt.setInt(8, strength);
            pstmt.setInt(9, dexterity);
            pstmt.setInt(10, exp);
            pstmt.setInt(11, nextLevelExp);
            pstmt.setInt(12, score);
            pstmt.setInt(13, pos_x);
            pstmt.setInt(14, pos_y);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveInventory(HashMap<Entity, Integer> inventory) {
        String sql = """
            INSERT INTO INVENTORY (item_name, quantity)
            VALUES (?, ?);
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            for (Entity item : inventory.keySet()) {
                int quantity = inventory.get(item);
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, item.getClass().getSimpleName());
                    pstmt.setInt(2, quantity);
                    pstmt.executeUpdate();
                }
            }

            // System.out.println("Inventory saved for slot " + slotId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public HashMap<Entity, Integer> loadInventory(String name) {
        HashMap<Entity, Integer> inventory = new HashMap<>();
        String sql = "SELECT item_name, quantity FROM INVENTORY WHERE slot_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                name = rs.getString("item_name");
                int quantity = rs.getInt("quantity");
                Entity item = createItemByName(name);

                if (item != null) {
                    System.out.println("AM INCARCAT "+item);
                    inventory.put(item, quantity);
                }
            }

            System.out.println("Inventory loaded for slot " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventory;
    }
    private Entity createItemByName(String name) {
        System.out.println("incarc name: " + name);
        return switch (name) {
            case "Magic Wand" -> new OBJ_Baston(gp);
            case "Potion" -> new OBJ_Potion(gp);
            case "Ammo" -> new OBJ_ManaCrystal(gp);
            case "Heart" -> new OBJ_Heart(gp);
            case "Armour" -> new OBJ_Armour(gp);

            case "Arrow" -> new OBJ_Arrow(gp);
            case "Smoke" -> new OBJ_Smoke(gp);
            case "Chest" -> new OBJ_Chest(gp);
            case "Boots" -> new OBJ_Boots(gp);
            case "Fireball" -> new OBJ_Fireball(gp);
            case "Sword" -> new OBJ_Sabie(gp);
            default -> null;
        };
    }
    public  DataStorage loadFromSlot(String name, GamePanel gp) {
        String sql = "SELECT * FROM PLAYER_INFO";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new DataStorage(
                        rs.getString("name"),
                        rs.getInt("harta"),
                        rs.getInt("level"),
                        rs.getInt("life"),
                        rs.getInt("maxLife"),
                        rs.getInt("mana"),
                        rs.getInt("maxMana"),
                        rs.getInt("strength"),
                        rs.getInt("dexterity"),
                        rs.getInt("exp"),
                        rs.getInt("nextLevelExp"),
                        rs.getInt("score"),
                        rs.getInt("pos_x"),
                        rs.getInt("pos_y")
                        //loadMonster(slotId,gp),
                        //loadInventory(name)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void clearSlotData() {
        String[] tables = {"PLAYER_INFO"};

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            for (String table : tables) {
                try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM " + table)) {
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void saveWinnerToDatabase(int score) {
        // Afișează fereastra popup pentru introducerea numelui
        String name = JOptionPane.showInputDialog(null, "Congratulations! Enter your name:", "Player", JOptionPane.PLAIN_MESSAGE);

        // Dacă jucătorul apasă Cancel sau închide fereastra
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Nume invalid. Salvarea a fost anulată.");
            return;
        }
        String sql = "INSERT INTO WINNERS (name, score) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name.trim());
            pstmt.setInt(2, score);
            pstmt.executeUpdate();

            System.out.println("Winner saved: " + name + " (karma: " + score + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
