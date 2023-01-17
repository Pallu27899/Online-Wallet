package Main;

import java.util.InputMismatchException;
import java.util.Scanner;
import Bean.CustomerBean;
import CustomerDao.*;
import exeptions.CustomerException;

public class Main {

	public static void main(String[] args) {
		System.out.println("*** welcome to Online Wallet System ***");
		System.out.println();
		System.out.println("welcome to Online Banking System");
		System.out.println("please select an option to  continue");
		System.out.println("1 : Signup");
		System.out.println("2 : login ");
		System.out.println("3 : Exit");
		Scanner sc = new Scanner(System.in);
		CustomerImpl ci =new CustomerImpl();
		
		String choice= sc.next();
		while(!choice.equals("3")){
		if(choice.equals("1")) {
			
			try {
				System.out.println("enter name");
				String cn=sc.next();
				System.out.println("create password ");
				String cp=sc.next();
				System.out.println("enter customer gender M/F");
				String cg=sc.next();
				while(!cg.equals("M") && !cg.equals("F")) {
					System.out.println("Please enter gender in M/F format");
					 cg=sc.next();
				}
				System.out.println("enter customer state location");
				String cs=sc.next();
				
				boolean v=true;
				while(v) {
					
					try {
						System.out.println("enter deposite amount ");
						
						int camt=sc.nextInt();
						String th="+"+camt;
						CustomerBean cb=new CustomerBean(cn, cp, cg, cs, camt,th );
						String cans=ci.AddCustomer(cb);
						System.out.println(cans);	
						v=false;
						
					    } 	
					    catch (InputMismatchException e) {
						System.out.println("please enter valid input");
						choice= sc.next();
						System.out.println();
						continue;
					}
				}	
			} catch (CustomerException e) {
				System.out.println(e.getMessage());
			}				
		}
		
//		//////////////////////////////////////////////////////////////////// customer queries
		else if(choice.equals("2")) {
			System.out.println("enter username");
			String n=sc.next();
			System.out.println("enter password");
			String p=sc.next();
			try {
				
				boolean log=ci.login(n, p);
				
				if(log) {
					System.out.println();
					System.out.println("login successfull you are in "+n+"'s user account");
					System.out.println("please select an option according to the operation you want to perform");
					System.out.println("1 : Withdrawl");
					System.out.println("2 : Deposit");
					System.out.println("3 : see transaction History");
					System.out.println("4 : Exit");
					String slc=sc.next();
					
					while(!slc.equals("4")) {
						//////
                     if(slc.equals("1")){ 
						
						try {
//							System.out.println("enter customer name ");
//							String cna=sc.next();
							
							String A5=ci.Withdraw(n);
							
							System.out.println(A5);
							
						} catch (InputMismatchException e) {
						System.out.println("Error accured due to invalid input");
						}catch (CustomerException e) {
							System.out.println(e.getMessage());
							
						}
					}
                     else if(slc.equals("2")){ 
 						
 						try {
// 							System.out.println("enter customer name ");
// 							String cna=sc.next();
 							
 							String A5=ci.Withdraw(n);
 							
 							System.out.println(A5);
 							
 						} catch (InputMismatchException e) {
 						System.out.println("Error accured due to invalid input");
 						}catch (CustomerException e) {
 							System.out.println(e.getMessage());
 							
 						}
 					}
                     
					/////////
					else if(slc.equals("3")) {
						
						String his=ci.transactions( n);
						System.out.println(his);
					}else {
						System.out.println("please select a valid option");
					}
                     
                     
					System.out.println();
					System.out.println("please select an option according to the operation you want to perform");
					System.out.println("1 : Withdrawl");
					System.out.println("2 : Deposit");
					System.out.println("3 : see transaction History");
					System.out.println("4 : Exit");
				
					 slc=sc.next();
					}
					System.out.println("Logging out of "+n+"'s account...");
					
				}else {
					System.out.println("incorrect username or password");
				}
				
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		else{
			System.out.println("please select a valid option");
		}
		System.out.println();
		System.out.println("please select an option to  continue");
		System.out.println("1 : Signup");
		System.out.println("2 : login ");
		System.out.println("3 : Exit");
		choice=sc.next();
		
		}	
		System.out.println("Thanks for visiting");
	}
	
}
