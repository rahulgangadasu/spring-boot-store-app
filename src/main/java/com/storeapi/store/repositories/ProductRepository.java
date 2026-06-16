package com.storeapi.store.repositories;

import com.storeapi.store.entities.Category;
import com.storeapi.store.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("unused")
public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    //Convention for Derived Query methods that use strings.
    List<Product> findProductsByCategoryName(String name);

    List<Product> findProductsByCategoryNameIgnoreCase(String name);

    List<Product> findProductsByCategoryNameLikeIgnoreCase(String name);

    //Convention for Derived Query methods that use numbers.
    List<Product> findProductsByPriceLessThan(Float price);

    List<Product> findProductsByPriceGreaterThan(Float price);

    //Convention for Derived Query Methods that work with null values.
    List<Product> findProductsByCategoryIsNull();

    List<Product> findProductsByCategoryIsNotNull();

    //Multiple conditions
    List<Product> findProductsByCategoryNameIgnoreCaseAndPriceGreaterThan(String name, Float price);

    List<Product> findProductsByCategoryNameIgnoreCaseAndPriceGreaterThanAndCategoryIsNotNull(String name, Float price);

    //Sort (must use OrderBy in method name)
    List<Product> findProductsByCategoryNameIgnoreCaseAndPriceGreaterThanOrderByPriceAsc(String name, Float price);

    List<Product> findProductsByCategoryNameIgnoreCaseAndPriceGreaterThanOrderByPriceDesc(String name, Float price);

    //Limit (must add Top/First keywords in method name)
    List<Product> findTop10ProductsByCategoryNameAndPriceGreaterThanOrderByPriceAsc(String name, Float price);

    List<Product> findFirst10ProductsByCategoryNameAndPriceGreaterThanOrderByPriceDesc(String name, Float price);

    //Using @Query() to create custom query methods using SQL or JPQL.
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max ORDER BY p.name")
    List<Product> findProducts(@Param("min") BigDecimal min,@Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.category = :category")
    void updatePriceByCategory(BigDecimal newPrice, Category category);

    @Query("select p.id, p.name from Product p where p.category = :category")
    List<Product> findByCategory(Category category);
}
