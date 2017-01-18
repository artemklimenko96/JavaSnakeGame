package main;


public class Constants {
    //size of a cell
    public static final int CELL_SIZE = 32;
    //size of the game field in terms of cells
    public static final int CELLS_COUNT_X = 20;
    public static final int CELLS_COUNT_Y = 20;
    //chance of getting apples on start in %
    //spawns 3-5 apples
    public static final INITIAL_SPAWN_CHANCE = 1;
    //fps
    public static final int FPS = 5;
    //window constants
    public static final int SCREEN_WIDTH =CELLS_COUNT_X*CELL_SIZE;
    public static final int SCREEN_HEIGHT = CELLS_COUNT_Y*CELL_SIZE;
    public static final String SCREEN_NAME = "Snake Game";
}
