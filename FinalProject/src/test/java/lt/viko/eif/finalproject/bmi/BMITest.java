/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.bmi;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author laimis
 */
public class BMITest {

    /**
     * Test of bmiIndexCalculator method, of class BMI.
     * @throws java.lang.Exception
     */
    @Test
    public void testBmiIndexCalculator() throws Exception {
        System.out.println("bmiIndexCalculator");
        double height = 1.6;
        double weight = 50;
        double expResult = 19.5;
        double result = BMI.bmiIndexCalculator(height, weight);
        assertEquals(expResult, result, 0.1);
    }

    /**
     * Test of getCategoryName method, of class BMI.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCategoryName() throws Exception {
        System.out.println("getCategoryName");
        BMI instance = new BMI(50,1.6);
        String expResult = "Normal (healthy weight)";
        String result = instance.getCategoryName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBmiIndex method, of class BMI.
     */
    @Test
    public void testGetBmiIndex() {
        System.out.println("getBmiIndex");
        BMI instance = new BMI();
        double expResult = -1;
        double result = instance.getBmiIndex();
        assertEquals(expResult, result, 0.0);
    }
    
}
