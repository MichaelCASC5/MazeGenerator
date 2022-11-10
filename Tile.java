import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.geom.Line2D;
import java.awt.Stroke;
import java.awt.BasicStroke;

public class Tile{
    private int x;
    private int y;

    private int xdisp;
    private int ydisp;

    private int scale;
    private int offset;
    private int stroke;

    private double visited_count;

    private boolean[] walls;

    public Tile(int a, int b){
        x = a;
        y = b;

        xdisp = 20;
        ydisp = 20;

        scale = 15;
        offset = 0;
        stroke = 3;

        visited_count = 0.0;

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
    public int getXDisp(){
        return xdisp;
    }
    public void setXDisp(int n){
        xdisp = n;
    }
    public int getYDisp(){
        return ydisp;
    }
    public void setYDisp(int n){
        ydisp = n;
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
    public void setStroke(int n){
        stroke = n;
    }
    public int getStroke(){
        return stroke;
    }
    public void setVisited(double n){
        visited_count = n;
    }
    public double getVisited(){
        return visited_count;
    }
    public void addVisited(int n){
        visited_count+=n;
        if(visited_count > 255){
            visited_count = 255;
        }else if(visited_count < 0){
            visited_count = 0;
        }
    }
    public String toString(){
        String output;
        output = "" + x + " " + y + " ";
        for(int i=0;i<walls.length;i++){
            output += walls[i] + " ";
        }
        return output;
    }
    /*
        * Real-time loop
    */
    public void loop(){
        visited_count-=0.01;
        if(visited_count < 0){
            visited_count = 0;
        }
    }
    /*
        * Paint Component
    */
    public void draw(Graphics g){
        /*
            * Draws user
        */
        g.setColor(new Color(255,255,255,(int)visited_count));
        g.fillRect(x*scale + xdisp,y*scale + ydisp,scale-offset,scale-offset);

        /*
            * Draws maze
        */
        Graphics2D g2 = (Graphics2D) g;

        Stroke stk = new BasicStroke(stroke);
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