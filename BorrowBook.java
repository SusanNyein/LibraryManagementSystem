
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class BorrowBook extends JFrame{
	
	private JLabel photo=new JLabel();
	private JLabel name=new JLabel("Name :");
	private JLabel card_no=new JLabel("Card_no :");
	private JLabel book1=new JLabel("Book1 :");
	private JLabel book2=new JLabel("Book2 :");
	private JLabel date_in=new JLabel("Date in :");
	private JLabel due_date=new JLabel("Due Date :");
	
	private JTextField jtfname=new JTextField(15);
	private JTextField jtfcard_no=new JTextField(15);
	private JTextField jtfisbn1=new JTextField(15);
	private JTextField jtfisbn2=new JTextField(15);
	private JTextField jtftitle1=new JTextField(15);
	private JTextField jtftitle2=new JTextField(15);
	
	private SpinnerNumberModel spYear=new SpinnerNumberModel(2014,2000,3000,1);
	private SpinnerNumberModel spMonth=new SpinnerNumberModel(1,1,12,1);
	private SpinnerNumberModel spDay=new SpinnerNumberModel(1,1,31,1);
	
	private JButton exit=new JButton("Exit");
	private JButton borrow=new JButton("Borrow");
	private JButton cancel=new JButton("Cancel");
	
	private JLabel imagelabel=new JLabel(new ImageIcon("src/photo.jpg"));
	
	private SpinnerNumberModel dueYear=new SpinnerNumberModel(2014,2000,3000,1);
	private SpinnerNumberModel dueMonth=new SpinnerNumberModel(1,1,12,1);
	private SpinnerNumberModel dueDay=new SpinnerNumberModel(1,1,31,1);
	
	public BorrowBook()
	{
		//insert current date into spinner
		Date d=new Date();
		spYear.setValue(d.getYear()+1900);
		spMonth.setValue(d.getMonth()+1);
		spDay.setValue(d.getDate());
		dueYear.setValue(d.getYear()+1900);
		dueMonth.setValue(d.getMonth()+1);
		dueDay.setValue(d.getDate());
		
		jtftitle1.setEditable(false);
		jtftitle2.setEditable(false);
		
		setLabelFontandColor(name);
		setLabelFontandColor(card_no);
		setLabelFontandColor(book1);
		setLabelFontandColor(book2);
		setLabelFontandColor(date_in);
		setLabelFontandColor(due_date);
		
		jtfisbn1.setText("ISBN");
		jtfisbn2.setText("ISBN");
		jtftitle1.setText("Title");
		jtftitle2.setText("Title");
		
		JPanel photoPanel=new JPanel(new BorderLayout());
			photo.setIcon(new ImageIcon("src/borrow.png"));
			photoPanel.add(photo,BorderLayout.CENTER);
		
		JPanel pdate_in=new JPanel();
			pdate_in.setLayout(new GridLayout(1,3,3,3));
			pdate_in.add(new JSpinner(spYear));
			pdate_in.add(new JSpinner(spMonth));
			pdate_in.add(new JSpinner(spDay));
		
		JPanel pdue_date=new JPanel();
			pdue_date.setLayout(new GridLayout(1,3,3,3));
			pdue_date.add(new JSpinner(dueYear));
			pdue_date.add(new JSpinner(dueMonth));
			pdue_date.add(new JSpinner(dueDay));
			
		JPanel first2=new JPanel();
			first2.setLayout(new GridLayout(2,2));
			first2.add(name);
			first2.add(jtfname);
			first2.add(card_no);
			first2.add(jtfcard_no);
			
		JPanel book1panel=new JPanel();
			book1panel.setLayout(new GridLayout(1,2));
			book1panel.add(jtfisbn1);
			book1panel.add(jtftitle1);
		
		JPanel book2panel=new JPanel();
			book2panel.setLayout(new GridLayout(1,2));
			book2panel.add(jtfisbn2);
			book2panel.add(jtftitle2);
			
		JPanel third2=new JPanel();
			third2.setLayout(new GridLayout(4,2));
			third2.add(book1);
			third2.add(book1panel);
			third2.add(book2);
			third2.add(book2panel);
			third2.add(date_in);
			third2.add(pdate_in);
			third2.add(due_date);
			third2.add(pdue_date);
		
		JPanel panel=new JPanel();
			panel.setLayout(new BorderLayout());
			panel.add(first2,BorderLayout.NORTH);
			panel.add(third2,BorderLayout.CENTER);	
			
		Box box3=Box.createHorizontalBox();
			box3.add(panel);
			box3.add(imagelabel);
			
		JPanel button=new JPanel();
			button.setLayout(new FlowLayout(FlowLayout.RIGHT));
			button.add(exit);
			button.add(borrow);
			button.add(cancel);
		
		JSplitPane jsp1=new JSplitPane(JSplitPane.VERTICAL_SPLIT,photoPanel,box3);
		JSplitPane jsp=new JSplitPane(JSplitPane.VERTICAL_SPLIT,jsp1,button);
		add(jsp);
		
		setSize(580,365);
		setLocationRelativeTo(null);
		setVisible(true);
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clear();
			}
		});
		
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clear();
				setVisible(false);
				
			}
		});
		
		
		spMonth.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent ce) 
			{
				int month=((Integer)(spMonth.getValue())).intValue();
				if(month==2)
				{
					int year=((Integer)(spYear.getValue())).intValue();
					if(year%4==0)
					{
						spDay.setMaximum(29);
						spDay.setValue(29);
					}
					else
					{
						spDay.setMaximum(28);
						spDay.setValue(28);
					}
				}
				else if(month==4||month==6||month==9||month==11)
				{
					spDay.setMaximum(30);
					spDay.setValue(30);
				}
				else
				{
					spDay.setMaximum(31);
					spDay.setValue(31);
				}
			}
		});
		
		dueMonth.addChangeListener(new ChangeListener() 
		{
			public void stateChanged(ChangeEvent ce) 
			{
				int month=((Integer)(dueMonth.getValue())).intValue();
				if(month==2)
				{
					int year=((Integer)(dueYear.getValue())).intValue();
					if(year%4==0)
					{
						dueDay.setMaximum(29);
						dueDay.setValue(29);
					}
					else
					{
						dueDay.setMaximum(28);
						dueDay.setValue(28);
					}
				}
				else if(month==4||month==6||month==9||month==11)
				{
					dueDay.setMaximum(30);
					dueDay.setValue(30);
				}
				else
				{
					dueDay.setMaximum(31);
					dueDay.setValue(31);
				}
			}
		});
		
		jtfisbn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			
				String s=jtfisbn1.getText().trim();
				String a=null;
				try{
					databaseconnection db=new  databaseconnection();
					Connection	connection=db.getConnection();
					Statement stmt=db.getStatement();
					ResultSet result=stmt.executeQuery("select Title from book where ISBN='"+s+"'");
					boolean queryresult=result.next();
					if(queryresult){
						a=result.getString(1);
						jtftitle1.setText(a);
						
					}
					else 
						JOptionPane.showMessageDialog(null,"Library does not have this book!");
					
					connection.close();
				}catch(Exception ex){
					
				}
			}
		});
		
		
		jtfisbn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String s=jtfisbn2.getText().trim();
				String a=null;
				try{
					databaseconnection db=new  databaseconnection();
					Connection	connection=db.getConnection();
					Statement stmt=db.getStatement();
					ResultSet result=stmt.executeQuery("select Title from book where ISBN='"+s+"'");
					boolean queryresult=result.next();
					if(queryresult){
						a=result.getString(1);
						jtftitle2.setText(a);
						
					}
					else 
						JOptionPane.showMessageDialog(null,"Library does not have this book!");
					
					connection.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
		borrow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)	{
				boolean validcardResult=validCard();
				boolean flag=false;
				if(!validcardResult)
				{
					setVisible(true);
				}
				else
				{
				try{
					String Limited = null;
					int no_copy=0;
					String d_in=((Integer)(spYear.getValue())).intValue()+"_"+((Integer)(spMonth.getValue())).intValue()+"_"
					+((Integer)(spDay.getValue())).intValue();
					String due=((Integer)(dueYear.getValue())).intValue()+"_"+((Integer)(dueMonth.getValue())).intValue()+"_"
					+((Integer)(dueDay.getValue())).intValue();
					int card_no=Integer.parseInt(jtfcard_no.getText());
					
					Statement checkCard=new databaseconnection().getStatement();
					ResultSet resultCard=checkCard.executeQuery("select card_no from bookloan where card_no="+Integer.parseInt(jtfcard_no.getText()));
					boolean existCard=resultCard.next();
					if(!existCard)
					{
					
						Statement checkLimited=new databaseconnection().getStatement();
						ResultSet result=checkLimited.executeQuery("select Limited from book where ISBN='"+jtfisbn1.getText()+"'");
						if(result.next())
						{
							Limited=result.getString(1);
					
						}
					
						if(!Limited.equalsIgnoreCase("limited"))
						{
							if(Limited.equalsIgnoreCase("available"))
							{
						
								Statement stmt1=new  databaseconnection().getStatement();
								stmt1.executeUpdate("insert into bookloan values('"+jtfisbn1.getText()+"',"+card_no+",'"+jtfname.getText()+"','"+
								d_in+"','"+due+"')");
						
						
								ResultSet noofcopy=stmt1.executeQuery("select No_of_copy from book where ISBN='"+jtfisbn1.getText()+"'");
								while(noofcopy.next())
								{
									no_copy=noofcopy.getInt(1);
								}
							
								no_copy-=1;
								if(no_copy==0)
								{
									stmt1.executeUpdate("update book set Limited='not available' where ISBN='"+jtfisbn1.getText()+"'");
								}
								stmt1.executeUpdate("update book set no_of_copy="+no_copy+" where ISBN='"+jtfisbn1.getText()+"'");
								flag=true;
								if(!jtfisbn2.getText().endsWith("ISBN"))
								{
										
									Statement checklimited=new databaseconnection().getStatement();
									ResultSet result2=checkLimited.executeQuery("select Limited from book where ISBN='"+jtfisbn2.getText()+"'");
									if(result2.next())
									{
										Limited=result2.getString(1);
							
									}
									if(!Limited.equalsIgnoreCase("limited"))
									{
										if(Limited.equalsIgnoreCase("available"))
										{
								
											Statement stmt2=new  databaseconnection().getStatement();
											stmt2.executeUpdate("insert into bookloan values('"+jtfisbn2.getText()+"',"+card_no+",'"+jtfname.getText()+"','"+
											d_in+"','"+due+"')");
									
											ResultSet noofcopy2=stmt2.executeQuery("select No_of_copy from book where ISBN='"+jtfisbn2.getText()+"'");
											while(noofcopy2.next())
											{
												no_copy=noofcopy2.getInt(1);
											}
										
											no_copy-=1;
											if(no_copy==0)
											{
												stmt2.executeUpdate("update book set Limited='not available' where ISBN='"+jtfisbn2.getText()+"'");
											}
											stmt2.executeUpdate("update book set no_of_copy="+no_copy+" where ISBN='"+jtfisbn2.getText()+"'");
											flag=true;
										}
										else JOptionPane.showMessageDialog(null,""+jtfisbn2.getText()+" is not available!");
									}
									else
										JOptionPane.showMessageDialog(null,""+jtfisbn2.getText()+" is not borrowed!");
								}
						}
						else JOptionPane.showMessageDialog(null,""+jtfisbn1.getText()+" is not available!");
					}
					
					else 
						JOptionPane.showMessageDialog(null,""+jtfisbn1.getText()+" is not borrowed!");
					
				if(flag)
						JOptionPane.showMessageDialog(null,"Have Recorded!");
				
				}
				else
				{
					String message=jtfcard_no.getText()+" has borrowed-books in ";
					Statement msg=new databaseconnection().getStatement();
					ResultSet books=msg.executeQuery("select Date_in,ISBN from bookloan where card_no="+Integer.parseInt(jtfcard_no.getText()));
					while(books.next())
					{
						message+=books.getString(1);
						message+=" : ";
						message+=books.getString(2);
						message+="\n";
					}
					JOptionPane.showMessageDialog(null,message,null,JOptionPane.WARNING_MESSAGE);
				}
				}catch(Exception ex){
					ex.printStackTrace();
				}
				
				}
			}
			});
			
		
	
	}
	public void clear()
	{
		jtfname.setText(null);
		jtfcard_no.setText(null);
		jtfisbn1.setText("ISBN");
		jtfisbn2.setText("ISBN");
		jtftitle1.setText(null);
		jtftitle2.setText(null);
		jtftitle2.setText(null);
		imagelabel.setIcon(new ImageIcon("src/photo.jpg"));
		imagelabel.validate();
		
	}
	
	public void setLabelFontandColor(JLabel l)
	{
		l.setFont(new Font("Algerian",Font.BOLD, 16));
		l.setForeground(Color.ORANGE);
	}
	
	public boolean validCard()
	{
		String name=jtfname.getText().trim();
		String s=jtfcard_no.getText().trim();
		String path=null;
		String a=null;
		try{
			databaseconnection db=new  databaseconnection();
			Connection	connection=db.getConnection();
			Statement stmt=db.getStatement();
			ResultSet result=stmt.executeQuery("select card_no from member where card_no="+s+" and name='"+name+"'");
			boolean queryresult=result.next();
			if(!queryresult)
			{
				JOptionPane.showMessageDialog(null,"Card_no and name are not match!");
				return false;
			}
			else{
				ResultSet result1=stmt.executeQuery("select photo_path from student where card_no="+s);
				boolean studentresult=result1.next();
				if(studentresult)
				{
					path=result1.getString(1);
					//System.out.println(path);
					if(!path.isEmpty())
					{
						imagelabel.setIcon(new ImageIcon(path));
						return true;
					}
				}
				else
				{
					ResultSet result2=stmt.executeQuery("select photo_path from teacher where card_no="+s);
					boolean teacherresult=result2.next();
					if(teacherresult)
					{
						path=result2.getString(1);
						//System.out.println(path);
						if(!path.isEmpty())
						{
							imagelabel.setIcon(new ImageIcon(path));
							return true;
						}
					}
				}
					
			}
			
			
			connection.close();
			
				
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BorrowBook();
		
	}

}
