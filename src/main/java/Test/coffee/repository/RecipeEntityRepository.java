package Test.coffee.repository;

import Test.coffee.model.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeEntityRepository extends JpaRepository<RecipeEntity, Integer> {
}