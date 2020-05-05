package et.com.Lottery.dto.responseData;



import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.CompanySetting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanySettingOut {
    private CompanySetting companySetting;
    private Status status;
}
