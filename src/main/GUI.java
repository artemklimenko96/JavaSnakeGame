package main;

import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class GUI {
    //Storing current state of the cells
    private static Cell [][] cells;

    //Drawing the cells
    public static void draw(){
    //clears screen from the old image
        glClear(GL_COLOR_BUFFER_BIT);

        for(Cell[] line:cells){
            for(Cell cell:line){
                drawElement(cell);
            }
        }
    }

    private static void drawElement(Cell elem){
        //if cell doesn't have a sprite assigned, no need to draw it
        if(elem.getSprite() == null) return;
        //else - drawing a sprite for the cell
        else {
            elem.getSprite().getTexture().bind();

            glBegin(GL_QUADS);
            glTexCoord2f(0,0);
            glVertex2f(elem.getX(),elem.getY()+elem.getHeight());
            glTexCoord2f(1,0);
            glVertex2f(elem.getX()+elem.getWidth(),elem.getY()+elem.getHeight());
            glTexCoord2f(1,1);
            glVertex2f(elem.getX()+elem.getWidth(), elem.getY());
            glTexCoord2f(0,1);
            glVertex2f(elem.getX(), elem.getY());
            glEnd();
        }
    }
    //Method is called outside
    public static void update(boolean have_to_decrease){
        updateOpenGL();
        for(Cell[] line:cells){
            for(Cell cell:line){
                cell.update(have_to_decrease);
            }
        }
    }
    //private method
    private static void updateOpenGL(){
        Display.update();
        Display.sync(FPS);
    }
}
