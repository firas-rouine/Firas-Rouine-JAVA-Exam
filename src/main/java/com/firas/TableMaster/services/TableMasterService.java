package com.firas.TableMaster.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firas.TableMaster.models.TableMaster;
import com.firas.TableMaster.repositories.TableMasterRepo;

@Service
public class TableMasterService {
	// === CRUD ===
	
	@Autowired
	private TableMasterRepo tableMasterRepo;
	
	// READ ALL
	public List<TableMaster> allTableMaster(){
		return tableMasterRepo.findAll();
	}
    // READ ALL BY USER ID
	   public List<TableMaster> allTableMasterByUserId(Long userId) {
	        return tableMasterRepo.findAllByPosterIdOrderByCreatedAtDesc(userId);
    }
	
	// CREATE
	public TableMaster create(TableMaster t) {
		return tableMasterRepo.save(t);
	}
	
	// READ ONE
	public TableMaster findTableMaster(Long id) {
		
		Optional<TableMaster> maybeTableMaster = tableMasterRepo.findById(id);
		if(maybeTableMaster.isPresent()) {
			return maybeTableMaster.get();
		}else {
			return null;
		}
	}
	
	// UPDATE 
	public TableMaster update(TableMaster t) {
		return tableMasterRepo.save(t);
	}
	
	// DELETE
	public void delete(Long id) {
		tableMasterRepo.deleteById(id);
	}
	

	
}
