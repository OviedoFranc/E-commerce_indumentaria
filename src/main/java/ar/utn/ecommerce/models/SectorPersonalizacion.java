package ar.utn.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class SectorPersonalizacion {
    @Getter @Setter
    private String sectorPersonalizacion;
    @Getter  @Setter
    private ArrayList<TipoPersonalizacion> tiposPersonalizacion = new ArrayList<TipoPersonalizacion>();


    public void addTipoPersonalizacion(TipoPersonalizacion personalizacion) {
        this.tiposPersonalizacion.add(personalizacion);
    }

}
