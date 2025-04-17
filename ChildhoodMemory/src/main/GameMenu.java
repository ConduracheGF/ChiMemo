package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class GameMenu {
    private JFrame menuFrame;
    private GamePanel gamePanel;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private GameState savedGameState;
    private final String SAVE_FILE = "game_state.sav";

    // Clasa pentru starea jocului
    private static class GameState implements Serializable {
        int worldX;
        int worldY;

        public GameState(int worldX, int worldY) {
            this.worldX = worldX;
            this.worldY = worldY;
        }
    }

    public GameMenu() {
        loadGameState(); // Încarcă starea salvată la inițializare
        createMenuFrame();
    }

    private void loadGameState() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            savedGameState = (GameState) ois.readObject();
        } catch (FileNotFoundException e) {
            // Fișierul nu există încă, folosim starea implicită
            savedGameState = new GameState(23 * 64, 21 * 64); // Poziție inițială (presupunând tileSize=64)
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            savedGameState = new GameState(23 * 64, 21 * 64); // Fallback la poziție inițială
        }
    }

    private void saveGameState() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(savedGameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createMenuFrame() {
        menuFrame = new JFrame("Childhood Memory - Menu");
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setSize(800, 600);
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        createMainMenu();
        createGameMenu();
        createSettingsMenu();

        menuFrame.add(cardPanel);
        menuFrame.setVisible(true);
    }

    private void createMainMenu() {
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridBagLayout());
        mainMenuPanel.setBackground(new Color(50, 50, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel title = new JLabel("CHILDHOOD MEMORY", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(0, 50, 50, 50);
        mainMenuPanel.add(title, gbc);

        gbc.insets = new Insets(10, 50, 10, 50);

        JButton startButton = createMenuButton("START");
        startButton.addActionListener(e -> cardLayout.show(cardPanel, "GAME_MENU"));
        mainMenuPanel.add(startButton, gbc);

        JButton settingsButton = createMenuButton("SETTINGS");
        settingsButton.addActionListener(e -> cardLayout.show(cardPanel, "SETTINGS"));
        mainMenuPanel.add(settingsButton, gbc);

        JButton exitButton = createMenuButton("EXIT");
        exitButton.addActionListener(e -> System.exit(0));
        mainMenuPanel.add(exitButton, gbc);

        cardPanel.add(mainMenuPanel, "MAIN_MENU");
    }

    private void createGameMenu() {
        JPanel gameMenuPanel = new JPanel();
        gameMenuPanel.setLayout(new GridBagLayout());
        gameMenuPanel.setBackground(new Color(50, 50, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel title = new JLabel("SELECT GAME", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(0, 50, 50, 50);
        gameMenuPanel.add(title, gbc);

        gbc.insets = new Insets(10, 50, 10, 50);

        JButton newGameButton = createMenuButton("NEW GAME");
        newGameButton.addActionListener(e -> {
            menuFrame.dispose();
            startGame(new GameState(23 * 64, 21 * 64)); // Poziție inițială
        });
        gameMenuPanel.add(newGameButton, gbc);

        JButton loadGameButton = createMenuButton("LOAD GAME");
        loadGameButton.addActionListener(e -> {
            menuFrame.dispose();
            startGame(savedGameState); // Ultima stare salvată
        });
        gameMenuPanel.add(loadGameButton, gbc);

        JButton backButton = createMenuButton("BACK");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "MAIN_MENU"));
        gameMenuPanel.add(backButton, gbc);

        cardPanel.add(gameMenuPanel, "GAME_MENU");
    }

    private void createSettingsMenu() {
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridBagLayout());
        settingsPanel.setBackground(new Color(50, 50, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel title = new JLabel("SETTINGS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        gbc.insets = new Insets(0, 50, 50, 50);
        settingsPanel.add(title, gbc);

        gbc.insets = new Insets(10, 50, 10, 50);

        JLabel volumeLabel = new JLabel("Volume:", SwingConstants.CENTER);
        volumeLabel.setForeground(Color.WHITE);
        settingsPanel.add(volumeLabel, gbc);

        JSlider volumeSlider = new JSlider(0, 100, 50);
        settingsPanel.add(volumeSlider, gbc);

        JButton backButton = createMenuButton("BACK");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "MAIN_MENU"));
        settingsPanel.add(backButton, gbc);

        cardPanel.add(settingsPanel, "SETTINGS");
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(70, 70, 120));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        button.setPreferredSize(new Dimension(300, 50));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(90, 90, 150));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 70, 120));
            }
        });

        return button;
    }

    private void startGame(GameState gameState) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Childhood Memory");

        gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Setăm poziția jucătorului
        gamePanel.player.worldX = gameState.worldX;
        gamePanel.player.worldY = gameState.worldY;

        // Actualizăm starea salvată periodic sau la închidere
        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                savedGameState = new GameState(
                        gamePanel.player.worldX,
                        gamePanel.player.worldY
                );
                saveGameState();
            }
        });

        gamePanel.startGameThread();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameMenu());
    }
}