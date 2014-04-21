package mockExample.jmock;
import java.math.BigDecimal;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author atul
 */
public interface PricingService {
     void setDataAccess(DataAccess dataAccess);
     BigDecimal getPrice(String sku) throws SkuNotFoundException;
}
