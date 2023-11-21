package com.guess.foodback.controllers;



import com.guess.foodback.entities.Food;
import com.guess.foodback.entities.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping({""})
public class FoodController {

    private final FoodRepository foodRepository;



    @Autowired
    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }



    @GetMapping("api/v1/auth/foods")
    public List<Food> getAllFoods() {

        return (List<Food>) foodRepository.findAll();
    }

    @GetMapping ("foods/tag/{tag}")
        public List<Food>getAllFoodsByTag(@PathVariable String tag){
            return "All".equalsIgnoreCase(tag)?
                    getAllFoods():
                    getAllFoods().stream()
                            .filter(food->food.getTags()!=null && food.getTags().contains(tag))
                            .collect(Collectors.toList());
        }
    @GetMapping ("foods/search/{searchTerm}")
    public List<Food>getAllFoodsBySearchTerm(@PathVariable String searchTerm){
        return getAllFoods().stream()
                        .filter(food->food.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                        .collect(Collectors.toList());
    }


    @PostMapping("/foods/add-food")
    Food createFood(@Valid @RequestBody Food food) {

        return foodRepository.save(food);

    }

    @PatchMapping("foods/update/{foodId}")
    public Food updateFood(
            @PathVariable (value = "foodId") Long foodId,
            @Valid @RequestBody Food foodRequest) {


        return foodRepository.findById(foodId).map(food -> {
            food.setName(foodRequest.getName());
            food.setCookTime(foodRequest.getCookTime());
            food.setPrice(foodRequest.getPrice());
            food.setFavorite(foodRequest.getFavorite());
            food.setOrigins(foodRequest.getOrigins());
            food.setStars(foodRequest.getStars());
            food.setImageUrl(foodRequest.getImageUrl());
            food.setTags(foodRequest.getTags());
            return foodRepository.save(food);
        }).orElseThrow(() -> new IllegalArgumentException("FoodId " + foodId + "not found"));
    }

    @GetMapping("foods/food/{foodId}")
    public Food getFood(@PathVariable Long foodId) {

        Optional<Food> p = foodRepository.findById(foodId);
        return p.get();
    }

    @DeleteMapping("foods/delete/{foodId}")
    public Food deleteFood(@PathVariable (value = "foodId") Long foodId) {
        return foodRepository.findById(foodId).map(food -> {
            foodRepository.delete(food);
            return food;
        }).orElseThrow(() -> new IllegalArgumentException("Food not found with id " + foodId));
    }
}


