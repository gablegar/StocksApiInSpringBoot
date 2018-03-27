package gablegar.stock.database;

import gablegar.stock.database.entities.Stock;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by glegarda on 13/03/18.
 * This interface is used for the interaction with db using CRUD operations for Stock entity
 */
public interface StockRepository extends CrudRepository<Stock, Long> {
}
