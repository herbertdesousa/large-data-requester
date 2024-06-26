import dotenv from "dotenv";
dotenv.config();

import { CustomerDatasource } from "./customer-datasource";
import { CustomerRepository } from "./customer-repository";

async function main() {
  const customerRepository = new CustomerRepository(new CustomerDatasource());

  await customerRepository.init();

  setInterval(async () => {
    const max = Number(process.env.INSERT_RATE_PER_BATCH) ?? 5;

    for (let i = 0; i < max; i++) {
      await customerRepository.insert();
      console.log("inserted");
    }
  }, Number(process.env.INSERT_BATCH_INTERVAL_IN_SECONDS) ?? 1000);

  setInterval(async () => {
    const max = Number(process.env.QUERY_RATE_PER_BATCH) ?? 5;

    for (let i = 0; i < max; i++) {
      await customerRepository.query();
      console.log("queried");
    }
  }, Number(process.env.QUERY_BATCH_INTERVAL_IN_SECONDS) ?? 1000);
}

main();
