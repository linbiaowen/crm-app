import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  SalesChannelMasterComponentsPage,
  SalesChannelMasterDeleteDialog,
  SalesChannelMasterUpdatePage
} from './sales-channel-master.page-object';

const expect = chai.expect;

describe('SalesChannelMaster e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let salesChannelMasterComponentsPage: SalesChannelMasterComponentsPage;
  let salesChannelMasterUpdatePage: SalesChannelMasterUpdatePage;
  let salesChannelMasterDeleteDialog: SalesChannelMasterDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SalesChannelMasters', async () => {
    await navBarPage.goToEntity('sales-channel-master');
    salesChannelMasterComponentsPage = new SalesChannelMasterComponentsPage();
    await browser.wait(ec.visibilityOf(salesChannelMasterComponentsPage.title), 5000);
    expect(await salesChannelMasterComponentsPage.getTitle()).to.eq('crmwebApp.salesChannelMaster.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(salesChannelMasterComponentsPage.entities), ec.visibilityOf(salesChannelMasterComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SalesChannelMaster page', async () => {
    await salesChannelMasterComponentsPage.clickOnCreateButton();
    salesChannelMasterUpdatePage = new SalesChannelMasterUpdatePage();
    expect(await salesChannelMasterUpdatePage.getPageTitle()).to.eq('crmwebApp.salesChannelMaster.home.createOrEditLabel');
    await salesChannelMasterUpdatePage.cancel();
  });

  it('should create and save SalesChannelMasters', async () => {
    const nbButtonsBeforeCreate = await salesChannelMasterComponentsPage.countDeleteButtons();

    await salesChannelMasterComponentsPage.clickOnCreateButton();

    await promise.all([
      salesChannelMasterUpdatePage.setSalesChannelInput('salesChannel'),
      salesChannelMasterUpdatePage.setSalesChannelDescInput('salesChannelDesc'),
      salesChannelMasterUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      salesChannelMasterUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      salesChannelMasterUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      salesChannelMasterUpdatePage.setCreatedByInput('createdBy'),
      salesChannelMasterUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      salesChannelMasterUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      salesChannelMasterUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await salesChannelMasterUpdatePage.getSalesChannelInput()).to.eq(
      'salesChannel',
      'Expected SalesChannel value to be equals to salesChannel'
    );
    expect(await salesChannelMasterUpdatePage.getSalesChannelDescInput()).to.eq(
      'salesChannelDesc',
      'Expected SalesChannelDesc value to be equals to salesChannelDesc'
    );
    expect(await salesChannelMasterUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await salesChannelMasterUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await salesChannelMasterUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await salesChannelMasterUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await salesChannelMasterUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await salesChannelMasterUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await salesChannelMasterUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await salesChannelMasterUpdatePage.save();
    expect(await salesChannelMasterUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await salesChannelMasterComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last SalesChannelMaster', async () => {
    const nbButtonsBeforeDelete = await salesChannelMasterComponentsPage.countDeleteButtons();
    await salesChannelMasterComponentsPage.clickOnLastDeleteButton();

    salesChannelMasterDeleteDialog = new SalesChannelMasterDeleteDialog();
    expect(await salesChannelMasterDeleteDialog.getDialogTitle()).to.eq('crmwebApp.salesChannelMaster.delete.question');
    await salesChannelMasterDeleteDialog.clickOnConfirmButton();

    expect(await salesChannelMasterComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
