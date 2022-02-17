package br.com.compass.programadebolsas.q9.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.compass.programadebolsas.q9.IO.LeitorDeArquivo;
import br.com.compass.programadebolsas.q9.controller.ProdutoController;
import br.com.compass.programadebolsas.q9.exceptions.IdException;
import br.com.compass.programadebolsas.q9.model.Produto;
import br.com.compass.programadebolsas.q9.util.DataFormatada;

public class Menu {

	private ProdutoController pc = new ProdutoController();
	private Scanner scanner = new Scanner(System.in);

	public Menu() {

	}

	public boolean mostraMenu() {
		System.out.println("========= XPTO System =========");
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - para INSERIR uma nova oferta");
		System.out.println("2 - para ATUALIZAR uma oferta");
		System.out.println("3 - para DELETAR uma nova oferta");
		System.out.println("4 - para listar as palavras que contem ?");
		System.out.println("5 - para LISTAR todos os produtos");
		System.out.println("6 - para INSERIR manualmente um produto");
		System.out.println("0 - para SAIR");
		return escolhas();

	}

	private boolean escolhas() {
		int op = -1;
		boolean continua = true;
		System.out.println("Opção:");

		op = scanner.nextInt();

		switch (op) {
		case (1):
			menuInserir();
			break;
		case (2):
			menuAtualizar();
			break;
		case (3):
			menuDeleta();
			break;
		case (4):
			contemPalavra();
			break;
		case (5):
			mostrarProdutos();
			break;
		case (6):
			menuInserirManual();
			break;
		case (0):
			continua = false;
			break;
		default:
			System.out.println("Digite uma opção valida.");
			break;
		}
		return continua;
	}

	private void menuInserir() {
		LeitorDeArquivo.leArquivoProdutos("produtos.cvs");	
	}

	private void menuInserirManual() {
		Produto produto = new Produto();

		System.out.println("Digite os dados do produto que deseja inserir:");

		System.out.print("Nome:");
		scanner.nextLine();
		produto.setNome(scanner.nextLine());
		System.out.print("Descricao:");
		produto.setDescricao(scanner.nextLine());
		System.out.print("Desconto:");
		produto.setDesconto(scanner.nextFloat());
		System.out.print("Valor:");
		produto.setValor(scanner.nextBigDecimal());
		scanner.nextLine();
		
		System.out.println("Data dia/mês/ano:");
		try {
			LocalDate data = DataFormatada.addDate(scanner.nextLine());
			produto.setDataInicio(data);
		}catch(DateTimeParseException e) {
			System.out.println("Data Incorreta, retornando ao menu");
			return;
		}

		pc.insere(produto);
		System.out.println("Produto Inserido com ID:"+produto.getId());
	}

	private void menuAtualizar() {
		

		int id;	
		
		mostrarProdutos();
		System.out.println("Digite o id que deseja alterar(-1 para voltar):");
		try {
			id=scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Entrada invalida, retornando ao menu");
			scanner.next();
			return;
		}
		if(id==-1)
			return;
		try {
			pc.validaId(id);
			Produto produto=pc.buscaPorId(id);
			System.out.println("Produto Selecionado: ");
			System.out.println(produto);
			System.out.println("========Digite os dados para alteração========");
			
			scanner.nextLine();
			System.out.print("Nome: ");
			try{
				produto.setNome(scanner.nextLine());
			}catch(InputMismatchException e) {
				System.out.println("Entrada Incompativel, por favor digite um id valido");
				return;
			}
			
			System.out.print("Descrição: ");
			produto.setDescricao(scanner.nextLine());
			
			System.out.print("Desconto: ");
			produto.setDesconto(scanner.nextFloat());
			System.out.print("Valor:");
			produto.setValor(scanner.nextBigDecimal());
			scanner.nextLine();
			System.out.println("Data ano-mes-dia:");
			try {
				LocalDate data = DataFormatada.addDate(scanner.nextLine());
				produto.setDataInicio(data);
			}catch(DateTimeParseException e) {
				System.out.println("Data Incorreta, retornando ao menu");
				return;
			}
			pc.atualiza(produto);
			
			System.out.println("Produto Atualizado: "+produto);
		}catch(IdException e) {
			System.out.println(e.getMessage());
			menuInserir();
		}
		
	}

	private void mostrarProdutos() {
		System.out.println("========Produtos Na Tabela========");
		List<Produto> produtos= pc.lista();
		produtos.stream().forEach(p -> {
			System.out.println(p);
		});
		System.out.println("");
		
		produtos.clear();
	}
	
	private void menuDeleta() {
		int id;	
		System.out.println("========Digite o id do Produto que deseja DELETAR========");
		id=scanner.nextInt();
		pc.deletar(id);	
		
	}
	
	private void contemPalavra() {
		String palavra;
		
		System.out.println("Digite a palavra que deseja procurar:");
		scanner.nextLine();
		palavra=scanner.nextLine();
	
		List<Produto> produtos = pc.contemPalavra(palavra);
		if(produtos.size()==0) {
			System.out.println("Nenhuma palavra correspondente");
			return;
		}
			
		produtos.stream().forEach(p -> {
			System.out.println(p);
		});	
		
		produtos.clear();
	}
	
	public void fechaScanner() {
		this.scanner.close();
	}
	
}
