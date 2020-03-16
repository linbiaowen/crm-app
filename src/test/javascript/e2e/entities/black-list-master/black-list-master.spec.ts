import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BlackListMasterComponentsPage, BlackListMasterDeleteDialog, BlackListMasterUpdatePage } from './black-list-master.page-object';

const expect = chai.expect;

describe('BlackListMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let blackListMasterComponentsPage: BlackListMasterComponentsPage;
  let blackListMasterUpdatePage: BlackListMasterUpdatePage;
  let blackListMasterDeleteDialog: BlackListMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load BlackListMasters', async () => {
    await navBarPage.goToEntity('black-list-master');
    blackListMasterComponentsPage = new BlackListMasterComponentsPage();
    await browser.wait(ec.visibilityOf(blackListMasterComponentsPage.title), 5000);
    expect(await blackListMasterComponentsPage.getTitle()).to.eq('crmwebApp.blackListMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(blackListMasterComponentsPage.entities), ec.visibilityOf(blackListMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create BlackListMaster page', async () => {
    await blackListMasterComponentsPage.clickOnCreateButton();
    blackListMasterUpdatePage = new BlackListMasterUpdatePage();
    expect(await blackListMasterUpdatePage.getPageTitle()).to.eq('crmwebApp.blackListMaster.home.createOrEditLabel');
    await blackListMasterUpdatePage.cancel();
  });

  it('should create and save BlackListMasters', async () => {
    const nbButtonsBeforeCreate = await blackListMasterComponentsPage.countDeleteButtons();

    await blackListMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      blackListMasterUpdatePage.setBlackListCodeInput('blackListCode'),
      blackListMasterUpdatePage.setBlackListReasonInput('blackListReason'),
      blackListMasterUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      blackListMasterUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      blackListMasterUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      blackListMasterUpdatePage.setCreatedByInput('createdBy'),
      blackListMasterUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      blackListMasterUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      blackListMasterUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await blackListMasterUpdatePage.getBlackListCodeInput()).to.eq(
      'blackListCode',
      'Expected BlackListCode value to be equals to blackListCode'
    );
    expect(await blackListMasterUpdatePage.getBlackListReasonInput()).to.eq(
      'blackListReason',
      'Expected BlackListReason value to be equals to blackListReason'
    );
    expect(await blackListMasterUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await blackListMasterUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await blackListMasterUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await blackListMasterUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await blackListMasterUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await blackListMasterUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await blackListMasterUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await blackListMasterUpdatePage.save();
    expect(await blackListMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await blackListMasterComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last BlackListMaster', async () => {
    const nbButtonsBeforeDelete = await blackListMasterComponentsPage.countDeleteButtons();
    await blackListMasterComponentsPage.clickOnLastDeleteButton();

    blackListMasterDeleteDialog = new BlackListMasterDeleteDialog();
    expect(await blackListMasterDeleteDialog.getDialogTitle()).to.eq('crmwebApp.blackListMaster.delete.question');
    await blackListMasterDeleteDialog.clickOnConfirmButton();

    expect(await blackListMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
