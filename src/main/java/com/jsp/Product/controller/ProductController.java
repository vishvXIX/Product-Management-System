package com.jsp.Product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jsp.Product.Entity.Product;
import com.jsp.Product.RequestDTOs.ProductRequest;
import com.jsp.Product.Utility.ResponseStructure;
import com.jsp.Product.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveProduct")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductRequest request){
		return productService.saveProduct(request);
	}
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int productId){
		return productService.deleteProduct(productId);
	}
	
	@GetMapping("/product/{productId}") 
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int productId){
		return productService.findProductById(productId);
	}
	
	@GetMapping("/product") 
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts(){
		return productService.findAllProducts();
	}
	
	@PutMapping("/product")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId,@RequestBody Product product){
		return productService.updateProduct(productId, product);
	}

	
}
