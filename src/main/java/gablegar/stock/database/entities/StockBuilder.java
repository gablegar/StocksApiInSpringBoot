package gablegar.stock.database.entities;

/**
 * Created by glegarda on 13/03/18.
 */
public class StockBuilder {
	private Long id;
	private String name;
	private Double currentPrice;
	private Integer quantity;
	private String description;

	public StockBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public StockBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public StockBuilder setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
		return this;
	}

	public StockBuilder setQuantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}

	public StockBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	public Stock createStock() {
		return new Stock(id, name, currentPrice, quantity, description);
	}
}