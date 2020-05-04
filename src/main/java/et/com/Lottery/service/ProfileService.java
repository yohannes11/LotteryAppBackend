package et.com.Lottery.service;

import com.cassiomolin.security.service.PasswordEncoder;
import com.cassiomolin.user.domain.User;
import com.cassiomolin.user.service.UserService;
import et.com.Lottery.convertor.UserConvertor;
import et.com.Lottery.dao.PasswordHistoryDao;
import et.com.Lottery.dao.UserDao;
import et.com.Lottery.dao.UsersDataDao;
import et.com.Lottery.dto.requestData.ChangePasswordIn;
import et.com.Lottery.dto.responseData.ProfileOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.PasswordHistory;
import et.com.Lottery.model.UsersData;
import et.com.Lottery.utility.RandomNumberGenerator;
import et.com.Lottery.utility.StatusInit;
import et.com.Lottery.validator.UserValidator;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfileService {

    @EJB
    RandomNumberGenerator randomNumberGenerator;
    @EJB
    UserValidator userValidator;
    @Inject
    UserService userService;
    @Inject
    PasswordEncoder passwordEncoder;
    @EJB
    StatusInit statusInit;
    @EJB
    PasswordHistoryDao passwordHistoryDao;
    @EJB
    UserDao userDao;
    @EJB
    UserConvertor userConvertor;
    @EJB
    UsersDataDao usersDataDao;
    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    public ProfileOut viewProfile(User user) {
        return this.userConvertor.userToProfileOut(user);
    }

    public Status editProfile(UsersData userData, User user) {
        UsersData usersData = usersDataDao.findById(user.getUserData());
        usersData.setFirstName(userData.getFirstName());
        usersData.setLastName(userData.getLastName());
        usersData.setEmail(userData.getEmail());
        usersData.setPhoneNumber(userData.getPhoneNumber());
        usersDataDao.update(usersData);
        return statusInit.successfullyUpdated();
    }

    public Status changePassword(ChangePasswordIn changePasswordIn, User user) {
        Status status = this.userValidator.validateUserPassword(user, changePasswordIn.getOldPassword());
        if (!status.isStatus()) {
            status.setMessage("Password Change Failed.");
            return status;
        } else {
            user.setPassword(this.passwordEncoder.hashPassword(changePasswordIn.getNewPassword()));
            this.em.merge(user);
            PasswordHistory passwordHistory = passwordHistoryDao.listByUserId(null, null, user.getId()).get(0);
            if (passwordHistory.getInitialPassword().compareTo(user.getPassword()) == 0) {
                passwordHistory.setIsPasswordChanged(false);
            } else {
                passwordHistory.setIsPasswordChanged(true);
            }
            status.setMessage("Password Successfully Changed.");
            return status;
        }
    }


    public Status reset(Long id) {
        try {
            User user = userDao.userFindByUserDataId(id);
            if (user == null) {
                return statusInit.singleErrorInit("User Not Found", "User Not Found");
            }
            if (user.isActive() == false) {
                return statusInit.singleErrorInit("User Not Active", "User Not Active");
            }

            String newPass = randomNumberGenerator.generateRandomNumber(10);
            user.setPassword(this.passwordEncoder.hashPassword(newPass));
            this.em.merge(user);
            PasswordHistory passwordHistory = passwordHistoryDao.listByUserId(null, null, user.getId()).get(0);
            passwordHistory.setIsPasswordChanged(false);
            passwordHistoryDao.update(passwordHistory);
            return statusInit.singleSuccessInit("Pasword reset to " + newPass);
        } catch (Exception E) {
            return statusInit.singleErrorInit("Unable to Reset Password", "Unable to Reset Password");
        }
    }
}
