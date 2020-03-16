import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductDataComponentsPage, ProductDataDeleteDialog, ProductDataUpdatePage } from './product-data.page-object';

const expect = chai.expect;

describe('ProductData e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productDataComponentsPage: ProductDataComponentsPage;
  let productDataUpdatePage: ProductDataUpdatePage;
  let productDataDeleteDialog: ProductDataDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductData', async () => {
    await navBarPage.goToEntity('product-data');
    productDataComponentsPage = new ProductDataComponentsPage();
    await browser.wait(ec.visibilityOf(productDataComponentsPage.title), 5000);
    expect(await productDataComponentsPage.getTitle()).to.eq('crmwebApp.productData.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productDataComponentsPage.entities), ec.visibilityOf(productDataComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductData page', async () => {
    await productDataComponentsPage.clickOnCreateButton();
    productDataUpdatePage = new ProductDataUpdatePage();
    expect(await productDataUpdatePage.getPageTitle()).to.eq('crmwebApp.productData.home.createOrEditLabel');
    await productDataUpdatePage.cancel();
  });

  it('should create and save ProductData', async () => {
    const nbButtonsBeforeCreate = await productDataComponentsPage.countDeleteButtons();

    await productDataComponentsPage.clickOnCreateButton();

    await promise.all([
      productDataUpdatePage.setDataIdInput('dataId'),
      productDataUpdatePage.setProductSpecIdInput('5'),
      productDataUpdatePage.setUnitInput('unit'),
      productDataUpdatePage.setVolumeInput('5'),
      productDataUpdatePage.setDataSlabInput('dataSlab'),
      productDataUpdatePage.setDataSpeedCategoryInput('dataSpeedCategory'),
      productDataUpdatePage.setSpecicalPackTypeInput('specicalPackType'),
      productDataUpdatePage.setDataServiceTypeInput('dataServiceType'),
      productDataUpdatePage.setRoamingRegionsInput('roamingRegions'),
      productDataUpdatePage.setRoamingCountriesInput('roamingCountries'),
      productDataUpdatePage.setRoamingDayPassTypeInput('roamingDayPassType'),
      productDataUpdatePage.setRoamingPackValidPeriodTypeInput('roamingPackValidPeriodType'),
      productDataUpdatePage.setRoamingPackPeriodInput('5'),
      productDataUpdatePage.setRoamingPackTermInput('roamingPackTerm'),
      productDataUpdatePage.setMinTransferQuotaInput('5'),
      productDataUpdatePage.setMaxTransferQuotaInput('5'),
      productDataUpdatePage.setMinRetentionQuotaInput('5'),
      productDataUpdatePage.setMinTpTransferQuotaInput('5'),
      productDataUpdatePage.setMaxTpTransferQuotaInput('5'),
      productDataUpdatePage.setMinTpRetentionQuotaInput('5'),
      productDataUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productDataUpdatePage.setCreatedByInput('createdBy'),
      productDataUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productDataUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productDataUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await productDataUpdatePage.getDataIdInput()).to.eq('dataId', 'Expected DataId value to be equals to dataId');
    expect(await productDataUpdatePage.getProductSpecIdInput()).to.eq('5', 'Expected productSpecId value to be equals to 5');
    expect(await productDataUpdatePage.getUnitInput()).to.eq('unit', 'Expected Unit value to be equals to unit');
    expect(await productDataUpdatePage.getVolumeInput()).to.eq('5', 'Expected volume value to be equals to 5');
    expect(await productDataUpdatePage.getDataSlabInput()).to.eq('dataSlab', 'Expected DataSlab value to be equals to dataSlab');
    expect(await productDataUpdatePage.getDataSpeedCategoryInput()).to.eq(
      'dataSpeedCategory',
      'Expected DataSpeedCategory value to be equals to dataSpeedCategory'
    );
    expect(await productDataUpdatePage.getSpecicalPackTypeInput()).to.eq(
      'specicalPackType',
      'Expected SpecicalPackType value to be equals to specicalPackType'
    );
    expect(await productDataUpdatePage.getDataServiceTypeInput()).to.eq(
      'dataServiceType',
      'Expected DataServiceType value to be equals to dataServiceType'
    );
    expect(await productDataUpdatePage.getRoamingRegionsInput()).to.eq(
      'roamingRegions',
      'Expected RoamingRegions value to be equals to roamingRegions'
    );
    expect(await productDataUpdatePage.getRoamingCountriesInput()).to.eq(
      'roamingCountries',
      'Expected RoamingCountries value to be equals to roamingCountries'
    );
    expect(await productDataUpdatePage.getRoamingDayPassTypeInput()).to.eq(
      'roamingDayPassType',
      'Expected RoamingDayPassType value to be equals to roamingDayPassType'
    );
    expect(await productDataUpdatePage.getRoamingPackValidPeriodTypeInput()).to.eq(
      'roamingPackValidPeriodType',
      'Expected RoamingPackValidPeriodType value to be equals to roamingPackValidPeriodType'
    );
    expect(await productDataUpdatePage.getRoamingPackPeriodInput()).to.eq('5', 'Expected roamingPackPeriod value to be equals to 5');
    expect(await productDataUpdatePage.getRoamingPackTermInput()).to.eq(
      'roamingPackTerm',
      'Expected RoamingPackTerm value to be equals to roamingPackTerm'
    );
    expect(await productDataUpdatePage.getMinTransferQuotaInput()).to.eq('5', 'Expected minTransferQuota value to be equals to 5');
    expect(await productDataUpdatePage.getMaxTransferQuotaInput()).to.eq('5', 'Expected maxTransferQuota value to be equals to 5');
    expect(await productDataUpdatePage.getMinRetentionQuotaInput()).to.eq('5', 'Expected minRetentionQuota value to be equals to 5');
    expect(await productDataUpdatePage.getMinTpTransferQuotaInput()).to.eq('5', 'Expected minTpTransferQuota value to be equals to 5');
    expect(await productDataUpdatePage.getMaxTpTransferQuotaInput()).to.eq('5', 'Expected maxTpTransferQuota value to be equals to 5');
    expect(await productDataUpdatePage.getMinTpRetentionQuotaInput()).to.eq('5', 'Expected minTpRetentionQuota value to be equals to 5');
    expect(await productDataUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productDataUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productDataUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productDataUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productDataUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productDataUpdatePage.save();
    expect(await productDataUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProductData', async () => {
    const nbButtonsBeforeDelete = await productDataComponentsPage.countDeleteButtons();
    await productDataComponentsPage.clickOnLastDeleteButton();

    productDataDeleteDialog = new ProductDataDeleteDialog();
    expect(await productDataDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productData.delete.question');
    await productDataDeleteDialog.clickOnConfirmButton();

    expect(await productDataComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
