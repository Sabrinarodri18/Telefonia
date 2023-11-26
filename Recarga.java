import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Recarga {

	private float valor;
	private GregorianCalendar data;
	//Formatação da data para atender normas ABNT
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public Recarga() {
	}
	//constructor recarga
	public Recarga(GregorianCalendar data, float valor) 
	{
		this.data = data;
		this.valor = valor;
	}
	//Retorna data 
	public GregorianCalendar getData() 
	{
		return data;
	}
	//Retorna valor, usado para somar valor total de recargas
	public float getValor() 
	{
		return valor;
	}
	//Retorna dados da recarga na formatação adequada
	@Override
	//Método "@Override" usado para substituir o método "toString()" da classe "Object"
	public String toString() {
		return "Data da Recarga: " + sdf.format(data.getTime()) + " Valor da Recarga: " + valor;
	}
}