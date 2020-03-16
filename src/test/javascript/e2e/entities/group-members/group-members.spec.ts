import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GroupMembersComponentsPage, GroupMembersDeleteDialog, GroupMembersUpdatePage } from './group-members.page-object';

const expect = chai.expect;

describe('GroupMembers e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupMembersComponentsPage: GroupMembersComponentsPage;
  let groupMembersUpdatePage: GroupMembersUpdatePage;
  let groupMembersDeleteDialog: GroupMembersDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GroupMembers', async () => {
    await navBarPage.goToEntity('group-members');
    groupMembersComponentsPage = new GroupMembersComponentsPage();
    await browser.wait(ec.visibilityOf(groupMembersComponentsPage.title), 5000);
    expect(await groupMembersComponentsPage.getTitle()).to.eq('crmwebApp.groupMembers.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(groupMembersComponentsPage.entities), ec.visibilityOf(groupMembersComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GroupMembers page', async () => {
    await groupMembersComponentsPage.clickOnCreateButton();
    groupMembersUpdatePage = new GroupMembersUpdatePage();
    expect(await groupMembersUpdatePage.getPageTitle()).to.eq('crmwebApp.groupMembers.home.createOrEditLabel');
    await groupMembersUpdatePage.cancel();
  });

  it('should create and save GroupMembers', async () => {
    const nbButtonsBeforeCreate = await groupMembersComponentsPage.countDeleteButtons();

    await groupMembersComponentsPage.clickOnCreateButton();

    await promise.all([
      groupMembersUpdatePage.setGroupIdInput('5'),
      groupMembersUpdatePage.setSubscriptionIdInput('subscriptionId'),
      groupMembersUpdatePage.setMsisdnInput('msisdn'),
      groupMembersUpdatePage.groupRoleSelectLastOption(),
      groupMembersUpdatePage.setEndReasonCodeInput('endReasonCode'),
      groupMembersUpdatePage.setRemarksInput('remarks'),
      groupMembersUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupMembersUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupMembersUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupMembersUpdatePage.setCreatedByInput('createdBy'),
      groupMembersUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupMembersUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      groupMembersUpdatePage.setTenantIdInput('tenantId'),
      groupMembersUpdatePage.subscriptionGroupSelectLastOption()
    ]);

    expect(await groupMembersUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await groupMembersUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await groupMembersUpdatePage.getMsisdnInput()).to.eq('msisdn', 'Expected Msisdn value to be equals to msisdn');
    expect(await groupMembersUpdatePage.getEndReasonCodeInput()).to.eq(
      'endReasonCode',
      'Expected EndReasonCode value to be equals to endReasonCode'
    );
    expect(await groupMembersUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await groupMembersUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await groupMembersUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await groupMembersUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await groupMembersUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await groupMembersUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await groupMembersUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await groupMembersUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await groupMembersUpdatePage.save();
    expect(await groupMembersUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await groupMembersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GroupMembers', async () => {
    const nbButtonsBeforeDelete = await groupMembersComponentsPage.countDeleteButtons();
    await groupMembersComponentsPage.clickOnLastDeleteButton();

    groupMembersDeleteDialog = new GroupMembersDeleteDialog();
    expect(await groupMembersDeleteDialog.getDialogTitle()).to.eq('crmwebApp.groupMembers.delete.question');
    await groupMembersDeleteDialog.clickOnConfirmButton();

    expect(await groupMembersComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
