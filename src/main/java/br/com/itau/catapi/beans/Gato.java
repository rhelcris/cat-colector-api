package br.com.itau.catapi.beans;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@Table(name = "gatos")
public class Gato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Raca raca;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "gato_id")
    private List<Foto> fotos;

}
