package repository.company

import conf.connection.DataConnection

/**
 * Created by hashcode on 2015/11/17.
 */

  abstract class TestAPI {
    implicit val keyspace = DataConnection.keySpace
    implicit val session = DataConnection.session

  }


