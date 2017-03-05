package br.com.kakobotasso.restaurantesfiap.models;

public class Restaurante {
    private Long id;
    private String nome;
    private String pedido;
    private String opiniao;

    public Restaurante(){}

    public Restaurante(String nome, String pedido, String opiniao) {
        this.nome = nome;
        this.pedido = pedido;
        this.opiniao = opiniao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }

    public String getOpiniao() {
        return opiniao;
    }

    public void setOpiniao(String opiniao) {
        this.opiniao = opiniao;
    }
}
