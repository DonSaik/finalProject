/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.bmi;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author donatas
 */
public class BMI {
    private double height;
    private double weight;
    private double bmiIndex = -1;
    private static final double indexCategoryMatrix [][] = {
        {0, 15},
        {15, 16},
        {16, 18.5},
        {18.5, 25},
        {25, 30},
        {30, Double.POSITIVE_INFINITY}
    };
    public BMI(){};
    
    public BMI ( double weight, double height) throws Exception{
        this.height=height;
        this.weight=weight;
        try {
            bmiIndex = bmiIndexCalculator(height, weight);
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static double bmiIndexCalculator(double height, double weight) throws Exception{
        double index = weight / (height * height);
        if (index <0 || index >100) throw new Exception("Error. Bad height or weight");
        else return index;
    }
    
    
    public String getCategoryName() throws Exception{
        try{
             bmiIndex= bmiIndexCalculator(height, weight);
        }
        catch (Exception e) {
            throw e;
        }
        
        if (bmiIndex >= indexCategoryMatrix[0][0] && bmiIndex < indexCategoryMatrix[0][1] )
            return "Very severely underweight";
        else if (bmiIndex >= indexCategoryMatrix[1][0] && bmiIndex < indexCategoryMatrix[1][1])
            return "Severely underweight";
        else if (bmiIndex >= indexCategoryMatrix[2][0] && bmiIndex < indexCategoryMatrix[2][1])
            return "Underwight";
        else if (bmiIndex >= indexCategoryMatrix[3][0] && bmiIndex < indexCategoryMatrix[3][1])
            return "Normal (healthy weight)";
        else if (bmiIndex >= indexCategoryMatrix[4][0] && bmiIndex < indexCategoryMatrix[4][1])
            return "Overweight";
        else if (bmiIndex >= indexCategoryMatrix[5][0] && bmiIndex < indexCategoryMatrix[5][1])
            return "Obese";
   
        return null;
    }

    public double getBmiIndex() {
        return bmiIndex;
    }
}
