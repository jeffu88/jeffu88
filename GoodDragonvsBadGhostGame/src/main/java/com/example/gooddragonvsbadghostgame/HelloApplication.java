package com.example.gooddragonvsbadghostgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloApplication extends Application {

    private Stage primaryStage;
    private static final int GRID_SIZE = 10;
    private Room[][] rooms;
    private PlayerCharacter player;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        initializeGame();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/gooddragonvsbadghostgame/GoodDragonVsBadGhost-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello And Welcome To Good Dragon Vs Bad Ghost!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void initializeGame() {
        rooms = new Room[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                rooms[i][j] = new Room(i, j, new NPC());
            }
        }

        player = new PlayerCharacter();
    }

    private void handleRoomButtonClick(int x, int y) {
        Room currentRoom = rooms[x][y];
        if (!currentRoom.isBlocked()) {
            handleRoomEvent(currentRoom);
        }
    }

    private void handleRoomEvent(Room room) {
        if (room.hasNPC()) {
            handleNPCEvent(room.getNpc());
        } else {
            int diceRoll = roll20SidedDie();
            if (diceRoll < player.getIntelligence()) {
                int goldFound = new Random().nextInt(11) + 5; // Random gold between 5 and 15
                player.addGold(goldFound);
                System.out.println("You found " + goldFound + " gold!");
            } else {
                System.out.println("The room is empty.");
            }
        }
    }

    private void handleNPCEvent(NPC npc) {
        int playerAttack = roll20SidedDie();
        if (playerAttack >= npc.getDexterity()) {
            int damage = player.getStrength() / 3;
            npc.takeDamage(damage);
            System.out.println("You hit the NPC for " + damage + " damage!");

            if (npc.isAlive()) {
                int npcAttack = roll20SidedDie();
                if (npcAttack >= player.getDexterity()) {
                    player.takeDamage(1); // NPC deals minimum 1 damage
                    System.out.println("The NPC hits you for 1 damage!");
                }
            } else {
                System.out.println("You defeated the NPC!");
            }
        } else {
            int npcAttack = roll20SidedDie();
            if (npcAttack < npc.getIntelligence()) {
                player.takeDamage(1); // NPC hits you as you try to run away
                System.out.println("The NPC hits you for 1 damage as you try to run away!");
            } else {
                System.out.println("You successfully ran away!");
            }
        }
    }

    private int roll20SidedDie() {
        return new Random().nextInt(20) + 1;
    }

    private static class Room {
        private int x;
        private int y;
        private boolean blocked;
        private NPC npc;

        public Room(int x, int y, NPC npc) {
            this.x = x;
            this.y = y;
            this.blocked = new Random().nextBoolean();
            this.npc = npc;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public boolean hasNPC() {
            return npc != null && npc.isAlive();
        }

        public NPC getNpc() {
            return npc;
        }
    }

    private static class NPC {
        private int hitPoints;
        private int strength;
        private int dexterity;
        private int intelligence;

        public NPC() {
            this.hitPoints = new Random().nextInt(6) + 1;
            this.strength = (new Random().nextInt(6) + 1) * 2;
            this.dexterity = (new Random().nextInt(6) + 1) * 2;
            this.intelligence = (new Random().nextInt(6) + 1) * 2;
        }

        public boolean isAlive() {
            return hitPoints > 0;
        }

        public void takeDamage(int damage) {
            hitPoints -= damage;
        }

        public int getDexterity() {
            return dexterity;
        }

        public int getIntelligence() {
            return intelligence;
        }
    }

    private static class PlayerCharacter {
        private int hitPoints = 20;
        private int strength = roll3d6();
        private int dexterity = roll3d6();
        private int intelligence = roll3d6();
        private int totalGold = 0;

        public int getHitPoints() {
            return hitPoints;
        }

        public int getStrength() {
            return strength;
        }

        public int getDexterity() {
            return dexterity;
        }

        public int getIntelligence() {
            return intelligence;
        }

        public int getTotalGold() {
            return totalGold;
        }

        public void addGold(int gold) {
            totalGold += gold;
        }

        public void takeDamage(int damage) {
            hitPoints -= damage;
            if (hitPoints <= 0) {
                System.out.println("Game Over! You died.");
                System.exit(0);
            }
        }

        private static int roll3d6() {
            Random rand = new Random();
            return rand.nextInt(6) + rand.nextInt(6) + rand.nextInt(6) + 3;
        }
    }
}
