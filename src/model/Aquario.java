package model;

import java.io.Serializable;

public class Aquario implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private double volume; 
    private TipoAquario tipo;
    private User dono;     

    public Aquario(int id, String nome, double volume, TipoAquario tipo, User dono) {
        this.id = id;
        this.nome = nome;
        this.volume = volume;
        this.tipo = tipo;
        this.dono = dono;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public double getVolume() { return volume; }
    public TipoAquario getTipo() { return tipo; }
    public User getDono() { return dono; }

    public void setNome(String nome) { this.nome = nome; }
    public void setVolume(double volume) { this.volume = volume; }
    public void setTipo(TipoAquario tipo) { this.tipo = tipo; }
    public void setDono(User dono) { this.dono = dono; }

    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Volume: %.1fL | Tipo: %s | Dono: %s",
                id, nome, volume, tipo, dono.getNome());
    }
}