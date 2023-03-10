package com.pictu.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
	private Integer categoryId;

	@NotBlank
	@Size(min = 4, max = 20, message = "Minimum size of the title is 4 and max is 20")
	private String categoryTitle;

	@NotBlank
	@Size(min = 10, max = 100, message = "Minimum size of category desc is 10 and max is 100")
	private String categoryDescription;

}
