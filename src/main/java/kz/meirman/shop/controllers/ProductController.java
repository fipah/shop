package kz.meirman.shop.controllers;

import kz.meirman.shop.entity.Product;
import kz.meirman.shop.services.FileService;
import kz.meirman.shop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;
    @GetMapping(value = "/add-product")
    public String openAddProduct(){
        return "add-product";
    }
    @PostMapping(value = "add-product")
    public String addProductPost(@RequestParam(name = "product-name") String name,
                                 @RequestParam(name = "product-price") int price){
        String redirect = "/product/add-product?error";
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        if(productService.addProduct(product) != null){
            redirect = "/product/home";
        }
        return "redirect:" + redirect;
    }
    @GetMapping(value = "/home")
    public String openHome(Model model){
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "home";
    }
    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable("id") Long id, Model model){
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        return "details";
    }
    @PostMapping(value = "/upload-photo")
    public String uploadPhotoPost(@RequestParam(name = "product-photo")MultipartFile file,
                                  @RequestParam(name = "product-id") Long id){
        fileService.uploadPhoto(file, id);
        return "redirect:home";
    }
}
