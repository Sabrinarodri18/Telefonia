import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Telefonia {
	// Nesse trecho, usamos a estrutura try/catch para lidar com os maiores erros

	// Atributos privatados telefonia
	private PrePago[] prePagos;
	private Integer numPrePago;
	private PosPago[] posPagos;
	private Integer numPosPago;

	// Construtor telefonia
	public Telefonia() {

		this.prePagos = new PrePago[350];
		this.posPagos = new PosPago[350];
	}

	// Formatacao da data para atender aos requisitos/Instanciação de scanner (criação de um objeto da classe Scanner)
	static SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	static Scanner sc = new Scanner(System.in);

	// Metodo Cadastrar assinante
	// Pergunta o tipo de assinante, Prepago/Pospago, e coleta seus dados
	// (nome,cpf,numero)
	// Em seguida, instancia os dados recebidos em seu respectivo vetor.
	public void cadastrarAssinante() throws ParseException {
		
		try {
			if (this.numPosPago == null || this.numPrePago == null) {
				System.out.println("Digite a quantidade de assinantes (Pré-Pago/Pós-Pago) que deseja cadastrar: ");
				System.out.print("Pré-Pago: ");
				this.numPrePago = sc.nextInt();
				System.out.print("Pós-Pago: ");
				this.numPosPago = sc.nextInt();

				if (this.numPrePago > this.prePagos.length || this.numPrePago <= 0) {
					System.out.println("Limite de cadastros Pré-Pagos é de 1 a "+ this.prePagos.length +" tente novamente!!!\n ");
					this.numPrePago= null;
				}

				if (this.numPosPago > this.posPagos.length || this.numPosPago <= 0) {
					System.out.println("Limite de cadastros Pós-Pagos é de 1 a "+ this.posPagos.length +" tente novamente!!!\n ");
					this.numPrePago= null;
				}
				cadastrarAssinante();
			} else if (this.numPosPago != null && this.numPrePago != null) {
				int option = 0;
				System.out.println("\nDigite o tipo de assinante:\n1- Prepago\n2- Pospago");
				System.out.print("Digite uma opção: ");
				option = sc.nextInt();
				if (option == 1) {
					if (numPrePago > 0) {
						System.out.print("\nDigite o Nome do Assinante: ");
						sc.nextLine();
						String nm = sc.nextLine();
						System.out.print("Digite o CPF do Assinante: ");
						long c = sc.nextLong();
						System.out.print("Digite o Número de Celular do Assinante: ");
						int num = sc.nextInt();
						this.prePagos[numPrePago - 1] = new PrePago(c, nm, num);
						this.numPrePago = this.numPrePago - 1;
						System.out.println("\nNovo Assinante Pré-Pago cadastrado!!!\n");
					} else {
						System.out.println("Não é possível realizar novo cadastro!!\n");
					}

				}
				if (option == 2) {
					if (this.numPosPago > 0) {
						System.out.print("\nDigite o Nome do Assinante: ");
						sc.nextLine();
						String nm = sc.nextLine();
						System.out.print("Digite o CPF do Assinante: ");
						long c = sc.nextLong();
						System.out.print("Digite o Número de Celular do Assinante: ");
						int num = sc.nextInt();
						System.out.print("Digite o valor da Assinatura: ");
						float vlAssinatura = sc.nextInt();
						this.posPagos[numPosPago - 1] = new PosPago(c, nm, num, vlAssinatura);
						this.numPosPago = this.numPosPago - 1;
						System.out.println("Novo Assinante Pós-Pago cadastrado!!!\n");
						System.out.println();
					} else {
						System.out.println("Não é possível realizar novo cadastro!!\n");
					}
				}
			}
		} catch (java.lang.NullPointerException ex) {
			System.out.println("insira um dado válido e tente novamente!!!\n");
		} catch (java.util.InputMismatchException ex) {
			this.numPosPago = null;
			this.numPrePago= null;
			System.out.println("insira um dado válido e tente novamente!!!\n");
			
		}
	}

	// Método ListarAssinante
	// Percorre os vetores dos assinantes pre-pago/pos-pago
	// printar os dados diferentes de null/nulo.
	public void listarAssinante() {

		System.out.println("\nDados dos Assinantes Pré-Pagos: ");
		int cont = 0;

		for (int i = 0; i < prePagos.length; i++) {
			if (this.prePagos[i] != null) {
				System.out.println(this.prePagos[i]);
			} else {
				cont += 1;
			}
		}
		if (cont == this.prePagos.length) {
			System.out.println("Ainda não existem dados de usuários Pré-Pagos Cadastrados!!!\n");
		}
		System.out.println("\nDados dos Assinantes Pós-Pagos:");
		cont = 0;
		for (int j = 0; j < posPagos.length; j++) {
			if (this.posPagos[j] != null) {
				System.out.println(this.posPagos[j]);
			} else {
				cont += 1;
			}
		}
		if (cont == this.posPagos.length) {
			System.out.println("Ainda não existem dados de usuários Pós-Pagos Cadastrados!!!\n");
			System.out.println();
		}
	}

	// Método LocalizarPrepago
	// Recebe CPF, percorre o vetor prePago
	// Descarta posições nulas no vetor
	// compara CPF recebido é o mesmo CPF dentro do vetor
	// retorna dados do Assinante prepago com o cpf digitado
	private PrePago localizarPrePago(long cpf) {
		for (int i = 0; i < this.prePagos.length; i++) {
			if (prePagos[i] != null) {
				if (this.prePagos[i].getCpf() == cpf) {
					return prePagos[i];
				}
			}
		}
		return null;
	}

	// Método LocalizarPosPago
	// Recebe CPF, percorre o vetor PosPagos
	// Descarta posições nulas no vetor
	// Compara CPF recebido é o mesmo CPF dentro do vetor
	// Retorna dados do Assinante PrePago com o cpf digitado
	private PosPago localizarPosPago(long cpf) {

		for (int i = 0; i < this.posPagos.length; i++) {
			if (posPagos[i] != null) {
				if (this.posPagos[i].getCpf() == cpf) {
					return posPagos[i];
				}
			}
		}
		return null;
	}
	// Metodo fazerChamada
	// Pergunta o cpf e o tipo de assinatura do cliente

	public void fazerChamada() throws ParseException {
		// ParseException serve para não ocorrer erros na formatacao da data***

		System.out.print("Digite o CPF para fazer chamada: ");
		long id = sc.nextLong();
		System.out.print("\nInforme o tipo da assinatura:\n1-Pré-pago\n2-Pós-pago\nDigite uma opção: ");
		int escolha = sc.nextInt();
		// Caso seja PrePago, invoca o método LocalizarPrePago recebendo o CPF de atributo
		// Recebe a duração e data da chamada
		// Formata a data
		// Joga a data formatada para o GregorianCalendar
		// Variável "prep" do tipo PrePago recebe o valor do localizarPrepago (dados do assinante com o cpf digitado)
		// Depois, invoca o método fazerChamada com os dados da variavel "prep" (esse recebe a data e minutos da chamada)
		if (escolha == 1) {
			localizarPrePago(id);
			GregorianCalendar gc = new GregorianCalendar();
			System.out.print("Digite a Duração da Chamada (minutos): ");
			int min = sc.nextInt();
			System.out.print("Insira a Data da Chamada (DD/MM/AAAA): ");
			Date data = formatador.parse(sc.next());
			gc.setTime(data);
			PrePago prep = localizarPrePago(id);
			prep.fazerChamada(gc, min);
			// Caso seja PosPago, continua com o mesmo caminho do PrePago, utilizando os dados do PosPago.
		} else if (escolha == 2) {
			localizarPosPago(id);
			GregorianCalendar gc = new GregorianCalendar();
			System.out.print("Digite a Duração da Chamada (minutos): ");
			int min = sc.nextInt();
			System.out.print("Insira a Data da Chamada (DD/MM/AAAA): ");
			Date data = formatador.parse(sc.next());
			gc.setTime(data);
			PosPago posp = localizarPosPago(id);
			posp.fazerChamada(gc, min);
		}
	}

	// Método fazerRecarga
	// Recebe CPF, localiza o assinante com o CPF correspondente
	// Descarta posições nulas no vetor
	// Instancia um GregorianCalendar
	// Usuário digita o valor e data da recarga
	// Data é formatada e inserida no GregorianCalendar
	// Variável "prep" do tipo PrePago recebe o valor do localizarPrepago (dados do assinante com o CPF digitado)
	// Depois, invoca o método recarregar com os dados da variável "prep"
	public void fazerRecarga() throws ParseException {
		try {
			System.out.print("Digite o CPF para fazer Recarga: ");
			long id = sc.nextLong();
			if (localizarPrePago(id) != null) {
				GregorianCalendar gt = new GregorianCalendar();
				System.out.print("Insira o Valor da Recarga: ");
				float rc = sc.nextFloat();
				System.out.print("Insira a Data da Recarga (DD/MM/AAAA): ");
				Date data = formatador.parse(sc.next());
				gt.setTime(data);
				PrePago prep = localizarPrePago(id);
				prep.recarregar(gt, rc);
			} else {
				System.out.println("Seu CPF não localizado no sistema... Tente novamente!");
			}
		} catch (java.lang.NullPointerException ex) {
			System.out.println("Nenhum Assinante cadastrado... Tente novamente!");
		} catch (java.text.ParseException ex) {
			System.out.println("Insira um dado válido e tente novamente!\n");
			fazerRecarga();
		}
	}

	// Método ImprimirFatura
	// Usuário digita o CPF do Assinante
	// Usuário informa o tipo de assinatura
	// Usuário digita o mês que deseja verificar a fatura
	// Programa exibe dados completos do assinante e valores totais de chamadas, recargas e assinaturas
	public void imprimirFaturas() throws ParseException {
		try {
			System.out.print("Digite um mês para verificação do histórico (1 a 12):  ");
			int m = sc.nextInt();
			int cont = 0;
			for (int i = 0; i < this.prePagos.length; i++) {
				if (this.prePagos[i] != null) {
					System.out.println("\nPRÉ-PAGO: \n" + this.prePagos[i]);
					this.prePagos[i].imprimirFatura(m - 1);
				} else {
					cont += 1;
				}
			}
			if (cont == this.prePagos.length) {
				System.out.println("Não existem dados de usuários Pré-Pagos para o mês selecionado!!!\n");
			}
			cont = 0;
			for (int i = 0; i < this.posPagos.length; i++) {
				if (this.posPagos[i] != null) {
					System.out.println("\nPÓS-PAGO: \n" + this.posPagos[i]);
					this.posPagos[i].imprimirFatura(m - 1);
				} else {
					cont += 1;
				}
			}
			if (cont == this.posPagos.length) {
				System.out.println("Não existem dados de usuários Pós-Pagos para o mês selecionado!!!\n");
			}
		} catch (java.lang.NullPointerException ex) {
			System.out.println("insira um dado válido e tente novamente!!!");
		}
	}

		public static void main(String[] args) throws ParseException {

		Scanner sc = new Scanner(System.in);
		Telefonia tel = new Telefonia();
		int op = 0;
		do {
			try {
				// Menu de interação que invoca os métodos
				System.out.println("\nBem-vindo! O que você deseja?\n");
				System.out.println("1- Cadastrar assinante\n2- Listar assinante\n3- Fazer chamada");
				System.out.println("4- Fazer recarga\n5- Imprimir faturas\n6- Sair\n");
				System.out.print("Digite uma opção: ");
				op = sc.nextInt();
				switch (op) {
				case 1:
					tel.cadastrarAssinante();
					break;
				case 2:
					tel.listarAssinante();
					break;
				case 3:
					tel.fazerChamada();
					break;
				case 4:
					tel.fazerRecarga();
					break;
				case 5:
					tel.imprimirFaturas();
					break;
				case 6:
					System.out.println("Agradecemos por escolher nossos serviços. Estamos aqui para atendê-lo(a)!\n");
					break;
				default:
					System.out.println("Comando não definido no menu! Por favor, confira novamente...\n");
					break;
				}
			} catch (java.util.InputMismatchException ex) {
				System.out.println("Insira um dado válido e tente novamente!!!");
			} catch (java.lang.ArrayIndexOutOfBoundsException el) {
				System.out.println("Insira um dado válido e tente novamente!!!");
			}

		} while (op != 6);
		sc.close();

	}
}