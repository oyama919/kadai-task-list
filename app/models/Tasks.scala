package models

import java.time.ZonedDateTime

import scalikejdbc._
import skinny.orm._

case class Task(id: Option[Long],
                title: Option[String],
                content: String,
                createAt: ZonedDateTime,
                updateAt: ZonedDateTime)

object Task extends SkinnyCRUDMapper[Task] {

  override def tableName = "tasks"

  override def defaultAlias: Alias[Task] = createAlias("m")

  override def extract(rs: WrappedResultSet, n: ResultName[Task]): Task =
    autoConstruct(rs, n)

  private def toNamedValues(record: Task): Seq[(Symbol, Any)] = Seq(
    'title    -> record.title,
    'content     -> record.content,
    'createAt -> record.createAt,
    'updateAt -> record.updateAt
  )

  def create(task: Task)(implicit session: DBSession): Long =
    createWithAttributes(toNamedValues(task): _*)

  def update(task: Task)(implicit session: DBSession): Int =
    updateById(task.id.get).withAttributes(toNamedValues(task): _*)

}