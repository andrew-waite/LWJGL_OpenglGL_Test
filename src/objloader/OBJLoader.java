package objloader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class OBJLoader
{
    List<Float> texCoords = new ArrayList<Float>();
    List<Float> vertCoords = new ArrayList<Float>();
    List<Float> faceCoords = new ArrayList<Float>();
    
    private float tx, ty, tz; //Translate axis
    private float ra, rz, ry, rx; //Rotation angle, and axis
    private float sx, sy, sz; //Scale axis and amount
    
    public OBJLoader(String fileName) throws IOException
    {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            
            String currentLine;
            
            while((currentLine = fileReader.readLine()) != null)
            {
                if(currentLine.charAt(0) == 'f')
                {
                    String[] currentLineSplit = currentLine.split("/");
                    
                    texCoords.add(Float.valueOf(currentLineSplit[0]));
                    texCoords.add(Float.valueOf(currentLineSplit[3]));
                    texCoords.add(Float.valueOf(currentLineSplit[6]));
                    
                    vertCoords.add(Float.valueOf(currentLineSplit[1]));
                    vertCoords.add(Float.valueOf(currentLineSplit[4]));
                    vertCoords.add(Float.valueOf(currentLineSplit[6]));
                    
                    faceCoords.add(Float.valueOf(currentLineSplit[2]));
                    faceCoords.add(Float.valueOf(currentLineSplit[5]));
                    faceCoords.add(Float.valueOf(currentLineSplit[6]));
                }
            }
            
            fileReader.close();
    }
    
    public OBJLoader rotate(float rotationAngle, float x, float y, float z)
    {
        this.ra = rotationAngle;
        this.rx = x;
        this.ry = y;
        this.rz = z;
        
        return this;
    }
    
    public OBJLoader setColor(int r, int g, int b)
    {
        GL11.glColor3f(r, g, b);
        return this;
    }
    
    public OBJLoader translate(float x, float y, float z)
    {
        this.tx = x;
        this.ty = y;
        this.tz = z;
        
        return this;
    }
    
    public OBJLoader scale(float x, float y, float z)
    {
        this.sx = x;
        this.sy = y;
        this.sz = z;
        
        return this;
    }
    
    public OBJLoader renderObjects()
    {
        GL11.glPushMatrix();
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        for(int i = 0; i < faceCoords.size(); i += 3)
        {
            GL11.glVertex3f(faceCoords.get(i), faceCoords.get(i + 1), faceCoords.get(i + 2));
            GL11.glTexCoord3f(texCoords.get(i), texCoords.get(i + 1), texCoords.get(i + 2));
            GL11.glNormal3f(vertCoords.get(i), vertCoords.get(i + 1), vertCoords.get(i + 2));
        }
        
        GL11.glEnd();
        GL11.glPopMatrix();
        return this;   
    }
}
