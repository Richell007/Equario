package model;

public class AquarioBuilder {
    private int id;
    private String nome;
    private double volume;
    private TipoAquario tipo;
    private User dono;

    public AquarioBuilder comId(int id) {
        this.id = id;
        return this;
    }

    public AquarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public AquarioBuilder comVolume(double volume) {
        this.volume = volume;
        return this;
    }

    public AquarioBuilder comTipo(TipoAquario tipo) {
        this.tipo = tipo;
        return this;
    }

    public AquarioBuilder comDono(User dono) {
        this.dono = dono;
        return this;
    }

    public Aquario build() {
        return new Aquario(this);
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