package memento;

import model.Aquario;

public class AquarioMemento {
    private final Aquario estado;

    public AquarioMemento(Aquario estado) {
        this.estado = estado;
    }

    public Aquario getEstado() {
        return estado;
    }
}