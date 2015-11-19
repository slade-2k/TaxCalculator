package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Order;
import view.GUI;

public class Controller implements ActionListener{
	private GUI gui;
	private Order order;

	private static final ArrayList<String> TAXDUTY = new ArrayList<String>();
	private static final double LISTPRICE = 2.5;
	private static final double TAX = 2.5;
	
	public Controller(GUI guiObj) {
		gui = guiObj;
		TAXDUTY.add("WI");
		TAXDUTY.add("WISCONSIN");
		
		gui.addButtonListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (gui.getOrder() != null) {
			order = gui.getOrder();
			calcTaxes();
		} else {
			gui.clearFields();
		}
	}

	private void calcTaxes() {
		if(TAXDUTY.contains(order.getState().toUpperCase())){
			double subtotal = LISTPRICE * order.getAmount();
			double tax = subtotal/100 * TAX;
			gui.setTotal(subtotal + tax, subtotal, tax);
		} else {
			gui.setTotal(LISTPRICE * order.getAmount());
		}
	}
}
