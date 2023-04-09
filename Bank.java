//Build a Banking application using Java

import java.util.Scanner;
import java.util.Random;
class LowBalanceException extends Exception{};
class InvalidOTP extends Exception{};
class PasswordNotMatch extends Exception{};
class InvalidAuthentication extends Exception{};

class Bank{
	static int count=1;
	int account_no;
	Scanner sc=new Scanner(System.in);
	private double balance;
	private String name;
	private String adhaar;
	private String mobile;
    String pass;
	
	public Bank(String name,String adhaar,String mobile,String pass){
		this.name=name;
		this.adhaar=adhaar;
		this.mobile=mobile;
		this.balance=500;
		this.account_no=count+99;
		count++;
		this.pass=pass;
	}
	
	public void balance_enq(){
		System.out.println("Your Bank Balance is : "+balance);
	}
	
	public void withdraw(){
	System.out.println("Enter the amount to be Withdrawn : ");
    int with=sc.nextInt();
	try{
	if(balance-with<500)
	{
		throw new LowBalanceException();
	}
	else
	{
		balance=balance-with;
		balance_enq();
	}
	}catch(LowBalanceException e)
	{
		System.out.println("Cannot Withdraw the Money due to Insufficient Balance!");
	}
	}
	
	public void transfer(Bank[] b){
	System.out.println("Enter the Account Number to whom you want to Transfer the Money : ");
    int temp=sc.nextInt();
	System.out.println("Enter the Amount you want to Transfer : ");
	int amount=sc.nextInt();
	try{
		b[temp-100].balance+=amount;
		b[temp-100].account_no+=amount;
		System.out.println("Transfer Successfull !");
	}catch(NullPointerException e)
	{
		System.out.println("Accout doesn't Exist.");
	}
	}
	
	public void deposit(){
	System.out.println("Enter the Amount you to Deposit : ");
	int dep=sc.nextInt();
	balance=balance+dep;
	System.out.println("Your Account Balance is : "+dep);
	}
	
	public void view_data(){
	System.out.println("Your Account Number is : "+account_no);
	System.out.println("Your Name is : "+name);
	System.out.println("Your Adhaar Card Number is : ");
	System.out.println("Your Mobile Number is : "+mobile);
	}
} 

class Test{
	static Bank[] b=new Bank[2];
	static int i=0;
	
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int ch;
		
		do{
			System.out.println("********************************************");
			System.out.println("ENTER YOUR CHOICE : ");
			System.out.println("1.CREATE ACCOUNT\n2.LOGIN\n3.EXIT");
			System.out.println("********************************************");
			ch=sc.nextInt();
			
			switch(ch){
				case 1:open_account();
				break;
				
				case 2:login();
				break;
				
				case 3:return;
				
				default: System.out.println("Invalid Choice !");
			}
		}while(ch!=3);
	sc.close();
	}

	public static void open_account(){
		String name,adhaar,mobile,pass,rpass;
		double balance;
		Random rand=new Random();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("Enter Your Name : ");
		name=sc.next();
		System.out.println("Enter Your Adhhar Card Number : ");
		adhaar=sc.next();
		System.out.println("Enter Your Mobile Number : ");
		mobile=sc.next();
		System.out.println("Enter Your Password : ");
		pass=sc.next();
		System.out.println("Retype Your Password : ");
		rpass=sc.next();
		
		try{
			if(pass.equals(rpass) && i<2)
			{
				int num=rand.nextInt(10000-1000);
				System.out.println("Enter Your OTP : "+num);
				System.out.println("Enter the OTP to Proceed : ");
				int otp=sc.nextInt();
				try{
					if(num==otp)
					{
						b[i]=new Bank(name,adhaar,mobile,pass);
						System.out.println("Account Opened Successfully !");
						System.out.println("Your Account Number is : "+b[i].account_no);
						i++;
					}
					else
					{
						throw new InvalidOTP();
					}
				}catch(InvalidOTP e)
					{
						System.out.println("Inavlid OTP !");
					}
		}else if(pass!=rpass)
				{
					throw new PasswordNotMatch();
				}
				else
				{
					System.out.println("Space Not Left.");
					return;
				}
		}catch(PasswordNotMatch e)
				{
					System.out.println("Incorrect Password !");
				}
			}
	
	 public static void login(){
		int temp;
		String pass,ogpass;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your Account Number : ");
		temp=sc.nextInt();
		System.out.println("Enter Your Password : ");
		pass=sc.next();
		ogpass=b[temp-100].pass;
		try{
			if(ogpass.equals(pass))
			{
				Bank_Operations(temp-100);
				return;
			}
			else
			{
				throw new InvalidAuthentication();
			}
		}catch(InvalidAuthentication e)
		    {
				System.out.println("Password Incorrect !");
			}
		}
	
	public static void Bank_Operations(int i){
		Scanner sc=new Scanner(System.in);
		int choice;
		
		do{System.out.println("********************************************");
			System.out.println("Enter Your Choice : ");
		  System.out.println("1.BALANCE ENQUIRY\n2.WITHDRAW\n3.TRANSFER\n4.DEPOSIT\n5.VIEW DATA\n6.EXIT");
		  System.out.println("********************************************");
			choice=sc.nextInt();
			
			switch(choice){
				case 1:b[i].balance_enq();
				break;
				
				case 2:b[i].withdraw();
				break;
				
				case 3:b[i].transfer(b);
				break;
				
				case 4:b[i].deposit();
				break;
				
				case 5:b[i].view_data();
				break;
				
				case 6:return;
				
				default: System.out.println("Invalid Choice !");
			}
		}while(choice!=6);
	sc.close();
	}
}
