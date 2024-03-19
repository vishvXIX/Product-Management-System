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
import com.jsp.Product.requestdtos.ProductRequest;
import com.jsp.Product.service.ProductService;
import com.jsp.Product.utility.ErrorStructure;
import com.jsp.Product.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Operation(description = "The endpoints is used for add new product in side the database.",
			responses = {@ApiResponse (responseCode = "400", description = "Invalid Inputs."),
					@ApiResponse (responseCode = "200",description = "Product saved Successfully!!!")})
	@PostMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody ProductRequest request){
		return productService.saveProduct(request);
	}

	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(@PathVariable int productId){
		return productService.deleteProduct(productId);
	}

	@Operation(description = "The endpoints is used to fatch the products based on productId.", 
			responses = {@ApiResponse (responseCode = "200", description = "Product fatch Successfully!!"),
					@ApiResponse (responseCode = "404", description = "Product Not Found By Given Id.", 
					content = {@Content(schema = @Schema(implementation = ErrorStructure.class))
					})
	})
	@GetMapping("/products/{productId}") 
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int productId){
		return productService.findProductById(productId);
	}

	@GetMapping("/products")  
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		return productService.findAllProducts();
	}

	@PutMapping("/products")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId,@RequestBody Product product){
		return productService.updateProduct(productId, product);
	}


}
