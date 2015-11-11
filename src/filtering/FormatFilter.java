package filtering;

public class FormatFilter implements Filter {

	//formating filter
	@Override
	public String filterMsg(String msg) {
		//remove double spaces
		msg = msg.trim().replaceAll(" +", " ");
		
		//First word is capital
		if(msg.length()>1){
			char firstChar = Character.toUpperCase(msg.charAt(0));
			msg=firstChar + msg.substring(1);
		}
		
		return msg;
	}

}
