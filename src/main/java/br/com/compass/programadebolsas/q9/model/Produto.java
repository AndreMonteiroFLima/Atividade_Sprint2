package br.com.compass.programadebolsas.q9.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;

public class Produto {
	
	private int id;
	private String nome;
	private String descricao;
	private double desconto;
	private BigDecimal valor;
	private LocalDate dataInicio ;
	
	
	public Produto() {
	}


	
	public Produto(int id, String nome, String descricao, double desconto, BigDecimal valor, LocalDate dataInicio) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.desconto = desconto;
		this.valor = valor;
		this.dataInicio = dataInicio;
	}


	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getDesconto() {
		return desconto;
	}
	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public void setDesconto(float desconto) {
		
		this.desconto = (float) (desconto/100);
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public void descontoRandomizado() {
		Random r = new Random();
		this.desconto = (float) Math.round(r.nextFloat()*100)/100;
	}
	
	@Override
	public String toString() {
		String formattedDate = getDataInicio().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		return String.format("id: %d - Nome:%s - Descricao: %s - Desconto: %.0f%% - Valor: %.2f - Data: %s",this.id,this.nome,this.descricao,this.desconto*100,this.valor,formattedDate);
	}
	
	
}
