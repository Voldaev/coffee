package Test.coffee.service;

import Test.coffee.dto.IngridDTO;
import Test.coffee.dto.RecipeDTO;
import Test.coffee.model.IngridEntity;
import Test.coffee.model.RecipeEntity;
import Test.coffee.repository.IngridEntityRepository;
import Test.coffee.repository.RecipeEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RecipeService {

    private final RecipeEntityRepository recipeEntityRepository;
    private final IngridEntityRepository ingridEntityRepository;

    public RecipeService(RecipeEntityRepository recipeEntityRepository, IngridEntityRepository ingridEntityRepository) {
        this.recipeEntityRepository = recipeEntityRepository;
        this.ingridEntityRepository = ingridEntityRepository;
    }

    public void createRecipe(RecipeDTO recipeDTO) {
        RecipeEntity entity = new RecipeEntity();
        entity.setName(recipeDTO.getName());
        entity.setDuration(recipeDTO.getDuration());
        recipeEntityRepository.save(entity);
        for (IngridDTO ingrid: recipeDTO.getIngrs()) {
            IngridEntity ing = new IngridEntity();
            ing.setName(ingrid.getName());
            ing.setRequired(ingrid.getCount());
            ing.setRecipeEntity(entity);
            ingridEntityRepository.save(ing);
        }
    }

    public List<RecipeDTO> show() {
        ArrayList<RecipeEntity> list = new ArrayList<>(recipeEntityRepository.findAll());
        ArrayList<RecipeDTO> result = new ArrayList<>();
        for (RecipeEntity entity : list) {
            Set<IngridDTO> ingr = new HashSet<>();
            for (IngridEntity ie : entity.getIngrid()) {
                ingr.add( IngridDTO.builder().name(ie.getName()).count(ie.getRequired()).build());
            }
            result.add( RecipeDTO.builder().id(entity.getId()).name(entity.getName()).duration(entity.getDuration()).ingrs(ingr).build());
        }

        return result;
    }

    public void delete(Integer id) {
        recipeEntityRepository.deleteById(id);
    }
}
