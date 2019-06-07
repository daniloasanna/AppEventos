package br.com.senac.appeventos.modelo;

import java.io.Serializable;

public class Evento implements Serializable {
    private Long id;
    private String nome;
    private String local;
    private String hora;
    private String data;


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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }



    @Override
    public String toString() {
        return getId() + " - Nome: " + getNome() + " - Local: " + getLocal() +" - Hora: " + getHora() + " - Data: " + getData();
    }
}
