package com.workintech.s18d2.validations;

import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import org.springframework.http.HttpStatus;

public class Validation {
    public static void checkFruit(Fruit fruit) {
        if (fruit == null || fruit.getName() == null || fruit.getName().trim().isEmpty() || fruit.getPrice() <= 0) {
            throw new PlantException("Eksik veya hatalı veri! İsim boş olamaz, fiyat 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkVegetable(Vegetable vegetable){
        if(vegetable == null || vegetable.getName() == null || vegetable.getName().trim().isEmpty() || vegetable.getPrice() <= 0) {
            throw new PlantException("Eksik veya hatalı data: Name boş olamaz, fiyat 0'dan büyük olmalıdır.", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkId(Long id) {
        if (id == null || id <= 0) {
            throw new PlantException("Geçersiz ID: " + id, HttpStatus.BAD_REQUEST);
        }
    }
}
