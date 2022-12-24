import java.io.*;
import java.util.Scanner;
class ATM{
static String name;
static String accountNumber;
static String password;
static double balance;

ATM(String name, String accountNumber, String password, double balance){
this.name = name;
this.accountNumber = accountNumber;
this.password = password;
this.balance = balance;
}
ATM()
{
    
}

public void showBalance(){
System.out.println("Your balance is: "+balance);
}

public void withdraw(double amount){
if(amount > balance || amount == 0){
System.out.println("Invalid Amount");
}else{
balance -= amount;
System.out.println("Please collect your cash");
System.out.println("Your current balance is: "+balance);
}
}

public void deposit(double amount){
if(amount == 0){
System.out.println("Invalid Amount");
}else{
balance += amount;
System.out.println("Deposit Successful");
System.out.println("Your current balance is: "+balance);
}
}

}

public class Main extends ATM{
Main(String name, String accountNumber,String password,double balance)
{
    super(name, accountNumber, password, balance);
}
Main()
{
    
}
private static Scanner sc;

public static void main(String args[]) throws IOException{

sc = new Scanner(System.in);
System.out.println("Welcome to the ATM System");

String choice;
do{
System.out.println("\nMenu\n1. Registration\n2. Login\n3. Exit\nEnter your choice:");
choice = sc.nextLine();
switch(choice){
case "1":
register();
break;
case "2":
login();
break;
case "3":
System.out.println("======================================");
System.out.println("### Thank you for using our services!!");
System.out.println("======================================");
break;
default:
System.out.println("Invalid choice");
break;
}
}while(!choice.equals("3"));

sc.close();

}

public static void register() throws IOException{
try{

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter your name:");
String name = br.readLine();

System.out.println("Enter your account number:");
String accountNumber = br.readLine();

System.out.println("Enter your password:");
String password = br.readLine();

System.out.println("Enter the initial balance:");
double balance = Double.parseDouble(br.readLine());

Main ma = new Main(name, accountNumber, password, balance);
OutputStream fos = new FileOutputStream("ATM.txt");
byte[] dataBytes_name = name.getBytes();
byte[] dataBytes_an = accountNumber.getBytes();
byte[] dataBytes_password = password.getBytes();
byte balance_byte = (byte) balance;
fos.write(dataBytes_name);
fos.write(dataBytes_an);
fos.write(dataBytes_password);
fos.write(balance_byte);
System.out.println("======================================");
System.out.println("------Registration successful---------");
System.out.println("======================================");
fos.close();
}
catch(Exception e){
    System.out.println(e);
}

}

public static void login() throws IOException{

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

System.out.println("Enter your account number:");
String account_Number = br.readLine();

System.out.println("Enter your password:");
String Password = br.readLine();
String check=account_Number+Password;
System.out.println(check);
InputStream fis = new FileInputStream("ATM.txt");
byte[] array = new byte[100];
try {
fis.read(array);
String data=new String(array);
boolean result = data.contains(check);
if(result){
System.out.println("======================================");
System.out.println("------------Login successful----------");
System.out.println("======================================");
Main ma=new Main();
String choice;
do{
System.out.println("\nMenu\n1. Balance Inquiry\n2. Withdraw\n3. Deposit\n4. Logout\nEnter your choice:");
choice = br.readLine();
switch(choice){
case "1":
ma.showBalance();
break;
case "2":
System.out.println("Enter the amount to withdraw:");
double amount = Double.parseDouble(br.readLine());
ma.withdraw(amount);
break;
case "3":
System.out.println("Enter the amount to deposit:");
amount = Double.parseDouble(br.readLine());
ma.deposit(amount);
break;
case "4":
System.out.println("======================================");
System.out.println("-----------Logout successful----------");
System.out.println("======================================");
break;
default:
System.out.println("Invalid choice");
break;
}
}while(!choice.equals("4"));
}else{
System.out.println("Incorrect account number or password");
}
} catch (Exception e) {
System.out.println("No account found");
}
fis.close();
}

}
