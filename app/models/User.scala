package models
import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current

/**
 * Created by Nikita on 24.04.2015.
 */
case class User(email:String,name:String,password:String)

object User {

  val user = {
      get[String]("email") ~
      get[String]("fullname") ~
      get[String]("password") map {
      case  email ~ fullname ~ password => User(email, fullname, password)
    }
  }

  def all(): List[User] = DB.withConnection { implicit c => {
    SQL("select * from User").as(user *)
  }
  }

  def create(email: String, name: String, password: String, isAdmin: Boolean) = {
    DB.withConnection { implicit c => {
      SQL("insert into User (email,password,fullname,isAdmin) values ({email},{password},{fullname},{isAdmin})").on(
        'email -> email,
        'password -> password,
        'fullname -> name,
        'isAdmin -> isAdmin
      ).executeUpdate()
    }
    }

  }

  def delete(email: String) = {
    DB.withConnection(implicit c => {
      SQL("delete from User where email = {email}").on(
        'email -> email
      ).executeUpdate()
    })
  }

  def authenticate(email: String, password: String): User = {
    DB.withConnection(implicit c => {
      SQL("select * from User where email = {email}").on(
        'email -> email
      ).as(user.singleOpt) match {
        case Some(x) if x.password.equals(password) => x
        case None => EmptyUser
      }
    })
  }

  def find(email: String): User = {
    DB.withConnection(implicit c => {
      SQL("select * from User where email = {email}").on(
        'email -> email
      ).as(user.singleOpt)match {
        case Some(x) => x
        case None => EmptyUser
      }
    })
  }
}

object EmptyUser extends User("empty","empty","empty")
