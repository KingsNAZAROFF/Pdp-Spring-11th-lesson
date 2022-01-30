package uz.pdp.pdpspring11thlesson.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring11thlesson.paylaod.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByNameAndCategoryId(String name, Integer category_id);
    Page<Product> findAllByCategoryId(Integer category_id, Pageable pageable);
}
