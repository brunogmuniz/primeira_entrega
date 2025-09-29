package br.csi.trilhagaucha.model.cidade;


import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

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

    @NonNull
    @Schema(description = "Nome da cidade", example = "Santa Maria")
    private String nome;

    @Schema(description = "Regiao da cidade", example = "Serra")
    @Enumerated(EnumType.STRING)
    private Regiao regiao;
    @NonNull
    @Schema(description = "Latitude da cidade")
    private Double latitude;
    @NonNull
    @Schema(description = "Longitude da cidade")
    private Double longitude;



    public enum Regiao {
        Norte,
        Serra,
        Litoral,
        CentroOeste,
        Missoes,
        Metropolitana
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
