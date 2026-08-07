package model;

public class AquarioBuilder {
    private String nome;
    private int volume;
    private char tipoAgua;

    public AquarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public AquarioBuilder comVolume(int volume) {
        this.volume = volume;
        return this;
    }

    public AquarioBuilder comTipoAgua(char tipoAgua) {
        this.tipoAgua = tipoAgua;
        return this;
    }

    public Aquario build() {
        return new Aquario(this);
    }

    public String getNome() {
        return nome;
    }

    public int getVolume() {
        return volume;
    }

    public char getTipoAgua() {
        return tipoAgua;
    }
}