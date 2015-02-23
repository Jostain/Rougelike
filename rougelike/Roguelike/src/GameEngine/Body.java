package GameEngine;

import java.util.ArrayList;

public class Body {
	private BodyPart soul = new BodyPart("Soul",null,100);
	
	public Body()
	{
	}
	public void growBodypart(BodyPart part, String from)
	{		
		soul.getBodyPart(from).growBodyPart(part);
		System.out.println(from+" grew a"+part.getName()+"!");
	}
	public void severBodyPart(String name)
	{
		soul.getBodyPart(name).sever();
	}
	public String toString()
	{
		return null;
		
	}
	public void attack(int accuracy,int attackType, int force, String targetPart) {
		soul.attack(accuracy,attackType, force, targetPart);
		
	}
}
