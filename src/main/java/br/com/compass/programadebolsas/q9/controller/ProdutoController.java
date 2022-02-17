package br.com.compass.programadebolsas.q9.controller;

import java.sql.Connection;
import java.util.List;

import br.com.compass.programadebolsas.q9.dao.ProdutoDAO;
import br.com.compass.programadebolsas.q9.exceptions.IdException;
import br.com.compass.programadebolsas.q9.factory.ConnectionFactory;
import br.com.compass.programadebolsas.q9.model.Produto;

public class ProdutoController {
	
	private ProdutoDAO produtoDao;

	public ProdutoController() {
		Connection con = new ConnectionFactory().recuperaConexao();
		this.produtoDao = new ProdutoDAO(con);
	}
	
	public void insere(Produto produto) {
		System.out.println("Inserindo Produto: "+System.lineSeparator()+produto);
		produtoDao.inserir(produto);
	}
	
	
	public void atualiza(Produto produto){
		produtoDao.atualizar(produto);
	}
	
	public List<Produto> lista(){
		return produtoDao.Listar();
	}
	
	public boolean validaId(int id) throws IdException{
		return produtoDao.validaId(id);
	}
	
	public Produto buscaPorId(int id) {
		return produtoDao.buscaPorId(id);
	}
	
	public void deletar(int id) {
		produtoDao.deletar(id);
	}
	
	public List<Produto> contemPalavra(String string){
		return produtoDao.contemPalavra(string);
	}
}
