package com.workintech.s18d2.services;

import com.workintech.s18d2.exceptions.PlantException;
import com.workintech.s18d2.repository.FruitRepository;
import com.workintech.s18d2.entity.Fruit;
import com.workintech.s18d2.validations.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class FruitServiceImpl implements FruitService{
    private final FruitRepository fruitRepository;

    @Autowired
    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public List<Fruit> getByPriceAsc() {
        return fruitRepository.getByPriceAsc();
    }

    @Override
    public List<Fruit> getByPriceDesc() {
        return fruitRepository.getByPriceDesc();
    }

    @Override
    public List<Fruit> searchByName(String name) {
        return fruitRepository.searchByName(name);
    }

    @Override
    public Fruit getById(Long id) {
        return fruitRepository.findById(id)
                .orElseThrow(() -> new PlantException("Ürün bulunamadı! ID: " + id, HttpStatus.NOT_FOUND));
    }

    @Override
    public Fruit save(Fruit fruit) {
        return fruitRepository.save(fruit); // Eğer ID null ise, JPA otomatik olarak yeni bir ID atar.
    }

    @Override
    public Fruit delete(Long id) {
        Validation.checkId(id); // ID'nin negatif veya 0 olmamasını kontrol et

        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new PlantException("Ürün bulunamadı! ID: " + id, HttpStatus.NOT_FOUND));

        try {
            fruitRepository.deleteById(id);
        } catch (Exception e) {
            throw new PlantException("Ürün silinirken bir hata oluştu! ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return fruit;
    }

}
