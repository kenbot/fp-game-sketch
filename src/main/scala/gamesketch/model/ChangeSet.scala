package gamesketch.model

object ChangeSet {
  val Empty = new ChangeSet

  def forGame(f: Game => Game) = new ChangeSet(f, identity, Map())
  def forLevel(f: GameLevel => GameLevel) = new ChangeSet(identity, f, Map())
  def forEntity(eid: EntityId, f: Entity => Entity) =
    new ChangeSet(identity, identity, Map(eid -> f))
}

class ChangeSet(
    private val changeGame: Game => Game,
    private val changeLevel: GameLevel => GameLevel,
    private val changeEntities: Map[EntityId, Entity => Entity]) extends (Game => Game) {

  import scalaz._, Scalaz._

  implicit def endoMonoid[A]: Monoid[A => A] =
    Monoid.instance(_ compose _, identity)

  def this() = this(identity, identity, Map())

  def forGame(f: Game => Game): ChangeSet =
    new ChangeSet(f compose changeGame, changeLevel, changeEntities)

  def forLevel(f: GameLevel => GameLevel): ChangeSet =
    new ChangeSet(changeGame, f compose changeLevel, changeEntities)

  def forEntity(eid: EntityId, f: Entity => Entity): ChangeSet =
    new ChangeSet(changeGame, changeLevel, addToMap(changeEntities, eid, f))

  override def apply(g: Game): Game = {
    val entities2 = changeEntities.foldLeft(g.currentLevel.entities) {
      case (es, (eid, f)) => es.get(eid).fold(es)(e => es.updated(eid, f(e)))
    }

    val level2 = changeLevel(g.currentLevel.copy(entities = entities2))

    changeGame(g.copy(currentLevel = level2))
  }

  def merge(ch: ChangeSet): ChangeSet =
    new ChangeSet(
      ch.changeGame compose changeGame,
      ch.changeLevel compose changeLevel,
      mergeMap(ch.changeEntities, changeEntities))

  private def mergeMap[K, V: Semigroup](map1: Map[K,V], map2: Map[K,V]): Map[K,V] =
    map2.toList.foldLeft(map1) {
      case (map, (k,v)) => addToMap(map, k, v)
    }

  private def addToMap[K, V: Semigroup](map: Map[K,V], key: K, value: V): Map[K,V] =
    map.get(key).fold(map)(v => map.updated(key, v |+| value))
}
