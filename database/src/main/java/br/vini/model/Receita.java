package br.vini.model;

public class Receita {
  
  private String nome;

  private int porcoes;


  public Receita(String nome, int porcoes) {
    this.nome = nome;
    this.porcoes = porcoes;
  }

  public String getNome() {
    return nome;
  }

  public int getPorcoes() {
    return porcoes;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPorcoes(int porcoes) {
    this.porcoes = porcoes;
  }


  @Override
  public String toString() {
    return "Nome: " + this.nome + "\tPorcoes: " + this.porcoes;
  }
}
