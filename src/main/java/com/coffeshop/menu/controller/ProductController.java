package com.coffeshop.menu.controller;


import com.coffeshop.menu.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/products")    // This means all URLs start with http://localhost:8080/products/
public class ProductController {

    private List<Product> productList = List.of(
            new Product(1, "Espresso", 2.50),
            new Product(2, "Cappuccino", 3.00),
            new Product(3, "Latte", 3.50),
            new Product(4, "Mocha", 4.00)
    );

    @RequestMapping("/")    // This maps to the URL http://localhost:8080/products/
    public String home(){
        return "welcome to the menu pagehome";
    }

    @RequestMapping("/list") // This maps to the URL http://localhost:8080/products/list
    public String listProducts(Model productListModel) { // Model argument is used to pass data to the view
        productListModel.addAttribute("products", productList); // Add the productsList to the model
        return "menu";  // This returns the view name, that is, the JSP file name
    }

    @RequestMapping("/details/{id}")     // This maps to the URL http://localhost:8080/products/details/{id}
    public String getProductDetails(@PathVariable int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return "<strong>Requested Product Details: </strong> <hr> Product ID: " + product.getId() + " - " + product.getName() + " - $" + product.getPrice();
            }
        }
        return "Product not found";
    }

    @PostMapping("/addNewProduct")  // Handles the form submission
    public String addProduct(Product product) {
        productsList.add(product);  // Adds the submitted product to productsList
        System.out.println(productsList);  // Logs the updated product list
        return "redirect:/";  // Redirects back to the main product list view
    }

}
