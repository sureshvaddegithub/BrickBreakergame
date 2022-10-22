/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myapp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Admin
 */
public class MapGenerator {
    public int brickwidth;
    public int brickheight;
    int[][] map;
    public MapGenerator(int row,int col){
        map = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j] = 1;
            }
        }
        brickwidth = 540/col;
        brickheight = 150/row;
    }
    
    public void draw(Graphics2D g){
        for(int i =0;i<map.length ;i++){
            for(int j=0; j<map[0].length;j++){
                if(map[i][j]==1){
                    g.setColor(Color.white);
                    g.fillRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                    
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickwidth+80, i*brickheight+50, brickwidth, brickheight);
                }
            }
        }
    }
    public void setbrick(int val,int row,int col){
        map[row][col]=val;
    }
}
