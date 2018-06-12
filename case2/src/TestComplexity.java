import java.util.Scanner;

/*********************************************************************
 *
 * Class Name: Met_P2
 * Author/s name: Diego Molero & Juan Perea
 * Release/Creation date: 25/02/2016
 * Class version: 1.0
 * Class description: Computes two algorithms, iteratively and recursively, 
 * and compares the execution times of each one
 * 
 *********************************************************************/

public class TestComplexity {

	/*********************************************************************
	 *
	 * Method name: main
	 * Description of the Method: Menu and calls to main program's methods
	 * Return value: void
	 *
	 *********************************************************************/ 

	public static void main (String [] args) {
		int menu_option;
		Scanner sc = new Scanner(System.in);

		do {
			System.out.print ("--------------------------");
			System.out.println ("\nSelect a point to execute: \n1. Input through keyboard \n2. Random values");
			System.out.println ("--------------------------");
			menu_option=sc.nextInt();

			if (menu_option != 1 && menu_option != 2) {
				System.out.println ("Wrong option. Try again.");
			}

			// Menu
			switch (menu_option) {
			case 1:
				point1();
				break;
			case 2:
				point2();
				break;
			}
		} while (menu_option!=2);
	}

	/*********************************************************************
	 *
	 * Method name: point1
	 * Description of the Method: Calculates the execution time, for a 
	 * given specific size read by the keyboard, of each algorithm's type
	 * and prints them
	 * Return value: void
	 *
	 *********************************************************************/ 

	public static void point1() {
		Scanner sc = new Scanner(System.in);
		boolean goOn;
		double b;
		int n;
		long begin, end, c, s;

		System.out.println("Introduce the size of the array");
		n=sc.nextInt();

		int []a = new int[n];
		System.out.println("Introduce "+n+" value/s");

		for(int i=0;i<a.length;i++){
			a[i]=sc.nextInt();
		}

		do {
			goOn = false;
			System.out.println(" Size    Iteratively (ms)  Recursively (ms)");
			for (int i = 0; i< a.length; i++){

				begin = System.currentTimeMillis();
				b = iterativelyAlgorithm(a[i]);
				//System.out.println(b);
				end = System.currentTimeMillis();
				c = end - begin;

				begin = System.currentTimeMillis();
				b = recursivelyAlgorithm(a[i]);
				//System.out.println(b);
				end = System.currentTimeMillis();
				s = end - begin;

				System.out.printf("%5d \t%10d %20d %n", a[i], c, s);
			}
			System.out.println ("Do you want to try again? Y for yes, any other key for no");
			if (sc.next().toUpperCase().charAt(0) == 'Y') goOn = true;
		} while (goOn);
	}

	/*********************************************************************
	 *
	 * Method name: point2
	 * Description of the Method: Calculates the execution time, for a 
	 * given specific size located in the sizes array, of each algorithm's
	 * type and prints them
	 * Return value: void
	 * 
	 *********************************************************************/

	public static void point2(){
		int[] n = {100, 500, 1000, 5000, 8000, 9000, 10000, 11000, 20000, 50000}; // Array sizes
		Scanner sc = new Scanner(System.in);
		boolean goOn;
		long begin, end, c, s;
		double b;
		int a;

		do {
			goOn = false;
			System.out.println(" Size    Iteratively (ms)  Recursively (ms)");
			for (int i = 0; i< n.length; i++){

				begin = System.currentTimeMillis();
				b = iterativelyAlgorithm(n[i]);
				//System.out.println(b);
				end = System.currentTimeMillis();
				c = end - begin;

				begin = System.currentTimeMillis();
				b = recursivelyAlgorithm(n[i]);
				//System.out.println(b);
				end = System.currentTimeMillis();
				s = end - begin;

				System.out.printf("%5d \t%10d %20d %n", n[i], c, s);
			}
			System.out.println ("Do you want to try again? Y for yes, any other key for no");
			if (sc.next().toUpperCase().charAt(0) == 'Y') goOn = true;
		} while (goOn);
	}

	/*********************************************************************
	 *
	 * Method name: iterativelyAlgorithm
	 * Description of the Method: Execute the iteratively algorithm
	 * Calling arguments: int n; specific value of the given array 
	 * we use to calculate the algorithm with
	 * Return value: double; value of the algorithm in that specific moment
	 * 
	 *********************************************************************/

	public static double iterativelyAlgorithm(int n){
		double s=0;

		for(int i=1;i<=n;i++){
			s+= Math.pow(-1, i-1) / Math.pow(i,2) ;
		}
		return s;
	}

	/*********************************************************************
	 *
	 * Method name: recursivelyAlgorithm
	 * Description of the Method: Execute the recursively algorithm
	 * Calling arguments: int n; specific value of the given array 
	 * we use to calculate the algorithm with
	 * Return value: double; value of the algorithm in that specific moment
	 * 
	 *********************************************************************/ 

	public static double recursivelyAlgorithm (int n){
		double s=0;

		try{
			if(n-1 >= 0)
				s=recursivelyAlgorithm(n-1)+ (Math.pow(-1,n-1) / Math.pow(n,2) );
		}catch(StackOverflowError e){

		}
		return s;
	}
}