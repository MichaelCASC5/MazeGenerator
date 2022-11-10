public class Point{
    private int x;
    private int y;

    public Point(){
        x = 0;
        y = 0;
    }
    public Point(int a, int b){
        x = a;
        y = b;
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
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}