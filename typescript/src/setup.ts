import yaml from "yaml";
import fs from "fs";
import path from "path";

interface SetupFile {
  typescript: {
    query_rate_per_seconds: number;
    insert_rate_per_seconds: number;
  };
}

export class Setup {
  private SETUP_YML_PATH = path.resolve("..", "setup.yml");

  query_rate_per_seconds!: number;

  insert_rate_per_seconds!: number;

  constructor() {
    const data = this.read();

    this.query_rate_per_seconds = data.typescript.query_rate_per_seconds;
    this.insert_rate_per_seconds = data.typescript.insert_rate_per_seconds;
  }

  read(): SetupFile {
    const data: SetupFile = yaml.parse(
      fs.readFileSync(this.SETUP_YML_PATH, { encoding: "utf-8" })
    );

    return data;
  }
}
