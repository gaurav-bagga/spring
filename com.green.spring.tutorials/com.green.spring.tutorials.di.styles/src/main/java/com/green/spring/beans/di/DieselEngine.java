package com.green.spring.beans.di;

import org.springframework.stereotype.Component;

import com.green.spring.beans.di.EngineType.Kind;

@Component
@EngineType(Kind.DIESEL)
public class DieselEngine implements Engine{

	@Override
	public void drive() {
		System.out.println("Driving DieselEngine");	
	}

}
