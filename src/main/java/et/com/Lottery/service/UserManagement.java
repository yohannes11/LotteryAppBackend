package et.com.Lottery.service;

import com.cassiomolin.user.domain.User;
import et.com.Lottery.convertor.UserConvertor;
import et.com.Lottery.dao.UserDao;
import et.com.Lottery.dao.UsersDataDao;
import et.com.Lottery.dto.requestData.RegisterUserIn;
import et.com.Lottery.dto.responseData.RegisterUserOut;
import et.com.Lottery.dto.responseData.RoleListOut;
import et.com.Lottery.dto.responseData.UserListOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.UsersData;
import et.com.Lottery.utility.DataTypeConvertor;
import et.com.Lottery.utility.StatusInit;
import et.com.Lottery.validator.UserValidator;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class UserManagement {
    @EJB
    UserValidator userValidator;
    @EJB
    UserConvertor userConvertor;
    @EJB
    UserDao userDao;
    @EJB
    UsersDataDao usersDataDao;
    @EJB
    StatusInit statusInit;
    @EJB
    DataTypeConvertor dataTypeConvertor;

    public RegisterUserOut addUser(RegisterUserIn registerUserIn) {
        RegisterUserOut registerUserOut = new RegisterUserOut();
        Status status = userValidator.registrationValidator(registerUserIn);
        if (!status.isStatus()) {
            registerUserOut.setStatus(status);
            return registerUserOut;
        }
        UsersData usersData = userConvertor.registrationInToUsersData(registerUserIn);
        User user = userConvertor.registrationInToUser(registerUserIn);
        UsersData userDataReturned = this.usersDataDao.createAndReturn(usersData);
        user.setUserData(userDataReturned.getId());
        user.setUserGroup(registerUserIn.getGroup());
        user.setActive(true);
        this.userDao.create(user);
        registerUserOut.setId(userDataReturned.getId());
        registerUserOut.setRegisterUserIn(registerUserIn);
        registerUserOut.setStatus(statusInit.successfullyAdded());
        return registerUserOut;
    }

    public Status userActivation(Long id, boolean activation) {
        User user = this.userDao.userFindByUserDataId(id);
        if (user == null) {
            return statusInit.emptyErrorInit();

        }
        user.setActive(activation);
        Status status = statusInit.successful();
        return status;
    }

    public UserListOut userList(Integer start, Integer max) {
        UserListOut userListOut = new UserListOut();
        List<User> userList = this.userDao.listAll(start, max);
        userListOut.setUserDatas(userConvertor.userToUserOutList(userList));
        userListOut.setCount(this.userDao.listAll(start, max).size());
        return userListOut;

    }

    public UserListOut searchUser(String parameter, Integer start, Integer max) {
        UserListOut userListOut = new UserListOut();
        List<UsersData> usersData = this.usersDataDao.searchUsersData(parameter, start, max);
        userListOut.setUserDatas(userConvertor.userDataToUserOut(usersData));
        userListOut.setCount(this.usersDataDao.searchUsersData(parameter, null, null).size());
        userListOut.setStatus(statusInit.successful());
        return userListOut;
    }

    public RoleListOut roleList(Boolean allowAll) {
        RoleListOut roleListOut = new RoleListOut();
        roleListOut.setRoles(dataTypeConvertor.enumToArray());
        roleListOut.setStatus(new Status(true, "Successfully Listed", null));
        return roleListOut;
    }


}
