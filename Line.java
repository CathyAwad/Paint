package vector;

import java.awt.Color;
import java.awt.geom.Line2D;

public class Line extends Shape {

	

	public Line(int x1, int y1, int x2, int y2, Color font) {
		super(x1, y1, x2, y2, font);
		this.setX0((this.getX1() + this.getX2())/2);
		this.setY0((this.getY1() + this.getY2())/2);

		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void draw() {
		this.drawMode=1;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Boolean contains(int x, int y)
	{
		Line2D line = new Line2D.Float(this.getX1(),this.getY1(),this.getX2(),this.getY2());
		
		
		double d = line.ptLineDist(x, y); 
				if (d <= 4)
			return true;
		else
			return false;
		
	}

	@Override
	public void move(int x1, int y1, int x2, int y2) {
		//return new Line(this.getX1() +x2-x1,this.getX2() +x2-x1,this.getY1() +y2 - y1,this.getY2() +y2 - y1,this.getFont());
		this.setX1(this.getX1() +x2-x1);
		this.setX2(this.getX2() +x2-x1);
		this.setY1(this.getY1() +y2 - y1);
		this.setY2(this.getY2() +y2 - y1);
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate( double angle) {
		// TODO Auto-generated method stub
		/*double s = Math.sin(angle);
		double c = Math.cos(angle);
		this.setX1( Math.abs(this.getX2() - this.getX1()));
		this.setY1(Math.abs(this.getY2() - this.getY1()));
		this.setX1((int) (this.getX1() * c - this.getY1() *s));
		this.setY1((int) (this.getX1() *s + this.getY1() *c));
		this.setX1(this.getX1() + this.getX2());
		this.setY1(this.getY1() + this.getY2());
*/
		//this.setX1((int) (this.getX1() *Math.cos(angle) - this.getY1() *Math.sin(angle)) );
		//this.setY1((int) (this.getX1() *Math.sin(angle) + this.getY1() *Math.cos(angle)) );
		//this.setX2((int) (this.getX2() *Math.cos(angle) - this.getY2() *Math.sin(angle)) );
		//this.setY2((int) (this.getX2() *Math.sin(angle) - this.getY2() *Math.cos(angle)) );
		//this.setX1( (int) (this.getX2() + Math.abs(this.getX2()-this.getX1())*Math.cos(angle) - Math.abs(this.getY2() - this.getY1() )*Math.sin(angle)));
		//this.setY1( (int) (this.getY2() + Math.abs(this.getX2()-this.getX1())*Math.sin(angle) + Math.abs(this.getY2() - this.getY1() )*Math.cos(angle)));
		this.setX1((int) (this.getX0() + Math.abs(this.getX1()-this.getX0())*Math.cos(angle) + Math.abs(this.getY1() - this.getY0())*Math.sin(angle)));
		this.setY1((int) (this.getY0() + Math.abs(this.getX1()-this.getX0())*Math.sin(angle) - Math.abs(this.getY1() - this.getY0())*Math.cos(angle)));
		//this.setX2((int) (this.getX0() + Math.abs(this.getX2()-this.getX0())*Math.cos(angle) + Math.abs(this.getY2() - this.getY0())*Math.sin(angle)));
		//this.setY2((int) (this.getY0() + Math.abs(this.getX2()-this.getX0())*Math.sin(angle) - Math.abs(this.getY2() - this.getY0())*Math.cos(angle)));

	
	}

	@Override
	public void resize(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		this.setX1(this.getX1() );
		this.setX2(x2);
		this.setY1(this.getY1() );
		this.setY2(y2);
		
	}
	

	
}
