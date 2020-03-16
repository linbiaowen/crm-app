import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductComponentsPage, ProductDeleteDialog, ProductUpdatePage } from './product.page-object';

const expect = chai.expect;

describe('Product e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productComponentsPage: ProductComponentsPage;
  let productUpdatePage: ProductUpdatePage;
  let productDeleteDialog: ProductDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Products', async () => {
    await navBarPage.goToEntity('product');
    productComponentsPage = new ProductComponentsPage();
    await browser.wait(ec.visibilityOf(productComponentsPage.title), 5000);
    expect(await productComponentsPage.getTitle()).to.eq('crmwebApp.product.home.title');
    await browser.wait(ec.or(ec.visibilityOf(productComponentsPage.entities), ec.visibilityOf(productComponentsPage.noResult)), 1000);
  });

  it('should load create Product page', async () => {
    await productComponentsPage.clickOnCreateButton();
    productUpdatePage = new ProductUpdatePage();
    expect(await productUpdatePage.getPageTitle()).to.eq('crmwebApp.product.home.createOrEditLabel');
    await productUpdatePage.cancel();
  });

  it('should create and save Products', async () => {
    const nbButtonsBeforeCreate = await productComponentsPage.countDeleteButtons();

    await productComponentsPage.clickOnCreateButton();

    await promise.all([
      productUpdatePage.setProductIdInput('productId'),
      productUpdatePage.setProductNameInput('productName'),
      productUpdatePage.setProductNameChiInput('productNameChi'),
      productUpdatePage.setProductDescInput('productDesc'),
      productUpdatePage.setProductDescChiInput('productDescChi'),
      productUpdatePage.productCateSelectLastOption(),
      productUpdatePage.productNatureSelectLastOption(),
      productUpdatePage.productFamilySelectLastOption(),
      productUpdatePage.productTypeSelectLastOption(),
      productUpdatePage.setModelCodeInput('modelCode'),
      productUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productUpdatePage.setCreatedByInput('createdBy'),
      productUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productUpdatePage.setTenantIdInput('tenantId'),
      productUpdatePage.productSpecSelectLastOption()
    ]);

    expect(await productUpdatePage.getProductIdInput()).to.eq('productId', 'Expected ProductId value to be equals to productId');
    expect(await productUpdatePage.getProductNameInput()).to.eq('productName', 'Expected ProductName value to be equals to productName');
    expect(await productUpdatePage.getProductNameChiInput()).to.eq(
      'productNameChi',
      'Expected ProductNameChi value to be equals to productNameChi'
    );
    expect(await productUpdatePage.getProductDescInput()).to.eq('productDesc', 'Expected ProductDesc value to be equals to productDesc');
    expect(await productUpdatePage.getProductDescChiInput()).to.eq(
      'productDescChi',
      'Expected ProductDescChi value to be equals to productDescChi'
    );
    expect(await productUpdatePage.getModelCodeInput()).to.eq('modelCode', 'Expected ModelCode value to be equals to modelCode');
    expect(await productUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await productUpdatePage.getEndDateInput()).to.contain('2001-01-01T02:30', 'Expected endDate value to be equals to 2000-12-31');
    const selectedIndependentlyOrderable = productUpdatePage.getIndependentlyOrderableInput();
    if (await selectedIndependentlyOrderable.isSelected()) {
      await productUpdatePage.getIndependentlyOrderableInput().click();
      expect(await productUpdatePage.getIndependentlyOrderableInput().isSelected(), 'Expected independentlyOrderable not to be selected').to
        .be.false;
    } else {
      await productUpdatePage.getIndependentlyOrderableInput().click();
      expect(await productUpdatePage.getIndependentlyOrderableInput().isSelected(), 'Expected independentlyOrderable to be selected').to.be
        .true;
    }
    const selectedNetworkProvisionRequired = productUpdatePage.getNetworkProvisionRequiredInput();
    if (await selectedNetworkProvisionRequired.isSelected()) {
      await productUpdatePage.getNetworkProvisionRequiredInput().click();
      expect(
        await productUpdatePage.getNetworkProvisionRequiredInput().isSelected(),
        'Expected networkProvisionRequired not to be selected'
      ).to.be.false;
    } else {
      await productUpdatePage.getNetworkProvisionRequiredInput().click();
      expect(await productUpdatePage.getNetworkProvisionRequiredInput().isSelected(), 'Expected networkProvisionRequired to be selected').to
        .be.true;
    }
    const selectedChangeEntitlementAllowed = productUpdatePage.getChangeEntitlementAllowedInput();
    if (await selectedChangeEntitlementAllowed.isSelected()) {
      await productUpdatePage.getChangeEntitlementAllowedInput().click();
      expect(
        await productUpdatePage.getChangeEntitlementAllowedInput().isSelected(),
        'Expected changeEntitlementAllowed not to be selected'
      ).to.be.false;
    } else {
      await productUpdatePage.getChangeEntitlementAllowedInput().click();
      expect(await productUpdatePage.getChangeEntitlementAllowedInput().isSelected(), 'Expected changeEntitlementAllowed to be selected').to
        .be.true;
    }
    expect(await productUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productUpdatePage.save();
    expect(await productUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Product', async () => {
    const nbButtonsBeforeDelete = await productComponentsPage.countDeleteButtons();
    await productComponentsPage.clickOnLastDeleteButton();

    productDeleteDialog = new ProductDeleteDialog();
    expect(await productDeleteDialog.getDialogTitle()).to.eq('crmwebApp.product.delete.question');
    await productDeleteDialog.clickOnConfirmButton();

    expect(await productComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
