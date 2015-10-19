package saving;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
   private List<Memento> mementoList = new ArrayList<Memento>();

   public void add(Memento state){
      mementoList.add(state);
   }

   public Memento get(int index){
      return mementoList.get(index);
   }
   
   public Memento getLatest(){
	   if(mementoList.size()==0)
		   return new Memento(null);
	   
	   return mementoList.get(mementoList.size()-1);
   }
}