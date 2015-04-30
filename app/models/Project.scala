package models

import anorm._
import anorm.SqlParser._
import play.api.db.DB
import play.api.Play.current
/**
 * Created by Nikita on 24.04.2015.
 */
case class Project (name:String ,folder:String,owner:String)
object Project{
  val project = {
    get[String]("projectname") ~
      get[String]("folder") ~
      get[String]("owner") map {
      case name ~ folder ~ owner => Project(name, folder, owner)
    }
  }

  def create(name:String,folder:String,owner:User) = {
    DB.withConnection(implicit c=>{
      SQL("insert into Project(projectname,folder,owner) values({projectname},{folder},{owner})").on(
        'projectname->name,
        'folder->folder,
        'owner->owner.email
      ).executeUpdate()
    })
  }
  def delete(name:String) = {
    DB.withConnection(implicit c=>{
      SQL("delete from Project where projectname = {projectname}").on(
      'projectname -> name

    ).executeUpdate()
    })
  }
  def findByOwner(owner:User):Project = {
    DB.withConnection { implicit c =>
      SQL("select * from Project where owner = {owner}").on(
        'owner -> owner.email
      ).as(project.singleOpt) match {
        case Some(x) => x
        case None => EmptyProject
      }
    }
  }

    def all():List[Project] = {
    DB.withConnection(implicit c=>{
      SQL("select * from Project").as(project*)
    })
    }

}
object EmptyProject extends Project("empty","empty","empty")

