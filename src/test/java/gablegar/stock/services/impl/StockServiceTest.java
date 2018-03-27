package gablegar.stock.services.impl;

import gablegar.stock.models.ResultBuilder;
import gablegar.stock.database.entities.StockBuilder;
import gablegar.stock.database.StockRepository;
import gablegar.stock.models.Result;
import gablegar.stock.database.entities.Stock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static gablegar.stock.constants.StockConstants.SUCCESSFUL;
import static gablegar.stock.constants.StockConstants.ERROR;
import static gablegar.stock.constants.StockConstants.NOT_FOUND;
import static org.mockito.Matchers.eq;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by glegarda on 13/03/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class StockServiceTest {

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	StockServiceImpl stockService;

	@Test
	public void getAllStockSuccess() {
		//given that there are stocks in the database
		Result expectedResponse = new ResultBuilder().setStatus(SUCCESSFUL)
										.setStocks(getMockedAllStocks())
										.createResult();

		//when requesting the stocks
		when(stockRepository.findAll()).thenReturn(getMockedAllStocks());
		Result result = stockService.getAllStock();

		//then the result object should contain all the stock info plus successful message
		assertEquals(expectedResponse, result);
	}

	@Test
	public void getAllStockFails() {
		//given no records in database
		Result expectedResponse = new ResultBuilder().setStatus(NOT_FOUND)
										.setMsg("No stocks available")
										.createResult();

		//when requesting the records
		when(stockRepository.findAll()).thenReturn(null);
		Result result = stockService.getAllStock();

		//then result object should have error status and no stocks
		assertEquals(expectedResponse, result);
	}

	@Test
	public void getOneStockSuccess() {
		//given that there are a particular stock in the database
		Result expectedResponse = new ResultBuilder().setStatus(SUCCESSFUL)
										.setStock(getMockedStock())
										.createResult();

		//when requesting this stock
		when(stockRepository.findOne(eq(1L))).thenReturn(getMockedStock());
		Result result = stockService.getOneStock(1L);

		//then the result object should contain the stock info plus successful message
		assertEquals(expectedResponse, result);
	}

	@Test
	public void getOneStockFails() {
		//given no specific stock returned by db

		//when trying to find it
		when(stockRepository.findOne(1L)).thenReturn(null);
		Result result = stockService.getOneStock(1L);

		//then result object should have error status no stock should be present
		assertEquals(getMockedNoStockAvailable(), result);
	}

	@Test
	public void updateOneStockSuccess() {
		//given that there is a stock to update
		Stock mockedStock = getMockedStock();
		Result expectedResponse = new ResultBuilder().setStatus(SUCCESSFUL)
										.setMsg("Stock with id : 1 updated")
										.createResult();

		//when requesting to update the stock
		when(stockRepository.findOne(eq(1L))).thenReturn(mockedStock);
		when(stockRepository.save(eq(mockedStock))).thenReturn(mockedStock);
		Result result = stockService.updateOneStock(1L, 23.45);

		//then the result object should contain all the stock info plus successful message
		assertEquals(expectedResponse, result);
	}

	@Test
	public void updateOneStockFails() {
		//given no specific stock returned by db
		Stock mockedStock = new StockBuilder().setId(1L).setCurrentPrice(45.0).createStock();

		//when trying to update it
		when(stockRepository.save(eq(mockedStock))).thenReturn(null);
		Result result = stockService.updateOneStock(mockedStock.getId(), mockedStock.getCurrentPrice());

		//then result object should have error status no stock should be present
		assertEquals(getMockedNoStockAvailable(), result);
	}

	@Test
	public void createOneStockSuccess() {
		//given that there is a stock to create
		Result expectedResponse = new ResultBuilder().setStatus(SUCCESSFUL)
										.setMsg("Stock with id : 1 created")
										.createResult();

		//when requesting to create the stock
		when(stockRepository.save(eq(getMockedStock()))).thenReturn(getMockedStock());
		Result result = stockService.createOneStock(getMockedStock());

		//then the result object should contain all the stock info plus successful message
		assertEquals(expectedResponse, result);
	}

	@Test
	public void createOneStockFails() {
		//given a stock
		Result expectedResponse = new ResultBuilder().setStatus(ERROR)
										.setMsg("Stock with id : " + 1L + " not created")
										.createResult();
		Stock mockedStock = new StockBuilder().setId(1L).setName("my stock example")
									.setCurrentPrice(55.0).createStock();

		//when trying to created the database fails to create it
		when(stockRepository.save(eq(mockedStock))).thenReturn(null);
		Result result = stockService.createOneStock(mockedStock);

		//then result object should have error status no stock should be present
		assertEquals(expectedResponse, result);
	}

	private Result getMockedNoStockAvailable() {
		return new ResultBuilder().setStatus(NOT_FOUND).setMsg("Stock with id : " + 1L + " not found").createResult();
	}

	private List<Stock> getMockedAllStocks() {
		Stock mockedStock = getMockedStock();
		Stock mockedStock2 = new StockBuilder().setId(2L).setName("my second stock example")
									.setQuantity(1000).setDescription("This is the stock of another product")
									.setCurrentPrice(25.0).createStock();
		Stock mockedStock3 = new StockBuilder().setId(3L).setName("my third stock example")
									.setQuantity(1000).setDescription("This is the stock of machines")
									.setCurrentPrice(35.0).createStock();
		List<Stock> mockedStocks = new ArrayList<>();
		mockedStocks.add(mockedStock);
		mockedStocks.add(mockedStock2);
		mockedStocks.add(mockedStock3);
		return  mockedStocks;
	}

	private Stock getMockedStock() {
		return new StockBuilder().setId(1L).setName("my stock example")
					.setQuantity(1000).setDescription("This is the stock of a product")
					.setCurrentPrice(55.0).createStock();
	}

}
