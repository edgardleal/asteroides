package com.edgardleal.engine;

import java.applet.AudioClip;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

public class MediaCenter implements Runnable {
  private MediaTracker tracker;
  private JApplet applet;
  private HashMap<String, Image> imagens;
  private HashMap<String, AudioClip> sounds;
  private JProgressBar progressBar;
  private Thread controle;
  private JDialog janela;
  private Vector<String> listaTemporaria = new Vector<String>();
  private Vector<String> listaURLSounds = new Vector<String>();

  public MediaCenter(JApplet applet) {
    this.applet = applet;
    controle = new Thread(this);
  }

  public void add(String image) {
    listaTemporaria.add(image);
  }

  private void add(String chave, Image image) {
    getTracker().addImage(image, getImages().size());
    getImages().put(chave, image);
    getProgressBar().setMaximum(getImages().size());
    System.out.println("add Imagem : " + chave);
  }

  public void start() {
    if (controle.isAlive())
      return;
    controle.start();
    carregarSons();
    // showProgress();
  }

  public void showProgress() {
    getJanela().setVisible(true);
  }

  public JDialog getJanela() {
    if (janela == null) {
      janela = new JDialog();
      // janela.setModal(true);
      janela.setUndecorated(true);
      janela.setSize(300, 100);
      janela.setTitle("Carregando imagens ...");
      janela.setResizable(false);
      Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
      int x = d.width / 2 - 150, y = d.height / 2 - 50;
      janela.setLocation(x, y);
      Container c = janela.getContentPane();
      c.setLayout(null);
      c.add(getProgressBar());
    }
    return janela;
  }

  public JProgressBar getProgressBar() {
    if (progressBar == null) {
      progressBar = new JProgressBar();
      progressBar.setSize(200, 20);
      progressBar.setLocation(10, 10);
      progressBar.setMinimum(0);
    }
    return progressBar;
  }

  /**
   * Retorna a imagem correspondente a chave informada , caso n�o exista uma imagen para o nome
   * informado , retorna null.
   * 
   * @param nome
   * @return
   */
  public Image getImage(String nome) {
    return imagens.get(nome);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    MediaCenter midias = new MediaCenter(null);
    midias.showProgress();

  }


  /* GGAS */
  public MediaTracker getTracker() {
    if (tracker == null)
      tracker = new MediaTracker(applet);
    return tracker;
  }

  public HashMap<String, Image> getImages() {
    if (imagens == null) {
      imagens = new HashMap<String, Image>();
    }
    return imagens;
  }

  @Override
  public void run() {
    boolean erro = false;
    try {
      for (int i = 0; i < listaTemporaria.size(); i++)
        add(listaTemporaria.get(i),
            applet.getImage(new URL(applet.getDocumentBase(), listaTemporaria.get(i))));

    } catch (Exception ex) {
      erro = true; /* Silenciador */
    }

    int total_ok = 0;
    while (!getTracker().checkAll()) {
      total_ok = 0;
      for (int i = 0; i < getImages().size(); i++) {
        total_ok += getTracker().checkID(i) ? 1 : 0;
      }

      // getProgressBar().setValue(total_ok);
      if (total_ok == getImages().size() || erro)
        break;

      try {
        Thread.sleep(100);
        System.out.println("Itens checkados " + total_ok + " , total de itens "
            + getImages().size());
      } catch (Exception e) {
        // Sinlenciador da exce��o
      }// try
    }// while
    janela.setVisible(false);
    janela = null;
    System.out.println("Fim da Thread");
  }// run

  private boolean carregarSons() {
    try {
      for (int i = 0; i < listaURLSounds.size(); i++) {
        sounds.put(listaURLSounds.get(i), applet.getAudioClip(new URL(listaURLSounds.get(i))));
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Adiciona um nome de arquivo a lista de sons . � inportante que se use apenas o nome do arquivo
   * e n�o a url completa, pois a url base ser� utilizada a da applet
   * 
   * @param arquivo
   */
  public void addSound(String arquivo) {
    listaURLSounds.add(arquivo);
  }

  public HashMap<String, AudioClip> getSounds() {
    if (sounds == null) {
      sounds = new HashMap<String, AudioClip>();
    }
    return sounds;
  }

  public AudioClip getSound(String chave) {
    return getSound(chave);
  }

}// class
