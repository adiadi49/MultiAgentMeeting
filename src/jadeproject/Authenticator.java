package jadeproject;

import jade.core.Agent;
import jade.core.AID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;


public class Authenticator extends Agent {
	 
	// init gui
     private AuthenticatorGui gui;
	 
	 protected void setup() {
		 	gui = new AuthenticatorGui(this);
	    	gui.display();
	     
		/** 
		  Object[] args = getArguments();
	      
	      int numberOfPlayers = Integer.parseInt( (String) args[0]);
		  
	      for (int i=0; i<numberOfPlayers; i++) {
	      	String name = "Player" + (i+1);
	        createAgent(name, "jadeproject.ParticipantAgent");
	      }
		 **/ 
	      
	  }

	  public void createAgent(String name, String className) {
	      	AID agentID = new AID( name, AID.ISLOCALNAME );
	      	System.out.println("Inside Create Agent");
	      	AgentContainer controller = getContainerController();
	      	try {
	      		AgentController agent = controller.createNewAgent( name, className, null );
	      		agent.start();
	      		System.out.println("+++ Created: " + agentID);
	      	}
	      	catch (Exception e){ e.printStackTrace(); }
	  }

}
