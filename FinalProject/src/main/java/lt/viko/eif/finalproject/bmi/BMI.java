/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.viko.eif.finalproject.bmi;

import com.google.maps.model.PlaceType;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to work with BMI.
 * @author donatas
 */
public class BMI {
    
    private double height;
    private double weight;
    private double bmiIndex = -1;
    private List <PlaceType> activities;

    public void setActivities(List<PlaceType> activities) {
        this.activities = activities;
    }

    public List<PlaceType> getActivities() {
        return activities;
    }
    
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
    /**
     * Method to calculate body mass index
     * @param height
     * @param weight
     * @return double value of index
     * @throws Exception 
     */
    public static double bmiIndexCalculator(double height, double weight) throws Exception{
        double index = weight / (height * height);
        if (index <5 || index >250) throw new Exception("Error. Bad height or weight");
        else return index;
    }
    
    /**
     * Method to get category by your BMI.
     * @return
     * @throws Exception 
     */
    public String getCategoryName() throws Exception{
        try{
             bmiIndex= bmiIndexCalculator(height, weight);
        }
        catch (Exception e) {
            throw e;
        }
        activities = new ArrayList<>();
        if (bmiIndex >= indexCategoryMatrix[0][0] && bmiIndex < indexCategoryMatrix[0][1] ){
            activities.add(PlaceType.CAFE);
            activities.add(PlaceType.RESTAURANT);
            return "Very severely underweight";}
        else if (bmiIndex >= indexCategoryMatrix[1][0] && bmiIndex < indexCategoryMatrix[1][1]){
            activities.add(PlaceType.CAFE);
            activities.add(PlaceType.RESTAURANT);
            return "Severely underweight";}
        else if (bmiIndex >= indexCategoryMatrix[2][0] && bmiIndex < indexCategoryMatrix[2][1]){
            activities.add(PlaceType.CAFE);
            activities.add(PlaceType.RESTAURANT);
            return "Underwight";}
        else if (bmiIndex >= indexCategoryMatrix[3][0] && bmiIndex < indexCategoryMatrix[3][1]){
            activities.add(PlaceType.AQUARIUM);
            activities.add(PlaceType.STORE);
            return "Normal (healthy weight)";}
        else if (bmiIndex >= indexCategoryMatrix[4][0] && bmiIndex < indexCategoryMatrix[4][1]){
            activities.add(PlaceType.GYM);
            activities.add(PlaceType.BICYCLE_STORE);
            return "Overweight";}
        else if (bmiIndex >= indexCategoryMatrix[5][0] && bmiIndex < indexCategoryMatrix[5][1]){
            activities.add(PlaceType.BICYCLE_STORE);
            activities.add(PlaceType.GYM);
            return "Obese";}
   
        return null;
    }

    public double getBmiIndex() {
        return bmiIndex;
    }
    
}
