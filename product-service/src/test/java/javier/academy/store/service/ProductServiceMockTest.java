package javier.academy.store.service;

import javier.academy.store.model.Category;
import javier.academy.store.model.Product;
import javier.academy.store.repository.ProductRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceMockTest {

    @Mock
    private ProductRepo productRepo;

//    @Autowired
    private ProductService productServiceUnderTest;

    @BeforeEach
    public void setup(){

        MockitoAnnotations.openMocks(this);
        productServiceUnderTest = new ProductService(productRepo);

        List<Product> prods = initTestProducts();
        Mockito.when(productRepo.findAll()).thenReturn(prods);

        Mockito.when(productRepo.findById(2l)).thenReturn(Optional.ofNullable(prods.get(1)));
    }

    @Test
    void whenRetrieveAllProducts_thenReturnsProductList() {

        List<Product> allProds = productServiceUnderTest.retrieveAllProducts();
        Assertions.assertThat(allProds.size()).isEqualTo(4);

    }

    @Test
    void whenRetrieveProductById_thenReturnsSpecificProduct()
    {
        Optional<Product> prodById = productServiceUnderTest.retrieveProductById(2l);
        Assertions.assertThat(prodById.get()).isNotNull();
        Assertions.assertThat(prodById.get().getName()).isEqualTo("Test prod2");
    }

    private List<Product> initTestProducts(){

        List<Category> cats = new ArrayList<>();
        cats.add(new Category("pcs"));

        List<Product> initialProds = new ArrayList<>();
        initialProds.add(new Product(1l,"Test prod1", "Generic product1 description",
                100d, 1350d, "active", LocalDate.now(), cats));
        initialProds.add(new Product(2l,"Test prod2", "Generic product1 description",
                150d, 1350d, "active", LocalDate.now(), cats));
        initialProds.add(new Product(3l,"Test prod3", "Generic product1 description",
                130d, 1350d, "active", LocalDate.now(), cats));
        initialProds.add(new Product(4l,"Test prod4", "Generic product1 description",
                100d, 1350d, "active", LocalDate.now(), cats));

        return initialProds;
    }
}