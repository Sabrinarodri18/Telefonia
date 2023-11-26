import java.util.GregorianCalendar;

public class PrePago extends Assinante {

	// Atributos Prepago filho do assinante
	private float creditos;
	private Integer numRecargas;
	private Recarga[] recargas;
	// Sobrecarga do construtor (boa prática)
	public PrePago() {
	}
	// Construtor do PrePago
	public PrePago(long cpf, String nome, int numero) {
		super(cpf, nome, numero);
		this.numRecargas = 30;
		this.recargas = new Recarga[numRecargas];
	}
	// Método fazer chamada, recebe data (gregorian calendar) e duracao da chamada.
	// Faz uma condição de pré-existencia da chamada, instancia e aloca os dados no
	// vetor chamada
	// E os créditos são atualizados
	void fazerChamada(GregorianCalendar data, int duracao) {
		if (this.creditos > 0 && this.numChamadas > 0 && duracao < this.creditos) {
//			if(this.chamadas[numChamadas-1].getData().get(GregorianCalendar.MONTH) < data.get(GregorianCalendar.MONTH)){}
			this.chamadas[this.numChamadas - 1] = new Chamada(data, duracao);

			this.numChamadas -= 1;
			this.creditos -= (duracao * 1.45f);
			System.out.println("Chamada realizada com sucesso!!!\n");
		} else if (this.numChamadas < 0) {
			System.out.println("Limite de chamadas atingido!!!\n");
		} else if (duracao > this.creditos) {
			System.out.println("Não foi possível fazer chamada, faça uma recarga!!!\n");
		}else {
			System.out.println("Não foi possível fazer chamada, faça uma recarga!!!\n");
		}
	}
	// Metodo para recarregar prepago.
	// Verifica se pode realizar recarga
	// Instancia a recarga e guarda no vetor recarga
	// Decrementa o numero possivel de proximas recargas
	// Aumenta a quantidade de creditos com base no valor
	public void recarregar(GregorianCalendar data, float valor) {

		if (numRecargas > 0) {
			this.recargas[numRecargas - 1] = new Recarga(data, valor);
			this.numRecargas -= 1;
			this.creditos += valor;
			System.out.println("Recarga realizada com sucesso!!!\n");
		} else {
			System.out.println("Não foi possível fazer recarga...");
		}
	}
	// Recebe um mes para exibir fatura
	// Percorre os vetores recargas e chamadas e compara o mes recebido como
	// parametro
	void imprimirFatura(int mes) {
		float r = 0;
		for (int i = 0; i < recargas.length; i++) {
			if (this.recargas[i] != null) {

				if (this.recargas[i].getData().get(GregorianCalendar.MONTH) == mes) {
					System.out.println(this.recargas[i]);
					r = r + recargas[i].getValor();
				}
			}
		}
		float c = 0;
		for (int i = 0; i < chamadas.length; i++) {
			if (this.chamadas[i] != null) {
				if (this.chamadas[i].getData().get(GregorianCalendar.MONTH) == mes) {
					System.out.println(this.chamadas[i]);
					c = c + this.chamadas[i].getDuracao() * 1.45f;
				}
			}
		}
		System.out.printf("O valor total gasto em chamadas: %.2f %n", c);
		System.out.printf("Valor total de recargas feitas no mês selecionado: %.2f %n", r);

		if (r > 0) {

			System.out.println("Créditos restantes: " + (r - c) + "\n");
		} else {
			System.out.println("Créditos restantes: " + r + "\n");
		}
	}
}