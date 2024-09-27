## Kotta
Kotta is a intuitive framework designed to simplify interaction with MongoDB in Kotlin.

## Features
- **Seamless MongoDB integration:** Kotta provides an easy-to-use abstraction for common MongoDB operations such as inserting, updating and querying documents. It is very inspired by Exposed and NestJS Mongo
- **Extensible and Flexible:** The framework is designed to be easily extendable, allowing you to add your own functionalities or adapt existing ones to fit your needs.

## Example Usage
```kotlin
@Document("users")
@Index("name", unique = true)
@Index("email", unique = true)
@Index("user_age", unique = true)
data class User(
    @Property("name", required = true) val name: String,
    @Property("user_age", required = true) val age: Int,
    @Property("email") val email: String
)

class UserCollection(database: MongoDatabase): KottaCollection<User>(User::class, database) {

    suspend fun findByEmail(email: String) = findBy("email" eq email)

    suspend fun save(user: User) = insert(user)

}

suspend fun printUserEmail(collection: UserCollection) {
    val user = collection.findByEmail("necessaryafter@email.com") ?: return

    println("Email: ${user.email}")
}
```



