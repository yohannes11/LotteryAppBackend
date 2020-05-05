package et.com.Lottery.service;

import com.cassiomolin.user.domain.User;
import et.com.Lottery.dao.FailedLoginAttemtDao;
import et.com.Lottery.dao.UserDao;
import et.com.Lottery.dto.restData.CompanySettingStaticData;
import et.com.Lottery.model.FailedLoginAtempt;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class loginAttemptServices {
    @EJB
    UserDao userDao;
    @EJB
    FailedLoginAttemtDao failedLoginAttemtDao;
    @EJB
    CompanySettingStaticData companySettingStaticData;

    public void updateFailedLogin(Long userid) {
        User user = userDao.findById(userid);
        List<FailedLoginAtempt> failedLoginAtemptList = failedLoginAttemtDao.findByUserId(userid);
        if (failedLoginAtemptList.isEmpty()) {
            FailedLoginAtempt failedLoginAtempt = new FailedLoginAtempt();
            failedLoginAtempt.setUserId(userid);
            failedLoginAtempt.setDailyAtempt(1);
            failedLoginAtempt.setHourlyAtempt(1);
            failedLoginAtempt.setTotalAtempt(1);
            failedLoginAtempt.setStatus(true);
            failedLoginAttemtDao.create(failedLoginAtempt);
        } else {
            failedLoginAtemptList.get(0).setDailyAtempt(failedLoginAtemptList.get(0).getDailyAtempt() + 1);
            failedLoginAtemptList.get(0).setHourlyAtempt(failedLoginAtemptList.get(0).getHourlyAtempt() + 1);
            failedLoginAtemptList.get(0).setTotalAtempt(failedLoginAtemptList.get(0).getTotalAtempt() + 1);

            if (failedLoginAtemptList.get(0).getHourlyAtempt() >= companySettingStaticData.getMaxFailed()) {

                user.setActive(false);
                userDao.update(user);

            }
            failedLoginAtemptList.get(0).setStatus(true);
            failedLoginAttemtDao.update(failedLoginAtemptList.get(0));
        }



    }
}
