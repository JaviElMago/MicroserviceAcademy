package javier.academy.store.service;

import javier.academy.store.model.Product;
import javier.academy.store.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

//    @Autowired
    private final ProductRepo productRepo;

    public List<Product> retrieveAllProducts(){
        return productRepo.findAll();
    }

    public Optional retrieveProductById(Long prodId){
        return productRepo.findById(prodId);
    }

    public Product saveNewProduct (Product product) {return productRepo.save(product);}

    public void deleteExistingProduct (Product product) { productRepo.delete(product);}

    public List<Product> retrieveProductByCategoryId(Long catId){
        return productRepo.findProductsByCategoryId(catId);
    }

    public List<Product> retrieveProductByCategoryName(String catName){
        return productRepo.findProductsByCategoryName(catName);
    }

}
