package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;

import java.util.List;

public interface VegetableService {
    List<Vegetable> vegetableOrderPriceAsc();
    List<Vegetable> vegetableOrderPriceDesc();
    List<Vegetable> vegetableName(String name);
    Vegetable findById(Long id);
    Vegetable save(Vegetable vegetable);
    Vegetable delete(Long id);
}
