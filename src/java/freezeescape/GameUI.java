package freezeescape;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameUI extends JPanel {

    private Image background;
    private Image tree;
    private Image bushGroup;
    private Image npcFront;
    private Image npcBack;
    private Image npcLeft;
    private Image npcRight;

    private final int GRID_SIZE = 20;

    private List<Point> innerTrees = new ArrayList<>();
    private List<Point> innerBushGroup = new ArrayList<>();
    private List<Point> npcInitialPositions = new ArrayList<>();

    private List<NPC> npcObjects = new ArrayList<>(); // NPCs reales (del engine)

    private Random random = new Random();

    public GameUI() {

        background = new ImageIcon("src/resources/Background.png").getImage();
        tree = new ImageIcon("src/resources/Tree.png").getImage();
        bushGroup = new ImageIcon("src/resources/Bushes.png").getImage();
        npcFront = new ImageIcon("src/resources/NPC_front.png").getImage();
        npcBack  = new ImageIcon("src/resources/NPC_back.png").getImage();
        npcLeft  = new ImageIcon("src/resources/NPC_left.png").getImage();
        npcRight = new ImageIcon("src/resources/NPC_right.png").getImage();

        generateRandomInnerTrees(5);
        generateRandomInnerBushGroup(5);
        generateRandomNPCPositions(4);
    }

    // ============================================
    // DIBUJO PRINCIPAL
    // ============================================
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // ACTIVAR CALIDAD ALTA
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int tileWidth = getWidth() / GRID_SIZE;
        int tileHeight = getHeight() / GRID_SIZE;

        // Usar g2 en vez de g
        g2.drawImage(background, 0, 0, getWidth(), getHeight(), this);

        drawBorderTrees(g2, tileWidth, tileHeight);
        drawInnerTrees(g2, tileWidth, tileHeight);
        drawBushGroups(g2, tileWidth, tileHeight);
        drawNPCs(g2, tileWidth, tileHeight);
    }

    // ============================================
    // DIBUJO DE OBJETOS
    // ============================================
    private void drawBorderTrees(Graphics g, int w, int h) {
        for (int col = 0; col < GRID_SIZE; col++) {
            g.drawImage(tree, col * w, 0, w, h, this);
            g.drawImage(tree, col * w, (GRID_SIZE - 1) * h, w, h, this);
        }

        for (int row = 0; row < GRID_SIZE; row++) {
            g.drawImage(tree, 0, row * h, w, h, this);
            g.drawImage(tree, (GRID_SIZE - 1) * w, row * h, w, h, this);
        }
    }

    private void drawInnerTrees(Graphics g, int w, int h) {
        for (Point p : innerTrees) {
            g.drawImage(tree, p.x * w, p.y * h, w, h, this);
        }
    }

    private void drawBushGroups(Graphics g, int w, int h) {
        for (Point p : innerBushGroup) {
            g.drawImage(bushGroup, p.x * w, p.y * h, w * 2, h * 2, this);
        }
    }

    private void drawNPCs(Graphics2D g, int w, int h) {

        for (NPC npc : npcObjects) {

            Image sprite = switch (npc.getDirection()) {
                case UP    -> npcBack;
                case DOWN  -> npcFront;
                case LEFT  -> npcLeft;
                case RIGHT -> npcRight;
            };

            g.drawImage(
                    sprite,
                    npc.getCol() * w,
                    npc.getRow() * h,
                    w,
                    h,
                    this
            );
        }
    }

    // ============================================
    // GETTERS PARA GAMEENGINE
    // ============================================
    public List<Point> getInnerTrees() {
        return innerTrees;
    }

    public List<Point> getInnerBushGroups() {
        return innerBushGroup;
    }

    public List<Point> getNpcPositions() {
        return npcInitialPositions;
    }

    // ============================================
    // SETTER PARA NPCs reales
    // ============================================
    public void setNPCObjects(List<NPC> npcs) {
        this.npcObjects = npcs;
    }

    // ============================================
    // GENERADORES DE MAPA
    // ============================================
    private void generateRandomInnerTrees(int count) {

        while (innerTrees.size() < count) {
            int row = 1 + random.nextInt(GRID_SIZE - 2);
            int col = 1 + random.nextInt(GRID_SIZE - 2);

            Point p = new Point(col, row);

            if (!innerTrees.contains(p)) {
                innerTrees.add(p);
            }
        }
    }

    private void generateRandomInnerBushGroup(int count) {
        while (innerBushGroup.size() < count) {

            int col = 1 + random.nextInt(GRID_SIZE - 4);
            int row = 1 + random.nextInt(GRID_SIZE - 4);

            Point p = new Point(col, row);

            if (!innerBushGroup.contains(p)) {
                innerBushGroup.add(p);
            }
        }
    }

    private void generateRandomNPCPositions(int count) {

        while (npcInitialPositions.size() < count) {

            int col = 1 + random.nextInt(GRID_SIZE - 2);
            int row = 1 + random.nextInt(GRID_SIZE - 2);

            Point p = new Point(col, row);

            if (npcInitialPositions.contains(p)) continue;

            boolean onTree = false;
            for (Point t : innerTrees) {
                if (t.equals(p)) {
                    onTree = true;
                    break;
                }
            }
            if (onTree) continue;

            npcInitialPositions.add(p);
        }
    }
}
