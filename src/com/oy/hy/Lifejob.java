package com.oy.hy;

public class Lifejob {

	public int _id;
	public String buy;
	public String food;
	public String wash;
	public String clean;
	
	public Lifejob(){
	}
	
	public Lifejob(String buy,String food,String wash,String clean){
		this.buy = buy;
		this.food = food;
		this.wash = wash;
		this.clean = clean;
	}
	
	public Lifejob(int _id,String buy,String food,String wash,String clean){
		this._id = _id;
		this.buy = buy;
		this.food = food;
		this.wash = wash;
		this.clean = clean;
	}
	
}