import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SubscriptionGroupComponentsPage,
  SubscriptionGroupDeleteDialog,
  SubscriptionGroupUpdatePage
} from './subscription-group.page-object';

const expect = chai.expect;

describe('SubscriptionGroup e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let subscriptionGroupComponentsPage: SubscriptionGroupComponentsPage;
  let subscriptionGroupUpdatePage: SubscriptionGroupUpdatePage;
  let subscriptionGroupDeleteDialog: SubscriptionGroupDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SubscriptionGroups', async () => {
    await navBarPage.goToEntity('subscription-group');
    subscriptionGroupComponentsPage = new SubscriptionGroupComponentsPage();
    await browser.wait(ec.visibilityOf(subscriptionGroupComponentsPage.title), 5000);
    expect(await subscriptionGroupComponentsPage.getTitle()).to.eq('crmwebApp.subscriptionGroup.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(subscriptionGroupComponentsPage.entities), ec.visibilityOf(subscriptionGroupComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SubscriptionGroup page', async () => {
    await subscriptionGroupComponentsPage.clickOnCreateButton();
    subscriptionGroupUpdatePage = new SubscriptionGroupUpdatePage();
    expect(await subscriptionGroupUpdatePage.getPageTitle()).to.eq('crmwebApp.subscriptionGroup.home.createOrEditLabel');
    await subscriptionGroupUpdatePage.cancel();
  });

  it('should create and save SubscriptionGroups', async () => {
    const nbButtonsBeforeCreate = await subscriptionGroupComponentsPage.countDeleteButtons();

    await subscriptionGroupComponentsPage.clickOnCreateButton();

    await promise.all([
      subscriptionGroupUpdatePage.setGroupIdInput('5'),
      subscriptionGroupUpdatePage.setCustAcctIdInput('custAcctId'),
      subscriptionGroupUpdatePage.setGroupTypeInput('groupType'),
      subscriptionGroupUpdatePage.setGroupNameInput('groupName'),
      subscriptionGroupUpdatePage.statusSelectLastOption(),
      subscriptionGroupUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionGroupUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionGroupUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionGroupUpdatePage.setCreatedByInput('createdBy'),
      subscriptionGroupUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      subscriptionGroupUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      subscriptionGroupUpdatePage.setTenantIdInput('tenantId'),
      subscriptionGroupUpdatePage.customerSelectLastOption()
    ]);

    expect(await subscriptionGroupUpdatePage.getGroupIdInput()).to.eq('5', 'Expected groupId value to be equals to 5');
    expect(await subscriptionGroupUpdatePage.getCustAcctIdInput()).to.eq(
      'custAcctId',
      'Expected CustAcctId value to be equals to custAcctId'
    );
    expect(await subscriptionGroupUpdatePage.getGroupTypeInput()).to.eq('groupType', 'Expected GroupType value to be equals to groupType');
    expect(await subscriptionGroupUpdatePage.getGroupNameInput()).to.eq('groupName', 'Expected GroupName value to be equals to groupName');
    expect(await subscriptionGroupUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionGroupUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionGroupUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionGroupUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await subscriptionGroupUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await subscriptionGroupUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await subscriptionGroupUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await subscriptionGroupUpdatePage.save();
    expect(await subscriptionGroupUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await subscriptionGroupComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SubscriptionGroup', async () => {
    const nbButtonsBeforeDelete = await subscriptionGroupComponentsPage.countDeleteButtons();
    await subscriptionGroupComponentsPage.clickOnLastDeleteButton();

    subscriptionGroupDeleteDialog = new SubscriptionGroupDeleteDialog();
    expect(await subscriptionGroupDeleteDialog.getDialogTitle()).to.eq('crmwebApp.subscriptionGroup.delete.question');
    await subscriptionGroupDeleteDialog.clickOnConfirmButton();

    expect(await subscriptionGroupComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
