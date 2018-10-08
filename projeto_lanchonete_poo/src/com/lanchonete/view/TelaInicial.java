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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class TelaInicial extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario;
	private JPasswordField passwordField;
	private static Usuario usuario;
	private TelaCadastroUsuario cadastroUsuario;
	private TelaPrincipal telaPrincipal;
	private JButton btnAutenticar;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		                if ("Nimbus".equals(info.getName())) {
		                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		                    break;
		                }
		            }
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
		setBounds(100, 100, 450, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		
		JButton btnCriar = new JButton("Criar nova conta");
		btnCriar.setFont(new Font("Chilanka", Font.BOLD, 14));
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
		btnCriar.setBounds(154, 310, 162, 25);
		contentPane.add(btnCriar);
		
		btnAutenticar = new JButton("Autenticar");
		btnAutenticar.setFont(new Font("Chilanka", Font.BOLD, 14));
		btnAutenticar.addActionListener(new ActionListener() {
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
				} catch (HeadlessException | ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch(IOException e1){
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}			
		});

		btnAutenticar.setBounds(175, 279, 114, 25);
		contentPane.add(btnAutenticar);
		getRootPane().setDefaultButton(btnAutenticar);
		
		tfUsuario = new JTextField();
		tfUsuario.setBounds(139, 190, 251, 30);
		contentPane.add(tfUsuario);
		tfUsuario.setColumns(10);
		
		JLabel lblUsurio = new JLabel("Usuário");
		lblUsurio.setBackground(Color.BLACK);
		lblUsurio.setFont(new Font("Chilanka", Font.BOLD, 18));
		lblUsurio.setBounds(58, 195, 80, 20);
		contentPane.add(lblUsurio);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Chilanka", Font.BOLD, 18));
		lblSenha.setBounds(58, 227, 66, 28);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 227, 251, 30);
		contentPane.add(passwordField);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("img/logo.png"));
		label.setBounds(100, -59, 424, 313);
		contentPane.add(label);
		
		JLabel lblLanchonete = new JLabel("Lanchonete");
		lblLanchonete.setBounds(271, 65, 119, 31);
		lblLanchonete.setFont(new Font("Chilanka", Font.BOLD, 18));
		contentPane.add(lblLanchonete);
		
		JLabel lblOrientadaAoCaf = new JLabel("Orientada ao Café");
		lblOrientadaAoCaf.setBounds(258, 119, 180, 25);
		lblOrientadaAoCaf.setFont(new Font("Chilanka", Font.BOLD, 18));
		contentPane.add(lblOrientadaAoCaf);
	}
	public static Usuario getAltenticado() {
		return usuario;
	} 
	public static void setAltenticado(Usuario u) {
		usuario = u;
	}
}
