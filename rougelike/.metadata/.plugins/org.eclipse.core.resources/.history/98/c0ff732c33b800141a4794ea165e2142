package GameEngine;

import java.util.ArrayList;

public class BodyPart {
		private String name;
		private Mat core;
		private Mat flesh;
		private Mat skin;
		private Mat clothes;
		private Mat armor;
		private int importance;
		private boolean severed = false;
		private ArrayList<BodyPart> bodyParts = new ArrayList<BodyPart>();
		public BodyPart(String name, Mat core, Mat flesh, Mat skin,int importance)
		{
			this.name = name;
			this.core=core;
			this.flesh=flesh;
			this.skin=skin;
			this.importance = importance;
			
		}
		public BodyPart getBodyPart(String name)
		{
			if (this.name == name)
			{
				return this;
			}
			else
			{
				for(BodyPart p: bodyParts)
				{
					BodyPart temp = p.getBodyPart(name);
					if (temp != null)
					{
						return temp;
					}
				}
				return null;
				
			}
			
		}
		public ArrayList<BodyPart> getBodyParts() {
			return bodyParts;
		}
		public void growBodyPart(BodyPart part)
		{
			bodyParts.add(part);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void sever() 
		{
			severed = true;
			for(BodyPart p: bodyParts)
			{
				p.sever();				
			}
			System.out.println(name+" Lost!");
			
		}
		public boolean isSevered() {
			return severed;
		}
		public void setSevered(boolean severed) {
			this.severed = severed;
		}
	}


