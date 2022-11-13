package ar.utn.ecommerce.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;

public class Personalizacion {
    @Getter @Setter
    private String producto;
    @Getter @Setter
    private ArrayList<SectorPersonalizacion> posiblesSectoresPersonalizacion= new ArrayList <SectorPersonalizacion>();

    public void addPosiblesSectoresPersonalizacion(SectorPersonalizacion posiblesSectoresPersonalizacion) {
        this.posiblesSectoresPersonalizacion.add(posiblesSectoresPersonalizacion);
    }
}
