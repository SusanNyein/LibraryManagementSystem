
import java.io.File;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;


public class backUpProcess {
	String filepath=null;
	File outFile;
	PrintWriter pw;
	public backUpProcess(String file)
	{
		filepath=file;
		try {
			outFile=new File(filepath+"/backup.txt");
			if(outFile.exists())
			{
				outFile.delete();
				System.out.println("DELETE");
			}
			
				pw=new PrintWriter(outFile);

				ResultSet resultBookType=new databaseconnection().getStatement().executeQuery("select * from Book_Type");
				writeColumn(resultBookType,"Book Type Table");
				
				ResultSet resultBook=new databaseconnection().getStatement().executeQuery("select * from Book");
				writeColumn(resultBook,"Book Table");
				
				ResultSet resultMember=new databaseconnection().getStatement().executeQuery("select * from Member");
				writeColumn(resultMember,"Member Table");
				
				ResultSet resultBookLoan=new databaseconnection().getStatement().executeQuery("select * from BookLoan");
				writeColumn(resultBookLoan,"BookLoan Table");
				
				ResultSet resultReader=new databaseconnection().getStatement().executeQuery("select * from Reader_List");
				writeColumn(resultReader,"ReaderList Table");
				
				ResultSet resultStudent=new databaseconnection().getStatement().executeQuery("select * from Student");
				writeColumn(resultStudent,"Student Table");
				
				ResultSet resultTeacher=new databaseconnection().getStatement().executeQuery("select * from Teacher");
				writeColumn(resultTeacher,"Teacher Table");
				
				ResultSet resultBorrower=new databaseconnection().getStatement().executeQuery("select * from Borrower_history");
				writeColumn(resultBorrower,"Borrower Table");
				
				ResultSet resultLostBook=new databaseconnection().getStatement().executeQuery("select * from Lost_Book");
				writeColumn(resultLostBook,"LostBook Table");
				
				ResultSet resultOverdate=new databaseconnection().getStatement().executeQuery("select * from Overdate_List");
				writeColumn(resultOverdate,"Overdate Table");
				
				JOptionPane.showMessageDialog(null,main.getFilePath()+"/backup.txt has been saved");
				pw.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
	}
	public void writeColumn(ResultSet result,String title)
	{
		StringBuffer sb=new StringBuffer(1000);
		sb.delete(0,sb.length());
		pw.println(title);
		try {
			
		ResultSetMetaData columnName=result.getMetaData();
		
			for(int i=1;i<=columnName.getColumnCount();i++)
			{
				sb.append(columnName.getColumnName(i)+" \t ");
			}
			pw.write(sb.toString());
			pw.println();
			sb.delete(0,sb.length());               // clear the string buffer 
			while(result.next())
			{
				for(int i=1;i<=columnName.getColumnCount();i++)
				{
					sb.append(result.getString(i)+" \t ");
				}
				
				pw.write(sb.toString());
				pw.println();
				sb.delete(0,sb.length());
			}
			pw.println();
			pw.println();		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e.getMessage(),null,JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
