import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  OrderProcessStatusHistoryComponentsPage,
  OrderProcessStatusHistoryDeleteDialog,
  OrderProcessStatusHistoryUpdatePage
} from './order-process-status-history.page-object';

const expect = chai.expect;

describe('OrderProcessStatusHistory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderProcessStatusHistoryComponentsPage: OrderProcessStatusHistoryComponentsPage;
  let orderProcessStatusHistoryUpdatePage: OrderProcessStatusHistoryUpdatePage;
  let orderProcessStatusHistoryDeleteDialog: OrderProcessStatusHistoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderProcessStatusHistories', async () => {
    await navBarPage.goToEntity('order-process-status-history');
    orderProcessStatusHistoryComponentsPage = new OrderProcessStatusHistoryComponentsPage();
    await browser.wait(ec.visibilityOf(orderProcessStatusHistoryComponentsPage.title), 5000);
    expect(await orderProcessStatusHistoryComponentsPage.getTitle()).to.eq('crmwebApp.orderProcessStatusHistory.home.title');
    await browser.wait(
      ec.or(
        ec.visibilityOf(orderProcessStatusHistoryComponentsPage.entities),
        ec.visibilityOf(orderProcessStatusHistoryComponentsPage.noResult)
      ),
      1000
    );
  });

  it('should load create OrderProcessStatusHistory page', async () => {
    await orderProcessStatusHistoryComponentsPage.clickOnCreateButton();
    orderProcessStatusHistoryUpdatePage = new OrderProcessStatusHistoryUpdatePage();
    expect(await orderProcessStatusHistoryUpdatePage.getPageTitle()).to.eq('crmwebApp.orderProcessStatusHistory.home.createOrEditLabel');
    await orderProcessStatusHistoryUpdatePage.cancel();
  });

  it('should create and save OrderProcessStatusHistories', async () => {
    const nbButtonsBeforeCreate = await orderProcessStatusHistoryComponentsPage.countDeleteButtons();

    await orderProcessStatusHistoryComponentsPage.clickOnCreateButton();

    await promise.all([
      orderProcessStatusHistoryUpdatePage.setOrderIdInput('orderId'),
      orderProcessStatusHistoryUpdatePage.entryOrderStatusSelectLastOption(),
      orderProcessStatusHistoryUpdatePage.exitOrderStatusSelectLastOption(),
      orderProcessStatusHistoryUpdatePage.setStatusUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusHistoryUpdatePage.setProcessNameInput('processName'),
      orderProcessStatusHistoryUpdatePage.statusSelectLastOption(),
      orderProcessStatusHistoryUpdatePage.setRemarksInput('remarks'),
      orderProcessStatusHistoryUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusHistoryUpdatePage.setCreatedByInput('createdBy'),
      orderProcessStatusHistoryUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderProcessStatusHistoryUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      orderProcessStatusHistoryUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await orderProcessStatusHistoryUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await orderProcessStatusHistoryUpdatePage.getStatusUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected statusUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getProcessNameInput()).to.eq(
      'processName',
      'Expected ProcessName value to be equals to processName'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await orderProcessStatusHistoryUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await orderProcessStatusHistoryUpdatePage.getTenantIdInput()).to.eq(
      'tenantId',
      'Expected TenantId value to be equals to tenantId'
    );

    await orderProcessStatusHistoryUpdatePage.save();
    expect(await orderProcessStatusHistoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderProcessStatusHistoryComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last OrderProcessStatusHistory', async () => {
    const nbButtonsBeforeDelete = await orderProcessStatusHistoryComponentsPage.countDeleteButtons();
    await orderProcessStatusHistoryComponentsPage.clickOnLastDeleteButton();

    orderProcessStatusHistoryDeleteDialog = new OrderProcessStatusHistoryDeleteDialog();
    expect(await orderProcessStatusHistoryDeleteDialog.getDialogTitle()).to.eq('crmwebApp.orderProcessStatusHistory.delete.question');
    await orderProcessStatusHistoryDeleteDialog.clickOnConfirmButton();

    expect(await orderProcessStatusHistoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
