package freezeescape;
import java.awt.*;
import javax.swing.*;
import java.math.*;
import java.util.Random;

public class NPC implements Person {
    private int row;
    private int col;
    private Direction direction = Direction.DOWN;

    public NPC(int row, int col){
        this.row = row;
        this.col = col;
        this.direction = Direction.DOWN;
    }

    public int getRow() { return row;}
    public int getCol() {return col;}
    public Direction getDirection() { return direction;}

    public boolean unfreze(){
        return false;
    }
    public boolean freezeStatus(){
        return false;
    }
    public boolean viewStatus(){
        return false;
    }
    public boolean isWalkable(){
        return false;
    }
    public void randomWalk(GameEngine engine) {

        // 4 direcciones posibles: up, down, left, right
        int[][] moves = {
                { 0, -1 },   // UP
                { 0,  1 },   // DOWN
                { -1, 0 },   // LEFT
                { 1,  0 }    // RIGHT
        };

        int dirIndex = engine.random.nextInt(4);

        int dx = moves[dirIndex][0];
        int dy = moves[dirIndex][1];

        int newCol = col + dx;
        int newRow = row + dy;

        // Actualizar la dirección según el movimiento elegido
        switch (dirIndex) {
            case 0 -> direction = Direction.UP;
            case 1 -> direction = Direction.DOWN;
            case 2 -> direction = Direction.LEFT;
            case 3 -> direction = Direction.RIGHT;
        }

        // Verificar si se puede mover
        if (engine.canMoveTo(newRow, newCol, this)) {
            col = newCol;
            row = newRow;
        }
        // Si no puede moverse, mantiene posición pero sí cambia dirección
    }
}
