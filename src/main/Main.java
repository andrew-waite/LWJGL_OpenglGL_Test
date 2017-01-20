package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import objloader.OBJLoader;

public class Main
{
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    
    private OBJLoader water;
    
    public void init()
    {
        
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
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glVertex2f(0,0);
        GL11.glVertex2f(0.5f,1);
        GL11.glVertex2f(1,0);
        GL11.glEnd();
        GL11.glPopMatrix();
    }
    
    public static void main(String[] args)
    {
        new Main().run();
    }

}
