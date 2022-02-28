package Test.coffee.service;

import Test.coffee.dto.CoffeeDTO;
import Test.coffee.model.CoffeeEntity;
import Test.coffee.model.IngridEntity;
import Test.coffee.model.RecipeEntity;
import Test.coffee.model.StorageEntity;
import Test.coffee.repository.CoffeeEntityRepository;
import Test.coffee.repository.RecipeEntityRepository;
import Test.coffee.repository.StorageEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CoffeeService {

    private final CoffeeEntityRepository coffeeEntityRepository;
    private final StorageEntityRepository storageEntityRepository;
    private final RecipeEntityRepository recipeEntityRepository;

    public CoffeeService(CoffeeEntityRepository coffeeEntityRepository, StorageEntityRepository storageEntityRepository, RecipeEntityRepository recipeEntityRepository) {
        this.coffeeEntityRepository = coffeeEntityRepository;
        this.storageEntityRepository = storageEntityRepository;
        this.recipeEntityRepository = recipeEntityRepository;
    }

    public void make(Integer id) {
        RecipeEntity recipe = recipeEntityRepository.getById(id);
        for (IngridEntity ie: recipe.getIngrid()) {
            StorageEntity storedIngrid = storageEntityRepository.getById(ie.getName());
            storedIngrid.setSaved(storedIngrid.getSaved()-ie.getRequired()); // транзакция должна спасти
            if (storedIngrid.getSaved()<0) {
                throw new RuntimeException("не получится сварить, не хватает " + ie.getName());
            } else {
                storageEntityRepository.save(storedIngrid);
            }
        }
        if (coffeeEntityRepository.interfered().size()>0)
            throw new RuntimeException("кофеварка уже варит кофе, подождите");

        CoffeeEntity coffee = new CoffeeEntity();
        coffee.setName(recipe.getName());
        coffee.setReadytime(LocalDateTime.now().plusMinutes(recipe.getDuration()));
        coffeeEntityRepository.save(coffee);
    }

    public List<CoffeeDTO> stats() {
        ArrayList<CoffeeDTO> result = new ArrayList<>();
        ArrayList<CoffeeEntity> coffee = new ArrayList<>(coffeeEntityRepository.findAll());
        for (CoffeeEntity cup: coffee) {
            result.add( CoffeeDTO.builder().id(cup.getId()).name(cup.getName()).ready(cup.getReadytime()).build());
        }
        return result;
    }
}
