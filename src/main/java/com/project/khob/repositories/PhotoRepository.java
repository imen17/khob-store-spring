package com.project.khob.repositories;

import com.project.khob.domain.entities.Photo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends CrudRepository<Photo,Long>, PagingAndSortingRepository<Photo,Long> {
}
