/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class GamePlay extends JPanel  implements ActionListener,KeyListener{
    
    private boolean play =false;
    private int totalbricks= 21;
    private Timer timer;
    private int score = 0;
   private  int playerX = 350;
   private int ballposX = 120;
   private int ballposY = 350;
   private int balldirX = -2;
   private int balldirY = -3;
   private int delay = 8;
   MapGenerator map;
   
   
    public GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        //setFocusTraversalKeysEnabled(true);
        map =new MapGenerator(3,7);
        
        timer =new Timer(delay,this);
        timer.start();
        
    }
    public void paint(Graphics g){
       g.setColor(Color.BLACK);
       g.fillRect(1,1,692,592);
       
        map.draw((Graphics2D) g);
       
       g.setColor(Color.YELLOW);
       g.fillRect(0, 0, 692, 3);
       g.fillRect(0, 3, 3, 592);
       g.fillRect(682, 3, 3, 592);
       
       
       g.setColor(Color.RED);
       g.fillRect(playerX, 550,100,8);
       
       g.setColor(Color.green);
       g.fillOval(ballposX, ballposY, 20, 20);
       
        g.setColor(Color.ORANGE);
           g.setFont(new Font("Arail",Font.BOLD,20));
           g.drawString("Score :"+score, 550, 30);
       
       
       if(ballposY>570){
           play=false;
           balldirX = 0;
           balldirY = 0;
           g.setColor(Color.red);
           g.setFont(new Font("Arail",Font.BOLD,30));
           g.drawString(" Game over Score :"+score, 200, 350);
           g.setFont(new Font("Arail",Font.BOLD,30));
           g.drawString("press ENTER to restart", 200, 400);
           
       }
       if(totalbricks<=0){
           play=false;
           balldirX = 0;
           balldirY = 0;
           g.setColor(Color.green);
           g.setFont(new Font("Arail",Font.BOLD,30));
           g.drawString(" YOU WON!! :"+score, 200, 350);
           g.setFont(new Font("Arail",Font.BOLD,30));
           g.drawString("press ENTER to restart", 200, 400);
           
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(play){
            if(ballposX <=0){
                balldirX = -balldirX;
            }
            if(ballposX >=670){
                balldirX = -balldirX;
            }
            if(ballposY <= 0){
                balldirY = -balldirY;
            }
            Rectangle ovalrect =new Rectangle(ballposX,ballposY,20,20);
            Rectangle paddelerect = new Rectangle(playerX,550,100,8);
            if(ovalrect.intersects(paddelerect)){
                balldirY = -balldirY;
            }
            A:for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                       
                        
                        int width = map.brickwidth;
                        int height =map.brickheight;
                        int posX=j*map.brickwidth+80;
                        int posY = i* map.brickheight+50;
                        Rectangle rect=new Rectangle(posX,posY,width,height);
                        if(ovalrect.intersects(rect)){
                           map.setbrick(0, i, j); 
                           totalbricks--;
                          score+=5;
                        if(ballposX+19<=posX || ballposX+1>posX+width){
                            balldirX= -balldirX;
                        }
                        else{
                            balldirY = -balldirY;
                        }
                    
                            break A;
                        }
                }
            }
            }
            ballposX += balldirX;
            ballposY +=balldirY;
        }
        repaint();
    }
    

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()== KeyEvent.VK_LEFT){
            if(playerX<=0){
                playerX = 0;
            }
            else
                moveLeft();
        }
        if(e.getKeyCode()== KeyEvent.VK_RIGHT){
            if(playerX>=600)
                playerX = 600;
            else
                moveRight();
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            if(!play){
                  score = 0;
                  playerX = 350;
                  ballposX = 120;
                  ballposY = 350;
                   balldirX = -2;
                   balldirY = -3;
                   totalbricks = 21;
                   map=new MapGenerator(3,7);
            }
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
       
    }
    private void moveLeft(){
        play =true;
        playerX -= 20;
    }
    private void moveRight(){
        play =true;
        playerX +=20;
        
    }
    
}
