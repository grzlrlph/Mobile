package com.example.exrecyclerviewfilme;

public class Filme  {
    private String nome, genero, diretor;

    public Filme(String nome, String genero, String diretor ) {
        this.nome = nome;
        this.genero = genero;
        this.diretor = diretor;

    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getDiretor() {
        return diretor;
    }

}
