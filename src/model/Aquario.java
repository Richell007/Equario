package model;

public class Aquario {
    private int id;
    private String nome;
    private double volume;
    private TipoAquario tipo;
    private User dono;

    private Aquario(int id, String nome, double volume, TipoAquario tipo, User dono) {
        this.id = id;
        this.nome = nome;
        this.volume = volume;
        this.tipo = tipo;
        this.dono = dono;
    }

    protected Aquario(AquarioBuilder construtor) {
        this.id = construtor.getId();
        this.nome = construtor.getNome();
        this.volume = construtor.getVolume();
        this.tipo = construtor.getTipo();
        this.dono = construtor.getDono();
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getVolume() {
        return volume;
    }

    public TipoAquario getTipo() {
        return tipo;
    }

    public User getDono() {
        return dono;
    }
}