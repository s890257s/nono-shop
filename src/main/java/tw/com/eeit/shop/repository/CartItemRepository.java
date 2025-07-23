package tw.com.eeit.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit.shop.model.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
