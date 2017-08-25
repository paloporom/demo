package com.example.demo.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;

import lombok.Builder;
import lombok.Value;

/**
 * Actor entity.
 * <p>
 * This entity class is made based on actor table.
 */
@Entity(immutable = true, listener = ActorListener.class)
@Table(name = "actor")
@Value
@Builder
public class Actor {

	/** id */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;

	/** name */
	@Column(name = "name")
	String name;

	/** height */
	@Column(name = "height")
	Short height;

	/** blood */
	@Column(name = "blood")
	String blood;

	/** birthday */
	@Column(name = "birthday")
	LocalDate birthday;

	/** birthplace_id */
	@Column(name = "birthplace_id")
	Short birthplaceId;

	/** update_at */
	@Column(name = "update_at")
	LocalDateTime updateAt;

}