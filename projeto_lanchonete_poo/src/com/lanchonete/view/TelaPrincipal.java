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
		btnCardpio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				telaCardapio = new TelaCardapio();
				telaCardapio.setVisible(true);
				dispose();
				
			}
		});
		btnCardpio.setBounds(12, 125, 130, 25);
		contentPane.add(btnCardpio);
		
		JButton btnMinhaConta = new JButton("Minha Conta");
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
		contentPane.add(btnMinhaConta);
		
		JButton btnMesa = new JButton("Mesas");
		btnMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaMesa = new TelaMesa();
				telaMesa.setVisible(true);
				dispose();
			}
		});
		btnMesa.setBounds(185, 125, 130, 25);
		contentPane.add(btnMesa);
		
		JButton btnCozinha = new JButton("Cozinha");
		btnCozinha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaCozinha = new TelaCozinha();
				telaCozinha.setVisible(true);
				dispose();
			}
		});
		btnCozinha.setBounds(12, 196, 130, 25);
		contentPane.add(btnCozinha);
		
		JButton btnGerncia = new JButton("Gerência");
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
		contentPane.add(btnGerncia);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicial = new TelaInicial();
				inicial.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(358, 196, 130, 25);
		contentPane.add(btnSair);
		
		JLabel lblSaborComPaixo = new JLabel("SABOR COM PAIXÃO");
		lblSaborComPaixo.setForeground(new Color(153, 0, 51));
		lblSaborComPaixo.setFont(new Font("Manjari Bold", Font.BOLD | Font.ITALIC, 16));
		lblSaborComPaixo.setBounds(306, 42, 204, 32);
		contentPane.add(lblSaborComPaixo);
		
		JLabel lblIgualA = new JLabel("É IGUAL A");
		lblIgualA.setFont(new Font("Manjari Bold", Font.BOLD | Font.ITALIC, 16));
		lblIgualA.setForeground(new Color(153, 0, 51));
		lblIgualA.setBounds(391, 64, 109, 25);
		contentPane.add(lblIgualA);
		
		JLabel lblSandubo = new JLabel("SANDUBÃO");
		lblSandubo.setFont(new Font("Manjari Bold", Font.BOLD | Font.ITALIC, 18));
		lblSandubo.setForeground(new Color(153, 0, 51));
		lblSandubo.setBounds(374, 86, 126, 25);
		contentPane.add(lblSandubo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img/principal.jpg"));
		label.setBounds(6, -12, 482, 265);
		contentPane.add(label);
	}
}
