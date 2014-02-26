package com.example.tahwissa;

//Define a new class called "Element" (element of the list) which can be a hotel, a restaurant or a cool place
public class Element {
	
	//An Element has three fields : name, rating and phoneNumber
	public String name;
	public int rating;
	public String phoneNumber;
	
	//Define a default constructor
	public Element(){
		super();
	}
	
	//Define a second constructor that takes three parameters and instantiate the fields of the Element
	public Element(String name, int valeur, String phoneNumber) {
		super();
		this.name = name;
		this.rating = valeur;
		this.phoneNumber = phoneNumber;
	}
	
	//Define a toString() method that returns the name of the Element
	public String toString() {
		return name;
	}

}
