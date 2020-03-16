import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CfsServicesComponentsPage, CfsServicesDeleteDialog, CfsServicesUpdatePage } from './cfs-services.page-object';

const expect = chai.expect;

describe('CfsServices e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cfsServicesComponentsPage: CfsServicesComponentsPage;
  let cfsServicesUpdatePage: CfsServicesUpdatePage;
  let cfsServicesDeleteDialog: CfsServicesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CfsServices', async () => {
    await navBarPage.goToEntity('cfs-services');
    cfsServicesComponentsPage = new CfsServicesComponentsPage();
    await browser.wait(ec.visibilityOf(cfsServicesComponentsPage.title), 5000);
    expect(await cfsServicesComponentsPage.getTitle()).to.eq('crmwebApp.cfsServices.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(cfsServicesComponentsPage.entities), ec.visibilityOf(cfsServicesComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CfsServices page', async () => {
    await cfsServicesComponentsPage.clickOnCreateButton();
    cfsServicesUpdatePage = new CfsServicesUpdatePage();
    expect(await cfsServicesUpdatePage.getPageTitle()).to.eq('crmwebApp.cfsServices.home.createOrEditLabel');
    await cfsServicesUpdatePage.cancel();
  });

  it('should create and save CfsServices', async () => {
    const nbButtonsBeforeCreate = await cfsServicesComponentsPage.countDeleteButtons();

    await cfsServicesComponentsPage.clickOnCreateButton();

    await promise.all([
      cfsServicesUpdatePage.setServiceIdInput('serviceId'),
      cfsServicesUpdatePage.setServiceNameInput('serviceName'),
      cfsServicesUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cfsServicesUpdatePage.setCreatedByInput('createdBy'),
      cfsServicesUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cfsServicesUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      cfsServicesUpdatePage.setTenantIdInput('tenantId'),
      cfsServicesUpdatePage.cfsServiceSpecSelectLastOption()
    ]);

    expect(await cfsServicesUpdatePage.getServiceIdInput()).to.eq('serviceId', 'Expected ServiceId value to be equals to serviceId');
    expect(await cfsServicesUpdatePage.getServiceNameInput()).to.eq(
      'serviceName',
      'Expected ServiceName value to be equals to serviceName'
    );
    expect(await cfsServicesUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await cfsServicesUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await cfsServicesUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await cfsServicesUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await cfsServicesUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await cfsServicesUpdatePage.save();
    expect(await cfsServicesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cfsServicesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CfsServices', async () => {
    const nbButtonsBeforeDelete = await cfsServicesComponentsPage.countDeleteButtons();
    await cfsServicesComponentsPage.clickOnLastDeleteButton();

    cfsServicesDeleteDialog = new CfsServicesDeleteDialog();
    expect(await cfsServicesDeleteDialog.getDialogTitle()).to.eq('crmwebApp.cfsServices.delete.question');
    await cfsServicesDeleteDialog.clickOnConfirmButton();

    expect(await cfsServicesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
