import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ResourceSpecificationComponentsPage,
  ResourceSpecificationDeleteDialog,
  ResourceSpecificationUpdatePage
} from './resource-specification.page-object';

const expect = chai.expect;

describe('ResourceSpecification e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let resourceSpecificationComponentsPage: ResourceSpecificationComponentsPage;
  let resourceSpecificationUpdatePage: ResourceSpecificationUpdatePage;
  let resourceSpecificationDeleteDialog: ResourceSpecificationDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ResourceSpecifications', async () => {
    await navBarPage.goToEntity('resource-specification');
    resourceSpecificationComponentsPage = new ResourceSpecificationComponentsPage();
    await browser.wait(ec.visibilityOf(resourceSpecificationComponentsPage.title), 5000);
    expect(await resourceSpecificationComponentsPage.getTitle()).to.eq('crmwebApp.resourceSpecification.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(resourceSpecificationComponentsPage.entities), ec.visibilityOf(resourceSpecificationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ResourceSpecification page', async () => {
    await resourceSpecificationComponentsPage.clickOnCreateButton();
    resourceSpecificationUpdatePage = new ResourceSpecificationUpdatePage();
    expect(await resourceSpecificationUpdatePage.getPageTitle()).to.eq('crmwebApp.resourceSpecification.home.createOrEditLabel');
    await resourceSpecificationUpdatePage.cancel();
  });

  it('should create and save ResourceSpecifications', async () => {
    const nbButtonsBeforeCreate = await resourceSpecificationComponentsPage.countDeleteButtons();

    await resourceSpecificationComponentsPage.clickOnCreateButton();

    await promise.all([
      resourceSpecificationUpdatePage.setResourceSpecIdInput('resourceSpecId'),
      resourceSpecificationUpdatePage.resourceTypeSelectLastOption(),
      resourceSpecificationUpdatePage.setServiceSpecIdInput('serviceSpecId'),
      resourceSpecificationUpdatePage.setRfsInput('rfs'),
      resourceSpecificationUpdatePage.setRfsParmsInput('rfsParms'),
      resourceSpecificationUpdatePage.setRemarksInput('remarks'),
      resourceSpecificationUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      resourceSpecificationUpdatePage.setCreatedByInput('createdBy'),
      resourceSpecificationUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      resourceSpecificationUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      resourceSpecificationUpdatePage.setTenantIdInput('tenantId'),
      resourceSpecificationUpdatePage.cfsServiceSpecSelectLastOption()
    ]);

    expect(await resourceSpecificationUpdatePage.getResourceSpecIdInput()).to.eq(
      'resourceSpecId',
      'Expected ResourceSpecId value to be equals to resourceSpecId'
    );
    expect(await resourceSpecificationUpdatePage.getServiceSpecIdInput()).to.eq(
      'serviceSpecId',
      'Expected ServiceSpecId value to be equals to serviceSpecId'
    );
    expect(await resourceSpecificationUpdatePage.getRfsInput()).to.eq('rfs', 'Expected Rfs value to be equals to rfs');
    expect(await resourceSpecificationUpdatePage.getRfsParmsInput()).to.eq('rfsParms', 'Expected RfsParms value to be equals to rfsParms');
    expect(await resourceSpecificationUpdatePage.getRemarksInput()).to.eq('remarks', 'Expected Remarks value to be equals to remarks');
    expect(await resourceSpecificationUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await resourceSpecificationUpdatePage.getCreatedByInput()).to.eq(
      'createdBy',
      'Expected CreatedBy value to be equals to createdBy'
    );
    expect(await resourceSpecificationUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await resourceSpecificationUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await resourceSpecificationUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await resourceSpecificationUpdatePage.save();
    expect(await resourceSpecificationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await resourceSpecificationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ResourceSpecification', async () => {
    const nbButtonsBeforeDelete = await resourceSpecificationComponentsPage.countDeleteButtons();
    await resourceSpecificationComponentsPage.clickOnLastDeleteButton();

    resourceSpecificationDeleteDialog = new ResourceSpecificationDeleteDialog();
    expect(await resourceSpecificationDeleteDialog.getDialogTitle()).to.eq('crmwebApp.resourceSpecification.delete.question');
    await resourceSpecificationDeleteDialog.clickOnConfirmButton();

    expect(await resourceSpecificationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
