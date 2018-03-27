package gablegar.stock.database.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Created by glegarda on 13/03/18.
 * The model and entity to represent the stock.
 */
@Entity
@Table(name = "Stock")
public class Stock {

	@Id
	private Long id;

	@Column
	private String name;

	@Column(name = "current_price")
	private Double currentPrice;

	@Column
	private Integer quantity;

	@Column
	private String description;

	@Column(nullable = false, name = "last_update")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime lastUpdate = ZonedDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(Double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public Integer getQuantity() { return quantity; }

	public void setQuantity(Integer quantity) { this.quantity = quantity; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public ZonedDateTime getLastUpdate() {
		return lastUpdate;
	}

	public Stock(Long id, String name, Double currentPrice, Integer quantity, String description) {
		this.id = id;
		this.name = name;
		this.currentPrice = currentPrice;
		this.quantity = quantity;
		this.description = description;
	}

	public Stock() {
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":\"" + id + "\""
				+ ",\"name\":\"" + name + "\""
				+ ",\"currentPrice\":" + currentPrice
				+ ",\"quantity\":" + quantity
				+ ",\"description\":\"" + description + "\""
				+ ",\"lastUpdate\":\"" + lastUpdate + "\""
				+ "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stock stock = (Stock) o;
		return Objects.equals(id, stock.id) &&
				Objects.equals(name, stock.name) &&
				Objects.equals(currentPrice, stock.currentPrice) &&
				Objects.equals(quantity, stock.quantity) &&
				Objects.equals(description, stock.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, currentPrice, quantity, description, lastUpdate);
	}
}
