package com.jsp.Product.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Product.Entity.Product;
import com.jsp.Product.Exception.ProductNotFoundByIdException;
import com.jsp.Product.RequestDTOs.ProductRequest;
import com.jsp.Product.Utility.ResponseStructure;
import com.jsp.Product.repository.ProductRepository;
import com.jsp.Product.service.ProductService;

@Service
public class ProductServiceIMPL implements ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private ResponseStructure<Product> responseStructure;

	@Override
	public ResponseEntity<ResponseStructure<Product>> saveProduct(ProductRequest productRequest) {
		Product product = repository.save(mapToProduct(productRequest));

		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Data saved successfully");
		responseStructure.setData(product);

		return ResponseEntity.status(HttpStatus.OK).body(responseStructure);

	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> deleteProduct(int productId) {

		Optional<Product> optional = repository.findById(productId);
		if(optional.isPresent()) {
			Product product = optional.get();
			repository.delete(product);
			ResponseStructure<Product> responseStructure = new ResponseStructure<Product>();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted Successfully...");
			responseStructure.setData(product);
			return new  ResponseEntity<ResponseStructure<Product>>(responseStructure,HttpStatus.OK);
		}
		else {
			throw new ProductNotFoundByIdException("Product Not Found");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> findProductById(int productId) {
		return repository.findById(productId)
				.map(product->{
					ResponseStructure<Product> responseStructure = new ResponseStructure<>();
					responseStructure.setStatus(HttpStatus.OK.value());
					responseStructure.setMessage("product fatch susscessfully");
					responseStructure.setData(product);
					return new ResponseEntity<>(responseStructure,HttpStatus.OK);
				})
				.orElseThrow(()-> new ProductNotFoundByIdException("product not found by id"));
	}

	@Override
	public ResponseEntity<ResponseStructure<Product>> updateProduct(int productId, Product product) {
		return repository.findById(productId)
				.map(existingProduct -> {
					product.setProductId(existingProduct.getProductId());
					existingProduct=repository.save(product);
					return ResponseEntity.ok(responseStructure.setStatus(HttpStatus.OK.value())
							.setMessage("Product update successfully!!")
							.setData(existingProduct));
				})
				.orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

	}


	private Product mapToProduct(ProductRequest request) {

		Product products = new Product();
		products.setProductName(request.getProductName());
		products.setProductPrice(request.getProductPrice());

		return products;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProducts() {
		List<Product> productList = repository.findAll();
		if (productList.isEmpty()) {
			throw new ProductNotFoundByIdException("No products found");
		}

		ResponseStructure<List<Product>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("Products fetched successfully");
		responseStructure.setData(productList);

		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

}
