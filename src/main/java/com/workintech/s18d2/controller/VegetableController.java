package com.workintech.s18d2.controller;

import com.workintech.s18d2.dto.SuccessResponse;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.services.VegetableService;
import com.workintech.s18d2.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/vegetables")
public class VegetableController {
    private VegetableService vegetableService;

    @Autowired
    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    @GetMapping
    public List<Vegetable> vegetableOrderPriceAsc(){
        return vegetableService.vegetableOrderPriceAsc();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vegetable> findById(@PathVariable Long id){
        Vegetable vegetable = vegetableService.findById(id);
        return ResponseEntity.ok(vegetable);
    }

    @GetMapping("/desc")
    public List<Vegetable> vegetableOrderPriceDesc(){
        return vegetableService.vegetableOrderPriceDesc();
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<Vegetable>> save(@RequestBody Vegetable vegetable) {
        Validation.checkVegetable(vegetable);
        Vegetable savedVegetable = vegetableService.save(vegetable);
        return ResponseEntity.ok(new SuccessResponse<>("Sebze başarıyla kaydedildi!", savedVegetable));
    }

    @PostMapping("/{name}")
    public List<Vegetable> vegetableName(@PathVariable String name){
        return vegetableService.vegetableName(name);
    }

    @DeleteMapping("/{id}")
    public Vegetable delete(@PathVariable Long id){
        return vegetableService.delete(id);
    }
}
