public class Assinante {
	private long cpf;
	private String nome;
	private int numero;
	protected Chamada[] chamadas;
	protected Integer numChamadas;

	public Assinante() {
	}

	//Construtor assinante (cpf, nome e numero)
	public Assinante(long cpf, String nome, int numero){
		this.numChamadas = 25;
		this.chamadas = new Chamada[numChamadas];
		this.nome = nome;
		this.cpf = cpf;
		this.numero = numero;

	}

	//Retorna CPF do assinante para o método localizarPrePago/localizarPosPago
	public long getCpf() {
		return this.cpf;
	}

	
	//Retorno toString padrao. Dados do assinante
	@Override
	public String toString() {
		return "Nome=" + this.nome + ", CPF=" + this.cpf +  ", Número de Celular=" + this.numero + ", Chamadas Disponíveis="
				+ numChamadas;
	}

}