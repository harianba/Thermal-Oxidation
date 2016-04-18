package thermal_oxidation;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JApplet;

@SuppressWarnings("serial")
public class thermal_oxidation extends JApplet implements ActionListener {
	static O_atomo[] atomos_o;
	static oxigen_mix[] line;
	Graphics gBuffer;
	Image image;
	BufferedImage img;
	int repeat;
	Button boton;
	private boolean flag;

	public void init() {
		// TODO Auto-generated method stub
		this.setLayout(null);
		boton = new Button("start");
		boton.setBounds(260, 360, 80, 25);
		boton.setBackground(new Color(200, 50, 50, 255));
		boton.setFont(new Font("TimesRoman", Font.BOLD, 20));
		boton.addActionListener(this);
		add(boton);
		init_componentes();
		new Thread() {
			public void run() {
				while (true) {
					delay_time();
					repaint();
				}
			}
		}.start();
	}

	protected void delay_time() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
			for (int i = 0; i < atomos_o.length; i++)
				atomos_o[i].mueve();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void init_componentes() {
		atomos_o = new O_atomo[40];
		for (int i = 0; i < atomos_o.length; i++) {
			atomos_o[i] = new O_atomo(350, 260,
					new Point((int) Math.random() * (350 - 20) + 20, (int) Math.random() * (255 - 20) + 20));
		}
		line = new oxigen_mix[5];
		for (int i = 0; i < line.length; i++) {
			line[i] = new oxigen_mix(new Point(49, 250 - (2 * i)), new Point(349, 250 - (2 * i)));
		}
	}

	public void paint(Graphics g) {
		setSize(400, 400);
		if (gBuffer == null) {
			image = createImage(400, 400);
			gBuffer = image.getGraphics();
		}
		gBuffer.setColor(Color.WHITE);
		gBuffer.fillRect(20, 0, 350, 260);
		Graphics2D g2d = (Graphics2D) gBuffer;
		si(g2d);
		g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (flag)
			draw_gas(g2d);
		draw_sio2(g2d);
		if (boton.getLabel() == "finish")
			draw_atom(g2d);
		g.drawImage(image, 0, 0, null);
		g.drawImage(img, 200, 5, null);
	}

	private void draw_gas(Graphics2D g2d) {
		// TODO Auto-generated method stub
		for (int i = 0; i < atomos_o.length; i++) {
			g2d.setColor(Color.RED);
			g2d.fillOval(atomos_o[i].getPosicion().x, atomos_o[i].getPosicion().y, 5, 5);
		}
	}

	private void draw_sio2(Graphics2D g2d) {
		for (int i = 0; i < repeat; i++) {
			g2d.setColor(Color.RED);
			g2d.drawLine(line[i].getPosicion1().x, line[i].getPosicion1().y, line[i].getPosicion2().x,
					line[i].getPosicion2().y);
		}
	}

	private void draw_atom(Graphics2D g2d) {
		try {
			img = ImageIO.read(new File("sio2.png"));
		} catch (Exception e) {
			System.out.println(e);
		}
		g2d.setColor(Color.BLUE);
		g2d.drawRect(100, 240, 10, 10);
		g2d.drawRect(200, 5, img.getWidth(), img.getHeight());
		g2d.drawLine(100, 240, 200, 5);
		g2d.drawLine(110, 240, 280, 5);
		g2d.drawLine(100, 250, 200, 88);
		g2d.drawLine(110, 250, 280, 88);
	}
	public void si(Graphics2D g2d){
		g2d.setColor(Color.GRAY);
		g2d.fillRect(50, 250, 300, 100);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g2d.drawString("Si", 200, 300);
	}

	/**
	 * Acciones posibles cuando presionamos el unico boton que que se tiene en
	 * la applet.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "start") {
			boton.setLabel("next");
			repeat = 1;
			flag = true;
		}
		if (e.getActionCommand() == "next") {
			if (repeat > 4) {
				boton.setLabel("finish");
				flag = false;
			} else {
				repeat++;
			}
		}
		if (e.getActionCommand() == "finish") {
			boton.setLabel("restart");
		}
		if (e.getActionCommand() == "restart") {
			this.getAppletContext().showDocument(this.getDocumentBase());
		}
	}
}
