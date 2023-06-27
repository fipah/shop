package kz.meirman.shop.services;

import kz.meirman.shop.entity.Product;

import java.util.List;

public interface ProductService {
    Product addProduct(Product product);
    List<Product> getAllProducts();
    Product getProduct(Long id);
}
