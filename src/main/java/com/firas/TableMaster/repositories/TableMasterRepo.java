package com.firas.TableMaster.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.firas.TableMaster.models.TableMaster;

@Repository
public interface TableMasterRepo extends CrudRepository<TableMaster, Long> {
    List<TableMaster> findAll();
    List<TableMaster> findAllByPosterIdOrderByCreatedAtDesc(Long userId);
 
}

