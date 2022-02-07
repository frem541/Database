package fff;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

public class program 
{
	static int prodcntr = 0;
	static String LastFileName = null;
	public static boolean isDigit(String string) 
	{
        try 
        {
            Integer.parseInt(string);
        } 
        catch (Exception e) 
        {
            return false;
        }
        if (Integer.parseInt(string) > 0)
        	return true;
        return false;
    }
	public static boolean isFloat(String string) 
	{
        try 
        {
            Float.parseFloat(string);
        } 
        catch (Exception e) 
        {
            return false;
        }
        int temp;
        if (Float.parseFloat(string) > 0)
        {
        	temp = (int)(Math.round((Float.parseFloat(string) - (int)Float.parseFloat(string))*1000));
        	if (temp % 10 == 0)
        		return true;
        }
        return false;
    }
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(640,480);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("����Excel");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("����");
		JMenuItem newItem = new JMenuItem("�����");
		JMenuItem openItem = new JMenuItem("�������");
		JMenuItem saveItem = new JMenuItem("���������");
		JMenuItem saveasItem = new JMenuItem("��������� ���");
		menuBar.add(fileMenu);
		fileMenu.add(newItem);
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.add(saveasItem);
		
		JToolBar toolbar = new JToolBar();
		JButton add = new JButton("��������");
		JButton search = new JButton("�����");
		JTextField textfield = new JTextField();
		JButton delete = new JButton("�������");
		toolbar.add(add);
		toolbar.add(search);
		toolbar.add(textfield);
		toolbar.add(delete);
		
		ArrayList products = new ArrayList<>();
		MyTableModel tModel = new MyTableModel(products);
		JTable table = new JTable(tModel);
		table.setPreferredScrollableViewportSize(new Dimension(640,480));
		
		final JScrollPane scrl = new JScrollPane(table);
		scrl.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		frame.getContentPane().add(BorderLayout.CENTER, scrl);
		frame.getContentPane().add(BorderLayout.NORTH, toolbar);
		frame.setJMenuBar(menuBar);
		
		frame.setVisible(true);
		
		newItem.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e) 
			{
				LastFileName = null;
				if (prodcntr != 0)
					for (int i = prodcntr-1; i >= 0; i--)
						products.remove(i);
				prodcntr = 0;
				tModel.fireTableDataChanged();
			}
		});
		saveasItem.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e) 
			{
				String str;
				do
				{
					str=JOptionPane.showInputDialog(null, "������� ��� �����", "��������� ���",1);
					try
					{
						str.equals("");
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "�������� ���� ��������", "��������� ���",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} while (str.equals(""));
				try (FileWriter writer = new FileWriter("./"+str+".txt"))
				{
					writer.write(Integer.toString(prodcntr)+'\n');
					for (int i = 0; i < prodcntr; i++)
					{
						writer.write(((product) products.get(i)).getname()+'\n');
						writer.write(((product) products.get(i)).getcode()+'\n');
						writer.write(((product) products.get(i)).getprice()+'\n');
						writer.write(Integer.toString(((product) products.get(i)).getnum())+'\n');
					}
					writer.close();
				} 
				catch (IOException e1) 
				{
					
				}
			}			
		});
		saveItem.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e) 
			{
				String str;
				do
				{
					if (LastFileName != null)
						str = LastFileName;
					else
						str=JOptionPane.showInputDialog(null, "������� ��� �����", "���������",1);
					try
					{
						str.equals("");
					}
					catch (Exception ex)
					{
					JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������",JOptionPane.WARNING_MESSAGE);
					return;
					}
				} while (str.equals(""));
				try (FileWriter writer = new FileWriter("./"+str+".txt"))
				{
					writer.write(Integer.toString(prodcntr)+'\n');
					for (int i = 0; i < prodcntr; i++)
					{
						writer.write(((product) products.get(i)).getname()+'\n');
						writer.write(((product) products.get(i)).getcode()+'\n');
						writer.write(((product) products.get(i)).getprice()+'\n');
						writer.write(Integer.toString(((product) products.get(i)).getnum())+'\n');
					}
					writer.close();
				} 
				catch (IOException e1) 
				{
					
				}
				LastFileName = str;
			}			
		});
		openItem.addActionListener(new ActionListener() 
		{			
			public void actionPerformed(ActionEvent e) 
			{
				int newprodcntr;
				String str;
				do
				{
					str=JOptionPane.showInputDialog(null, "������� ��� �����", "�������",1);
					try
					{
						str.equals("");
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "�������� ���� ��������", "�������",JOptionPane.WARNING_MESSAGE);
						return;
					}
				} while (str.equals(""));
				try(FileReader reader = new FileReader("./"+str+".txt"))
		        {
					Scanner scan = new Scanner(reader);
					try
					{
						newprodcntr = Integer.parseInt(scan.nextLine());
						for (int i = 0; i < newprodcntr; i++)
						{
							products.add(new product(scan.nextLine(), scan.nextLine(), scan.nextLine(), Integer.parseInt(scan.nextLine())));
							for (int j = 0; j < i; j++)
							{
								if (((product) products.get(j+prodcntr)).getcode().equals(((product) products.get(i+prodcntr)).getcode()))
								{
									JOptionPane.showMessageDialog(null, "���� "+str+" �� ������� ���������!", "������",JOptionPane.ERROR_MESSAGE);
									for (int k = 0; k <= i; k++)
									{
										products.remove(prodcntr);
									}
									return;
								}
								if (!(isFloat(((product) products.get(j+prodcntr)).getprice())))
								{
									JOptionPane.showMessageDialog(null, "���� "+str+" �� ������� ���������!", "������",JOptionPane.ERROR_MESSAGE);
									for (int k = 0; k <= i; k++)
									{
										products.remove(prodcntr);
									}
									return;
								}
							}
						}
						for (int i = 0; i < prodcntr; i++)
							products.remove(0);
						tModel.fireTableDataChanged();
						LastFileName = str;
						prodcntr = newprodcntr;
					}
					catch (Exception ex)
					{
						JOptionPane.showMessageDialog(null, "���� "+str+" �� ������� ���������!", "������",JOptionPane.ERROR_MESSAGE);
					}
					reader.close();
		        }
				catch(IOException e1)
				{
					JOptionPane.showMessageDialog(null, "���� "+str+" �� ������!", "������",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		add.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = textfield.getText();
				if (name.equals(""))
					name=JOptionPane.showInputDialog(null, "������� �������� ������", "���������� ������",1);
				try
				{
					name.equals("");
				}
				catch (Exception ex)
				{
					JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String code = " ";
				String price = " ";
				boolean flag = false;
				int i;
				String strnum;
				for (i = 0; i < prodcntr; i++)
				{
					if (((product) products.get(i)).getcode().equals(name) || ((product) products.get(i)).getname().equals(name))
					{
						flag = true;
						break;
					}
				}
				if (flag)
				{
					do
					{
						strnum=JOptionPane.showInputDialog(null, "������� ����������", "���������� ������",1);
						try
						{
							strnum.equals("");
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������",JOptionPane.WARNING_MESSAGE);
							return;
						}
					} while ((strnum.equals("")) || (!(isDigit(strnum))));
					((product) products.get(i)).setnum(((product) products.get(i)).getnum()+Integer.parseInt(strnum));
					JOptionPane.showMessageDialog(null, "������ "+name+" ��������� � ���������� "+strnum+" ��.");
				} 
				else
				{
					do
					{
						code=JOptionPane.showInputDialog(null, "������� ���", "���������� ������ ������",1);
						try
						{
							code.equals("");
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������ ������",JOptionPane.WARNING_MESSAGE);
							return;
						}
						flag = true;
						for ( i = 0; i < prodcntr; i++)
						{
							if (((product) products.get(i)).getcode().equals(code))
							{
								JOptionPane.showMessageDialog(null, "����� � ����� "+code+" ��� ���� � �������!","������",JOptionPane.ERROR_MESSAGE);
								code = "";
								break;
							}
						}
					} while (code.equals("") && flag);
					do
					{
						price=JOptionPane.showInputDialog(null, "������� ����", "���������� ������ ������",1);
						try
						{
							price.equals("");
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������ ������",JOptionPane.WARNING_MESSAGE);
								return;
						}
					} while (price.equals("") || !(isFloat(price)));
					do
					{
						strnum=JOptionPane.showInputDialog(null, "������� ����������", "���������� ������ ������",1);
						try
						{
							strnum.equals("");
						}
						catch (Exception ex)
						{
							JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������ ������",JOptionPane.WARNING_MESSAGE);
							return;
						}
					} while ((strnum.equals("")) || (!(isDigit(strnum))));
					products.add(new product(name, code, price, Math.abs(Integer.parseInt(strnum))));
					prodcntr++;
				}
				tModel.fireTableDataChanged();
			}
		});
		search.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = textfield.getText();
				if (str.equals(""))
					JOptionPane.showMessageDialog(null, "����� ������ ������!","������",JOptionPane.ERROR_MESSAGE);
				else
				{
					boolean flag = true;
					String tempcode = str;
					for (int i = 0; i < prodcntr; i++)
					{
						if (((product) products.get(i)).getname().equals(str) || ((product) products.get(i)).getcode().equals(tempcode))
						{
							JOptionPane.showMessageDialog(null, ((product) products.get(i)).getname()+", "+String.valueOf(((product) products.get(i)).getprice())+" ���. " + ((product) products.get(i)).getnum() + " ��.");
							flag = false;
						}
					}
					if (flag)
						JOptionPane.showMessageDialog(null, "����� "+str+" �� ������", "����� ������" , 1);
				}
			}
		});
		delete.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String strnum;
				String str = textfield.getText();
				if (str.equals(""))
					JOptionPane.showMessageDialog(null, "����� ������ ������!","������",JOptionPane.ERROR_MESSAGE);
				else
				{
					boolean flag = true;
					String tempcode = str;
					for (int i = 0; i < prodcntr; i++)
					{
						if (((product) products.get(i)).getname().equals(str) || ((product) products.get(i)).getcode().equals(tempcode))
						{
							flag = false;
							do
							{
								strnum=JOptionPane.showInputDialog(null, "������� ����������", "�������� ������",1);
								try
								{
									strnum.equals("");
								}
								catch (Exception ex)
								{
									JOptionPane.showMessageDialog(null, "�������� ���� ��������", "���������� ������ ������", JOptionPane.WARNING_MESSAGE);
									return;
								}
							} while ((strnum.equals("")) || (!(isDigit(strnum))));
							if (((product) products.get(i)).getnum() == Integer.parseInt(strnum))
							{
								products.remove(i);
								JOptionPane.showMessageDialog(null, "����� "+str+" ����� � ���������� "+strnum+" ��.");
								prodcntr--;
							}
							else
							if (((product) products.get(i)).getnum() < Integer.parseInt(strnum))
							{
								JOptionPane.showMessageDialog(null, "������ "+str+" � ������� ������ "+((product) products.get(i)).getnum()+" ��.","��������������",JOptionPane.WARNING_MESSAGE);
							}
							else
							if (((product) products.get(i)).getnum() > Integer.parseInt(strnum))
							{
								((product) products.get(i)).setnum(((product) products.get(i)).getnum()-Integer.parseInt(strnum));
								JOptionPane.showMessageDialog(null, "����� "+str+" ����� � ���������� "+strnum+" ��.","�������� ������",1);
							}
						}
					}
					if (flag)
						JOptionPane.showMessageDialog(null, "����� "+str+" �� ������!","�������� ������",JOptionPane.WARNING_MESSAGE);
					tModel.fireTableDataChanged();
				}
			}
		});
	}
}
