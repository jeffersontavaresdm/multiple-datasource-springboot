# Multiplos Bancos de Dados com Spring Boot e PostgreSQL
Neste repositório, encontramos uma aplicação Spring Boot que trabalha com dois bancos de dados separados, cada um com suas próprias tabelas e schemas. As configurações são armazenadas nos arquivos YAML (`application.yaml` e `docker-compose.yaml`).

### Configurações de Banco de Dados
As configurações de banco de dados estão contidas nos arquivos `DataSourceConfiguration.kt` e `application.yaml`. Cada banco de dados tem suas próprias configurações de URL, nome de usuário e senha.

````
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

// Restante do código omitido...
````
```
app:
  datasource:
    post:
      url: jdbc:postgresql://localhost:5432/pdb
      username: pdb
      password: pdb
    comment:
      url: jdbc:postgresql://localhost:5433/cdb
      username: cdb
      password: cdb
```

### Inicialização das Tabelas
Os arquivos de schema `table01-schema.sql` e `table02-schema.sql` são carregados dinamicamente quando a aplicação é iniciada. Essas definições estão contidas no arquivo `DataSourceConfiguration.kt`.
```
@Bean
fun postDatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
        schemaLocations = listOf("classpath:table01-schema.sql")
        mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
}

@Bean
fun commentDatabaseInitializer(dataSource: DataSource): DataSourceScriptDatabaseInitializer {
    val settings = DatabaseInitializationSettings().apply {
        schemaLocations = listOf("classpath:table02-schema.sql")
        mode = DatabaseInitializationMode.ALWAYS
    }

    return DataSourceScriptDatabaseInitializer(dataSource, settings);
}
```

### Executando a Aplicação
Antes de executar a aplicação, certifique-se de que os containers Docker estão rodando com base no arquivo `docker-compose.yaml`. Em seguida, execute a aplicação com o comando:

`./mvnw spring-boot:run`

###

Espero que esse guia ajude a entender melhor a configuração e execução de uma aplicação multi-banco de dados com Spring Boot.

#

<br>

<div align="center">
  <a  href="https://github.com/jeffersontavaresdm">
    <img width="30%" src="https://github.com/jeffersontavaresdm/jeffersontavaresdm/blob/main/images/rs.gif" width="25"/>
  </a>
</div>
