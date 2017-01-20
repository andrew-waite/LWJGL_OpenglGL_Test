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
    
    public void pre_init()
    {
        water = new OBJLoader("./OBJ_FILES/water.obj");
    }
    
    public void init()
    {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GLU.gluLookAt(1, 0, 0, 0, 0, 0, 0, 1, 0);
    }
    
    public void run() throws LWJGLException
    {
        pre_init();
        
        try
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
        } 
        catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        
        init();
        
        while(!(Display.isCloseRequested()))
        {
            loop();
        }
        
        Display.destroy();
    }
    
    public void loop() throws LWJGLException
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
        GL11.glClearColor(0f, 129.0f, 255.0f, 1.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // Reset transformations
        GL11.glLoadIdentity();
        
        draw();
        Display.swapBuffers();
        Display.update();  
    }
    
    public void draw()
    {
        GL11.glColor3f(255,255,255);
       //water.setColor(255, 255, 255).renderObject();
        GL11.glBegin(GL11.GL_POLYGON);
        GL11.glVertex3f(0.5f, -0.5f, 0.5f );
        GL11.glVertex3f(0.5f,  0.5f, 0.5f );
        GL11.glVertex3f(-0.5f,  0.5f, 0.5f );
        GL11.glVertex3f(-0.5f, -0.5f, 0.5f );
        GL11.glEnd();
         
        // Purple side - RIGHT
        GL11.glBegin(GL11.GL_POLYGON);
        //glColor3f(  1.0,  0.0,  1.0 );
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f,  0.5f, -0.5f);
        GL11.glVertex3f(0.5f,  0.5f,  0.5f);
        GL11.glVertex3f(0.5f, -0.5f,  0.5f);
        GL11.glEnd();
         
        // Green side - LEFT
        GL11.glBegin(GL11.GL_POLYGON);
        //glColor3f(   0.0,  1.0,  0.0 );
        GL11.glVertex3f(-0.5f, -0.5f,  0.5f);
        GL11.glVertex3f(-0.5f,  0.5f,  0.5f);
        GL11.glVertex3f(-0.5f,  0.5f, -0.5f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glEnd();
         
        // Blue side - TOP
        GL11.glBegin(GL11.GL_POLYGON);
        //glColor3f(   0.0,  0.0,  1.0 );
        GL11.glVertex3f(0.5f,  0.5f,  0.5f);
        GL11.glVertex3f(0.5f,  0.5f, -0.5f);
        GL11.glVertex3f(-0.5f,  0.5f, -0.5f);
        GL11.glVertex3f(-0.5f,  0.5f,  0.5f);
        GL11.glEnd();
         
        // Red side - BOTTOM
        GL11.glBegin(GL11.GL_POLYGON);
        //glColor3f(   1.0,  0.0,  0.0 );
        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
        GL11.glVertex3f(0.5f, -0.5f,  0.5f);
        GL11.glVertex3f(-0.5f, -0.5f,  0.5f);
        GL11.glVertex3f(-0.5f, -0.5f, -0.5f);
        GL11.glEnd();
    }
    
    public static void main(String[] args) throws LWJGLException
    {
        new Main().run();
    }

}
