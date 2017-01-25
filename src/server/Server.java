package server;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import AgendaCORBA.AgendaInterface;
import AgendaCORBA.AgendaInterfaceHelper;

public class Server {
	
	public static AgendaInterface agenda;
	
	public static int checkConection(String []args, int value){
		try {
			setConection(args, value);
			agenda.isAlive();
			return value;
		} catch (Exception e1) {
			try {
				try {
					try {
						if(value == 1) throw new Exception();
						setConection(args, 1);
						agenda.isAlive();
						return 1;
					} catch (Exception e2) {
						if(value == 2) throw new Exception();
						setConection(args, 2);
						agenda.isAlive();
						return 2;
					}
				} catch (Exception e3) {
					if (value == 3) throw new Exception();
					setConection(args,3);
					agenda.isAlive();
					return 3;
				}
			} catch (Exception e4) {
				return -1;
			}
		}
	}
	
	public static void setConection(String args[], int value){
		try {
      		ORB orb = ORB.init(args, null);
      		org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService") ;
			NamingContext naming = NamingContextHelper.narrow(obj);

			NameComponent[] name = { new NameComponent("Agenda-Server","Agenda"+value)};
			org.omg.CORBA.Object objRef = naming.resolve(name);

			agenda = AgendaInterfaceHelper.narrow(objRef);
		} catch(Exception ex){
			System.err.println("Erro ao tentar conectar a Agenda-Server " + value);
		} 
	}
	
	/**
	 * Para uma execução perfeita indicar os parametros abaixo, pois todos os metodos
	 * estão dependentes disto, mesmo que seja porta padrão e localhost. 
	 * @param args: -ORBInitialPort port -ORBInitalHost host -ServerDesired number
	 */
	public static void main(String args[]){
		try{
			ORB orb = ORB.init(args, null);
			org.omg.CORBA.Object objPoa = orb.resolve_initial_references("RootPOA");
			POA rootPOA = POAHelper.narrow(objPoa);
			org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService") ;
			NamingContext naming =  NamingContextHelper.narrow(obj);

			AgendaImpl agenda = new AgendaImpl();
			
			org.omg.CORBA.Object objRef = rootPOA.servant_to_reference(agenda);
			NameComponent[] name = {new NameComponent("Agenda-Server","Agenda"+args[5])};
			
			naming.rebind(name,objRef);
			rootPOA.the_POAManager().activate();
			
			int online = checkConection(args, 1);
			if(online != -1 && online != Integer.parseInt(args[5]))
				agenda.setListContacts(Server.agenda.getListContacts());
			
			System.out.println("Agenda-Server " + args[5] + " pronto...");
			orb.run();
		}catch (Exception ex){ 
			System.out.println("Erro"); 
			ex.printStackTrace();
		} 
	}
}
