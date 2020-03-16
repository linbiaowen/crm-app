import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubsPurchaseControlComponentsPage,
  SubsPurchaseControlDeleteDialog,
  SubsPurchaseControlUpdatePage
} from './subs-purchase-control.page-object';

const expect = chai.expect;

describe('SubsPurchaseControl e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subsPurchaseControlComponentsPage: SubsPurchaseControlComponentsPage;
  let subsPurchaseControlUpdatePage: SubsPurchaseControlUpdatePage;
  let subsPurchaseControlDeleteDialog: SubsPurchaseControlDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubsPurchaseControls', async () => {
    await navBarPage.goToEntity('subs-purchase-control');
    subsPurchaseControlComponentsPage = new SubsPurchaseControlComponentsPage();
    await browser.wait(ec.visibilityOf(subsPurchaseControlComponentsPage.title), 5000);
    expect(await subsPurchaseControlComponentsPage.getTitle()).to.eq('crmwebApp.subsPurchaseControl.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subsPurchaseControlComponentsPage.entities), ec.visibilityOf(subsPurchaseControlComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubsPurchaseControl page', async () => {
    await subsPurchaseControlComponentsPage.clickOnCreateButton();
    subsPurchaseControlUpdatePage = new SubsPurchaseControlUpdatePage();
    expect(await subsPurchaseControlUpdatePage.getPageTitle()).to.eq('crmwebApp.subsPurchaseControl.home.createOrEditLabel');
    await subsPurchaseControlUpdatePage.cancel();
  });

  it('should create and save SubsPurchaseControls', async () => {
    const nbButtonsBeforeCreate = await subsPurchaseControlComponentsPage.countDeleteButtons();

    await subsPurchaseControlComponentsPage.clickOnCreateButton();

    await promise.all([
      subsPurchaseControlUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subsPurchaseControlUpdatePage.setServiceTypeInput('serviceType'),
      subsPurchaseControlUpdatePage.setPurchaseControlFlagInput('purchaseControlFlag'),
      subsPurchaseControlUpdatePage.statusSelectLastOption(),
      subsPurchaseControlUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsPurchaseControlUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsPurchaseControlUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsPurchaseControlUpdatePage.setCreatedByInput('createdBy'),
      subsPurchaseControlUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsPurchaseControlUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subsPurchaseControlUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await subsPurchaseControlUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subsPurchaseControlUpdatePage.getServiceTypeInput()).to.eq(
      'serviceType',
      'Expected ServiceType value to be equals to serviceType'
    );
    expect(await subsPurchaseControlUpdatePage.getPurchaseControlFlagInput()).to.eq(
      'purchaseControlFlag',
      'Expected PurchaseControlFlag value to be equals to purchaseControlFlag'
    );
    expect(await subsPurchaseControlUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await subsPurchaseControlUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subsPurchaseControlUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subsPurchaseControlUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await subsPurchaseControlUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subsPurchaseControlUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subsPurchaseControlUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subsPurchaseControlUpdatePage.save();
    expect(await subsPurchaseControlUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subsPurchaseControlComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubsPurchaseControl', async () => {
    const nbButtonsBeforeDelete = await subsPurchaseControlComponentsPage.countDeleteButtons();
    await subsPurchaseControlComponentsPage.clickOnLastDeleteButton();

    subsPurchaseControlDeleteDialog = new SubsPurchaseControlDeleteDialog();
    expect(await subsPurchaseControlDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subsPurchaseControl.delete.question');
    await subsPurchaseControlDeleteDialog.clickOnConfirmButton();

    expect(await subsPurchaseControlComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
