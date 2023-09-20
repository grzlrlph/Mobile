package com.example.petshop;

public class Pet {
    private Long id;
    private String nome, sexo, raca, especie, dono;
    private int idade;

    public Pet() {
    }

    public Pet(Long id, String nome, String sexo, String raca, String especie, String dono, int idade) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        this.raca = raca;
        this.especie = especie;
        this.dono = dono;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pet {" +
                "id=" + id +
                ", nome='" + nome  +
                ", sexo='" + sexo  +
                ", raca='" + raca  +
                ", especie='" + especie  +
                ", dono='" + dono +
                ", idade=" + idade +
                '}';
    }
}
