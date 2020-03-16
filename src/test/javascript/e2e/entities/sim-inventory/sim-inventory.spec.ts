import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SimInventoryComponentsPage, SimInventoryDeleteDialog, SimInventoryUpdatePage } from './sim-inventory.page-object';

const expect = chai.expect;

describe('SimInventory e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let simInventoryComponentsPage: SimInventoryComponentsPage;
  let simInventoryUpdatePage: SimInventoryUpdatePage;
  let simInventoryDeleteDialog: SimInventoryDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load SimInventories', async () => {
    await navBarPage.goToEntity('sim-inventory');
    simInventoryComponentsPage = new SimInventoryComponentsPage();
    await browser.wait(ec.visibilityOf(simInventoryComponentsPage.title), 5000);
    expect(await simInventoryComponentsPage.getTitle()).to.eq('crmwebApp.simInventory.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(simInventoryComponentsPage.entities), ec.visibilityOf(simInventoryComponentsPage.noResult)),
      1000
    );
  });

  it('should load create SimInventory page', async () => {
    await simInventoryComponentsPage.clickOnCreateButton();
    simInventoryUpdatePage = new SimInventoryUpdatePage();
    expect(await simInventoryUpdatePage.getPageTitle()).to.eq('crmwebApp.simInventory.home.createOrEditLabel');
    await simInventoryUpdatePage.cancel();
  });

  it('should create and save SimInventories', async () => {
    const nbButtonsBeforeCreate = await simInventoryComponentsPage.countDeleteButtons();

    await simInventoryComponentsPage.clickOnCreateButton();

    await promise.all([
      simInventoryUpdatePage.setIccidInput('iccid'),
      simInventoryUpdatePage.setImsiInput('imsi'),
      simInventoryUpdatePage.setKiInput('ki'),
      simInventoryUpdatePage.setK4snoInput('k4sno'),
      simInventoryUpdatePage.setOpsnoInput('opsno'),
      simInventoryUpdatePage.setOfferIdInput('offerId'),
      simInventoryUpdatePage.setOfferNameInput('offerName'),
      simInventoryUpdatePage.statusSelectLastOption(),
      simInventoryUpdatePage.setOfferValidFromDateInput('offerValidFromDate'),
      simInventoryUpdatePage.setOfferValidToDateInput('offerValidToDate'),
      simInventoryUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      simInventoryUpdatePage.setCreatedByInput('createdBy'),
      simInventoryUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      simInventoryUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      simInventoryUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await simInventoryUpdatePage.getIccidInput()).to.eq('iccid', 'Expected Iccid value to be equals to iccid');
    expect(await simInventoryUpdatePage.getImsiInput()).to.eq('imsi', 'Expected Imsi value to be equals to imsi');
    expect(await simInventoryUpdatePage.getKiInput()).to.eq('ki', 'Expected Ki value to be equals to ki');
    expect(await simInventoryUpdatePage.getK4snoInput()).to.eq('k4sno', 'Expected K4sno value to be equals to k4sno');
    expect(await simInventoryUpdatePage.getOpsnoInput()).to.eq('opsno', 'Expected Opsno value to be equals to opsno');
    expect(await simInventoryUpdatePage.getOfferIdInput()).to.eq('offerId', 'Expected OfferId value to be equals to offerId');
    expect(await simInventoryUpdatePage.getOfferNameInput()).to.eq('offerName', 'Expected OfferName value to be equals to offerName');
    expect(await simInventoryUpdatePage.getOfferValidFromDateInput()).to.eq(
      'offerValidFromDate',
      'Expected OfferValidFromDate value to be equals to offerValidFromDate'
    );
    expect(await simInventoryUpdatePage.getOfferValidToDateInput()).to.eq(
      'offerValidToDate',
      'Expected OfferValidToDate value to be equals to offerValidToDate'
    );
    expect(await simInventoryUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await simInventoryUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await simInventoryUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await simInventoryUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await simInventoryUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await simInventoryUpdatePage.save();
    expect(await simInventoryUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await simInventoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last SimInventory', async () => {
    const nbButtonsBeforeDelete = await simInventoryComponentsPage.countDeleteButtons();
    await simInventoryComponentsPage.clickOnLastDeleteButton();

    simInventoryDeleteDialog = new SimInventoryDeleteDialog();
    expect(await simInventoryDeleteDialog.getDialogTitle()).to.eq('crmwebApp.simInventory.delete.question');
    await simInventoryDeleteDialog.clickOnConfirmButton();

    expect(await simInventoryComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
