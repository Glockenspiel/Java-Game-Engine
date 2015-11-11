package filtering;

public class ErrorFilter implements Filter {

	@Override
	public String filterMsg(String msg) {
		return "ERROR!: " + msg;
	}

}
