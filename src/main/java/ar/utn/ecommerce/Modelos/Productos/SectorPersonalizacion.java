package ar.utn.ecommerce.Modelos.Productos;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter @Setter @Table(name = "sectoresPersonalizacion")
public class SectorPersonalizacion {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sectorID")
    private Integer sectorID;
    @NotNull
    @Column(name = "sectorPersonalizacion")
    private String sectorPersonalizacion;
    @ElementCollection
    @CollectionTable(name = "tipoPersonalizacion" , joinColumns = @JoinColumn(name = "sectorID" ))
    @Column(name = "TipoPersonalizacion")
    private List<String> posiblesTipoPersonalizacion = new ArrayList<>();

    public SectorPersonalizacion(String sectorPersonalizacion,List<String> posiblesTipoPersonalizacion) {
        this.sectorPersonalizacion = sectorPersonalizacion;
        this.posiblesTipoPersonalizacion = posiblesTipoPersonalizacion;
    }

    public SectorPersonalizacion() {

    }
    public void addposiblesTipoPersonalizacion(String posiblesTipoPersonalizacion) {
        this.posiblesTipoPersonalizacion.add(posiblesTipoPersonalizacion);
    }
    public Boolean poseeTipoPersonalizacion (String tipoPersonalizacion){
        return  this.posiblesTipoPersonalizacion.stream()
                .anyMatch(p -> p.equals(tipoPersonalizacion));
    }
    public void deletePosiblesTipoPersonalizacion(String posiblesTipoPersonalizacion) {

        List<String> listaFiltrada = this.posiblesTipoPersonalizacion.stream()
                .filter(i -> ! i.equals(posiblesTipoPersonalizacion))
                .collect(Collectors.toList());
        this.setPosiblesTipoPersonalizacion(listaFiltrada);
    }

    @Override
    public String toString() {
        return "SectorPersonalizacion{" +
                "sectorID=" + sectorID +
                ", sectorPersonalizacion='" + sectorPersonalizacion + '\'' +
                ", posiblesTipoPersonalizacion=" + posiblesTipoPersonalizacion +
                '}';
    }
}
