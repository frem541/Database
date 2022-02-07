package fff;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
	
	 ArrayList<product> products;
	 MyTableModel(ArrayList<product> products) 
	    {
	        super();
	        this.products = products;
	    }
	
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		switch (c) 
		{
        case 0:
            return products.get(r).getname();
        case 1:
            return products.get(r).getcode();
        case 2:
        	return products.get(r).getprice()+" ���.";
        case 3:
        	return Integer.toString(products.get(r).getnum())+" ��.";
        default:
            return "";
		}
	}
	
	public String getColumnName(int c) 
	{
		String result = "";
		switch (c) 
		{
			case 0:
				result = "������������";
				break;
			case 1:
				result = "���";
				break;
			case 2:
				result = "����";
				break;
			case 3:
				result = "����������";
				break;
		}
		return result;
	}
}
