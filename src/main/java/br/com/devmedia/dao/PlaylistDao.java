package br.com.devmedia.dao;

import br.com.devmedia.domain.Playlist;

import java.util.List;
// PROGRAMAR PARA INTERFACE AO INVÉS DE PROGRAMAR PARA IMPLEMENTAÇÃO
public interface PlaylistDao {

    void salvar(Playlist playlist);
    List<Playlist> recuperar();
    Playlist recuperarPorId(long id);
    void atualizar(Playlist playlist);
    void excluir(long id);
}
