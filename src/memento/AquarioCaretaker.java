package memento;

public class AquarioCaretaker {
    private AquarioMemento ultimoMemento;

    public void salvar(AquarioMemento memento) {
        this.ultimoMemento = memento;
    }

    public AquarioMemento recuperar() {
        return ultimoMemento;
    }

    public boolean possuiMemento() {
        return ultimoMemento != null;
    }

    public void limpar() {
        ultimoMemento = null;
    }
}