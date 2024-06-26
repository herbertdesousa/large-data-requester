package bad1

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import random.RandomData

object CitiesDAO : Table("cities") {
    val Cod_City = integer("Cod_City").autoIncrement().uniqueIndex()
    val City_Name = varchar("City_Name", 255).nullable()
}

object NeighborhoodsDAO : Table("neighborhoods") {
    val Cod_Neighborhood = integer("Cod_Neighborhood").autoIncrement().uniqueIndex()
    val Neighborhood_Name = varchar("Neighborhood_Name", 255).nullable()
}

object CustomerDAO : Table("customers") {
    val Cod_User = integer("Cod_User").autoIncrement().uniqueIndex()
    val Trade_Name = varchar("Trade_Name", 255).nullable()
    val Legal_Name = varchar("Legal_Name", 255).nullable()
    // 0 = PF   1 = PJ
    val CType = integer("CType").default(0)
    val Document = varchar("Document", 255).nullable()
    val Document2 = varchar("Document2", 255).nullable()
    val Document3 = varchar("Document3", 255).nullable()
    val Phone = varchar("Phone", 255).nullable()
    val Phone2 = varchar("Phone2", 255).nullable()
    val Mobile1 = varchar("Mobile1", 255).nullable()
    // address
    val Address_Street = varchar("Address_Street", 255).nullable()
    val Address_Number = varchar("Address_Number", 50).nullable()
    val Address_Complement = varchar("Address_Complement", 255).nullable()
    val Address_Neighborhood = integer("Address_Neighborhood") references NeighborhoodsDAO.Cod_Neighborhood
    val Address_Zip = varchar("Address_Zip", 20).nullable()
    val Address_City = integer("Address_City") references CitiesDAO.Cod_City
    val Address_State = varchar("Address_State", 3).nullable()
    // address 2
    val Address_Street2 = varchar("Address_Street2", 255).nullable()
    val Address_Number2 = varchar("Address_Number2", 50).nullable()
    val Address_Complement2 = varchar("Address_Complement2", 255).nullable()
    val Address_Neighborhood2 = integer("Address_Neighborhood2") references NeighborhoodsDAO.Cod_Neighborhood
    val Address_Zip2 = varchar("Address_Zip2", 20).nullable()
    val Address_City2 = integer("Address_City2") references CitiesDAO.Cod_City
    val Address_State2 = varchar("Address_State2", 3).nullable()
    // address 3
    val Address_Street3 = varchar("Address_Street3", 355).nullable()
    val Address_Number3 = varchar("Address_Number3", 50).nullable()
    val Address_Complement3 = varchar("Address_Complement3", 355).nullable()
    val Address_Neighborhood3 = integer("Address_Neighborhood3") references NeighborhoodsDAO.Cod_Neighborhood
    val Address_Zip3 = varchar("Address_Zip3", 30).nullable()
    val Address_City3 = integer("Address_City3") references CitiesDAO.Cod_City
    val Address_State3 = varchar("Address_State3", 3).nullable()
    //
    val Comments = varchar("Comments", 255).nullable()
}

fun main() {
    Database.connect(
        url = "jdbc:sqlserver://localhost:1433;databaseName=pulsar",
        user = "sa",
        password = "SqlServer2019!"
    )

    transaction {
        SchemaUtils.create(CitiesDAO, NeighborhoodsDAO, CustomerDAO)

        List(5_000) {
            CustomerDAO.insert {
                val type = (0..1).random()

                it[Trade_Name] = RandomData.name()
                it[Legal_Name] = RandomData.name()
                it[CType] = type
                it[Document] = if (type == 1) RandomData.legalEntityDocument() else RandomData.naturalPersonDocument()
                it[Document2] = if (type == 1) RandomData.legalEntityDocument() else RandomData.naturalPersonDocument()
                it[Document3] = if (type == 1) RandomData.legalEntityDocument() else RandomData.naturalPersonDocument()
                it[Phone] = RandomData.phone()
                it[Phone2] = RandomData.phone()
                it[Mobile1] = RandomData.phone()
                // address
                val address = RandomData.address()
                it[Address_Street] = address.street
                it[Address_Number] = address.number
                it[Address_Complement] = address.complement
                it[Address_Neighborhood] = NeighborhoodsDAO.insert {
                    it[Neighborhood_Name] = address.neighborhood
                } get NeighborhoodsDAO.Cod_Neighborhood
                it[Address_Zip] = address.zip
                it[Address_City] = CitiesDAO.insert {
                    it[City_Name] = address.city
                } get CitiesDAO.Cod_City
                it[Address_State] = address.state
                // address 2
                val address2 = RandomData.address()
                it[Address_Street2] = address2.street
                it[Address_Number2] = address2.number
                it[Address_Complement2] = address2.complement
                it[Address_Neighborhood2] = NeighborhoodsDAO.insert {
                    it[Neighborhood_Name] = address2.neighborhood
                } get NeighborhoodsDAO.Cod_Neighborhood
                it[Address_Zip2] = address2.zip
                it[Address_City2] = CitiesDAO.insert {
                    it[City_Name] = address.city
                } get CitiesDAO.Cod_City
                it[Address_State2] = address2.state
                // address 3
                val address3 = RandomData.address()
                it[Address_Street3] = address3.street
                it[Address_Number3] = address3.number
                it[Address_Complement3] = address3.complement
                it[Address_Neighborhood3] = NeighborhoodsDAO.insert {
                    it[Neighborhood_Name] = address3.neighborhood
                } get NeighborhoodsDAO.Cod_Neighborhood
                it[Address_Zip3] = address3.zip
                it[Address_City3] = CitiesDAO.insert {
                    it[City_Name] = address.city
                } get CitiesDAO.Cod_City
                it[Address_State3] = address3.state
                //
                it[Comments] = "asda das dad"
            }
        }
    }
}
