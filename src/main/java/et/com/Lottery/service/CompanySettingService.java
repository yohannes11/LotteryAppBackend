package et.com.Lottery.service;

import et.com.Lottery.dao.CompanySettingDao;
import et.com.Lottery.dto.responseData.CompanySettingLisitOut;
import et.com.Lottery.dto.responseData.CompanySettingOut;
import et.com.Lottery.dto.restData.Status;
import et.com.Lottery.model.CompanySetting;
import et.com.Lottery.utility.StatusInit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class
CompanySettingService {
    @EJB
    CompanySettingDao companySettingDao;
    @EJB
    StatusInit statusInit;

    public Status addSetting(CompanySetting companySetting) {
        try {
            companySetting.setId(null);
            companySettingDao.create(companySetting);
            return statusInit.successfullyAdded();
        } catch (Exception e) {
            return statusInit.unknownErrorInit();
        }
    }

    public Status updateSetting(CompanySetting companySetting) {
        try {
            companySettingDao.update(companySetting);
            return statusInit.successful();

        } catch (Exception e) {
            return statusInit.unknownErrorInit();
        }

    }

    public CompanySettingLisitOut listCompanySetting(Integer start, Integer max) {
        CompanySettingLisitOut companySettingLisitOut = new CompanySettingLisitOut();
        List<CompanySettingOut> companySettingOutList = new ArrayList<>();
        try {
            List<CompanySetting> companySettingList = companySettingDao.listAll(start, max);
            companySettingList.forEach(companySetting -> {
                CompanySettingOut companySettingOut = new CompanySettingOut();
                companySettingOut.setCompanySetting(companySetting);
                companySettingOutList.add(companySettingOut);
            });
            companySettingLisitOut.setCompanySettingList(companySettingOutList);
            companySettingLisitOut.setCount(companySettingDao.listAll(null, null).size());
            companySettingLisitOut.setStatus(statusInit.successful());
            return companySettingLisitOut;
        } catch (Exception e) {
            companySettingLisitOut.setStatus(statusInit.unknownErrorInit());
            return companySettingLisitOut;
        }
    }

    public Status deleteSetting(Long id) {
        try {
            CompanySetting companySetting = companySettingDao.findById(id);
            if (companySetting != null) {
                companySettingDao.deleteById(id);
                return statusInit.successfullyDeleted();
            } else {
                return statusInit.notFound(id);
            }
        } catch (Exception e) {
            return statusInit.unknownErrorInit();
        }
    }

    public CompanySettingOut searchSetting(String name) {
        CompanySettingOut companySettingOut = new CompanySettingOut();
        CompanySetting companySetting = companySettingDao.findByName(name);
        companySettingOut.setCompanySetting(companySetting);
        companySettingOut.setStatus(statusInit.successful());
        return companySettingOut;
    }
}
