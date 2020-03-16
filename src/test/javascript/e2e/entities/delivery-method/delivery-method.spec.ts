import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeliveryMethodComponentsPage, DeliveryMethodDeleteDialog, DeliveryMethodUpdatePage } from './delivery-method.page-object';

const expect = chai.expect;

describe('DeliveryMethod e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deliveryMethodComponentsPage: DeliveryMethodComponentsPage;
  let deliveryMethodUpdatePage: DeliveryMethodUpdatePage;
  let deliveryMethodDeleteDialog: DeliveryMethodDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DeliveryMethods', async () => {
    await navBarPage.goToEntity('delivery-method');
    deliveryMethodComponentsPage = new DeliveryMethodComponentsPage();
    await browser.wait(ec.visibilityOf(deliveryMethodComponentsPage.title), 5000);
    expect(await deliveryMethodComponentsPage.getTitle()).to.eq('crmwebApp.deliveryMethod.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(deliveryMethodComponentsPage.entities), ec.visibilityOf(deliveryMethodComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DeliveryMethod page', async () => {
    await deliveryMethodComponentsPage.clickOnCreateButton();
    deliveryMethodUpdatePage = new DeliveryMethodUpdatePage();
    expect(await deliveryMethodUpdatePage.getPageTitle()).to.eq('crmwebApp.deliveryMethod.home.createOrEditLabel');
    await deliveryMethodUpdatePage.cancel();
  });

  it('should create and save DeliveryMethods', async () => {
    const nbButtonsBeforeCreate = await deliveryMethodComponentsPage.countDeleteButtons();

    await deliveryMethodComponentsPage.clickOnCreateButton();

    await promise.all([
      deliveryMethodUpdatePage.setDeliveryMethodIdInput('5'),
      deliveryMethodUpdatePage.setDeliveryMethodInput('deliveryMethod'),
      deliveryMethodUpdatePage.setDeliveryMethodDescInput('deliveryMethodDesc'),
      deliveryMethodUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryMethodUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryMethodUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryMethodUpdatePage.setCreatedByInput('createdBy'),
      deliveryMethodUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryMethodUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      deliveryMethodUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await deliveryMethodUpdatePage.getDeliveryMethodIdInput()).to.eq('5', 'Expected deliveryMethodId value to be equals to 5');
    expect(await deliveryMethodUpdatePage.getDeliveryMethodInput()).to.eq(
      'deliveryMethod',
      'Expected DeliveryMethod value to be equals to deliveryMethod'
    );
    expect(await deliveryMethodUpdatePage.getDeliveryMethodDescInput()).to.eq(
      'deliveryMethodDesc',
      'Expected DeliveryMethodDesc value to be equals to deliveryMethodDesc'
    );
    expect(await deliveryMethodUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await deliveryMethodUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await deliveryMethodUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await deliveryMethodUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await deliveryMethodUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await deliveryMethodUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await deliveryMethodUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await deliveryMethodUpdatePage.save();
    expect(await deliveryMethodUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deliveryMethodComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DeliveryMethod', async () => {
    const nbButtonsBeforeDelete = await deliveryMethodComponentsPage.countDeleteButtons();
    await deliveryMethodComponentsPage.clickOnLastDeleteButton();

    deliveryMethodDeleteDialog = new DeliveryMethodDeleteDialog();
    expect(await deliveryMethodDeleteDialog.getDialogTitle()).to.eq('crmwebApp.deliveryMethod.delete.question');
    await deliveryMethodDeleteDialog.clickOnConfirmButton();

    expect(await deliveryMethodComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
