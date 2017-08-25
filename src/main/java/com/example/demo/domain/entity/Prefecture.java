package com.example.demo.domain.entity;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Value;

/**
 * Prefecture entity.
 * <p>
 * This entity class is made based on prefecture table.
 */
@Entity(immutable = true, listener = PrefectureListener.class)
@Table(name = "prefecture")
@Value
public class Prefecture {

	/** id */
	@Id
	@Column(name = "id")
	Short id;

	/** name */
	@Column(name = "name")
	String name;

}