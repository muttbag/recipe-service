package co.uk.recipe.group.dao;

import co.uk.recipe.group.domain.Recipe;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface RecipesRepository extends CrudRepository<Recipe, String> {

    @Override
    Optional<Recipe> findById(String s);

    @Override
    <S extends Recipe> S save(S s);

    @Override
    Iterable<Recipe> findAll();

    @Override
    void deleteById(String s);
}
