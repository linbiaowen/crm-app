import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustomerComponentsPage, CustomerDeleteDialog, CustomerUpdatePage } from './customer.page-object';

const expect = chai.expect;

describe('Customer e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let customerComponentsPage: CustomerComponentsPage;
  let customerUpdatePage: CustomerUpdatePage;
  let customerDeleteDialog: CustomerDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Customers', async () => {
    await navBarPage.goToEntity('customer');
    customerComponentsPage = new CustomerComponentsPage();
    await browser.wait(ec.visibilityOf(customerComponentsPage.title), 5000);
    expect(await customerComponentsPage.getTitle()).to.eq('crmwebApp.customer.home.title');
    await browser.wait(ec.or(ec.visibilityOf(customerComponentsPage.entities), ec.visibilityOf(customerComponentsPage.noResult)), 1000);
  });

  it('should load create Customer page', async () => {
    await customerComponentsPage.clickOnCreateButton();
    customerUpdatePage = new CustomerUpdatePage();
    expect(await customerUpdatePage.getPageTitle()).to.eq('crmwebApp.customer.home.createOrEditLabel');
    await customerUpdatePage.cancel();
  });

  it('should create and save Customers', async () => {
    const nbButtonsBeforeCreate = await customerComponentsPage.countDeleteButtons();

    await customerComponentsPage.clickOnCreateButton();

    await promise.all([
      customerUpdatePage.setCustAcctIdInput('custAcctId'),
      customerUpdatePage.setParentCustAcctIdInput('parentCustAcctId'),
      customerUpdatePage.acctStatusSelectLastOption(),
      customerUpdatePage.setAcctStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      customerUpdatePage.setAcctEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      customerUpdatePage.setCabsAcctIdInput('cabsAcctId'),
      customerUpdatePage.setTitleInput('title'),
      customerUpdatePage.setGivenNameInput('givenName'),
      customerUpdatePage.setFamilyNameInput('familyName'),
      customerUpdatePage.setGivenNameChiInput('givenNameChi'),
      customerUpdatePage.setFamilyNameChiInput('familyNameChi'),
      customerUpdatePage.setAliasNameInput('aliasName'),
      customerUpdatePage.setGenderInput('gender'),
      customerUpdatePage.setBirthDateInput('2000-12-31'),
      customerUpdatePage.setIdTypeInput('idType'),
      customerUpdatePage.setIdNumberInput('idNumber'),
      customerUpdatePage.setCompanyNameEngInput('companyNameEng'),
      customerUpdatePage.setCompanyNameChiInput('companyNameChi'),
      customerUpdatePage.langSelectLastOption(),
      customerUpdatePage.setSelfCareUserIdInput('selfCareUserId'),
      customerUpdatePage.setSelfCarePasswordInput('selfCarePassword'),
      customerUpdatePage.setIvrPinInput('ivrPin'),
      customerUpdatePage.setMaritialStatusInput('maritialStatus'),
      customerUpdatePage.customerSegmentSelectLastOption(),
      customerUpdatePage.setCustomerClassInput('customerClass'),
      customerUpdatePage.setBillingAcctIdInput('billingAcctId'),
      customerUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      customerUpdatePage.setCreatedByInput('createdBy'),
      customerUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      customerUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      customerUpdatePage.setTenantIdInput('tenantId'),
      customerUpdatePage.parentCustomerSelectLastOption()
    ]);

    expect(await customerUpdatePage.getCustAcctIdInput()).to.eq('custAcctId', 'Expected CustAcctId value to be equals to custAcctId');
    expect(await customerUpdatePage.getParentCustAcctIdInput()).to.eq(
      'parentCustAcctId',
      'Expected ParentCustAcctId value to be equals to parentCustAcctId'
    );
    expect(await customerUpdatePage.getAcctStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected acctStartDate value to be equals to 2000-12-31'
    );
    expect(await customerUpdatePage.getAcctEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected acctEndDate value to be equals to 2000-12-31'
    );
    expect(await customerUpdatePage.getCabsAcctIdInput()).to.eq('cabsAcctId', 'Expected CabsAcctId value to be equals to cabsAcctId');
    expect(await customerUpdatePage.getTitleInput()).to.eq('title', 'Expected Title value to be equals to title');
    expect(await customerUpdatePage.getGivenNameInput()).to.eq('givenName', 'Expected GivenName value to be equals to givenName');
    expect(await customerUpdatePage.getFamilyNameInput()).to.eq('familyName', 'Expected FamilyName value to be equals to familyName');
    expect(await customerUpdatePage.getGivenNameChiInput()).to.eq(
      'givenNameChi',
      'Expected GivenNameChi value to be equals to givenNameChi'
    );
    expect(await customerUpdatePage.getFamilyNameChiInput()).to.eq(
      'familyNameChi',
      'Expected FamilyNameChi value to be equals to familyNameChi'
    );
    expect(await customerUpdatePage.getAliasNameInput()).to.eq('aliasName', 'Expected AliasName value to be equals to aliasName');
    expect(await customerUpdatePage.getGenderInput()).to.eq('gender', 'Expected Gender value to be equals to gender');
    expect(await customerUpdatePage.getBirthDateInput()).to.eq('2000-12-31', 'Expected birthDate value to be equals to 2000-12-31');
    expect(await customerUpdatePage.getIdTypeInput()).to.eq('idType', 'Expected IdType value to be equals to idType');
    expect(await customerUpdatePage.getIdNumberInput()).to.eq('idNumber', 'Expected IdNumber value to be equals to idNumber');
    expect(await customerUpdatePage.getCompanyNameEngInput()).to.eq(
      'companyNameEng',
      'Expected CompanyNameEng value to be equals to companyNameEng'
    );
    expect(await customerUpdatePage.getCompanyNameChiInput()).to.eq(
      'companyNameChi',
      'Expected CompanyNameChi value to be equals to companyNameChi'
    );
    const selectedUnlimitedCompany = customerUpdatePage.getUnlimitedCompanyInput();
    if (await selectedUnlimitedCompany.isSelected()) {
      await customerUpdatePage.getUnlimitedCompanyInput().click();
      expect(await customerUpdatePage.getUnlimitedCompanyInput().isSelected(), 'Expected unlimitedCompany not to be selected').to.be.false;
    } else {
      await customerUpdatePage.getUnlimitedCompanyInput().click();
      expect(await customerUpdatePage.getUnlimitedCompanyInput().isSelected(), 'Expected unlimitedCompany to be selected').to.be.true;
    }
    expect(await customerUpdatePage.getSelfCareUserIdInput()).to.eq(
      'selfCareUserId',
      'Expected SelfCareUserId value to be equals to selfCareUserId'
    );
    expect(await customerUpdatePage.getSelfCarePasswordInput()).to.eq(
      'selfCarePassword',
      'Expected SelfCarePassword value to be equals to selfCarePassword'
    );
    expect(await customerUpdatePage.getIvrPinInput()).to.eq('ivrPin', 'Expected IvrPin value to be equals to ivrPin');
    expect(await customerUpdatePage.getMaritialStatusInput()).to.eq(
      'maritialStatus',
      'Expected MaritialStatus value to be equals to maritialStatus'
    );
    expect(await customerUpdatePage.getCustomerClassInput()).to.eq(
      'customerClass',
      'Expected CustomerClass value to be equals to customerClass'
    );
    expect(await customerUpdatePage.getBillingAcctIdInput()).to.eq(
      'billingAcctId',
      'Expected BillingAcctId value to be equals to billingAcctId'
    );
    expect(await customerUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await customerUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await customerUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await customerUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await customerUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await customerUpdatePage.save();
    expect(await customerUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await customerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Customer', async () => {
    const nbButtonsBeforeDelete = await customerComponentsPage.countDeleteButtons();
    await customerComponentsPage.clickOnLastDeleteButton();

    customerDeleteDialog = new CustomerDeleteDialog();
    expect(await customerDeleteDialog.getDialogTitle()).to.eq('crmwebApp.customer.delete.question');
    await customerDeleteDialog.clickOnConfirmButton();

    expect(await customerComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
