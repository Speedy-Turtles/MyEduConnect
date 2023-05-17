package authPrametre;

import org.springframework.data.domain.Page;

import app.project.entities.specialite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class DataSpecialite {
	private int[] count_page;
	private Page<specialite> spec;
	int page;
}
