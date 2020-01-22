package guru.springframework.services;

import guru.springframework.domain.Category;
import guru.springframework.repositories.CategoryRepository;

import java.util.Optional;

public class CategoryMapService implements CategoryRepository {
    @Override
    public Optional<Category> findByDescription(String description) {
        return Optional.empty();
    }

    @Override
    public <S extends Category> S save(S s) {
        return null;
    }

    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Category> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Category> findAll() {
        return null;
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Category category) {

    }

    @Override
    public void deleteAll(Iterable<? extends Category> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
