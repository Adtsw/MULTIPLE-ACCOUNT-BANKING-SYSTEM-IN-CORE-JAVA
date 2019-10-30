package bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main {


	
			//Create Global ArrayLists for Savings and Credits
			private static ArrayList<Saving> Savings_Acc;
			private static ArrayList<Credit> Credit_Acc;
			private static ArrayList<Bank> Total_details; //Contains both Savings and Credits
			
		
			private static Scanner sc;
			public static void main(String[] args) throws ClassNotFoundException{
				
				Deatils_Of_All_User();
				 sc = new Scanner (System.in);
				 
				
				print("WELCOME TO OUR BANK");
				
				try {
					int choice = 1 ;
					while (choice > 0 && choice < 6) {
						System.out.println("-----------------------------------------------------");
						println("***********************MENU********************************");
						System.out.println("-----------------------------------------------------");
						println("Please Select your Option :-");
						println("(1.) ENTER THE USER ID OF THE PERSON WHOSE ACCOUNT IS TO BE SEARCHED");
						println("(2.) DEPOSIT MONEY IN ACCOUNT");
						println("(3.) WITHDRAW MONEY FROM ACCOUNT");
						println("(4.) TRANSFER MONEY TO OTHER ACCOUNT");
						println("(5.) CREATE AN ACCOUNT");
						println("Enter any other Number to Exit : ");
						choice = sc.nextInt();
						
						switch(choice) {
							case 1:
								displayAccountBasedOnUserID();
								break;
							case 2:
								depositMoneyInAccount();
								break;
							case 3:
								withdrawMoneyFromAccount();
								break;
							case 4:
								transferMoneyToOtherAccount();
								break;
							case 5:
								addAccount();
								break;
							default:
								println("");
								println("Thankyou for using our Publications App. Bye!");
								break;
						}	
					}
				} catch (InputMismatchException e) {
					println("");
						println("Please Enter Valid Input (Input Mismatch Exception)");
					println("");
				} finally {
					sc.close();
					println("Program Terminated");
				}
					
			}

			private static void print(String s) {
				System.out.print(s);
				
			}

			private static void println(String s) {
				System.out.println(s);
			}

			private static void Deatils_Of_All_User() throws ClassNotFoundException {
				
				
				try {
					FileInputStream fi = new FileInputStream(new File("C:\\Database\\Database.txt"));
					ObjectInputStream oi = new ObjectInputStream(fi);

					// Read objects
					Savings_Acc = (ArrayList<Saving>) oi.readObject();
					Credit_Acc = (ArrayList<Credit>) oi.readObject();
					Total_details = (ArrayList<Bank>) oi.readObject();
					oi.close();
					fi.close();
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
					Populate_Data();
				} catch (IOException e) {
					System.out.println("Error initializing stream" + e);
				}
				
			}
			
			//Displaying the account details after searching
			private static void displayAccountBasedOnUserID() {
				println("");
				println("Enter the User_ID of the account holder");
				int user_id = sc.nextInt();
				boolean found = false ;
				
				for(Bank Saving : Savings_Acc) {
					if(user_id==Saving.getUser_id()){
						println(""+Saving.toString());
						found = true;
					}
				}
				for(Bank Credit : Credit_Acc) {
					if(user_id==Credit.getUser_id()){
						println("----------------------------------------------------");
						println("THE ACCOUNT HAS BEEN FOUND ");
						println("----------------------------------------------------");
						println(""+Credit.toString());
						found = true;
					}
				}
				
				//If not found
				if(!found){
					println("");
					println("None of the Savings or Credit Account have this  user id \'"+user_id+"\'");
				}
				println("");
			}
			
			//Deposit the money 
			private static void depositMoneyInAccount() {
				println("");
				double New_balance = 0 ;
				print("Enter UserID : ");
				int user_id = sc.nextInt();
				for(Bank Saving: Savings_Acc) {
					if(user_id==Saving.getUser_id()){
						println("----------------------------------------------------");
						print("Do you want to Deposit in Savings Account : Y/N ?");
						println("----------------------------------------------------");
						String opt = sc.next();
						if(opt.equalsIgnoreCase("Y")) {
							System.out.println("Enter The Amount you Want To Deposit into Saving Account :");
							double amount = sc.nextInt();
							New_balance = Saving.getbank_balance() + amount;
							Saving.setbank_balance(New_balance);
							println("Balance :" +(Saving.getbank_balance())); 
						}
					}
				}
				
				for(Bank Credit : Credit_Acc) {
					if(user_id==Credit.getUser_id()){
						println("----------------------------------------------------");
						print("Deposit in Credit Account : Y/N ?");
						println("----------------------------------------------------");
						String opt = sc.next();
						if(opt.equalsIgnoreCase("Y")) {
						System.out.println("Enter The Amount you Want To Deposit into Credit Account :");
						double amount = sc.nextInt();
						New_balance = Credit.getbank_balance() + amount;
						Credit.setbank_balance(New_balance);
						println("Balance :" +(Credit.getbank_balance()));
						}
					}
					
				}
				
				Save_Data();
				println("");
			}
			
			//Withdrawing money from account
			private static void withdrawMoneyFromAccount() {
				println("");
				double New_balance = 0 ;
				print("Enter UserID : ");
				int user_id = sc.nextInt();
				for(Bank Saving: Savings_Acc) {
					if(user_id==Saving.getUser_id()){
						println("----------------------------------------------------");
						print("Withdraw from Savings Account : Y/N ?");
						println("----------------------------------------------------");
						String opt = sc.next();
						if(opt.equalsIgnoreCase("Y")) {
							System.out.println("Enter The Amount you Want To Withdraw from Saving Account :");
							double amount = sc.nextInt();
							if(amount>Saving.getbank_balance()) {
								println("Balance less than amount entered");
							} else {
								New_balance = Saving.getbank_balance() - amount;
								Saving.setbank_balance(New_balance);
							}
							println("Balance :" +(Saving.getbank_balance())); 
						}
					}
				}
				
				for(Bank Credit : Credit_Acc) {
					if(user_id==Credit.getUser_id()){
						println("----------------------------------------------------");
						print("Withdraw from Credit Account : Y/N ?");
						println("----------------------------------------------------");
						String opt = sc.next();
						if(opt.equalsIgnoreCase("Y")) {
						System.out.println("Enter The Amount you Want To Withdraw from Credit Account :");
						double amount = sc.nextInt();
						if(amount>Credit.getbank_balance()) {
							println("Balance less than amount entered");
						} else {
							New_balance = Credit.getbank_balance() - amount;
							Credit.setbank_balance(New_balance);
						}
						println("Balance :" +(Credit.getbank_balance()));
						}
					}
					
				}
				
				Save_Data();
				println("");
			}
			
			//Transferring money to other Account 
			private static void transferMoneyToOtherAccount() {
				Boolean found1 =false, found2 = false, lowbal = false;
				println("");
				println("Enter the Account number from which transfer to be made:-");
				int from = sc.nextInt();
				println("Enter the Account number to which transfer to be made:-");
				int to = sc.nextInt();
				println("Enter amount to be transferred");
				int amount = sc.nextInt();
				for(Bank Saving : Savings_Acc) {
					if(from==Saving.getaccount_number()){
						if(amount > Saving.getbank_balance()) {
							println("Low Balance !");
							lowbal= true;
						}else {
							Saving.setbank_balance(Saving.getbank_balance()-amount);
							found1 = true;
						}
					}
				}
				for(Bank Credit : Credit_Acc) {
					if(from==Credit.getaccount_number()){
						if(amount > Credit.getbank_balance()) {
							println("Low Balance !");
							lowbal = true;
						}else {
							Credit.setbank_balance(Credit.getbank_balance()-amount);
							found1 = true;
						}
					}
				}
				
				for(Bank Saving : Savings_Acc) {
					if(to==Saving.getaccount_number()){
						found2 = true;
						Saving.setbank_balance(Saving.getbank_balance()+amount);
					}
				}
				for(Bank Credit : Credit_Acc) {
					if(to==Credit.getaccount_number()){
						found2 = true;
						Credit.setbank_balance(Credit.getbank_balance()+amount);
					}
				}
				if(found1 && found2 && !lowbal)
				{
					println("Transaction Succesfull");
					Save_Data();
				}else if(!found1 && !found2) {
					println("Bank account(s) not found");
				}
			}
			
			//Add Savings and Credit 
			private static void addAccount() {
				println("");
				println("1. Add Saving");
				println("2. Add Credit");
				println("Enter your Choice: ");
				int choice = sc.nextInt();
				
				if (choice == 1 ){
					addSaving();
				} else if (choice == 2) {
					addCredit();
				} else {
					println("Please Enter Valid Input");
				}
				Save_Data();
			}
			
			//Adding a Savings account 
			private static void addSaving() {
				
				Scanner sc1 = new Scanner(System.in);

				Scanner sc2 = new Scanner(System.in);
				Boolean conflict= false;
				println("");
				int account_number ;
				while(true) {
				println("Enter the account number : ");
				 account_number = sc1.nextInt() ;
					for(Saving saving: Savings_Acc) {
						if(saving.getaccount_number()==account_number) {
							conflict=true;
						}
					}
					for(Credit credit: Credit_Acc) {
						if(credit.getaccount_number()==account_number) {
							conflict=true;
						}
					}
					if(!conflict) {
						break;
					}
				}
				println("Enter the User ID : ");
				int user_Id = sc1.nextInt() ;
				println("Enter the bank name : ");
				String bank_name = sc1.next();
				println("Enter the account holder's full name : ");
				String accountholderFullName = sc2.nextLine();
				println("Enter mobile number of the account holder : ");
				int mobile_number= sc1.nextInt();
				println("Enter the bank balance : ");
				double bank_balance = sc1.nextDouble();
				println("Enter the City name : ");
				String City_name=sc2.nextLine();
				
					
				Saving newSaving = new Saving(account_number,user_Id, bank_name, accountholderFullName, mobile_number,bank_balance,City_name);
				Savings_Acc.add(newSaving);
				Total_details.add(newSaving);
				println("");
				println("Saving Added! "+newSaving.toString());
				println("");
			}
			
			//Adding a Credit account
			private static void addCredit() {
				
				Scanner sc1 = new Scanner(System.in);

				Scanner sc2 = new Scanner(System.in);
				Boolean conflict = false;
				int account_number; 
				println("");
				while(true) {
					println("Enter the account number : ");
					account_number = sc1.nextInt() ;
						for(Credit credit: Credit_Acc) {
							if(credit.getaccount_number()==account_number) {
								conflict=true;
							}
						}
						for(Saving saving: Savings_Acc) {
							if(saving.getaccount_number()==account_number) {
								conflict=true;
							}
						}
						if(!conflict) {
							break;
						}
					}
				println("Enter the User ID : ");
				int user_Id = sc1.nextInt() ;
				println("Enter the bank name : ");
				String bank_name = sc1.next();
				println("Enter the account holder's full name : ");
				String accountholderFullName = sc2.nextLine();
				println("Enter mobile number of the account holder : ");
				int mobile_number= sc1.nextInt();
				println("Enter the bank balance : ");
				double bank_balance = sc1.nextDouble();
				println("Enter the City name : ");
				String City_name=sc2.nextLine();
				
					
				Credit newCredit = new Credit(account_number,user_Id, bank_name, accountholderFullName, mobile_number,bank_balance,City_name);
				Credit_Acc.add(newCredit);
				Total_details.add(newCredit);
				println("");
				println("Credit Added! "+newCredit.toString());
				println("");
			}
			
			private static void Populate_Data() {
				//Initialize Savings
				Saving Saving1 = new Saving(2001201,001,"ICICI","Aditya Shaw",76542110,1000.50,"toronto");
				Saving Saving2 = new Saving(2001202,002,"ICICI","Parimal Patel",76542220,1000.50,"toronto");
				Saving Saving3 = new Saving(2001203,003,"ICICI","John Miller",76542330,1000.50,"toronto");
				Saving Saving4 = new Saving(2001204,004,"ICICI","Prithvi Shaw",76542440,1000.50,"toronto");
				Saving Saving5 = new Saving(2001205,005,"ICICI","Adam Gill",76542550,1000.50,"toronto");
				
				//Initialize Credits
				Credit Credit1 = new Credit(2001206,105,"ICICI","Mark Dcosta",76542550,1000.50,"toronto");
				Credit Credit2 = new Credit(2001207,001,"ICICI","Aditya Shaw",76542110,1000.50,"toronto");
				Credit Credit3 = new Credit(2001208,002,"ICICI","Parimal PAtel",76542220,1000.50,"toronto");
				Credit Credit4 = new Credit(2001209,103,"ICICI","Prithvi Shaw",98765330,1000.50,"toronto");
				Credit Credit5 = new Credit(2001210,104,"ICICI","Sachin Dev",76542440,1000.50,"toronto");
				
				//Initialize ArrayLists
				Savings_Acc = new ArrayList<>();
				Credit_Acc = new ArrayList<>();
				Total_details = new ArrayList<>();
				
				//Add all Savings to Savings_Acc
				Savings_Acc.add(Saving1);
				Savings_Acc.add(Saving2);
				Savings_Acc.add(Saving3);
				Savings_Acc.add(Saving4);
				Savings_Acc.add(Saving5);
				
				//Add all Credits to Credit_Acc
				Credit_Acc.add(Credit1);
				Credit_Acc.add(Credit2);
				Credit_Acc.add(Credit3);
				Credit_Acc.add(Credit4);
				Credit_Acc.add(Credit5);
				
				//Add all Savings in Total Details
				Total_details.addAll(Savings_Acc);
				Total_details.addAll(Credit_Acc);
				try {
					FileOutputStream f = new FileOutputStream(new File("C:\\Database\\Database.txt"));
					ObjectOutputStream o = new ObjectOutputStream(f);

					// Write objects to file
					o.writeObject(Savings_Acc);
					o.writeObject(Credit_Acc);
					o.writeObject(Total_details);
					o.close();
					f.close();
					println("----------------------------------------------------");
					println("New .txt Database created.");
					println("----------------------------------------------------");
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream");
				}
			
				
			}
			//Creating a method to save data in Database.txt
			private static void Save_Data() {
				try {
					FileOutputStream f = new FileOutputStream(new File("C:\\Database\\Database.txt"));
					ObjectOutputStream o = new ObjectOutputStream(f);

					// Write objects to file
					o.writeObject(Savings_Acc);
					o.writeObject(Credit_Acc);
					o.writeObject(Total_details);
					o.close();
					f.close();
					println("New data saved.");
				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream");
				}
			
			}
}






