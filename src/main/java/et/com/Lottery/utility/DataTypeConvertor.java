package et.com.Lottery.utility;

import com.cassiomolin.security.domain.Authority;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Stateless
public class DataTypeConvertor {
    public List<String> enumToArray() {
        ArrayList<String> roles = new ArrayList<>();
        Arrays.asList(Authority.values())
                .forEach(role -> {
                    roles.add(role.name());
                });
        return roles;
    }
}
