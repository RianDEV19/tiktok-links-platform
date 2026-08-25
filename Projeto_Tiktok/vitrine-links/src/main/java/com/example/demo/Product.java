package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity //Avisa o Java que essa classe vai virar uma tabela no banco de dados
public class Product {
    @Id //Diz que este e o indentificador unico(A chave primaria)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //O banco vai gerar os IDs automaticamente
    private Long id;

    private String titulo;
    private String urlAfiliado;
    private String urlImagem;
    private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlAfiliado() {
        return urlAfiliado;
    }

    public void setUrlAfiliado(String urlAfiliado) {
        this.urlAfiliado = urlAfiliado;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }
}
