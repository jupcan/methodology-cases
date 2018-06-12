package met_Lab_case5;

/*********************************************************************
*
* Class Name: Deposit
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 07/04/2016
* Class version: 1.0
* Class description: Contains the info of each truck's deposit
*
*********************************************************************/

public class Deposit {
	int max_fuel;
	float actual_fuel;
	public Deposit(int max_fuel){
		this.max_fuel=max_fuel;
		actual_fuel=max_fuel;
	}
	public int getMax_fuel() {
		return max_fuel;
	}
	public void setMax_fuel(int max_fuel) {
		this.max_fuel = max_fuel;
	}
	public float getActual_fuel() {
		return actual_fuel;
	}
	public void setActual_fuel(float f) {
		this.actual_fuel = f;
	}
}
