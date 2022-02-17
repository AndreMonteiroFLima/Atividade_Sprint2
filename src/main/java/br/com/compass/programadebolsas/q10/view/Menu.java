package br.com.compass.programadebolsas.q10.view;

import java.util.List;
import java.util.Scanner;

import br.com.compass.programadebolsas.q10.dao.FuncionarioDAO;
import br.com.compass.programadebolsas.q10.model.Funcionario;

public class Menu {

	public static void mostaMenu(){
		Scanner scanner = new Scanner(System.in);
		Funcionario func = new Funcionario();
		FuncionarioDAO funcDAO = new FuncionarioDAO();
		
		System.out.println("=============Novo Funcionarios=============");

		System.out.println("Digite seu nome:");
		try {
			func.setNome(scanner.nextLine());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			scanner.close();
			return;
		}
		System.out.println("Digite seu texto:");
		func.setHumor(scanner.nextLine());

		scanner.close();

		funcDAO.cadastrar(func);
		funcDAO.trasactionBegin();
		funcDAO.commit();

		mostraFuncionarios(funcDAO);
		funcDAO.close();

	}

	private static void mostraFuncionarios(FuncionarioDAO funcDAO) {
		List<Funcionario> funcs = funcDAO.listaTodosFuncionarios();
		funcs.stream().forEach(f -> {
			System.out.println("=============Funcionarios=============");
			System.out.println(f);
		});
	}
}
