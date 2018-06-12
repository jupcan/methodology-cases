import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/*********************************************************************
 *
 * Class Name: Met_P1
 * Author/s name: Diego Molero & Juan Perea
 * Release/Creation date: 18/02/2016
 * Class version: 1.0
 * Class description: Compares the execution times of two algorithms, 
 * bubblesort and selectionsort, with different inputs
 *
 *********************************************************************/

public class TestSort{

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
			System.out.println ("\nSelect a point to execute: \n1. Point A \n2. Point B");
			System.out.println ("--------------------------");
			menu_option=sc.nextInt();

			if (menu_option != '1' && menu_option != '2') {
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
	 * Description of the Method: Execute the comparison with the
	 * given different inputs
	 * Return value: void
	 *
	 *********************************************************************/ 

	public static void point1() {
		Scanner sc = new Scanner(System.in);
		boolean goOn;
		int [] b;
		long begin, end, c, s;
		do {
			goOn = false;
			int [] a = readData(); 

			begin = System.currentTimeMillis();	
			b = bubbleSort(a);
			end = System.currentTimeMillis();
			c = end - begin;

			begin = System.currentTimeMillis();	
			b = selectionSort(a);
			end = System.currentTimeMillis();
			s = end - begin;

			System.out.println(" Size    Bubble time (ms)  Selection time (ms)");
			System.out.printf("%5d \t%10d %20d %n", a.length, c, s);
			System.out.println ("Do you want to try again? Y for yes, any other key for no");

			if (sc.next().toUpperCase().charAt(0) == 'Y') goOn = true;
		} while (goOn);
	}

	/*********************************************************************
	 *
	 * Method name: point2
	 * Name of the original author: Professor
	 * Description of the Method: Determines the running time of both 
	 * algorithms for random arrays of size: 100, 500, 1000, 5000, 8000, 
	 * 9000, 10000, 11000, 20000 and 50000
	 * Return value: void
	 *
	 *********************************************************************/

	public static void point2(){
		int[] n = {100, 500, 1000, 5000, 8000, 9000, 10000, 11000, 20000, 50000}; // Array sizes
		Scanner sc = new Scanner(System.in);
		boolean goOn;
		long begin, end, c, s;
		int[] a, b;

		do {
			goOn = false;
			System.out.println(" Size    Bubble time (ms)  Selection time (ms)");
			for (int i = 0; i< n.length; i++){
				a = randomInput (n[i]);

				begin = System.currentTimeMillis();
				b = bubbleSort(a);
				end = System.currentTimeMillis();
				c = end - begin;

				begin = System.currentTimeMillis();
				b = selectionSort(a);
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
	 * Method name: readData
	 * Name of the original author: Professor
	 * Description of the Method: Allows the user to choose from 3 
	 * different ways of inserting values into the array 
	 * Return value: int[]; array that contains the read values 
	 *
	 *********************************************************************/ 

	public static int[] readData() {
		int [] v = null;
		char option;
		boolean goOn;
		Scanner sc = new Scanner(System.in);

		do {
			goOn = false;
			System.out.print ("--------------------------");
			System.out.println ("\nSelect input from: \nK: Keyboard \nF: File \nR: Random");
			System.out.println ("--------------------------");
			option = sc.next().toUpperCase().charAt(0);
			if (option != 'K' && option != 'F' && option != 'R') {
				System.out.println ("Wrong option. Try again.");
				goOn = true;
			}
		} while (goOn);

		switch (option){
		case 'K':
			v = keyboardInput();
			break;
		case 'R':
			int n;
			System.out.println("Introduce the size of the array");
			n = sc.nextInt();
			v = randomInput(n);
			break;
		case 'F':
			v = fileInput();
		}
		for(int a=0;a<v.length;a++){
			System.out.println(v[a]);
		}
		return v;
	}

	/*********************************************************************
	 *
	 * Method name: keyboardInput
	 * Description of the Method: The user inserts the number of elements 
	 * in the array then he/she introduces the values one by one
	 * Return value: int[]; returns the array with the values given 
	 *
	 *********************************************************************/ 

	public static int[] keyboardInput(){
		int n;
		Scanner sc = new Scanner(System.in);

		System.out.println("Introduce the size of the array");
		n=sc.nextInt();
		int []v = new int[n];
		System.out.println("Introduce "+n+" value/s");

		for(int a=0;a<v.length;a++){
			v[a]=sc.nextInt();
		}
		return v;
	}

	/*********************************************************************
	 *
	 * Method name: ramdomInput
	 * Description of the Method: Generates and inserts random values into
	 * an array with a size given by arguments (n)
	 * Calling arguments: int n; size of the array
	 * Return value: int[]; array with the random values
	 *
	 *********************************************************************/ 

	public static int[] randomInput(int n){
		int []v = new int[n];
		int max = 1000000;
		Random r = new Random();

		for (int i = 0; i < n; i++){
			v[i] = r.nextInt(max);
		}
		return v;
	}

	/*********************************************************************
	 *
	 * Method name: fileInput
	 * Description of the Method: Opens and reads the values written in the 
	 * file given by the user, then it stores them in an array
	 * Required Files: A text file where the first number is the size of 
	 * the array and the followings are the values, separated by lanes. 
	 * It must be into the same directory as the program
	 * Return value: int[]; array with the read values of the file
	 * Exception e: when trying to open the file
	 * Exception e1: when trying to close the file
	 *
	 *********************************************************************/

	public static int[] fileInput(){
		Scanner sc = new Scanner(System.in);
		String file_name;
		System.out.println("What file do you want to read?");
		file_name=sc.next();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;

		int []array = null;
		int n=-1; // The first element (iteration n=-1) its the array's size, then the values of the array
		try {
			archivo = new File (file_name);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null){
				if(n==-1){
					array = new int[Integer.parseInt(linea)];
				}else{
					array[n]=Integer.parseInt(linea);  
				}
				n++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}finally{
			try{                    
				if( null != fr ){   
					fr.close();     
				}                  
			}catch (Exception e2){ 
				e2.printStackTrace();
			}
		}
		return array;
	}

	/*********************************************************************
	 *
	 * Method name: bubbleSort
	 * Description of the Method: Sorts the array given by checking each 
	 * element that it's inside by comparing the greater value and changing 
	 * the position between them. This algorithm checks the array even if
	 * it is already ordered 
	 * Calling arguments: int a[]; unsorted array
	 * Return value: int[]; sorted array
	 *
	 *********************************************************************/

	public static int[] bubbleSort (int[] a){
		int n = a.length;
		int aux = 0; // Stores the value of the changed element in order not to lose it

		for(int i=0; i < n; i++){
			for(int j=1; j < (n-i); j++){
				if(a[j-1] > a[j]){
					aux = a[j-1];
					a[j-1] = a[j];
					a[j] = aux;
				}
			}
		}
		return a;
	}

	/*********************************************************************
	 *
	 * Method name: selectionSort
	 * Description of the Method: Sorts the array by going through the array 
	 * and looking for the smallest values in order to put them at the 
	 * following position since the beginning 
	 * Calling arguments: int a[]; the unsorted array
	 * Return value: int[]; sorted array
	 *
	 *********************************************************************/

	public static int[] selectionSort (int[] a){
		int aux1=0; // Stores the smallest value
		int aux2=0; // Stores the value where the array goes through
		int pos=0; // Stores the position where the smallest value is

		for(int i=0  ;  i<a.length  ;  i++){
			aux1=a[i];
			pos=i;
			for(int j=i;j<a.length;j++){
				aux2=a[j];
				if(aux2<aux1){
					aux1=aux2;
					pos=j;
				}
			}
			aux1=a[i];
			a[i]=a[pos];
			a[pos]=aux1;
		}
		return a;
	}
}