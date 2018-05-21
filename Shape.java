package vector;

import java.awt.Color;
import java.io.Serializable;

public abstract  class Shape implements Cloneable {
	int drawMode;
	private int x1,x2,y1,y2;
	private int x0,y0;
	private Color font;
	private String id;
	private Boolean solidMode = false;
	public void draw()
	{
		
	}
	
	public Shape(int x1, int y1, int x2, int y2, Color font)
	{
		this.setX1(x1);
		this.setX2(x2);
		this.setY1(y1);
		this.setY2(y2);
		this.setFont(font);
		
	}
	public Boolean isSolid()
	{
		return this.solidMode;
	}
	public void setSolid(Boolean solid)
	{
		this.solidMode = solid;
	}

	public Color getFont() {
		return font;
	}

	public void setFont(Color font) {
		this.font = font;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}
	public abstract Boolean contains(int x, int y);
	public abstract void move(int x1, int y1, int x2, int y2);
	public abstract void resize(int x1, int y1, int x2, int y2);
	

	
	public abstract void rotate( double angle);

	public int getX0() {
		return x0;
	}

	public void setX0(int x0) {
		this.x0 = x0;
	}

	public int getY0() {
		return y0;
	}

	public void setY0(int y0) {
		this.y0 = y0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public final Object clone() {
	      Object clone = null;
	      
	      try {
	         clone = super.clone();
	         
	      } catch (CloneNotSupportedException e) {
	         e.printStackTrace();
	      }
	      
	      return clone;
	   }
	
	
}
