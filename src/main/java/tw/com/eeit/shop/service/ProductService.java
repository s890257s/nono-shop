package tw.com.eeit.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.Predicate;
import tw.com.eeit.shop.config.security.SecurityConfig;
import tw.com.eeit.shop.model.dto.PageQuery;
import tw.com.eeit.shop.model.dto.ProductDto;
import tw.com.eeit.shop.model.dto.ProductQuery;
import tw.com.eeit.shop.model.entity.Product;
import tw.com.eeit.shop.model.mapper.ProductMapper;
import tw.com.eeit.shop.repository.ProductRepository;

@Service
public class ProductService {

	private final SecurityConfig securityConfig;

	@Autowired
	private ProductRepository productRepository;

	ProductService(SecurityConfig securityConfig) {
		this.securityConfig = securityConfig;
	}

	public List<ProductDto> getAll() {
		return productRepository.findAll().stream()
				.map(ProductMapper::toDto)
				.toList();
	}

	/**
	 * 根據傳入參數，動態增刪欄位查詢 Product 表格
	 */
	public List<ProductDto> getByQuery(ProductQuery productQuery) {

		Specification<Product> spce = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (Strings.isNotBlank(productQuery.getName())) {
				Predicate predicate = cb.like(root.get("productName"), "%" + productQuery.getName() + "%");
				predicates.add(predicate);
			}

			if (productQuery.getMin() != null) {
				Predicate predicate = cb.greaterThanOrEqualTo(root.get("price"), productQuery.getMin());
				predicates.add(predicate);
			}

			if (productQuery.getMax() != null) {
				predicates.add(cb.lessThanOrEqualTo(root.get("price"), productQuery.getMax()));
			}

			if (Strings.isNotBlank(productQuery.getColors())) {
				In<Object> in = cb.in(root.get("color"));
				for (String c : productQuery.getColors().split(",")) {
					System.out.println(c);
					in.value(c);
				}
				predicates.add(in);
			}

			return cb.and(predicates.toArray(new Predicate[0]));

		};

		return productRepository.findAll(spce).stream().map(ProductMapper::toDto).toList();
	}

	public Page<ProductDto> getByPaginated(PageQuery dto) {

		/**
		 * 建立分頁物件，依參數順序: </br>
		 * 參數一: page，表示當前頁數，起始值為 0 </br>
		 * 參數二: size，表示每頁顯示筆數 </br>
		 * 參數三: direction，表示排序順序由小到大(升冪 ASC)，或大到小(降冪 DESC) </br>
		 * 參數四: sort，排序目標欄位
		 */
		PageRequest pageRequest = PageRequest.of(dto.getPage(), dto.getSize(), dto.getDirectionEnum(), dto.getSort());

		Page<Product> pageProducts = productRepository.findAll(pageRequest);

		Page<ProductDto> pageProductDtos = pageProducts.map(post -> ProductMapper.toDto(post));

		return pageProductDtos;

	}
}
