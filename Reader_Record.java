
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JApplet;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
public class Reader_Record extends JApplet {
	String[] columnnames;
	int row=1000;
	Object[][] data=new Object[row][10];
	DefaultTableModel dft;
	JTable jtable;
	Connection connection;
	
	public Reader_Record()
	{
		
		//fill member data from database into table
		fillReaderData();
		
		//create a table with data
		jtable=new JTable(dft);
		jtable.setRowHeight(20);
		jtable.setSelectionBackground(new Color(100,240,150));
		jtable.setGridColor(new Color(100,240,150));
		jtable.setRowSelectionAllowed(true);
		jtable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		
		JScrollPane p;
		add(p=new JScrollPane(jtable),BorderLayout.CENTER);
		p.setBorder(new TitledBorder("YCC Library Daily Record"));
		
	}
	public void fillReaderData()
	{
		try {
			//connect to database ycclibrary
			databaseconnection	connection=new databaseconnection();
			Statement statement=connection.getStatement();
			
			//retrieve data from member
			ResultSet resultSet=statement.executeQuery("select * from Reader_List");
			
			//add table column names into array
			ResultSetMetaData rs=resultSet.getMetaData();
			columnnames=new String[rs.getColumnCount()];
			for(int i=1;i<=rs.getColumnCount();i++)
			{
				columnnames[i-1]=rs.getColumnName(i);
			}
			
			//add data from table into array
			int j=0;
			while(resultSet.next())
			{
				for(int i=1;i<=rs.getColumnCount();i++)
				{
					data[j][i-1]=resultSet.getObject(i);
				}
				j++;
			}
			
			//change dataarray into fixed sized
			Object[][] dataarray=new Object[j][data[0].length];
			for(int i=0;i<j;i++)
			{
				for(int k=0;k<dataarray[0].length;k++)
					dataarray[i][k]=data[i][k];
			}
		
			//create default table modal object eith data and column name
			dft=new DefaultTableModel(dataarray,columnnames);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage(),"Error Message",JOptionPane.ERROR_MESSAGE);
		}
		
		}	
	
	}



