import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeliveryOptionComponentsPage, DeliveryOptionDeleteDialog, DeliveryOptionUpdatePage } from './delivery-option.page-object';

const expect = chai.expect;

describe('DeliveryOption e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deliveryOptionComponentsPage: DeliveryOptionComponentsPage;
  let deliveryOptionUpdatePage: DeliveryOptionUpdatePage;
  let deliveryOptionDeleteDialog: DeliveryOptionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DeliveryOptions', async () => {
    await navBarPage.goToEntity('delivery-option');
    deliveryOptionComponentsPage = new DeliveryOptionComponentsPage();
    await browser.wait(ec.visibilityOf(deliveryOptionComponentsPage.title), 5000);
    expect(await deliveryOptionComponentsPage.getTitle()).to.eq('crmwebApp.deliveryOption.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(deliveryOptionComponentsPage.entities), ec.visibilityOf(deliveryOptionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DeliveryOption page', async () => {
    await deliveryOptionComponentsPage.clickOnCreateButton();
    deliveryOptionUpdatePage = new DeliveryOptionUpdatePage();
    expect(await deliveryOptionUpdatePage.getPageTitle()).to.eq('crmwebApp.deliveryOption.home.createOrEditLabel');
    await deliveryOptionUpdatePage.cancel();
  });

  it('should create and save DeliveryOptions', async () => {
    const nbButtonsBeforeCreate = await deliveryOptionComponentsPage.countDeleteButtons();

    await deliveryOptionComponentsPage.clickOnCreateButton();

    await promise.all([
      deliveryOptionUpdatePage.deliveryOptionSelectLastOption(),
      deliveryOptionUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOptionUpdatePage.setCreatedByInput('createdBy'),
      deliveryOptionUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOptionUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      deliveryOptionUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await deliveryOptionUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOptionUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await deliveryOptionUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOptionUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await deliveryOptionUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await deliveryOptionUpdatePage.save();
    expect(await deliveryOptionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deliveryOptionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DeliveryOption', async () => {
    const nbButtonsBeforeDelete = await deliveryOptionComponentsPage.countDeleteButtons();
    await deliveryOptionComponentsPage.clickOnLastDeleteButton();

    deliveryOptionDeleteDialog = new DeliveryOptionDeleteDialog();
    expect(await deliveryOptionDeleteDialog.getDialogTitle()).to.eq('crmwebApp.deliveryOption.delete.question');
    await deliveryOptionDeleteDialog.clickOnConfirmButton();

    expect(await deliveryOptionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
