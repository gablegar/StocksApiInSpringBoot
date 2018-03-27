package gablegar.stock.models;

import gablegar.stock.database.entities.Stock;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultBuilderTest {

	@Test
	public void testCreateSimpleResult() {
		//given a simple result
		ResultBuilder resultBuilder = new ResultBuilder();
		Result expected = new Result("Status", "Message", null, null);

		//when building the result using the builder
		Result result = resultBuilder.setStatus("Status").setMsg("Message").createResult();

		//then
		assertEquals(expected, result);
	}

	@Test
	public void testCreateOneStockResult() {
		//given a result with stock
		ResultBuilder resultBuilder = new ResultBuilder();
		Stock testStock = new Stock(3l, "stock", 55.0, 1000, "description");
		Result expected = new Result("Status", "Message", testStock, null);

		//when building the result using the builder
		Result result = resultBuilder.setStatus("Status").setMsg("Message").setStock(testStock).createResult();

		//then
		assertEquals(expected, result);
	}

	@Test
	public void testCreateAllStockResult() {
		//given a result with all the stock
		ResultBuilder resultBuilder = new ResultBuilder();
		List<Stock> stocks = Arrays.asList(new Stock(3l, "stock", 55.0, 1000, "description"));
		Result expected = new Result("Status", "Message", null, stocks);

		//when building the result using the builder
		Result result = resultBuilder.setStatus("Status").setMsg("Message").setStocks(stocks).createResult();

		//then
		assertEquals(expected, result);
	}

}