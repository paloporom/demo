package com.example.demo.web.form;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorForm implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3779502067637706439L;
	private String id;
	@NotNull
	@Size(min = 1, max = 30)
	private String name;
	@Min(1)
	@Max(200)
	private String height;
	@Pattern(regexp = "A|B|AB|O")
	private String blood;
	@Pattern(regexp = "\\d{4}/\\d{2}/\\d{2}")
	private String birthday;
	@Min(1)
	@Max(47)
	private String birthplaceId;
	private String birthplaceName;
	private String updateAt;
}
