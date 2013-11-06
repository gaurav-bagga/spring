package com.green.spring.beans.di.autowire;

public class Guitar implements Instrument{

	@Override
	public void play() {
		System.out.println("Playing guitar");
	}

}
