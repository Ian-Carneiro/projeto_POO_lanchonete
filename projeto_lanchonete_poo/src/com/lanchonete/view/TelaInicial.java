package com.lanchonete.view;


import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import com.lanchonete.control.GerenciaUsuario;
import com.lanchonete.model.Usuario;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField passwordField;
	private static Usuario usuario;
	private TelaCadastroUsuario cadastroUsuario;
	private TelaPrincipal telaPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Erro ao iniciar", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	public TelaInicial() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnCriar = new JButton("Criar nova conta");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				try {
					cadastroUsuario = new TelaCadastroUsuario();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				cadastroUsuario.setVisible(true);
			}
		});
		btnCriar.setBounds(141, 202, 162, 25);
		contentPane.add(btnCriar);
		
		JButton btnAltenticar = new JButton("Autenticar");
		btnAltenticar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(GerenciaUsuario.isAutenticado(tfUsuario.getText(), new String(passwordField.getPassword()))) {
						setVisible(false);
						usuario = GerenciaUsuario.buscarUsuario(tfUsuario.getText());
						telaPrincipal = new TelaPrincipal();
						telaPrincipal.setVisible(true);
						
					}else {
						JOptionPane.showMessageDialog(null, "Não Foi possível altenticar");
						passwordField.setText("");
					}
				} catch (HeadlessException e1) {
					System.out.println("HeadlessException");
				} catch (FileNotFoundException e1) {
					System.out.println("FileNotFoundException");
				} catch (ClassNotFoundException e1) {
					System.out.println("ClassNotFoundException");
				} catch (IOException e1) {
					System.out.println("IOException");
				}
			}			
		});
		btnAltenticar.setBounds(162, 171, 114, 25);
		contentPane.add(btnAltenticar);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(129, 87, 251, 25);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBounds(45, 92, 66, 15);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(45, 132, 66, 15);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 127, 251, 25);
		contentPane.add(passwordField);
	}
	public static Usuario getAltenticado() {
		return usuario;
	} 
	public static void setAltenticado(Usuario u) {
		usuario = u;
	}
}
