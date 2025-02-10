package dtos;

public class ItemDto {
	private long codigo;
	private String descricao;
	private double preco;

	public ItemDto(long codigo, String descricao, double preco) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public double getPreco() {
		return preco;
	}

	@Override
	public String toString() {
		return "ItemDto{codigo=" + codigo + ", descricao='" + descricao + "', preco=" + preco + "}";
	}
}