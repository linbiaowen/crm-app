import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CustAcctBlackListComponentsPage,
  CustAcctBlackListDeleteDialog,
  CustAcctBlackListUpdatePage
} from './cust-acct-black-list.page-object';

const expect = chai.expect;

describe('CustAcctBlackList e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custAcctBlackListComponentsPage: CustAcctBlackListComponentsPage;
  let custAcctBlackListUpdatePage: CustAcctBlackListUpdatePage;
  let custAcctBlackListDeleteDialog: CustAcctBlackListDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustAcctBlackLists', async () => {
    await navBarPage.goToEntity('cust-acct-black-list');
    custAcctBlackListComponentsPage = new CustAcctBlackListComponentsPage();
    await browser.wait(ec.visibilityOf(custAcctBlackListComponentsPage.title), 5000);
    expect(await custAcctBlackListComponentsPage.getTitle()).to.eq('crmwebApp.custAcctBlackList.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custAcctBlackListComponentsPage.entities), ec.visibilityOf(custAcctBlackListComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustAcctBlackList page', async () => {
    await custAcctBlackListComponentsPage.clickOnCreateButton();
    custAcctBlackListUpdatePage = new CustAcctBlackListUpdatePage();
    expect(await custAcctBlackListUpdatePage.getPageTitle()).to.eq('crmwebApp.custAcctBlackList.home.createOrEditLabel');
    await custAcctBlackListUpdatePage.cancel();
  });

  it('should create and save CustAcctBlackLists', async () => {
    const nbButtonsBeforeCreate = await custAcctBlackListComponentsPage.countDeleteButtons();

    await custAcctBlackListComponentsPage.clickOnCreateButton();

    await promise.all([
      custAcctBlackListUpdatePage.setBlackListIdInput('5'),
      custAcctBlackListUpdatePage.setIdTypeInput('idType'),
      custAcctBlackListUpdatePage.setIdNumberInput('idNumber'),
      custAcctBlackListUpdatePage.setBlackListCodeInput('blackListCode'),
      custAcctBlackListUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAcctBlackListUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAcctBlackListUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAcctBlackListUpdatePage.setCreatedByInput('createdBy'),
      custAcctBlackListUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAcctBlackListUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custAcctBlackListUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await custAcctBlackListUpdatePage.getBlackListIdInput()).to.eq('5', 'Expected blackListId value to be equals to 5');
    expect(await custAcctBlackListUpdatePage.getIdTypeInput()).to.eq('idType', 'Expected IdType value to be equals to idType');
    expect(await custAcctBlackListUpdatePage.getIdNumberInput()).to.eq('idNumber', 'Expected IdNumber value to be equals to idNumber');
    expect(await custAcctBlackListUpdatePage.getBlackListCodeInput()).to.eq(
      'blackListCode',
      'Expected BlackListCode value to be equals to blackListCode'
    );
    expect(await custAcctBlackListUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await custAcctBlackListUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await custAcctBlackListUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custAcctBlackListUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custAcctBlackListUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custAcctBlackListUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custAcctBlackListUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custAcctBlackListUpdatePage.save();
    expect(await custAcctBlackListUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custAcctBlackListComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CustAcctBlackList', async () => {
    const nbButtonsBeforeDelete = await custAcctBlackListComponentsPage.countDeleteButtons();
    await custAcctBlackListComponentsPage.clickOnLastDeleteButton();

    custAcctBlackListDeleteDialog = new CustAcctBlackListDeleteDialog();
    expect(await custAcctBlackListDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custAcctBlackList.delete.question');
    await custAcctBlackListDeleteDialog.clickOnConfirmButton();

    expect(await custAcctBlackListComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
