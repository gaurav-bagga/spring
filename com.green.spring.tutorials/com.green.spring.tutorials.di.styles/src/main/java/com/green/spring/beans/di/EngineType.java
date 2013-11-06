package com.green.spring.beans.di;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Qualifier;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.TYPE,ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface EngineType {

	public enum Kind {PETROL,DIESEL};
	
	public Kind value() default Kind.PETROL ;
}
