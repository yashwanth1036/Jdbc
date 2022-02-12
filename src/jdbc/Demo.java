package jdbc;
import java.sql.Connection; // interface
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.ResultSet; // interface
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		String url = "jdbc:mysql://localhost:3306/employee";
		String un="root";
		String pwd="Yash8999@123";
		Connection con = null;
		PreparedStatement pstmt = null;
		// ResultSet res = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Successfully loaded");
			
			con= DriverManager.getConnection(url, un, pwd);
			System.out.println("Connection Successfully Established");
			
			String query = "insert into emp(`id`,`name`,`desg`,`sal`) values (?,?,?,?)";
			pstmt = con.prepareStatement(query);
			
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter your id");
			int id = scan.nextInt();
			System.out.println("Enter your name");
			String name = scan.next();
			System.out.println("Enter your desg");
			String desg = scan.next();
			System.out.println("Enter your salary");
			int sal = scan.nextInt();
			
			pstmt.setInt(1,id);
			pstmt.setString(2,name);
			pstmt.setString(3, desg);
			pstmt.setInt(4, sal);
			
			pstmt.execute();
			System.out.println("Query Excecuted Successfully");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(SQLException f) {
			f.printStackTrace();
		}
		try {
			//res.close();
			pstmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
