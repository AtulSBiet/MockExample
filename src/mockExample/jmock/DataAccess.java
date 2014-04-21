/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mockExample.jmock;
import java.math.BigDecimal;

/**
 *
 * @author atul
 */
public interface DataAccess {
    BigDecimal getPriceBySku(String sku);
}

