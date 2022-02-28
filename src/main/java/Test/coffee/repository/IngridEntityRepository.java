package Test.coffee.repository;

import Test.coffee.model.IngridEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngridEntityRepository extends JpaRepository<IngridEntity, String> {
}