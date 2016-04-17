package com.edgardleal.engine.math;


/**
 */
public class Pilha {
  private String items[];
  private int pos = -1;

  public Pilha() {
    items = new String[100];
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

  /**
   * Method add.
   * @param item String
   */
  public void add(String item) {
    items[++pos] = item;
  }

  /**
   * Method get.
   * @return String
   */
  public String get() {
    return items[pos--];
  }

}
