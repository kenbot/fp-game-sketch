package gamesketch.model



case class GameLevel(
    name: String,
    entities: Map[EntityId, Entity],
    region: Region) {

  def getEntity(eid: EntityId): Option[Entity] =
    entities.get(eid)

  def modifyEntity(eid: EntityId, f: Entity => Entity): GameLevel = {
    val e = entities.get(eid)
    val entities2 = e.map(f).fold(entities)(entities.updated(eid, _))
    copy(entities = entities2)
  }
}


