package gablegar.stock.controllers;


import gablegar.stock.models.Result;
import gablegar.stock.database.entities.Stock;
import gablegar.stock.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static gablegar.stock.constants.StockConstants.ERROR;
import static gablegar.stock.constants.StockConstants.NOT_FOUND;
import static gablegar.stock.constants.StockConstants.SUCCESSFUL;

/**
 * Created by glegarda on 13/03/18.
 * The main controller of the API all the REST operations for stock are here
 */
@RestController
@RequestMapping("api")
public class StockController {

	private static final Logger LOG = LoggerFactory.getLogger(StockController.class);

	@Autowired
	StockService stockService;

	@RequestMapping(value = "stocks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Result> getAllStock(){
		return getResult(stockService.getAllStock());
	}

	@RequestMapping(value = "stocks/{stockId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Result> getOneStock(@Valid @PathVariable long stockId){
		return getResult(stockService.getOneStock(stockId));
	}

	@RequestMapping(value = "stocks/{stockId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Result> updateOneStock(@Valid @PathVariable long stockId, @Valid @RequestBody Stock stock){
		return getResult(stockService.updateOneStock(stockId, stock.getCurrentPrice()));
	}

	@RequestMapping(value = "stocks", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Result> createOneStock(@Valid @RequestBody Stock stock){
		return getResult(stockService.createOneStock(stock));
	}

	private ResponseEntity<Result> getResult(Result result) {
		return new ResponseEntity<>(result, getHttpStatus(result));
	}

	private HttpStatus getHttpStatus(Result result){
		String status = result.getStatus();
		if(status.equals(SUCCESSFUL)) {
			LOG.debug("Request successful, status : {} message: {}",result.getStatus(),result.getMessage());
			return HttpStatus.OK;
		} else if (status.equals(ERROR)) {
			LOG.error("An error was found, status : {} message: {}",result.getStatus(),result.getMessage());
			return  HttpStatus.INTERNAL_SERVER_ERROR;
		} else if (status.equals(NOT_FOUND)) {
			LOG.debug("Request object not found, status : {} message: {}",result.getStatus(),result.getMessage());
			return HttpStatus.NOT_FOUND;
		} else {
			LOG.error("Bad reques to the api, status : {} message: {}",result.getStatus(),result.getMessage());
			return HttpStatus.BAD_REQUEST;
		}
	}
}
