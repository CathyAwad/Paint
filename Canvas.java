package vector;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.event.*;
import java.io.Serializable;
import java.util.Stack;
//import java.util.Vector;
import java.util.Vector;

import javax.swing.*;

// 0-circle  1-line 2-square 3-oval 4- rectangle 5- right Triangle 6- Isosceles triangle
// select mode = 1 >>> on      else >>> off
//size mode = 1 >>> resize


public class Canvas extends JPanel implements MouseListener,MouseMotionListener{
	
	public static Vector<Shape> shapes;
	public int drawMode = -1;
	public int selectMode =0;
	public int sizeMode =0;
	Shape selected;

	public Boolean solidMode = false;
	private int x1,x2,y1,y2;
	public Color col,back;
	public ShapeFact fact;
	private Stack<Shape> undo,redo;
	public JTextArea history;
	public ShapeCache cl;
	private static Canvas instance;
	//private constructor singleton design pattern
	private Canvas()
	{
		col = Color.BLACK;
		//history list
		history = new JTextArea();
		shapes = new Vector();
		undo = new Stack();
		redo = new Stack();
		fact = new ShapeFact();
		cl = new ShapeCache();
		addMouseListener(this);
		addMouseMotionListener(this);
		back = Color.WHITE;
		setBackground(back);
		history.setLocation(1100,100);
		this.add(history);
		history.setBackground(Color.BLACK);
		history.setForeground(Color.WHITE);
		repaint();
	}
	public static Canvas getInstance()
	{
		instance = new Canvas();
		return instance;
	}
	public void setCol(Color font)
	{
		this.col = font;
	}
	public Color getCol()
	{
		return this.col;
	}
	
	
	//the mouse events that must bue implemented due to mouseListener and motionListener
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		//used to move or resize a shape according to sizeMode
		repaint();
		x2= e.getX();
		y2= e.getY();
		if(selected != null)
		{
			if(sizeMode != 1)
				selected.move( selected.getX1(), selected.getY1(),e.getX(), e.getY());
			else
				selected.resize(selected.getX1(), selected.getY1(), e.getX(), e.getY());
		}
		//repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//for getting the handshaped mouse to know when to select the shape
		//mmkn nesta3'na 3nha
		if(selectMode == 1)
		{
			for(Shape s:shapes)
			{
				if(s.contains(e.getX(), e.getY()))
				{
					setCursor(new Cursor(Cursor.HAND_CURSOR));
				}
			}
		}
		else
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		// getting the initial x and y coordinates to draw the shape
		x1 =  e.getX();
        y1 =  e.getY();
        //selecting the shape
		if(selectMode == 1)
		{
			for(int i = shapes.size()-1; i>=0; i--)
			{
				Shape cur = shapes.elementAt(i);
				
					if ( cur.contains(e.getX(), e.getY()))
						selected = cur;
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//selecting the shape
		/*
		if(selectMode == 1)
		{
			for(int i = shapes.size()-1; i>=0; i--)
			{
				Shape cur = shapes.elementAt(i);
				if(cur instanceof Line)
				{
					if ( cur.contains(e.getX(), e.getY()))
						selected = cur;
				}
			}
		}*/
		//emptying the history list
		if(undo.isEmpty())
		{
			history.setText(" ");
		}
		//creating the instance depending on the chosen drawMode and adding it to the shapes vector and the undo stack
		if(drawMode == 0)
		{
			int x = Math.min(x1, e.getX());
			int y = Math.min(y1, e.getY());
			int d = Math.abs(x1 - x2);
				shapes.add(fact.getShape("circle",x,y,d,d,col));
				undo.push(shapes.lastElement());
				if(solidMode == true)
				{
					shapes.lastElement().setSolid(true);
				}
				history.append("" +shapes.size()+"  draw circle\n" );
				//repaint();
			
		}
		if(drawMode == 1)
		{
			shapes.addElement(fact.getShape("line", x1, y1, e.getX(), e.getY(), col));
			undo.push(shapes.elementAt(shapes.size()-1));
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}
			history.append("" +shapes.size()+"  draw line\n" );
			
			
			//repaint();
		}
		if(drawMode == 2)
		{
			int x = Math.min(x1, e.getX());
			int y = Math.min(y1, e.getY());
			int d = Math.abs(x1 - x2);
			shapes.addElement(fact.getShape("square", x, y, d, d, col));
			undo.push(shapes.lastElement());
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}
			history.append("" +shapes.size()+"  draw square\n" );

		}
		if(drawMode == 3)
		{
			int x = Math.min(x1, e.getX());
			int y = Math.min(y1, e.getY());
			int l = Math.abs(x1 - e.getX());
			int w = Math.abs(y1 - e.getY());
			shapes.addElement(fact.getShape("oval", x, y, l, w, col));
			undo.push(shapes.lastElement());
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}
			history.append("" +shapes.size()+"  draw oval\n" );


		}
		if(drawMode == 4)
		{
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int l = Math.abs(x1 - x2);
			int w = Math.abs(y1-y2);
			shapes.addElement(fact.getShape("rectangle", x, y, l, w, col));
			undo.push(shapes.lastElement());
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}
			history.append("" +shapes.size()+"  draw rectangle\n" );


		}
		if(drawMode == 5)
		{
			shapes.addElement(fact.getShape("right", x1, y1, e.getX(), e.getY(), col));
			undo.push(shapes.lastElement());
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}
			history.append("" +shapes.size()+"  draw right tri\n" );


		}
		if(drawMode == 6)
		{
			shapes.add(fact.getShape("iso", x1, y1, e.getX(), e.getY(), col));
			undo.push(shapes.lastElement());
			if(solidMode == true)
			{
				shapes.lastElement().setSolid(true);
			}       
			history.append("" +shapes.size()+"  draw iso tri\n" );

		}
		
		
		repaint();
		
	}
	
	public void paintComponent(Graphics g)
	{
		//redrawBuffer(g);
		super.paintComponent(g);
		redrawBuffer(g);
		//g.setColor(col);
	
		// drawing the shapes on the screen using graphics class
		if (drawMode ==0)
		{
			if(solidMode == false)
			{
				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int d = Math.abs(x1 - x2);
				g.drawOval(x, y, d, d);
			}
			else
			{
				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int d = Math.abs(x1 - x2);
				g.fillOval(x, y, d, d);
			}
		}
		if(drawMode == 1)
		{
			g.drawLine(x1, y1, x2, y2);
			
		}
		if(drawMode == 2)
		{
			if(solidMode == false)
			{
				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int d = Math.abs(x1 - x2);
				g.drawRect(x, y, d, d);
		
			}
			else
			{
				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int d = Math.abs(x1 - x2);
				g.fillRect(x, y, d, d);
			}
		}
		if(drawMode == 3)
		{	
			if(solidMode == false)
			{
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int l = Math.abs(x1 - x2);
			int w = Math.abs(y1-y2);
			g.drawOval(x, y, l, w);
			}
			else{
				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int l = Math.abs(x1 - x2);
				int w = Math.abs(y1-y2);
				g.fillOval(x, y, l, w);
			}
		}
		if(drawMode == 4)
		{
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int l = Math.abs(x1 - x2);
			int w = Math.abs(y1-y2);
			if(solidMode == false)
			{
			g.drawRect(x, y, l, w);
			}
			else
			{
				g.fillRect(x, y, l, w);

			}
		}
		if(drawMode == 5)
		{
			int [] x = {x1,x1,x2};
			int [] y = {y1,y2,y2};
			if(solidMode == false)
				g.drawPolygon(x,y,3);
			else
				g.fillPolygon(x,y,3);

		}
		if(drawMode == 6)
		{
			int [] x = {x1,2*x1-x2,x2};
			int [] y = {y1,y2,y2};
			if(solidMode == false)
				g.drawPolygon(x, y, 3);
			else
				g.fillPolygon(x, y, 3);

		}
		// adding the small circle on the start point of the shape to indicate it's been selected
		if(selectMode == 1 && selected != null)
		{
			g.fillOval(selected.getX1(), selected.getY1(), 10, 10);
			selectMode = 0;
		}
			//x1=0;
		//y1=0;
	
	}
	
	private void redrawBuffer(Graphics g)
	{
		//drawing all the saved shapes back on the screen after adding a new shape
		//without this method the program will only paint one shape at a time
		history.setLocation(1220, 50);

		//looping on the vector shapes to draw all the previous shapes
		for(int i = 0; i< shapes.size(); i++)
		{
			Shape cur = shapes.elementAt(i);
			// setting the correct colour to draw according to the selected colour from the colour panel
			g.setColor(cur.getFont());
			if(cur instanceof Circle)
			{
				if(cur.isSolid()== false)
				{
					g.drawOval(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());
				}
				else
				{
					g.fillOval(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());

				}
							
			}
			else if(cur instanceof Line)
			{
				g.drawLine(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());

			}
			else if(cur instanceof Square)
			{
				if(cur.isSolid()== false)
				{
					g.drawRect(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());
				}
				else
				{
					g.fillRect(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());

				}
			}
			else if(cur instanceof Oval)
			{
				if(cur.isSolid()== false)
					g.drawOval(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());
				else 
					g.fillOval(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());
					
			}
			else if(cur instanceof Rectangle)
			{
				if(cur.isSolid()== false)
					g.drawRect(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());
				else
					g.fillRect(cur.getX1(), cur.getY1(), cur.getX2(), cur.getY2());

			}
			else if(cur instanceof RightTri)
			{
				int [] x = {cur.getX1(),cur.getX1(),cur.getX2()};
				int [] y = {cur.getY1(),cur.getY2(),cur.getY2()};
				if(cur.isSolid()== false)
					g.drawPolygon(x, y, 3);
				else
					g.fillPolygon(x, y, 3);
					
				
			}
			else if(cur instanceof IsoTri)
			{
				int [] x = {cur.getX1(), 2*cur.getX1()-cur.getX2(),cur.getX2()};
				int [] y = {cur.getY1(),cur.getY2(),cur.getY2()};
				if(cur.isSolid()== false)
					g.drawPolygon(x, y, 3);
				else
					g.fillPolygon(x, y, 3);
				
					
			}
}
		
		
		
	}
	public void setDrawMode(int mode)
	{
		drawMode = mode;
	}
	public int getDrawMode()
	{	
		return drawMode;	
	}
	

	public void undo()
	{
		//every item poped from undo stack is pushed into redo stack and vice versa
		drawMode = -1;
		Shape last;
		if(undo.isEmpty() == false)
		{
			last = undo.pop();
			redo.push(last);
			shapes.remove(last);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "can't undo");
			
		}
		repaint();
	
	}
	public void redo()
	{
		Shape last;
		if(redo.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "can't redo");
			repaint();
		}
		else
		{
			last = redo.pop();
			shapes.addElement(last);
			undo.push(last);
		}
		repaint();
	}
	public void clear()
	{
		//deleting all the shapes from the canvas
		drawMode = -1;
		shapes.removeAllElements();
		history.setText("");
		
		repaint();
	}
	public void undoHistory()
	{
		//the user gives a number corresponding to the number of the shape in the vector
		//so we loop the vector from last to first and delete every member that's not the needed shape
		drawMode = -1;
		String x =JOptionPane.showInputDialog(null, "enter number of step you want to go to");
		int choice = Integer.parseInt(x);
		while(choice > shapes.size())
		{
			JOptionPane.showMessageDialog(null, "shape already deleted");
			 x =JOptionPane.showInputDialog(null, "enter number of step you want to go to");
			 choice = Integer.parseInt(x);
		}
		Shape last;
		last = undo.peek();
		while(last != shapes.elementAt(choice))
		{
			shapes.remove(last);
			undo.pop();
			last = undo.peek();
		}
		shapes.remove(last);
		repaint();
		
	}
	public void deleteSelected()
	{
		if(selected == null)
			JOptionPane.showMessageDialog(null, "nothing selected");
		else
			shapes.remove(selected);
		repaint();
		selected = null;
	}
	public void rotate()
	{
		
			selected.rotate(Math.toRadians(45));
			repaint();
		
		
	}
	public Shape Clone()
	{
		//returns a copy of the selected shape
		if(selected == null)
		{	JOptionPane.showMessageDialog(null, "nothing selected");
			return null;
		}
		else
		{
			return  (Shape) selected.clone();
		}
			
	}
	public void Copy()
	{
		//the copy is added to the shapes vector so it could be drawn on the canvas
		Shape n = Clone();
		undo.push(n);
		shapes.addElement(n);
		repaint();
	}
	



	
}
