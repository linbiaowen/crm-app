import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductSmsComponentsPage, ProductSmsDeleteDialog, ProductSmsUpdatePage } from './product-sms.page-object';

const expect = chai.expect;

describe('ProductSms e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productSmsComponentsPage: ProductSmsComponentsPage;
  let productSmsUpdatePage: ProductSmsUpdatePage;
  let productSmsDeleteDialog: ProductSmsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductSms', async () => {
    await navBarPage.goToEntity('product-sms');
    productSmsComponentsPage = new ProductSmsComponentsPage();
    await browser.wait(ec.visibilityOf(productSmsComponentsPage.title), 5000);
    expect(await productSmsComponentsPage.getTitle()).to.eq('crmwebApp.productSms.home.title');
    await browser.wait(ec.or(ec.visibilityOf(productSmsComponentsPage.entities), ec.visibilityOf(productSmsComponentsPage.noResult)), 1000);
  });

  it('should load create ProductSms page', async () => {
    await productSmsComponentsPage.clickOnCreateButton();
    productSmsUpdatePage = new ProductSmsUpdatePage();
    expect(await productSmsUpdatePage.getPageTitle()).to.eq('crmwebApp.productSms.home.createOrEditLabel');
    await productSmsUpdatePage.cancel();
  });

  it('should create and save ProductSms', async () => {
    const nbButtonsBeforeCreate = await productSmsComponentsPage.countDeleteButtons();

    await productSmsComponentsPage.clickOnCreateButton();

    await promise.all([
      productSmsUpdatePage.setSmsIdInput('smsId'),
      productSmsUpdatePage.setProductSpecIdInput('5'),
      productSmsUpdatePage.setUnitInput('unit'),
      productSmsUpdatePage.setVolumeInput('5'),
      productSmsUpdatePage.smsTypeSelectLastOption(),
      productSmsUpdatePage.setMinTransferQuotaInput('5'),
      productSmsUpdatePage.setMaxTransferQuotaInput('5'),
      productSmsUpdatePage.setMinRetentionQuotaInput('5'),
      productSmsUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSmsUpdatePage.setCreatedByInput('createdBy'),
      productSmsUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSmsUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productSmsUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await productSmsUpdatePage.getSmsIdInput()).to.eq('smsId', 'Expected SmsId value to be equals to smsId');
    expect(await productSmsUpdatePage.getProductSpecIdInput()).to.eq('5', 'Expected productSpecId value to be equals to 5');
    expect(await productSmsUpdatePage.getUnitInput()).to.eq('unit', 'Expected Unit value to be equals to unit');
    expect(await productSmsUpdatePage.getVolumeInput()).to.eq('5', 'Expected volume value to be equals to 5');
    const selectedRoamingAllowed = productSmsUpdatePage.getRoamingAllowedInput();
    if (await selectedRoamingAllowed.isSelected()) {
      await productSmsUpdatePage.getRoamingAllowedInput().click();
      expect(await productSmsUpdatePage.getRoamingAllowedInput().isSelected(), 'Expected roamingAllowed not to be selected').to.be.false;
    } else {
      await productSmsUpdatePage.getRoamingAllowedInput().click();
      expect(await productSmsUpdatePage.getRoamingAllowedInput().isSelected(), 'Expected roamingAllowed to be selected').to.be.true;
    }
    expect(await productSmsUpdatePage.getMinTransferQuotaInput()).to.eq('5', 'Expected minTransferQuota value to be equals to 5');
    expect(await productSmsUpdatePage.getMaxTransferQuotaInput()).to.eq('5', 'Expected maxTransferQuota value to be equals to 5');
    expect(await productSmsUpdatePage.getMinRetentionQuotaInput()).to.eq('5', 'Expected minRetentionQuota value to be equals to 5');
    expect(await productSmsUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productSmsUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productSmsUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productSmsUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productSmsUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productSmsUpdatePage.save();
    expect(await productSmsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productSmsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProductSms', async () => {
    const nbButtonsBeforeDelete = await productSmsComponentsPage.countDeleteButtons();
    await productSmsComponentsPage.clickOnLastDeleteButton();

    productSmsDeleteDialog = new ProductSmsDeleteDialog();
    expect(await productSmsDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productSms.delete.question');
    await productSmsDeleteDialog.clickOnConfirmButton();

    expect(await productSmsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
