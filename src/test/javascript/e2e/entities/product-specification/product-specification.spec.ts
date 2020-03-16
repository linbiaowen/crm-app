import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProductSpecificationComponentsPage,
  ProductSpecificationDeleteDialog,
  ProductSpecificationUpdatePage
} from './product-specification.page-object';

const expect = chai.expect;

describe('ProductSpecification e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productSpecificationComponentsPage: ProductSpecificationComponentsPage;
  let productSpecificationUpdatePage: ProductSpecificationUpdatePage;
  let productSpecificationDeleteDialog: ProductSpecificationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductSpecifications', async () => {
    await navBarPage.goToEntity('product-specification');
    productSpecificationComponentsPage = new ProductSpecificationComponentsPage();
    await browser.wait(ec.visibilityOf(productSpecificationComponentsPage.title), 5000);
    expect(await productSpecificationComponentsPage.getTitle()).to.eq('crmwebApp.productSpecification.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productSpecificationComponentsPage.entities), ec.visibilityOf(productSpecificationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductSpecification page', async () => {
    await productSpecificationComponentsPage.clickOnCreateButton();
    productSpecificationUpdatePage = new ProductSpecificationUpdatePage();
    expect(await productSpecificationUpdatePage.getPageTitle()).to.eq('crmwebApp.productSpecification.home.createOrEditLabel');
    await productSpecificationUpdatePage.cancel();
  });

  it('should create and save ProductSpecifications', async () => {
    const nbButtonsBeforeCreate = await productSpecificationComponentsPage.countDeleteButtons();

    await productSpecificationComponentsPage.clickOnCreateButton();

    await promise.all([
      productSpecificationUpdatePage.setProductSpecIdInput('5'),
      productSpecificationUpdatePage.setProductIdInput('productId'),
      productSpecificationUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      productSpecificationUpdatePage.productSpecTypeSelectLastOption(),
      productSpecificationUpdatePage.skuTypeSelectLastOption(),
      productSpecificationUpdatePage.simTypeSelectLastOption(),
      productSpecificationUpdatePage.boxTypeSelectLastOption(),
      productSpecificationUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSpecificationUpdatePage.setCreatedByInput('createdBy'),
      productSpecificationUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSpecificationUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productSpecificationUpdatePage.setTenantIdInput('tenantId'),
      productSpecificationUpdatePage.voiceSelectLastOption(),
      productSpecificationUpdatePage.dataSelectLastOption(),
      productSpecificationUpdatePage.smsSelectLastOption(),
      productSpecificationUpdatePage.offerSelectLastOption()
    ]);

    expect(await productSpecificationUpdatePage.getProductSpecIdInput()).to.eq('5', 'Expected productSpecId value to be equals to 5');
    expect(await productSpecificationUpdatePage.getProductIdInput()).to.eq(
      'productId',
      'Expected ProductId value to be equals to productId'
    );
    expect(await productSpecificationUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    expect(await productSpecificationUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productSpecificationUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await productSpecificationUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productSpecificationUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productSpecificationUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productSpecificationUpdatePage.save();
    expect(await productSpecificationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productSpecificationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProductSpecification', async () => {
    const nbButtonsBeforeDelete = await productSpecificationComponentsPage.countDeleteButtons();
    await productSpecificationComponentsPage.clickOnLastDeleteButton();

    productSpecificationDeleteDialog = new ProductSpecificationDeleteDialog();
    expect(await productSpecificationDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productSpecification.delete.question');
    await productSpecificationDeleteDialog.clickOnConfirmButton();

    expect(await productSpecificationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
