import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CustDocDataStoreComponentsPage,
  CustDocDataStoreDeleteDialog,
  CustDocDataStoreUpdatePage
} from './cust-doc-data-store.page-object';
import * as path from 'path';

const expect = chai.expect;

describe('CustDocDataStore e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custDocDataStoreComponentsPage: CustDocDataStoreComponentsPage;
  let custDocDataStoreUpdatePage: CustDocDataStoreUpdatePage;
  let custDocDataStoreDeleteDialog: CustDocDataStoreDeleteDialog;
  const fileNameToUpload = 'logo-jhipster.png';
  const fileToUpload = '../../../../../../src/main/webapp/content/images/' + fileNameToUpload;
  const absolutePath = path.resolve(__dirname, fileToUpload);

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustDocDataStores', async () => {
    await navBarPage.goToEntity('cust-doc-data-store');
    custDocDataStoreComponentsPage = new CustDocDataStoreComponentsPage();
    await browser.wait(ec.visibilityOf(custDocDataStoreComponentsPage.title), 5000);
    expect(await custDocDataStoreComponentsPage.getTitle()).to.eq('crmwebApp.custDocDataStore.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custDocDataStoreComponentsPage.entities), ec.visibilityOf(custDocDataStoreComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustDocDataStore page', async () => {
    await custDocDataStoreComponentsPage.clickOnCreateButton();
    custDocDataStoreUpdatePage = new CustDocDataStoreUpdatePage();
    expect(await custDocDataStoreUpdatePage.getPageTitle()).to.eq('crmwebApp.custDocDataStore.home.createOrEditLabel');
    await custDocDataStoreUpdatePage.cancel();
  });

  it('should create and save CustDocDataStores', async () => {
    const nbButtonsBeforeCreate = await custDocDataStoreComponentsPage.countDeleteButtons();

    await custDocDataStoreComponentsPage.clickOnCreateButton();

    await promise.all([
      custDocDataStoreUpdatePage.setDocDataStoreIdInput('5'),
      custDocDataStoreUpdatePage.setDocumentDataInput(absolutePath),
      custDocDataStoreUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custDocDataStoreUpdatePage.setCreatedByInput('createdBy'),
      custDocDataStoreUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custDocDataStoreUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custDocDataStoreUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await custDocDataStoreUpdatePage.getDocDataStoreIdInput()).to.eq('5', 'Expected docDataStoreId value to be equals to 5');
    expect(await custDocDataStoreUpdatePage.getDocumentDataInput()).to.endsWith(
      fileNameToUpload,
      'Expected DocumentData value to be end with ' + fileNameToUpload
    );
    expect(await custDocDataStoreUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custDocDataStoreUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custDocDataStoreUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custDocDataStoreUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custDocDataStoreUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custDocDataStoreUpdatePage.save();
    expect(await custDocDataStoreUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custDocDataStoreComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CustDocDataStore', async () => {
    const nbButtonsBeforeDelete = await custDocDataStoreComponentsPage.countDeleteButtons();
    await custDocDataStoreComponentsPage.clickOnLastDeleteButton();

    custDocDataStoreDeleteDialog = new CustDocDataStoreDeleteDialog();
    expect(await custDocDataStoreDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custDocDataStore.delete.question');
    await custDocDataStoreDeleteDialog.clickOnConfirmButton();

    expect(await custDocDataStoreComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
