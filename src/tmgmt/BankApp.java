package tmgmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class BankApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url ="jdbc:mysql://localhost:3306/tap_bank";
		String uname="root";
		String pwd ="Yash8999@123";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, uname, pwd);	
			Scanner sc = new Scanner(System.in);
			
			// Login Module
			System.out.println("Welcome To CanaraBank");
			System.out.println("Enter your Account Number");
			int senderAcc = sc.nextInt();
			System.out.println("Enter Your Pin");
			int senderPin = sc.nextInt();
			PreparedStatement pstmt = con.prepareStatement("select * from account where " + 
			" acc_no = ?  and  pin = ? ");
			 pstmt.setInt(1, senderAcc);
			 pstmt.setInt(2, senderPin);
			 ResultSet res1 = pstmt.executeQuery();
			 res1.next();
			 String senderName = res1.getString(2);
			 int bal = res1.getInt(4);
			 System.out.println("Welcome MR " + senderName);
			 System.out.println("Your Current Balanace is :" + bal);
			 
			 // Transfer Module
			 con.setAutoCommit(false);
			 System.out.println("Enter The Benificiary Account Numebr");
			 int receiveAcc = sc.nextInt();
			 System.out.println("Enetr The Transfer Amount");
			 int transferAmt = sc.nextInt();
			 Savepoint s = con.setSavepoint();
			PreparedStatement pstmt1 = con.prepareStatement("update account set balance = balance - ? "
			 		+ " where acc_no = ? ");
			pstmt1.setInt(1, transferAmt);
			pstmt1.setInt(2, senderAcc);
			pstmt1.executeUpdate();
			
			// Incoming Request
			
			System.out.println(senderName + " wants to send Amount in Rs " + transferAmt);
			System.out.println("Enter Y To Accept ");
			System.out.println("Enter N To Reject");
			String choice = sc.next();
			
			if(choice.equalsIgnoreCase("Y")) {
				PreparedStatement pstmt2 =  con.prepareStatement("update account set balance = balance + ? "
				 		+ " where acc_no = ? ");
				pstmt2.setInt(1, transferAmt);
				pstmt2.setInt(2,receiveAcc);
				
			    pstmt2.executeUpdate();
				
				PreparedStatement pstmt3 = con.prepareStatement(" select * from account "
						+ " where acc_no = ? ");
				pstmt3.setInt(1, receiveAcc);
				ResultSet res2 =  pstmt3.executeQuery();
				res2.next();
				System.out.println("Congradulations  " + res2.getString(2));
				System.out.println(transferAmt+" Amount Credited To Your Account ");
				System.out.println("Your Current Balance is :" +res2.getInt(4));
				
				
			}else {
				con.rollback(s);
				PreparedStatement pstmt4 = con.prepareStatement(" select * from account "
						+ " where acc_no = ? ");
				pstmt4.setInt(1, receiveAcc);
				ResultSet res3 =  pstmt4.executeQuery();
				res3.next();
				System.out.println("Congradulations MR" + res3.getString(2));
				System.out.println(transferAmt+" Amount Credited To Your Account ");
				System.out.println("Your Current Balance is :" +res3.getInt(4));
				
			}
			con.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
