package et.com.Lottery.validator;

import com.cassiomolin.security.service.PasswordEncoder;
import com.cassiomolin.user.domain.User;
import et.com.Lottery.dao.UserDao;
import et.com.Lottery.dto.requestData.RegisterUserIn;
import et.com.Lottery.dto.restData.ErrorMessage;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.utility.StatusInit;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserValidator {

    @EJB
    UserDao userDao;
    @Inject
    PasswordEncoder passwordEncoder;
    @EJB
    StatusInit statusInit;


    public Status validateUserAvailability(Long id) {
        if (this.userDao.findById(id) == null) {
            return statusInit.singleErrorInit("user not available", "user not available");
        } else {
            return statusInit.successful();
        }
    }

    public Status validateUserPassword(User user, String password) {
        List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
        if (!this.passwordEncoder.checkPassword(password, user.getPassword())) {
            return statusInit.singleErrorInit("Password mismatch", "Old password is incorrect");
        } else {
            return statusInit.successful();
        }
    }

    public Status registrationValidator(RegisterUserIn registerUserIn) {
        List<ErrorMessage> errorMessages = new ArrayList<ErrorMessage>();
        if (!(this.userDao.findUserByUserName(registerUserIn.getUsername()) == null)) {
            return statusInit.singleErrorInit("Unable to add user by this name", "user already exist by this username");
        }

        return statusInit.successfullyAdded();

    }
}