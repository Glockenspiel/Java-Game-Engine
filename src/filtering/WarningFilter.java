package filtering;

public class WarningFilter implements Filter {

	//warning filter
	@Override
	public String filterMsg(String msg) {
		return "Warning!: " + msg;
	}

}
