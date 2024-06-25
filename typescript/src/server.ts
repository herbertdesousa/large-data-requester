import { CustomerDatasource } from "./customer-datasource";
import { Setup } from "./setup";

async function main() {
  const setup = new Setup();

  // const customerDatasource = new CustomerDatasource();
  // console.log(customerDatasource.randomCustomer());

  const insertInterval = setInterval(() => {
    console.log("insert");
  }, setup.insertRatePerSeconds * 1000);

  const queryInterval = setInterval(() => {
    console.log("query");
  }, setup.queryRatePerSeconds * 1000);

  setInterval(() => {}, 100);
}

main();
