import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProductBoxTypeComponentsPage, ProductBoxTypeDeleteDialog, ProductBoxTypeUpdatePage } from './product-box-type.page-object';

const expect = chai.expect;

describe('ProductBoxType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let productBoxTypeComponentsPage: ProductBoxTypeComponentsPage;
  let productBoxTypeUpdatePage: ProductBoxTypeUpdatePage;
  let productBoxTypeDeleteDialog: ProductBoxTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProductBoxTypes', async () => {
    await navBarPage.goToEntity('product-box-type');
    productBoxTypeComponentsPage = new ProductBoxTypeComponentsPage();
    await browser.wait(ec.visibilityOf(productBoxTypeComponentsPage.title), 5000);
    expect(await productBoxTypeComponentsPage.getTitle()).to.eq('crmwebApp.productBoxType.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(productBoxTypeComponentsPage.entities), ec.visibilityOf(productBoxTypeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ProductBoxType page', async () => {
    await productBoxTypeComponentsPage.clickOnCreateButton();
    productBoxTypeUpdatePage = new ProductBoxTypeUpdatePage();
    expect(await productBoxTypeUpdatePage.getPageTitle()).to.eq('crmwebApp.productBoxType.home.createOrEditLabel');
    await productBoxTypeUpdatePage.cancel();
  });

  it('should create and save ProductBoxTypes', async () => {
    const nbButtonsBeforeCreate = await productBoxTypeComponentsPage.countDeleteButtons();

    await productBoxTypeComponentsPage.clickOnCreateButton();

    await promise.all([
      productBoxTypeUpdatePage.setBoxTypeInput('boxType'),
      productBoxTypeUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productBoxTypeUpdatePage.setCreatedByInput('createdBy'),
      productBoxTypeUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      productBoxTypeUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      productBoxTypeUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await productBoxTypeUpdatePage.getBoxTypeInput()).to.eq('boxType', 'Expected BoxType value to be equals to boxType');
    expect(await productBoxTypeUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await productBoxTypeUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await productBoxTypeUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await productBoxTypeUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await productBoxTypeUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await productBoxTypeUpdatePage.save();
    expect(await productBoxTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await productBoxTypeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ProductBoxType', async () => {
    const nbButtonsBeforeDelete = await productBoxTypeComponentsPage.countDeleteButtons();
    await productBoxTypeComponentsPage.clickOnLastDeleteButton();

    productBoxTypeDeleteDialog = new ProductBoxTypeDeleteDialog();
    expect(await productBoxTypeDeleteDialog.getDialogTitle()).to.eq('crmwebApp.productBoxType.delete.question');
    await productBoxTypeDeleteDialog.clickOnConfirmButton();

    expect(await productBoxTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
