package com.sweetsdelight_bk.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Exceptions.CategoryException;
import com.sweetsdelight_bk.Exceptions.ProductException;
import com.sweetsdelight_bk.Model.Category;
import com.sweetsdelight_bk.Model.Product;
import com.sweetsdelight_bk.Repository.CategoryRepo;
import com.sweetsdelight_bk.Repository.ProductRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public Product addProduct(Product product,Integer categoryId) throws ProductException {
		if(product==null)throw new ProductException("product should not be null");
		log.debug("Calling save method from ProductJpa Repository");
		Category category= categoryRepo.findById(categoryId).orElseThrow(()->new ProductException("No category found"));
		product.setCategory(category);
		Product savedProduct = productRepo.save(product);
		log.info("product saved successfully");		
		return savedProduct;
	}

	@Override
	public Product updateProduct(Product product) throws ProductException {
		
		Optional<Product> opt = productRepo.findById(product.getProductid());
		
		if(opt.isPresent()) {
			log.debug("Calling save method from ProductJpa Repository");
			Product updatedProduct = productRepo.save(product);
			log.info("product updated successfully");	
			return updatedProduct;
		} else {
			throw new ProductException("Product not found!");
		}
	}

	@Override
	public String deleteProduct(Integer productId) throws ProductException {

		Optional<Product> opt = productRepo.findById(productId);
		
		if(opt.isPresent()) {
			log.debug("Calling delete method from ProductJpa Repository");
			productRepo.delete(opt.get());
			log.info("product deleted successfully");
			return "Product deleted successfully.";
		} else {
			throw new ProductException("Product not found!");
		}
	}

	@Override
	public Product showProductById(Integer productId) throws ProductException {
		log.debug("Calling findbyId method from ProductJpa Repository");
		Optional<Product> opt = productRepo.findById(productId);
		
		if(opt.isPresent()) {
			log.info("product got ");
			return opt.get();
		} else {
			throw new ProductException("Product not found!");
		}
		
	}

	@Override
	public List<Product> showAllProduct() throws ProductException {
		
		List<Product> products = productRepo.findAll();
		
		if(products.isEmpty()) {
			throw new ProductException("Products not exists!");
		} else {
			return products;
		}
		
	}

	@Override
	public List<Product> searchByName(String productName) throws ProductException {
		log.debug("Calling findByName method from ProductJpa Repository");
		List<Product> pr= productRepo.searchProductByName(productName);
		if(pr.isEmpty())throw new ProductException("Product not Available");
		
		log.info("product got");
		return pr;
	}

	@Override
	public List<Product> showAllProducts(int pageNumber, int pageSize) throws ProductException {
		
		Pageable page=  PageRequest.of(pageNumber, pageSize, Sort.by("productId"));
		log.debug("Calling findAll method from ProductJpa Repository");
		
		List<Product> list= productRepo.findAll(page).getContent();
		if(list.isEmpty())throw new ProductException("No product are there");
		
		log.info("Allproduct got");
		
		return list;
	}

	@Override
	public Page<Product> showAllProductsByAvailable(int pageNumber, int pageSize) throws ProductException {
		PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
		
		log.debug("Calling showAllAvailableProduct method from ProductJpa Repository");
		Page<Product> page=productRepo.showAllAvailableProduct(pageRequest);
		if(page.isEmpty())throw new ProductException("No product are there which are available");
		
		log.info("Allproduct got which is available");
		
		return page;
	}

	@Override
	public Product addProductToCategory(Integer productId, Integer categoryId)throws ProductException,CategoryException {
		log.debug("Calling findbyId method from ProductJpa Repository");
		Optional<Product> opt= productRepo.findById(productId);
		if(opt.isEmpty()) {
			throw new ProductException("No product found");
		}
		Product product=opt.get();
		Optional<Category> cat= categoryRepo.findById(categoryId);
		if(cat.isEmpty())throw new CategoryException("No category found");
		Category category=cat.get();
		product.setCategory(category);
		return productRepo.save(product);
	}
	
}
