package br.com.devmedia.dao;

import br.com.devmedia.domain.Playlist;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // => COMPONENTE RESPONSAVEL POR ACESSO A DADOS, ARMAZENADOS EM ALGUM MECANISMO DE PERSISTENCIA
public class PlaylistDaoImpl implements PlaylistDao {

    @PersistenceContext // o Spring irá gerenciar o EntityManager dessa forma | no need rollback e close
    private EntityManager em;

    @Override
    public void salvar(Playlist playlist) {
        em.persist(playlist); // INSERT INTO O OBJETO PLAYLIST
    }

    @Override
    public List<Playlist> recuperar() {// RECUPERA TDS A PLAYLISTS CADASTRADAS
        return em.createQuery("select p from Playlist p", Playlist.class).getResultList();
    }

    @Override
    public Playlist recuperarPorId(long id) {
        return em.find(Playlist.class, id);
    }

    @Override
    public void atualizar(Playlist playlist) {
        em.merge( playlist ); // PARECIDO COM O INSERT MA É UM UPDATE
    }

    @Override
    public void excluir(long id) {
        em.remove( em.getReference( Playlist.class, id ) ); // PARECIDO COM O RECUPERARPOR ID MAS ... DELETA
    }
}
