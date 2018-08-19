package controllers

import javax.inject._

import models.Task
import play.api.i18n.{ I18nSupport, Messages }
import play.api.mvc._
import scalikejdbc.AutoSession

@Singleton
class DeleteTaskController @Inject()(components: ControllerComponents)
  extends AbstractController(components) with I18nSupport {

  def delete(taskId: Long): Action[AnyContent] = Action { implicit request =>
    implicit val session = AutoSession
    val result           = Task.deleteById(taskId)
    if (result > 0) {
      Redirect(routes.GetTasksController.index())
    } else {
      InternalServerError(Messages("DeleteTaskError"))
    }
  }

}