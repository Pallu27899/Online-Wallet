package CustomerDao;


import Bean.CustomerBean;
import exeptions.CustomerException;
public interface Customer {

	public String AddCustomer(CustomerBean customer)throws CustomerException;
	
	public boolean login(String name,String password)throws CustomerException ;
	
	public String transactions(String name)throws CustomerException;
	
	public String Withdraw(String name) throws CustomerException ;
	
	public String Deposit(String name) throws CustomerException ;
	
	
	
	
}
