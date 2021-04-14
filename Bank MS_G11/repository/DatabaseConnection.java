package repository;
import java.sql.*;

public class DatabaseConnection
{
	Connection con;
	Statement st;
	ResultSet result;
	
	public DatabaseConnection()
	{
		
	}
	public void openConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/g11", "root", "");
			st=con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public void closeConnection()
	{
		try{
			if(result!=null)
				con.close();
			if(result!=null)
				st.close();
			if(result!=null)
				result.close();
		}
		
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}