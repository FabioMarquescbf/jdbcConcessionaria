package br.com.aula.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.aula.model.Veiculo;

public class VeiculoDAO {
	private Connection conn = null;

	public VeiculoDAO() {
		this.iniciarConexao();
	}

	
	public boolean estaDesconectada() {
		try {
			return this.conn.isClosed();
		} catch (SQLException e) {
			System.out.println("SQLException Erro: "+e.getMessage());
			throw new RuntimeException(e);
		}
	}
	
	public void checarConexao() {
		if (this.estaDesconectada()) {
			this.iniciarConexao();
		}
	}
	
	public void iniciarConexao() {
		try {
			this.conn = ConnectionFactory.getConnection();
			
			if(conn != null) {
				System.out.println("Conexão realizada com sucesso!");
			} else {
				System.out.println("Falfa ao criar a conexão!");
			}
			
		} catch(Exception e) {
			System.out.println("SQLException Erro: "+e.getMessage());
			throw new RuntimeException(e);
		}
	}

	
	
	
	public void salvar(Veiculo veiculo) {
		String sql = "insert into veiculo (modelo, marca, placa, valor) value (?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		
		try {
			this.checarConexao();
			conn = this.conn;
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, veiculo.getModelo());
			stm.setString(2, veiculo.getMarca());
			stm.setString(3, veiculo.getPlaca());
			stm.setDouble(4, veiculo.getValor());
			
			stm.execute();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Veiculo> listar() {
		String sql = "select * from veiculo";
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
	
		int id;
		String modelo, marca, placa;
		double valor;
		
		
		try {
			this.checarConexao();
			conn = this.conn;
			
			stm = conn.prepareStatement(sql);
			rs = stm.executeQuery();
			
			List<Veiculo> veiculos = new ArrayList<Veiculo>();
			
			while(rs.next()) {
				id = rs.getInt("id");
				modelo = rs.getString("modelo");
				marca = rs.getString("marca");
				placa = rs.getString("placa");
				valor = rs.getDouble("valor");
				
				veiculos.add(new Veiculo(id, modelo, marca, placa, valor));
			}
			
			System.out.println("Lista de veiculos criada com sucesso");
			
			return veiculos;
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(conn, stm, rs);
		}
	}
	
	
	
	public void editar(Veiculo veiculo) {
		
		String sql = "update veiculo set modelo=?, marca=?, placa=?, valor=? where id= ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			this.checarConexao();
			conn = this.conn;

			stm = conn.prepareStatement(sql);			
			
			stm.setString(1, veiculo.getModelo() );
			stm.setString(2, veiculo.getMarca() );
			stm.setString(3, veiculo.getPlaca() );
			stm.setDouble(4, veiculo.getValor() );
			
			stm.setInt(5, veiculo.getId() );
			
			stm.executeUpdate();
			
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(conn, stm);
		}			
	}
	
	
	public void excluir(Veiculo veiculo) {
		String sql = "delete from veiculo where id= ?";
		
		Connection conn = null;
		PreparedStatement stm = null;
		
		try {
			this.checarConexao();
			conn = this.conn;
			
			stm = conn.prepareStatement(sql);
			stm.setInt(1, veiculo.getId() );
			
			stm.executeUpdate();
			
			System.out.println( veiculo.toString() );
			System.out.println("Veiculo excluido com sucesso");	
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		}  finally {
			ConnectionFactory.closeConnection(conn, stm);
		}			
	}
	
	
	public Veiculo pesquisar(Veiculo veiculo) {
		String sql = "select * from veiculo where placa = ?";
		
		int id;
		String modelo, marca, placa;
		double valor;
		
		
		Connection conn = null;
		PreparedStatement stm = null;
		ResultSet rs = null;
		
		try {
			this.checarConexao();
			conn = this.conn;
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, veiculo.getPlaca() );
			
			rs = stm.executeQuery();
			
			if( ! rs.next() )
				return null;
			
			id = rs.getInt("id");
			modelo = rs.getString("modelo");
			marca = rs.getString("marca");
			placa = rs.getString("placa");
			valor = rs.getDouble("valor");
			
			return new Veiculo(id, modelo, marca, placa, valor);
			
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionFactory.closeConnection(conn, stm);
		}
		
	}
	

	
}
