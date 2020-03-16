import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { CustAddressComponentsPage, CustAddressDeleteDialog, CustAddressUpdatePage } from './cust-address.page-object';

const expect = chai.expect;

describe('CustAddress e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let custAddressComponentsPage: CustAddressComponentsPage;
  let custAddressUpdatePage: CustAddressUpdatePage;
  let custAddressDeleteDialog: CustAddressDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.loginWithOAuth('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CustAddresses', async () => {
    await navBarPage.goToEntity('cust-address');
    custAddressComponentsPage = new CustAddressComponentsPage();
    await browser.wait(ec.visibilityOf(custAddressComponentsPage.title), 5000);
    expect(await custAddressComponentsPage.getTitle()).to.eq('crmwebApp.custAddress.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(custAddressComponentsPage.entities), ec.visibilityOf(custAddressComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CustAddress page', async () => {
    await custAddressComponentsPage.clickOnCreateButton();
    custAddressUpdatePage = new CustAddressUpdatePage();
    expect(await custAddressUpdatePage.getPageTitle()).to.eq('crmwebApp.custAddress.home.createOrEditLabel');
    await custAddressUpdatePage.cancel();
  });

  it('should create and save CustAddresses', async () => {
    const nbButtonsBeforeCreate = await custAddressComponentsPage.countDeleteButtons();

    await custAddressComponentsPage.clickOnCreateButton();

    await promise.all([
      custAddressUpdatePage.setAddressIdInput('5'),
      custAddressUpdatePage.setAccountIdInput('accountId'),
      custAddressUpdatePage.accountTypeSelectLastOption(),
      custAddressUpdatePage.addressTypeSelectLastOption(),
      custAddressUpdatePage.addressLangSelectLastOption(),
      custAddressUpdatePage.setRoomInput('room'),
      custAddressUpdatePage.setFloorInput('floor'),
      custAddressUpdatePage.setBlockInput('block'),
      custAddressUpdatePage.setBuildingInput('building'),
      custAddressUpdatePage.setStreetEstateInput('streetEstate'),
      custAddressUpdatePage.setDistrictInput('district'),
      custAddressUpdatePage.setRegionInput('region'),
      custAddressUpdatePage.setAddress1Input('address1'),
      custAddressUpdatePage.setAddress2Input('address2'),
      custAddressUpdatePage.setAddress3Input('address3'),
      custAddressUpdatePage.setAddress4Input('address4'),
      custAddressUpdatePage.setAddress5Input('address5'),
      custAddressUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAddressUpdatePage.setCreatedByInput('createdBy'),
      custAddressUpdatePage.setLastUpdatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      custAddressUpdatePage.setLastUpdatedByInput('lastUpdatedBy'),
      custAddressUpdatePage.setTenantIdInput('tenantId'),
      custAddressUpdatePage.customerSelectLastOption()
    ]);

    expect(await custAddressUpdatePage.getAddressIdInput()).to.eq('5', 'Expected addressId value to be equals to 5');
    expect(await custAddressUpdatePage.getAccountIdInput()).to.eq('accountId', 'Expected AccountId value to be equals to accountId');
    const selectedFormattedAddress = custAddressUpdatePage.getFormattedAddressInput();
    if (await selectedFormattedAddress.isSelected()) {
      await custAddressUpdatePage.getFormattedAddressInput().click();
      expect(await custAddressUpdatePage.getFormattedAddressInput().isSelected(), 'Expected formattedAddress not to be selected').to.be
        .false;
    } else {
      await custAddressUpdatePage.getFormattedAddressInput().click();
      expect(await custAddressUpdatePage.getFormattedAddressInput().isSelected(), 'Expected formattedAddress to be selected').to.be.true;
    }
    expect(await custAddressUpdatePage.getRoomInput()).to.eq('room', 'Expected Room value to be equals to room');
    expect(await custAddressUpdatePage.getFloorInput()).to.eq('floor', 'Expected Floor value to be equals to floor');
    expect(await custAddressUpdatePage.getBlockInput()).to.eq('block', 'Expected Block value to be equals to block');
    expect(await custAddressUpdatePage.getBuildingInput()).to.eq('building', 'Expected Building value to be equals to building');
    expect(await custAddressUpdatePage.getStreetEstateInput()).to.eq(
      'streetEstate',
      'Expected StreetEstate value to be equals to streetEstate'
    );
    expect(await custAddressUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await custAddressUpdatePage.getRegionInput()).to.eq('region', 'Expected Region value to be equals to region');
    expect(await custAddressUpdatePage.getAddress1Input()).to.eq('address1', 'Expected Address1 value to be equals to address1');
    expect(await custAddressUpdatePage.getAddress2Input()).to.eq('address2', 'Expected Address2 value to be equals to address2');
    expect(await custAddressUpdatePage.getAddress3Input()).to.eq('address3', 'Expected Address3 value to be equals to address3');
    expect(await custAddressUpdatePage.getAddress4Input()).to.eq('address4', 'Expected Address4 value to be equals to address4');
    expect(await custAddressUpdatePage.getAddress5Input()).to.eq('address5', 'Expected Address5 value to be equals to address5');
    expect(await custAddressUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await custAddressUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await custAddressUpdatePage.getLastUpdatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdatedDate value to be equals to 2000-12-31'
    );
    expect(await custAddressUpdatePage.getLastUpdatedByInput()).to.eq(
      'lastUpdatedBy',
      'Expected LastUpdatedBy value to be equals to lastUpdatedBy'
    );
    expect(await custAddressUpdatePage.getTenantIdInput()).to.eq('tenantId', 'Expected TenantId value to be equals to tenantId');

    await custAddressUpdatePage.save();
    expect(await custAddressUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await custAddressComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last CustAddress', async () => {
    const nbButtonsBeforeDelete = await custAddressComponentsPage.countDeleteButtons();
    await custAddressComponentsPage.clickOnLastDeleteButton();

    custAddressDeleteDialog = new CustAddressDeleteDialog();
    expect(await custAddressDeleteDialog.getDialogTitle()).to.eq('crmwebApp.custAddress.delete.question');
    await custAddressDeleteDialog.clickOnConfirmButton();

    expect(await custAddressComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
