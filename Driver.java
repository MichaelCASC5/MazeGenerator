import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Driver extends JComponent implements KeyListener, MouseListener, MouseMotionListener{
    private int WIDTH;
    private int HEIGHT;

    private Maze maze;
    
    public Driver(){
        WIDTH = 1250;//1250
        HEIGHT = 650;//650

        //Initializing maze class
        maze = new Maze();
        
        //Setting up the GUI
        JFrame gui = new JFrame();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Maze Generator");
        gui.setPreferredSize(new Dimension(WIDTH + 5, HEIGHT + 30));
        gui.setResizable(false);
        gui.getContentPane().add(this);
        
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setVisible(true);
        gui.addKeyListener(this);
        gui.addMouseListener(this);
        gui.addMouseMotionListener(this);
    }
    public void paintComponent(Graphics g){
        g.setColor(new Color(25,25,25));
        g.fillRect(0,0,WIDTH,HEIGHT);
        maze.draw(g);
    }
    public void loop(){
        repaint();
    }
    public void keyPressed(KeyEvent e){
        int k = e.getKeyCode();

        if(k == 32){
            maze = new Maze();
        }else if(k == 27){
            System.exit(0);
        }

        else if(k == 38){
            maze.user_up();
        }else if(k == 37){
            maze.user_left();
        }else if(k == 40){
            maze.user_down();
        }else if(k == 39){
            maze.user_right();
        }
    }
    public void keyReleased(KeyEvent e){
    }
    public void keyTyped(KeyEvent e){
    }
    public void mousePressed(MouseEvent e){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseClicked(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    public void mouseMoved(MouseEvent e){
    }
    public void mouseDragged(MouseEvent e){
    }
    public void start(final int ticks){
        Thread gameThread = new Thread(){
            public void run(){
                while(true){
                    loop();
                    try{
                        Thread.sleep(1000 / ticks);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        };	
        gameThread.start();
    }
    public static void main(String[] args){
        Driver g = new Driver();
        g.start(60);
    }
}
