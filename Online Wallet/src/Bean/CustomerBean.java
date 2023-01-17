package Bean;

public class CustomerBean {

	private String name;
	private String password;
	private String gender;
	private String state;
	private int AccountBalance;
	private String TransactionDetails;


	
	
	@Override
	public String toString() {
		return "CustomerBean [name=" + name + ", password=" + password + ", gender=" + gender + ", state=" + state
				+ ", AccountBalance=" + AccountBalance + ", TransactionDetails=" + TransactionDetails + "]";
	}


	
	
	public CustomerBean(String name, String password, String gender, String state, int accountBalance,
			String transactionDetails) {
		super();
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.state = state;
		AccountBalance = accountBalance;
		TransactionDetails = transactionDetails;
	}




	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountBalance() {
		return AccountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		AccountBalance = accountBalance;
	}
	public String getTransactionDetails() {
		return TransactionDetails;
	}
	public void setTransactionDetails(String transactionDetails) {
		TransactionDetails = transactionDetails;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
   
	
}
