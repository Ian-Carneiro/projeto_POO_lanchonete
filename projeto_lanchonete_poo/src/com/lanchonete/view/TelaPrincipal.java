package com.lanchonete.view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private TelaInicial inicial;
	private TelaEditarUsuario editarUsuario;
	private TelaCardapio telaCardapio;
	private TelaMesa telaMesa;
	private TelaCozinha telaCozinha;
	/**
	 * Create the frame.
	 * @param usuario 
	 */
	public TelaPrincipal() {
		setTitle("Inicial");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnCardpio = new JButton("Cardápio");
		btnCardpio.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnCardpio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaCardapio = new TelaCardapio();
				telaCardapio.setVisible(true);
				dispose();
				
			}
		});
		btnCardpio.setFont(new Font(null, 1, 12));
		btnCardpio.setBounds(12, 125, 130, 25);
		contentPane.add(btnCardpio);
		
		JButton btnMinhaConta = new JButton("Minha Conta");
		btnMinhaConta.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnMinhaConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setEnabled(false);
				try {
					editarUsuario = new TelaEditarUsuario();
				} catch (ParseException e1) {
					System.out.println("Aqui");
				}
				editarUsuario.setVisible(true);
				dispose();
			}
		});
		btnMinhaConta.setBounds(358, 125, 130, 25);
		btnMinhaConta.setFont(new Font(null, 1, 12));
		contentPane.add(btnMinhaConta);
		
		JButton btnMesa = new JButton("Mesas");
		btnMesa.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnMesa.setBounds(185, 125, 130, 25);
		btnMesa.setFont(new Font(null, 1, 12));
		contentPane.add(btnMesa);
		
		JButton btnCozinha = new JButton("Cozinha");
		btnCozinha.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnCozinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaCozinha = new TelaCozinha();
				telaCozinha.setVisible(true);
				dispose();
			}
		});
		btnCozinha.setBounds(12, 196, 130, 25);
		btnCozinha.setFont(new Font(null, 1, 12));
		contentPane.add(btnCozinha);
		
		JButton btnGerncia = new JButton("Gerência");
		btnGerncia.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnGerncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new TelaGerencia().setVisible(true);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnGerncia.setBounds(185, 196, 130, 25);
		btnGerncia.setFont(new Font(null, 1, 12));
		contentPane.add(btnGerncia);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBackground(UIManager.getColor("Button.disabledToolBarBorderBackground"));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial = new TelaInicial();
				inicial.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(358, 196, 130, 25);
		btnSair.setFont(new Font(null, 1, 12));
		contentPane.add(btnSair);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img/principal.jpg"));
		label.setBounds(6, -12, 482, 265);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Image designed by macrovector / Freepik");
		lblNewLabel.setFont(new Font(null, 1, 9));
		lblNewLabel.setBounds(286, 257, 202, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblLanchone = new JLabel("Lanchone");
		lblLanchone.setBounds(286, 12, 66, 15);
		contentPane.add(lblLanchone);
		
		JLabel lblOrientadaAoCaf = new JLabel("Orientada ao Café");
		lblOrientadaAoCaf.setBounds(296, 39, 130, 15);
		contentPane.add(lblOrientadaAoCaf);
	}
}
