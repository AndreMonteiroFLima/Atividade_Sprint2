package br.com.compass.programadebolsas.q9.testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.compass.programadebolsas.q9.exceptions.NoDataBaseException;
import br.com.compass.programadebolsas.q9.factory.ConnectionFactory;
import br.com.compass.programadebolsas.q9.model.Produto;

public class TesteRetornaPalavras {

	public static void main(String[] args) {
		Connection con;
		try {
			con = new ConnectionFactory().recuperaConexao();

			List<Produto> produtos = new ArrayList<Produto>();
			int i = 0;

			System.out.println("Inicando");

			String teste = "celular preto";

			String[] splited = teste.split(" ");

			while (i < splited.length) {
				try (PreparedStatement pstm = con.prepareStatement(
						"SELECT id,nome,descricao,desconto,dataInsercao FROM produtos_q9 WHERE nome LIKE ?",
						Statement.RETURN_GENERATED_KEYS)) {
					pstm.setString(1, "%" + splited[i] + "%");
					pstm.execute();

					try (ResultSet rs = pstm.getResultSet()) {
						while (rs.next()) {
							produtos.add(new Produto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4),
									rs.getBigDecimal(5), rs.getDate(6).toLocalDate()));
						}
						produtos.stream().forEach(p -> {
							System.out.println(p);
						});
					} catch (Exception e) {
						System.out.println("Erro ResultSet " + e.getMessage());
					}

				} catch (Exception e) {
					System.out.println("Erro PreparedStatement " + e.getMessage());
				}
				i++;
			}
		} catch (NoDataBaseException e1) {
			e1.printStackTrace();
		}

	}

}
