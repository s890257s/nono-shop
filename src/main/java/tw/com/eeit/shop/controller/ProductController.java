package tw.com.eeit.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit.shop.model.dto.PageQuery;
import tw.com.eeit.shop.model.dto.ProductDto;
import tw.com.eeit.shop.model.dto.ProductQuery;
import tw.com.eeit.shop.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public List<ProductDto> getAll() {
		return productService.getAll();
	}

	@GetMapping("/query")
	public List<ProductDto> getByQuery(@ModelAttribute ProductQuery query) {
		return productService.getByQuery(query);
	}

	@GetMapping("/page")
	public Page<ProductDto> getByPaginated(@ModelAttribute PageQuery query) {
		return productService.getByPaginated(query);
	}
}
