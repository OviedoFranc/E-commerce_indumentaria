package ar.utn.ecommerce.Modelos.DTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class DTOUsuarioValido {
    Integer getID;
    String getNombre;

    public DTOUsuarioValido(Integer getID, String getNombre) {
        this.getID = getID;
        this.getNombre = getNombre;
    }
}

