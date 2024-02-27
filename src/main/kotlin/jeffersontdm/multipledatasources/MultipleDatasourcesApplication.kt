package jeffersontdm.multipledatasources

import jeffersontdm.multipledatasources.classes.Comment
import jeffersontdm.multipledatasources.classes.Post
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
    @Qualifier("commentJDBCClient") commentJDBCClient: JdbcClient
  ) = ApplicationRunner {
    val posts = postJDBCClient.sql("SELECT * FROM post").query(Post::class.java).list()
    val comments = commentJDBCClient.sql("SELECT * FROM comment").query(Comment::class.java).list()

    println("POSTS: $posts")
    println()
    println("COMMENTS: $comments")
  }
}

fun main(args: Array<String>) {
  runApplication<MultipleDatasourcesApplication>(*args)
}