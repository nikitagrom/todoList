
import org.junit.Before
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
import models._
import java.util.Date



/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends Specification {

"User List" should {
  "contains user " in {
    running(FakeApplication()) {
      User.create("John","john@email.com","qwerty",false)

      User.all() must have size (1)
    }
  }
}
  "User List" should {
    "be empty list" in {
      running(FakeApplication()){
        User.delete("john@email.com")


        User.all() must beEmpty
      }
    }
    "User name" should{
      "be Mike" in {
        running(FakeApplication()){
          User.create("mike@gmail.com","mike","qwerty",false)
          val user = User.authenticate("mike@gmail.com","qwerty")
          user.name must equalTo("mike")
        }
      }
    }
  }
  "User name " should{
    "be Empty" in{
      running(FakeApplication()){
        val emptyUser = User.authenticate("hohn@jask.com","qwer")
        emptyUser.name must equalTo("empty")
      }
    }
  }


"Project name" should {
    "Project" in {
      running(FakeApplication()){
        User.create("john@email.com","john","qwerty",false)
        val user = User.find("john@email.com")
        Project.create("Project","folder",user)
        val project =  Project.findByOwner(user)
        project.name must equalTo("Project")
      }
    }
  }
  "Task name" should {
    "Task" in {
      running(FakeApplication()){
        User.create("sergio@gmail.com","sergio","qwerty",false)
       val user =  User.find("sergio@gmail.com")
        Project.create("Project1","folder",user)
        val project = Project.findByOwner(user)
        Task.create("charge phone",project,false,new Date(),project.folder)
     //   Task.create("buy milk",project,false,"31.02.2016",project.folder)
        val task = Task.findByProject(project)
        task.head.name must equalTo("charge phone")


      }
    }
  }



}
