package com.green.spring.beans.aop.expl;

public class AOPExplaination {

	
	interface Service{
		void action();
	}
	
	class MainService implements Service{

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
	}
	
	class MainServiceProxy implements Service{

		private Service service = new MainService();
		
		@Override
		public void action() {
			try{
				//before
				//-----------------
				//around
				//-------------
				service.action();
				//after
				//after returning
			}catch(Exception e){
				//after exception
			}finally{
				
			}
			
		}
		
	}
}
