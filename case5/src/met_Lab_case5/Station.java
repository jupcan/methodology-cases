package met_Lab_case5;

/*********************************************************************
*
* Class Name: Station
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 07/04/2016
* Class version: 1.0
* Class description: Contains the info of each fuel station the 
* truck can or can not stop at
*
*********************************************************************/

public class Station {
	private String name;
	private float nextStation;
	public Station(String name, float nextStation){
		this.name=name;
		this.nextStation=nextStation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getNextStation() {
		return nextStation;
	}
	public void setNextStation(float nextStation) {
		this.nextStation = nextStation;
	}
	
	/*********************************************************************
	 *
	 * Method name: addFuel
	 * Description of the Method: Adds an amount of fuel equal to the 
	 * deposit of a truck (so that it's in full capacity)
	 * Calling arguments: Truck a; given truck
	 * Return value: void
	 *
	 *********************************************************************/ 
	
	public void addFuel(Truck a){
		a.getDep().setActual_fuel(a.getDep().getMax_fuel());
	}
}
