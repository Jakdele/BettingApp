package pl.coderslab.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import pl.coderslab.validator.CheckEmail;
import pl.coderslab.validator.CheckLogin;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CheckEmail
    @Column(nullable = false, unique = true)
    private String email;

    @CheckLogin
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private boolean enabled = true;

    @NotBlank
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Wallet wallet;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles;
}
