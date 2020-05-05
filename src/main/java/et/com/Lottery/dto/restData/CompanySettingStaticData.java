package et.com.Lottery.dto.restData;



import et.com.Lottery.dao.CompanySettingDao;
import et.com.Lottery.model.CompanySetting;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class CompanySettingStaticData {
    @EJB
    CompanySettingDao companySettingDao;

    private String username;
    private String password;
    private String companyName;
    private String phone;
    private String email;
    private String logo;
    private String salesManager;
    private int transactionLimit;
    private String websiteUrl;
    private String companyDescription;
    private String merchantRefGenType;
    private String uniEnterpriseId;
    private int validFor;
    private int enterpriseBankAccountId;
    private int cancelationPeriod;
    private Date lastSyncDate;
    private int maxFaield;
    private Boolean allowCustRegistration;
    private Boolean allowMembership;
    private Date lastCustSyncDate;



    public int getMaxFaield() {
        return maxFaield;
    }

    public String getUsername() {
        return companySettingDao.findByName("username").getValue();
    }

    public String getPassword() {
        return companySettingDao.findByName("password").getValue();
    }

    public String getCompanyName() {
        return companySettingDao.findByName("companyname").getValue();
    }

    public String getPhone() {
        return companySettingDao.findByName("phone").getValue();
    }

    public String getEmail() {
        return companySettingDao.findByName("email").getValue();
    }

    public String getLogo() {
        return companySettingDao.findByName("logo").getValue();
    }

    public String getSalesManager() {
        return companySettingDao.findByName("salesManager").getValue();
    }

    public int getTransactionLimit() {
        return Integer.parseInt(companySettingDao.findByName("transactionLimit").getValue());
    }

    public String getWebsiteUrl() {
        return companySettingDao.findByName("websiteUrl").getValue();
    }

    public String getCompanyDescription() {
        return companySettingDao.findByName("companyDescription").getValue();
    }

    public String getMerchantRefGenType() {
        return companySettingDao.findByName("merchantRefGenType").getValue();
    }

    public String getUniEnterpriseId() {
        return companySettingDao.findByName("uniEnterpriseId").getValue();
    }

    public int getValidFor() {
        return Integer.parseInt(companySettingDao.findByName("validfor").getValue());
    }

    public int getEnterpriseBankAccountId() {
        return Integer.parseInt(companySettingDao.findByName("enterpriseBankAccountId").getValue());
    }

    public int getCancelationPeriod() {
        return Integer.parseInt(companySettingDao.findByName("CancelationPeriod").getValue());
    }

    public Date getLastSyncDate() {
        return companySettingDao.findByName("lastSyncTime").getUpdatedOn();
    }

    public void setLastSyncDate(Date lastSyncDate) {
        CompanySetting companySetting = companySettingDao.findByName("lastSyncTime");
        companySetting.setUpdatedOn(lastSyncDate);
        companySettingDao.update(companySetting);
    }

    public int getMaxFailed() {
        return Integer.parseInt(companySettingDao.findByName("maxFailed").getValue());
    }

    public Boolean getAllowCustRegistration() {
        if (companySettingDao.findByName("allowCustRegistration").getValue().equals("false")) {
            this.allowCustRegistration = false;
        } else {
            this.allowCustRegistration = true;
        }
        return allowCustRegistration;
    }
    public Boolean getAllowMembership() {
        if (companySettingDao.findByName("allowMembership").getValue().equals("false")) {
            this.allowMembership = false;
        } else {
            this.allowMembership = true;
        }
        return allowMembership;
    }
    public void setLastCustSyncDate(Date lastCustSyncDate) {
        CompanySetting companySetting = companySettingDao.findByName("lastCustSyncTime");
        companySetting.setUpdatedOn(lastCustSyncDate);
        companySettingDao.update(companySetting);
    }
    public Date getLastCustSyncDate() {
        return companySettingDao.findByName("lastCustSyncTime").getUpdatedOn();
    }
}
