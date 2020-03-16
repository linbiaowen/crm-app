import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProductSimAttributesComponentsPage,
  ProductSimAttributesDeleteDialog,
  ProductSimAttributesUpdatePage
} from './product-sim-attributes.page-object';

const expect = chai.expect;

describe('ProductSimAttributes e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productSimAttributesComponentsPage: ProductSimAttributesComponentsPage;
  let productSimAttributesUpdatePage: ProductSimAttributesUpdatePage;
  let productSimAttributesDeleteDialog: ProductSimAttributesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductSimAttributes', async () => {
    await navBarPage.goToEntity('product-sim-attributes');
    productSimAttributesComponentsPage = new ProductSimAttributesComponentsPage();
    await browser.wait(ec.visibilityOf(productSimAttributesComponentsPage.title), 5000);
    expect(await productSimAttributesComponentsPage.getTitle()).to.eq('crmwebApp.productSimAttributes.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productSimAttributesComponentsPage.entities), ec.visibilityOf(productSimAttributesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductSimAttributes page', async () => {
    await productSimAttributesComponentsPage.clickOnCreateButton();
    productSimAttributesUpdatePage = new ProductSimAttributesUpdatePage();
    expect(await productSimAttributesUpdatePage.getPageTitle()).to.eq('crmwebApp.productSimAttributes.home.createOrEditLabel');
    await productSimAttributesUpdatePage.cancel();
  });

  it('should create and save ProductSimAttributes', async () => {
    const nbButtonsBeforeCreate = await productSimAttributesComponentsPage.countDeleteButtons();

    await productSimAttributesComponentsPage.clickOnCreateButton();

    await promise.all([
      productSimAttributesUpdatePage.simTypeSelectLastOption(),
      productSimAttributesUpdatePage.setImsiRangeFromInput('imsiRangeFrom'),
      productSimAttributesUpdatePage.setImsiRangeToInput('imsiRangeTo'),
      productSimAttributesUpdatePage.setSimRepositoryRefInput('simRepositoryRef'),
      productSimAttributesUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSimAttributesUpdatePage.setCreatedByInput('createdBy'),
      productSimAttributesUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productSimAttributesUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productSimAttributesUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await productSimAttributesUpdatePage.getImsiRangeFromInput()).to.eq(
      'imsiRangeFrom',
      'Expected ImsiRangeFrom value to be equals to imsiRangeFrom'
    );
    expect(await productSimAttributesUpdatePage.getImsiRangeToInput()).to.eq(
      'imsiRangeTo',
      'Expected ImsiRangeTo value to be equals to imsiRangeTo'
    );
    expect(await productSimAttributesUpdatePage.getSimRepositoryRefInput()).to.eq(
      'simRepositoryRef',
      'Expected SimRepositoryRef value to be equals to simRepositoryRef'
    );
    expect(await productSimAttributesUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productSimAttributesUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await productSimAttributesUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productSimAttributesUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productSimAttributesUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productSimAttributesUpdatePage.save();
    expect(await productSimAttributesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productSimAttributesComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProductSimAttributes', async () => {
    const nbButtonsBeforeDelete = await productSimAttributesComponentsPage.countDeleteButtons();
    await productSimAttributesComponentsPage.clickOnLastDeleteButton();

    productSimAttributesDeleteDialog = new ProductSimAttributesDeleteDialog();
    expect(await productSimAttributesDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productSimAttributes.delete.question');
    await productSimAttributesDeleteDialog.clickOnConfirmButton();

    expect(await productSimAttributesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
