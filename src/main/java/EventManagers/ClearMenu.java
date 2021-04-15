package EventManagers;



import baseinterfacesclasses.Command;
import baseinterfacesclasses.SingletonObjects;

public class ClearMenu implements Command{

	public ClearMenu(){};

	@Override
	public void execute() {
		SingletonObjects.menumanager.clearAtnext();
		
	}

}
