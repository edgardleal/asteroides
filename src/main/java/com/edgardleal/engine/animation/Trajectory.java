package com.edgardleal.engine.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JDialog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.edgardleal.engine.Vetor;

public class Trajectory {
  private static final Logger LOGGER = LoggerFactory.getLogger(Trajectory.class);
  ArrayList<Vetor> vetores = new ArrayList<Vetor>();
  Vetor deslocamento = new Vetor(2, 0);
  int x, y, pos = 0;

  public Trajectory(int x, int y) {
    this.y = y;
    this.x = x;
  }

  public void setDeslocamento(Vetor v) {
    deslocamento = v;
  }

  public void add(Vetor v) {
    vetores.add(v);
  }

  public int getX() {
    return x;
  }

  public boolean next() {
    if (pos == vetores.size() - 1)
      return false;
    deslocamento.setDirecao(vetores.get(pos).getDirecao());
    vetores.get(pos).setRaio(vetores.get(pos).getRaio() - deslocamento.getRaio());
    x = +(int) (vetores.get(pos).getX());
    y = +(int) (vetores.get(pos).getY());
    if (vetores.get(pos).getRaio() < 1.5)
      pos++;
    LOGGER.debug("Raio: {}", vetores.get(pos).getRaio());
    return true;
  }

  public int getY() {
    return y;
  }

  public static void main(String[] args) {
    Trajectory t = new Trajectory(50, 50);
    t.add(new Vetor(-5, -50));
    t.add(new Vetor(-50, 5));
    t.add(new Vetor(5, 50));
    t.add(new Vetor(50, -5));

    Tela tela = new Tela(t);
    tela.setVisible(true);
    LOGGER.info("fim da execu��o");
    System.exit(0);
  }
}


class Tela extends JDialog implements Runnable {
  /**
	 * 
	 */
  private static final long serialVersionUID = -382822471525634591L;
  private Thread controle = new Thread(this, this.getClass().getSimpleName());
  private Trajectory t;
  private Vetor v;
  private int delay = 100, x = 0, y = 0;

  public Tela(Trajectory t) {
    this.t = t;
    setSize(400, 400);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    setLocation(d.width / 2 - 200, d.height / 2 - 200);
    setResizable(false);
    setUndecorated(true);
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        setVisible(false);
      }
    });
    this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.red));
    controle.start();
    setModal(true);
  }

  public synchronized void setDelay(int value) {
    delay = value;
  }

  public boolean next() {
    v = t.vetores.get(t.pos);
    return t.next();
  }

  @Override
  public void run() {
    while (next()) {
      try {
        Thread.sleep(delay);
        x = t.getX();
        y = t.getY();
        this.repaint();
      } catch (Exception e) {
        // TODO: handle exception
      }
    }
  }

  @Override
  public void paint(java.awt.Graphics g) {
    super.paint(g);
    if (v == null)
      return;
    g.setColor(Color.blue);
    g.fillOval(200 + x, 200 + y, 7, 7);
    g.drawString("POS: " + t.pos + " X:" + x + " , Y:" + y + "  Raio : "
        + t.vetores.get(t.pos).getRaio(), 20, 20);
  }
}
