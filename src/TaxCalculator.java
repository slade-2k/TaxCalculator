import controller.Controller;
import view.GUI;

public class TaxCalculator {
	public static void main(String args[]){
		GUI gui = new GUI();
		Controller controller = new Controller(gui);
	}
}
