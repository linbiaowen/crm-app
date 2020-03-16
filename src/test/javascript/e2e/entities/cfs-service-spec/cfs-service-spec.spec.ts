import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CfsServiceSpecComponentsPage, CfsServiceSpecDeleteDialog, CfsServiceSpecUpdatePage } from './cfs-service-spec.page-object';

const expect = chai.expect;

describe('CfsServiceSpec e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let cfsServiceSpecComponentsPage: CfsServiceSpecComponentsPage;
  let cfsServiceSpecUpdatePage: CfsServiceSpecUpdatePage;
  let cfsServiceSpecDeleteDialog: CfsServiceSpecDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CfsServiceSpecs', async () => {
    await navBarPage.goToEntity('cfs-service-spec');
    cfsServiceSpecComponentsPage = new CfsServiceSpecComponentsPage();
    await browser.wait(ec.visibilityOf(cfsServiceSpecComponentsPage.title), 5000);
    expect(await cfsServiceSpecComponentsPage.getTitle()).to.eq('crmwebApp.cfsServiceSpec.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(cfsServiceSpecComponentsPage.entities), ec.visibilityOf(cfsServiceSpecComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CfsServiceSpec page', async () => {
    await cfsServiceSpecComponentsPage.clickOnCreateButton();
    cfsServiceSpecUpdatePage = new CfsServiceSpecUpdatePage();
    expect(await cfsServiceSpecUpdatePage.getPageTitle()).to.eq('crmwebApp.cfsServiceSpec.home.createOrEditLabel');
    await cfsServiceSpecUpdatePage.cancel();
  });

  it('should create and save CfsServiceSpecs', async () => {
    const nbButtonsBeforeCreate = await cfsServiceSpecComponentsPage.countDeleteButtons();

    await cfsServiceSpecComponentsPage.clickOnCreateButton();

    await promise.all([
      cfsServiceSpecUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      cfsServiceSpecUpdatePage.setServiceSpecDescInput('serviceSpecDesc'),
      cfsServiceSpecUpdatePage.setServiceIdInput('serviceId'),
      cfsServiceSpecUpdatePage.setVoiceSpecIdInput('5'),
      cfsServiceSpecUpdatePage.setDataSpecIdInput('5'),
      cfsServiceSpecUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cfsServiceSpecUpdatePage.setCreatedByInput('createdBy'),
      cfsServiceSpecUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      cfsServiceSpecUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      cfsServiceSpecUpdatePage.setTenantIdInput('tenantId'),
      cfsServiceSpecUpdatePage.voiceServiceSpecSelectLastOption(),
      cfsServiceSpecUpdatePage.dataServiceSpecSelectLastOption(),
      cfsServiceSpecUpdatePage.productSpecificationSelectLastOption()
    ]);

    expect(await cfsServiceSpecUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    expect(await cfsServiceSpecUpdatePage.getServiceSpecDescInput()).to.eq(
      'serviceSpecDesc',
      'Expected ServiceSpecDesc value to be equals to serviceSpecDesc'
    );
    expect(await cfsServiceSpecUpdatePage.getServiceIdInput()).to.eq('serviceId', 'Expected ServiceId value to be equals to serviceId');
    expect(await cfsServiceSpecUpdatePage.getVoiceSpecIdInput()).to.eq('5', 'Expected voiceSpecId value to be equals to 5');
    expect(await cfsServiceSpecUpdatePage.getDataSpecIdInput()).to.eq('5', 'Expected dataSpecId value to be equals to 5');
    expect(await cfsServiceSpecUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await cfsServiceSpecUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await cfsServiceSpecUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await cfsServiceSpecUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await cfsServiceSpecUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await cfsServiceSpecUpdatePage.save();
    expect(await cfsServiceSpecUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await cfsServiceSpecComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CfsServiceSpec', async () => {
    const nbButtonsBeforeDelete = await cfsServiceSpecComponentsPage.countDeleteButtons();
    await cfsServiceSpecComponentsPage.clickOnLastDeleteButton();

    cfsServiceSpecDeleteDialog = new CfsServiceSpecDeleteDialog();
    expect(await cfsServiceSpecDeleteDialog.getDialogTitle()).to.eq('crmwebApp.cfsServiceSpec.delete.question');
    await cfsServiceSpecDeleteDialog.clickOnConfirmButton();

    expect(await cfsServiceSpecComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
