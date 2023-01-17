package CustomerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Bean.CustomerBean;
import Utility.Db;
import exeptions.CustomerException;

public class CustomerImpl implements Customer
{

	@Override
	public String AddCustomer(CustomerBean c) throws CustomerException {
		// TODO Auto-generated method stub
		String name=c.getName();
		String password=c.getPassword();
		String gender=c.getGender();
		String state=c.getState();
		int balance=c.getAccountBalance();
		String transactions=c.getTransactionDetails();
		
		try (Connection conn=Db.connect()){

			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				throw new CustomerException("username already taken please select another name ");
			}else {
				
				PreparedStatement ps=conn.prepareStatement("insert into customer(username,password,gender,state,Account_Balance,transaction_history) values(?,?,?,?,?,?)");
				
				ps.setString(1, name);
				ps.setString(2, password);
				ps.setString(3, gender);
				ps.setString(4, state);
				ps.setInt(5,balance);
				ps.setString(6, transactions);
//			System.out.println("4567");
				
				int x=ps.executeUpdate();
				System.out.println(x);
				if(x>0) {
					PreparedStatement nn=conn.prepareStatement("select * from customer where username =? ");
					nn.setString(1, name);
					ResultSet nri=nn.executeQuery();
					
					nri.next();
					return   "Account created Successfully \n Account no : " +nri.getInt("accountNo")+" | "+" username : "+nri.getString("username")+" | "+" password : "+nri.getString("password")+" | "+" Gender : "+nri.getString("gender")+" | "+" State : "+nri.getString("state")+" | "+" Account Balance : "+nri.getInt("Account_Balance")+" | "+" Transaction History : "+nri.getString("Transaction_history");
											
				}else {
					throw new CustomerException("Account creation unsuccessfull: TEchnical Error");
				
			   }	
		}
			} catch (SQLException e){
//			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
	}

	
	
//	2...................................................................
	@Override
	public boolean login(String name, String password) throws CustomerException {
	try (Connection conn=Db.connect()){
			
			PreparedStatement pst =conn.prepareStatement("select * from customer where username=? and password=?" );
			pst.setString(1, name);
			pst.setString(2, password);
			
			ResultSet rs=pst.executeQuery();
//			System.out.println(rs);
			if(rs.next()) {
//				System.out.println("hii");
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		
	}

	

}
  


	@Override
	public String transactions(String name) throws CustomerException {
		// TODO Auto-generated method stub
		try (Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			if(nrs.next()) {
				return "Transcaction History : "+nrs.getString("transaction_history");
			}else {
				throw new CustomerException("No user found with this name");
			}
			
		} catch (SQLException e){
			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
		
		
	}
	
	


	@Override
	public String Withdraw(String name) throws CustomerException {
		try(Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				Scanner sc=new Scanner(System.in);	
				System.out.println("please enter amount");
				Long amt=sc.nextLong();
				if(amt<=nrs.getInt( "Account_Balance")) {
					String tt=nrs.getString("Transaction_history")+"  ,-"+amt;
					PreparedStatement aas=conn.prepareStatement("update customer set Account_Balance = Account_Balance -? ,Transaction_history=?  where username = ?");
					aas.setLong(1, amt);
					aas.setString(2, tt);
					aas.setString(3, name);
					
					int z=aas.executeUpdate();
					if(z>0) {
					Long bala=nrs.getLong("Account_Balance")-amt;
		 			return"withdrawal successfull \n "+"Account no : " +nrs.getInt("accountNo")+" | "+" username : "+nrs.getString("username")+" | "+" password : "+nrs.getString("password")+" | "+" Gender : "+nrs.getString("gender")+" | "+" State : "+nrs.getString("state")+" | "+" Account Balance : "+bala+" | "+" Last Transaction : -"+amt;
					}else {
						throw new CustomerException("some error accured");
					}
				    
				}else{
					throw new CustomerException("not enough money to withdraw");
				}				
			}
			else {
				throw new CustomerException("NO Customer found for  user name : "+name);
			}
			
			
		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
	}



	@Override
	public String Deposit(String name) throws CustomerException {
		// TODO Auto-generated method stub
		
		try(Connection conn=Db.connect()){
			PreparedStatement cps=conn.prepareStatement("select * from customer where username =? ");
			cps.setString(1, name);
			ResultSet nrs=cps.executeQuery();
			
			if(nrs.next()) {
				Scanner sc=new Scanner(System.in);	
				
				
				
				System.out.println("please enter amount");
				Long amt=sc.nextLong();
				
					String tt=nrs.getString("Transaction_history")+"  ,+"+amt;
					PreparedStatement aas=conn.prepareStatement("update customer set Account_Balance = Account_Balance +? ,Transaction_history=?  where username = ?");
					aas.setLong(1, amt);
					aas.setString(2, tt);
					aas.setString(3, name);
					
					int z=aas.executeUpdate();
					if(z>0) {
						Long bala=nrs.getLong("Account_Balance")+amt;
			 			return"Deposit successfull \n "+"Account no : " +nrs.getInt("accountNo")+" | "+" username : "+nrs.getString("username")+" | "+" password : "+nrs.getString("password")+" | "+" Gender : "+nrs.getString("gender")+" | "+" State : "+nrs.getString("state")+" | "+" Account Balance : "+bala+" | "+" Last Transaction : +"+amt;
						}
					else {
						throw new CustomerException("some error accured");
					}	
			}
			else {
				throw new CustomerException("NO Customer found for  user name : "+name);
			}
			
			
		} catch (SQLException e) {
//			System.out.println(e.getMessage());
			throw new CustomerException(e.getMessage());
			// TODO: handle exception
		}
		
	}


	}
