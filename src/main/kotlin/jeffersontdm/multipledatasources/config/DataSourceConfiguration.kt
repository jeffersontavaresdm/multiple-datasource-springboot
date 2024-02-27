package jeffersontdm.multipledatasources.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer
import org.springframework.boot.sql.init.DatabaseInitializationMode
import org.springframework.boot.sql.init.DatabaseInitializationSettings
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.core.simple.JdbcClient
import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {

  /**
   * Database config: table_01
   */
  @ConfigurationProperties(prefix = "app.datasource.table01")
  @Bean
  @Primary
  fun table01DataSourcePropperties(): DataSourceProperties {
    return DataSourceProperties()
  }

  @Bean
  @Primary
  fun table01DataSource(datasourcePropperties: DataSourceProperties): DataSource {
    return datasourcePropperties
      .initializeDataSourceBuilder()
      .build()
  }

  @Bean
  @Primary
  fun table01JDBCClient(dataSource: DataSource): JdbcClient = JdbcClient.create(dataSource)

  @Bean
  fun table01DatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
      schemaLocations = listOf("classpath:table01-schema.sql")
      mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
  }

  /**
   * Database config: table_02
   */
  @ConfigurationProperties(prefix = "app.datasource.table02")
  @Bean
  fun table02DataSourcePropperties(): DataSourceProperties {
    return DataSourceProperties()
  }

  @Bean
  @Qualifier("table02DataSourcePropperties")
  fun table02DataSource(datasourcePropperties: DataSourceProperties): DataSource {
    return datasourcePropperties
      .initializeDataSourceBuilder()
      .build()
  }

  @Bean
  @Qualifier("table02DataSource")
  fun table02JDBCClient(dataSource: DataSource): JdbcClient = JdbcClient.create(dataSource)

  @Bean
  fun table02DatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
      schemaLocations = listOf("classpath:table02-schema.sql")
      mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
  }
}