package CustomerDao;

import java.util.Scanner;

import Bean.CustomerBean;
import exeptions.CustomerException;

public class ccheck {

	public static void main(String[] args) {
		
		CustomerImpl ci =new CustomerImpl();
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("enter customer name");
		String cn=sc.next();
		System.out.println("create a password for customer");
		String cp=sc.next();
		System.out.println("enter customer gneder M/F");
		String cg=sc.next();
		System.out.println("enter customer state location");
		String cs=sc.next();
		System.out.println("enter deposite amount ");
		int camt=sc.nextInt();
		String th=""+camt;
		CustomerBean cb=new CustomerBean(cn, cp, cg, cs, camt,th );
		try {
			String cans=ci.AddCustomer(cb);
			System.out.println(cans);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
