import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustContactComponentsPage, CustContactDeleteDialog, CustContactUpdatePage } from './cust-contact.page-object';

const expect = chai.expect;

describe('CustContact e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custContactComponentsPage: CustContactComponentsPage;
  let custContactUpdatePage: CustContactUpdatePage;
  let custContactDeleteDialog: CustContactDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustContacts', async () => {
    await navBarPage.goToEntity('cust-contact');
    custContactComponentsPage = new CustContactComponentsPage();
    await browser.wait(ec.visibilityOf(custContactComponentsPage.title), 5000);
    expect(await custContactComponentsPage.getTitle()).to.eq('crmwebApp.custContact.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custContactComponentsPage.entities), ec.visibilityOf(custContactComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustContact page', async () => {
    await custContactComponentsPage.clickOnCreateButton();
    custContactUpdatePage = new CustContactUpdatePage();
    expect(await custContactUpdatePage.getPageTitle()).to.eq('crmwebApp.custContact.home.createOrEditLabel');
    await custContactUpdatePage.cancel();
  });

  it('should create and save CustContacts', async () => {
    const nbButtonsBeforeCreate = await custContactComponentsPage.countDeleteButtons();

    await custContactComponentsPage.clickOnCreateButton();

    await promise.all([
      custContactUpdatePage.setContactIdInput('5'),
      custContactUpdatePage.setAccountIdInput('accountId'),
      custContactUpdatePage.accountTypeSelectLastOption(),
      custContactUpdatePage.contactTypeSelectLastOption(),
      custContactUpdatePage.setContactPersonInput('contactPerson'),
      custContactUpdatePage.setContactPhoneInput('contactPhone'),
      custContactUpdatePage.setContactEmailInput('contactEmail'),
      custContactUpdatePage.statusSelectLastOption(),
      custContactUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custContactUpdatePage.setCreatedByInput('createdBy'),
      custContactUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custContactUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custContactUpdatePage.setTenantIdInput('tenantId'),
      custContactUpdatePage.customerSelectLastOption()
    ]);

    expect(await custContactUpdatePage.getContactIdInput()).to.eq('5', 'Expected contactId value to be equals to 5');
    expect(await custContactUpdatePage.getAccountIdInput()).to.eq('accountId', 'Expected AccountId value to be equals to accountId');
    expect(await custContactUpdatePage.getContactPersonInput()).to.eq(
      'contactPerson',
      'Expected ContactPerson value to be equals to contactPerson'
    );
    expect(await custContactUpdatePage.getContactPhoneInput()).to.eq(
      'contactPhone',
      'Expected ContactPhone value to be equals to contactPhone'
    );
    expect(await custContactUpdatePage.getContactEmailInput()).to.eq(
      'contactEmail',
      'Expected ContactEmail value to be equals to contactEmail'
    );
    expect(await custContactUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custContactUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custContactUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custContactUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custContactUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custContactUpdatePage.save();
    expect(await custContactUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custContactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CustContact', async () => {
    const nbButtonsBeforeDelete = await custContactComponentsPage.countDeleteButtons();
    await custContactComponentsPage.clickOnLastDeleteButton();

    custContactDeleteDialog = new CustContactDeleteDialog();
    expect(await custContactDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custContact.delete.question');
    await custContactDeleteDialog.clickOnConfirmButton();

    expect(await custContactComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
