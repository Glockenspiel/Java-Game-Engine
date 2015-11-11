package filtering;

import services.Print;

public class FilterChain {
	
	private Print target;
	private FilteringState filteringState;
	
	public FilterChain(Print target){
		setTarget(target);
	}
	
	//get the filtering state
	public void setFilteringState(FilteringState filterState){
		this.filteringState = filterState;
	}
	
	//set the target to print to
	public void setTarget(Print target){
		this.target = target;
	}
	
	//get filtering state name
	public String getFilteringStateName(){
		return filteringState.getName();
	}
	
	//execute the filters and then execute the target
	public void execute(String msg){
		for(Filter f : filteringState.getFilters()){
			msg = f.filterMsg(msg);
		}
		
		target.log(msg);
	}
}
