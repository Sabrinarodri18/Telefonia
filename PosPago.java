import java.util.GregorianCalendar;

public class PosPago extends Assinante {

	private float assinatura;

	public PosPago() {
	}

	// Construtor da classe
	public PosPago(long cpf, String nome, int numero, float assinatura) {
		super(cpf, nome, numero);
		this.assinatura = assinatura;
	}

	// Faz uma condição de pré-existencia da chamada, instancia e aloca os dados no
	// vetor chamada
	public void fazerChamada(GregorianCalendar data, int duracao) {

		if (numChamadas > 0) {
			this.chamadas[this.numChamadas - 1] = new Chamada(data, duracao);
			this.numChamadas = this.numChamadas - 1;
			System.out.println("Chamada realizada com sucesso!!!\n");
		} else {
			System.out.println("Não pode realizar a chamada...\n");
		}
	}

	// Recebe um mes para exibir fatura
	// Percorre o vetore chamadas e compara o mes recebido como parametro
	// com o mes referente a data da chamada
	// No final, atualiza o valor das chamadas e soma a assinatura com o total de
	// chamadas
	void imprimirFatura(int mes) {
		float c = 0;
		for (int i = 0; i < chamadas.length; i++) {
			if (this.chamadas[i] != null) {
				if (this.chamadas[i].getData().get(GregorianCalendar.MONTH) == mes) {
					System.out.println(this.chamadas[i]);
					c = c + this.chamadas[i].getDuracao() * 1.04f;
				}
			}
		}
		System.out.printf("Valor gasto nas chamadas: %.2f %n", c);
		System.out.printf("Valor total assinatura + chamadas: %.2f %n", c + this.assinatura);
	}
}

