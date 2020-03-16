import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ModelCategoryComponentsPage, ModelCategoryDeleteDialog, ModelCategoryUpdatePage } from './model-category.page-object';

const expect = chai.expect;

describe('ModelCategory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let modelCategoryComponentsPage: ModelCategoryComponentsPage;
  let modelCategoryUpdatePage: ModelCategoryUpdatePage;
  let modelCategoryDeleteDialog: ModelCategoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ModelCategories', async () => {
    await navBarPage.goToEntity('model-category');
    modelCategoryComponentsPage = new ModelCategoryComponentsPage();
    await browser.wait(ec.visibilityOf(modelCategoryComponentsPage.title), 5000);
    expect(await modelCategoryComponentsPage.getTitle()).to.eq('crmwebApp.modelCategory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(modelCategoryComponentsPage.entities), ec.visibilityOf(modelCategoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ModelCategory page', async () => {
    await modelCategoryComponentsPage.clickOnCreateButton();
    modelCategoryUpdatePage = new ModelCategoryUpdatePage();
    expect(await modelCategoryUpdatePage.getPageTitle()).to.eq('crmwebApp.modelCategory.home.createOrEditLabel');
    await modelCategoryUpdatePage.cancel();
  });

  it('should create and save ModelCategories', async () => {
    const nbButtonsBeforeCreate = await modelCategoryComponentsPage.countDeleteButtons();

    await modelCategoryComponentsPage.clickOnCreateButton();

    await promise.all([
      modelCategoryUpdatePage.setModelCateInput('modelCate'),
      modelCategoryUpdatePage.setParentModelCateInput('parentModelCate'),
      modelCategoryUpdatePage.setModelCateDescInput('modelCateDesc'),
      modelCategoryUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelCategoryUpdatePage.setCreatedByInput('createdBy'),
      modelCategoryUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelCategoryUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      modelCategoryUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await modelCategoryUpdatePage.getModelCateInput()).to.eq('modelCate', 'Expected ModelCate value to be equals to modelCate');
    expect(await modelCategoryUpdatePage.getParentModelCateInput()).to.eq(
      'parentModelCate',
      'Expected ParentModelCate value to be equals to parentModelCate'
    );
    expect(await modelCategoryUpdatePage.getModelCateDescInput()).to.eq(
      'modelCateDesc',
      'Expected ModelCateDesc value to be equals to modelCateDesc'
    );
    expect(await modelCategoryUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await modelCategoryUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await modelCategoryUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await modelCategoryUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await modelCategoryUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await modelCategoryUpdatePage.save();
    expect(await modelCategoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await modelCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ModelCategory', async () => {
    const nbButtonsBeforeDelete = await modelCategoryComponentsPage.countDeleteButtons();
    await modelCategoryComponentsPage.clickOnLastDeleteButton();

    modelCategoryDeleteDialog = new ModelCategoryDeleteDialog();
    expect(await modelCategoryDeleteDialog.getDialogTitle()).to.eq('crmwebApp.modelCategory.delete.question');
    await modelCategoryDeleteDialog.clickOnConfirmButton();

    expect(await modelCategoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
