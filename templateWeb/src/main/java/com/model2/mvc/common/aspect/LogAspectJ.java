package com.model2.mvc.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;


public class LogAspectJ {

	///Constructor
	public LogAspectJ() {
		System.out.println("\nCommon :: "+this.getClass()+"\n");
	}
	
	//Around  Advice
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
			
		System.out.println("");
		System.out.println("[Around before]ddd"+
													joinPoint.getTarget().getClass().getName() +"."+
													joinPoint.getSignature().getName());
		if(joinPoint.getArgs().length !=0){
			System.out.println("[Around before]ddd : "+ joinPoint.getArgs()[0]);
		}
		Object obj = joinPoint.proceed();

		System.out.println("[Around after]ddd  : "+obj);
		System.out.println("");
		
		return obj;
	}
	
}//end of class