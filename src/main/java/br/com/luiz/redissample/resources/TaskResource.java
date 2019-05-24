package br.com.luiz.redissample.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.luiz.redissample.entity.Task;
import br.com.luiz.redissample.repository.TaskRepository;

@RestController
@RequestMapping(path = "/task")
public class TaskResource {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable(value = "id") String id) {
		Task task = this.taskRepository.findById(id).get();
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Task> tasks = new ArrayList<>();
		taskRepository.findAll().forEach(tasks::add);
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Task task, Errors errors) {
		if (!errors.hasErrors()) {
			Task created = this.taskRepository.save(task);
			return new ResponseEntity<Task>(created, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body(
				errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(",")));
	}

	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable(value = "id") String id, @Valid @RequestBody Task task,
			Errors errors) {
		if (!errors.hasErrors()) {
			Task updated = this.taskRepository.save(task);
			return new ResponseEntity<Task>(updated, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body(
				errors.getAllErrors().stream().map(msg -> msg.getDefaultMessage()).collect(Collectors.joining(",")));
	}

	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") String id) {
		this.taskRepository.deleteById(id);
	}
}