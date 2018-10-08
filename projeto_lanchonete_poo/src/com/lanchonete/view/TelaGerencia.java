package com.lanchonete.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaGerencia extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table_1;
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
		
		JLabel lblInicio = new JLabel("Inicio");
		contentPane.add(lblInicio);
		
		JFormattedTextField ftfDI = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftfDI.setBounds(60, 12, 113, 25);
		contentPane.add(ftfDI);
		
		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(235, 17, 37, 15);
		contentPane.add(lblFim);
		
		JFormattedTextField ftfDF = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftfDF.setBounds(270, 12, 113, 25);
		contentPane.add(ftfDF);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 49, 426, 149);
		contentPane.add(scrollPane);
		table_1 = new JTable() {
		    public boolean isCellEditable(int rowIndex, int vColIndex) {
		    	return false;
		    }
		};
		table_1.removeEditor();
		model = new DefaultTableModel();
		model.addColumn("Data");
		model.addColumn("Comanda");
		model.addColumn("Valor");
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);
		
		JLabel lblR = new JLabel("R$");
		lblR.setBounds(367, 210, 66, 15);
		contentPane.add(lblR);
		
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
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
							int quantRows = model.getRowCount();
							if(quantRows>0) {
								for(int i = 0;i < quantRows;i++) {
									model.removeRow(0);
								}
							}
							for(Comanda c:comandas) {
								model.addRow(new String[]{c.getData().toString(), "Comanda "+c.getContador(), c.valorTotal()+" R$"});	
							}
							lblR.setText(Gerencia.lucroTotalEntre(di, df)+" R$");
						}
					}
				}catch (DateTimeParseException ex) {
					JOptionPane.showMessageDialog(null, "Preencha com datas válidas", "Formato inválido", JOptionPane.ERROR_MESSAGE);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Arquivo não encontrado", "Falha", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Falha na busca", "Falha", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnBuscar.setBounds(235, 221, 114, 25);
		contentPane.add(btnBuscar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TelaPrincipal().setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(109, 221, 114, 25);
		contentPane.add(btnVoltar);
		
		
		
		
		
		
	}
}
