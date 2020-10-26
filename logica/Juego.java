package logica;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import javax.swing.JOptionPane;


public class Juego {
	private Celda [][] tablero;
	private int cantFilas;
	private Celda[] items;
	private Celda ultimaSeleccionada;
	private int cantElementos;
	
	private int [][] archivo;
	
	public Juego() {
		
		this.cantFilas = 9;
		
		tablero = new Celda[this.cantFilas][this.cantFilas];
		
		items = new Celda[this.cantFilas+1];
		
		ultimaSeleccionada = null;
		
		cantElementos = 0;
		
		archivo = new int[cantFilas][cantFilas];
		
		String nombreFichero = getClass().getResource("/archivo/archivo.txt").getPath();
		
		BufferedReader br = null; 
	    
	    try {
	    	
		    //Abrir el fichero indicado en la variable nombreFichero
	    	br = new BufferedReader(new FileReader(nombreFichero)); 
	           
	    	String linea = br.readLine();   
	    	
	    	int i = 0;
	    	
	        while ((linea != null) && i < cantFilas) {   
	        	
	        	for (int j = 0; j < cantFilas; j++) {
	        		
	        		int n = linea.replaceAll("\\s","").charAt(j)-48;
		        	//System.out.println(n);
	        		archivo[i][j] = n;
	        	//	System.out.print(archivo[i][j]);
	        	}
	        	
	        //	System.out.println();
	        	
	          	i++;
	        	linea = br.readLine() ; 
	        		
	        		
	        }
	        
	        for (int a = 0; a < cantFilas; a++) {
	        	for (int b = 0; b < cantFilas; b++) {
	        		
	        		tablero[a][b] = new Celda();
	        		
	        		
	        		Random r = new Random();
	        		
	        		if(r.nextInt(2) == 1 ) {
		        		tablero[a][b].setValor(0);	
	        		}else {
		        		tablero[a][b].setValor(archivo[a][b]);	

	        		}
						
					
					//System.out.print(tablero[a][b].getValor());
					
					
					tablero[a][b].setX(a);
					tablero[a][b].setY(b);

					
					if(tablero[a][b].getValor() != 0) {
						tablero[a][b].setPuedoCambiar(false);
						incrementarCantElementos();
					}
	        		
	        		
	        		
	        	}
	        	//System.out.println();

	        }
	        
	    
	   }catch (FileNotFoundException e) {
	            //Operaciones en caso de no encontrar el fichero
	            System.out.println("Error: Fichero no encontrado");
	            //Mostrar el error producido por la excepción
	            System.out.println(e.getMessage());
	   }
	   catch (Exception e) {
	            //Operaciones en caso de error general
	            System.out.println("Error de lectura del fichero");
	            System.out.println(e.getMessage());
	   }
	   finally {
	            //Operaciones que se harán en cualquier caso. Si hay error o no.
	   try {
	                //Cerrar el fichero si se ha abierto
	                if(br != null)
	                	br.close();
	                }
	   catch (Exception e) {
	                System.out.println("Error al cerrar el fichero");
	                System.out.println(e.getMessage());
	    	}
	   }
	    
	//   System.out.println("HAY :"+getCantElementos());
	    
	    
	   //inicializo items 
	   for (int c = 0; c <getCantFilas()+1; c++) {
			items[c] = new Celda();
			items[c].setValor(c);
	   } 
			
		
	}
	
	public void accionar(Celda c) {
		ultimaSeleccionada = c;
	}
	
	public Celda ultimaSeleccionada() {
		return ultimaSeleccionada;
	}
	
	public Celda getCelda(int i, int j) {
		return this.tablero[i][j];
	}
	
	public int getCantFilas() {
		return this.cantFilas;
	}
	
	public Celda[] getItems() {
		return this.items;
	}
	
	
	public int getCantElementos() {
		return cantElementos;
	}
	
	public void incrementarCantElementos() {
		this.cantElementos++;
	}
	
	public void decrementarCantElementos() {
		this.cantElementos--;
	}

	
    public boolean posicionValida(int x, int y, int valor) {
	    
    	if(valor != 0) {
	    	//comprueba columna y columna
	    	
	    	for(int a = 0; a < cantFilas; a++) {
	    		
	    		
	    		//System.out.println("X: "+ultimaSeleccionada().getX() +" parametro "+x);
				
				//System.out.println("Y: "+ultimaSeleccionada().getY() +" parametro "+y);
				
	    		
				
	    		if((tablero[x][a].getValor() == valor) || (tablero[a][y].getValor() == valor)) {
	    			JOptionPane.showMessageDialog(null, "Jugada no válida.");
	    			//System.out.println("Posición INVÁLIDA");
	                return false;
	    		}
	    	}
	    	
	    	//comprueba bloque
	    	
	    	for(int a = 0; a < cantFilas/3; a++){
	    		for(int b = 0; b < cantFilas/3; b++){
	    			
	    				 if(tablero[((x/3)*3)+a][(y/3)*3+b].getValor() == valor){
	    					 //System.out.println("Posición INVÁLIDA, bloque");
	    					 JOptionPane.showMessageDialog(null, "Jugada no válida.");
	    					 return false;
	    				 }
	    		}
	    	}
	    	
	    	
	  	
	    	//System.out.println("posición válida");
	    	incrementarCantElementos();
	    	  
	    	//System.out.println("HAY :"+getCantElementos());
    	} else {
    		
    		if(tablero[x][y].getValor() != 0) {
	    		decrementarCantElementos();
	    		//System.out.println("HAY :"+getCantElementos());
	    		return true;
	    		
    		} else {
    			//System.out.println("HAY :"+getCantElementos());
				JOptionPane.showMessageDialog(null, "Jugada no válida.");
    			return false;
    		}
    	}
    	
        return true;
    }
    
    
    
}