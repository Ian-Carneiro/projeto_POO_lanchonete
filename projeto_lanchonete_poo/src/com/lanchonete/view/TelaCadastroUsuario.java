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
import com.lanchonete.exception.MenorIdadeException;
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

public class TelaCadastroUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField tfNome;
	private JFormattedTextField ftfCpf;
	private JTextField tfEmail;
	private JPasswordField passwordField;
	private Usuario usuario;
	private TelaInicial inicial;
	private JComboBox cbSetor;
	private JComboBox cbEmail;
	
	/**
	 * Create the frame.
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public TelaCadastroUsuario() throws ParseException {
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
		
		JLabel lblNascimento = new JLabel("Nascimento*");
		lblNascimento.setBounds(11, 157, 94, 15);
		contentPane.add(lblNascimento);
		
		JLabel lblSetor = new JLabel("Setor");
		lblSetor.setBounds(11, 186, 84, 15);
		contentPane.add(lblSetor);
		
		JLabel lblEmail = new JLabel("E-mail *");
		lblEmail.setBounds(11, 72, 84, 15);
		contentPane.add(lblEmail);
		
		ftfCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		ftfCpf.setBounds(111, 11, 328, 22);
		contentPane.add(ftfCpf);
		
		cbSetor = new JComboBox(new String[]{"ATENDIMENTO", "COZINHA", "CAIXA", "GERENCIA"});
		cbSetor.setBounds(101, 181, 338, 24);
		contentPane.add(cbSetor);
		
		cbEmail = new JComboBox(new String[]{"@gmail.com", "@outlook.com", "@hotmail.com"});
		cbEmail.setBounds(261, 67, 178, 24);
		contentPane.add(cbEmail);
		
		tfNome = new JTextField();
		tfNome.setBounds(111, 39, 328, 22);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		JFormattedTextField ftfTelefone = new JFormattedTextField(new MaskFormatter("(##)#####-####"));
		ftfTelefone.setBounds(111, 125, 328, 22);
		contentPane.add(ftfTelefone);
		
		JFormattedTextField ftfData = new JFormattedTextField(new MaskFormatter("##/##/####"));
		ftfData.setBounds(111, 153, 328, 22);
		contentPane.add(ftfData);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(111, 67, 138, 24);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha *");
		lblSenha.setBounds(11, 101, 84, 15);
		contentPane.add(lblSenha);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(111, 97, 328, 22);
		contentPane.add(passwordField);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate ld = LocalDate.parse(ftfData.getText(), formatter);
					usuario = new Usuario(ftfCpf.getText(), tfNome.getText(), tfEmail.getText()+cbEmail.getSelectedItem(),
							new String(passwordField.getPassword()), ftfTelefone.getText(),
							ld, (String)cbSetor.getSelectedItem());
					addUsuarioGerenciaUsuario(usuario);
					inicial = new TelaInicial();
					inicial.setVisible(true);
					dispose();
					
				} catch (HeadlessException | ClassNotFoundException  e) {
					JOptionPane.showMessageDialog(null, "Falha na operação de Novo Cadastro", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(DataNascimentoException ex) {
					JOptionPane.showMessageDialog(null, "Data de nascimento após a data atual", "Data de nascimento inválida", JOptionPane.ERROR_MESSAGE);
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(DateTimeParseException e) {
					JOptionPane.showMessageDialog(null, "Data fornecida é inválida", "Data inválida", JOptionPane.ERROR_MESSAGE);
				} catch (CampoVazioException e) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Campos vazios", JOptionPane.ERROR_MESSAGE);
				} catch (MenorIdadeException e) {
					JOptionPane.showMessageDialog(null, "Usuário com idade inadequada", "Idade inadequada", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(111, 226, 114, 25);
		contentPane.add(btnSalvar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicial = new TelaInicial();
				inicial.setVisible(true);
				dispose();
			}
		});
		btnVoltar.setBounds(250, 226, 114, 25);
		contentPane.add(btnVoltar);
	}
	public void addUsuarioGerenciaUsuario(Usuario u) throws HeadlessException, FileNotFoundException, ClassNotFoundException, IOException, DataNascimentoException, CampoVazioException, MenorIdadeException {
		if(GerenciaUsuario.adicionarLogin(u)) {
			JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
		}else {
			JOptionPane.showMessageDialog(null, "Já existe um usuário com este Email!");
		}
	}
}
