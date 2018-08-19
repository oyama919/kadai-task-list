package controllers

import forms.TaskForm
import play.api.data.Forms._
import play.api.data._
import play.api.mvc.AbstractController

trait TaskControllerSupport { this: AbstractController =>

  protected val form = Form(
    mapping(
      "id"    -> optional(longNumber),
      "title" -> nonEmptyText,
      "content"  -> nonEmptyText
    )(TaskForm.apply)(TaskForm.unapply)
  )

}