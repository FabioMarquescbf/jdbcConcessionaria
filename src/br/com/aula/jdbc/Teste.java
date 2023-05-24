package br.com.aula.jdbc; 

import java.util.List;

import javax.swing.JOptionPane;

import br.com.aula.dao.VeiculoDAO;
import br.com.aula.model.Veiculo;

public class Teste {

	public static void main(String[] args) {
		VeiculoDAO veiculoDAO = new VeiculoDAO();
		Veiculo carro;
		

		int id = 0;
		String modelo = "", marca = "", placa = "";
		double valor = 0.0;
		
		int opcao = 0;
		String resposta;
		
		String menu = "Gestão de veículos - Escolha umas das seguintes opççoes:";
		menu += "\n 1- Cadatrar veículo.";
		menu += "\n 2- Editar veículo.";
		menu += "\n 3- Deletar veículo";
		menu += "\n 4- Pesquisar veículo por placa.";
		menu += "\n 5- Listar veículo";
		menu += "\n 0- Sair";
		
		do {
			resposta = JOptionPane.showInputDialog(menu);
			opcao = Integer.parseInt(resposta);
			
			switch(opcao) {
				case 1: {
					modelo = JOptionPane.showInputDialog("Informe o modelo");
					marca = JOptionPane.showInputDialog("Informe a marca");
					placa = JOptionPane.showInputDialog("Informe a placa");
					valor = Double.parseDouble ( JOptionPane.showInputDialog("Informe o  valor") );
					
					carro = new Veiculo(id, modelo, marca, placa, valor);
					
					veiculoDAO.salvar(carro);
					System.out.println("Veiculo Cadastrado Com sucesso");
					break;	
				}
				case 2: {
					id = Integer.parseInt( JOptionPane.showInputDialog("Informe o ID") );
					modelo = JOptionPane.showInputDialog("Informe o modelo");
					marca = JOptionPane.showInputDialog("Informe a marca");
					placa = JOptionPane.showInputDialog("Informe a placa");
					valor = Double.parseDouble ( JOptionPane.showInputDialog("Informe o  valor") );
					
					carro = new Veiculo(id, modelo, marca, placa, valor);
					
					veiculoDAO.editar(carro);
					break;			
				}
				case 3: {
					id = Integer.parseInt( JOptionPane.showInputDialog("Informe o ID") );
					
					carro = new Veiculo(id, modelo, marca, placa, valor);
					
					veiculoDAO.excluir(carro);
					break;
				}
				case 4: {
					placa = JOptionPane.showInputDialog("Informe a placa");
					
					carro = new Veiculo(id, modelo, marca, placa, valor);
					
					Veiculo carro2 = veiculoDAO.pesquisar(carro);
					
					System.out.println(carro2.toString() );
					veiculoDAO.pesquisar(carro);
					break;
				}
				case 5: {
					List<Veiculo> veiculosLista = veiculoDAO.listar();
					for (Veiculo obj : veiculosLista) {
						System.out.println(obj.toString() );
					}
				}
				default: System.out.println("Saindo...");
			}
			
	
		} while(opcao > 0 && opcao <6);
	
	}
}
