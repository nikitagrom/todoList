package controllers
import models._
import java.util.Date

import models.{Task, Project, User}
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    val user = User.find("john@mail.ru")
    val project = Project.findByOwner(user)
    Ok(views.html.index(List(project),Task.findByProject(project)))
  }

}