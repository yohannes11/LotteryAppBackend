package et.com.Lottery.dto.responseData;


import et.com.Lottery.dto.restData.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListOut {
    private List<String> roles;
    private Status status;

}
