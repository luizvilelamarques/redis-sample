package br.com.luiz.redissample.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.luiz.redissample.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, String> {
	
}
