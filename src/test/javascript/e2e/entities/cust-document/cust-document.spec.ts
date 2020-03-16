import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustDocumentComponentsPage, CustDocumentDeleteDialog, CustDocumentUpdatePage } from './cust-document.page-object';

const expect = chai.expect;

describe('CustDocument e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custDocumentComponentsPage: CustDocumentComponentsPage;
  let custDocumentUpdatePage: CustDocumentUpdatePage;
  let custDocumentDeleteDialog: CustDocumentDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustDocuments', async () => {
    await navBarPage.goToEntity('cust-document');
    custDocumentComponentsPage = new CustDocumentComponentsPage();
    await browser.wait(ec.visibilityOf(custDocumentComponentsPage.title), 5000);
    expect(await custDocumentComponentsPage.getTitle()).to.eq('crmwebApp.custDocument.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custDocumentComponentsPage.entities), ec.visibilityOf(custDocumentComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustDocument page', async () => {
    await custDocumentComponentsPage.clickOnCreateButton();
    custDocumentUpdatePage = new CustDocumentUpdatePage();
    expect(await custDocumentUpdatePage.getPageTitle()).to.eq('crmwebApp.custDocument.home.createOrEditLabel');
    await custDocumentUpdatePage.cancel();
  });

  it('should create and save CustDocuments', async () => {
    const nbButtonsBeforeCreate = await custDocumentComponentsPage.countDeleteButtons();

    await custDocumentComponentsPage.clickOnCreateButton();

    await promise.all([
      custDocumentUpdatePage.setCustDocIdInput('custDocId'),
      custDocumentUpdatePage.setCustAcctIdInput('custAcctId'),
      custDocumentUpdatePage.setSubscriptionIdInput('subscriptionId'),
      custDocumentUpdatePage.docTypeSelectLastOption(),
      custDocumentUpdatePage.setDocIdNumberInput('docIdNumber'),
      custDocumentUpdatePage.setDocDataStoreIdInput('5'),
      custDocumentUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custDocumentUpdatePage.setCreatedByInput('createdBy'),
      custDocumentUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custDocumentUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custDocumentUpdatePage.setTenantIdInput('tenantId'),
      custDocumentUpdatePage.docDataStoreSelectLastOption(),
      custDocumentUpdatePage.customerSelectLastOption(),
      custDocumentUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await custDocumentUpdatePage.getCustDocIdInput()).to.eq('custDocId', 'Expected CustDocId value to be equals to custDocId');
    expect(await custDocumentUpdatePage.getCustAcctIdInput()).to.eq('custAcctId', 'Expected CustAcctId value to be equals to custAcctId');
    expect(await custDocumentUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await custDocumentUpdatePage.getDocIdNumberInput()).to.eq(
      'docIdNumber',
      'Expected DocIdNumber value to be equals to docIdNumber'
    );
    expect(await custDocumentUpdatePage.getDocDataStoreIdInput()).to.eq('5', 'Expected docDataStoreId value to be equals to 5');
    expect(await custDocumentUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custDocumentUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custDocumentUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custDocumentUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custDocumentUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custDocumentUpdatePage.save();
    expect(await custDocumentUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custDocumentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CustDocument', async () => {
    const nbButtonsBeforeDelete = await custDocumentComponentsPage.countDeleteButtons();
    await custDocumentComponentsPage.clickOnLastDeleteButton();

    custDocumentDeleteDialog = new CustDocumentDeleteDialog();
    expect(await custDocumentDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custDocument.delete.question');
    await custDocumentDeleteDialog.clickOnConfirmButton();

    expect(await custDocumentComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
