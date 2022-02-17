package br.com.compass.programadebolsas.q9.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.compass.programadebolsas.q9.exceptions.IdException;
import br.com.compass.programadebolsas.q9.model.Produto;

public class ProdutoDAO {

	private Connection con;

	public ProdutoDAO(Connection con) {
		this.con = con;
	}

	public void inserir(Produto produto) {

		try {
			String sql = "INSERT INTO PRODUTOS_Q9 (NOME, DESCRICAO, DESCONTO, VALOR, DATAINSERCAO) VALUES (?,?,?,?,?)";

			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				Date sqlDate = Date.valueOf(produto.getDataInicio());

				pstm.setString(1, produto.getNome());
				pstm.setString(2, produto.getDescricao());
				pstm.setDouble(3, produto.getDesconto());
				pstm.setBigDecimal(4, produto.getValor());

				pstm.setDate(5, sqlDate);

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na Inserção");
		}
	}

	public boolean validaId(int id) throws IdException {
		try {
			String sql = "SELECT id FROM PRODUTOS_Q9 WHERE id=?";

			try (PreparedStatement stm = con.prepareStatement(sql);) {
				stm.setInt(1, id);
				stm.execute();

				try (ResultSet rst = stm.getResultSet();) {
					if (rst.next())
						if (rst.getInt(1) == id)
							return true;
				}
				throw new IdException("id Não Encontrado");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Produto buscaPorId(int id) {
		try {
			try {
				validaId(id);

				Produto produto = null;
				String sql = "SELECT ID, NOME, DESCRICAO, DESCONTO, DATAINSERCAO FROM PRODUTOS_Q9 WHERE ID = ?";

				try (PreparedStatement pstm = con.prepareStatement(sql)) {
					pstm.setInt(1, id);
					pstm.execute();

					try (ResultSet rst = pstm.getResultSet()) {
						if (rst.next())
							produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),rst.getBigDecimal(5),
									rst.getDate(6).toLocalDate());
					} catch (SQLException e) {
						System.out.println("Erro SQL:" + e.getMessage());
					}
				}
				return produto;
			} catch (IdException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Produto produto) {
		try {
			String sql = "UPDATE PRODUTOS_Q9 p SET p.ID=?, p.NOME = ?, p.DESCRICAO = ?, p.DESCONTO = ?, p.VALOR =?, p.DATAINSERCAO=? WHERE p.id = ?";

			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				Date sqlDate = Date.valueOf(produto.getDataInicio());

				pstm.setInt(1, produto.getId());
				pstm.setString(2, produto.getNome());
				pstm.setString(3, produto.getDescricao());
				pstm.setDouble(4, produto.getDesconto());
				pstm.setBigDecimal(5, produto.getValor());
				pstm.setDate(6, sqlDate);
				pstm.setInt(7, produto.getId());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						produto.setId(rst.getInt(1));
					}

				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro na Inserção");
		}
	}

	public List<Produto> Listar() {
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			String sql = "SELECT ID, NOME, DESCRICAO, DESCONTO,VALOR, DATAINSERCAO FROM PRODUTOS_Q9 ";

			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();

				transformarResultSetEmProduto(produtos, pstm);
			}
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
		try {
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Produto produto = new Produto(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getDouble(4),rst.getBigDecimal(5),
							rst.getDate(6).toLocalDate());
					produtos.add(produto);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deletar(Integer id) {
		try {
			try (PreparedStatement stm = con.prepareStatement("DELETE FROM PRODUTOS_Q9 WHERE ID = ?")) {
				stm.setInt(1, id);
				stm.execute();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> contemPalavra(String string) {
		List<Produto> produtos = new ArrayList<Produto>();
		int i = 0;
		String[] splited = string.split(" ");

		while (i < splited.length) {
			try (PreparedStatement pstm = con.prepareStatement(
					"SELECT id,nome,descricao,desconto,valor,dataInsercao FROM PRODUTOS_Q9 WHERE nome LIKE ? OR descricao  LIKE ?",
					Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, "%" + splited[i] + "%");
				pstm.setString(2, "%" + splited[i] + "%");
				pstm.execute();

				transformarResultSetEmProduto(produtos, pstm);

			} catch (Exception e) {
				System.out.println("Erro PreparedStatement " + e.getMessage());
			}
			i++;
		}
		return produtos;
	}

}
