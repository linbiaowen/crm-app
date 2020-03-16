import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DataServiceSpecComponentsPage, DataServiceSpecDeleteDialog, DataServiceSpecUpdatePage } from './data-service-spec.page-object';

const expect = chai.expect;

describe('DataServiceSpec e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let dataServiceSpecComponentsPage: DataServiceSpecComponentsPage;
  let dataServiceSpecUpdatePage: DataServiceSpecUpdatePage;
  let dataServiceSpecDeleteDialog: DataServiceSpecDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DataServiceSpecs', async () => {
    await navBarPage.goToEntity('data-service-spec');
    dataServiceSpecComponentsPage = new DataServiceSpecComponentsPage();
    await browser.wait(ec.visibilityOf(dataServiceSpecComponentsPage.title), 5000);
    expect(await dataServiceSpecComponentsPage.getTitle()).to.eq('crmwebApp.dataServiceSpec.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(dataServiceSpecComponentsPage.entities), ec.visibilityOf(dataServiceSpecComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DataServiceSpec page', async () => {
    await dataServiceSpecComponentsPage.clickOnCreateButton();
    dataServiceSpecUpdatePage = new DataServiceSpecUpdatePage();
    expect(await dataServiceSpecUpdatePage.getPageTitle()).to.eq('crmwebApp.dataServiceSpec.home.createOrEditLabel');
    await dataServiceSpecUpdatePage.cancel();
  });

  it('should create and save DataServiceSpecs', async () => {
    const nbButtonsBeforeCreate = await dataServiceSpecComponentsPage.countDeleteButtons();

    await dataServiceSpecComponentsPage.clickOnCreateButton();

    await promise.all([
      dataServiceSpecUpdatePage.setDataSpecIdInput('5'),
      dataServiceSpecUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      dataServiceSpecUpdatePage.setMaxEntitlementInput('maxEntitlement'),
      dataServiceSpecUpdatePage.setMaxAccessSpeedInput('maxAccessSpeed'),
      dataServiceSpecUpdatePage.setThrottledSpeedInput('throttledSpeed'),
      dataServiceSpecUpdatePage.setUpstreamSpeedInput('upstreamSpeed'),
      dataServiceSpecUpdatePage.setDownstreamSpeedInput('downstreamSpeed'),
      dataServiceSpecUpdatePage.setSocialSitesInput('socialSites'),
      dataServiceSpecUpdatePage.setEntertainmentPackOptionsInput('entertainmentPackOptions'),
      dataServiceSpecUpdatePage.setRoamingDataSpeedInput('roamingDataSpeed'),
      dataServiceSpecUpdatePage.setRoamingDataVolumeInput('roamingDataVolume'),
      dataServiceSpecUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      dataServiceSpecUpdatePage.setCreatedByInput('createdBy'),
      dataServiceSpecUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      dataServiceSpecUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      dataServiceSpecUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await dataServiceSpecUpdatePage.getDataSpecIdInput()).to.eq('5', 'Expected dataSpecId value to be equals to 5');
    expect(await dataServiceSpecUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    expect(await dataServiceSpecUpdatePage.getMaxEntitlementInput()).to.eq(
      'maxEntitlement',
      'Expected MaxEntitlement value to be equals to maxEntitlement'
    );
    expect(await dataServiceSpecUpdatePage.getMaxAccessSpeedInput()).to.eq(
      'maxAccessSpeed',
      'Expected MaxAccessSpeed value to be equals to maxAccessSpeed'
    );
    expect(await dataServiceSpecUpdatePage.getThrottledSpeedInput()).to.eq(
      'throttledSpeed',
      'Expected ThrottledSpeed value to be equals to throttledSpeed'
    );
    expect(await dataServiceSpecUpdatePage.getUpstreamSpeedInput()).to.eq(
      'upstreamSpeed',
      'Expected UpstreamSpeed value to be equals to upstreamSpeed'
    );
    expect(await dataServiceSpecUpdatePage.getDownstreamSpeedInput()).to.eq(
      'downstreamSpeed',
      'Expected DownstreamSpeed value to be equals to downstreamSpeed'
    );
    expect(await dataServiceSpecUpdatePage.getSocialSitesInput()).to.eq(
      'socialSites',
      'Expected SocialSites value to be equals to socialSites'
    );
    expect(await dataServiceSpecUpdatePage.getEntertainmentPackOptionsInput()).to.eq(
      'entertainmentPackOptions',
      'Expected EntertainmentPackOptions value to be equals to entertainmentPackOptions'
    );
    expect(await dataServiceSpecUpdatePage.getRoamingDataSpeedInput()).to.eq(
      'roamingDataSpeed',
      'Expected RoamingDataSpeed value to be equals to roamingDataSpeed'
    );
    expect(await dataServiceSpecUpdatePage.getRoamingDataVolumeInput()).to.eq(
      'roamingDataVolume',
      'Expected RoamingDataVolume value to be equals to roamingDataVolume'
    );
    expect(await dataServiceSpecUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await dataServiceSpecUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await dataServiceSpecUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await dataServiceSpecUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await dataServiceSpecUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await dataServiceSpecUpdatePage.save();
    expect(await dataServiceSpecUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await dataServiceSpecComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DataServiceSpec', async () => {
    const nbButtonsBeforeDelete = await dataServiceSpecComponentsPage.countDeleteButtons();
    await dataServiceSpecComponentsPage.clickOnLastDeleteButton();

    dataServiceSpecDeleteDialog = new DataServiceSpecDeleteDialog();
    expect(await dataServiceSpecDeleteDialog.getDialogTitle()).to.eq('crmwebApp.dataServiceSpec.delete.question');
    await dataServiceSpecDeleteDialog.clickOnConfirmButton();

    expect(await dataServiceSpecComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
