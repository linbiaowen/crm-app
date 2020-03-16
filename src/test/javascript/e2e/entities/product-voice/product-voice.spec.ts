import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductVoiceComponentsPage, ProductVoiceDeleteDialog, ProductVoiceUpdatePage } from './product-voice.page-object';

const expect = chai.expect;

describe('ProductVoice e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productVoiceComponentsPage: ProductVoiceComponentsPage;
  let productVoiceUpdatePage: ProductVoiceUpdatePage;
  let productVoiceDeleteDialog: ProductVoiceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductVoices', async () => {
    await navBarPage.goToEntity('product-voice');
    productVoiceComponentsPage = new ProductVoiceComponentsPage();
    await browser.wait(ec.visibilityOf(productVoiceComponentsPage.title), 5000);
    expect(await productVoiceComponentsPage.getTitle()).to.eq('crmwebApp.productVoice.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productVoiceComponentsPage.entities), ec.visibilityOf(productVoiceComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductVoice page', async () => {
    await productVoiceComponentsPage.clickOnCreateButton();
    productVoiceUpdatePage = new ProductVoiceUpdatePage();
    expect(await productVoiceUpdatePage.getPageTitle()).to.eq('crmwebApp.productVoice.home.createOrEditLabel');
    await productVoiceUpdatePage.cancel();
  });

  it('should create and save ProductVoices', async () => {
    const nbButtonsBeforeCreate = await productVoiceComponentsPage.countDeleteButtons();

    await productVoiceComponentsPage.clickOnCreateButton();

    await promise.all([
      productVoiceUpdatePage.setVoiceIdInput('voiceId'),
      productVoiceUpdatePage.setProductSpecIdInput('5'),
      productVoiceUpdatePage.setUnitInput('unit'),
      productVoiceUpdatePage.setVolumeInput('5'),
      productVoiceUpdatePage.setMinTransferQuotaInput('5'),
      productVoiceUpdatePage.setMaxTransferQuotaInput('5'),
      productVoiceUpdatePage.setMinRetentionQuotaInput('5'),
      productVoiceUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productVoiceUpdatePage.setCreatedByInput('createdBy'),
      productVoiceUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productVoiceUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productVoiceUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await productVoiceUpdatePage.getVoiceIdInput()).to.eq('voiceId', 'Expected VoiceId value to be equals to voiceId');
    expect(await productVoiceUpdatePage.getProductSpecIdInput()).to.eq('5', 'Expected productSpecId value to be equals to 5');
    expect(await productVoiceUpdatePage.getUnitInput()).to.eq('unit', 'Expected Unit value to be equals to unit');
    expect(await productVoiceUpdatePage.getVolumeInput()).to.eq('5', 'Expected volume value to be equals to 5');
    const selectedRoamingFlag = productVoiceUpdatePage.getRoamingFlagInput();
    if (await selectedRoamingFlag.isSelected()) {
      await productVoiceUpdatePage.getRoamingFlagInput().click();
      expect(await productVoiceUpdatePage.getRoamingFlagInput().isSelected(), 'Expected roamingFlag not to be selected').to.be.false;
    } else {
      await productVoiceUpdatePage.getRoamingFlagInput().click();
      expect(await productVoiceUpdatePage.getRoamingFlagInput().isSelected(), 'Expected roamingFlag to be selected').to.be.true;
    }
    expect(await productVoiceUpdatePage.getMinTransferQuotaInput()).to.eq('5', 'Expected minTransferQuota value to be equals to 5');
    expect(await productVoiceUpdatePage.getMaxTransferQuotaInput()).to.eq('5', 'Expected maxTransferQuota value to be equals to 5');
    expect(await productVoiceUpdatePage.getMinRetentionQuotaInput()).to.eq('5', 'Expected minRetentionQuota value to be equals to 5');
    expect(await productVoiceUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productVoiceUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productVoiceUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productVoiceUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productVoiceUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productVoiceUpdatePage.save();
    expect(await productVoiceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productVoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProductVoice', async () => {
    const nbButtonsBeforeDelete = await productVoiceComponentsPage.countDeleteButtons();
    await productVoiceComponentsPage.clickOnLastDeleteButton();

    productVoiceDeleteDialog = new ProductVoiceDeleteDialog();
    expect(await productVoiceDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productVoice.delete.question');
    await productVoiceDeleteDialog.clickOnConfirmButton();

    expect(await productVoiceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
