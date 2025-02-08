package domain;

public interface IConta {

	void addItem(Item item, int qtde);

	void remove(int index);

	double getTotal();

	StringBuilder listar();
}
