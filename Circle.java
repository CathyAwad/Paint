package vector;
import java.awt.geom.Ellipse2D;


import java.awt.Color;

public class Circle extends Shape {

	private int r;
	private int x0,y0;
	public Circle(int x1, int y1, int x2, int y2, Color font) {
		super(x1, y1, x2, y2, font);
		// TODO Auto-generated constructor stub
	}
	private int getR()
	{
		return this.getX2()/2;
	}
	private void setCenter()
	{
		this.x0 = Math.abs(this.getX1()- this.getX2())/2;
		this.y0 = Math.abs(this.getY1()-this.getY2())/2;
	}
	@Override
	public Boolean contains(int x, int y)
	{
		Ellipse2D circle = new Ellipse2D.Float(this.getX1(),this.getY1(),this.getX2(),this.getY2());
		if(circle.contains(x, y))
			return true;
		else return false;
	}
	
	
	

	@Override
	public void draw() {
		this.drawMode=0;
		// TODO Auto-generated method stub
		
	}
	@Override
	public void move(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		this.setX1(this.getX1() +x2-x1);
		this.setX2(this.getX2() );
		this.setY1(this.getY1() +y2 - y1);
		this.setY2(this.getY2() );
		
	}
	@Override
	public void resize(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		
		this.setX1(this.getX1() );
		this.setX2(x2-x1);
		this.setY1(this.getY1() );
		this.setY2(x2-x1);
		
	}
	@Override
	public void rotate(double angle) {
		// TODO Auto-generated method stub
		
	}

}
