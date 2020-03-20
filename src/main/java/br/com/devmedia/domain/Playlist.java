package br.com.devmedia.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "playlists")
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto_increment
    private long id;

    @NotBlank // validação
    @Size(min = 2, max = 60) // validação
    @Column(nullable = false, length = 60) // naõ pode ser nula
    private String nome;

    @NotBlank
    @Column(nullable =  false)
    private String descricao;

    // relacionamento 1:N entre playlist e musicas | mappedBy significa a fk de playlist em musicas
    // ao atualizar uma playlist | toda essa playlist será atualizada (CASCADETYPEALL)
    @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL) // 1 playlist pode ter 1 ou varias musicas
    private List<Musica> musicas;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }
}
