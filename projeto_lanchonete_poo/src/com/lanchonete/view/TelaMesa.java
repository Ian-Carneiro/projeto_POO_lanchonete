package com.lanchonete.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.control.GerenciaMenu;
import com.lanchonete.control.GerenciaMesa;
import com.lanchonete.model.Comanda;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class TelaMesa extends JFrame {

	private JPanel contentPane;
	private TelaFazerPedido telaFazerPedido;
	private TelaVerPedidos telaVerPedidos;
	private TelaPrincipal telaPrincipal;
	private static Integer mesa;

	public TelaMesa() {
		setTitle("Mesa");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblMesa = new JLabel("MESA");
		lblMesa.setBounds(40, 17, 42, 15);
		contentPane.add(lblMesa);
		
		JSpinner spinnerMesa = new JSpinner();
		spinnerMesa.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerMesa.setBounds(84, 12, 364, 25);
		contentPane.add(spinnerMesa);
		
		JButton btnNovaComanda = new JButton("Nova comanda");
		btnNovaComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(GerenciaMesa.novaComanda((Integer)spinnerMesa.getValue())){
						JOptionPane.showMessageDialog(null, "Comanda criada com sucesso!");
					}else {
						JOptionPane.showMessageDialog(null, "A comanda não pôde ser criada!");
					}
				} catch (HeadlessException | ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNovaComanda.setBounds(12, 59, 140, 25);
		contentPane.add(btnNovaComanda);
		
		JButton btnNewButton = new JButton("Fazer Pedido");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(GerenciaMenu.listarProdutos()!=null && GerenciaMesa.getComanda((Integer)spinnerMesa.getValue())!=null) {
						mesa = (Integer)spinnerMesa.getValue();//static
						telaFazerPedido = new TelaFazerPedido();
						telaFazerPedido.setVisible(true);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "A lista de produtos está vazia\nou a comanda não foi criada!");
					}
				} catch (HeadlessException | ClassNotFoundException | IOException e) {
					JOptionPane.showMessageDialog(null, "Falha na operação de Novo Pedido", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(348, 59, 140, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver pedidos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(GerenciaMesa.verPedidos((Integer)spinnerMesa.getValue())=="") {
						JOptionPane.showMessageDialog(null, "A comanda não foi criada!");
					}else{
						mesa = (Integer)spinnerMesa.getValue();//static
						telaVerPedidos = new TelaVerPedidos();
						telaVerPedidos.setVisible(true);
						dispose();
					}
				} catch (HeadlessException | ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(179, 59, 140, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnEncerrarComanda = new JButton("Encerrar Comanda");
		btnEncerrarComanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Comanda c = null;
				try {
					c = GerenciaMesa.getComanda((Integer)spinnerMesa.getValue());
					if(c!=null && GerenciaMesa.encerrarComanda(c.getMesa())) {
						JOptionPane.showMessageDialog(null, "Comanda encerrada com sucesso!\n" +"Valor Total:"
								+ c.valorTotal());
					}
					else {
						JOptionPane.showMessageDialog(null, "A comanda não existe ou seus pedidos não foram atendidos");
					}
				}catch (HeadlessException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Falha na operação Encerrar Comanda", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha no acesso ao arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEncerrarComanda.setBounds(12, 100, 476, 25);
		contentPane.add(btnEncerrarComanda);
		
		JButton btnNewButton_2 = new JButton("Voltar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(12, 137, 476, 25);
		contentPane.add(btnNewButton_2);
	}
	public static Integer getMesa() {
		return mesa;
	}
}
