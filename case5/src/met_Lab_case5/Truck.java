package met_Lab_case5;

/*********************************************************************
*
* Class Name: Truck
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 07/04/2016
* Class version: 1.0
* Class description: Contains the info of each truck
*
*********************************************************************/

public class Truck {
	String id;
	Deposit dep;
	public Truck(String id,Deposit dep){
		this.id=id;
		this.dep=dep;
	}
	public String getId() {
		return id;
	}
	public Deposit getDep() {
		return dep;
	}
	
	/*********************************************************************
	 *
	 * Method name: drive
	 * Description of the Method: Calculates if a given truck needs to
	 * stop in a fuel station or not by checking its actual fuel. If
	 * it does not have enough to reach the next station then it stops
	 * and the fuel is added to its deposit
	 * Calling arguments: Station [] array_st; array of fuel stations
	 * Return value: String; info of the stations the truck has stopped at
	 *
	 *********************************************************************/ 
	
	public String drive(Station [] array_st){
		int i=0;
		int count=0;
		String info="";
		info+=array_st[i].getName()+" "+count+"º\n";
		count++;
		while(i < array_st.length){
			if(this.dep.actual_fuel>= array_st[i].getNextStation()){
				this.getDep().setActual_fuel(this.dep.getActual_fuel()-array_st[i].getNextStation());
				i++;
			}else{
				array_st[i].addFuel(this);
				count++;
				info+= array_st[i].getName()+" "+count+"º\n";
				
			}
		}
		info+=array_st[i-1].getName()+" "+count+"º\n";
		return info;
	}
}
