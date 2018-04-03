package myApp.client;

import com.google.gwt.core.client.EntryPoint;
import myApp.frame.Login;

public class HIIS implements EntryPoint {

	@Override
	public void onModuleLoad() {
		myApp.frame.Login login = new Login();
		login.open();  
	}
}
