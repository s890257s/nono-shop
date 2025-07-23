package tw.com.eeit.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.com.eeit.shop.model.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
