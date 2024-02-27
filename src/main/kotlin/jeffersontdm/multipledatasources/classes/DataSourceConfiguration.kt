package jeffersontdm.multipledatasources.classes

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
   * Database config: post
   */
  @ConfigurationProperties(prefix = "app.datasource.post")
  @Bean
  @Primary
  fun postDataSourcePropperties(): DataSourceProperties {
    return DataSourceProperties()
  }

  @Bean
  @Primary
  fun postDataSource(datasourcePropperties: DataSourceProperties): DataSource {
    return datasourcePropperties
      .initializeDataSourceBuilder()
      .build()
  }

  @Bean
  @Primary
  fun postJDBCClient(dataSource: DataSource): JdbcClient = JdbcClient.create(dataSource)

  @Bean
  fun postDatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
      schemaLocations = listOf("classpath:posts-schema.sql")
      mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
  }

  /**
   * Database config: comment
   */
  @ConfigurationProperties(prefix = "app.datasource.comment")
  @Bean
  fun commentDataSourcePropperties(): DataSourceProperties {
    return DataSourceProperties()
  }

  @Bean
  @Qualifier("commentDataSourcePropperties")
  fun commentDataSource(datasourcePropperties: DataSourceProperties): DataSource {
    return datasourcePropperties
      .initializeDataSourceBuilder()
      .build()
  }

  @Bean
  @Qualifier("commentDataSource")
  fun commentJDBCClient(dataSource: DataSource): JdbcClient = JdbcClient.create(dataSource)

  @Bean
  fun commentDatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
      schemaLocations = listOf("classpath:comments-schema.sql")
      mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
  }
}