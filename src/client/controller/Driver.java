package client.controller;

import client.view.GUI;

public class Driver {
	
	/**
	 * Para uma execução perfeita indicar os parametros abaixo, pois todos os metodos
	 * estão dependentes disto, mesmo que seja porta padrão e localhost. 
	 * @param args: -ORBInitialPort port -ORBInitalHost host -ServerDesired number
	 */
	public static void main(String args[]){
		GUI gui = new GUI();
		Controller controller = new Controller(args, gui);
		
		gui.setVisible(true);
	}

}
