package com.lanchonete.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.lanchonete.control.Gerencia;
import com.lanchonete.model.Comanda;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaGerencia extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
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
		String[] cols = {"Data", "Comanda", "Valor"};
		model = new DefaultTableModel(cols, 0);
		table.setModel(model);
		contentPane.add(table);
		table.setEnabled(true);
		table.setRowSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(12, 44, 426, 154);
		
		
		JLabel label = new JLabel("");
		label.setBounds(372, 210, 66, 15);
		contentPane.add(label);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ftfDI.getText();
				ftfDF.getText();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate di = LocalDate.parse(ftfDI.getText(), formatter);
				LocalDate df = LocalDate.parse(ftfDF.getText(), formatter);
				if(di.isAfter(df)) {
					JOptionPane.showMessageDialog(null, "Informe corretamente o intervalo de datas!");
				}else {
					ArrayList<Comanda> comandas = Gerencia.listarComandasEntre(di, df);
					if(comandas.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Comandas não encontradas");
					}else {
						for(Comanda c:comandas) {
							model.addRow(new String[]{c.getData().toString(), " Comanda "+c.getContador(), " "+c.valorTotal()+" R$"});
						}
					}
				}
			}
		});
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
