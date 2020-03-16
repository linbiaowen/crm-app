import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CommMediaTypeComponentsPage, CommMediaTypeDeleteDialog, CommMediaTypeUpdatePage } from './comm-media-type.page-object';

const expect = chai.expect;

describe('CommMediaType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let commMediaTypeComponentsPage: CommMediaTypeComponentsPage;
  let commMediaTypeUpdatePage: CommMediaTypeUpdatePage;
  let commMediaTypeDeleteDialog: CommMediaTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CommMediaTypes', async () => {
    await navBarPage.goToEntity('comm-media-type');
    commMediaTypeComponentsPage = new CommMediaTypeComponentsPage();
    await browser.wait(ec.visibilityOf(commMediaTypeComponentsPage.title), 5000);
    expect(await commMediaTypeComponentsPage.getTitle()).to.eq('crmwebApp.commMediaType.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(commMediaTypeComponentsPage.entities), ec.visibilityOf(commMediaTypeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CommMediaType page', async () => {
    await commMediaTypeComponentsPage.clickOnCreateButton();
    commMediaTypeUpdatePage = new CommMediaTypeUpdatePage();
    expect(await commMediaTypeUpdatePage.getPageTitle()).to.eq('crmwebApp.commMediaType.home.createOrEditLabel');
    await commMediaTypeUpdatePage.cancel();
  });

  it('should create and save CommMediaTypes', async () => {
    const nbButtonsBeforeCreate = await commMediaTypeComponentsPage.countDeleteButtons();

    await commMediaTypeComponentsPage.clickOnCreateButton();

    await promise.all([
      commMediaTypeUpdatePage.setOptoutMediaIdInput('optoutMediaId'),
      commMediaTypeUpdatePage.setMediaTypeInput('mediaType'),
      commMediaTypeUpdatePage.setMediaTypeDescInput('mediaTypeDesc'),
      commMediaTypeUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commMediaTypeUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commMediaTypeUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commMediaTypeUpdatePage.setCreatedByInput('createdBy'),
      commMediaTypeUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commMediaTypeUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      commMediaTypeUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await commMediaTypeUpdatePage.getOptoutMediaIdInput()).to.eq(
      'optoutMediaId',
      'Expected OptoutMediaId value to be equals to optoutMediaId'
    );
    expect(await commMediaTypeUpdatePage.getMediaTypeInput()).to.eq('mediaType', 'Expected MediaType value to be equals to mediaType');
    expect(await commMediaTypeUpdatePage.getMediaTypeDescInput()).to.eq(
      'mediaTypeDesc',
      'Expected MediaTypeDesc value to be equals to mediaTypeDesc'
    );
    expect(await commMediaTypeUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await commMediaTypeUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await commMediaTypeUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await commMediaTypeUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await commMediaTypeUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await commMediaTypeUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await commMediaTypeUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await commMediaTypeUpdatePage.save();
    expect(await commMediaTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await commMediaTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CommMediaType', async () => {
    const nbButtonsBeforeDelete = await commMediaTypeComponentsPage.countDeleteButtons();
    await commMediaTypeComponentsPage.clickOnLastDeleteButton();

    commMediaTypeDeleteDialog = new CommMediaTypeDeleteDialog();
    expect(await commMediaTypeDeleteDialog.getDialogTitle()).to.eq('crmwebApp.commMediaType.delete.question');
    await commMediaTypeDeleteDialog.clickOnConfirmButton();

    expect(await commMediaTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
