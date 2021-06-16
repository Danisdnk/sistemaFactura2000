package models.impuesto;

import java.time.LocalDate;

public class CertificadoExento {


    public CertificadoExento() {

    }

    private TipoImpuesto tipo;

    private LocalDate caducidad;

    private Boolean esCaducado;

    public TipoImpuesto getTipo() {
        return tipo;
    }

    public void setTipo(TipoImpuesto tipo) {
        this.tipo = tipo;
    }

    public LocalDate getCaducidad() {
        return caducidad;
    }

    public void setCaducidad(LocalDate caducidad) {
        this.caducidad = caducidad;
    }

    public Boolean getEsCaducado() {
        return esCaducado;
    }

    public void setEsCaducado(Boolean esCaducado) {
        this.esCaducado = esCaducado;
    }
}