import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubscriptionProvisionComponentsPage,
  SubscriptionProvisionDeleteDialog,
  SubscriptionProvisionUpdatePage
} from './subscription-provision.page-object';

const expect = chai.expect;

describe('SubscriptionProvision e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subscriptionProvisionComponentsPage: SubscriptionProvisionComponentsPage;
  let subscriptionProvisionUpdatePage: SubscriptionProvisionUpdatePage;
  let subscriptionProvisionDeleteDialog: SubscriptionProvisionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubscriptionProvisions', async () => {
    await navBarPage.goToEntity('subscription-provision');
    subscriptionProvisionComponentsPage = new SubscriptionProvisionComponentsPage();
    await browser.wait(ec.visibilityOf(subscriptionProvisionComponentsPage.title), 5000);
    expect(await subscriptionProvisionComponentsPage.getTitle()).to.eq('crmwebApp.subscriptionProvision.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subscriptionProvisionComponentsPage.entities), ec.visibilityOf(subscriptionProvisionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubscriptionProvision page', async () => {
    await subscriptionProvisionComponentsPage.clickOnCreateButton();
    subscriptionProvisionUpdatePage = new SubscriptionProvisionUpdatePage();
    expect(await subscriptionProvisionUpdatePage.getPageTitle()).to.eq('crmwebApp.subscriptionProvision.home.createOrEditLabel');
    await subscriptionProvisionUpdatePage.cancel();
  });

  it('should create and save SubscriptionProvisions', async () => {
    const nbButtonsBeforeCreate = await subscriptionProvisionComponentsPage.countDeleteButtons();

    await subscriptionProvisionComponentsPage.clickOnCreateButton();

    await promise.all([
      subscriptionProvisionUpdatePage.setProvisionSeqIdInput('provisionSeqId'),
      subscriptionProvisionUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subscriptionProvisionUpdatePage.setOrderIdInput('orderId'),
      subscriptionProvisionUpdatePage.setProductIdInput('productId'),
      subscriptionProvisionUpdatePage.setMsisdnInput('msisdn'),
      subscriptionProvisionUpdatePage.setIccidInput('iccid'),
      subscriptionProvisionUpdatePage.setImsiInput('imsi'),
      subscriptionProvisionUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      subscriptionProvisionUpdatePage.setResourceSpecIdInput('resourceSpecId'),
      subscriptionProvisionUpdatePage.setRfsInput('rfs'),
      subscriptionProvisionUpdatePage.setProvisionStatusInput('provisionStatus'),
      subscriptionProvisionUpdatePage.setProvisionStatusDescInput('provisionStatusDesc'),
      subscriptionProvisionUpdatePage.setProvisionResponseInput('provisionResponse'),
      subscriptionProvisionUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProvisionUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProvisionUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProvisionUpdatePage.setCreatedByInput('createdBy'),
      subscriptionProvisionUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionProvisionUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subscriptionProvisionUpdatePage.setTenantIdInput('tenantId'),
      subscriptionProvisionUpdatePage.orderMasterSelectLastOption()
    ]);

    expect(await subscriptionProvisionUpdatePage.getProvisionSeqIdInput()).to.eq(
      'provisionSeqId',
      'Expected ProvisionSeqId value to be equals to provisionSeqId'
    );
    expect(await subscriptionProvisionUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subscriptionProvisionUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await subscriptionProvisionUpdatePage.getProductIdInput()).to.eq(
      'productId',
      'Expected ProductId value to be equals to productId'
    );
    expect(await subscriptionProvisionUpdatePage.getMsisdnInput()).to.eq('msisdn', 'Expected Msisdn value to be equals to msisdn');
    expect(await subscriptionProvisionUpdatePage.getIccidInput()).to.eq('iccid', 'Expected Iccid value to be equals to iccid');
    expect(await subscriptionProvisionUpdatePage.getImsiInput()).to.eq('imsi', 'Expected Imsi value to be equals to imsi');
    expect(await subscriptionProvisionUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    expect(await subscriptionProvisionUpdatePage.getResourceSpecIdInput()).to.eq(
      'resourceSpecId',
      'Expected ResourceSpecId value to be equals to resourceSpecId'
    );
    expect(await subscriptionProvisionUpdatePage.getRfsInput()).to.eq('rfs', 'Expected Rfs value to be equals to rfs');
    expect(await subscriptionProvisionUpdatePage.getProvisionStatusInput()).to.eq(
      'provisionStatus',
      'Expected ProvisionStatus value to be equals to provisionStatus'
    );
    expect(await subscriptionProvisionUpdatePage.getProvisionStatusDescInput()).to.eq(
      'provisionStatusDesc',
      'Expected ProvisionStatusDesc value to be equals to provisionStatusDesc'
    );
    expect(await subscriptionProvisionUpdatePage.getProvisionResponseInput()).to.eq(
      'provisionResponse',
      'Expected ProvisionResponse value to be equals to provisionResponse'
    );
    expect(await subscriptionProvisionUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProvisionUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProvisionUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProvisionUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await subscriptionProvisionUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionProvisionUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subscriptionProvisionUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subscriptionProvisionUpdatePage.save();
    expect(await subscriptionProvisionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subscriptionProvisionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubscriptionProvision', async () => {
    const nbButtonsBeforeDelete = await subscriptionProvisionComponentsPage.countDeleteButtons();
    await subscriptionProvisionComponentsPage.clickOnLastDeleteButton();

    subscriptionProvisionDeleteDialog = new SubscriptionProvisionDeleteDialog();
    expect(await subscriptionProvisionDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subscriptionProvision.delete.question');
    await subscriptionProvisionDeleteDialog.clickOnConfirmButton();

    expect(await subscriptionProvisionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
