package fff;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer 
{
	public JLabel getTableCellRendererComponent()
	{
		JLabel  lbl = new JLabel();
		ImageIcon icon = new ImageIcon(getClass().getResource("shit.png"));
		lbl.setIcon(icon);
		return lbl;
	}
}
