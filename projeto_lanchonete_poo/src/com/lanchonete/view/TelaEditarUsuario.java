package com.lanchonete.view;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.lanchonete.control.GerenciaUsuario;
import com.lanchonete.exception.CampoVazioException;
import com.lanchonete.exception.DataNascimentoException;
import com.lanchonete.model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class TelaEditarUsuario extends JFrame {
	private JPanel contentPane;
	private JTextField tfNome;
	private JFormattedTextField ftfCpf;
	private JTextField tfEmail;
	private JPasswordField passwordField;
	private TelaInicial inicial;
	private TelaPrincipal telaPrincipal;
	private JComboBox cbSetor;
	private JComboBox cbEmail;
	

	public TelaEditarUsuario() throws ParseException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblCpf = new JLabel("CPF *");
		lblCpf.setBounds(11, 15, 84, 15);
		contentPane.add(lblCpf);
		
		JLabel lblNome = new JLabel("Nome *");
		lblNome.setBounds(11, 43, 84, 15);
		contentPane.add(lblNome);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(11, 129, 58, 15);
		contentPane.add(lblTelefone);
		
		JLabel lblNascimento = new JLabel("Nascimento *");
		lblNascimento.setBounds(11, 157, 103, 15);
		contentPane.add(lblNascimento);
		
		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setBounds(11, 186, 84, 15);
		contentPane.add(lblSetor);
		
		JLabel lblEmail = new JLabel("E-mail *");
		lblEmail.setBounds(11, 72, 84, 15);
		contentPane.add(lblEmail);
		
		JLabel lblSenha = new JLabel("Senha *");
		lblSenha.setBounds(11, 101, 84, 15);
		contentPane.add(lblSenha);
		
		
		ftfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		ftfCpf.setText(TelaInicial.getAltenticado().getCpf());
		ftfCpf.setBounds(122, 11, 317, 22);
		contentPane.add(ftfCpf);
		
		String[] s1 = {"ATENDIMENTO", "COZINHA", "CAIXA", "GERENCIA"};
		cbSetor = new JComboBox(s1);
		int i;
		for(i = 0; i<s1.length; i++) {
			if(s1[i].equals(TelaInicial.getAltenticado().getSetor())) {
				break;
			}
		}
		cbSetor.setSelectedIndex(i);
		cbSetor.setBounds(101, 181, 338, 24);
		contentPane.add(cbSetor);
		
		tfNome = new JTextField();
		tfNome.setText(TelaInicial.getAltenticado().getNome());
		tfNome.setBounds(121, 39, 317, 22);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JFormattedTextField ftfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		ftfTelefone.setText(TelaInicial.getAltenticado().getTelefone());
		ftfTelefone.setBounds(121, 125, 318, 22);
		contentPane.add(ftfTelefone);
		
		JFormattedTextField ftfData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		String[] s = TelaInicial.getAltenticado().getNascimento().toString().split("-");
		ftfData.setText(s[2]+s[1]+s[0]);
		ftfData.setBounds(121, 153, 318, 22);
		contentPane.add(ftfData);
		
		String email = TelaInicial.getAltenticado().getEmail();
		String[] partesEmail = email.split("@");
		
		tfEmail = new JTextField();
		tfEmail.setText(partesEmail[0]);
		tfEmail.setBounds(121, 67, 124, 24);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		cbEmail = new JComboBox(new String[]{"@gmail.com", "@outlook.com", "@hotmail.com"});
		String[] s2 = {"gmail.com", "outlook.com", "hotmail.com"};
		for(i = 0; i<s2.length; i++) {
			if(s2[i].equals(partesEmail[1])) {
				break;
			}
		}
		System.out.println(i);
		cbEmail.setSelectedIndex(i);
		cbEmail.setBounds(261, 67, 178, 24);
		contentPane.add(cbEmail);
		
		passwordField = new JPasswordField();
		passwordField.setText(TelaInicial.getAltenticado().getSenha());
		passwordField.setBounds(121, 97, 318, 22);
		contentPane.add(passwordField);
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ld = LocalDate.parse(ftfData.getText(), formatter);
					if(ld.isAfter(LocalDate.now())) {
						String[] s = TelaInicial.getAltenticado().getNascimento().toString().split("-");
						ftfData.setText(s[2]+s[1]+s[0]);
						throw new DataNascimentoException();
					}
					Usuario u = new Usuario(ftfCpf.getText(), tfNome.getText(),
							tfEmail.getText()+cbEmail.getSelectedItem(), new String(passwordField.getPassword()),
							ftfTelefone.getText(), ld, (String)cbSetor.getSelectedItem());
					editarUsuarioGerenciaUsuario(TelaInicial.getAltenticado().getEmail(), u);
					TelaInicial.setAltenticado(u);
					telaPrincipal = new TelaPrincipal();
					telaPrincipal.setVisible(true);
					setVisible(false);
				}catch(DateTimeParseException ex){
					JOptionPane.showMessageDialog(null, "Formato de data inválido!", "Campo inválido", JOptionPane.ERROR_MESSAGE);
				}catch (HeadlessException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao editar usuário", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				} catch (DataNascimentoException e1) {
					JOptionPane.showMessageDialog(null, "Data de nascimento após a data atual", "Data de nascimento inválida", JOptionPane.ERROR_MESSAGE);
				} catch (CampoVazioException e1) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Campos vazios", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(55, 213, 114, 25);
		contentPane.add(btnSalvar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Are you sure?", "WARNING",
				        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					removerUsuarioGerenciaUsuario(TelaInicial.getAltenticado().getEmail());
					inicial = new TelaInicial();
					inicial.setVisible(true);
					dispose();
				}
			}
		});
		btnExcluir.setBounds(181, 213, 114, 25);
		contentPane.add(btnExcluir);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPrincipal telaPrinc = new TelaPrincipal();
				telaPrinc.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(307, 213, 114, 25);
		contentPane.add(btnVoltar);
	}
	//deve apenas lançar, se tratar as exceções aqui é gerado erro "lógico"
	public void editarUsuarioGerenciaUsuario(String email, Usuario usuario) throws HeadlessException, FileNotFoundException, ClassNotFoundException, IOException, DataNascimentoException, CampoVazioException {
		if(GerenciaUsuario.editarUsuario(email, usuario)) {
			JOptionPane.showMessageDialog(null, "Dados do usuário atualizados!");
		}else {
			JOptionPane.showMessageDialog(null, "Operação não realizada!");
		}
	}
	public void removerUsuarioGerenciaUsuario(String email) {
		try {
			if(GerenciaUsuario.removerLogin(email)) {
				JOptionPane.showMessageDialog(null, "Dados do usuário removidos!");
			}else {
				JOptionPane.showMessageDialog(null, "Não houve alteração!");
			}
		} catch (HeadlessException | ClassNotFoundException | IOException e) {
			JOptionPane.showMessageDialog(null, "Falha ao remover usuário", "Falha", JOptionPane.ERROR_MESSAGE);
		}
	}
}