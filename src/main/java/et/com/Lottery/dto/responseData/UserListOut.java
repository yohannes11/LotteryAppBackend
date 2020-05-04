package et.com.Lottery.dto.responseData;



import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.UsersData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListOut {
    private List<UserOut> userDatas;
    private int count;
    private Status status;

}
