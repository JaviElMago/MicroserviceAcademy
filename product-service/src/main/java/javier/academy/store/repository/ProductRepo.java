package javier.academy.store.repository;

import javier.academy.store.model.Category;
import javier.academy.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

//    @Query("SELECT p FROM Product p WHERE p.categories.name = ?1")
    @Query("SELECT p FROM Product p join p.categories c WHERE c.name = ?1")
    public List<Product> findProductsByCategoryName(String name);

    @Query("SELECT p FROM Product p join p.categories c WHERE c.id = ?1")
    public List<Product> findProductsByCategoryId(Long name);

}
