import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CustCommOptoutMasterComponentsPage,
  CustCommOptoutMasterDeleteDialog,
  CustCommOptoutMasterUpdatePage
} from './cust-comm-optout-master.page-object';

const expect = chai.expect;

describe('CustCommOptoutMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custCommOptoutMasterComponentsPage: CustCommOptoutMasterComponentsPage;
  let custCommOptoutMasterUpdatePage: CustCommOptoutMasterUpdatePage;
  let custCommOptoutMasterDeleteDialog: CustCommOptoutMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustCommOptoutMasters', async () => {
    await navBarPage.goToEntity('cust-comm-optout-master');
    custCommOptoutMasterComponentsPage = new CustCommOptoutMasterComponentsPage();
    await browser.wait(ec.visibilityOf(custCommOptoutMasterComponentsPage.title), 5000);
    expect(await custCommOptoutMasterComponentsPage.getTitle()).to.eq('crmwebApp.custCommOptoutMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custCommOptoutMasterComponentsPage.entities), ec.visibilityOf(custCommOptoutMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustCommOptoutMaster page', async () => {
    await custCommOptoutMasterComponentsPage.clickOnCreateButton();
    custCommOptoutMasterUpdatePage = new CustCommOptoutMasterUpdatePage();
    expect(await custCommOptoutMasterUpdatePage.getPageTitle()).to.eq('crmwebApp.custCommOptoutMaster.home.createOrEditLabel');
    await custCommOptoutMasterUpdatePage.cancel();
  });

  it('should create and save CustCommOptoutMasters', async () => {
    const nbButtonsBeforeCreate = await custCommOptoutMasterComponentsPage.countDeleteButtons();

    await custCommOptoutMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      custCommOptoutMasterUpdatePage.setOptoutIdInput('optoutId'),
      custCommOptoutMasterUpdatePage.setCustAcctIdInput('custAcctId'),
      custCommOptoutMasterUpdatePage.setSubscriptionIdInput('subscriptionId'),
      custCommOptoutMasterUpdatePage.setPrimaryMobNbrInput('primaryMobNbr'),
      custCommOptoutMasterUpdatePage.setOptoutTypeIdInput('optoutTypeId'),
      custCommOptoutMasterUpdatePage.setOptoutMediaIdInput('optoutMediaId'),
      custCommOptoutMasterUpdatePage.setOptoutStatusInput('optoutStatus'),
      custCommOptoutMasterUpdatePage.setOptoutStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custCommOptoutMasterUpdatePage.setOptoutEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custCommOptoutMasterUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custCommOptoutMasterUpdatePage.setCreatedByInput('createdBy'),
      custCommOptoutMasterUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custCommOptoutMasterUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custCommOptoutMasterUpdatePage.setTenantIdInput('tenantId'),
      custCommOptoutMasterUpdatePage.customerSelectLastOption(),
      custCommOptoutMasterUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await custCommOptoutMasterUpdatePage.getOptoutIdInput()).to.eq('optoutId', 'Expected OptoutId value to be equals to optoutId');
    expect(await custCommOptoutMasterUpdatePage.getCustAcctIdInput()).to.eq(
      'custAcctId',
      'Expected CustAcctId value to be equals to custAcctId'
    );
    expect(await custCommOptoutMasterUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await custCommOptoutMasterUpdatePage.getPrimaryMobNbrInput()).to.eq(
      'primaryMobNbr',
      'Expected PrimaryMobNbr value to be equals to primaryMobNbr'
    );
    expect(await custCommOptoutMasterUpdatePage.getOptoutTypeIdInput()).to.eq(
      'optoutTypeId',
      'Expected OptoutTypeId value to be equals to optoutTypeId'
    );
    expect(await custCommOptoutMasterUpdatePage.getOptoutMediaIdInput()).to.eq(
      'optoutMediaId',
      'Expected OptoutMediaId value to be equals to optoutMediaId'
    );
    expect(await custCommOptoutMasterUpdatePage.getOptoutStatusInput()).to.eq(
      'optoutStatus',
      'Expected OptoutStatus value to be equals to optoutStatus'
    );
    expect(await custCommOptoutMasterUpdatePage.getOptoutStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected optoutStartDate value to be equals to 2000-12-31'
    );
    expect(await custCommOptoutMasterUpdatePage.getOptoutEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected optoutEndDate value to be equals to 2000-12-31'
    );
    expect(await custCommOptoutMasterUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custCommOptoutMasterUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await custCommOptoutMasterUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custCommOptoutMasterUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custCommOptoutMasterUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custCommOptoutMasterUpdatePage.save();
    expect(await custCommOptoutMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custCommOptoutMasterComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CustCommOptoutMaster', async () => {
    const nbButtonsBeforeDelete = await custCommOptoutMasterComponentsPage.countDeleteButtons();
    await custCommOptoutMasterComponentsPage.clickOnLastDeleteButton();

    custCommOptoutMasterDeleteDialog = new CustCommOptoutMasterDeleteDialog();
    expect(await custCommOptoutMasterDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custCommOptoutMaster.delete.question');
    await custCommOptoutMasterDeleteDialog.clickOnConfirmButton();

    expect(await custCommOptoutMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
