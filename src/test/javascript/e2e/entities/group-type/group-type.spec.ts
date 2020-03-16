import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GroupTypeComponentsPage, GroupTypeDeleteDialog, GroupTypeUpdatePage } from './group-type.page-object';

const expect = chai.expect;

describe('GroupType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let groupTypeComponentsPage: GroupTypeComponentsPage;
  let groupTypeUpdatePage: GroupTypeUpdatePage;
  let groupTypeDeleteDialog: GroupTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GroupTypes', async () => {
    await navBarPage.goToEntity('group-type');
    groupTypeComponentsPage = new GroupTypeComponentsPage();
    await browser.wait(ec.visibilityOf(groupTypeComponentsPage.title), 5000);
    expect(await groupTypeComponentsPage.getTitle()).to.eq('crmwebApp.groupType.home.title');
    await browser.wait(ec.or(ec.visibilityOf(groupTypeComponentsPage.entities), ec.visibilityOf(groupTypeComponentsPage.noResult)), 1000);
  });

  it('should load create GroupType page', async () => {
    await groupTypeComponentsPage.clickOnCreateButton();
    groupTypeUpdatePage = new GroupTypeUpdatePage();
    expect(await groupTypeUpdatePage.getPageTitle()).to.eq('crmwebApp.groupType.home.createOrEditLabel');
    await groupTypeUpdatePage.cancel();
  });

  it('should create and save GroupTypes', async () => {
    const nbButtonsBeforeCreate = await groupTypeComponentsPage.countDeleteButtons();

    await groupTypeComponentsPage.clickOnCreateButton();

    await promise.all([
      groupTypeUpdatePage.setGroupTypeIdInput('5'),
      groupTypeUpdatePage.setGroupTypeInput('groupType'),
      groupTypeUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupTypeUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupTypeUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupTypeUpdatePage.setCreatedByInput('createdBy'),
      groupTypeUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      groupTypeUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      groupTypeUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await groupTypeUpdatePage.getGroupTypeIdInput()).to.eq('5', 'Expected groupTypeId value to be equals to 5');
    expect(await groupTypeUpdatePage.getGroupTypeInput()).to.eq('groupType', 'Expected GroupType value to be equals to groupType');
    expect(await groupTypeUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await groupTypeUpdatePage.getEndDateInput()).to.contain('2001-01-01T02:30', 'Expected endDate value to be equals to 2000-12-31');
    expect(await groupTypeUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await groupTypeUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await groupTypeUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await groupTypeUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await groupTypeUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await groupTypeUpdatePage.save();
    expect(await groupTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await groupTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GroupType', async () => {
    const nbButtonsBeforeDelete = await groupTypeComponentsPage.countDeleteButtons();
    await groupTypeComponentsPage.clickOnLastDeleteButton();

    groupTypeDeleteDialog = new GroupTypeDeleteDialog();
    expect(await groupTypeDeleteDialog.getDialogTitle()).to.eq('crmwebApp.groupType.delete.question');
    await groupTypeDeleteDialog.clickOnConfirmButton();

    expect(await groupTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
