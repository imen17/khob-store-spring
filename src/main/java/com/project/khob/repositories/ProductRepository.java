package com.project.khob.repositories;

import com.project.khob.domain.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long>, PagingAndSortingRepository<Product,Long> {

}
