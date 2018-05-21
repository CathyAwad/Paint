package vector;

import java.awt.Color;
import java.awt.Polygon;

public class IsoTri extends Shape{

	public IsoTri(int x1, int y1, int x2, int y2, Color font) {
		super(x1, y1, x2, y2, font);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		this.drawMode=6;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean contains(int x, int y)
	{
		
		Polygon tri = new Polygon();
		tri.addPoint(this.getX1(), this.getY1());
		tri.addPoint(2*this.getX1() - this.getX2(), this.getY2());
		tri.addPoint(this.getX2(), this.getY2());
		if(tri.contains(x, y))
			return true;
		else return false;
	}

	@Override
	public void move(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		this.setX1(this.getX1() +x2-x1);
		this.setX2(this.getX2() +x2-x1);
		this.setY1(this.getY1() +y2 - y1);
		this.setY2(this.getY2() +y2-y1);
	}
	@Override
	public void resize(int x1, int y1, int x2, int y2)
	{
		this.setX1(this.getX1() +x2-x1);
		this.setX2(this.getX2() );
		this.setY1(this.getY1() +y2 - y1);
		this.setY2(this.getY2() );
	}

	

	@Override
	public void rotate(double angle) {
		// TODO Auto-generated method stub
		
	}
}
