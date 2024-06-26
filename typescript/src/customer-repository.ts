import sql from "mssql";

import { CustomerDatasource } from "./customer-datasource";

export class CustomerRepository {
  private pool: sql.ConnectionPool;

  constructor(private customerDatasource: CustomerDatasource) {
    this.pool = new sql.ConnectionPool({
      server: "localhost",
      user: "sa",
      password: "SqlServer2019!",
      database: "pulsar",
      options: {
        trustedConnection: true,
        trustServerCertificate: true,
      },
    });
  }

  async init() {
    await this.pool.connect();
  }

  async insert() {
    const customer = this.customerDatasource.randomCustomer();

    const req = this.pool.request();

    req.input("Cod_User", customer.Cod_User);
    req.input("Trade_Name", customer.Trade_Name);
    req.input("Legal_Name", customer.Legal_Name);
    req.input("CType", customer.CType);
    req.input("Document", customer.Document);
    req.input("Phone", customer.Phone);
    req.input("Phone2", customer.Phone2);
    req.input("Mobile1", customer.Mobile1);
    req.input("Address_Street", customer.Address_Street);
    req.input("Address_Number", customer.Address_Number);
    req.input("Address_Complement", customer.Address_Complement);
    req.input("Address_Neighborhood", customer.Address_Neighborhood);
    req.input("Address_Zip", customer.Address_Zip);
    req.input("Address_City", customer.Address_City);
    req.input("Address_State", customer.Address_State);

    await req.query`
      INSERT INTO customers(
        [Trade_Name],
        [Legal_Name],
        [CType],
        [Document],
        [Phone],
        [Phone2],
        [Mobile1],
        [Address_Street],
        [Address_Number],
        [Address_Complement],
        [Address_Neighborhood],
        [Address_Zip],
        [Address_City],
        [Address_State]
      ) VALUES (
        @Trade_Name,
        @Legal_Name,
        @CType,
        @Document,
        @Phone,
        @Phone2,
        @Mobile1,
        @Address_Street,
        @Address_Number,
        @Address_Complement,
        @Address_Neighborhood,
        @Address_Zip,
        @Address_City,
        @Address_State
      )
    `;
  }
}
