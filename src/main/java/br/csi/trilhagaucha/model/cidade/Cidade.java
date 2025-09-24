package br.csi.trilhagaucha.model.cidade;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Regiao regiao;
    private Double latitude;
    private Double longitude;



public enum Regiao {
    NORTE, LITORAL, METROPOLITANA , MISSÕES , CENTRO_OESTE, SERRA
}


    //    -- 3️⃣ Tabela cidades
//    CREATE TABLE cidades (
//            id SERIAL PRIMARY KEY,
//            nome VARCHAR(100) NOT NULL,
//    regiao regioes NOT NULL,
//    latitude DECIMAL(9,6),
//    longitude DECIMAL(9,6)
//);

}
