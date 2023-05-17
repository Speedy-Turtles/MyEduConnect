package authPrametre;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data@AllArgsConstructor@NoArgsConstructor
public class InfoPersonnel {
	 private String firstname;
	 private String lastname;
	 private String cin;
	 private String num_tlf;
	 private LocalDate birth_day;
}
