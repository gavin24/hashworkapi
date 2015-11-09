package repository.common.education
import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.iteratee.Iteratee
import com.websudos.phantom.keys.PartitionKey
import conf.connection.DataConnection
import domain.common.education.Evaluation

import scala.concurrent.Future

sealed class EvaluationRepository extends CassandraTable[EvaluationRepository,Evaluation]{
  object id extends StringColumn(this) with PartitionKey[String]
  object name extends StringColumn(this)
  override def fromRow(r: Row): Evaluation = {
    Evaluation(id(r),name(r))
  }
}

object EvaluationRepository extends EvaluationRepository with RootConnector {
  override lazy val tableName = "evaluation"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(evaluation: Evaluation): Future[ResultSet] = {
    insert
      .value(_.id, evaluation.id)
      .value(_.name, evaluation.name)
      .future()
  }

  def findById(id: String):Future[Option[Evaluation]] = {
    select.where(_.id eqs id).one()
  }
  def findAll: Future[Seq[Evaluation]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def deleteById(id:String): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}
