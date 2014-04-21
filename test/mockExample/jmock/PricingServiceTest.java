/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mockExample.jmock;

import java.math.BigDecimal;
import junit.framework.Assert;
import org.jmock.Expectations;
import static org.jmock.Expectations.returnValue;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;

/**
 *
 * @author atul
 */
public class PricingServiceTest {

    public PricingServiceTest() {
    }
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();

    private static final String SKU = "3283947";
    private static final String BAD_SKU = "-9999993434";

    private PricingService systemUnderTest;
    private DataAccess mockedDependency;
    private Mockery mockingContext;
    
    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mockingContext = new JUnit4Mockery();
        mockedDependency = mockingContext.mock(DataAccess.class);
        systemUnderTest = new mockExample.jmock.PricingServiceImpl();
        systemUnderTest.setDataAccess(mockedDependency);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getPrice method, of class PricingService.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetPrice() throws Exception {
        mockingContext.checking(new Expectations() {
            {
                oneOf(mockedDependency).getPriceBySku(SKU);
                will(returnValue(new BigDecimal(200)));
            }
        });
        final BigDecimal testPrice = systemUnderTest.getPrice(SKU);
        Assert.assertEquals(SKU, new BigDecimal(400), testPrice);
    }

    @Test(expected = SkuNotFoundException.class)
    public void getPriceNonExistentSkuThrowsException() throws SkuNotFoundException {
        mockingContext.checking(new Expectations() {
            {
                oneOf(mockedDependency).getPriceBySku(BAD_SKU);
                will(returnValue(null));
            }
        });
        final BigDecimal price = systemUnderTest.getPrice(BAD_SKU);
    }

    @Test(expected = SkuNotFoundException.class)
    public void getPriceDataAccessThrowsRuntimeException() throws SkuNotFoundException {
        mockingContext.checking(new Expectations() {
            {
                oneOf(mockedDependency).getPriceBySku(with(any(String.class)));
                will(throwException(new RuntimeException("Fatal data access exception.")));
            }
        });
        final BigDecimal price = systemUnderTest.getPrice(SKU);
    }
    
     /**
     * Test of setDataAccess method, of class PricingService.
     */
    @Test
    public void testSetDataAccess() {
        System.out.println("setDataAccess");
        DataAccess dataAccess = null;
        PricingService instance = new PricingServiceImpl();
        instance.setDataAccess(dataAccess);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
