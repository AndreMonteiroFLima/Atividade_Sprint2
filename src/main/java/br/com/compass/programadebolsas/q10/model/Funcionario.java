package br.com.compass.programadebolsas.q10.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String nome;
	String Humor;

	public Funcionario() {
	}

	public Funcionario(String nome, String humor) {
		this.nome = nome;
		Humor = humor;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws Exception {
		if (nome.isEmpty()) 
			throw new Exception("Erro: Nome Vazio");
		this.nome = nome;
	}

	public String getHumor() {
		return Humor;
	}

	public void setHumor(String humor) {
		String carinhaTriste = ":-(";
		String carinhaFeliz = ":-)";

		int qtdCarinhaTriste = StringUtils.countMatches(humor, carinhaTriste);
		int qtdCarinhaFeliz = StringUtils.countMatches(humor, carinhaFeliz);

		if (qtdCarinhaFeliz == qtdCarinhaTriste) {
			this.Humor = "neutro";
		} else if (qtdCarinhaFeliz > qtdCarinhaTriste) {
			this.Humor = "divertido";
		} else {
			this.Humor = "chateado";
		}

	}

	@Override
	public String toString() {
		return String.format("id: %d - Nome: %s - Humor: %s", this.id, this.nome, this.Humor);
	}

}
