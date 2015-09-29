package framework;

public class ExampleComponent extends Component {

	private int num=1;
	
	public ExampleComponent(){
		setType("Example");
	}
	
	@Override
	public void update() {
		System.out.println("Updating Component:" + getType() + " in " + getGameObjectTag() + " num:" + num);
	}
	
	//example of changing value in component
	public void setNum(int num){
		this.num=num;
	}

}
