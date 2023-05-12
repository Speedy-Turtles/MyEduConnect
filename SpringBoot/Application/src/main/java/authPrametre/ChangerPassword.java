package authPrametre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class ChangerPassword {
 private String Token;
 private String email;
 private String password;
}
