import path from "path";
import fs from "fs";

interface Customer {
  Cod_User: string;
  Trade_Name: string;
  Legal_Name: string;
  CType: string;
  Document: string;
  Phone: string;
  Phone2: string;
  Mobile1: string;
  Address_Street: string;
  Address_Number: string;
  Address_Complement: string;
  Address_Neighborhood: string;
  Address_Zip: string;
  Address_City: string;
  Address_State: string;
}

export class CustomerDatasource {
  private PATH = path.resolve("..", "customers.csv");

  private customers: Customer[] = [];

  constructor() {
    this.read();
  }

  randomCustomer(): Customer {
    return this.customers[Math.floor(Math.random() * this.customers.length)];
  }

  private read() {
    const data = fs.readFileSync(this.PATH, { encoding: "utf-8" });
    const cols = data.split("\r\n");

    for (const row of cols) {
      const [
        Cod_User,
        Trade_Name,
        Legal_Name,
        CType,
        Document,
        Phone,
        Phone2,
        Mobile1,
        Address_Street,
        Address_Number,
        Address_Complement,
        Address_Neighborhood,
        Address_Zip,
        Address_City,
        Address_State,
      ] = row.split(",");

      this.customers.push({
        Cod_User,
        Trade_Name,
        Legal_Name,
        CType,
        Document,
        Phone,
        Phone2,
        Mobile1,
        Address_Street,
        Address_Number,
        Address_Complement,
        Address_Neighborhood,
        Address_Zip,
        Address_City,
        Address_State,
      });
    }
  }
}
