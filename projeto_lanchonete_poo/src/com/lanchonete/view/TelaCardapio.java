package com.lanchonete.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lanchonete.control.GerenciaMenu;
import com.lanchonete.exception.ValorNegativoException;
import com.lanchonete.model.Produto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TelaCardapio extends JFrame{

	private JPanel contentPane;
	private JLabel lblNome;
	private JLabel lblDescrio;
	private JTextField tfNome;
	private JTextField tfPreco;
	private JButton btnSalvar;
	private JButton btnExcluir;
	private JButton btnEditar;
	private JTextPane tpDescricao;
	private JSpinner spinnerCodigo;
	private TelaPrincipal telaPrincipal;

	/**
	 * Create the frame.
	 */
	public TelaCardapio() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(telaPrincipal);
		
		JLabel lblCdigo = new JLabel("Código*");
		lblCdigo.setBounds(12, 41, 66, 15);
		contentPane.add(lblCdigo);
		
		lblNome = new JLabel("Nome*");
		lblNome.setBounds(12, 83, 66, 15);
		contentPane.add(lblNome);
		
		lblDescrio = new JLabel("Descrição");
		lblDescrio.setBounds(12, 126, 66, 15);
		contentPane.add(lblDescrio);
		
		tpDescricao = new JTextPane();
		tpDescricao.setBounds(107, 126, 318, 172);
		contentPane.add(tpDescricao);
		
		tfNome = new JTextField();
		tfNome.setBounds(107, 78, 318, 25);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		JLabel lblPreo = new JLabel("Preço*");
		lblPreo.setBounds(12, 328, 66, 15);
		contentPane.add(lblPreo);
		
		tfPreco = new JTextField();
		tfPreco.setBounds(107, 326, 318, 25);
		contentPane.add(tfPreco);
		tfPreco.setColumns(10);
		
		spinnerCodigo = new JSpinner();
		spinnerCodigo.setModel(new SpinnerNumberModel(1, 1, null, 1));
		spinnerCodigo.setBounds(107, 36, 175, 25);
		contentPane.add(spinnerCodigo);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				Produto p = null;
				try {
					p = GerenciaMenu.EscolherProduto((Integer)spinnerCodigo.getValue());
				} catch (ClassNotFoundException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(p!=null) {
					tfNome.setText(p.getNome());
					tpDescricao.setText(p.getDescricao());
					tfPreco.setText(p.getPreco().toString());
				}else {
					JOptionPane.showMessageDialog(null, "Produto não encontrado!");
				}
				
			}
		});
		btnNewButton.setBounds(285, 36, 140, 25);
		contentPane.add(btnNewButton);
		
		btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				try {
					if(tfNome.getText().equals("") || tfPreco.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "É necessário preencher os campos obrigatórios", "Falha", JOptionPane.ERROR_MESSAGE);
						limpaCapos();
					}else if(GerenciaMenu.adicionarProduto(new Produto((Integer)spinnerCodigo.getValue(), tfNome.getText(),
							tpDescricao.getText(), Float.parseFloat(tfPreco.getText())))) {
							JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
							limpaCapos();
					}else {
						JOptionPane.showMessageDialog(null, "Produto não cadastrado!");
					}
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Preencha com um valor númerico", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(ValorNegativoException ex) {
					JOptionPane.showMessageDialog(null, "Valor inválido para preço!", "Falha", JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Falha na operação de Salvar", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSalvar.setBounds(25, 382, 115, 25);
		contentPane.add(btnSalvar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(GerenciaMenu.excluirProduto((Integer)spinnerCodigo.getValue())) {
						JOptionPane.showMessageDialog(null, "Produto excluido com sucesso!");
						limpaCapos();
					}else {
						JOptionPane.showMessageDialog(null, "Não houve alteração!");
					}
				} catch (HeadlessException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Falha na operação de Excluir", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnExcluir.setBounds(168, 382, 115, 25);
		contentPane.add(btnExcluir);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(GerenciaMenu.editarProduto((Integer)spinnerCodigo.getValue(), new Produto((Integer)spinnerCodigo.getValue(), tfNome.getText(),
							tpDescricao.getText(), Float.parseFloat(tfPreco.getText())))){
						JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!");
						limpaCapos();
					}else {
						JOptionPane.showMessageDialog(null, "Não houve alterações!");
					}
				}catch (ClassNotFoundException  ex) {
					JOptionPane.showMessageDialog(null, "Falha na operação de Editar", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(ValorNegativoException ex) {
					JOptionPane.showMessageDialog(null, "Valor negativo! Valor inválido para preço!", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(IOException ex) {
					JOptionPane.showMessageDialog(null, "Falha ao acessar arquivo", "Falha", JOptionPane.ERROR_MESSAGE);
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Valor numerico inválido para conversão", "Falha", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditar.setBounds(310, 382, 115, 25);
		contentPane.add(btnEditar);
		
		JButton btnNewButton_1 = new JButton("Voltar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telaPrincipal = new TelaPrincipal();
				telaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(25, 419, 400, 25);
		contentPane.add(btnNewButton_1);
		
	}
	private void limpaCapos() {
		tfNome.setText("");
		tfPreco.setText("");
		tpDescricao.setText("");
	}
}
