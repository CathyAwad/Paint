package vector;

import java.awt.Color;

public class ShapeFact {
	public Shape getShape(String type,int x1, int y1, int x2, int y2, Color font)
	{
		if(type.equalsIgnoreCase("circle"))
			return new Circle(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("line"))
			return new Line(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("square"))
			return new Square(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("oval"))
			return new Oval(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("rectangle"))
			return new Rectangle(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("right"))
			return new RightTri(x1,y1,x2,y2,font);
		if(type.equalsIgnoreCase("iso"))
			return new IsoTri(x1,y1,x2,y2,font);
		
		return null;
	}

}
