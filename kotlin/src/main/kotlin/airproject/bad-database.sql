CREATE TABLE customers
(
    Cod_User             SERIAL PRIMARY KEY,
    Trade_Name           VARCHAR(255),
    CType                VARCHAR(255),
    CDocument            VARCHAR(255),
    Address_Street       VARCHAR(255),
    Address_Number       VARCHAR(50),
    Address_Complement   VARCHAR(255),
    Address_Neighborhood VARCHAR(255),
    Address_Zip          VARCHAR(20),
    Address_City         VARCHAR(255),
    Address_State        VARCHAR(255)
);

CREATE TABLE representatives
(
    Cod_Representative SERIAL PRIMARY KEY,
    Trade_Name         VARCHAR(255),
    RType              VARCHAR(255),
    Cod_User           INT NOT NULL,
    FOREIGN KEY (Cod_User) REFERENCES customers (Cod_User)
);

CREATE TABLE papers
(
    COd_Paper        SERIAL PRIMARY KEY,
    PType            VARCHAR(255),
    Add_bank_details BIT,
    Add_Signatures   BIT,
    Cod_User         INT NOT NULL,
    FOREIGN KEY (Cod_User) REFERENCES customers (Cod_User)
);


CREATE TABLE paper_blocks
(
    Cod_Block   SERIAL PRIMARY KEY,
    Title       VARCHAR(255),
    Description TEXT,
    Price       FLOAT,
    Cod_Paper   INT NOT NULL,
    Materials   TEXT,
    FOREIGN KEY (Cod_Paper) REFERENCES papers (Cod_Paper)
);

CREATE TABLE paper_block_places
(
    Cod_Place SERIAL PRIMARY KEY,
    Room      VARCHAR(255),
    Floor     VARCHAR(255),
    Cod_Block INT NOT NULL,
    FOREIGN KEY (Cod_Block) REFERENCES paper_blocks (Cod_Block)
);

CREATE TABLE paper_block_place_devices
(
    Cod_Device SERIAL PRIMARY KEY,
    DType      VARCHAR(255),
    Brand      VARCHAR(255),
    Capacity   VARCHAR(255),
    DMode      VARCHAR(255),
    Quantity   INT,
    Cod_Place  INT NOT NULL,
    FOREIGN KEY (Cod_Place) REFERENCES paper_block_places (Cod_Place)
);
