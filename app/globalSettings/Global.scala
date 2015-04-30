package globalSettings

import java.util.Date

import models._
import play.api.Application
import play.api.GlobalSettings


/**
 * Created by Nikita on 5/1/2015.
 */
object Global extends GlobalSettings{

 override def onStart(app: Application) ={
if(User.all().size == 0 ){
  User.create("john@mail.ru","john","secret",false)
  val user = User.find("john@mail.ru")
  Project.create("Start","fold",user)
  val project = Project.findByOwner(user)
  Task.create("do it",project,false,new Date(),"fold")
  Task.create("kill bill",project,false,new Date(),"fold")
  Task.create("make coctail",project,false,new Date(),"fold")
}
  }
}
