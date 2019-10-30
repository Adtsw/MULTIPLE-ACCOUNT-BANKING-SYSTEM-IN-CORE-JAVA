package bank;


import java.io.Serializable;
//Defining an abstract class and implementing it in Bank class
public class Bank implements Serializable{
			private int account_number ;
			private int user_id;
			private String bank_name ;
			private String accountholderFirstName ;
			private String accountholderLastName ;
			private String accountholderFullName ;
			private int mobile_number;
			private double bank_balance;
			private String City_name;
			
			//Creating Parameterized Constructor
			public Bank(int account_number,int user_id,String bank_name,String accountholderFullName,int mobile_number,double bank_balance,String City_name) 
			{
				super();
				splitFullName(accountholderFullName);
				this.user_id=user_id;
				this.account_number = account_number;
				this.bank_name = bank_name;
				this.accountholderFullName = accountholderFullName;
				this.mobile_number=mobile_number;
				this.bank_balance=bank_balance;
				this.City_name=City_name;
			}
			//Calling a method to split the name
			private void splitFullName(String fullName) {
				String[] splittedName = fullName.split("\\s+");
				setaccountholderFirstName(splittedName[0]);
				setaccountholderLastName(splittedName[splittedName.length - 1]);
			}
			//To print the parameters
			@Override
			public String toString() {
				return " account_number=" + account_number + "User_ID" +user_id+ ", bank_name=" + bank_name + ", accountholderFirstName=" + accountholderFirstName
						+ ", accountholderLastName=" + accountholderLastName + ", accountholderFullName=" + accountholderFullName+ "Mobile_number" +mobile_number+ "Bank_balance"
						+bank_balance+"City_name"+City_name;
			}
			//Getters and Setters for the parameters
			public int getaccount_number() {
				return account_number;
			}

			public void setaccount_number(int account_number) {
				this.account_number = account_number;
			}

			public String getbank_name() {
				return bank_name;
			}

			public void setbank_name(String bank_name) {
				this.bank_name = bank_name;
			}

			public String getaccountholderFirstName() {
				return accountholderFirstName;
			}

			public void setaccountholderFirstName(String accountholderFirstName) {
				this.accountholderFirstName = accountholderFirstName;
			}

			public String getaccountholderLastName() {
				return accountholderLastName;
			}

			public void setaccountholderLastName(String accountholderLastName) {
				this.accountholderLastName = accountholderLastName;
			}

			public String getaccountholderFullName() {
				return accountholderFullName;
			}

			public void setaccountholderFullName(String accountholderFullName) {
				this.accountholderFullName = accountholderFullName;
			}
			public void setmobile_number(int mobile_number)
			{
				 this.mobile_number=mobile_number;
			}
			public int getmobile_number()
			{
				return mobile_number;
			}
			public void setbank_balance(double new_balance)
			{
				 this.bank_balance=new_balance;
			}
			public double getbank_balance()
			{
				return bank_balance;
			}
			public void setCity_name(String City_name)
			{
				 this.City_name=City_name;
			}
			public String getCity_name()
			{
				return City_name;
			}

			public int getUser_id() {
				return user_id;
			}

			public void setUser_id(int user_id) {
				this.user_id = user_id;
			}
			
			
			
		}

