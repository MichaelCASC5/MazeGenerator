import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.util.ArrayList;

public class Maze{
    private int posx;
    private int posy;

    private int resx;
    private int resy;

    private boolean build;

    ArrayList<Point> path;
    Tile[][] maze;

    Point user;
    /*
        * Default Constructor
        * Also calls setup() function to build maze
    */
    public Maze(){
        posx = 0;
        posy = 0;

        resx = 80;
        resy = 40;

        build = true;

        path = new ArrayList<>();
        maze = new Tile[resx][resy];

        user = new Point(0,0);
        setup();
    }
    /*
        * Accessor and mutator methods
    */
    public int getPosX(){
        return posx;
    }
    public void setPosX(int n){
        posx = n;
    }
    public int getPosY(){
        return posy;
    }
    public void setPosY(int n){
        posy = n;
    }
    public int getResX(){
        return resx;
    }
    public void setResX(int n){
        resx = n;
    }
    public int getResY(){
        return resy;
    }
    public void setResY(int n){
        resy = n;
    }
    public boolean getBuild(){
        return build;
    }
    public void setBuild(boolean b){
        build = b;
    }
    public String toString(){
        return "" + posx + " " + posy + " " + build;
    }


    /* * ===== MAZE BUILDING METHODS ===== */

    /*
        * Builds the maze.
    */
    public void setup(){
        System.out.println("Generating maze...");

        //Setting up maze
        int r = -1;
        int saver = -1;
        int size = 0;
        int maze_size = resx * resy;

        Tile save_back_tile = new Tile(posx,posy);
        Tile tile;
        tile = new Tile(posx,posy);

        boolean back = false;
        while(build){

            r = (int)(Math.random()*4);

            if(size == maze_size){
                build = false;
                break;
            }
            
            int[] integer = new int[1];
            integer[0] = r;

            if(!stuck(posx,posy)){
                if(!tileBlocked(r,posx,posy)){
                    tile = new Tile(posx,posy);

                    tile.setWall(r,false);
                    tile.setWall((saver+2)%4,false);
                    tile = edgeWall(tile);

                    saver = r;
                    if(!back){
                        path.add(new Point(posx,posy));
                        maze[posx][posy] = tile;
                        size++;
                    }else{
                        save_back_tile.setWall(r,false);
                    }
                    back = false;

                    if(r == 0){
                        posy = posy - 1;
                    }else if(r == 1){
                        posx = posx + 1;
                    }else if(r == 2){
                        posy = posy + 1;
                    }else if(r == 3){
                        posx = posx - 1;
                    }
                }
            }else{
                tile = new Tile(posx,posy);

                tile.setWall((r+2)%4,false);
                tile.setWall((saver+2)%4,false);
                tile = edgeWall(tile);

                saver=r;

                path.add(new Point(posx,posy));
                maze[posx][posy] = tile;
                size++;

                int trackx,tracky;
                for(int i=0;i<path.size();i++){
                    trackx = path.get(i).getX();
                    tracky = path.get(i).getY();

                    if(!stuck(trackx,tracky)){
                        posx = maze[trackx][tracky].getX();
                        posy = maze[trackx][tracky].getY();

                        save_back_tile = maze[trackx][tracky];

                        back = true;
                        break;
                    }
                }
            }
        }

        path.removeAll(path);

        System.out.println("Finished Generating " + maze_size + " tiles.");
    }
    /*
        * Ensures the edges of the maze will be walled off
    */
    public Tile edgeWall(Tile tile){
        if(tile.getX() == 0){
            tile.setWall(3,true);
        }else if(tile.getX() == resx - 1){
            tile.setWall(1,true);
        }

        if(tile.getY() == 0){
            tile.setWall(0,true);
        }else if(tile.getY() == resy - 1){
            tile.setWall(2,true);
        }
        
        return tile;
    }
    /*
        * Checks if the current x and y position of the user is stick
        * It was call the tileBlocked function and check every wall
    */
    public boolean stuck(int tilex, int tiley){
        boolean output = true;
        for(int i=0;i<4;i++){
            if(!tileBlocked(i,tilex,tiley)){
                output = false;
                break;
            }
        }
        return output;
    }
    /*
        * Uses the future direction of movement to check if the position will be stuck
        * int r is the chosen random direction of expansion
    */
    public boolean tileBlocked(int r, int tilex, int tiley){
        boolean output = false;

        int ax = 0;
        int ay = 0;

        if(r == 0){
            ay = -1;
        }else if(r == 1){
            ax = 1;
        }else if(r == 2){
            ay = 1;
        }else if(r == 3){
            ax = -1;
        }

        if(tilex + ax < 0 || tilex + ax >= resx || tiley + ay < 0 || tiley + ay >= resy){
            output = true;
        }else if(maze[tilex + ax][tiley + ay] != null){//&&build
            output = true;
        }

        return output;
    }
    public boolean canVisit(int dir, int tilex, int tiley){
        boolean output = true;
        Tile tile = maze[tilex][tiley];

        if(tile.getWall(dir)){
            return false;
        }

        int ax = 0;
        int ay = 0;

        if(dir == 0){
            ay = -1;
        }else if(dir == 1){
            ax = 1;
        }else if(dir == 2){
            ay = 1;
        }else if(dir == 3){
            ax = -1;
        }

        if(maze[tilex + ax][tiley + ay].getWall((dir+2)%4)){
            output = false;
        }

        return output;
    }
    /*
        * Visits tiles
    */
    public void user_up(){
        if(canVisit(0,user.getX(),user.getY())){
            user.setY(user.getY()-1);
            visit_tile();
        }
    }
    public void user_left(){
        if(canVisit(3,user.getX(),user.getY())){
            user.setX(user.getX()-1);
            visit_tile();
        }
    }
    public void user_down(){
        if(canVisit(2,user.getX(),user.getY())){
            user.setY(user.getY()+1);
            visit_tile();
        }
    }
    public void user_right(){
        if(canVisit(1,user.getX(),user.getY())){
            user.setX(user.getX()+1);
            visit_tile();
        }
    }
    public void visit_tile(){
        maze[user.getX()][user.getY()].addVisited((int)(Math.random()*100) + 50);
    }
    /*
        * Draws to canvas
    */
    public void draw(Graphics g){
        for(int i=0;i<resx;i++){
            for(int j=0;j<resy;j++){
                if(maze[i][j] != null){
                    maze[i][j].draw(g);
                }
            }
        }
    }
}