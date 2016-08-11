package com.machinomy.crdt.state

import com.github.nscala_time.time.Imports._

trait TombStone[A] {
  def next: A
}

object TombStone {
  implicit object DateTimeTombStone extends TombStone[DateTime] {
    override def next: DateTime = DateTime.now()
  }
}
