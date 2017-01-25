package server;

import java.util.ArrayList;
import java.util.List;

import AgendaCORBA.AgendaInterfacePOA;

public class AgendaImpl extends AgendaInterfacePOA{
	
	private List<String> lisContacts = new ArrayList<String>();
	
	private List<String> decodeContacts(List<String> contacts){
		List<String> listNames = new ArrayList<String>();
		for (String string : contacts) {
			String aux[] = string.split("::"); 
			listNames.add(aux[0]);
		}
		return listNames;
	}
	
	private boolean contactExist(String name){
		List<String> currentContacts = decodeContacts(lisContacts);
		if(currentContacts.contains(name))
			return true;
		else
			return false;
	}
	
	@Override
	public void insert(String name, int number) {
		if(!contactExist(name))
			lisContacts.add(name+"::"+number);
		else
			System.out.println("O contato já existe!");
	}

	@Override
	public void remove(int index) {
		if(index != -1)
			lisContacts.remove(index);
	}
	
	@Override
	public int search(String name) {
		List<String> listNames = decodeContacts(lisContacts);
		if(listNames.contains(name))
			return listNames.indexOf(name);
		else
			return -1;
	}

	@Override
	public void update(int index, String name, int number) {
		lisContacts.set(index, name+"::"+number);
	}

	@Override
	public String[] getListContacts() {
		return lisContacts.toArray(new String[lisContacts.size()]);
	}

	@Override
	public void setListContacts(String[] list) {
		this.lisContacts = new ArrayList<String>();
		for (int i=0; i<list.length; i++){
			lisContacts.add(list[i]);
		}
	}
	
	@Override
	public String isAlive(){
		return "Agenda";
	}

}
	