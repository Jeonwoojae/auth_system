package com.example.monolithic_auth.domain;

import lombok.*;

import javax.persistence.*;

import static com.example.monolithic_auth.utils.EncryptionUtils.encryptSHA256;

@Getter
@NoArgsConstructor
@Entity
@Setter
@Table
public class Users extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Users(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = encryptSHA256(password);
        this.role = role;
    }

    public void update(Users newUser){
        this.name = newUser.getName();
        this.email = newUser.getEmail();
        this.password = encryptSHA256(newUser.getPassword());
        this.role = newUser.getRole();
    }

}
