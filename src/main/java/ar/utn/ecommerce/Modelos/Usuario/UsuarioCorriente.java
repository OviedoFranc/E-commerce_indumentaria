package ar.utn.ecommerce.Modelos.Usuario;

import ar.utn.ecommerce.Modelos.Venta.Compra;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name ="UsuarioCorriente")
public class UsuarioCorriente extends Usuario{

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "usuarioID" , referencedColumnName = "ID")
    List<Compra> comprasRealizadas = new ArrayList<>();


    public UsuarioCorriente(String nombre, String email, String password) {
        super(nombre,email,password, TipoCuenta.CORRRIENTE);
    }

    public void addCompra(Compra compra) {
        this.comprasRealizadas.add(compra);
    }


    public UsuarioCorriente() {
        super();
    }

}