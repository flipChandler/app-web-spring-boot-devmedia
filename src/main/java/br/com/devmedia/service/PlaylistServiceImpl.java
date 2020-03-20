package br.com.devmedia.service;

import br.com.devmedia.dao.PlaylistDao;
import br.com.devmedia.domain.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service // ESSA CLASSE REPRESENTA  UMA CLASSE DE SERVIÇO PARA O SPRING (API)
@Transactional // INDICA AO SPRING QUE ELE SERÁ O RESPONSÁVEL PELAS TRANSAÇÕES BEGIN(), COMMIT(), ROLLBACK()
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired // ESSA CLASSE DEPENDE DE PLAYLISTDAO (INTERFACE), E O SPRING VAI CUIDAR DISSO
    private PlaylistDao playlistDao; // O = new PlaylistDao() NÃO SERÁ NECESSÁRIO POR CAUSA DO AUTOWIRED
    // COMO PlaylistDao é uma interface, ela não pode ser instanciada, DAÌ, automaticamente = new PlaylistDaoImpl será injetado
    // OU SEJA, QUEM ESTIVER PROGRAMANDO SERVICE, PRECISA CONHECER SOMENTE A INTERFACE PLAYLISTDAO
    // E NÃO OS MÉTODOS DE PLAYLISTDAOIMPL

    @Override
    public void salvar(Playlist playlist) {
        playlistDao.salvar(playlist);
    }

    @Override
    @Transactional(readOnly = true)// INDICA QUE ESSAS TRANSAÇÕES SERÃO SÓ PRA LEITURA (CONSULTA), NADA DE ALTERAR
    public List<Playlist> recuperar() {
        return playlistDao.recuperar();
    }

    @Override
    @Transactional(readOnly = true) // TRANSAÇÕES DE SOMENTE DE LEITURA, NÃO EXISTIRÁ TENTATIVA DE MODIFICAR O BD
    public Playlist recuperarPorId(long id) {
        return playlistDao.recuperarPorId(id);
    }

    @Override
    public void atualizar(Playlist playlist) {
        playlistDao.atualizar(playlist);
    }

    @Override
    public void excluir(long id) {
        playlistDao.excluir(id);
    }
}
