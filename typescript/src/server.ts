import { Setup } from "./setup";

const READ_RATE_IN_MS = 1000;

async function main() {
  let setup = new Setup();

  setInterval(() => {
    const changed = setup.read();

    const hasSetupChanged =
      setup.insert_rate_per_seconds !==
        changed.typescript.insert_rate_per_seconds ||
      setup.query_rate_per_seconds !==
        changed.typescript.query_rate_per_seconds;

    setup.insert_rate_per_seconds = changed.typescript.insert_rate_per_seconds;
    setup.query_rate_per_seconds = changed.typescript.query_rate_per_seconds;

    if (hasSetupChanged) {
      console.log(
        `CHANGED: insert rate=${setup.insert_rate_per_seconds}s, query rate=${setup.query_rate_per_seconds}s`
      );
    }
  }, READ_RATE_IN_MS);
}

main();
