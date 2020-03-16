import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubsDeliveryItemDetailsComponentsPage,
  SubsDeliveryItemDetailsDeleteDialog,
  SubsDeliveryItemDetailsUpdatePage
} from './subs-delivery-item-details.page-object';

const expect = chai.expect;

describe('SubsDeliveryItemDetails e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subsDeliveryItemDetailsComponentsPage: SubsDeliveryItemDetailsComponentsPage;
  let subsDeliveryItemDetailsUpdatePage: SubsDeliveryItemDetailsUpdatePage;
  let subsDeliveryItemDetailsDeleteDialog: SubsDeliveryItemDetailsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubsDeliveryItemDetails', async () => {
    await navBarPage.goToEntity('subs-delivery-item-details');
    subsDeliveryItemDetailsComponentsPage = new SubsDeliveryItemDetailsComponentsPage();
    await browser.wait(ec.visibilityOf(subsDeliveryItemDetailsComponentsPage.title), 5000);
    expect(await subsDeliveryItemDetailsComponentsPage.getTitle()).to.eq('crmwebApp.subsDeliveryItemDetails.home.title');
    await browser.wait(
      ec.or(
        ec.visibilityOf(subsDeliveryItemDetailsComponentsPage.entities),
        ec.visibilityOf(subsDeliveryItemDetailsComponentsPage.noResult)
      ),
      1000
    );
  });

  it('should load create SubsDeliveryItemDetails page', async () => {
    await subsDeliveryItemDetailsComponentsPage.clickOnCreateButton();
    subsDeliveryItemDetailsUpdatePage = new SubsDeliveryItemDetailsUpdatePage();
    expect(await subsDeliveryItemDetailsUpdatePage.getPageTitle()).to.eq('crmwebApp.subsDeliveryItemDetails.home.createOrEditLabel');
    await subsDeliveryItemDetailsUpdatePage.cancel();
  });

  it('should create and save SubsDeliveryItemDetails', async () => {
    const nbButtonsBeforeCreate = await subsDeliveryItemDetailsComponentsPage.countDeleteButtons();

    await subsDeliveryItemDetailsComponentsPage.clickOnCreateButton();

    await promise.all([
      subsDeliveryItemDetailsUpdatePage.setSubscriptionItemIdInput('5'),
      subsDeliveryItemDetailsUpdatePage.setDeliveryIdInput('5'),
      subsDeliveryItemDetailsUpdatePage.setProductIdInput('productId'),
      subsDeliveryItemDetailsUpdatePage.setProductNameInput('productName'),
      subsDeliveryItemDetailsUpdatePage.setDeviceTypeInput('deviceType'),
      subsDeliveryItemDetailsUpdatePage.setDeviceModelInput('deviceModel'),
      subsDeliveryItemDetailsUpdatePage.setDeviceSerialNbrInput('deviceSerialNbr'),
      subsDeliveryItemDetailsUpdatePage.setImeiInput('imei'),
      subsDeliveryItemDetailsUpdatePage.setProductThemeInput('productTheme'),
      subsDeliveryItemDetailsUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsDeliveryItemDetailsUpdatePage.setCreatedByInput('createdBy'),
      subsDeliveryItemDetailsUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subsDeliveryItemDetailsUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subsDeliveryItemDetailsUpdatePage.setTenantIdInput('tenantId'),
      subsDeliveryItemDetailsUpdatePage.subsItemDeliverySelectLastOption()
    ]);

    expect(await subsDeliveryItemDetailsUpdatePage.getSubscriptionItemIdInput()).to.eq(
      '5',
      'Expected subscriptionItemId value to be equals to 5'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getDeliveryIdInput()).to.eq('5', 'Expected deliveryId value to be equals to 5');
    expect(await subsDeliveryItemDetailsUpdatePage.getProductIdInput()).to.eq(
      'productId',
      'Expected ProductId value to be equals to productId'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getProductNameInput()).to.eq(
      'productName',
      'Expected ProductName value to be equals to productName'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getDeviceTypeInput()).to.eq(
      'deviceType',
      'Expected DeviceType value to be equals to deviceType'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getDeviceModelInput()).to.eq(
      'deviceModel',
      'Expected DeviceModel value to be equals to deviceModel'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getDeviceSerialNbrInput()).to.eq(
      'deviceSerialNbr',
      'Expected DeviceSerialNbr value to be equals to deviceSerialNbr'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getImeiInput()).to.eq('imei', 'Expected Imei value to be equals to imei');
    expect(await subsDeliveryItemDetailsUpdatePage.getProductThemeInput()).to.eq(
      'productTheme',
      'Expected ProductTheme value to be equals to productTheme'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subsDeliveryItemDetailsUpdatePage.getTenantIdInput()).to.eq(
      'tenantId',
      'Expected TenantId value to be equals to tenantId'
    );

    await subsDeliveryItemDetailsUpdatePage.save();
    expect(await subsDeliveryItemDetailsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subsDeliveryItemDetailsComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubsDeliveryItemDetails', async () => {
    const nbButtonsBeforeDelete = await subsDeliveryItemDetailsComponentsPage.countDeleteButtons();
    await subsDeliveryItemDetailsComponentsPage.clickOnLastDeleteButton();

    subsDeliveryItemDetailsDeleteDialog = new SubsDeliveryItemDetailsDeleteDialog();
    expect(await subsDeliveryItemDetailsDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subsDeliveryItemDetails.delete.question');
    await subsDeliveryItemDetailsDeleteDialog.clickOnConfirmButton();

    expect(await subsDeliveryItemDetailsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
