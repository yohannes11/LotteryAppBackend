package et.com.Lottery.dto.responseData;

import com.cassiomolin.user.domain.User;
import et.com.Lottery.model.UsersData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOut {
    private UsersData usersData;
    private User user;
}
