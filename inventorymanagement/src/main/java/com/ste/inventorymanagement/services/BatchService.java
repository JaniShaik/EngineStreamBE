package com.ste.inventorymanagement.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	BatchRepository batchRepo;

	public List<Batch> findAll() {
		return batchRepo.findAll();
	}

	public Material getMaterialByBatchIdUsingQuery(Long batchId) {
		return batchRepo.getMaterialByBatchIdUsingQuery(batchId);
	}

	public List<Batch> getEnabledSurplusFlagBatchRecords(Pageable pageable, boolean visited) {

		Page<Batch> page =  batchRepo.findAll(new Specification<Batch>() {
			@Override
			public Predicate toPredicate(Root<Batch> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<>();
				predicates.add(
						cb.equal(root.get("surplusFlag").as(String.class), "1")
						);

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		}, pageable);
		System.out.println(page.getContent().size() + " "+ visited+ " "+ pageable.getPageSize());

		if(page.getContent().size() == 0 && !visited) {
			int pageIndex = 0;
			System.out.println("if is called");
			Pageable p = PageRequest.of(pageIndex, pageable.getPageSize());
			visited = true;
			return getEnabledSurplusFlagBatchRecords(p,visited);
		}
		return page.getContent();
	}
	
}
