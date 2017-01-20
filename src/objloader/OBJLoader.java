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
    List<Float> normalCoords = new ArrayList<Float>();
    
    List<Integer> normalIndex = new ArrayList<Integer>();
    List<Integer> vertIndex = new ArrayList<Integer>();
    List<Integer> texIndex = new ArrayList<Integer>();
    
    private float tx, ty, tz; //Translate axis
    private float ra, rz, ry, rx; //Rotation angle, and axis
    private float sx, sy, sz; //Scale axis and amount
    
    public OBJLoader(String fileName) throws IOException
    {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
            
            String currentLine;
            
            while((currentLine = fileReader.readLine()) != null)
            {
                if(currentLine.charAt(0) == 'v')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    vertCoords.add(Float.valueOf(currentLineSplit[1]));
                    vertCoords.add(Float.valueOf(currentLineSplit[2]));
                    vertCoords.add(Float.valueOf(currentLineSplit[3]));
                }
                
                if(currentLine.charAt(0) == 'v' && currentLine.charAt(1) == 'n')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    normalCoords.add(Float.valueOf(currentLineSplit[1]));
                    normalCoords.add(Float.valueOf(currentLineSplit[2]));
                    normalCoords.add(Float.valueOf(currentLineSplit[3]));
                }
                
                if(currentLine.charAt(0) == 'v' && currentLine.charAt(1) == 't')
                {
                    String[] currentLineSplit = currentLine.split(" ");
                    
                    texCoords.add(Float.valueOf(currentLineSplit[1]));
                    texCoords.add(Float.valueOf(currentLineSplit[2]));
                    texCoords.add(Float.valueOf(currentLineSplit[3]));
                }
                
                
                if(currentLine.charAt(0) == 'f')
                {
                    String[] currentLineSplit = currentLine.split("/");
                    
                    texIndex.add(Integer.valueOf(currentLineSplit[0]));
                    texIndex.add(Integer.valueOf(currentLineSplit[3]));
                    texIndex.add(Integer.valueOf(currentLineSplit[6]));
                    
                    vertIndex.add(Integer.valueOf(currentLineSplit[1]));
                    vertIndex.add(Integer.valueOf(currentLineSplit[4]));
                    vertIndex.add(Integer.valueOf(currentLineSplit[6]));
                    
                    normalIndex.add(Integer.valueOf(currentLineSplit[2]));
                    normalIndex.add(Integer.valueOf(currentLineSplit[5]));
                    normalIndex.add(Integer.valueOf(currentLineSplit[6]));
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
        
        GL11.glTranslatef(tx, ty, tz);
        GL11.glRotatef(ra, rx, ry, rz);
        GL11.glScalef(sx, sy, sz);
        
        GL11.glBegin(GL11.GL_TRIANGLES);
        
        for(int i = 0; i < vertIndex.size(); i += 3)
        {
            GL11.glNormal3f(normalCoords.get(normalIndex.get(i) - 1), normalCoords.get(normalIndex.get(i + 1) - 1), normalCoords.get(normalIndex.get(i + 2) - 1));
            GL11.glTexCoord3f(texCoords.get(texIndex.get(i) - 1), texCoords.get(texIndex.get(i + 1) - 1), texCoords.get(texIndex.get(i + 2) - 1));
            GL11.glVertex3f(vertCoords.get(vertIndex.get(i) - 1), vertCoords.get(vertIndex.get(i + 1) - 1), vertCoords.get(vertIndex.get(i + 2) - 1));
        }
        
        GL11.glEnd();
        GL11.glPopMatrix();
        return this;   
    }
}
