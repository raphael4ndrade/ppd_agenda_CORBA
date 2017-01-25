package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddGui {
	
	private JFrame frame;
	private JTextField nameText, numberText;
	private JButton btn;
	
	public AddGui(){
		frame = new JFrame();
		frame.setTitle("Adicionar Contato");
		frame.setSize(400, 100);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.setLayout(new BorderLayout());
		
		frame.add(createAddPanel(new JPanel()), BorderLayout.NORTH);
		frame.add(createBtnPanel(new JPanel()), BorderLayout.CENTER);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public JPanel createAddPanel(JPanel panel){
		panel.setPreferredSize(new Dimension(400, 35));
		panel.setLayout(new FlowLayout(SwingConstants.CENTER, 0, 0));
		
		JLabel nameLabel = new JLabel("Nome");
		nameLabel.setPreferredSize(new Dimension(45, 35));
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 35));
		
		JLabel numberLabel = new JLabel("Número ");
		numberLabel.setPreferredSize(new Dimension(55, 35));
		numberText = new JTextField();
		numberText.setPreferredSize(new Dimension(150, 35));
		
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(numberLabel);
		panel.add(numberText);
		
		return panel;
	}
	
	public JPanel createBtnPanel(JPanel panel){
		panel.setPreferredSize(new Dimension(400, 35));
		
		btn = new JButton("Adicionar!");
		btn.setPreferredSize(new Dimension(100, 35));
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		
		panel.add(btn);
		
		return panel;
	}
	
	public String getName(){
		return nameText.getText();
	}
	
	public String getNumber(){
		return numberText.getText();
	}
}
