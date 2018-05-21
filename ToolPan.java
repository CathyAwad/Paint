package vector;

import java.awt.Checkbox;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

public class ToolPan extends JPanel {
	//elbuttons elly benersemhom
	private JButton circle;
	private JButton line;
	private JButton square;
	private JButton oval;
	private JButton rectangle;
	private JButton right;
	private JButton iso;
	private JButton select;
	private JButton delete;
	private JButton copy;
	private JButton resize;
	private JButton undo;
	private JButton redo;
	private JButton clear;
	private JButton history;
	private JCheckBox fill;
	//instance elcanvas 3ashan ne3'ayar eldrawmode
	private Canvas canvas;
	
	public ToolPan(Canvas c)
	{
		canvas = c;
		
		circle = new JButton("",new ImageIcon("circle.jpg"));
		line = new JButton("",new ImageIcon("line.jpg"));
		square = new JButton("",new ImageIcon("square.jpg"));
		oval = new JButton("",new ImageIcon("oval.jpg")); 
		rectangle = new JButton("",new ImageIcon("rectangle.jpg"));
		right = new JButton("",new ImageIcon("right.jpg"));
		iso = new JButton("",new ImageIcon("triangle.jpg"));
		select = new JButton("select");
		delete = new JButton("delete");
		copy = new JButton("copy");
		resize = new JButton("resize");
		undo = new JButton("undo");
		redo = new JButton("redo");
		clear = new JButton("clear canvas");
		history = new JButton("History");
		fill = new JCheckBox("fill");
		
		circle.addActionListener(new ToolButton());
		circle.setToolTipText("circle");
		line.addActionListener(new ToolButton());
		line.setToolTipText("line");

		square.addActionListener(new ToolButton());
		square.setToolTipText("square");
		
		oval.addActionListener(new ToolButton());
		oval.setToolTipText("oval");
		
		right.addActionListener(new ToolButton());
		right.setToolTipText("right triangle");
		
		iso.addActionListener(new ToolButton());
		iso.setToolTipText("isosceles triangle");
		
		rectangle.addActionListener(new ToolButton());
		rectangle.setToolTipText("rectangle");
		
		undo.addActionListener(new ToolButton());
		undo.setToolTipText("undo");
		redo.addActionListener(new ToolButton());
		redo.setToolTipText("redo");
		delete.addActionListener(new ToolButton());
		delete.setToolTipText("delete selected");
		clear.addActionListener(new ToolButton());
		clear.setToolTipText("clear canvas");
		select.addActionListener(new ToolButton());
		select.setToolTipText("select shape");
		history.addActionListener(new ToolButton());
		history.setToolTipText("history");
		resize.addActionListener(new ToolButton());
		resize.setToolTipText("rotate shape");
		copy.addActionListener(new ToolButton());
		copy.setToolTipText("copy shape");
		
		fill.addItemListener( 
				new ItemListener()
				{
					

					@Override
					public void itemStateChanged(ItemEvent arg0) {
						// TODO Auto-generated method stub
						if(fill.isSelected())
							canvas.solidMode= true;
						else 
							canvas.solidMode = false;
						
					}
				}
				);
				

		
		this.setLayout(new GridLayout(2,11));
		this.add(circle);
		this.add(line);
		this.add(square);
		this.add(oval);
		this.add(rectangle);
		this.add(right);
		this.add(iso);
		this.add(select);
		this.add(delete);
		this.add(resize);
		this.add(copy);
		this.add(undo);
		this.add(redo);
		this.add(clear);
		this.add(history);
		this.add(fill);
	}
	
	private class ToolButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == circle)
			{
				canvas.selectMode =0;
				canvas.drawMode = 0;
				canvas.selected = null;
			}
			if (e.getSource() == line)
			{				canvas.selectMode =0;

				canvas.drawMode = 1;
				canvas.selected = null;

			}
			if(e.getSource() == undo)
			{				canvas.selectMode =0;

				canvas.undo();
				canvas.selected = null;

			}
			if(e.getSource() == square)
			{				canvas.selectMode =0;

				canvas.drawMode = 2;
				canvas.selected = null;

			}
			if(e.getSource() == oval)
			{				canvas.selectMode =0;

				canvas.drawMode = 3;
				canvas.selected = null;

			}
			if(e.getSource() == rectangle)
			{
				canvas.drawMode =4 ;
				canvas.selected = null;

			}
			if(e.getSource() == redo)
			{
				canvas.redo();
				canvas.selected = null;

			}
			if(e.getSource() == right)
			{
				canvas.drawMode = 5;
				canvas.selected = null;

			}
			if(e.getSource() == iso)
			{
				canvas.drawMode =6;
				canvas.selected = null;

			}
			if(e.getSource() == clear)
			{
				canvas.clear();
				canvas.selected = null;

			}
			if(e.getSource() == history)
			{
				canvas.undoHistory();
				canvas.selected = null;

			}
			if(e.getSource() == select)
			{
				canvas.drawMode = -1;
				canvas.selectMode = 1;
				canvas.sizeMode = 0;
			}
			if(e.getSource() == delete)
			{
				canvas.deleteSelected();
				canvas.selected = null;

			}
			if(e.getSource() == resize)
			{
				if(canvas.selected == null)
					JOptionPane.showMessageDialog(null, "nothing selected");
				canvas.sizeMode = 1;
				canvas.drawMode = -1;

			}
			if(e.getSource() == copy)
			{
				canvas.Copy();
			}
			// TODO Auto-generated method stub
			
		}


	}
}
