package met_Lab_case6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/*********************************************************************
*
* Class Name: Main
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 21/04/2016
* Class version: 1.0
* Class description: Main class of the program
*
*********************************************************************/

public class main {
	public static void main(String[] args){
		Fellowship [] fellowships;
		int [] x ,solution;
		//INITIALIZE VARIALBES
		fellowships= fileInput();
		x= new int[fellowships.length];
		solution = new int[fellowships.length];
		for (int i = 0; i < fellowships.length; i++){
			x[i] = 0; solution[i] = 0;
		}
		//-----------------------
		selection(x,solution,0,fellowships);
		printSolution(solution, fellowships);
		
	}
	/*********************************************************************
	 *
	 * Method name: selection
	 * Name of the original author: Professor
	 * Description of the Method: Selects a fellowship and includes it
	 * Calling arguments: Fellowship fellow; another fellowship, int[] x;
	 * auxiliary array, int level; fellowship's tree level, 
	 * Fellowship [] fellowships; whole fellowship's array
	 * Return value: void
	 *
	 *********************************************************************/ 
	
	public static void selection(int[] x, int[] solution,int level,Fellowship[] fellowships){
		if(level == fellowships.length){
			if(isBest(x, solution, fellowships)){
				for (int i = 0; i< x.length; i++){
					solution[i] = x[i];
				}
			}
		}else{
			if(isCompatible(fellowships[level], x, level, fellowships)){
				x[level]=1;
				selection(x,solution,level+1,fellowships);
			}
			x[level]=0;
			selection(x,solution,level+1,fellowships);
			
		}
	}
	
	/*********************************************************************
	 *
	 * Method name: isBest
	 * Name of the original author: Professor
	 * Description of the Method: checks if x is a better solution than 
	 * solution
	 * Calling arguments: int[] x; auxiliary array, int[] solution; 
	 * solutions array to compare with x, Fellowship [] fellowships; 
	 * whole fellowship's array
	 * Return value: int; best value between the ones we had (x and solution)
	 *
	 *********************************************************************/ 
	
	public static boolean isBest(int[] x, int[] solution,
			Fellowship[] fellowships){
		int sx = 0, ss = 0;
		for (int i = 0; i< x.length; i++){
			if (x[i] == 1) {
				sx += fellowships[i].getTotalSalary();
			}
			if (solution[i] == 1) {
				ss += fellowships[i].getTotalSalary();
			}
		}
		return sx > ss;
	}
	
	/*********************************************************************
	 *
	 * Method name: isCompatible
	 * Name of the original author: Professor
	 * Description of the Method: Checks if fellowship fellow is 
	 * compatible with the selection in x
	 * Calling arguments: Fellowship fellow; another fellowship, int[] x;
	 * auxiliary array, int level; fellowship's tree level, 
	 * Fellowship [] fellowships; whole fellowship's array
	 * Return value: boolean; whether its compatible or not to take it
	 *
	 *********************************************************************/ 
	
	public static boolean isCompatible(Fellowship fellow,int[] x, int level, Fellowship [] fellowships){
		boolean comp= true;
		for(int i=0; i< level && comp; i++){
			if(x[i] == 1){
				comp = fellow.isCompatible(fellowships[i]);
			}
		}
		return comp;
	}
	public static Fellowship[] fileInput(){
		Scanner sc = new Scanner(System.in);
		String file_name;
		System.out.println("What file do you want to read?");
		file_name=sc.next();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Fellowship [] array_st = null;
		String [] array_linea= new String [2];

		int array = 0;
		int n=-1; // The first element (iteration n=-1) its the array's size, then the values of the array
		try {
			archivo = new File (file_name);
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea=br.readLine())!=null){
				if(n==-1){
					array = Integer.parseInt(linea);
					array_st = new Fellowship[array];
				}else{
					array_linea=linea.split("\t");
					array_st[n]= new Fellowship(Integer.parseInt(array_linea[0]),Integer.parseInt(array_linea[1])
							,Integer.parseInt(array_linea[2]),Integer.parseInt(array_linea[3]));
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
		return array_st;
	}
	
	/*********************************************************************
	 *
	 * Method name: printSolution
	 * Description of the Method: Prints the final solution 
	 * Calling arguments: int[] x; auxiliary array, 
	 * Fellowship [] fellowships; whole fellowship's array
	 * Return value: void
	 *
	 *********************************************************************/
	
	public static void printSolution(int[]x,Fellowship [] fellowships){
		System.out.println("Solution:");
		int solution=0;
		for(int i=0;i<fellowships.length;i++){
			if(x[i]==1){
				System.out.println(fellowships[i].toString()+" "+fellowships[i].getTotalSalary());
				solution+=fellowships[i].getTotalSalary();
			}
		}
		System.out.println(solution+"€");
	}
}
