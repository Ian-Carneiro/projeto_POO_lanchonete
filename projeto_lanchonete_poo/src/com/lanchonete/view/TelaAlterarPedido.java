package com.lanchonete.view;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.control.GerenciaMenu;
import com.lanchonete.control.GerenciaMesa;
import com.lanchonete.model.Pedido;
import com.lanchonete.model.Produto;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.SpinnerNumberModel;

public class TelaAlterarPedido extends JFrame {

	private JPanel contentPane;
	private TelaMesa telaMesa;
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public TelaAlterarPedido() throws FileNotFoundException, ClassNotFoundException, IOException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 370);
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
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(GerenciaMesa.modificarPedido(TelaMesa.getMesa(),
							new Pedido((Integer)spinnerQuant.getValue(),
									GerenciaMenu.EscolherProduto(Integer.parseInt(listProdutos.getSelectedValue().split("-")[0]))), 
							TelaVerPedidos.getPedido().getNumeroPedido())) {
						JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!");
					}else {
						JOptionPane.showMessageDialog(null, "Não houve modificações!");
					}
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Falha na modificação do pedido", "Falha", JOptionPane.ERROR_MESSAGE);
				}
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnModificar.setBounds(167, 295, 114, 25);
		contentPane.add(btnModificar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(GerenciaMesa.excluirPedido(TelaMesa.getMesa(), TelaVerPedidos.getPedido().getNumeroPedido())) {
						JOptionPane.showMessageDialog(null, "Pedido excluido com sucesso!");
					}else {
						JOptionPane.showMessageDialog(null, "Não houve alterações!");
					}
				} catch (HeadlessException | ClassNotFoundException | IOException e1) {
					JOptionPane.showMessageDialog(null, "Falha ao excluir pedido", "Falha", JOptionPane.ERROR_MESSAGE);
				}
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnExcluir.setBounds(324, 295, 114, 25);
		contentPane.add(btnExcluir);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.setBounds(12, 295, 114, 25);
		contentPane.add(btnNewButton);
	}

}
