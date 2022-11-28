import java.util.*;

interface Atm_methods
{
    void trans_hist();
    void transactions(String opr, String sender, String receiver, int amount);
    void withdraw(int amount);
    void deposit(int amount);
    void transfer();
    void quit();
}

class Atm implements Atm_methods 
{
    private String user_id = "";
    private int user_pin = 0;
    private int balance = 0;

    private ArrayList<String> tran_hist = new ArrayList<String>();

    public void trans_hist()
    {
        if(tran_hist.isEmpty())
        {
            System.out.println("\nNo Transactions done yet!!");
            return ;
        }

        Iterator itr = tran_hist.iterator();
        
        System.out.println(" \n\n----- TRANSACTIONS -----\n");
        System.out.println("Opr\tSender\tRcvr\tAmount\tBalance");
        while(itr.hasNext())
        {
            System.out.println(itr.next());
        }
        System.out.println("\n");
    }
    
    public void transactions(String opr, String sender, String receiver, int amount)
    {
        tran_hist.add(opr + "\t" + sender + "\t"+ receiver + "\t" + amount + "\t" + this.balance);
    }

    public void withdraw(int amount)
    {
        if(this.balance <= 0 || this.balance - amount < 0)
        {
            System.out.println("\nSorry!! Insufficient Balance!\n");
            return ;
        }

        this.balance -= amount;
        
        System.out.println("\nWithdraw successful !!\n");
        
        cur_bal();
        transactions("W", "Bank", this.user_id, amount);
    }

    public void deposit(int amount)
    {
        this.balance += amount;

        System.out.println("\nDeposit successful !!\n");

        cur_bal();
        transactions("D", this.user_id, "Bank", amount);
    }

    public void transfer()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter the amount to transfer : ");
        int amount = scan.nextInt();

        if(this.balance <= 0 || this.balance - amount < 0)
        {
            System.out.println("\nSorry!! Insufficient Balance!\n");
            return ;
        }

        System.out.print("Enter the receipent name to transfer the amount : ");
        String recp_name = scan.next();
        
        this.balance -= amount;

        System.out.println("\nTransfer to " + recp_name + " successful !!\n");

        cur_bal();
        transactions("T", this.user_id, recp_name, amount);
    }

    public void quit()
    {
        System.exit(0);
    }
    
    public void get_id_pin_bal()
    {
        System.out.println("\nUser : " + this.user_id);
        System.out.println("Pin : " + this.user_pin);
        System.out.println("Balance : " + this.balance);
    }

    public void cur_bal()
    {
        System.out.println("\n ----- Current Balance : " + this.balance);
    }

    public void set_id_pin_bal(String user_id, int user_pin, int init_bal)
    {
        this.user_id = "";
        this.user_id += user_id;
        
        this.user_pin = user_pin;

        this.balance = init_bal;
    }

    public int get_pin()
    {
        return this.user_pin;
    }
}

public class AtmInterface {

    public static void main(String[] args) {
        
        Atm a = new Atm();
        
        Scanner scan = new Scanner(System.in);

        System.out.println(" \n----- WELCOME -----\n");

        System.out.println("Enter your user id and pin to proceed : ");
        System.out.print("Enter your user ID : ");
        String id = scan.next();
        System.out.print("Enter your Pin : ");
        int pin = scan.nextInt();
        
        System.out.print("Enter the initial amount to deposit : ");
        int init = scan.nextInt();

        a.set_id_pin_bal(id, pin, init);

        int amt, attempts = 3;

        System.out.println("\n----- User ID and PIN updated successfully!! -----");

        while(true)
        {
            System.out.println("\n1. Show Transaction History \n2. Withdraw \n3. Deposit \n4. Transfer \n5. Quit\n");
            System.out.print("\nEnter your choice : ");
            int choice = scan.nextInt();

            if(attempts == 0)
            {
                System.out.println("----- You have exhausted your attempts!! -----\nPlease try again after some time");
                System.out.println("\n ---------- Thanks for using our service!! ----------");
                System.exit(0);
            }

            switch(choice)
            {
                case 1:
                        System.out.println(" ----- Transaction History ----- ");
                        System.out.print("Enter your Pin : ");
                        pin = scan.nextInt();

                        if(pin != a.get_pin())
                        {
                            System.out.println("You have entered incorrect pin...!!");
                            attempts--;
                            System.out.println("Attempts left : " + attempts);
                        }
                        else
                        {
                            attempts = 3;
                            a.trans_hist();
                        }
                        break;
                
                case 2:
                        System.out.println(" ----- Withdraw ----- ");
                        
                        System.out.print("Enter the amount to withdraw : ");
                        amt = scan.nextInt();

                        System.out.print("Enter your Pin : ");
                        pin = scan.nextInt();

                        if(pin != a.get_pin())
                        {
                            System.out.println("You have entered incorrect pin...!!");
                            attempts--;
                            System.out.println("Attempts left : " + attempts);
                        }
                        else
                        {
                            attempts = 3;
                            a.withdraw(amt);
                        }

                        break;

                case 3:
                        System.out.println(" ----- Deposit ----- ");
                        
                        System.out.print("Enter the amount to deposit : ");
                        amt = scan.nextInt();
                        
                        System.out.print("Enter your Pin : ");
                        pin = scan.nextInt();

                        if(pin != a.get_pin())
                        {
                            System.out.println("You have entered incorrect pin...!!");
                            attempts--;
                            System.out.println("Attempts left : " + attempts);
                        }
                        else
                        {
                            attempts = 3;
                            a.deposit(amt);
                        }
                        break;

                case 4:
                        System.out.println(" ----- Transfer ----- ");
                        
                        System.out.print("Enter your Pin : ");
                        pin = scan.nextInt();

                        if(pin != a.get_pin())
                        {
                            System.out.println("You have entered incorrect pin...!!");
                            attempts--;
                            System.out.println("Attempts left : " + attempts);
                        }
                        else
                        {
                            attempts = 3;
                            a.transfer();
                        }

                        break;

                case 5:
                        System.out.println("\nThanks for using our service!!\n");
                        System.exit(0);
                        break;
                    
                default:
                        System.out.println("\n!!!!!  Please enter a valid option  !!!!!\n");
                        break;
            }
        }

    }
}
