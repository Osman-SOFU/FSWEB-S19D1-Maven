package com.workintech.s18d2.services;

import com.workintech.s18d2.entity.Vegetable;
import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.VegetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class VegetableServiceImpl implements VegetableService {
    private final VegetableRepository vegetableRepository;

    @Autowired
    public VegetableServiceImpl(VegetableRepository vegetableRepository){
        this.vegetableRepository = vegetableRepository;
    }

    @Override
    public List<Vegetable> vegetableOrderPriceAsc() {
        return vegetableRepository.vegetableOrderPriceAsc();
    }

    @Override
    public List<Vegetable> vegetableOrderPriceDesc() {
        return vegetableRepository.vegetableOrderPriceDesc();
    }

    @Override
    public List<Vegetable> vegetableName(String name) {
        return vegetableRepository.vegetableName(name);
    }

    @Override
    public Vegetable findById(Long id) {
        Optional<Vegetable> vegetableProduct = vegetableRepository.findById(id);
        if(vegetableProduct.isPresent()){
            return vegetableProduct.get();
        }
        throw new PlantException("Ürün bulunamadı! ID: " + id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Vegetable save(Vegetable vegetable) {
        return vegetableRepository.save(vegetable);
    }

    @Override
    public Vegetable delete(Long id) {
        Vegetable vegetable = vegetableRepository.findById(id)
                .orElseThrow(() -> new PlantException("Ürün bulunamadı! ID: " + id, HttpStatus.NOT_FOUND));

        vegetableRepository.deleteById(id);
        return vegetable;
    }
}
