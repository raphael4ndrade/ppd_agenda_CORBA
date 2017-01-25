package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import AgendaCORBA.AgendaInterface;
import AgendaCORBA.AgendaInterfaceHelper;
import client.view.GUI;

public class Controller {
	
	private class ListContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if((agendaNumber = checkConection(args, agendaNumber)) != -1){
				refresh();
			}
		}
	}
	
	private class AddContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if((agendaNumber = checkConection(args, agendaNumber)) != -1){
					String name = JOptionPane.showInputDialog(gui, "Nome do Contato");
					String number = JOptionPane.showInputDialog(gui, "Número do Contato");
					//TODO verificação do número
					agenda.insert(name, Integer.parseInt(number));
					refresh();
					updateAgendas(agenda.getListContacts());
				}
			} catch(NumberFormatException ex){
				System.out.println("Erro ao converter o numero...");
			}
		}
	}
	
	private class RemoveContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if((agendaNumber = checkConection(args, agendaNumber)) != -1){
				int i = gui.getContactsList().getSelectedIndex();
				agenda.remove(i);
				refresh();
				updateAgendas(agenda.getListContacts());
			}
		}
	}
	
	private class EditContact implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				if((agendaNumber = checkConection(args, agendaNumber)) != -1){
					int i = gui.getContactsList().getSelectedIndex();
					String []aux = agenda.getListContacts()[i].split("::");
					String name = JOptionPane.showInputDialog(gui, "Novo nome do contato", aux[0]);
					String number = JOptionPane.showInputDialog(gui, "Novo numero do contato", aux[1]);
					
					agenda.update(i, name, Integer.parseInt(number));
					refresh();
					updateAgendas(agenda.getListContacts());
				}
			} catch(NumberFormatException ex){
				System.out.println("Erro ao converter o numero...");
			}
		}
	}
	
	private GUI gui;
	private AgendaInterface agenda;
	private final String[] args;
	private int agendaNumber;
	
	public Controller(String []args, GUI gui){
		this.gui = gui;
		this.args = args;
		
		gui.searchContactListener(new ListContact());
		gui.addContactListener(new AddContact());
		gui.removeContactListener(new RemoveContact());
		gui.editContatcListener(new EditContact());
		
		//Primeira conecção e update da lista de contatos.
		this.agendaNumber = checkConection(args, Integer.parseInt(args[5]));
		refresh();
	}
	
	private void refresh(){
		gui.getListModel().clear();
		String[] listContacts = agenda.getListContacts();
		for (String s : listContacts) {
			gui.getListModel().addElement(s);
		}
		gui.getContactsList().setModel(gui.getListModel());
	}
	
	public int checkConection(String []args, int value){
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
	
	private void setConection(String args[], int value){
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
	
	private void updateAgendas(String[] contacts){
		for(int i=1; i<4; i++){
			try {
	      		ORB orb = ORB.init(args, null);
	      		org.omg.CORBA.Object obj = orb.resolve_initial_references("NameService") ;
				NamingContext naming = NamingContextHelper.narrow(obj);
	
				NameComponent[] name = { new NameComponent("Agenda-Server","Agenda"+i)};
				org.omg.CORBA.Object objRef = naming.resolve(name);
				
				agenda = AgendaInterfaceHelper.narrow(objRef);
				agenda.setListContacts(contacts);
			} catch(Exception ex){
				System.err.println("Erro ao tentar atualizar a Agenda-Server " +i);
			}
		}
	}
}
