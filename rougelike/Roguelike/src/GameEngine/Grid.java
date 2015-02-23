package GameEngine;

import graphs.Edge;
import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import Renderer.*;

public class Grid implements IGrid {
	private final int sizeX;
	private final int sizeY;
	private HashMap<Integer, HashMap<Integer, Tile>> tileMap = new HashMap<>();
	private ArrayList<IEntity> entities = new ArrayList<>(); 
	private int turnCounter = 0;
	private GameField graphics;
	private int currentActor = 0;

	public Grid(int sizeX, int sizeY) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		for (int y = 0; y < sizeY; y++) {
			HashMap<Integer, Tile> Y = new HashMap<Integer, Tile>();
			for (int x = 0; x < sizeX; x++) {
				Tile tile = new Tile(x, y);
				Y.put(x, tile);
			}
			tileMap.put(y, Y);
		}
		
	}

	@Override
	public ArrayList<IEntity> getEntities(int x, int y) {

		return tileMap.get(y).get(x).entities;
	}
	public void addWorld(GameField g)
	{
		this.graphics = g;
	}
	@Override
	public ArrayList<IEntity> getEntitiesLookAhead(int x, int y, int edge) {

		if (edge == 0 && tileMap.get(y).get(x).North != null) {
			return tileMap.get(y).get(x).North.entities ;
		} else if (edge == 1 && tileMap.get(y).get(x).East != null) {
			return tileMap.get(y).get(x).East.entities ;
		} else if (edge == 2 && tileMap.get(y).get(x).South != null) {
			return tileMap.get(y).get(x).South.entities ;
		} else if (edge == 3 && tileMap.get(y).get(x).West != null) {
			return tileMap.get(y).get(x).West.entities ;
		}
		else 
		{
			return null;
		}
		
	}

	@Override
	public int getTileType(int x, int y) {
		if (x < sizeX && y < sizeY) {
			return tileMap.get(y).get(x).tileType;
		} else {
			return -1;
		}

	}

	@Override
	public boolean addEntity(int x, int y, IEntity entity) {
		if (x <= sizeX && y <= sizeY) {
			if (getEntities().contains(entity)) {
				return false;
			} else {
				getEntities().add(entity);
				tileMap.get(y).get(x).entities.add(entity);
				entity.setXY(x, y);
				return true;
			}

		} else {
			return false;
		}

	}

	@Override
	public boolean removeEntity(IEntity entity) {
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				if (tileMap.get(y).get(x).entities.contains(entity)) {
					getEntities().remove(entity);
					return tileMap.get(y).get(x).entities.remove(entity);
				}

			}
		}
		return false;
	}
	public int act(int i)
	{
		IEntity actor = entities.get(currentActor);
		if(actor.act(i) == 0)
		{
			currentActor++;
			if(currentActor == entities.size())
				currentActor=0;
			entities.get(currentActor).initiateTurn();	
		}
		return 0;
	}

	@Override
	public boolean moveEntity(IEntity entity, int x, int y) {
		if (x <= sizeX && y <= sizeY) {
			tileMap.get(entity.getY()).get(entity.getX()).entities.remove(entity);
			entity.setXY(x, y);
			tileMap.get(y).get(x).entities.add(entity);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean walkEntity(IEntity entity, int i) {
		int x = entity.getX();
		int y = entity.getY();
		if (i == 0) {
			if (tileMap.get(y).get(x).North != null) {
				int destX = tileMap.get(y).get(x).North.X;
				int destY = tileMap.get(y).get(x).North.Y;
				return moveEntity(entity, destX, destY);
			} else {
				// System.out.println("North is null");
				return false;
			}

		} else if (i == 1) {
			if (tileMap.get(y).get(x).East != null) {
				int destX = tileMap.get(y).get(x).East.X;
				int destY = tileMap.get(y).get(x).East.Y;
				return moveEntity(entity, destX, destY);
			} else {
				return false;
			}

		} else if (i == 2) {
			if (tileMap.get(y).get(x).South != null) {
				int destX = tileMap.get(y).get(x).South.X;
				int destY = tileMap.get(y).get(x).South.Y;
				return moveEntity(entity, destX, destY);
			} else {
				return false;
			}

		} else if (i == 3) {
			if (tileMap.get(y).get(x).West != null) {
				int destX = tileMap.get(y).get(x).West.X;
				int destY = tileMap.get(y).get(x).West.Y;
				return moveEntity(entity, destX, destY);
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	@Override
	public void setTileType(int x, int y, int type) {
		tileMap.get(y).get(x).tileType = type;
	}

	@Override
	public boolean isWalkable(int x, int y) {
		return tileMap.get(y).get(x).walkable;
	}

	@Override
	public void setWalkable(int x, int y, boolean walkable) {
		tileMap.get(y).get(x).walkable = walkable;

	}

	@Override
	public boolean isVisible(int x, int y) {
		return tileMap.get(y).get(x).visible;
	}

	@Override
	public void setVisible(int x, int y, boolean visible) {
		tileMap.get(y).get(x).visible = visible;

	}

	@Override
	public void updatePaths() {
		System.out.println("");
		System.out.println("");
		for (int y = 0; y < sizeY; y++) {
			System.out.println("");
			for (int x = 0; x < sizeX; x++) {

				try {
					if (tileMap.get(y - 1).get(x).walkable == true) {
						tileMap.get(y).get(x).North = tileMap.get(y - 1).get(x);
					} else {
						tileMap.get(y).get(x).North = null;
					}

				} catch (NullPointerException e) {
					tileMap.get(y).get(x).North = null;
				}
				try {
					if (tileMap.get(y).get(x + 1).walkable == true) {
						tileMap.get(y).get(x).East = tileMap.get(y).get(x + 1);
					} else {
						tileMap.get(y).get(x).East = null;
					}

				} catch (NullPointerException e) {
					tileMap.get(y).get(x).East = null;
				}
				try {
					if (tileMap.get(y + 1).get(x).walkable == true) {
						tileMap.get(y).get(x).South = tileMap.get(y + 1).get(x);
					} else {
						tileMap.get(y).get(x).South = null;
					}

				} catch (NullPointerException e) {
					tileMap.get(y).get(x).South = null;
				}
				try {
					if (tileMap.get(y).get(x - 1).walkable == true) {
						tileMap.get(y).get(x).West = tileMap.get(y).get(x - 1);
					} else {
						tileMap.get(y).get(x).West = null;
					}

				} catch (NullPointerException e) {
					tileMap.get(y).get(x).West = null;
				}
				System.out.print(tileMap.get(y).get(x));
			}

		}
	}

	@Override
	public void customPath(int edge, int fromY, int fromX, int toX, int toY) {

		if (edge == 0) {
			tileMap.get(fromY).get(fromX).North = tileMap.get(toY).get(toX);
		} else if (edge == 1) {
			tileMap.get(fromY).get(fromX).East = tileMap.get(toY).get(toX);
		} else if (edge == 2) {
			tileMap.get(fromY).get(fromX).South = tileMap.get(toY).get(toX);
		} else if (edge == 3) {
			tileMap.get(fromY).get(fromX).West = tileMap.get(toY).get(toX);
		}
	}

	private void dfs(Tile where, Set<Tile> visited) {
		visited.add(where);

		if (!visited.contains(where.North) && where.North != null) {
			dfs(where.North, visited);
		}
		if (!visited.contains(where.East) && where.East != null) {
			dfs(where.East, visited);
		}
		if (!visited.contains(where.South) && where.South != null) {
			dfs(where.South, visited);
		}
		if (!visited.contains(where.West) && where.West != null) {
			dfs(where.West, visited);
		}

	}

	public boolean pathExists(int fromX, int fromY, int toX, int toY) {
		Tile from = tileMap.get(fromY).get(fromX);
		Tile to = tileMap.get(toY).get(toX);

		Set<Tile> visited = new HashSet<>();
		dfs(from, visited);
		return visited.contains(to);
	}

	public ArrayList<Tile> getTiles() {
		ArrayList<Tile> list = new ArrayList();
		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				list.add(tileMap.get(y).get(x));
			}
		}
		return list;
	}

	public ArrayList<Integer> getPath(int fromX, int fromY, int toX, int toY) {
		if (!pathExists(fromX, fromY, toX, toY)) {
			return null;
		}
		Tile from = tileMap.get(fromY).get(fromX);
		Tile to = tileMap.get(toY).get(toX);
		HashMap<Tile, Integer> time = new HashMap<>();
		HashMap<Tile, Boolean> determined = new HashMap<>();
		HashMap<Tile, Tile> origin = new HashMap<>();
		Tile current = from;

		for (Tile tile : getTiles()) {
			time.put(tile, Integer.MAX_VALUE);
			determined.put(tile, false);
			origin.put(tile, null);

		}

		time.put(from, 0);
		determined.put(from, true);
		while (!determined.get(to)) {

			if (current.North != null) {
				if ((current.North.getWeight() + time.get(current)) < time
						.get(current.North)) {

					Integer newInt = time.get(current)
							+ current.North.getWeight();
					time.put(current.North, newInt);
					origin.put(current.North, current);

				}

			}
			if (current.East != null) {
				if ((current.East.getWeight() + time.get(current)) < time
						.get(current.East)) {

					Integer newInt = time.get(current)
							+ current.East.getWeight();
					time.put(current.East, newInt);
					origin.put(current.East, current);

				}

			}
			if (current.South != null) {
				if ((current.South.getWeight() + time.get(current)) < time
						.get(current.South)) {

					Integer newInt = time.get(current)
							+ current.South.getWeight();
					time.put(current.South, newInt);
					origin.put(current.South, current);

				}

			}
			if (current.West != null) {
				if ((current.West.getWeight() + time.get(current)) < time
						.get(current.West)) {

					Integer newInt = time.get(current)
							+ current.West.getWeight();
					time.put(current.West, newInt);
					origin.put(current.West, current);

				}

			}

			for (Tile tile : getTiles()) {

				if (!determined.get(tile)) {
					if (determined.get(current)) {
						current = tile;

					} else {
						if (time.get(current) > time.get(tile)) {
							current = tile;
						}
					}

				}

			}
			determined.put(current, true);
		}

		ArrayList<Integer> returnList = new ArrayList<Integer>();
		return recursionRus(to, from, origin, returnList);
	}

	private static ArrayList<Integer> recursionRus(Tile current, Tile from,
			HashMap<Tile, Tile> origin, ArrayList<Integer> returnList) {
		if (!current.equals(from)) {
			Tile next = origin.get(current);
			recursionRus(next, from, origin, returnList);
			returnList.add(origin.get(current).getDirection(current));

		}

		return returnList;
	}

	class Tile {
		ArrayList<IEntity> entities = new ArrayList<IEntity>();
		Tile North = null;
		Tile East = null;
		Tile South = null;
		Tile West = null;
		boolean walkable = true;
		boolean visible = true;
		int tileType = 1;
		final int X;
		final int Y;

		private Tile(int x, int y) {
			X = x;
			Y = y;
		}

		public ArrayList<Tile> getEdges() {
			ArrayList<Tile> list = new ArrayList();
			if (North != null)
				list.add(North);
			if (East != null)
				list.add(East);
			if (South != null)
				list.add(South);
			if (West != null)
				list.add(West);

			return list;
		}

		public int getWeight() {
			return 1;
		}

		public int getDirection(Tile tile) {
			if (North == tile)
				return 0;
			else if (East == tile)
				return 1;
			else if (South == tile)
				return 2;
			else if (West == tile)
				return 3;
			else {
				return -1;
			}
		}

		public String toString() {
			String s = "(";
			if (North != null) {
				s = s + "A";
			} else {
				s = s + "0";
			}

			if (East != null) {
				s = s + ">";
			} else {
				s = s + "0";
			}

			if (South != null) {
				s = s + "V";
			} else {
				s = s + "0";
			}

			if (West != null) {
				s = s + "<";
			} else {
				s = s + "0";
			}
			s = s + ")";
			return s;

		}
	}

	@Override
	public void setWalkableSquare(int firstX, int firstY, int secondX,
			int secondY) {
		// TODO Auto-generated method stub
		
	}
	public void endTurn() {
		turnCounter++;
	}
	public int getTurnCounter() {
		return turnCounter;
	}

	public void setTurnCounter(int turnCounter) {
		this.turnCounter = turnCounter;
	}

	public ArrayList<IEntity> getEntities() {
		return entities;
	}

	private void setEntities(ArrayList<IEntity> entities) {
		this.entities = entities;
	}

	public GameField getGraphics() {
		return graphics;
	}

	public void setGraphics(GameField graphics) {
		this.graphics = graphics;
	}

	public int getCurrentActor() {
		return currentActor;
	}

	public void setCurrentActor(int currentActor) {
		this.currentActor = currentActor;
	}

	
}
