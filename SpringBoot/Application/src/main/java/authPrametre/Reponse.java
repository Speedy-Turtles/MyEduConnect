package authPrametre;

import app.project.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class Reponse {
  private User user;
  private String token;
}
