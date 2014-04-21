/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mockExample.jmock;
import java.math.BigDecimal;
import java.math.MathContext;
import static javax.swing.Spring.scale;

/**
 *
 * @author atul
 */
public class PricingServiceImpl implements PricingService{

    private DataAccess dataAccess;
    @Override
    public void setDataAccess(DataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public BigDecimal getPrice(String sku) throws SkuNotFoundException {
        final BigDecimal price = this.dataAccess.getPriceBySku(sku);
         if (price == null) {
             throw new SkuNotFoundException();
         }
         return price.add(price);
    }
    
}
