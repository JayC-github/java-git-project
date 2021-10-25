/**
 *
 */
package unsw.automata;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Conway's Game of Life on a 10x10 grid.
 *
 * @author Robert Clifton-Everest
 *
 */
public class GameOfLife {

    private BooleanProperty[][] grid;

    public GameOfLife() {
        // TODO At the start all cells are dead
        // I'm sure the intial valus is false which means they are dead
        this.grid = new BooleanProperty[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new SimpleBooleanProperty(false);
            }
        }
    }

    // add a method with the signature
    public BooleanProperty cellProperty(int x, int y) {
        return grid[x][y];
    }

    public void ensureAlive(int x, int y) {
        // TODO Set the cell at (x,y) as alive
        grid[x][y].set(true);;

    }

    public void ensureDead(int x, int y) {
        // TODO Set the cell at (x,y) as dead
        grid[x][y].set(false);
    }

    public boolean isAlive(int x, int y) {
        // TODO Return true if the cell is alive
        return grid[x][y].get();
    }

    public void tick() {
        // TODO Transition the game to the next generation.
        boolean[][] temporary = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                temporary[i][j] = grid[i][j].get();
            }
        }

        for  (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                // live cell
                if (grid[i][j].get() == true) {
                    // underpopulation or overpopulation, cell die
                    if (neighbourAlive(i, j) < 2 || neighbourAlive(i, j) > 3) {
                        temporary[i][j] = false;
                    }
                // dead cell
                } else {
                    // exact three live neighbours, cell live
                    if (neighbourAlive(i, j) == 3) {
                        temporary[i][j] = true;
                    }
                }    
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j].set(temporary[i][j]);
            }
        }
        
    }

    // check how many neighbours of one cell alive
    public int neighbourAlive(int x, int y) {
        int alive = 0;
        
        int up = (x-1)%10;
        if (up < 0) {
            up += 10;
        }
        
        int left = (y-1)%10;
        if (left < 0) {
            left += 10;
        }

        // up left
        if (grid[up][left].get()) {
            alive++;
        }
        // up
        if (grid[up][y].get()) {
            alive++;
        }
        // up right
        if (grid[up][(y+1)%10].get()) {
            alive++;
        }
        
        // left     
        if (grid[x][left].get()) {
            alive++;
        }
        
        // right
        if (grid[x][(y+1)%10].get()) {
            alive++;
        }

        // down left
        if (grid[(x+1)%10][left].get()) {
            alive++;
        }

        // down
        if (grid[(x+1)%10][y].get()) {
            alive++;
        }

        // down right
        if (grid[(x+1)%10][(y+1)%10].get()) {
            alive++;
        }
        
        return alive;
    }


}
