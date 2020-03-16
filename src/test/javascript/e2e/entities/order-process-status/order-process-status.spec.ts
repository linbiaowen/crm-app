import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OrderProcessStatusComponentsPage,
  OrderProcessStatusDeleteDialog,
  OrderProcessStatusUpdatePage
} from './order-process-status.page-object';

const expect = chai.expect;

describe('OrderProcessStatus e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderProcessStatusComponentsPage: OrderProcessStatusComponentsPage;
  let orderProcessStatusUpdatePage: OrderProcessStatusUpdatePage;
  let orderProcessStatusDeleteDialog: OrderProcessStatusDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderProcessStatuses', async () => {
    await navBarPage.goToEntity('order-process-status');
    orderProcessStatusComponentsPage = new OrderProcessStatusComponentsPage();
    await browser.wait(ec.visibilityOf(orderProcessStatusComponentsPage.title), 5000);
    expect(await orderProcessStatusComponentsPage.getTitle()).to.eq('crmwebApp.orderProcessStatus.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(orderProcessStatusComponentsPage.entities), ec.visibilityOf(orderProcessStatusComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OrderProcessStatus page', async () => {
    await orderProcessStatusComponentsPage.clickOnCreateButton();
    orderProcessStatusUpdatePage = new OrderProcessStatusUpdatePage();
    expect(await orderProcessStatusUpdatePage.getPageTitle()).to.eq('crmwebApp.orderProcessStatus.home.createOrEditLabel');
    await orderProcessStatusUpdatePage.cancel();
  });

  it('should create and save OrderProcessStatuses', async () => {
    const nbButtonsBeforeCreate = await orderProcessStatusComponentsPage.countDeleteButtons();

    await orderProcessStatusComponentsPage.clickOnCreateButton();

    await promise.all([
      orderProcessStatusUpdatePage.setOrderIdInput('orderId'),
      orderProcessStatusUpdatePage.entryOrderStatusSelectLastOption(),
      orderProcessStatusUpdatePage.exitOrderStatusSelectLastOption(),
      orderProcessStatusUpdatePage.setStatusUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusUpdatePage.setProcessNameInput('processName'),
      orderProcessStatusUpdatePage.statusSelectLastOption(),
      orderProcessStatusUpdatePage.setRemarksInput('remarks'),
      orderProcessStatusUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusUpdatePage.setCreatedByInput('createdBy'),
      orderProcessStatusUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      orderProcessStatusUpdatePage.setTenantIdInput('tenantId'),
      orderProcessStatusUpdatePage.orderMasterSelectLastOption()
    ]);

    expect(await orderProcessStatusUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await orderProcessStatusUpdatePage.getStatusUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected statusUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusUpdatePage.getProcessNameInput()).to.eq(
      'processName',
      'Expected ProcessName value to be equals to processName'
    );
    expect(await orderProcessStatusUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await orderProcessStatusUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await orderProcessStatusUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await orderProcessStatusUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await orderProcessStatusUpdatePage.save();
    expect(await orderProcessStatusUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderProcessStatusComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OrderProcessStatus', async () => {
    const nbButtonsBeforeDelete = await orderProcessStatusComponentsPage.countDeleteButtons();
    await orderProcessStatusComponentsPage.clickOnLastDeleteButton();

    orderProcessStatusDeleteDialog = new OrderProcessStatusDeleteDialog();
    expect(await orderProcessStatusDeleteDialog.getDialogTitle()).to.eq('crmwebApp.orderProcessStatus.delete.question');
    await orderProcessStatusDeleteDialog.clickOnConfirmButton();

    expect(await orderProcessStatusComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
