package pac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cricket {

    //scanner obj. for taking string input
    static Scanner scannerObjectForString=new Scanner(System.in);
   
    //will store list of bowlers
    static Bowlers queueArray[];
    
    //for performing enqueue operation
	static int rear = -1;

    public static void main(String[] args) {
        try {
            int numberOfBowls=getNumberOfBowls();
            int numberOfBowlers=getNumberOfBowlers();
            queueArray = new Bowlers[numberOfBowlers];
            getBowlers(numberOfBowlers, numberOfBowls);
            
            List<String> bowlerList=orderOfBowlers(numberOfBowls);
            System.out.println("Order of bowlers are:");
            System.out.println(bowlerList);
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * will arrange bowlers according to no. of balls they are left with
     * @param {numberOfBowls}->a bowler has
     * @return-> list of ordered bolwers
     */
    public static List<String> orderOfBowlers(int numberOfBowls){
        List<String> bowlersList=new ArrayList();
        while(queueArray[0].getNumberOfBowls()!=0 && numberOfBowls>0){
            bowlersList.add(queueArray[0].getName());
            int bowlsLeft=queueArray[0].getNumberOfBowls()-1;
            queueArray[0].numberOfBowls=bowlsLeft;
            numberOfBowls--;
            //will arrange the list every time a change is done
            rearrange();
        }
        return bowlersList;
    }

    /**
     * will rearrange the list every time a change is done
     */
    public static void rearrange(){
        for(int queueIndex=queueArray.length-1;queueIndex>0;queueIndex--){
            if(queueArray[queueIndex].getNumberOfBowls()>queueArray[0].getNumberOfBowls()){
                Bowlers temp=queueArray[queueIndex];
                queueArray[queueIndex]=queueArray[0];
                queueArray[0]=temp;
            }
        }
    }

    /**
     * will get no. of balls Virat has to play
     * @return no. of balls Virat has to play
     * @throws Exception in case Virat has 0 bowl to play
     */
    public static int getNumberOfBowls() throws Exception{
        Scanner scannerObject=new Scanner(System.in);
        System.out.println("Enter number of bowls Virat has");
        if(scannerObject.hasNextInt()){
            int numberOfBowls=scannerObject.nextInt();
            if(numberOfBowls<0){
                System.out.println("Please enter valid no. of bowls");
                System.out.println();
                return getNumberOfBowls();
            }
            else if(numberOfBowls==0){
                throw  new Exception("Virat Kohli has no balls to play\n"+
                "Program terminated successfully");
            }
            else return numberOfBowls;
        }

        else{
            System.out.println("Please enter valid no. of bowls");
            System.out.println();
            scannerObject.nextLine();
           return  getNumberOfBowls();
        }
    }

    /**
     * will get no. of bowlers a team has
     * @return->no. of bowlers a team has
     */
    public static  int getNumberOfBowlers(){
        Scanner scannerObject=new Scanner(System.in);
        System.out.println("Enter number of bowlers you have");
        if(scannerObject.hasNextInt()){
            int bowlerNumber=scannerObject.nextInt();
            if(bowlerNumber<=0){
                System.out.println("No. of bowlers should be greater than 0");
                System.out.println();
                return getNumberOfBowlers();
            }
            return bowlerNumber;
        }
        else {
            System.out.println("Please enter valid bowler no.");
            System.out.println();
            scannerObject.nextLine();
            return getNumberOfBowlers();
        }
    }

    /**
     * will get bowlers list
     * @param {numberOfBowlers}->total no. of bowlers a team has
     * @param {bowlsCount}->total no. of balls Virat has
     * @throws Exception-> in case total no. of balls!=total no. of balls quota of all bowlers
     */
    public static void getBowlers(int numberOfBowlers, int bowlsCount) throws Exception{
        Scanner scannerObject=new Scanner(System.in);
        int totalQuota=0;
        for(int bowlerIndex=1;bowlerIndex<=numberOfBowlers;bowlerIndex++){

            System.out.println("Enter name of bowler "+(bowlerIndex));
            String name=scannerObjectForString.nextLine(); 
            
            System.out.println("Enter number of bowls "+(name)+"  has");

            if(scannerObject.hasNextInt()){
                int numberOfBowls=scannerObject.nextInt();
                if(numberOfBowls<=0){
                    System.out.println("There should be at least 1 ball");
                    System.out.println();
                    bowlerIndex--;
                }
                else{
                    totalQuota+=numberOfBowls;
                    Bowlers bowlerObject=new Bowlers(name, numberOfBowls);
                    //add a bowler to bowler queue
                    enqueue(bowlerObject);
                }
            }

            else{
                System.out.println("Please enter valid bowls");
                System.out.println();
                scannerObject.nextLine();
                bowlerIndex--;
            }
        }
        if(bowlsCount>totalQuota)throw  new Exception("Invalid program\n"+
        "Total bowler's bowls should be greater than or equal to no. of balls Virat has");
    }

    /**
     * will add bowler to bowler queue
     * @param {bowlerObject}->denotes bolwer
     * @return->whether a bolwer is added or not
     */
    static boolean enqueue(Bowlers bowlerObject) {
		 	rear++;
			queueArray[rear] = bowlerObject;
			if(queueArray[0].getNumberOfBowls()<queueArray[rear].getNumberOfBowls()){
				Bowlers temp=queueArray[0];
				queueArray[0]=queueArray[rear];
				queueArray[rear]=temp;
			}	
			return true;
		}

	}
    

