package com.jsp.Product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.Product.Entity.Product;
import com.jsp.Product.requestdtos.ProductRequest;
import com.jsp.Product.utility.ResponseStructure;

public interface ProductService {

	ResponseEntity<ResponseStructure<Product>> saveProduct(ProductRequest request);

	ResponseEntity<ResponseStructure<Product>> findProductById(int productId);

	ResponseEntity<ResponseStructure<Product>> deleteProduct(int productId);

	ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product product);

	ResponseEntity<ResponseStructure<List<Product>>> findAllProducts();

	
}
