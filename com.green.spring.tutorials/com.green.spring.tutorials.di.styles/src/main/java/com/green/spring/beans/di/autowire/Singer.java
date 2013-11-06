package com.green.spring.beans.di.autowire;


public class Singer {

	
	private Instrument instrument;
	
	public Singer(){}
	
	public Singer(Instrument instrument){
		this.instrument = instrument;
	}
	
	public void play(){
		this.instrument.play();
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}
	
	
}
