package uz.pdp.pdpspring11thlesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.pdpspring11thlesson.paylaod.entity.InputProduct;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
}
