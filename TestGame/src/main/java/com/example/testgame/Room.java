package com.example.testgame;

public class Room {
    private int x;
    private int y;
    private boolean blocked;
    private NPC npc;

    public Room(int x, int y, NPC npc) {
        this.x = x;
        this.y = y;
        this.blocked = false; // Adjust the default value if needed
        this.npc = npc;
    }

    public boolean hasNPC() {
        return npc != null && npc.isAlive();
    }

    public boolean isBlocked() {
        return blocked;
    }

    public NPC getNpc() {
        return npc;
    }
}
