package com.programacion.distribuida.db;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Schema(name = "Author" , description = "Presentación Author ")
public class Authors {

    @Getter @Setter
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    @Getter @Setter
    @Schema(required = true)
    private String firtName;

    @Column(name = "last_name")
    @Getter @Setter
    @Schema(required = true)
    private String lastName;
}
