package logica;

public class Celda {
	private Integer valor;
	private EntidadGrafica entidadGrafica;
	private boolean puedoCambiar;
	int x,y;	

	public Celda() {
		this.valor = null;
		this.entidadGrafica = new EntidadGrafica();
		this.puedoCambiar = true;
		this.x = 0;
		this.y = 0;

	}
	
	public void actualizar(int val) {
		
		this.valor = val;

		entidadGrafica.actualizar(val);
	}
	
	public int getCantElementos() {
		return this.entidadGrafica.getImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	//para evitar cambiar las celdas de los número preestablecidos en el archivo
	public boolean puedoCambiar() {
		return this.puedoCambiar;
	}
	
	public void setPuedoCambiar(boolean p) {
		this.puedoCambiar = p;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	
}