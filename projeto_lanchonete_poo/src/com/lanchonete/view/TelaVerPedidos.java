package com.lanchonete.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.control.GerenciaMesa;
import com.lanchonete.model.Pedido;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class TelaVerPedidos extends JFrame {

	private JPanel contentPane;
	private Float total;
	private TelaMesa telaMesa;
	private TelaAlterarPedido telaAlterarPedido;
	private static Integer numeroPedido;
	/**
	 * Create the frame.
	 */
	public TelaVerPedidos() {
		setTitle("Pedidos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		total = 0f;
		for(Pedido p:GerenciaMesa.getComanda(TelaMesa.getMesa()).getListaPedidos()) {
			listModel.addElement(p.getNumeroPedido()+"- Quantidade:"+p.getQuantidade()+" "+p.getProduto().getNome()+" subtotal: R$ "+p.getValorTotal());
			total+=p.getProduto().getPreco();
		}
		
		JList<String> listPedidos = new JList<>();
		listPedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPedidos.setModel(listModel);
		listPedidos.setSelectedIndex(0);
		listPedidos.setBounds(12, 0, 426, 187);
		contentPane.add(listPedidos);
		
		JLabel lblTotal = new JLabel("Total: R$ "+GerenciaMesa.getComanda(TelaMesa.getMesa()).valorTotal());
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setBounds(213, 199, 225, 15);
		contentPane.add(lblTotal);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnOk.setBounds(79, 226, 114, 25);
		contentPane.add(btnOk);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				numeroPedido = Integer.parseInt(listPedidos.getSelectedValue().split("-")[0]);
				telaAlterarPedido = new TelaAlterarPedido();
				telaAlterarPedido.setVisible(true);
				dispose();
				}catch(ClassNotFoundException | IOException e1) {
					
				}
			}
		});
		btnEditar.setBounds(273, 226, 114, 25);
		contentPane.add(btnEditar);
	}
	public static Pedido getPedido() {
		return GerenciaMesa.getComanda(TelaMesa.getMesa()).getPedido(numeroPedido);
	}
}
