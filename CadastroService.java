package com.literalura.service;

import com.literalura.dto.DadosAutor;
import com.literalura.dto.DadosLivro;
import com.literalura.dto.ResultadoAPI;
import com.literalura.model.Autor;
import com.literalura.model.Livro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final ConsumoAPI api = new ConsumoAPI();

    public CadastroService(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void buscarESalvarLivro(String titulo) {
        ResultadoAPI resultado = api.buscarLivroPorTitulo(titulo);

        if (resultado.results().isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
            return;
        }

        DadosLivro dadosLivro = resultado.results().get(0);

        DadosAutor dadosAutor = dadosLivro.authors().get(0);

        Autor autor = autorRepository.findByNome(dadosAutor.name())
                .orElseGet(() -> {
                    Autor novo = new Autor();
                    novo.setNome(dadosAutor.name());
                    novo.setAnoNascimento(dadosAutor.birth_year());
                    novo.setAnoFalecimento(dadosAutor.death_year());
                    return autorRepository.save(novo);
                });

        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.title());
        livro.setIdioma(dadosLivro.languages().isEmpty() ? "desconhecido" : dadosLivro.languages().get(0));
        livro.setDownloads(dadosLivro.download_count());
        livro.setAutor(autor);

        livroRepository.save(livro);

        System.out.println("Livro salvo com sucesso: " + livro.getTitulo());
    }

    public void listarLivros() {
        livroRepository.findAll().forEach(l ->
                System.out.println(l.getTitulo() + " - " + l.getAutor().getNome()));
    }

    public void listarAutores() {
        autorRepository.findAll().forEach(a ->
                System.out.println(a.getNome() + " (" + a.getAnoNascimento() + " - " + a.getAnoFalecimento() + ")"));
    }
}
