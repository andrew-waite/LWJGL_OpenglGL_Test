package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import objloader.OBJLoader;

public class Main
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    
    private OBJLoader water;
    
    public void init()
    {
        water = new OBJLoader("./OBJ_FILES/water.obj");
        
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GLU.gluLookAt(0, 0.0f, 10.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
        GL11.glLoadIdentity();
    }
    
    public void run()
    {
        init();
        
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } 
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        
        while(!(Display.isCloseRequested()))
        {
            loop();
        }
        
        Display.destroy();
    }
    
    public void loop()
    {
        Display.update();
        draw();
    }
    
    public void draw()
    {
       water.setColor(255, 255, 255).renderObject();
    }
    
    public static void main(String[] args)
    {
        new Main().run();
    }

}
