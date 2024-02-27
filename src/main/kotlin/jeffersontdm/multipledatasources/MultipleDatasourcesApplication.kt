package jeffersontdm.multipledatasources

import jeffersontdm.multipledatasources.dto.Table02
import jeffersontdm.multipledatasources.dto.Table01
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.simple.JdbcClient

@SpringBootApplication
class MultipleDatasourcesApplication {

  @Bean
  fun runner(
    postJDBCClient: JdbcClient,
    @Qualifier("table02DataSource") commentJDBCClient: JdbcClient
  ) = ApplicationRunner {
    val table01 = postJDBCClient
      .sql("SELECT * FROM table_01")
      .query(Table01::class.java)
      .list()
    val table02 = commentJDBCClient
      .sql("SELECT * FROM table_02")
      .query(Table02::class.java)
      .list()

    println("TABLE_01: $table01")
    println("TABLE_02: $table02")
  }
}

fun main(args: Array<String>) {
  runApplication<MultipleDatasourcesApplication>(*args)
}