package dtos;

import java.util.List;

public class CategoriaDto {
    private String nome;
    private List<ItemDto> itens;

    public CategoriaDto(String nome, List<ItemDto> itens) {
        this.nome = nome;
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public List<ItemDto> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "CategoriaDto{nome='" + nome + "', itens=" + itens + "}";
    }
}
