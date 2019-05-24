package br.com.luiz.redissample.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

@RedisHash("Task")
public class Task implements Serializable {

	private String id;
    private String name;
    private Date data;
    private Double tempo;
    
    public Task() {
    	
    }
    		
	public Task(String id, String name, Date data, Double tempo) {
		super();
		this.id = id;
		this.name = name;
		this.data = data;
		this.tempo = tempo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getTempo() {
		return tempo;
	}
	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}
}
