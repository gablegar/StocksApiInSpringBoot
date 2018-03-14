package gablegar.stock.services;

import gablegar.stock.models.Result;
import gablegar.stock.database.entities.Stock;

/**
 * Created by glegarda on 13/03/18.
 * This is the interface for the main service of the API, here are the main operations to manipulate the Stocks
 */
public interface StockService {

	Result getAllStock();

	Result getOneStock(Long id);

	Result updateOneStock(Long id, double price);

	Result createOneStock(Stock stock);
}
