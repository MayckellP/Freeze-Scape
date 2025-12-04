package freezeescape;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class GameUI extends  JPanel{
    private Image background;
    private Image tree;
    private Image bushGroup;

    private final int GRID_SIZE = 20;
    private final int TILE_SIZE = 32;

    private java.util.List<Point> innerTrees = new ArrayList<>();
    private java.util.List<Point> innerBushGroup = new ArrayList<>();
    private Random random = new Random();

    public GameUI(){
        background = new ImageIcon("src/resources/Background.png").getImage();
        tree = new ImageIcon("src/resources/Tree.png").getImage();
        bushGroup = new ImageIcon("src/resources/Bushes.png").getImage();
        generateRandomInnerTrees(5);
        generateRandomInnerBushGroup(5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int tileWidth = getWidth()/GRID_SIZE;
        int tileHeight = getHeight()/GRID_SIZE;
        super.paintComponent(g);

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        drawBorderTree(g, tileWidth, tileHeight);
        drawInnerTree(g, tileWidth, tileHeight);
        drawInnerBushGroup(g, tileWidth, tileHeight);
    }

    private void drawBorderTree(Graphics g, int tileWidth, int tileHeight){

        // ----- FILA SUPERIOR -----
        for (int col = 0; col < GRID_SIZE; col++) {
            g.drawImage(tree, col * tileWidth, 0, tileWidth, tileHeight, this);
        }

        // ----- FILA INFERIOR -----
        for (int col = 0; col < GRID_SIZE; col++) {
            g.drawImage(tree, col * tileWidth, (GRID_SIZE - 1) * tileHeight, tileWidth, tileHeight, this);
        }

        // ----- COLUMNA IZQUIERDA -----
        for (int row = 0; row < GRID_SIZE; row++) {
            g.drawImage(tree, 0, row * tileHeight, tileWidth, tileHeight, this);
        }

        // ----- COLUMNA DERECHA -----
        for (int row = 0; row < GRID_SIZE; row++) {
            g.drawImage(tree, (GRID_SIZE - 1) * tileWidth, row * tileHeight, tileWidth, tileHeight, this);
        }
    }

    private void generateRandomInnerTrees(int count){
        while (innerTrees.size() < count){
            int row = 1 + random.nextInt(GRID_SIZE - 2);
            int col = 1 + random.nextInt(GRID_SIZE - 2);

            Point p = new Point(col, row);

            if(!innerTrees.contains(p)){
                innerTrees.add(p);
            }
        }
    }

    private void drawInnerTree(Graphics g, int tileWidth, int tileHeight){
        for(Point p: innerTrees){
            int col = p.x;
            int row = p.y;

            g.drawImage(tree, col*tileWidth, row*tileHeight, tileWidth, tileHeight, this);
        }
    }

    private void generateRandomInnerBushGroup(int count){
        while (innerBushGroup.size() < count){
            int col = 1 + random.nextInt(GRID_SIZE - 4);
            int row = 1 + random.nextInt(GRID_SIZE - 4);

            Point p = new Point(col, row);

            if (!innerBushGroup.contains(p)){
                innerBushGroup.add(p);
            }
        }
    }

    private void drawInnerBushGroup(Graphics g, int tileWidth, int tileHeight){
        for(Point p: innerBushGroup){
            int col = p.x;
            int row = p.y;

            g.drawImage(bushGroup, col*tileWidth, row*tileHeight, tileWidth*2, tileHeight*2, this);
        }
    }
}
