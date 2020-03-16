import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OrderProcessConfigComponentsPage,
  OrderProcessConfigDeleteDialog,
  OrderProcessConfigUpdatePage
} from './order-process-config.page-object';

const expect = chai.expect;

describe('OrderProcessConfig e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderProcessConfigComponentsPage: OrderProcessConfigComponentsPage;
  let orderProcessConfigUpdatePage: OrderProcessConfigUpdatePage;
  let orderProcessConfigDeleteDialog: OrderProcessConfigDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderProcessConfigs', async () => {
    await navBarPage.goToEntity('order-process-config');
    orderProcessConfigComponentsPage = new OrderProcessConfigComponentsPage();
    await browser.wait(ec.visibilityOf(orderProcessConfigComponentsPage.title), 5000);
    expect(await orderProcessConfigComponentsPage.getTitle()).to.eq('crmwebApp.orderProcessConfig.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(orderProcessConfigComponentsPage.entities), ec.visibilityOf(orderProcessConfigComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OrderProcessConfig page', async () => {
    await orderProcessConfigComponentsPage.clickOnCreateButton();
    orderProcessConfigUpdatePage = new OrderProcessConfigUpdatePage();
    expect(await orderProcessConfigUpdatePage.getPageTitle()).to.eq('crmwebApp.orderProcessConfig.home.createOrEditLabel');
    await orderProcessConfigUpdatePage.cancel();
  });

  it('should create and save OrderProcessConfigs', async () => {
    const nbButtonsBeforeCreate = await orderProcessConfigComponentsPage.countDeleteButtons();

    await orderProcessConfigComponentsPage.clickOnCreateButton();

    await promise.all([
      orderProcessConfigUpdatePage.setOrderTypeInput('orderType'),
      orderProcessConfigUpdatePage.setSubOrderTypeInput('subOrderType'),
      orderProcessConfigUpdatePage.setOrderNatureInput('orderNature'),
      orderProcessConfigUpdatePage.setProcessNameInput('processName'),
      orderProcessConfigUpdatePage.setChildProcessNameInput('childProcessName'),
      orderProcessConfigUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessConfigUpdatePage.setCreatedByInput('createdBy'),
      orderProcessConfigUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessConfigUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      orderProcessConfigUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await orderProcessConfigUpdatePage.getOrderTypeInput()).to.eq('orderType', 'Expected OrderType value to be equals to orderType');
    expect(await orderProcessConfigUpdatePage.getSubOrderTypeInput()).to.eq(
      'subOrderType',
      'Expected SubOrderType value to be equals to subOrderType'
    );
    expect(await orderProcessConfigUpdatePage.getOrderNatureInput()).to.eq(
      'orderNature',
      'Expected OrderNature value to be equals to orderNature'
    );
    expect(await orderProcessConfigUpdatePage.getProcessNameInput()).to.eq(
      'processName',
      'Expected ProcessName value to be equals to processName'
    );
    expect(await orderProcessConfigUpdatePage.getChildProcessNameInput()).to.eq(
      'childProcessName',
      'Expected ChildProcessName value to be equals to childProcessName'
    );
    expect(await orderProcessConfigUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessConfigUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await orderProcessConfigUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessConfigUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await orderProcessConfigUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await orderProcessConfigUpdatePage.save();
    expect(await orderProcessConfigUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderProcessConfigComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OrderProcessConfig', async () => {
    const nbButtonsBeforeDelete = await orderProcessConfigComponentsPage.countDeleteButtons();
    await orderProcessConfigComponentsPage.clickOnLastDeleteButton();

    orderProcessConfigDeleteDialog = new OrderProcessConfigDeleteDialog();
    expect(await orderProcessConfigDeleteDialog.getDialogTitle()).to.eq('crmwebApp.orderProcessConfig.delete.question');
    await orderProcessConfigDeleteDialog.clickOnConfirmButton();

    expect(await orderProcessConfigComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
