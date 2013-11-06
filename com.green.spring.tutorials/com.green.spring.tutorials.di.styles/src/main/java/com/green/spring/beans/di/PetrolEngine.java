package com.green.spring.beans.di;

import org.springframework.stereotype.Component;

import com.green.spring.beans.di.EngineType.Kind;

@Component
@EngineType(Kind.PETROL)
public class PetrolEngine implements Engine{

	@Override
	public void drive() {
		System.out.println("Driving PetrolEngine");
	}

	
}
