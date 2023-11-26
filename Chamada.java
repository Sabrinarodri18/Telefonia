import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Chamada {
	private GregorianCalendar data;
	private Integer duracao;
	//Formatacao da data para atender normas ABNT
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
 
	public Chamada() {

	}

	public Chamada(GregorianCalendar data, Integer duracao){
		super();
		this.data = data;
		this.duracao = duracao;
	}
	
	//retorna a data
	//Metodo Imprimirfaturas
	public GregorianCalendar getData(){
		return data;
	}

	public Integer getDuracao(){
		return duracao;
	}

	//Retorna dados da chamada na formatacao adequada
	@Override
	public String toString() 
	{

		return "Data da Chamada: " + sdf.format(data.getTime()) + " Duração da chamada: " + duracao + " minutos";
	}
}