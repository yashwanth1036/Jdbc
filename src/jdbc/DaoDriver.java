package jdbc;

import java.util.List;
import java.util.Scanner;

import com.tap.dao.EmployeeDAOImpl;
import com.tap.dto.Employee;

public class DaoDriver {

	public static void main(String[] args) {
		EmployeeDAOImpl emplDaoImpl =new EmployeeDAOImpl();
		List<Employee> employees = emplDaoImpl.getEmployees();
		
		for(Employee e :employees) {
			System.out.println(e);
		}
		Scanner sc = new Scanner(System.in);
		  System.out.println("Enter The Employee Id To Update "); 
		  int id =sc.nextInt(); 
		  Employee e = emplDaoImpl.getEmployee(id);
		  System.out.println(e);
		  
		  System.out.println("Enter The salary to update"); 
		  int newsalary =sc.nextInt(); 
		  e.setSalary(newsalary);
		  
		  System.out.println(emplDaoImpl.updateEmployee(e));
		 
		
		// Delete Implementation 
		System.out.println("Enter Id To delete");
		int deleteId = sc.nextInt();
		System.out.println(emplDaoImpl.deleteEmployee(deleteId));
		
		// insert Operation
		System.out.println("<------Insert Operation------>");
		System.out.println("Enter The Id to Insert");
		int insId = sc.nextInt();
		System.out.println("Enter The Name To Insert");
		String insName = sc.next();
		System.out.println("Enter The Designation To Insert");
		String insDesg = sc.next();
		System.out.println("Enter The salary To Insert");
		int insSalary = sc.nextInt();
		
		System.out.println(emplDaoImpl.insertEmployee(insId, insName, insDesg, insSalary));
		
	}

}
