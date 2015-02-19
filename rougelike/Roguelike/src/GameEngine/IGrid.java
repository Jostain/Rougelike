package GameEngine;

import java.awt.List;
import java.util.ArrayList;

import Renderer.GameField;


public interface IGrid {
   
	public ArrayList<IEntity> getEntities(int x, int y);
	public ArrayList<IEntity> getEntitiesLookAhead(int x, int y, int direction);
    public boolean addEntity(int x, int y, IEntity entity);
    public boolean removeEntity(IEntity entity);
    public boolean moveEntity(IEntity entity , int x, int y);
    public boolean walkEntity(IEntity entity, int i);
    public ArrayList<Integer> getPath(int fromX, int fromY, int toX, int toY);
    public void setTileType(int x, int y, int type);
    public int getTileType(int x, int y);
    public boolean isWalkable(int x, int y);
    public void setWalkable(int x, int y, boolean walkable);
    public boolean isVisible(int x, int y);
    public void setVisible(int x, int y, boolean visible);  
    public void updatePaths();
    public void customPath(int edge, int fromY, int fromX, int toX, int toY);
	public boolean pathExists(int i, int j, int k, int l);
	public void setWalkableSquare(int firstX,int firstY,int secondX,int secondY);
	public void endTurn();
	public void addWorld(GameField gameField);
	public GameField getGraphics();
	public ArrayList<IEntity> getEntities();
}
