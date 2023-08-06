package com.authapi.auth.domain;


import com.authapi.auth.Dto.TypeUserDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "typeUser")
@Table(name = "typeuser")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class TypeUser {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_typeuser;
    private String name;
    private String descricao;

    public TypeUser(TypeUserDTO data) {
        this.name = data.name();
        this.descricao = data.descricao();
    }

    public void updateType(TypeUserDTO data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.descricao() != null){
            this.descricao = data.descricao();
        }


    }
}
