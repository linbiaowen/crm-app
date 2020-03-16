import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CommOptoutTypeComponentsPage, CommOptoutTypeDeleteDialog, CommOptoutTypeUpdatePage } from './comm-optout-type.page-object';

const expect = chai.expect;

describe('CommOptoutType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let commOptoutTypeComponentsPage: CommOptoutTypeComponentsPage;
  let commOptoutTypeUpdatePage: CommOptoutTypeUpdatePage;
  let commOptoutTypeDeleteDialog: CommOptoutTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CommOptoutTypes', async () => {
    await navBarPage.goToEntity('comm-optout-type');
    commOptoutTypeComponentsPage = new CommOptoutTypeComponentsPage();
    await browser.wait(ec.visibilityOf(commOptoutTypeComponentsPage.title), 5000);
    expect(await commOptoutTypeComponentsPage.getTitle()).to.eq('crmwebApp.commOptoutType.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(commOptoutTypeComponentsPage.entities), ec.visibilityOf(commOptoutTypeComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CommOptoutType page', async () => {
    await commOptoutTypeComponentsPage.clickOnCreateButton();
    commOptoutTypeUpdatePage = new CommOptoutTypeUpdatePage();
    expect(await commOptoutTypeUpdatePage.getPageTitle()).to.eq('crmwebApp.commOptoutType.home.createOrEditLabel');
    await commOptoutTypeUpdatePage.cancel();
  });

  it('should create and save CommOptoutTypes', async () => {
    const nbButtonsBeforeCreate = await commOptoutTypeComponentsPage.countDeleteButtons();

    await commOptoutTypeComponentsPage.clickOnCreateButton();

    await promise.all([
      commOptoutTypeUpdatePage.setOptoutTypeIdInput('optoutTypeId'),
      commOptoutTypeUpdatePage.setOptoutTypeInput('optoutType'),
      commOptoutTypeUpdatePage.setOptoutTypeDescInput('optoutTypeDesc'),
      commOptoutTypeUpdatePage.setStartDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commOptoutTypeUpdatePage.setEndDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commOptoutTypeUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commOptoutTypeUpdatePage.setCreatedByInput('createdBy'),
      commOptoutTypeUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      commOptoutTypeUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      commOptoutTypeUpdatePage.setTenantIdInput('tenantId')
    ]);

    expect(await commOptoutTypeUpdatePage.getOptoutTypeIdInput()).to.eq(
      'optoutTypeId',
      'Expected OptoutTypeId value to be equals to optoutTypeId'
    );
    expect(await commOptoutTypeUpdatePage.getOptoutTypeInput()).to.eq('optoutType', 'Expected OptoutType value to be equals to optoutType');
    expect(await commOptoutTypeUpdatePage.getOptoutTypeDescInput()).to.eq(
      'optoutTypeDesc',
      'Expected OptoutTypeDesc value to be equals to optoutTypeDesc'
    );
    expect(await commOptoutTypeUpdatePage.getStartDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected startDate value to be equals to 2000-12-31'
    );
    expect(await commOptoutTypeUpdatePage.getEndDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected endDate value to be equals to 2000-12-31'
    );
    expect(await commOptoutTypeUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await commOptoutTypeUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await commOptoutTypeUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await commOptoutTypeUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await commOptoutTypeUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await commOptoutTypeUpdatePage.save();
    expect(await commOptoutTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await commOptoutTypeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CommOptoutType', async () => {
    const nbButtonsBeforeDelete = await commOptoutTypeComponentsPage.countDeleteButtons();
    await commOptoutTypeComponentsPage.clickOnLastDeleteButton();

    commOptoutTypeDeleteDialog = new CommOptoutTypeDeleteDialog();
    expect(await commOptoutTypeDeleteDialog.getDialogTitle()).to.eq('crmwebApp.commOptoutType.delete.question');
    await commOptoutTypeDeleteDialog.clickOnConfirmButton();

    expect(await commOptoutTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
