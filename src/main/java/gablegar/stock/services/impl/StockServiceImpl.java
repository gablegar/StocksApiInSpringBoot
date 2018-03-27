package gablegar.stock.services.impl;

import gablegar.stock.controllers.StockController;
import gablegar.stock.models.ResultBuilder;
import gablegar.stock.database.StockRepository;
import gablegar.stock.models.Result;
import gablegar.stock.database.entities.Stock;
import gablegar.stock.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static gablegar.stock.constants.StockConstants.*;

/**
 * Created by glegarda on 13/03/18.
 * This is the main service of the API, here are the main operations and logic to manipulate the Stocks
 */
@Service
public class StockServiceImpl implements StockService {

	private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

	@Autowired
	private StockRepository stockRepository;

	public Result getAllStock() {

		Iterable<Stock> stocks = stockRepository.findAll();

		if(stocks == null) {
			return new ResultBuilder().setStatus(NOT_FOUND).setMsg(NOT_AVAILABLE_STOCKS).createResult();
		}

		List<Stock> allStock = new ArrayList<>();
		stocks.forEach(allStock::add);
		return new ResultBuilder().setStatus(SUCCESSFUL).setStocks(allStock).createResult();

	}

	public Result getOneStock(Long id) {

		Stock retrievedStock = stockRepository.findOne(id);

		if(retrievedStock == null) { return resultNotFound(id); }

		return new ResultBuilder().setStatus(SUCCESSFUL).setStock(retrievedStock).createResult();
	}

	public Result updateOneStock(Long id, double price) {

		Stock targetStock = stockRepository.findOne(id);

		if(targetStock == null) { return resultNotFound(id); }

		targetStock.setCurrentPrice(price);
		Stock updatedStock = stockRepository.save(targetStock);

		if (updatedStock == null) { return resultNotFound(id); }

		return new ResultBuilder().setStatus(SUCCESSFUL).setMsg(constructMessage(id, UPDATED)).createResult();
	}

	public Result createOneStock(Stock stock) {
		Stock resultStock = stockRepository.save(stock);
		if(resultStock == null) {
			return new ResultBuilder().setStatus(ERROR).setMsg(constructMessage(stock.getId(), NOT_CREATED)).createResult();
		}
		return new ResultBuilder().setStatus(SUCCESSFUL).setMsg(constructMessage(stock.getId(), CREATED)).createResult();
	}

	private Result resultNotFound(Long id) {
		LOG.debug("id of stock not found {}", id);
		return new ResultBuilder().setStatus(NOT_FOUND).setMsg(constructMessage(id, NOT_FOUND)).createResult();
	}

	private String constructMessage(Long id, String endingPart) {
		return new StringBuffer(INITIAL_MSG)
					.append(id).append(" ")
					.append(endingPart).toString();
	}
}
