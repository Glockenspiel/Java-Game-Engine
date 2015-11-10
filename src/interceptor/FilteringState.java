package interceptor;

import java.util.ArrayList;

public class FilteringState {
	
	private ArrayList<Filter> filters;
	private final String stateName;
	
	//object to store multiple filters with a key value, rather than using a hash map
	public FilteringState(ArrayList<Filter> filters, String stateName){
		this.stateName = stateName;
		this.filters = new ArrayList<Filter>(filters);
	}
	
	//returns the ArrayList of filters
	public ArrayList<Filter> getFilters(){
		return filters;
	}
	
	//returns the state name
	public String getName(){
		return stateName;
	}
}
