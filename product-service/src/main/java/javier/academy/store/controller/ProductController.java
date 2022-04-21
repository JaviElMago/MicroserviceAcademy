package javier.academy.store.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javier.academy.store.model.Product;
import javier.academy.store.service.ProductService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = productService.retrieveAllProducts();
        if(!products.isEmpty()){
            return ResponseEntity.ok(products);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long prodId){
        Optional<Product> product = productService.retrieveProductById(prodId);
        if(!product.isEmpty()){
            return ResponseEntity.ok(product.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addNewProduct(@Valid @RequestBody Product newProd, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorsMessageFormatter(bindingResult));
        }

        Product product = productService.saveNewProduct(newProd);
        if(null != product){
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cat/{catName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable String catName){
        List<Product> products = productService.retrieveProductByCategoryName(catName);
        if(!products.isEmpty()){
            return ResponseEntity.ok(products);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    private String errorsMessageFormatter(BindingResult result){

        List<Map<String, String>> errors = result.getFieldErrors().stream().map(er -> {
            Map<String, String> error = new HashMap<>();
            error.put(er.getField(), er.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());

        ErrorMessage errorMessage = ErrorMessage.builder().code("01").errors(errors).build();
        ObjectMapper mapper = new ObjectMapper();

        String stringErrors = "";

        try {
            stringErrors = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return  stringErrors;
    }

}
