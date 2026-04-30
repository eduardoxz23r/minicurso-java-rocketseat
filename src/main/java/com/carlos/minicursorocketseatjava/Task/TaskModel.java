package com.carlos.minicursorocketseatjava.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "tb_Tasks")
public class TaskModel {

    @Id   //Chave Primária
    @GeneratedValue(generator = "UUID")
    private UUID id;  // UUID garante que cada registro de usuários seja único.
    private String description;

    @Column(length = 50)    //Limitação de caracteres
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    private UUID idUser;

    @CreationTimestamp    // Coloca a data automaticamente no banco de dados
    private LocalDateTime createdAt;


    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("O Campo title deve conter no máximo 50 caractere");
        }
        this.title = title;
    }
}
