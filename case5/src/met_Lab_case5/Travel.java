package met_Lab_case5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/*********************************************************************
*
* Class Name: Travel
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 07/04/2016
* Class version: 1.0
* Class description: Main class that stores all the info of the
* truck's travel throughout all the stations
*
*********************************************************************/


public class Travel {
	
	/*********************************************************************
	 *
	 * Method name: main
	 * Description of the Method: Creation of the stations and 
	 * execution of the truck's travel calculating whether it's necessary 
	 * to stop in a gas station or not
	 *
	 *********************************************************************/ 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Deposit dep = new Deposit(150);
		Truck tru = new Truck("01",dep);
		Station [] array_st = null;
		/*Station [] array_st = new Station[6];
		array_st[0] = new Station ("G0",20);
		array_st[1] = new Station ("G1",10);
		array_st[2] = new Station ("G2",20);
		array_st[3] = new Station ("G3",20);
		array_st[4] = new Station ("G4",10);
		array_st[5] = new Station ("G5",10);*/
		array_st=fileInput();
		System.out.println(tru.drive(array_st));
		System.out.println("Fin del trayecto");
		
	}
	
	/*********************************************************************
	 *
	 * Method name: fileInput
	 * Description of the Method: Opens and reads the values written in the 
	 * file given by the user, then it stores them in an array
	 * Required Files: A text file where the first number is the size of 
	 * the array and the followings are the values, separated by lanes. 
	 * It must be into the same directory as the program
	 * Return value: Station[]; array with the read values of the file
	 * Exception e: when trying to open the file
	 * Exception e1: when trying to close the file
	 *
	 *********************************************************************/
	
	public static Station[] fileInput(){
		Scanner sc = new Scanner(System.in);
		String file_name;
		System.out.println("What file do you want to read?");
		file_name=sc.next();
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Station [] array_st = null;
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
					array_st = new Station[array];
				}else{
					array_linea=linea.split(" ");
					array_st[n]= new Station ((array_linea[1]),Float.parseFloat(array_linea[0]));
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

}
