import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ModelComponentsPage, ModelDeleteDialog, ModelUpdatePage } from './model.page-object';

const expect = chai.expect;

describe('Model e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let modelComponentsPage: ModelComponentsPage;
  let modelUpdatePage: ModelUpdatePage;
  let modelDeleteDialog: ModelDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Models', async () => {
    await navBarPage.goToEntity('model');
    modelComponentsPage = new ModelComponentsPage();
    await browser.wait(ec.visibilityOf(modelComponentsPage.title), 5000);
    expect(await modelComponentsPage.getTitle()).to.eq('crmwebApp.model.home.title');
    await browser.wait(ec.or(ec.visibilityOf(modelComponentsPage.entities), ec.visibilityOf(modelComponentsPage.noResult)), 1000);
  });

  it('should load create Model page', async () => {
    await modelComponentsPage.clickOnCreateButton();
    modelUpdatePage = new ModelUpdatePage();
    expect(await modelUpdatePage.getPageTitle()).to.eq('crmwebApp.model.home.createOrEditLabel');
    await modelUpdatePage.cancel();
  });

  it('should create and save Models', async () => {
    const nbButtonsBeforeCreate = await modelComponentsPage.countDeleteButtons();

    await modelComponentsPage.clickOnCreateButton();

    await promise.all([
      modelUpdatePage.setModelCodeInput('modelCode'),
      modelUpdatePage.setModelGroupInput('modelGroup'),
      modelUpdatePage.statusSelectLastOption(),
      modelUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelUpdatePage.setCreatedByInput('createdBy'),
      modelUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      modelUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      modelUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await modelUpdatePage.getModelCodeInput()).to.eq('modelCode', 'Expected ModelCode value to be equals to modelCode');
    expect(await modelUpdatePage.getModelGroupInput()).to.eq('modelGroup', 'Expected ModelGroup value to be equals to modelGroup');
    expect(await modelUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await modelUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await modelUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await modelUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await modelUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await modelUpdatePage.save();
    expect(await modelUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await modelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Model', async () => {
    const nbButtonsBeforeDelete = await modelComponentsPage.countDeleteButtons();
    await modelComponentsPage.clickOnLastDeleteButton();

    modelDeleteDialog = new ModelDeleteDialog();
    expect(await modelDeleteDialog.getDialogTitle()).to.eq('crmwebApp.model.delete.question');
    await modelDeleteDialog.clickOnConfirmButton();

    expect(await modelComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
