package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logica.Celda;
import logica.Juego;
import logica.tiempoGrafico;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

import javax.swing.JOptionPane;



@SuppressWarnings("unused")
public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Juego juego;
	private JLabel S_0,S_1,M_0,M_1,H_0,H_1;
	private int s0,s1,m0,m1,h0,h1 = 0;	
	private tiempoGrafico tg;
	private boolean estaJugando;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		
		juego = new Juego();		
		tg = new tiempoGrafico();
		estaJugando = true;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rafael\\Desktop\\UNI\\tdp\\proyecto2\\img\\2.png"));
		
		FondoPanel fondo = new FondoPanel("/img/fondo.png");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 950);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("EmojiSudoku");
		
		
		JPanel panelPrincipal = new JPanel();
		fondo.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipal.setBackground(new Color(0,0,0,0));

		//Panel Arriba
		JPanel panelUp = new JPanel();
		panelUp.setOpaque(false);

		panelPrincipal.add(panelUp, BorderLayout.NORTH);
		panelUp.setLayout(new BorderLayout(0, 0));
		
		JLabel logo = new JLabel("");
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		logo.setIcon(new ImageIcon(GUI.class.getResource("/img/logo.png")));
		
	
		panelUp.add(logo, BorderLayout.CENTER);
		
		
		//panel tiempo
		JPanel panelTiempo = new JPanel();
		panelTiempo.setOpaque(false);
		panelUp.add(panelTiempo,BorderLayout.SOUTH);
		
		panelTiempo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		JLabel tiempo_img = new JLabel("");
		tiempo_img.setHorizontalAlignment(SwingConstants.CENTER);
		tiempo_img.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/tiempo.png")));
		panelTiempo.add(tiempo_img);
		
	
		H_1 = new JLabel("");
		H_1.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(H_1);
		H_0 = new JLabel("");
		H_0.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(H_0);
		
		JLabel dosPuntos1 = new JLabel("");
		dosPuntos1.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_dp.png")));
		panelTiempo.add(dosPuntos1);
		
		M_1 = new JLabel("");
		M_1.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(M_1);
		M_0 = new JLabel("");
		M_0.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(M_0);
		
		JLabel dosPuntos2 = new JLabel("");
		dosPuntos2.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_dp.png")));
		panelTiempo.add(dosPuntos2);
		
		S_1 = new JLabel("");
		S_1.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(S_1);
		S_0 = new JLabel("");
		S_0.setIcon(new ImageIcon(GUI.class.getResource("/img/tiempo/t_0.png")));
		panelTiempo.add(S_0);
	
		//inica el tiempo
		tiempo(tg);
				
		//Panel Centro
			
		FondoPanel panelCenter = new FondoPanel("/img/barras.png");
		
		panelCenter.setOpaque(false);
		panelPrincipal.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(juego.getCantFilas(), 0, 5, 5));
    
		cargarTablero(juego,panelCenter);
		

		//Panel Bajo
		FondoPanel panelDown = new FondoPanel("/img/transparente.png");
		panelPrincipal.add(panelDown, BorderLayout.SOUTH);
		panelDown.setOpaque(false);
		
		
		cargarItems(juego,panelDown);

		this.setContentPane(fondo);
		
		
	}	
	
	private void cargarTablero(Juego juego, FondoPanel fp) {
		
		for (int i = 0; i <juego.getCantFilas(); i++) {
			
			for(int j = 0; j<juego.getCantFilas(); j++) {
				
				Celda c = juego.getCelda(i,j);

				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				
				
				fp.add(label);
				
				label.addComponentListener(new ComponentAdapter() {
					@Override
					public void componentResized(ComponentEvent e) {
						label.setIcon(grafico);

						
					}
				});
			
				
				label.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						
						if(c.puedoCambiar()) {
							
							juego.accionar(c);
							
							label.setIcon(c.getEntidadGrafica().getGrafico());
							
							//reDimensionar(label,c.getEntidadGrafica().getGrafico());
							
						//	System.out.println("click tablero "+c.getValor());
							
						}
					


					}
					
				
					
				});
				
				
			}
		}	
		
	}
	
	private void cargarItems(Juego juego, FondoPanel panel){
		
		for (int i = 0; i <juego.getCantFilas()+1; i++) {
			
			Celda c = juego.getItems()[i];
			ImageIcon imagen;
			
			if(i != 0) {
				imagen = new ImageIcon(GUI.class.getResource("/img/"+i+".png"));
			}else {
			
				imagen = new ImageIcon(GUI.class.getResource("/img/00.png"));
			}

			c.getEntidadGrafica().setGrafico(imagen);

			JLabel emoji = new JLabel();
			
			emoji.setIcon(imagen);
			
			panel.add(emoji);
			
			emoji.addMouseListener(new MouseAdapter() {
				
				public void mouseClicked(MouseEvent e) {
				
					if((juego.ultimaSeleccionada() != null) && (juego.posicionValida(juego.ultimaSeleccionada().getX(),juego.ultimaSeleccionada().getY(), c.getValor()))) {
						
						juego.ultimaSeleccionada().actualizar(c.getValor());
						
						
					//	System.out.println(juego.getCantElementos());
						
						
						if(juego.getCantElementos() == 81) {
					    	System.out.println("GANÉ!!!!!!!!!!!!!!!");
					    	
					    	finDeJuego();
							
							JOptionPane.showMessageDialog(null, "Haz GANADO!!!");
					    	
						}
						
					
					}
				}
			});
		
			
		}
	}
	
	private void tiempo(tiempoGrafico tg) {
		
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				
				if(s0<9) {
					s0++;
					ImageIcon imageIconS0 = new ImageIcon(getClass().getResource(tg.getImagenes()[s0]));
					S_0.setIcon(imageIconS0);
					
				}else {
					if(s1<5) {
						s1++;
						ImageIcon imageIconS1 = new ImageIcon(getClass().getResource(tg.getImagenes()[s1]));
						S_1.setIcon(imageIconS1);
						
					}else {
						s1=0;
						ImageIcon imageIconS1 = new ImageIcon(getClass().getResource(tg.getImagenes()[s1]));
						S_1.setIcon(imageIconS1);
						
						if(m0<9) {
							m0++;
							ImageIcon imageIconM0 = new ImageIcon(getClass().getResource(tg.getImagenes()[m0]));
							M_0.setIcon(imageIconM0);
						}else {
							m0=0;
							ImageIcon imageIconM0 = new ImageIcon(getClass().getResource(tg.getImagenes()[m0]));
							M_0.setIcon(imageIconM0);
							
							if(m1<5) {
								m1++;
								ImageIcon imageIconM1 = new ImageIcon(getClass().getResource(tg.getImagenes()[m1]));
								M_1.setIcon(imageIconM1);
							}else {
								m1=0;
								ImageIcon imageIconM1 = new ImageIcon(getClass().getResource(tg.getImagenes()[m1]));
								M_1.setIcon(imageIconM1);
								
								if(h0<9) {
									h0++;
									ImageIcon imageIconH0 = new ImageIcon(getClass().getResource(tg.getImagenes()[h0]));
									H_0.setIcon(imageIconH0);
									
								}else {
									h0=0;
									ImageIcon imageIconH0 = new ImageIcon(getClass().getResource(tg.getImagenes()[h0]));
									H_0.setIcon(imageIconH0);
									
									if(h1<9) {
										h1++;
										ImageIcon imageIconH1 = new ImageIcon(getClass().getResource(tg.getImagenes()[h1]));
										H_1.setIcon(imageIconH1);
											
									}else {
										h1=0;
										ImageIcon imageIconH1 = new ImageIcon(getClass().getResource(tg.getImagenes()[h1]));
										H_1.setIcon(imageIconH1);
										
									}
								}
							}
							
							
						}
						
						
					}
					
					s0=0;
					ImageIcon imageIconS0 = new ImageIcon(getClass().getResource(tg.getImagenes()[s0]));
					S_0.setIcon(imageIconS0);
					
				}
				
				repaint();
				
			}
			
			
		});
		
		if(estaJugando) {
			timer.start();
		}else {
			timer.stop();
		}
		
	
	}
	
	
	public void finDeJuego() {
		this.estaJugando = false;
	}
	

}
