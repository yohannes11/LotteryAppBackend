package et.com.Lottery.dto.responseData;


import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.UsersData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileOut {
    private UsersData userData;
    private String userName;
    private String authorities;
    private Boolean isActive;
    private Status status;
}
