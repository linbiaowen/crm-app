import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderMasterComponentsPage, OrderMasterDeleteDialog, OrderMasterUpdatePage } from './order-master.page-object';

const expect = chai.expect;

describe('OrderMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderMasterComponentsPage: OrderMasterComponentsPage;
  let orderMasterUpdatePage: OrderMasterUpdatePage;
  let orderMasterDeleteDialog: OrderMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderMasters', async () => {
    await navBarPage.goToEntity('order-master');
    orderMasterComponentsPage = new OrderMasterComponentsPage();
    await browser.wait(ec.visibilityOf(orderMasterComponentsPage.title), 5000);
    expect(await orderMasterComponentsPage.getTitle()).to.eq('crmwebApp.orderMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(orderMasterComponentsPage.entities), ec.visibilityOf(orderMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create OrderMaster page', async () => {
    await orderMasterComponentsPage.clickOnCreateButton();
    orderMasterUpdatePage = new OrderMasterUpdatePage();
    expect(await orderMasterUpdatePage.getPageTitle()).to.eq('crmwebApp.orderMaster.home.createOrEditLabel');
    await orderMasterUpdatePage.cancel();
  });

  it('should create and save OrderMasters', async () => {
    const nbButtonsBeforeCreate = await orderMasterComponentsPage.countDeleteButtons();

    await orderMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      orderMasterUpdatePage.setOrderIdInput('orderId'),
      orderMasterUpdatePage.setCustAcctIdInput('custAcctId'),
      orderMasterUpdatePage.setSubscriptionIdInput('subscriptionId'),
      orderMasterUpdatePage.orderTypeSelectLastOption(),
      orderMasterUpdatePage.subOrderTypeSelectLastOption(),
      orderMasterUpdatePage.orderNatureSelectLastOption(),
      orderMasterUpdatePage.orderStatusSelectLastOption(),
      orderMasterUpdatePage.setRemarksInput('remarks'),
      orderMasterUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderMasterUpdatePage.setCreatedByInput('createdBy'),
      orderMasterUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderMasterUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      orderMasterUpdatePage.setTenantIdInput('tenantId'),
      orderMasterUpdatePage.customerSelectLastOption(),
      orderMasterUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await orderMasterUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await orderMasterUpdatePage.getCustAcctIdInput()).to.eq('custAcctId', 'Expected CustAcctId value to be equals to custAcctId');
    expect(await orderMasterUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    const selectedIsChangePlan = orderMasterUpdatePage.getIsChangePlanInput();
    if (await selectedIsChangePlan.isSelected()) {
      await orderMasterUpdatePage.getIsChangePlanInput().click();
      expect(await orderMasterUpdatePage.getIsChangePlanInput().isSelected(), 'Expected isChangePlan not to be selected').to.be.false;
    } else {
      await orderMasterUpdatePage.getIsChangePlanInput().click();
      expect(await orderMasterUpdatePage.getIsChangePlanInput().isSelected(), 'Expected isChangePlan to be selected').to.be.true;
    }
    expect(await orderMasterUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await orderMasterUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await orderMasterUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await orderMasterUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await orderMasterUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await orderMasterUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await orderMasterUpdatePage.save();
    expect(await orderMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last OrderMaster', async () => {
    const nbButtonsBeforeDelete = await orderMasterComponentsPage.countDeleteButtons();
    await orderMasterComponentsPage.clickOnLastDeleteButton();

    orderMasterDeleteDialog = new OrderMasterDeleteDialog();
    expect(await orderMasterDeleteDialog.getDialogTitle()).to.eq('crmwebApp.orderMaster.delete.question');
    await orderMasterDeleteDialog.clickOnConfirmButton();

    expect(await orderMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
