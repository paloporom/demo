package com.example.demo.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import com.example.demo.domain.entity.embeddable.Prefecture;

import lombok.Builder;
import lombok.Value;

/**
 * This entity class is for the Acotr/index screen.
 * <p>
 * Please see link document.
 *
 * @see /demo/src/main/resources/templates/Actor/index.html
 *
 */
@Entity(immutable = true)
@Value
@Builder
public class ActorPrefecture {
	/**  */
	@Id
	@Column(name = "id")
	Integer id;

	/**  */
	@Column(name = "name")
	String name;

	/**  */
	@Column(name = "height")
	Short height;

	/**  */
	@Column(name = "blood")
	String blood;

	/**  */
	@Column(name = "birthday")
	LocalDate birthday;

	Prefecture prefecture;

	/**  */
	@Column(name = "update_at")
	LocalDateTime updateAt;
}
