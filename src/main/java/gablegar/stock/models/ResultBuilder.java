package gablegar.stock.models;

import gablegar.stock.database.entities.Stock;

import java.util.List;

/**
 * Created by glegarda on 13/03/18.
 */
public class ResultBuilder {
	private String status;
	private String msg;
	private Stock stock;
	private List<Stock> stocks;

	public ResultBuilder setStatus(String status) {
		this.status = status;
		return this;
	}

	public ResultBuilder setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public ResultBuilder setStock(Stock stock) {
		this.stock = stock;
		return this;
	}

	public ResultBuilder setStocks(List<Stock> stocks) {
		this.stocks = stocks;
		return this;
	}

	public Result createResult() {
		return new Result(status, msg, stock, stocks);
	}
}