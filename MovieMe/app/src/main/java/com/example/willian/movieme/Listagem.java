package com.example.willian.movieme;

/**
 * Created by Willian on 30/03/2018.
 */

public class Listagem {
    private String nome;
    private String genero;
    private String diretor;
    private Integer faixa;
    private String ano;
    private Integer id;
    private static Integer idA = 0;

    public Listagem(String nome, String genero, String diretor, Integer faixa, String ano) {
        this.nome = nome;
        this.genero = genero;
        this.diretor = diretor;
        this.faixa = faixa;
        this.ano = ano;
        idA++;
        this.id = idA;
    }

    //construtor padrão
    //public Listagem() {
    //    this(null, null, null, null);
    //}

    //getters
    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public String getAno() {
        return ano;
    }

    public Integer getId(){ return this.id; }

    public Integer getFaixa() {
        return faixa;
    }

    //setters
    public void setAno(String ano) {
        this.ano = ano;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public void setFaixa(int faixa) {
        this.faixa = faixa;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id){this.id = id;}
}
