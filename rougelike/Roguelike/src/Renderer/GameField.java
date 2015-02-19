/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Renderer;

import javax.swing.*;
import java.awt.*;
import GameEngine.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Erik
 */
public class GameField extends JPanel {

    private IGrid grid;
    private final HashMap<Integer, HashMap<Integer,Cell>> matrix = new HashMap<>();

    public GameField(IGrid grid) {
        
        setLayout(null);
        Dimension d = new Dimension(1024,512);
	setPreferredSize(d);
	setMaximumSize(d);
	setMinimumSize(d);
        
        
        this.grid = grid;
        for(int y = 0;y < 16; y++)
        {
            matrix.put(y ,new HashMap<>());
             for(int x = 0;x < 32; x++)
            {
              Cell cell = new Cell(x,y,grid);
              matrix.get(y).put(x, cell);
              add(cell);
            }
        }
         grid.addWorld(this);  
    }
    public void CenterOnCoordinate(int x,int y)
    {
        Cell.setOffset(x, y);
        repaint();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

    }
}
        
         
        
    

