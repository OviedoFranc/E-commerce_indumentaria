package ar.utn.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

public class TipoPersonalizacion{
    @Getter @Setter
    private String tipoPersonalizacion;

    public TipoPersonalizacion(String tipoPersonalizacion) {
        this.tipoPersonalizacion = tipoPersonalizacion;
    }
}
