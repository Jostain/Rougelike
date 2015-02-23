package GameEngine;

import java.util.ArrayList;
import java.util.Random;
import Renderer.GameField;

public class Entity implements IEntity {
	private int x = 0;
	private int y = 0;
	private int sprite = 3;
	private int speed = 10;
	private int actionPoints = 100;
	private int health = 12;
	private int mode = 0;
	private boolean controllable = false;
	Random rand = new Random();
	IGrid grid;
	private IEntity target;
	private String name = "???";

	public Entity(IGrid g, IEntity target, String name, boolean controllable,
			int sprite) {
		grid = g;
		this.target = target;
		this.name = name;
		this.controllable = controllable;
		this.sprite = sprite;
	}

	public boolean initiateTurn() {
		grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
		System.out.println("Initiating Turn for "+name);
		
		return controllable;
	}

	@Override
	public int act(int key) {

		if (controllable == false) {
			
			System.out.println("running ai");
			return AI();

		} else {
			
			if (actionPoints > 0) {
				if (key == 0) {
					System.out.println("walking north");
					System.out.println(grid.walkEntity(this, 0));
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = actionPoints - speed;
				} else if (key == 1) {
					System.out.println("walking east");
					System.out.println(grid.walkEntity(this, 1));
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = actionPoints - speed;
				} else if (key == 2) {
					System.out.println("walking south");
					System.out.println(grid.walkEntity(this, 2));
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = actionPoints - speed;
				} else if (key == 3) {
					System.out.println("walking west");
					System.out.println(grid.walkEntity(this, 3));
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = actionPoints - speed;
				} else if (key == 5) {
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = 100;
					return 0;
				}
				for (IEntity e : grid.getEntities()) {
					e.notOnTurn();
				}
			} else {
				if (key == 5) {
					grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
					actionPoints = 100;
					return 0;
				}
			}
		}

		return -1;

	}

	public void endTurn() {

	}

	public void notOnTurn() {

	}

	@Override
	public void attack(int Accuracy, int attakccType, int damage,String s, IEntity attacker) {
		target = attacker;
		System.out.println(target.getName() + " attacks " + name + " for "
				+ damage + " damage!");
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void setName(String string) {
		this.name = string;
	}

	@Override
	public boolean getControllable() {
		return controllable;
	}

	@Override
	public void setControllable(boolean controllable) {
		this.controllable = controllable;

	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return health;
	}

	@Override
	public int getSprite() {
		return sprite;
	}

	@Override
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return this.y;
	}

	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public int AI() {

		if (actionPoints > 0) {
			if (target.getX() + 1 == x && target.getY() == y
					|| target.getX() - 1 == x && target.getY() == y
					|| target.getX() == x && target.getY() == y + 1
					|| target.getX() == x && target.getY() == y - 1) {
				target.attack(100,0,5,null, this);
				actionPoints = actionPoints - speed;
				grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
				for (IEntity e : grid.getEntities()) {
					e.notOnTurn();
					
				}
				return -1;
			} else {
				ArrayList<Integer> list = grid.getPath(x, y, target.getX(),
						target.getY());
				grid.walkEntity(this, list.get(0));
				actionPoints = actionPoints - speed;
				grid.getGraphics().CenterOnCoordinate(x - 16, y - 8);
				for (IEntity e : grid.getEntities()) {
					e.notOnTurn();
				}
				return -1;
			}
			
		}
		else{
		actionPoints = 100;
		System.out.println("end Turn");
		return 0;
		}
	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}
}
