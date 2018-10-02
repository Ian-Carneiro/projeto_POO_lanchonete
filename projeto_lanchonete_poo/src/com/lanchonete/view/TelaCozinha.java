package com.lanchonete.view;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.model.Cozinha;
import com.lanchonete.model.Pedido;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;

public class TelaCozinha extends JFrame {

	private JPanel contentPane;
	private TelaPrincipal telaPrincipal;
	/**
	 * Create the frame.
	 */
	public TelaCozinha() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		String[] pedidos = new String[Cozinha.listar().size()];
		int k=0;
		for(Pedido p:Cozinha.listar()) {
			pedidos[k++] = p.getNumeroPedido()+"- mesa:"+p.getMesa()+" "+p.getProduto().getNome();
		}
		
		JComboBox comboBox = new JComboBox(pedidos);
		comboBox.setBounds(58, 48, 340, 24);
		contentPane.add(comboBox);
		
		JButton btnAtender = new JButton("Atender");
		btnAtender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(comboBox.getSelectedItem()!=null && Cozinha.atender(Integer.parseInt(((String)comboBox.getSelectedItem()).split("-")[0]))){
						JOptionPane.showMessageDialog(null, "Pedido atendido com sucesso!");
						dispose();
						new TelaCozinha().setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Não houve alteração!");
					}
				} catch (NumberFormatException | HeadlessException | ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAtender.setBounds(284, 174, 114, 25);
		contentPane.add(btnAtender);
		
		JButton btnNewButton = new JButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(58, 174, 114, 25);
		contentPane.add(btnNewButton);
	}
}
