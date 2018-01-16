package com.edgardleal.engine;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author edgardleal
 *
 */
public class MediaCenter implements Runnable {

  private static final Logger LOGGER = LoggerFactory.getLogger(MediaCenter.class);
  private MediaTracker tracker;
  private HashMap<String, Image> imagens;
  private HashMap<String, Clip> sounds;
  private JProgressBar progressBar;
  private Thread controle;
  private JDialog janela;
  private Vector<String> listaTemporaria = new Vector<>();
  private Vector<String> listaURLSounds = new Vector<>();


  private static MediaCenter innerInstance;

  /**
   * Constructor for MediaCenter.
   * 
   */
  private MediaCenter() {
    controle = new Thread(this, getClass().getSimpleName());
    imagens = new HashMap<>();
    sounds = new HashMap<>();
  }

  public static MediaCenter instance() {
    if (innerInstance == null) {
      innerInstance = new MediaCenter();
    }
    return innerInstance;
  }

  /**
   * Method add.
   * 
   * @param image String
   */
  public void add(String image) {
    listaTemporaria.add(image);
  }

  /**
   * Method add.
   * 
   * @param chave String
   * @param image Image
   */
  private void add(String chave, Image image) {
    getTracker().addImage(image, getImages().size());
    getImages().put(chave, image);
    getProgressBar().setMaximum(getImages().size());
    LOGGER.debug("add Imagem : ", chave);
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

  /**
   * Method getJanela.
   * 
   * @return JDialog
   */
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

  /**
   * Method getProgressBar.
   * 
   * @return JProgressBar
   */
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
   * 
   * @return Image
   */
  public Image getImage(String nome) {

    Image image = imagens.get(nome);
    if (image == null) {
      try {
        image = ImageIO.read(ClassLoader.getSystemResource(nome));
      } catch (IOException e) {
        LOGGER.debug("Erro ao carregar a imagem", e);
      }
    }
    return image;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    MediaCenter midias = new MediaCenter();
    midias.showProgress();
  }


  /* GGAS */
  /**
   * Method getTracker.
   * 
   * @return MediaTracker
   */
  public MediaTracker getTracker() {
    if (tracker == null)
      tracker = new MediaTracker(null);
    return tracker;
  }

  /**
   * Method getImages.
   * 
   * @return HashMap<String,Image>
   */
  public HashMap<String, Image> getImages() {
    if (imagens == null) {
      imagens = new HashMap<>();
    }
    return imagens;
  }

  /**
   * Method run.
   * 
   * @see java.lang.Runnable#run()
   */
  @Override
  public void run() {
    boolean erro = false;
    try {
      for (String aListaTemporaria : listaTemporaria) {
        add(aListaTemporaria, getImage(aListaTemporaria));
      }

    } catch (Exception ex) {
      LOGGER.error("Erro ao carregar as imagens", ex);
      erro = true; /* Silenciador */
    }

    int totalOk;
    while (!getTracker().checkAll()) {
      totalOk = 0;
      for (int i = 0; i < getImages().size(); i++) {
        totalOk += getTracker().checkID(i) ? 1 : 0;
      }

      // getProgressBar().setValue(total_ok);
      if (totalOk == getImages().size() || erro)
        break;

      try {
        Thread.sleep(100);
        System.out
            .println("Itens checkados " + totalOk + " , total de itens " + getImages().size());
      } catch (Exception e) {
        // Sinlenciador da exce��o
        LOGGER.error("Thread sleep was interrupted", e);
      }// try
    }// while
    janela.setVisible(false);
    janela = null;
    System.out.println("Fim da Thread");
  }// run

  /**
   * Method carregarSons.
   * 
   * @return boolean
   */
  private boolean carregarSons() {
    try {
      for (int i = 0; i < listaURLSounds.size(); i++) {
        sounds.put(listaURLSounds.get(i), loadAudioClip(listaURLSounds.get(i)));
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  private Clip loadAudioClip(final String name) throws LineUnavailableException,
      UnsupportedAudioFileException, IOException {
    URL url = this.getClass().getClassLoader().getResource(name);
    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
    // Get a sound clip resource.
    Clip clip = AudioSystem.getClip();
    // Open audio clip and load samples from the audio input stream.
    clip.open(audioIn);
    return clip;
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

  /**
   * Method getSounds.
   * 
   * @return HashMap<String,AudioClip>
   */
  public HashMap<String, Clip> getSounds() {
    if (sounds == null) {
      sounds = new HashMap<>();
    }
    return sounds;
  }

  /**
   * Method getSound.
   * 
   * @param chave String
   * @return AudioClip
   */
  public Clip getSound(String chave) {
    return getSounds().get(chave);
  }

}// class
