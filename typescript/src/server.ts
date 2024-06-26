import { Setup } from "./setup";
import { CustomerDatasource } from "./customer-datasource";
import { CustomerRepository } from "./customer-repository";

async function main() {
  const setup = new Setup();

  const customerRepository = new CustomerRepository(new CustomerDatasource());

  await customerRepository.init();

  setInterval(async () => {
    await customerRepository.insert();
  }, setup.insertRatePerSeconds * 1000);

  /* setInterval(async () => {
    const queries = [
      `
      SELECT Cod_User, Trade_Name, Legal_Name, CType 
      FROM customers 
      WHERE CType = 1
    `,
      `
      SELECT Cod_User, Trade_Name, Address_City, Address_State 
      FROM customers 
      WHERE Address_State = 'CA'
    `,
      `
      SELECT Cod_User, Legal_Name, Document 
      FROM customers 
      WHERE Document IS NOT NULL
    `,
      `
      SELECT Cod_User, Trade_Name, Phone 
      FROM customers 
      WHERE Phone = '123-456-7890
    `,
      `
      SELECT Cod_User, Legal_Name, Address_Zip 
      FROM customers 
      WHERE Address_Zip LIKE '90%'
    `,
      `
      SELECT Cod_User, Trade_Name, Mobile1, Address_City 
      FROM customers 
      WHERE Address_City = 'Los Angeles'
    `,
      `
      SELECT Cod_User, Trade_Name, Address_Complement 
      FROM customers 
      WHERE Address_Complement IS
      NOT NULL 
      ORDER BY Cod_User
    `,
      `
      SELECT Cod_User, Legal_Name, CType, Address_State 
      FROM customers 
      WHERE CType = 0
    `,
      `
      SELECT Cod_User, Trade_Name, Legal_Name 
      FROM customers 
      WHERE Trade_Name LIKE '%Corp%'
    `,
      `
      SELECT Cod_User, Trade_Name, Phone2 
      FROM customers 
      WHERE Phone2 IS NOT NULL 
      ORDER BY Trade_Name
    `,
    ];

    await pool
      .request()
      .query(queries[Math.floor(Math.random() * queries.length)]);
  }, setup.queryRatePerSeconds * 1000); */
}

main();
