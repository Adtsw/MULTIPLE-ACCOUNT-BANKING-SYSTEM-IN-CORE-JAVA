package bank;

public class Saving extends Bank{
   
    private static double interestRate;
    
     Saving(int account_number,int user_id,String bank_name,String accountholderFullName,int mobile_number,double bank_balance,String City_name)
    {
    	 super(account_number,user_id,bank_name,accountholderFullName,mobile_number,bank_balance,City_name);
    	 interestRate=5;
    	 
    }
     @Override
 	public String toString() {
    	 return " Saving account Details:\n"
	 				+ " Account_Number - "+this.getaccount_number() +"\n"
	 				+ " User ID - "+this.getUser_id()+"\n"
	 				+ " Client Name - "+this.getaccountholderFullName()+"\n"
	 				+ " Mobile Number - "+this.getmobile_number()+"\n"
	 				+ " Balance - "+this.getbank_balance()+"\n"
	 				+ " City - "+this.getCity_name()+"\n";
 	}
     }