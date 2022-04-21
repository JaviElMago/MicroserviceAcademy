package javier.academy.store.repository;

import javier.academy.store.model.Category;
import javier.academy.store.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@DataJpaTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepoUnderTest;


    @Test
    public void whenFindByCategoryName_thenReturnProductList(){

//        List<Category> cats = new ArrayList<>();
//        cats.add(new Category("pcs"));
//        Product prod = new Product("Laptop", "laptop generica", 149d, 999.99, "active", LocalDate.now(),
//                cats);
//
//        productRepoUnderTest.save(prod);

        List<Product> retrieved = productRepoUnderTest.findProductsByCategoryName("cat01");

        Assertions.assertThat(retrieved.size()).isEqualTo(6);

    }

    @Test
    public void whenFindByCategoryId_thenReturnProductList(){

//        List<Category> cats = new ArrayList<>();
//        cats.add(new Category("pcs"));
//        Product prod = new Product("Laptop", "laptop generica", 149d, 999.99, "active", LocalDate.now(),
//                cats);
//
//        productRepoUnderTest.save(prod);

        List<Product> retrieved = productRepoUnderTest.findProductsByCategoryId(1l);

        Assertions.assertThat(retrieved.size()).isEqualTo(6);

    }

}