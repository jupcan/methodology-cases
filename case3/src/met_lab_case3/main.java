import java.util.Random;
import java.util.Scanner;

/*********************************************************************
 *
 * Class Name: Met_P3
 * Author/s name: Diego Molero & Juan Perea
 * Release/Creation date: 03/03/2016
 * Class version: 1.0
 * Class description: Gives us the value of the array that we would
 * obtain if that mentioned array was already ordered being k
 * the position we want to know the value of
 * 
 *********************************************************************/

public class main {

	/*********************************************************************
	 *
	 * Method name: main
	 * Description of the Method: Creation of the different types of
	 * arrays and calls to methods in order to test all the cases
	 * Return value: void
	 *
	 *********************************************************************/ 

	public static void main(String[] args) {
		int[] array = null;

		// Random array
		array=randomInput(10);
		int k,val;
		for( k=0;k<array.length;k++){
			System.out.print(array[k]+",");
		}
		executeTest(array);

		// Increasing order array
		array=selectionSort(array,true);
		for( k=0;k<array.length;k++){
			System.out.print(array[k]+",");
		}
		executeTest(array);

		// Decreasing order array
		array=selectionSort(array,false);
		for(k=0;k<array.length;k++){
			System.out.print(array[k]+",");
		}
		executeTest(array);
	}

	/*********************************************************************
	 *
	 * Method name: selection
	 * Name of the original author: Professor
	 * Description of the Method: Calculates the kth order statistics of 
	 * a given array
	 * Calling arguments: int[] a; array we want to calculate the kth 
	 * order statistics of, int init_pos; initial position in subarray,
	 * int final_pos; final position in subarray, int k; statistics desired
	 * Return value: int; kth order statistics of the array
	 *
	 *********************************************************************/ 

	public static int selection(int[] a,int init_pos,int final_pos,int k){
		int val,r,n;

		if(init_pos==final_pos){
			val=a[init_pos];
		}else{
			r=partition_array(a, init_pos, final_pos);
			n=r-init_pos+1;
			if(k==n){
				val=a[r];
			}else if(k<n){
				val=selection(a,init_pos,r-1,k);
			}else{
				val=selection(a,r+1,final_pos,k-n);
			}	
		}
		return val;
	}

	/*********************************************************************
	 *
	 * Method name: partition_array
	 * Name of the original author: Professor
	 * Description of the Method: The algorithm puts the pivot in 
	 * a variable i so that when it finds an element lower or equal to 
	 * it, this index is incremented and that specific element is placed 
	 * before the pivot
	 * Calling arguments: int[] a; given array, int left; an auxiliary
	 * left limit, int right; an auxiliary right limit
	 * Return value: int; left limit ordered array value
	 *
	 *********************************************************************/ 

	public static int partition_array(int[] a, int left, int right) {
		int p= a[left], s = left, aux;
		
		// p is the pivot
		for (int i=left+1; i<=right; i++){
			if (a[i] < p) {
				s++;
				aux = a[s]; a[s] = a[i]; a[i] = aux;
			}
		}
		aux = a[left];
		a[left] = a[s];
		a[s] = aux;
		return s;
	}

	/*********************************************************************
	 *
	 * Method name: selectionSort
	 * Description of the Method: Sorts the array by going through the array 
	 * and looking for the smallest values in order to put them at the 
	 * following position from the beginning 
	 * Calling arguments: int a[]; the unsorted array, boolean flag; 
	 * auxiliary flag to indicate if we want to sort the array in an 
	 * increasing order or in a decreasing one
	 * Return value: int[]; sorted array
	 *
	 *********************************************************************/

	public static int[] selectionSort (int[] a, boolean flag){
		int aux1=0; // Stores the smallest value
		int aux2=0; // Stores the value where the array goes through
		int pos=0; // Stores the position where the smallest value is

		for(int i=0  ;  i<a.length  ;  i++){
			aux1=a[i];
			pos=i;
			for(int j=i;j<a.length;j++){
				aux2=a[j];
				if(aux2<aux1 && flag==true){
					aux1=aux2;
					pos=j;
				}else if(aux2>aux1 && flag==false){
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
		int max = 100;
		Random r = new Random();
		for (int i = 0; i < n; i++){
			v[i] = r.nextInt(max);
		}
		return v;
	}

	/*********************************************************************
	 *
	 * Method name: askforK
	 * Description of the Method: Asks the user for a k value  
	 * Calling arguments: int[] a; given array
	 * Return value: int; k value introduced by the user
	 *
	 *********************************************************************/ 

	public static int askforK(int[] a){
		int k=0;
		Scanner sc = new Scanner(System.in);
		System.out.print ("\nIntroduce a k value:");
		do {
			k=sc.nextInt();

			if (k<1) {
				System.out.println ("Wrong k. Try again.");
			}else if(k >a.length ){
				System.out.println("Warning, the maximum value is: "+a.length);
				k=a.length;
			}
		} while (k<1);

		return k;
	}
	
	/*********************************************************************
	 *
	 * Method name: testcase
	 * Description of the Method: Auxiliary method to test all the cases
	 * It calls the main selection method described above
	 * Calling arguments: int[] array; given array, int k; statistics
	 * desired
	 * Return value: int; value of the executed test case
	 *
	 *********************************************************************/ 

	public static int testcase(int[] array,int k){
		int val=0;
		val=selection(array, 0, array.length-1, k);
		return val;
	}

	/*********************************************************************
	 *
	 * Method name: executeTest
	 * Description of the Method: Executes an specific test  
	 * Calling arguments: int[] array; a given array that can be of one
	 * of the three types we have already created them (random, increasing
	 * order or decreasing order)
	 * Return value: void;
	 *
	 *********************************************************************/ 

	public static void executeTest(int [] array){
		int k,val;
		k=askforK(array);
		val=testcase(array, k);
		System.out.println("Result: "+val);
	}
}