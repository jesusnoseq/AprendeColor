import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;

@SuppressWarnings("serial")
public class AprendeColor extends JFrame {

	private JButton changeColor;
	private JButton learn;
	private Color color;
	private Container c;
	private Label answer;
	private Rectangle2D r;
	private PerceptronColor neuron;
	

	public AprendeColor() {
		super("AprendeColor");

		c = getContentPane();
		c.setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		
		answer=new Label("Selecciona un color");
		
		neuron=new PerceptronColor();
		
		r = new Rectangle2D.Float(this.getWidth()/2-100,this.getHeight()/2-70, 200, 200);
		
		changeColor = new JButton("Cambiar color");
		changeColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				color = JColorChooser.showDialog(AprendeColor.this,"Elige un color", color);
				repaint();
				if (color == null){
					color = Color.lightGray;
				}
				if(neuron.responde(color)){
					answer.setText("Posivo");
				}
				else{
					answer.setText("Negativo");
				}
			}
		});
		
		
		learn = new JButton("Entrenar");
		learn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( neuron.entrena(!neuron.responde(color)) ){
					answer.setText("Neurona entrenada");
				}
				else{
					answer.setText("Neurona  no entrenada");
				}
			}
		});
		
		
		c.add(changeColor);
		c.add(learn);
		c.add(answer);

		setVisible(true);

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(color);
		g2.fill(r);
	}

	public static void main(String args[]) {
		AprendeColor app = new AprendeColor();
	}
}