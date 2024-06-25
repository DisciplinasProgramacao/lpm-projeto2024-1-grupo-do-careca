package src.main.java.myapp.grupocarecaspring.entities;

import java.util.List;

public class MenuFechado extends Item {
    private String comida;
    private List<String> bebidas;

    public MenuFechado(String nome, double preco, int identificador, String comida, List<String> bebidas) {
        super(nome, preco, identificador);
        this.comida = comida;
        this.bebidas = bebidas;
    }

   
    @Override
    public String toString() {
        return super.toString() + ", Comida: " + comida + ", Bebidas: " + bebidas;
    }
}