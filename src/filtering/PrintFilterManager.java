package filtering;

import java.util.ArrayList;

import framework.Game;
import services.Print;

public class PrintFilterManager {

	private FilterChain chain;
	private ArrayList<FilteringState> filteringStates = new ArrayList<FilteringState>();
	
	public PrintFilterManager(Print target){
		chain = new FilterChain(target);
		
		//create filter states
		ArrayList<Filter> filters = new ArrayList<Filter>();
		
		//debugging filter state
		filters.add(new FormatFilter());
		filters.add(new DebugFilter());
		filteringStates.add(new FilteringState(filters, "debug"));
		
		filters.clear();
		
		//warning filter state
		filters.add(new FormatFilter());
		filters.add(new WarningFilter());
		filteringStates.add(new FilteringState(filters, "warning"));
		
		filters.clear();
		
		//error filter state
		filters.add(new FormatFilter());
		filters.add(new ErrorFilter());
		filteringStates.add(new FilteringState(filters, "error"));
		
		setCurrentFilteringState("debug");
	}
	
	//adds a new filtering state that can be used
	public void addFilteringState(FilteringState filterState){
		filteringStates.add(filterState);
	}
	
	//sets the current filtering state to use
	private void setCurrentFilteringState(String stateName){
		if(getFilteringStateByName(stateName)==null) {
			return;
		}
			
		chain.setFilteringState(getFilteringStateByName(stateName));
	}
	
	
	//returns a filter state by its name
	private FilteringState getFilteringStateByName(String stateName){
		for(FilteringState state : filteringStates)
			if(state.getName().equalsIgnoreCase(stateName))
				return state;
		
		Game.print("Filtering state name not found: " + stateName, "warning");
		return null;
	}
	
	//filters a request in the current filter state
	public void filterRequest(String msg){
		chain.execute(msg);
	}
	
	//filters a request in the filter state for this request only
	public void filterRequest(String msg, String stateName){
		String oldState = chain.getFilteringStateName();
		setCurrentFilteringState(stateName);
		filterRequest(msg);
		setCurrentFilteringState(oldState);
	}
}
