package models

import anorm._
import anorm.SqlParser._
import java.util.Date
import play.api.db.DB
import play.api.Play.current
/**
 * Created by Nikita on 30.04.2015.
 */
case class Task(id:Long,name:String,project:String,isDone:Boolean,dueDate:Date,folder:String)
object Task{
  val task = {
    get[Long]("id")~
    get[String]("name")~
    get[String]("project")~
    get[Boolean]("isDone")~
    get[Date]("dueDate")~
    get[String]("folder") map{
      case id~name~project~isDone~dueDate~folder=>Task(id,name,project,isDone,dueDate,folder)
    }

  }
  def create(name:String,project: Project,isDone:Boolean,dueDate:Date,folder:String)={
    DB.withConnection(implicit c=>{
      SQL("insert into Task (name,project,isDone,dueDate,folder) values({name},{projectName},{isDone}" +
        ",{dueDate},{folder})").on(
      'name->name,
      'projectName->project.name,
      'isDone->isDone,
      'dueDate->dueDate,
      'folder->folder
        ).executeUpdate()
    })
  }
  def delete(project:Project)={
    DB.withConnection(implicit c=>{
      SQL("delete from Task where project = {projectName}").on(
      'projectName->project.name
      ).executeUpdate()
    })
  }
  def findByProject(project: Project)={
    DB.withConnection(implicit c=>{
      SQL("select * from Task where project = {projectName}").on(
      'projectName->project.name
      ).as(task*)
    })
  }
}

