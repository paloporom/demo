package com.example.demo.domain.entity.embeddable;

import org.seasar.doma.Column;
import org.seasar.doma.Embeddable;

import lombok.Value;

/**
 * This class is embeddable entity for Prefecture.
 */
@Embeddable
@Value
public class Prefecture {

	/** id */
	@Column(name = "prefecture_id")
	final Short id;
	/** name */
	@Column(name = "prefecture_name")
	final String name;

}
