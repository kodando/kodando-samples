package sample.viewmodel

object NextId {
  private var next = 1

  operator fun invoke(): String {
    return "id-${next++}"
  }
}