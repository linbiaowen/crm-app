import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ProductAttributesComponentsPage,
  ProductAttributesDeleteDialog,
  ProductAttributesUpdatePage
} from './product-attributes.page-object';

const expect = chai.expect;

describe('ProductAttributes e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productAttributesComponentsPage: ProductAttributesComponentsPage;
  let productAttributesUpdatePage: ProductAttributesUpdatePage;
  let productAttributesDeleteDialog: ProductAttributesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductAttributes', async () => {
    await navBarPage.goToEntity('product-attributes');
    productAttributesComponentsPage = new ProductAttributesComponentsPage();
    await browser.wait(ec.visibilityOf(productAttributesComponentsPage.title), 5000);
    expect(await productAttributesComponentsPage.getTitle()).to.eq('crmwebApp.productAttributes.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productAttributesComponentsPage.entities), ec.visibilityOf(productAttributesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductAttributes page', async () => {
    await productAttributesComponentsPage.clickOnCreateButton();
    productAttributesUpdatePage = new ProductAttributesUpdatePage();
    expect(await productAttributesUpdatePage.getPageTitle()).to.eq('crmwebApp.productAttributes.home.createOrEditLabel');
    await productAttributesUpdatePage.cancel();
  });

  it('should create and save ProductAttributes', async () => {
    const nbButtonsBeforeCreate = await productAttributesComponentsPage.countDeleteButtons();

    await productAttributesComponentsPage.clickOnCreateButton();

    await promise.all([
      productAttributesUpdatePage.skuTypeSelectLastOption(),
      productAttributesUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productAttributesUpdatePage.setCreatedByInput('createdBy'),
      productAttributesUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productAttributesUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productAttributesUpdatePage.setTenantIdInput('tenantId')
    ]);

    const selectedShippable = productAttributesUpdatePage.getShippableInput();
    if (await selectedShippable.isSelected()) {
      await productAttributesUpdatePage.getShippableInput().click();
      expect(await productAttributesUpdatePage.getShippableInput().isSelected(), 'Expected shippable not to be selected').to.be.false;
    } else {
      await productAttributesUpdatePage.getShippableInput().click();
      expect(await productAttributesUpdatePage.getShippableInput().isSelected(), 'Expected shippable to be selected').to.be.true;
    }
    expect(await productAttributesUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productAttributesUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productAttributesUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productAttributesUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productAttributesUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productAttributesUpdatePage.save();
    expect(await productAttributesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productAttributesComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProductAttributes', async () => {
    const nbButtonsBeforeDelete = await productAttributesComponentsPage.countDeleteButtons();
    await productAttributesComponentsPage.clickOnLastDeleteButton();

    productAttributesDeleteDialog = new ProductAttributesDeleteDialog();
    expect(await productAttributesDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productAttributes.delete.question');
    await productAttributesDeleteDialog.clickOnConfirmButton();

    expect(await productAttributesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
