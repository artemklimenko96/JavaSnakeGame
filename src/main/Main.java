package main;


import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.Random;

import static main.Constants.CELLS_COUNT_X;
import static main.Constants.CELLS_COUNT_Y;

public class Main{

    //A variable, closing the window
    private static boolean isExitRequested=false;

   //Snake data, it comes from bottom left corner
    //directions are calculated clockwise 0-up, 1-right, 2-down, 3-left
    private static int x=-1,y=0, direction=1, length=3;

   //flag which turns to false if a snake has eaten something
    private static boolean have_to_decrease = true;


    public static void main(String[] args) {
        //Initializing GUI
        GUI.init();

        //Generating a fruit at a random pace
        generate_new_obj();

        //while we don't get a closing trigger
        while(!isExitRequested){
            //checking keyboard input
            input();

            move();

            GUI.draw();
            GUI.update(have_to_decrease);

        }
    }

    private static void move() {
        //if at last frame we have eaten something, we set the value back
        have_to_decrease=true;

        //changing the snake's coordinates depending on the direction
        switch(direction){
            case 0:
                y++; break;
            case 1:
                x++; break;
            case 2:
                y--; break;
            case 3:
                x--; break;
        }

        //border check
        if(x < 0 || x >= CELLS_COUNT_X || y < 0 || y >= CELLS_COUNT_Y){
            System.exit(1);
        }

        //checking current cell's state
        int next_cell_state = GUI.getState(x,y);

        //if it contains the snake's body ==> game over!
        if(next_cell_state>0){
            System.exit(1);

        }else{
            //if it contains food
            if(next_cell_state < 0){
                length++;
                generate_new_obj(); //creating new food
                have_to_decrease=false; //we have eaten something
            }

            //putting snake's body to the cell
            GUI.setState(x,y,length);
        }
    }



/*Food generation algorithm is as follows
The amount of empty cells (without snake body) is calculated using the following formula:
CELLS_COUNT_X*CELLS_COUNT_Y-length
We select a random cell and save the number to "point"
then we iterate through all the cells, and if the cell has no snakebody, we decrease the counter
*/

    private static void generate_new_obj() {
        int point = new Random().nextInt(CELLS_COUNT_X*CELLS_COUNT_Y-length);

        for(int i=0; i<CELLS_COUNT_X; i++){
            for(int j=0; j<CELLS_COUNT_Y; j++){
                if(GUI.getState(i,j) <= 0) {
                    if (point == 0) {
                        GUI.setState(i, j, -1); //TODO randomize objects
                        return;
                    }else {
                        point--;
                    }
                }
            }
        }

    }


    private static void input(){
        ///Перебираем события клавиатуры

        int newDirection = direction;

        while(Keyboard.next()){
            if(Keyboard.getEventKeyState()){
                switch(Keyboard.getEventKey()) {

                    case Keyboard.KEY_ESCAPE:
                        isExitRequested = true;
                        break;
                    case Keyboard.KEY_UP:
                        if(direction!=2) newDirection=0;
                        break;
                    case Keyboard.KEY_RIGHT:
                        if(direction!=3) newDirection=1;
                        break;
                    case Keyboard.KEY_DOWN:
                        if(direction!=0) newDirection=2;
                        break;
                    case Keyboard.KEY_LEFT:
                        if(direction!=1) newDirection=3;
                        break;
                }
            }
        }

        direction = newDirection; //this is used to forbid a "backturn" (turning into itself)

        //if user clicks "close"
        isExitRequested=isExitRequested || Display.isCloseRequested();
    }

}
