package freezeescape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.*;

public class GameEngine {

    public static final int GRID_SIZE = 20;

    private List<Point> trees;
    private List<Point> bushGroups;
    private List<NPC> npcs;

    private GameUI ui;
    public Random random;

    private Timer gameLoop;

    public GameEngine(GameUI ui) {

        this.ui = ui;

        this.random = new Random();
        this.trees = ui.getInnerTrees();
        this.bushGroups = ui.getInnerBushGroups();

        this.npcs = new ArrayList<>();

        // Crear NPCs REALES en base a posiciones del UI
        for (Point p : ui.getNpcPositions()) {
            npcs.add(new NPC(p.y, p.x));
        }

        // Pasar esta lista real al UI
        ui.setNPCObjects(npcs);

        // Loop → 300ms por movimiento
        gameLoop = new Timer(300, e -> update());
    }

    public void start() {
        gameLoop.start();
    }

    private void update() {
        moveNPCs();
        ui.setNPCObjects(npcs);
        ui.repaint();
    }

    private void moveNPCs() {
        for (NPC npc : npcs) {
            npc.randomWalk(this);
        }
    }

    public boolean canMoveTo(int row, int col, NPC npc) {

        if (row <= 0 || row >= GRID_SIZE - 1) return false;
        if (col <= 0 || col >= GRID_SIZE - 1) return false;

        for (Point t : trees) {
            if (t.x == col && t.y == row)
                return false;
        }

        for (NPC other : npcs) {
            if (other != npc &&
                    other.getRow() == row &&
                    other.getCol() == col) return false;
        }

        return true;
    }

    public List<NPC> getNPCs() {
        return npcs;
    }
}
