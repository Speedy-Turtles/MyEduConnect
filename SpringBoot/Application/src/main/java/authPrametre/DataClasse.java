package authPrametre;

import org.springframework.data.domain.Page;


import app.project.entities.classe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class DataClasse {
	private int[] count_page;
	private Page<classe> classe;
	int page;
}
