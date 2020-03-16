import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GroupEndReasonComponentsPage, GroupEndReasonDeleteDialog, GroupEndReasonUpdatePage } from './group-end-reason.page-object';

const expect = chai.expect;

describe('GroupEndReason e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupEndReasonComponentsPage: GroupEndReasonComponentsPage;
  let groupEndReasonUpdatePage: GroupEndReasonUpdatePage;
  let groupEndReasonDeleteDialog: GroupEndReasonDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GroupEndReasons', async () => {
    await navBarPage.goToEntity('group-end-reason');
    groupEndReasonComponentsPage = new GroupEndReasonComponentsPage();
    await browser.wait(ec.visibilityOf(groupEndReasonComponentsPage.title), 5000);
    expect(await groupEndReasonComponentsPage.getTitle()).to.eq('crmwebApp.groupEndReason.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(groupEndReasonComponentsPage.entities), ec.visibilityOf(groupEndReasonComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GroupEndReason page', async () => {
    await groupEndReasonComponentsPage.clickOnCreateButton();
    groupEndReasonUpdatePage = new GroupEndReasonUpdatePage();
    expect(await groupEndReasonUpdatePage.getPageTitle()).to.eq('crmwebApp.groupEndReason.home.createOrEditLabel');
    await groupEndReasonUpdatePage.cancel();
  });

  it('should create and save GroupEndReasons', async () => {
    const nbButtonsBeforeCreate = await groupEndReasonComponentsPage.countDeleteButtons();

    await groupEndReasonComponentsPage.clickOnCreateButton();

    await promise.all([
      groupEndReasonUpdatePage.setEndReasonCodeInput('endReasonCode'),
      groupEndReasonUpdatePage.setEndReasonInput('endReason'),
      groupEndReasonUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupEndReasonUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupEndReasonUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupEndReasonUpdatePage.setCreatedByInput('createdBy'),
      groupEndReasonUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupEndReasonUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      groupEndReasonUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await groupEndReasonUpdatePage.getEndReasonCodeInput()).to.eq(
      'endReasonCode',
      'Expected EndReasonCode value to be equals to endReasonCode'
    );
    expect(await groupEndReasonUpdatePage.getEndReasonInput()).to.eq('endReason', 'Expected EndReason value to be equals to endReason');
    expect(await groupEndReasonUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await groupEndReasonUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await groupEndReasonUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await groupEndReasonUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await groupEndReasonUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await groupEndReasonUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await groupEndReasonUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await groupEndReasonUpdatePage.save();
    expect(await groupEndReasonUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await groupEndReasonComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GroupEndReason', async () => {
    const nbButtonsBeforeDelete = await groupEndReasonComponentsPage.countDeleteButtons();
    await groupEndReasonComponentsPage.clickOnLastDeleteButton();

    groupEndReasonDeleteDialog = new GroupEndReasonDeleteDialog();
    expect(await groupEndReasonDeleteDialog.getDialogTitle()).to.eq('crmwebApp.groupEndReason.delete.question');
    await groupEndReasonDeleteDialog.clickOnConfirmButton();

    expect(await groupEndReasonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
