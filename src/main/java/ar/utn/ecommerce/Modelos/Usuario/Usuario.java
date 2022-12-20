package ar.utn.ecommerce.Modelos.Usuario;

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

    @Column( name = "Password")
    private String Password;

    @Enumerated(EnumType.STRING)
    @Column( name = "tipoDeCuenta")
    private TipoCuenta tipoDeCuenta;


    public Usuario(String nombre, String email, String password, TipoCuenta tipoDeCuenta) {
        this.Nombre = nombre;
        this.email = email;
        this.Password = password;
        this.tipoDeCuenta = tipoDeCuenta;
    }
    public Usuario() {

    }
}
