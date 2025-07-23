package tw.com.eeit.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit.shop.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
