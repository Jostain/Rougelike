/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import javax.swing.*;

import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.*;

import GameEngine.IEntity;
import GameEngine.IGrid;
/**
 *
 * @author Erik
 */
class Cell extends JPanel{

    private static final ImageIcon spriteSheet = new ImageIcon(Cell.class.getResource("spritesheet.png"));
    private static final ImageIcon texture = new ImageIcon(Cell.class.getResource("texturepack.png"));
   // private static final ImageIcon voidTexture = new ImageIcon(Cell.class.getResource("texturepack.png"));

   
    private ImageIcon imageIcon = new ImageIcon(texture.getImage());
    private int x;
    private int y;
    IGrid grid;
    private static int offsetX;
    private static int offsetY;

    protected Cell(int x, int y, IGrid grid) {
        this.grid = grid;
        setLayout(null);
        setBounds(x * 32, y * 32, 32, 32);

        this.x = x;
        this.y = y;

        //renderSprites(grid);
    }

    private void updateCell() {
    	try{
        imageIcon = texture;
        ArrayList<IEntity> entities = grid.getEntities(x + getOffsetX(), y + getOffsetY());
        int textureID = grid.getTileType(x + getOffsetX(), y + getOffsetY());
        
        if (entities == null || textureID == -1 || !(grid.isVisible(x + getOffsetX(), y + getOffsetY()))) {
        	
        } else {
        	

            Iterator<IEntity> itr = entities.iterator();
           
            Image image1 = texture.getImage();
            Image image2 = spriteSheet.getImage();
            Image image = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
            Graphics g2 = image.getGraphics();
            g2.drawImage(image1, 0, -(textureID*32), null);

            while (itr.hasNext()) {
                 int spriteID = itr.next().getSprite();
                g2.drawImage(image2, 0, -(spriteID*32), null);
            }
            g2.dispose();
            imageIcon = new ImageIcon(image);
        }
        }
    	catch(NullPointerException e)
    	{
    		
    	}
    }

    public static void setOffset(int x, int y) {
        offsetX = x;
        offsetY = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateCell();
        g.drawImage(imageIcon.getImage(), 0, 0, this);

    }
    public static int getOffsetX() {
        return offsetX;
    }
    public static int getOffsetY() {
        return offsetY;
    }
}
