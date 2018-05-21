package vector;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ColorPan extends JPanel {

	private JPanel color;
	private JButton col;
	private Color font;
	private Canvas canvas;
	
	public ColorPan( Canvas c)
	{
		canvas = c;
	
		col = new JButton("font");
		col.addActionListener(new ColorButton());
		this.add(col);
		
		
	}
	public void setFont()
	{
		font = JColorChooser.showDialog(null, "Font Color", font);
		if(font != null)
		{
			col.setBackground(font);
			canvas.col = font;
		}
	}
	private class ColorButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == col)
				setFont();
			
		}
	
	
	}

}

