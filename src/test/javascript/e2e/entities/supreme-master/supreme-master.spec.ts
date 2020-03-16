import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SupremeMasterComponentsPage, SupremeMasterDeleteDialog, SupremeMasterUpdatePage } from './supreme-master.page-object';

const expect = chai.expect;

describe('SupremeMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let supremeMasterComponentsPage: SupremeMasterComponentsPage;
  let supremeMasterUpdatePage: SupremeMasterUpdatePage;
  let supremeMasterDeleteDialog: SupremeMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SupremeMasters', async () => {
    await navBarPage.goToEntity('supreme-master');
    supremeMasterComponentsPage = new SupremeMasterComponentsPage();
    await browser.wait(ec.visibilityOf(supremeMasterComponentsPage.title), 5000);
    expect(await supremeMasterComponentsPage.getTitle()).to.eq('crmwebApp.supremeMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(supremeMasterComponentsPage.entities), ec.visibilityOf(supremeMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SupremeMaster page', async () => {
    await supremeMasterComponentsPage.clickOnCreateButton();
    supremeMasterUpdatePage = new SupremeMasterUpdatePage();
    expect(await supremeMasterUpdatePage.getPageTitle()).to.eq('crmwebApp.supremeMaster.home.createOrEditLabel');
    await supremeMasterUpdatePage.cancel();
  });

  it('should create and save SupremeMasters', async () => {
    const nbButtonsBeforeCreate = await supremeMasterComponentsPage.countDeleteButtons();

    await supremeMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      supremeMasterUpdatePage.setSupremeSeqIdInput('5'),
      supremeMasterUpdatePage.setSubscriptionIdInput('subscriptionId'),
      supremeMasterUpdatePage.setMsisdnInput('msisdn'),
      supremeMasterUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      supremeMasterUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      supremeMasterUpdatePage.membershipServiceTypeSelectLastOption(),
      supremeMasterUpdatePage.setPeCodeInput('peCode'),
      supremeMasterUpdatePage.setPersonalExecDirectLineInput('personalExecDirectLine'),
      supremeMasterUpdatePage.setPersonalExecNameInput('personalExecName'),
      supremeMasterUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      supremeMasterUpdatePage.setCreatedByInput('createdBy'),
      supremeMasterUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      supremeMasterUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      supremeMasterUpdatePage.setTenantIdInput('tenantId'),
      supremeMasterUpdatePage.custSubscriptionSelectLastOption()
    ]);

    expect(await supremeMasterUpdatePage.getSupremeSeqIdInput()).to.eq('5', 'Expected supremeSeqId value to be equals to 5');
    expect(await supremeMasterUpdatePage.getSubscriptionIdInput()).to.eq(
      'subscriptionId',
      'Expected SubscriptionId value to be equals to subscriptionId'
    );
    expect(await supremeMasterUpdatePage.getMsisdnInput()).to.eq('msisdn', 'Expected Msisdn value to be equals to msisdn');
    expect(await supremeMasterUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await supremeMasterUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await supremeMasterUpdatePage.getPeCodeInput()).to.eq('peCode', 'Expected PeCode value to be equals to peCode');
    expect(await supremeMasterUpdatePage.getPersonalExecDirectLineInput()).to.eq(
      'personalExecDirectLine',
      'Expected PersonalExecDirectLine value to be equals to personalExecDirectLine'
    );
    expect(await supremeMasterUpdatePage.getPersonalExecNameInput()).to.eq(
      'personalExecName',
      'Expected PersonalExecName value to be equals to personalExecName'
    );
    expect(await supremeMasterUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await supremeMasterUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await supremeMasterUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await supremeMasterUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await supremeMasterUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await supremeMasterUpdatePage.save();
    expect(await supremeMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await supremeMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SupremeMaster', async () => {
    const nbButtonsBeforeDelete = await supremeMasterComponentsPage.countDeleteButtons();
    await supremeMasterComponentsPage.clickOnLastDeleteButton();

    supremeMasterDeleteDialog = new SupremeMasterDeleteDialog();
    expect(await supremeMasterDeleteDialog.getDialogTitle()).to.eq('crmwebApp.supremeMaster.delete.question');
    await supremeMasterDeleteDialog.clickOnConfirmButton();

    expect(await supremeMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
