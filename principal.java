package com.literalura;

import com.literalura.service.CadastroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Principal implements CommandLineRunner {

    private final CadastroService service;

    public Principal(CadastroService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication.run(Principal.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                ===== LiterAlura =====
                1 - Buscar e salvar livro por título
                2 - Listar livros cadastrados
                3 - Listar autores cadastrados
                0 - Sair
                """);

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();
                    service.buscarESalvarLivro(titulo);
                }
                case 2 -> service.listarLivros();
                case 3 -> service.listarAutores();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }
}
