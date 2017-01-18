package main;

import static main.Constants.CELL_SIZE;

public class Cell {
    //state = 0 - empty cell
    //state > 0 - snake body in a cell, which will be in for N frames
    //state < 0 - apple in a cell (-1)
    private int x;
    private int y;
    private int state;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    public int getHeight(){
        return CELL_SIZE;
    }

    public int getWidth(){
        return CELL_SIZE;
    }


    public Cell(int x, int y, int state) {

        this.x = x;
        this.y = y;
        this.state = state;
    }
    //update cell method

    public void update(boolean have_to_decrease){
        if (have_to_decrease && this.state > 0) {
            this.state--;
        }
    }
    public  Sprite getSprite(){
        if(this.state > 0){
            //if "state" contains the snake body
            return Sprite.BODY;
        }
        else if(this.state == 0){
            //empty cell
            return null;
        }
        else{
            return Sprite.APPLE;
        }
    }
}
