package br.com.devmedia.service;

import br.com.devmedia.dao.MusicaDao;
import br.com.devmedia.domain.Musica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MusicaServiceImpl implements MusicaService {

    @Autowired // SPRING INJETARA O = new MusicDao() automaticamente
    private MusicaDao musicaDao;

    @Autowired // SPRING INJETARA O = new PlaylistService() automaticamente
    private PlaylistService playlistService;

    @Override
    public void salvar(Musica musica, long playlistId) {
        musica.setPlaylist( playlistService.recuperarPorId( playlistId ) );
        musicaDao.salvar( musica );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Musica> recuperarPorPlaylist( long playlistId ) {
        return musicaDao.recuperarPorPlaylist(playlistId);
    }

    @Override
    @Transactional(readOnly = true)
    public Musica recuperarPorPlaylistEMusicaId(long playlistId, long musicaId) {
        return musicaDao.recuperarPorPlaylistEMusicaId(playlistId, musicaId);
    }

    @Override
    public void atualizar( Musica musica, long playlistId ) {
        // EVITA QUE UMA MUSICA SEJA ATUALIZADA OU SALVA NO BANCO SEM PERTENCER A UMA PLAYLIST
        musica.setPlaylist( playlistService.recuperarPorId( playlistId ) );
        musicaDao.salvar( musica );
    }

    @Override
    public void excluir(long playlistId, long musicaId) {
        musicaDao.excluir(recuperarPorPlaylistEMusicaId(playlistId, musicaId).getId());
    }
}
