package views.utils.v2;

import java.util.List;

public interface Controlador<TModelo, TModeloTabla> {
    static Controlador getInstancia() {
        return null;
    }

    void agregar(TModelo m);

    List<TModeloTabla> getDatos();
}
