package ar.utn.ecommerce.models.Usuario;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name ="Usuario")
public class Usuario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer ID;

    @Column(name = "nombreUsuario")
    private String Nombre;

    @Column( name = "correoElectronico")
    private String email;

    @Column( name = "Password")             // Generar con encriptacion de 64b
    private String Password;

    @Enumerated(EnumType.STRING)
    @Column( name = "tipoDeCuenta")
    private TipoCuenta tipoDeCuenta;

    public Usuario(String nombre, String email, String password, TipoCuenta tipoDeCuenta) {
        Nombre = nombre;
        this.email = email;
        Password = password;
        this.tipoDeCuenta = tipoDeCuenta;
    }

    public Usuario() {

    }
}
