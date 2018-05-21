package vector;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


public class Paint extends JFrame {
	private Canvas canvas;
	private ToolPan toolpan;
	private ColorPan color;
	private Container main;
	public static void main(String [] args)
	{
		Paint application = new Paint();
		application.show();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public Paint ()
	{
		super ("PAINT");
		 // calling canvas
		canvas = Canvas.getInstance();
		canvas.setCol(Color.BLACK); 
		//calling toolpan
		toolpan = new ToolPan(canvas);
		//calling colour
		color = new ColorPan(canvas);
		main = getContentPane();
		main.add(toolpan, BorderLayout.NORTH);
		main.add(canvas, BorderLayout.CENTER);
		//main.add(history, BorderLayout.EAST);
		main.add(color, BorderLayout.WEST);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,1000);
		this.setResizable(false);
		setVisible(true);
		//canvas.drawMode = 0;
		/*addWindowListener (
      		new WindowAdapter () 
      		{
      			public void windowClosing (WindowEvent e) 
      			{
      				System.exit(0);
      			}
      			public void windowDeiconified (WindowEvent e) 
      			{
      				canvas.repaint();
      			}
      			public void windowActivated (WindowEvent e) 
      			{	 
      				canvas.repaint();
      			}
      		}
      	);*/
	}
	
	
	
}
