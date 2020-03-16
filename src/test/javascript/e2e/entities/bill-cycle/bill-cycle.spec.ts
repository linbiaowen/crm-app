import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BillCycleComponentsPage, BillCycleDeleteDialog, BillCycleUpdatePage } from './bill-cycle.page-object';

const expect = chai.expect;

describe('BillCycle e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let billCycleComponentsPage: BillCycleComponentsPage;
  let billCycleUpdatePage: BillCycleUpdatePage;
  let billCycleDeleteDialog: BillCycleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load BillCycles', async () => {
    await navBarPage.goToEntity('bill-cycle');
    billCycleComponentsPage = new BillCycleComponentsPage();
    await browser.wait(ec.visibilityOf(billCycleComponentsPage.title), 5000);
    expect(await billCycleComponentsPage.getTitle()).to.eq('crmwebApp.billCycle.home.title');
    await browser.wait(ec.or(ec.visibilityOf(billCycleComponentsPage.entities), ec.visibilityOf(billCycleComponentsPage.noResult)), 1000);
  });

  it('should load create BillCycle page', async () => {
    await billCycleComponentsPage.clickOnCreateButton();
    billCycleUpdatePage = new BillCycleUpdatePage();
    expect(await billCycleUpdatePage.getPageTitle()).to.eq('crmwebApp.billCycle.home.createOrEditLabel');
    await billCycleUpdatePage.cancel();
  });

  it('should create and save BillCycles', async () => {
    const nbButtonsBeforeCreate = await billCycleComponentsPage.countDeleteButtons();

    await billCycleComponentsPage.clickOnCreateButton();

    await promise.all([
      billCycleUpdatePage.setBillCycleIdInput('5'),
      billCycleUpdatePage.setBillCycleDescInput('billCycleDesc'),
      billCycleUpdatePage.setBillCycleInput('5'),
      billCycleUpdatePage.setBillFrequencyInput('billFrequency'),
      billCycleUpdatePage.setBillCycleStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      billCycleUpdatePage.setBillCycleEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      billCycleUpdatePage.setDueDateOffsetInput('5'),
      billCycleUpdatePage.setDirectDebitProcessDayInput('5'),
      billCycleUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      billCycleUpdatePage.setCreatedByInput('createdBy'),
      billCycleUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      billCycleUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      billCycleUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await billCycleUpdatePage.getBillCycleIdInput()).to.eq('5', 'Expected billCycleId value to be equals to 5');
    expect(await billCycleUpdatePage.getBillCycleDescInput()).to.eq(
      'billCycleDesc',
      'Expected BillCycleDesc value to be equals to billCycleDesc'
    );
    expect(await billCycleUpdatePage.getBillCycleInput()).to.eq('5', 'Expected billCycle value to be equals to 5');
    expect(await billCycleUpdatePage.getBillFrequencyInput()).to.eq(
      'billFrequency',
      'Expected BillFrequency value to be equals to billFrequency'
    );
    expect(await billCycleUpdatePage.getBillCycleStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected billCycleStartDate value to be equals to 2000-12-31'
    );
    expect(await billCycleUpdatePage.getBillCycleEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected billCycleEndDate value to be equals to 2000-12-31'
    );
    expect(await billCycleUpdatePage.getDueDateOffsetInput()).to.eq('5', 'Expected dueDateOffset value to be equals to 5');
    expect(await billCycleUpdatePage.getDirectDebitProcessDayInput()).to.eq('5', 'Expected directDebitProcessDay value to be equals to 5');
    expect(await billCycleUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await billCycleUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await billCycleUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await billCycleUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await billCycleUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await billCycleUpdatePage.save();
    expect(await billCycleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await billCycleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last BillCycle', async () => {
    const nbButtonsBeforeDelete = await billCycleComponentsPage.countDeleteButtons();
    await billCycleComponentsPage.clickOnLastDeleteButton();

    billCycleDeleteDialog = new BillCycleDeleteDialog();
    expect(await billCycleDeleteDialog.getDialogTitle()).to.eq('crmwebApp.billCycle.delete.question');
    await billCycleDeleteDialog.clickOnConfirmButton();

    expect(await billCycleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
