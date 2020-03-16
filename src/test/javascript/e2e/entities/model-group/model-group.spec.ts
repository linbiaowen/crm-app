import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ModelGroupComponentsPage, ModelGroupDeleteDialog, ModelGroupUpdatePage } from './model-group.page-object';

const expect = chai.expect;

describe('ModelGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let modelGroupComponentsPage: ModelGroupComponentsPage;
  let modelGroupUpdatePage: ModelGroupUpdatePage;
  let modelGroupDeleteDialog: ModelGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ModelGroups', async () => {
    await navBarPage.goToEntity('model-group');
    modelGroupComponentsPage = new ModelGroupComponentsPage();
    await browser.wait(ec.visibilityOf(modelGroupComponentsPage.title), 5000);
    expect(await modelGroupComponentsPage.getTitle()).to.eq('crmwebApp.modelGroup.home.title');
    await browser.wait(ec.or(ec.visibilityOf(modelGroupComponentsPage.entities), ec.visibilityOf(modelGroupComponentsPage.noResult)), 1000);
  });

  it('should load create ModelGroup page', async () => {
    await modelGroupComponentsPage.clickOnCreateButton();
    modelGroupUpdatePage = new ModelGroupUpdatePage();
    expect(await modelGroupUpdatePage.getPageTitle()).to.eq('crmwebApp.modelGroup.home.createOrEditLabel');
    await modelGroupUpdatePage.cancel();
  });

  it('should create and save ModelGroups', async () => {
    const nbButtonsBeforeCreate = await modelGroupComponentsPage.countDeleteButtons();

    await modelGroupComponentsPage.clickOnCreateButton();

    await promise.all([
      modelGroupUpdatePage.setModelGroupInput('modelGroup'),
      modelGroupUpdatePage.setGroupDescInput('groupDesc'),
      modelGroupUpdatePage.setListPriceInput('5'),
      modelGroupUpdatePage.setBrandInput('brand'),
      modelGroupUpdatePage.setModelInput('model'),
      modelGroupUpdatePage.setOrigCountryInput('origCountry'),
      modelGroupUpdatePage.setNetworkInput('network'),
      modelGroupUpdatePage.setCameraInput('camera'),
      modelGroupUpdatePage.setMemCardSlotInput('memCardSlot'),
      modelGroupUpdatePage.setDataTransferInput('dataTransfer'),
      modelGroupUpdatePage.setWarrantyInput('warranty'),
      modelGroupUpdatePage.setWarrantyProviderInput('warrantyProvider'),
      modelGroupUpdatePage.setModelCateInput('modelCate'),
      modelGroupUpdatePage.setRemarksInput('remarks'),
      modelGroupUpdatePage.setRemarksEndDateInput('remarksEndDate'),
      modelGroupUpdatePage.setBrandChiInput('brandChi'),
      modelGroupUpdatePage.setModelChiInput('modelChi'),
      modelGroupUpdatePage.setOrigCountryChiInput('origCountryChi'),
      modelGroupUpdatePage.setNetworkChiInput('networkChi'),
      modelGroupUpdatePage.setCameraChiInput('cameraChi'),
      modelGroupUpdatePage.setMemCardSlotChiInput('memCardSlotChi'),
      modelGroupUpdatePage.setDataTransferChiInput('dataTransferChi'),
      modelGroupUpdatePage.setWarrantyChiInput('warrantyChi'),
      modelGroupUpdatePage.setWarrantyProviderChiInput('warrantyProviderChi'),
      modelGroupUpdatePage.setModelCateChiInput('modelCateChi'),
      modelGroupUpdatePage.setRemarksChiInput('remarksChi'),
      modelGroupUpdatePage.setRemarksChiEndDateInput('remarksChiEndDate'),
      modelGroupUpdatePage.setCouponFlagInput('couponFlag'),
      modelGroupUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelGroupUpdatePage.setCreatedByInput('createdBy'),
      modelGroupUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelGroupUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      modelGroupUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await modelGroupUpdatePage.getModelGroupInput()).to.eq('modelGroup', 'Expected ModelGroup value to be equals to modelGroup');
    expect(await modelGroupUpdatePage.getGroupDescInput()).to.eq('groupDesc', 'Expected GroupDesc value to be equals to groupDesc');
    expect(await modelGroupUpdatePage.getListPriceInput()).to.eq('5', 'Expected listPrice value to be equals to 5');
    expect(await modelGroupUpdatePage.getBrandInput()).to.eq('brand', 'Expected Brand value to be equals to brand');
    expect(await modelGroupUpdatePage.getModelInput()).to.eq('model', 'Expected Model value to be equals to model');
    expect(await modelGroupUpdatePage.getOrigCountryInput()).to.eq('origCountry', 'Expected OrigCountry value to be equals to origCountry');
    expect(await modelGroupUpdatePage.getNetworkInput()).to.eq('network', 'Expected Network value to be equals to network');
    expect(await modelGroupUpdatePage.getCameraInput()).to.eq('camera', 'Expected Camera value to be equals to camera');
    expect(await modelGroupUpdatePage.getMemCardSlotInput()).to.eq('memCardSlot', 'Expected MemCardSlot value to be equals to memCardSlot');
    expect(await modelGroupUpdatePage.getDataTransferInput()).to.eq(
      'dataTransfer',
      'Expected DataTransfer value to be equals to dataTransfer'
    );
    expect(await modelGroupUpdatePage.getWarrantyInput()).to.eq('warranty', 'Expected Warranty value to be equals to warranty');
    expect(await modelGroupUpdatePage.getWarrantyProviderInput()).to.eq(
      'warrantyProvider',
      'Expected WarrantyProvider value to be equals to warrantyProvider'
    );
    expect(await modelGroupUpdatePage.getModelCateInput()).to.eq('modelCate', 'Expected ModelCate value to be equals to modelCate');
    expect(await modelGroupUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await modelGroupUpdatePage.getRemarksEndDateInput()).to.eq(
      'remarksEndDate',
      'Expected RemarksEndDate value to be equals to remarksEndDate'
    );
    expect(await modelGroupUpdatePage.getBrandChiInput()).to.eq('brandChi', 'Expected BrandChi value to be equals to brandChi');
    expect(await modelGroupUpdatePage.getModelChiInput()).to.eq('modelChi', 'Expected ModelChi value to be equals to modelChi');
    expect(await modelGroupUpdatePage.getOrigCountryChiInput()).to.eq(
      'origCountryChi',
      'Expected OrigCountryChi value to be equals to origCountryChi'
    );
    expect(await modelGroupUpdatePage.getNetworkChiInput()).to.eq('networkChi', 'Expected NetworkChi value to be equals to networkChi');
    expect(await modelGroupUpdatePage.getCameraChiInput()).to.eq('cameraChi', 'Expected CameraChi value to be equals to cameraChi');
    expect(await modelGroupUpdatePage.getMemCardSlotChiInput()).to.eq(
      'memCardSlotChi',
      'Expected MemCardSlotChi value to be equals to memCardSlotChi'
    );
    expect(await modelGroupUpdatePage.getDataTransferChiInput()).to.eq(
      'dataTransferChi',
      'Expected DataTransferChi value to be equals to dataTransferChi'
    );
    expect(await modelGroupUpdatePage.getWarrantyChiInput()).to.eq('warrantyChi', 'Expected WarrantyChi value to be equals to warrantyChi');
    expect(await modelGroupUpdatePage.getWarrantyProviderChiInput()).to.eq(
      'warrantyProviderChi',
      'Expected WarrantyProviderChi value to be equals to warrantyProviderChi'
    );
    expect(await modelGroupUpdatePage.getModelCateChiInput()).to.eq(
      'modelCateChi',
      'Expected ModelCateChi value to be equals to modelCateChi'
    );
    expect(await modelGroupUpdatePage.getRemarksChiInput()).to.eq('remarksChi', 'Expected RemarksChi value to be equals to remarksChi');
    expect(await modelGroupUpdatePage.getRemarksChiEndDateInput()).to.eq(
      'remarksChiEndDate',
      'Expected RemarksChiEndDate value to be equals to remarksChiEndDate'
    );
    expect(await modelGroupUpdatePage.getCouponFlagInput()).to.eq('couponFlag', 'Expected CouponFlag value to be equals to couponFlag');
    expect(await modelGroupUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await modelGroupUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await modelGroupUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await modelGroupUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await modelGroupUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await modelGroupUpdatePage.save();
    expect(await modelGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await modelGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ModelGroup', async () => {
    const nbButtonsBeforeDelete = await modelGroupComponentsPage.countDeleteButtons();
    await modelGroupComponentsPage.clickOnLastDeleteButton();

    modelGroupDeleteDialog = new ModelGroupDeleteDialog();
    expect(await modelGroupDeleteDialog.getDialogTitle()).to.eq('crmwebApp.modelGroup.delete.question');
    await modelGroupDeleteDialog.clickOnConfirmButton();

    expect(await modelGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
