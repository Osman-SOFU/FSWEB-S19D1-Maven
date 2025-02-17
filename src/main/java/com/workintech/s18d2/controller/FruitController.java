package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.SuccessResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.services.FruitService;
import com.workintech.s18d2.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/fruit")
public class FruitController {
    private FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public List<Fruit> fruitOrderPriceAsc(){
        return fruitService.getByPriceAsc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<Fruit>> findById(@PathVariable Long id) {
        Validation.checkId(id);
        Fruit fruit = fruitService.getById(id);
        return ResponseEntity.ok(new SuccessResponse<>("Meyve başarıyla bulundu!", fruit));
    }

    @GetMapping("/desc")
    public List<Fruit> fruitOrderPriceDesc(){
        return fruitService.getByPriceDesc();
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<Fruit>> save(@RequestBody Fruit fruit) {
        Validation.checkFruit(fruit);
        Fruit savedFruit = fruitService.save(fruit);
        return ResponseEntity.ok(new SuccessResponse<>("Meyve başarıyla kaydedildi!", savedFruit));
    }

    @GetMapping("/name/{name}")
    public List<Fruit> fruitName(@PathVariable String name){
        List<Fruit> foundFruit = fruitService.searchByName(name);
        return foundFruit;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<Fruit>> delete(@PathVariable Long id) {
        Validation.checkId(id);
        Fruit deletedFruit = fruitService.delete(id);
        return ResponseEntity.ok(new SuccessResponse<>("Meyve başarıyla silindi!", deletedFruit));
    }
}
