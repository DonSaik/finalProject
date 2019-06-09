/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.bmi;

/**
 *
 * @author donatas
 */
public class BMI {
    private double height;
    private double weight;
    private static final double indexCategoryMatrix [][] = {
        {0, 15},
        {15, 16},
        {16, 18.5},
        {18.5, 25},
        {25, 30},
        {30, 35},
        {35, 40},
        {40, 45},
        {45, 50},
        {50, 60},
        {60, Double.POSITIVE_INFINITY}
    };
    public BMI(){};
    
    public BMI ( double weight, double height){
        this.height=height;
        this.weight=weight;
    }
    
    public static double bmiIndexCalculator(double height, double weight) throws Exception{
        double index = weight / (height * height);
        if (index <0 || index >100) throw new Exception("Error. Bad height or weight");
        else return index;
    }
    
    
    public String getCategoryName() throws Exception{
        double bmiIndex = -1;
        try{
             bmiIndex= bmiIndexCalculator(height, weight);
        }
        catch (Exception e) {
            throw e;
        }
        
        if (bmiIndex >= indexCategoryMatrix[0][0] && bmiIndex < indexCategoryMatrix[0][1] )
            return bmiIndex + " " +indexCategoryMatrix[0][0]+"Very severely underweight"+ indexCategoryMatrix[0][1];
        else if (bmiIndex >= indexCategoryMatrix[1][0] && bmiIndex < indexCategoryMatrix[1][1])
            return "Severely underweight";
        else if (bmiIndex >= indexCategoryMatrix[2][0] && bmiIndex < indexCategoryMatrix[2][1])
            return "Underwight";
        else if (bmiIndex >= indexCategoryMatrix[3][0] && bmiIndex < indexCategoryMatrix[3][1])
            return "Normal (healthy weight)";
        else if (bmiIndex >= indexCategoryMatrix[4][0] && bmiIndex < indexCategoryMatrix[4][1])
            return "Overweight";
        else if (bmiIndex >= indexCategoryMatrix[5][0] && bmiIndex < indexCategoryMatrix[5][1])
            return "Obese Class I (Moderately obese)";
        else if (bmiIndex >= indexCategoryMatrix[6][0] && bmiIndex < indexCategoryMatrix[6][1])
            return "Obese Class II (Severely obese)";
        else if (bmiIndex >= indexCategoryMatrix[7][0] && bmiIndex < indexCategoryMatrix[7][1])
            return "Obese Class III (Very severely obese)";
        else if (bmiIndex >= indexCategoryMatrix[8][0] && bmiIndex < indexCategoryMatrix[8][1])
            return "Obese Class IV (Morbidly Obese)";
        else if (bmiIndex >= indexCategoryMatrix[9][0] && bmiIndex < indexCategoryMatrix[9][1])
            return "Obese Class V (Super Obese)";
        else if (bmiIndex >= indexCategoryMatrix[10][0] && bmiIndex < indexCategoryMatrix[10][1])
            return "Obese Class VI (Hyper Obese)";       
        return null;
    }
}
