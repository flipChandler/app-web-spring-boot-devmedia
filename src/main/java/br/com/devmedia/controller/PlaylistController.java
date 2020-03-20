package br.com.devmedia.controller;

import br.com.devmedia.domain.Playlist;
import br.com.devmedia.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("playlists")  // uma requisição que venha pra localhost:8080/playlists, será tratada pelos metodos dessa classe
public class PlaylistController {

    @Autowired // o controller precisa ter acesso a playlistservice
    private PlaylistService playlistService;

    // requisições GET p/ localhost:8080/playlists/listar
    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("playlists", playlistService.recuperar()); // model envia dados para view (list.html)
        // MODEL ADICIONA O ATRIBUTO PLAYLIST Q RECEBE .RECUPERAR
        return new ModelAndView("/playlist/list", model);// UM NOVO MODEL É CRIADO E ENVIA O OUTRO MODEL É ENVIADO PARA A VIEM LIST

    }

    // requisições GET p/ localhost:8080/playlists/cadastros vai ser atendido por esse método
    @GetMapping("/cadastro") // ModelAttribute 'playlist' que vem da View
    public String preSalvar( @ModelAttribute("playlist") Playlist playlist ) { // recebe um objeto como parametro tipo Playlist
        return "/playlist/add"; // será direcionado para essa pagina
    }

    // requisições POST para localhost:8080/playlists/salvar vai ser atendido por esse método
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr) {
        if ( result.hasErrors() ) { // se ocorreu um erro de validação
            return "playlist/add";
        }

        playlistService.salvar( playlist );
        attr.addFlashAttribute("mensagem", "Playlist criada com sucesso!");
        return "redirect:/playlists/listar"; // vai executar o listar, renderizando novamente a pagina
    }

    // esse método é pra direcionar o usuario para a página que fará a atualização
    // requisições GET para localhost:8080/playlists/3/atualizar vai ser atendido por esse método
    @GetMapping("/{id}/atualizar")
    public ModelAndView preAtualizar(@PathVariable("id") long id, ModelMap model ) {
        Playlist playlist = playlistService.recuperarPorId( id );
        model.addAttribute( "playlist", playlist );
        return new ModelAndView( "/playlist/add", model );
        // a pagina de edição e adição será a mesma
        // id é uma variavel, logo, é declarada entre {}
    }

    @PutMapping("/salvar")
    public String atualizar( @Valid @ModelAttribute("playlist") Playlist playlist, BindingResult result, RedirectAttributes attr ) {
        if ( result.hasErrors() ) {
            return "/playlist/add"; // manda para a pagina
        }

        playlistService.atualizar( playlist );
        attr.addFlashAttribute( "mensagem", "Playlist atualizada com sucesso.");
        return "redirect:/playlists/listar"; // redireciona para o método
    }

    // requisições GET para localhost:8080/playlists/3/remover vai ser atendido por esse método
    @GetMapping("/{id}/remover")
    public String remover(@PathVariable("id") long id, RedirectAttributes attr) {
        playlistService.excluir( id );
        attr.addFlashAttribute("mensagem", "Playlist excluida com sucesso!");
        return "redirect:/playlists/listar";
    }


}
