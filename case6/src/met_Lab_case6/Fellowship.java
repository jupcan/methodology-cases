package met_Lab_case6;

/*********************************************************************
*
* Class Name: Fellowship
* Author/s name: Diego Molero & Juan Perea
* Release/Creation date: 21/04/2016
* Class version: 1.0
* Class description: Contains the fellowship's info
*
*********************************************************************/

public class Fellowship {
	private int id;
	private int start_m;
	private int finish_m;
	private int salary;
	public Fellowship(int a,int b,int c,int d){
		id=a;
		start_m=b;
		finish_m=c;
		salary=d;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Fellowship [id=" + id + ", start_m=" + start_m + ", finish_m=" + finish_m + ", salary=" + salary + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStart_m() {
		return start_m;
	}
	public void setStart_m(int start_m) {
		this.start_m = start_m;
	}
	public int getFinish_m() {
		return finish_m;
	}
	public void setFinish_m(int finish_m) {
		this.finish_m = finish_m;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	/*********************************************************************
	 *
	 * Method name: getDuration
	 * Description of the Method: Calculates the duration of a fellowship
	 * by subtracting to its finish date the one when it started 
	 * Return value: void
	 *
	 *********************************************************************/ 
	
	public int getDuration(){
		return this.finish_m - this.start_m +1;
	}
	
	/*********************************************************************
	 *
	 * Method name: getTotalSalary
	 * Description of the Method: Calculates the total salary of a 
	 * fellowship by multiplying its duration and its given salary 
	 * Return value: void
	 *
	 *********************************************************************/ 
	
	public int getTotalSalary(){
		return this.getDuration()*this.getSalary();
	}
	
	/*********************************************************************
	 *
	 * Method name: isCompatible
	 * Description of the Method: Checks if its compatible to take another
	 * fellowship or not because of them overlapping 
	 * Calling arguments: Fellowship other; another fellowship 
	 * Return value: boolean; whether its compatible or not to take it
	 *
	 *********************************************************************/ 
	
	
	public boolean isCompatible(Fellowship other){
		int as = this.start_m - 1;
		int af = this.finish_m;
		int bs = other.getStart_m() - 1;
		int bf = other.getFinish_m();
		if(af > bs && af <= bf ||
				as <=bs && af >= bf ||
				as >=bs && as < bf ||
				as >=bs && af <= bf ){
			return false;
		}else{
			return true;
		}
	}
}