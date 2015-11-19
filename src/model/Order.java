package model;

public class Order {
	private int amount;
	private String state;
	
	public Order(int amount, String state){
		this.amount = amount;
		this.state = state;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public String getState(){
		return state;
	}
}
