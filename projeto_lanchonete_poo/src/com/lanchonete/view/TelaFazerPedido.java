package com.lanchonete.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.control.GerenciaMenu;
import com.lanchonete.control.GerenciaMesa;
import com.lanchonete.model.Pedido;
import com.lanchonete.model.Produto;
import com.lanchonete.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class TelaFazerPedido extends JFrame {

	private JPanel contentPane;
	private TelaPrincipal telaPrincipal;
	private TelaMesa telaMesa;

	/**
	 * Create the frame.
	 */
	public TelaFazerPedido() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblProduto = new JLabel("Produto");
		lblProduto.setBounds(12, 45, 66, 15);
		contentPane.add(lblProduto);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		for(Produto p:GerenciaMenu.listarProdutos()) {
			listModel.addElement(p.getCodigo()+"-"+p.getNome());
		}
		JList<String> listProdutos = new JList<>();
		listProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listProdutos.setModel(listModel);
		listProdutos.setSelectedIndex(0);
		listProdutos.setBounds(115, 44, 323, 183);
		contentPane.add(listProdutos);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(12, 247, 89, 15);
		contentPane.add(lblQuantidade);
		
		JSpinner spinnerQuant = new JSpinner();
		spinnerQuant.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerQuant.setBounds(115, 242, 323, 25);
		contentPane.add(spinnerQuant);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String s = listProdutos.getSelectedValue();
				
				if(GerenciaMesa.fazerPedido(TelaMesa.getMesa(), new Pedido((Integer)spinnerQuant.getValue(), 
						GerenciaMenu.EscolherProduto(Integer.parseInt(s.split("-")[0]))))) {
					JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!");
				}else {
					JOptionPane.showMessageDialog(null, "Pedido não foi efetuado!");
				}
				
			}
		});
		btnAdicionar.setBounds(12, 279, 426, 25);
		contentPane.add(btnAdicionar);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(12, 335, 426, 25);
		contentPane.add(btnNewButton);
	}
}
