package com.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerencia extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	public TelaGerencia() throws ParseException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JFormattedTextField ftfDI = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftfDI.setBounds(60, 12, 113, 25);
		contentPane.add(ftfDI);
		
		JLabel lblNewLabel = new JLabel("Inicio");
		lblNewLabel.setBounds(12, 17, 50, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(235, 17, 37, 15);
		contentPane.add(lblFim);
		
		JFormattedTextField ftfDF = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftfDF.setBounds(270, 12, 113, 25);
		contentPane.add(ftfDF);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(12, 44, 426, 154);
		contentPane.add(table);
		
		JLabel label = new JLabel("");
		label.setBounds(372, 210, 66, 15);
		contentPane.add(label);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(235, 235, 114, 25);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipal().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(59, 235, 114, 25);
		contentPane.add(btnVoltar);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(372, 210, 66, 15);
		contentPane.add(lblR);
	}
}
