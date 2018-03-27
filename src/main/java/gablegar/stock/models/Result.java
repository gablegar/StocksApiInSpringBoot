package gablegar.stock.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import gablegar.stock.database.entities.Stock;

import java.util.List;
import java.util.Objects;

/**
 * Created by glegarda on 13/03/18.
 * This model is used to represent the response of the API
 */
@JsonInclude(Include.NON_NULL)
public class Result {
	String status;
	String message;
	Stock stock;
	List<Stock> stocks;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public List<Stock> getStocks() {
		return stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Result(String status, String message, Stock stock, List<Stock> stocks) {
		this.status = status;
		this.message = message;
		this.stock = stock;
		this.stocks = stocks;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"status\":\"" + status + "\""
				+ ",\"message\":\"" + message + "\""
				+ "}";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Result that = (Result) o;
		return Objects.equals(status, that.status) &&
				Objects.equals(message, that.message) &&
				Objects.equals(stock, that.stock) &&
				Objects.equals(stocks, that.stocks);
	}
}
