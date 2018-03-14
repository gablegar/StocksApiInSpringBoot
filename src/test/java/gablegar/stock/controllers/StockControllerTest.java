package gablegar.stock.controllers;

import gablegar.stock.models.ResultBuilder;
import gablegar.stock.database.entities.StockBuilder;
import gablegar.stock.models.Result;
import gablegar.stock.database.entities.Stock;
import gablegar.stock.services.StockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static gablegar.stock.constants.StockConstants.ERROR;
import static gablegar.stock.constants.StockConstants.NOT_FOUND;
import static gablegar.stock.constants.StockConstants.SUCCESSFUL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Created by glegarda on 13/03/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class StockControllerTest {

	private MockMvc mvc;

	@Mock
	StockService stockService;

	@InjectMocks
	private StockController stockController;

	@Before
	public void init() {
		mvc = MockMvcBuilders.standaloneSetup(stockController).dispatchOptions(true).build();
	}

	@Test
	public void testGetAllStock() throws Exception{
		//given that there is stock on db

		//when requesting it to the service
		when(stockService.getAllStock()).thenReturn(getMockedAllStockResponse());
		MvcResult result = mvc.perform(get("/api/stocks"))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();

		//then the answer will contain all the stock data and status will be ok
		Assert.assertTrue(responseBody.contains(getTestString(getMockedOneStock())));
		Assert.assertTrue(responseBody.contains(getTestString(getMockedSecondStock())));
		assertEquals(getHttpStatus(result), HttpStatus.OK);
	}

	@Test
	public void testGetOneStock() throws Exception{
		//given one stock in db

		//when requesting to the service
		when(stockService.getOneStock(1L)).thenReturn(getMockedOneStockResponse());
		MvcResult result = mvc.perform(get("/api/stocks/1", 1L))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();

		//then the stock will come in the answer and status will be ok
		Assert.assertTrue(responseBody.contains(getTestString(getMockedOneStock())));
		assertEquals(getHttpStatus(result), HttpStatus.OK);
	}

	@Test
	public void testPutOneStockPrice() throws Exception{
		//given one stock request to update
		Stock stock = new StockBuilder().setId(1L).setCurrentPrice(22.0).createStock();


		//when calling the service if answer is ok
		when(stockService.updateOneStock(eq(1L), eq(22.0))).thenReturn(getMockedPutStockResponse());
		MvcResult result = mvc.perform(put("/api/stocks/1").contentType(MediaType.APPLICATION_JSON_VALUE)
								.content(stock.toString()))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();

		//then the status will be ok with the corresponding message
		Assert.assertTrue(responseBody.contains(getMockedPutStockResponse().toString()));
		assertEquals(getHttpStatus(result), HttpStatus.OK);
	}

	@Test
	public void testCreateOneStock() throws Exception{
		//given a request to create one stock
		Stock stock = new StockBuilder().setId(1L).setCurrentPrice(22.0)
							.setQuantity(1000).setDescription("test description")
							.setName("test name").createStock();

		//when calling the services, if the answer is successful
		when(stockService.createOneStock(eq(stock))).thenReturn(getMockedCreateStockResponse());
		MvcResult result = mvc.perform(post("/api/stocks")
								.contentType(MediaType.APPLICATION_JSON_VALUE).content(stock.toString()))
								.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		final String responseBody = result.getResponse().getContentAsString();

		//then the status will be ok with the corresponding message
		Assert.assertTrue(responseBody.contains(getMockedCreateStockResponse().toString()));
		assertEquals(getHttpStatus(result), HttpStatus.OK);
	}

	@Test
	public void testAllStockNotFound() throws Exception{
		//given that there's no stock in the db


		//when the service is called an not found answer comes
		when(stockService.getAllStock()).thenReturn(getMockedAllStockResponseNotFound());
		MvcResult result = mvc.perform(get("/api/stocks"))
								.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		//then the http status for the response should be not found
		assertEquals(getHttpStatus(result), HttpStatus.NOT_FOUND);
	}

	@Test
	public void testGetOneStockNotFound() throws Exception{
		//given a request for a stock
		Long stockIdRequested = 1L;

		//when calling the service and it is not found
		when(stockService.getOneStock(stockIdRequested)).thenReturn(getMockedOneStockResponseNotFound());
		MvcResult result = mvc.perform(get("/api/stocks/1", 1L))
								.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		//then the http status for the response should be not found
		assertEquals(getHttpStatus(result), HttpStatus.NOT_FOUND);
	}

	@Test
	public void testPutOneStockPriceNotFound() throws Exception{
		//given a request for updating a price
		Long stockIdRequested = 1L;
		Stock stock = new StockBuilder().setId(stockIdRequested).setCurrentPrice(22.0).createStock();

		//when the stock doesn't exist and the service returns not found
		when(stockService.updateOneStock(eq(1L), eq(22.0)))
				.thenReturn(getMockedOneStockResponseNotFound());
		MvcResult result = mvc
				.perform(put("/api/stocks/1")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(stock.toString()))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		//then the http status for the response should be not found
		assertEquals(getHttpStatus(result), HttpStatus.NOT_FOUND);
	}

	@Test
	public void testCreateOneStockError() throws Exception{
		//given a request for stock creation
		Stock stock = new StockBuilder().setId(1L).setCurrentPrice(22.0)
							.setQuantity(234).setDescription("my test description")
							.setName("test name").createStock();

		//when creating a stock and error comes
		when(stockService.createOneStock(eq(stock))).thenReturn(getMockedResponseError());
		MvcResult result = mvc
				.perform(post("/api/stocks")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(stock.toString()))
				.andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();

		//then the http status for the response should be not internal server error
		assertEquals(getHttpStatus(result), HttpStatus.INTERNAL_SERVER_ERROR);
	}



	private HttpStatus getHttpStatus(MvcResult result) {
		return HttpStatus.valueOf(result.getResponse().getStatus());
	}

	private Stock getMockedOneStock() {
		return new StockBuilder().setId(1L)
							.setName("mocked stock")
							.setCurrentPrice(22.22)
							.createStock();
	}

	private Stock getMockedSecondStock() {
		return new StockBuilder().setId(2L)
				.setName("mocked stock second")
				.setCurrentPrice(11.11)
				.createStock();
	}

	private Result getMockedAllStockResponseNotFound() {
		return new ResultBuilder()
						.setStatus(NOT_FOUND)
						.createResult();
	}

	private Result getMockedResponseError() {
		return new ResultBuilder()
				.setStatus(ERROR)
				.createResult();
	}

	private Result getMockedOneStockResponseNotFound() {
		return new ResultBuilder()
				.setStatus(NOT_FOUND).setMsg("Stock with id : 1 not found")
				.createResult();
	}

	private Result getMockedAllStockResponse() {
		List<Stock> list = new ArrayList<>();
		Stock stock = getMockedOneStock();
		Stock stockSecond = getMockedSecondStock();
		list.add(stock);
		list.add(stockSecond);
		return new ResultBuilder().setStocks(list)
				.setStatus(SUCCESSFUL)
				.createResult();
	}

	private Result getMockedOneStockResponse() {
		return new ResultBuilder().setStock(getMockedOneStock())
				.setStatus(SUCCESSFUL)
				.createResult();
	}


	private Result getMockedPutStockResponse() {
		return new ResultBuilder()
				.setMsg("Stock price updated")
				.setStatus(SUCCESSFUL)
				.createResult();
	}

	private Result getMockedCreateStockResponse() {
		return new ResultBuilder()
				.setMsg("Stock created with id : 1")
				.setStatus(SUCCESSFUL)
				.createResult();
	}

	public String getTestString(Stock stock) {
		return  "\"id\":" + stock.getId()
				+ ",\"name\":\"" + stock.getName() + "\""
				+ ",\"currentPrice\":" + stock.getCurrentPrice();
	}
}
