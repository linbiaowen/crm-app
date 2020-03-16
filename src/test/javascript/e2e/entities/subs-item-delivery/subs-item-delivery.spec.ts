import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SubsItemDeliveryComponentsPage, SubsItemDeliveryDeleteDialog, SubsItemDeliveryUpdatePage } from './subs-item-delivery.page-object';

const expect = chai.expect;

describe('SubsItemDelivery e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subsItemDeliveryComponentsPage: SubsItemDeliveryComponentsPage;
  let subsItemDeliveryUpdatePage: SubsItemDeliveryUpdatePage;
  let subsItemDeliveryDeleteDialog: SubsItemDeliveryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubsItemDeliveries', async () => {
    await navBarPage.goToEntity('subs-item-delivery');
    subsItemDeliveryComponentsPage = new SubsItemDeliveryComponentsPage();
    await browser.wait(ec.visibilityOf(subsItemDeliveryComponentsPage.title), 5000);
    expect(await subsItemDeliveryComponentsPage.getTitle()).to.eq('crmwebApp.subsItemDelivery.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subsItemDeliveryComponentsPage.entities), ec.visibilityOf(subsItemDeliveryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubsItemDelivery page', async () => {
    await subsItemDeliveryComponentsPage.clickOnCreateButton();
    subsItemDeliveryUpdatePage = new SubsItemDeliveryUpdatePage();
    expect(await subsItemDeliveryUpdatePage.getPageTitle()).to.eq('crmwebApp.subsItemDelivery.home.createOrEditLabel');
    await subsItemDeliveryUpdatePage.cancel();
  });

  it('should create and save SubsItemDeliveries', async () => {
    const nbButtonsBeforeCreate = await subsItemDeliveryComponentsPage.countDeleteButtons();

    await subsItemDeliveryComponentsPage.clickOnCreateButton();

    await promise.all([
      subsItemDeliveryUpdatePage.setDeliveryIdInput('5'),
      subsItemDeliveryUpdatePage.setOrderIdInput('orderId'),
      subsItemDeliveryUpdatePage.setSubscriptionIdInput('subscriptionId'),
      subsItemDeliveryUpdatePage.setSubscriptionItemIdInput('subscriptionItemId'),
      subsItemDeliveryUpdatePage.setDeliveryStatusInput('deliveryStatus'),
      subsItemDeliveryUpdatePage.setDeliveryMethodIdInput('deliveryMethodId'),
      subsItemDeliveryUpdatePage.setDeliveryRefCodeInput('deliveryRefCode'),
      subsItemDeliveryUpdatePage.setFileGenerationDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsItemDeliveryUpdatePage.setFileReceivedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsItemDeliveryUpdatePage.setDeliveryDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsItemDeliveryUpdatePage.setRemarksInput('remarks'),
      subsItemDeliveryUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsItemDeliveryUpdatePage.setCreatedByInput('createdBy'),
      subsItemDeliveryUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsItemDeliveryUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subsItemDeliveryUpdatePage.setTenantIdInput('tenantId'),
      subsItemDeliveryUpdatePage.orderMasterSelectLastOption()
    ]);

    expect(await subsItemDeliveryUpdatePage.getDeliveryIdInput()).to.eq('5', 'Expected deliveryId value to be equals to 5');
    expect(await subsItemDeliveryUpdatePage.getOrderIdInput()).to.eq('orderId', 'Expected OrderId value to be equals to orderId');
    expect(await subsItemDeliveryUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await subsItemDeliveryUpdatePage.getSubscriptionItemIdInput()).to.eq(
      'subscriptionItemId',
      'Expected SubscriptionItemId value to be equals to subscriptionItemId'
    );
    expect(await subsItemDeliveryUpdatePage.getDeliveryStatusInput()).to.eq(
      'deliveryStatus',
      'Expected DeliveryStatus value to be equals to deliveryStatus'
    );
    expect(await subsItemDeliveryUpdatePage.getDeliveryMethodIdInput()).to.eq(
      'deliveryMethodId',
      'Expected DeliveryMethodId value to be equals to deliveryMethodId'
    );
    expect(await subsItemDeliveryUpdatePage.getDeliveryRefCodeInput()).to.eq(
      'deliveryRefCode',
      'Expected DeliveryRefCode value to be equals to deliveryRefCode'
    );
    expect(await subsItemDeliveryUpdatePage.getFileGenerationDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected fileGenerationDate value to be equals to 2000-12-31'
    );
    expect(await subsItemDeliveryUpdatePage.getFileReceivedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected fileReceivedDate value to be equals to 2000-12-31'
    );
    expect(await subsItemDeliveryUpdatePage.getDeliveryDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected deliveryDate value to be equals to 2000-12-31'
    );
    expect(await subsItemDeliveryUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await subsItemDeliveryUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subsItemDeliveryUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await subsItemDeliveryUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subsItemDeliveryUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subsItemDeliveryUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subsItemDeliveryUpdatePage.save();
    expect(await subsItemDeliveryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subsItemDeliveryComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubsItemDelivery', async () => {
    const nbButtonsBeforeDelete = await subsItemDeliveryComponentsPage.countDeleteButtons();
    await subsItemDeliveryComponentsPage.clickOnLastDeleteButton();

    subsItemDeliveryDeleteDialog = new SubsItemDeliveryDeleteDialog();
    expect(await subsItemDeliveryDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subsItemDelivery.delete.question');
    await subsItemDeliveryDeleteDialog.clickOnConfirmButton();

    expect(await subsItemDeliveryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
