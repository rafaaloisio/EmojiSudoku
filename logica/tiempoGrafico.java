package logica;


public class tiempoGrafico{
	
	private String[] imagenes;

	
	public tiempoGrafico() {
		this.imagenes = new String[]{"/img/tiempo/t_0.png","/img/tiempo/t_1.png","/img/tiempo/t_2.png","/img/tiempo/t_3.png","/img/tiempo/t_4.png","/img/tiempo/t_5.png","/img/tiempo/t_6.png","/img/tiempo/t_7.png","/img/tiempo/t_8.png","/img/tiempo/t_9.png"};
	}


	public String[] getImagenes() {
		return this.imagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		this.imagenes = imagenes;
	}


}