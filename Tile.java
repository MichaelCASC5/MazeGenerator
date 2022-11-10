import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Line2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class Tile{
    private int x;
    private int y;

    private int xdisp = 20;
    private int ydisp = 20;

    private int scale = 15;
    private int offset = 0;

    private int visited_count = 0;

    private boolean[] walls;

    public Tile(){
        x = 0;
        y = 0;

        walls = new boolean[4];
    }
    public Tile(int a, int b){
        x = a;
        y = b;

        walls = new boolean[4];

        for(int i=0;i<walls.length;i++){
            walls[i] = true;
        }
    }
    public int getX(){
        return x;
    }
    public void setX(int n){
        x = n;
    }
    public int getY(){
        return y;
    }
    public void setY(int n){
        y = n;
    }
    public boolean getWall(int n){
        return walls[n];
    }
    public void setWall(int wallnum, boolean b){
        walls[wallnum] = b;
    }
    public void setScale(int n){
        scale = n;
    }
    public int getScale(){
        return scale;
    }
    public void addVisited(int n){
        if(visited_count < 255)
            visited_count+=n;
    }
    public String toString(){
        String output;
        output = "" + x + " " + y + " ";
        for(int i=0;i<walls.length;i++){
            output += walls[i] + " ";
        }
        return output;
    }
    public void draw(Graphics g){
        g.setColor(new Color(255,255,255,visited_count));
        g.fillRect(x*scale + xdisp,y*scale + ydisp,scale-2,scale-2);

        Graphics2D g2 = (Graphics2D) g;

        Stroke stk = new BasicStroke(3);
        g2.setStroke(stk);

        g.setColor(new Color(100,100,100));
        if(walls[0]){
            g.drawLine(x*scale + xdisp,y*scale + ydisp,x*scale+(scale-offset) + xdisp,y*scale + ydisp);
        }
        if(walls[1]){
            g.drawLine(x*scale+(scale-offset) + xdisp,y*scale + ydisp,x*scale+(scale-offset) + xdisp,y*scale+(scale-offset) + ydisp);
        }
        if(walls[2]){
            g.drawLine(x*scale + xdisp,y*scale+(scale-offset) + ydisp,x*scale+(scale-offset) + xdisp,y*scale+(scale-offset) + ydisp);
        }
        if(walls[3]){
            g.drawLine(x*scale + xdisp,y*scale + ydisp,x*scale + xdisp,y*scale+(scale-offset) + ydisp);
        }
    }
}