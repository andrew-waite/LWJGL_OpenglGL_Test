package main;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import objloader.OBJLoader;

public class Main
{
    private static final int WIDTH = 1200;
    private static final int HEIGHT = 800;
    
    private OBJLoader water;
    
    public void pre_init()
    {
       // water = new OBJLoader("./OBJ_FILES/water.obj");
    }
    
    public void init()
    {
        pre_init();
        
        try 
        {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.create();
             
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
             
            GLU.gluPerspective(60.0f, (WIDTH / HEIGHT), 0.4f, 1000f);
             
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glDepthFunc(GL11.GL_NEAREST);
        } 
        catch (LWJGLException e) 
        {
            e.printStackTrace();
        }
    }
    
    public void run()
    {
        init();
        
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
             
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
                          
            GLU.gluLookAt(0, 0, 5, 0, 0, 0, 0, 1, 0);
             
            draw();
             
            Display.update();
        }
        
        Display.destroy();
        System.exit(-1);
    }
    
    public void draw()
    {   
        GL11.glPushMatrix();
<<<<<<< HEAD
        GL11.glLoadIdentity();  
        //GL11.glTranslatef(0f,0.0f,-7f);  
        //GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glRotatef(45f,0.0f,1.0f,0.0f);               
        GL11.glColor3f(0.5f,0.5f,1.0f);  
=======
        
        GL11.glRotatef(45, 0, 1, 0);

        GL11.glBegin(GL11.GL_TRIANGLES);
        {
            GL11.glColor3f(1.0f,0.0f,0.0f);                      // Red
            GL11.glVertex3f(0.0f, 1.0f, 0.0f);                  // Top Of Triangle (Front)
            GL11.glColor3f(0.0f,1.0f,0.0f);                      // Green
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);                  // Left Of Triangle (Front)
            GL11.glColor3f(0.0f,0.0f,1.0f);                      // Blue
            GL11.glVertex3f(1.0f,-1.0f, 1.0f);                  // Right Of Triangle (Front)
            GL11.glColor3f(1.0f,0.0f,0.0f);                      // Red
            GL11.glVertex3f(0.0f, 1.0f, 0.0f);                  // Top Of Triangle (Right)
            GL11.glColor3f(0.0f,0.0f,1.0f);                      // Blue
            GL11.glVertex3f(1.0f,-1.0f, 1.0f);                  // Left Of Triangle (Right)
            GL11.glColor3f(0.0f,1.0f,0.0f);                      // Green
            GL11.glVertex3f(1.0f,-1.0f, -1.0f);                 // Right Of Triangle (Right)
            GL11.glColor3f(1.0f,0.0f,0.0f);                      // Red
            GL11.glVertex3f(0.0f, 1.0f, 0.0f);                  // Top Of Triangle (Back)
            GL11.glColor3f(0.0f,1.0f,0.0f);                      // Green
            GL11.glVertex3f(1.0f,-1.0f, -1.0f);                 // Left Of Triangle (Back)
            GL11.glColor3f(0.0f,0.0f,1.0f);                      // Blue
            GL11.glVertex3f(-1.0f,-1.0f, -1.0f);                 // Right Of Triangle (Back)
            GL11.glColor3f(1.0f,0.0f,0.0f);                      // Red
            GL11.glVertex3f(0.0f, 1.0f, 0.0f);                  // Top Of Triangle (Left)
            GL11.glColor3f(0.0f,0.0f,1.0f);                      // Blue
            GL11.glVertex3f(-1.0f,-1.0f,-1.0f);                  // Left Of Triangle (Left)
            GL11.glColor3f(0.0f,1.0f,0.0f);                      // Green
            GL11.glVertex3f(-1.0f,-1.0f, 1.0f);                  // Right Of Triangle (Left)
>>>>>>> daa229835a957790dacac225912f468f25b61f90
             
        }
        GL11.glEnd();
         
        GL11.glPopMatrix();
        
       //GL11.glColor3f(1.0f,0.0f,0.0f);
       //water.setColor(0, 0, 255).renderObject();         
    }
    
    public static void main(String[] args) throws LWJGLException
    {
        new Main().run();
    }
}
