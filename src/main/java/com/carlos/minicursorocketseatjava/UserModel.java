package com.carlos.minicursorocketseatjava;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true)      // Não aceita o mesmo registro com o mesmo valor na coluna
    private String username;
    private String password;

    @CreationTimestamp          // preencher automaticamente a data de criação de um registro
    private LocalDateTime createdAt;
}
