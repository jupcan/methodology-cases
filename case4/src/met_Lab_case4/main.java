import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

/*********************************************************************
 *
 * Class Name: Met_P4
 * Author/s name: Diego Molero & Juan Perea
 * Release/Creation date: 03/03/2016
 * Class version: 1.0
 * Class description: Counts inversions in an array located in a file
 * 
 *********************************************************************/

public class main {

	/*********************************************************************
	 *
	 * Method name: main
	 * Description of the Method: Calls to methods in order to ask for 
	 * the file in which the program should looks for the array and
	 * to the main program one that makes all the code work
	 * arrays and calls to methods in order to test all the cases
	 * Return value: void
	 *
	 *********************************************************************/ 

	public static void main(String[] args) {
		int[] array = null;
		int val=0;
		while(true){
			array=fileInput();
			val=countInversions(array, 0, array.length-1);
			System.out.println(val);
		}
	}

	/*********************************************************************
	 *
	 * Method name: mergeAndCount
	 * Name of the original author: Professor
	 * Description of the Method: Indicates how far (or close) the array 
	 * is from being sorted. If the array is already sorted then inversion 
	 * count is 0. If array is sorted in reverse order that inversion 
	 * count is the maximum
	 * Calling arguments: int[] a; given array we want to work with, 
	 * int p; pointer for each subarray pointing to the front elements,
	 * int q; pointer for each subarray pointing to the front elements,
	 * int r; auxiliary variable to count
	 * Return value: int; number of inversions
	 *
	 *********************************************************************/ 

	static int mergeAndCount (int [ ] a, int p, int q, int r) {
		int i = p, j = q+1, k = p, count = 0;
		int [ ] temp = new int [r-p+1]; 
		for (int l = p; l <= r; l++)
			temp[ l - p ] = a[ l ];
		while (i <= q && j <= r) {
			if (temp [ i-p ] <= temp[ j-p ]) { 
				i++;
			} else { 
				a[k] = temp[ j-p ];
				j++;
				count += q+1-i; 
			} 
			k++;
		}
		if ( j - 1 == r ){
			while (i <= q ) {
				a[k] = temp[ i-p ];
				k++; i++;
			}
		}
		return count;
	}

	/*********************************************************************
	 *
	 * Method name: countInversions
	 * Name of the original author: Professor
	 * Description of the Method: Counts inversions in a recursive way
	 * Calling arguments: int[] a; given array, int left; an auxiliary
	 * left limit, int right; an auxiliary right limit
	 * Return value: int; number of inversions
	 *
	 *********************************************************************/ 

	public static int countInversions (int[] a, int left, int right){
		int count=0,middle;
		middle=(left+right)/2;

		if(left<right){
			count=countInversions(a,left,middle);
			count=count+countInversions(a,middle+1,right);
			count=count+mergeAndCount(a, left, middle, right);
		}
		return count;
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
}