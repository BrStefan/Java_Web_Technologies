package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Food;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FoodRepository {

    private List<Food> foodList;

    private Food add(Food food){
        foodList.add(food);
        return food;
    }

    private List<Food> getFoodList(){
        return foodList;
    }

    private Food delete(Food food){
        foodList.remove(food);
        return food;
    }
}
