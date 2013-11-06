package com.green.spring.beans.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.green.spring.beans.di.EngineType.Kind;

@Component
public class Car {

	private Engine engine;
	
	public Car(){}
	
	//constructor
	@Autowired
	@EngineType(Kind.DIESEL)
	public Car(Engine engine){
		this.engine = engine;
	}
	
	public void drive(){
		this.engine.drive();
	}

	public Engine getEngine() {
		return engine;
	}

	//setter
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	
}
