package et.com.Lottery.convertor;

import com.cassiomolin.security.domain.Authority;
import com.cassiomolin.security.service.PasswordEncoder;
import com.cassiomolin.user.domain.User;
import et.com.Lottery.dao.UserDao;
import et.com.Lottery.dao.UsersDataDao;
import et.com.Lottery.dto.requestData.RegisterUserIn;
import et.com.Lottery.dto.responseData.ProfileOut;
import et.com.Lottery.dto.responseData.UserOut;
import et.com.Lottery.model.UsersData;
import et.com.Lottery.utility.StatusInit;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Stateless
public class UserConvertor {
    @PersistenceContext(unitName = "LotteryApp-persistence-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;
    @EJB
    UserDao userDao;
    @EJB
    UsersDataDao usersDataDao;
    @Inject
    PasswordEncoder passwordEncoder;
    @EJB
    StatusInit statusInit;


    public ProfileOut userToProfileOut(User user) {
        ProfileOut profileOut = new ProfileOut();
        UsersData userData = new UsersData();
        profileOut.setUserName(user.getUsername());
        profileOut.setAuthorities(user.getAuthorities().iterator().next().toString());
        UsersData usersData = usersDataDao.findById(user.getUserData());
        userData.setId(usersData.getId());
        userData.setEmail(usersData.getEmail());
        userData.setFirstName(usersData.getFirstName());
        userData.setLastName(usersData.getLastName());
        userData.setPhoneNumber(usersData.getPhoneNumber());
        profileOut.setUserData(userData);
        profileOut.setStatus(statusInit.successful());
        return profileOut;
    }


    public UsersData registrationInToUsersData(RegisterUserIn registerUserIn) {
        UsersData usersData = new UsersData();
        usersData.setFirstName(registerUserIn.getFirstName());
        usersData.setLastName(registerUserIn.getLastName());
        usersData.setEmail(registerUserIn.getEmail());
        usersData.setPhoneNumber(registerUserIn.getPhoneNumber());
        return usersData;
    }


    public User registrationInToUser(RegisterUserIn registerUserIn) {
        User user = new User();
        user.setUsername(registerUserIn.getUsername());
        user.setPassword(passwordEncoder.hashPassword(registerUserIn.getPassword()));
        user.setActive(true);
        user.setUserGroup(registerUserIn.getGroup());
        EnumSet<Authority> authorities = EnumSet.of(Authority.CUSTOMER);
        switch (registerUserIn.getAuthorities()) {
            case "ADMIN":
                authorities = EnumSet.of(Authority.ADMIN);
                break;
            case "USER":
                authorities = EnumSet.of(Authority.USER);
                break;
            case "ACTUSER":
                authorities = EnumSet.of(Authority.ACTUSER);
                break;
            case "CUSTOMER":
                authorities = EnumSet.of(Authority.CUSTOMER);
                break;
        }
        user.setAuthorities(authorities);
        return user;
    }

    public UserOut userToUserOut(User user) {
        UserOut userOut = new UserOut();
        user.setPassword("");
        userOut.setUser(user);
        userOut.setUsersData(usersDataDao.findById(user.getUserData()));
        em.detach(user);
        return userOut;
    }

    public UserOut userDataToUserOut(UsersData usersData) {
        UserOut userOut = new UserOut();
        userOut.setUser(userDao.userFindByUserDataId(usersData.getId()));
        userOut.setUsersData(usersData);
        return userOut;
    }

    public List<UserOut> userToUserOutList(List<User> userList) {
        List<UserOut> userOuts = new ArrayList<>();
        userList.forEach(user -> {
            userOuts.add(this.userToUserOut(user));
        });
        return userOuts;
    }

    public List<UserOut> userDataToUserOut(List<UsersData> usersData) {
        List<UserOut> userOutList = new ArrayList<>();
        usersData.forEach(userData -> {
            userOutList.add(this.userDataToUserOut(userData));
        });
        return userOutList;

    }

}
