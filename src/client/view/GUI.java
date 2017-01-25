package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class GUI extends JFrame{
	
	private JButton addBtn, removeBtn, editBtn, listhBtn;
	private JPanel mainPanel, btnPanel, labelPanel;
	private DefaultListModel listModel;
	private JList contactsList;
	
	public GUI(){
		setWindows();
		labelPanel = createLabelPanel(new JPanel());
		mainPanel = createMainPanel(new JPanel());
		btnPanel = createButtonPanel(new JPanel());
		
		this.add(labelPanel, BorderLayout.NORTH);
		this.add(mainPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
	}
	
	private void setWindows(){
		this.setTitle("Agenda Compartilhada");
		this.setSize(400, 300);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
	}
	
	public JPanel createButtonPanel(JPanel panel){
		panel.setPreferredSize(new Dimension(400, 50));
		panel.setLayout(new FlowLayout());
		
		addBtn = new JButton("Adicionar");
		addBtn.setPreferredSize(new Dimension(80, 45));
		
		removeBtn = new JButton("Remover");
		removeBtn.setPreferredSize(new Dimension(80, 45));
		
		editBtn = new JButton("Editar");
		editBtn.setPreferredSize(new Dimension(80, 45));
		
		listhBtn = new JButton("Consultar");
		listhBtn.setPreferredSize(new Dimension(80, 45));
		
		panel.add(addBtn);
		panel.add(editBtn);
		panel.add(listhBtn);
		panel.add(removeBtn);
		
		return panel;
	}
	
	public JPanel createLabelPanel(JPanel panel){
		panel.setPreferredSize(new Dimension(400, 45));
		
		JLabel label = new JLabel("AGENDA");
		label.setPreferredSize(new Dimension(400, 45));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setFont(new Font(Font.SERIF, Font.ITALIC, 30));
		panel.add(label);
		
		return panel;
	}
	
	public JPanel createMainPanel(JPanel panel){
		panel.setPreferredSize(new Dimension(400, 175));
		
		contactsList = new JList<>();
		listModel = new DefaultListModel();
		contactsList.setModel(listModel);
//		contactsList.setPreferredSize(new Dimension(400, 175));
		JScrollPane scroll = new JScrollPane(contactsList);
		scroll.setPreferredSize(new Dimension(400, 175));
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scroll);
		
		return panel;
	}
	
	public void addContactListener(ActionListener listener){
		addBtn.addActionListener(listener);
	}
	
	public void removeContactListener(ActionListener listener){
		removeBtn.addActionListener(listener);
	}
	
	public void searchContactListener(ActionListener listener){
		listhBtn.addActionListener(listener);
	}
	
	public void editContatcListener(ActionListener listener){
		editBtn.addActionListener(listener);
	}
	/*
	 * Getters and Setters
	 */
	public JList getContactsList() {
		return contactsList;
	}
//
//	public void setContactsList(JList contactsList) {
//		this.contactsList = contactsList;
//	}

	public DefaultListModel getListModel() {
		return listModel;
	}

//	public void setListModel(DefaultListModel listModel) {
//		this.listModel = listModel;
//	}
	
	
}
